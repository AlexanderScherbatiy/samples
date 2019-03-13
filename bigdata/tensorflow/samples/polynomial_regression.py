import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf

learning_rate = 0.01
training_epochs = 100

observation_number = 101
polynome_degree = 3


def polynomial(w, x):
    y = 0
    x_pow = 1
    for a in w:
        y += a * x_pow
        x_pow *= x
    return y


w_input = np.array([6.0, -20.0, 3.0, 2.0])

x_train = np.linspace(-5, 5, observation_number)
y_train = polynomial(w_input, x_train)
plt.scatter(x_train, y_train, s=3)

# Add noise
y_train += np.random.normal(0, 2.0, x_train.shape)
plt.scatter(x_train, y_train, s=3)


def model(w, X):
    terms = []
    for i in range(int(w.shape[0])):
        term = tf.multiply(w[i], tf.pow(X, i))
        terms.append(term)
    return tf.add_n(terms)


X = tf.placeholder(tf.float32)
Y = tf.placeholder(tf.float32)

w = tf.Variable(tf.random_normal([polynome_degree + 1]), name='weights')
Y_pred = model(w, X)

cost = tf.reduce_sum(tf.pow(Y_pred - Y, 2)) / (observation_number - 1)
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

y_learned = polynomial(w_val, x_train)
plt.scatter(x_train, y_learned, s=3)

plt.show()
