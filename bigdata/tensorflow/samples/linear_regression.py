import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf

learning_rate = 0.01
training_epochs = 200

w_input = 7.5

x_train = np.linspace(-10, 10, 201)
y_train = w_input * x_train + np.random.normal(0, 0.9, x_train.shape)

X = tf.placeholder(tf.float32)
Y = tf.placeholder(tf.float32)


def model(X, w):
    return tf.multiply(w, X)


w = tf.Variable(0.0, name="weights")
y_model = model(X, w)
cost = tf.square(Y - y_model)
train_op = tf.train.GradientDescentOptimizer(learning_rate).minimize(cost)

with tf.Session() as sess:
    init = tf.global_variables_initializer()
    sess.run(init)

    for epoch in range(training_epochs):
        for (x, y) in zip(x_train, y_train):
            sess.run(train_op, feed_dict={X: x, Y: y})

        if epoch % 20 == 0:
            w_val = sess.run(w)
            print("epoch:", epoch, "learned w:", w_val)

    w_val = sess.run(w)

print("real w:", w_input, "learned w:", w_val)

# Show plot
plt.scatter(x_train, y_train)

y_learned = w_val * x_train
plt.scatter(x_train, y_learned)

rc_fonts = {'figure.figsize': (15, 9)}
plt.rcParams.update(rc_fonts)

plt.show()
