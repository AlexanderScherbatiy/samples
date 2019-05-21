import random
import numpy as np
from tensorflow import keras

A = 50
B = 50


def f(x):
    return A * x + B


N = 1000
xs = np.array([random.uniform(-10, 10) for _ in range(N)], dtype=float)
ys = np.array([f(x) for x in xs], dtype=float)

model = keras.Sequential([keras.layers.Dense(units=1, input_shape=[1])])

model.compile(optimizer='sgd', loss='mean_squared_error')

Y_MAX = np.max(ys)
ys = ys / Y_MAX
model.fit(xs, ys, epochs=100, batch_size=20)

predict_x = 4.8
predict_y = model.predict([predict_x]) * Y_MAX
print("x:", predict_x, ", y:", f(predict_x), ", predict y: ", predict_y)
