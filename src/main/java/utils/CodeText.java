package utils;

/**
 * @author Zarrow
 */
public class CodeText {
    public static final String ADD_MODEL = "model.add(";
    public static final String NET_BUILD = "model = Sequential()\n";
    public static final String SUMMARY = "model.summary()\n";
    public static final String IMPORT_PACKAGE = "from tensorflow import keras\n" +
            "from keras.models import Sequential\n" +
            "from keras.layers import Dense, Dropout, Flatten\n" +
            "from keras.layers import Conv2D, MaxPooling2D, BatchNormalization\n" +
            "from keras.layers import GlobalAveragePooling2D\n" +
            "from tensorflow.compat.v1 import ConfigProto\n" +
            "from tensorflow.compat.v1 import InteractiveSession\n" +
            "\n";

    public static final String GPU_CONFIGURATION = "config = ConfigProto()\n" +
            "config.gpu_options.allow_growth = True\n" +
            "session = InteractiveSession(config=config)\n" +
            "\n";

    public static final String LOAD_FILE = "import gzip\n" +
            "import pickle\n" +
            "f = gzip.open('%s', 'rb')\n" +
            "\n" +
            "data = pickle.load(f, encoding='bytes')\n" +
            "\n" +
            "f.close()\n" +
            "(x_train, y_train), (x_test, y_test) = data\n" +
            "\n";

    public static final String INIT = "input_shape = (x_train.shape[1],\n" +
            "               x_train.shape[2],\n" +
            "               1 if len(x_train.shape) == 3 else x_train.shape[3])\n" +
            "x_train = x_train.astype('float32')\n" +
            "x_test = x_test.astype('float32')\n" +
            "x_train /= 255\n" +
            "x_test /= 255\n" +
            "batch_size = 128\n" +
            "epochs = 2000\n" +
            "img_rows, img_cols = x_train.shape[1], x_train.shape[2]\n" +
            "\n" +
            "num_classes = %s\n" +
            "\n";
    public static final String TO_CATEGORICAL = "y_train = keras.utils.to_categorical(y_train, num_classes)\n" +
            "y_test = keras.utils.to_categorical(y_test, num_classes)\n" +
            "\n";

    public static final String COMPILE = "model.compile(loss='sparse_categorical_crossentropy', \n" +
            "              optimizer='Adam', \n" +
            "              metrics=['accuracy'])\n" +
            "\n";

    public static final String EARLY_STOPPING = "early_stopping = keras.callbacks.EarlyStopping(monitor='val_accuracy',\n" +
            "                                               min_delta=0.001,\n" +
            "                                               patience=5,\n" +
            "                                               verbose=0,\n" +
            "                                               mode='max')\n" +
            "\n";

    public static final String FIT = "history = model.fit(x_train, y_train,\n" +
            "                    batch_size=batch_size,\n" +
            "                    epochs=epochs,\n" +
            "                    verbose=2,\n" +
            "                    validation_data=(x_test, y_test),\n" +
            "                    callbacks=[early_stopping])\n" +
            "\n";

    public static final String EVALUATE = "score = model.evaluate(x_test, y_test, verbose=0)\n" +
            "\n";

    public static final String PRINT = "print('Test loss:', score[0])\n" +
            "print('Test accuracy:', score[1])\n";
}
