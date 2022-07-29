from tensorflow import keras
from keras.models import Sequential
from keras.layers import Dense, Dropout, Flatten
from keras.layers import Conv2D, MaxPooling2D, BatchNormalization
from keras.layers import GlobalAveragePooling2D

from tensorflow.compat.v1 import ConfigProto
from tensorflow.compat.v1 import InteractiveSession

config = ConfigProto()
config.gpu_options.allow_growth = True
session = InteractiveSession(config=config)

import gzip
import pickle
f = gzip.open('cifar10.pkl.gz', 'rb')
data = pickle.load(f, encoding="bytes")
f.close()
(x_train, y_train), (x_test, y_test) = data


input_shape = (x_train.shape[1],
               x_train.shape[2],
               1 if len(x_train.shape) == 3 else x_train.shape[3])
x_train = x_train.astype('float32')
x_test = x_test.astype('float32')
x_train = x_train / 255
x_test = x_test / 255
batch_size = 128
epochs = 2000
img_rows, img_cols = x_train.shape[1], x_train.shape[2]

num_classes = 10

model = Sequential()
model.add(Conv2D(64, (3, 3), activation='relu', input_shape=input_shape))
model.add(Conv2D(64, (3, 3), activation='relu'))
model.add(BatchNormalization())
model.add(MaxPooling2D())
model.add(Dropout(0.25))
model.add(Conv2D(128, (3, 3), activation='relu'))
model.add(Conv2D(128, (3, 3), activation='relu'))
model.add(BatchNormalization())
model.add(MaxPooling2D())
model.add(Dropout(0.25))
model.add(Conv2D(256, (3, 3), activation='relu'))
model.add(Conv2D(256, (1, 1), activation='relu'))
model.add(BatchNormalization())
model.add(Dropout(0.25))
model.add(GlobalAveragePooling2D())
model.add(Dense(128))
model.add(BatchNormalization())
model.add(Dropout(0.5))
model.add(Dense(units=num_classes, activation='softmax'))

model.summary()

model.compile(loss='sparse_categorical_crossentropy', optimizer='Adam', metrics=['accuracy'])

early_stopping = keras.callbacks.EarlyStopping(monitor='val_accuracy', min_delta=0.001, patience=5, verbose=0,
                                               mode='max')

history = model.fit(x_train, y_train, batch_size=batch_size, epochs=epochs, verbose=1, validation_data=(x_test, y_test),
                    callbacks=[early_stopping])

score = model.evaluate(x_test, y_test)

print('Test loss:', score[0])
print('Test accuracy:', score[1])