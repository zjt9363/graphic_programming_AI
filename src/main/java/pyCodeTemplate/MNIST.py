import sys

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

f = gzip.open('mnist.pkl.gz', 'rb')

data = pickle.load(f, encoding='bytes')

f.close()
(x_train, y_train), (x_test, y_test) = data

# file = gzip.open('test1.pkl.gz', 'wb')
# pickle.dump(data, file)
# file.close


input_shape = (x_train.shape[1],
               x_train.shape[2],
               1 if len(x_train.shape) == 3 else x_train.shape[3])
x_train = x_train.astype('float32')
x_test = x_test.astype('float32')
x_train /= 255
x_test /= 255
batch_size = 128
epochs = 2000
img_rows, img_cols = x_train.shape[1], x_train.shape[2]

num_classes = 10

y_train = keras.utils.to_categorical(y_train, num_classes)
y_test = keras.utils.to_categorical(y_test, num_classes)

model = Sequential()
model.add(Conv2D(filters=128, kernel_size=(6, 6), activation='relu', input_shape=input_shape))
model.add(Conv2D(filters=128, kernel_size=(6, 6), activation='relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Dropout(rate=0.5))
model.add(Flatten())
model.add(Dense(units=128, activation='relu'))
model.add(Dense(units=256, activation='relu'))
model.add(Dropout(rate=0.75))
model.add(Dense(units=num_classes, activation='softmax'))

model.summary()

# model.compile(loss=keras.losses.categorical_crossentropy, optimizer='Adam', metrics=['accuracy'])
model.compile(loss='categorical_crossentropy',
              optimizer='Adam',
              metrics=['accuracy'])

early_stopping = keras.callbacks.EarlyStopping(monitor='val_accuracy',
                                               min_delta=0.001,
                                               patience=5,
                                               verbose=0,
                                               mode='max')

history = model.fit(x_train, y_train,
                    batch_size=batch_size,
                    epochs=epochs,
                    verbose=2,
                    validation_data=(x_test, y_test),
                    callbacks=[early_stopping])

score = model.evaluate(x_test, y_test, verbose=0)

print('Test loss:', score[0])
print('Test accuracy:', score[1])
