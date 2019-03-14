import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf

learning_rate = 0.0003
training_epochs = 5000

observation_number = 1000


def f_normal(mean, sigma, ampl, background, X):
    sigma_sqr = sigma * sigma
    exp = np.exp(-np.square(X - mean) / (2 * sigma_sqr))
    return background + ampl * exp / (np.sqrt(2 * np.pi) * sigma)


def model(mean, sigma, ampl, background, X):
    sigma_sqr = tf.square(sigma)
    exp = tf.exp(tf.div(-tf.square(X - mean), 2 * sigma_sqr))
    return background + ampl * exp / (np.sqrt(2 * np.pi) * sigma)


def shuffle_and_split_indices(size, split_rate):
    split_index = int(split_rate * size)
    indices = np.random.permutation(size)
    return indices[:split_index], indices[split_index:]


w_input = np.array([32.5, 2.28, 2800, 20.0])

mean_input = 34.5
sigma_input = 2.28
ampl_input = 2800
background_input = 18.0

x_input = np.linspace(25, 45, observation_number)
y_input = f_normal(mean_input, sigma_input, ampl_input, background_input, x_input)

idx_train, idx_test = shuffle_and_split_indices(observation_number, 0.8)

x_train, x_test = x_input[idx_train], x_input[idx_test]
y_train, y_test = y_input[idx_train], y_input[idx_test]

plt.scatter(x_train, y_train, s=3)
plt.scatter(x_test, y_test, marker='x')

BATCH_SIZE = tf.placeholder(tf.float32)
X = tf.placeholder(tf.float32)
Y = tf.placeholder(tf.float32)

mean_param = tf.Variable(30.0, name='mean')
sigma_param = tf.Variable(1.5, name='sigma')
ampl_param = tf.Variable(3500.0, name='ampl')
background_param = tf.Variable(10.0, name='background')

Y_pred = model(mean_param, sigma_param, ampl_param, background_param, X)
cost = tf.reduce_sum(tf.square(Y_pred - Y)) / (2 * BATCH_SIZE)
train_op = tf.train.GradientDescentOptimizer(learning_rate).minimize(cost)

with tf.Session() as sess:
    init = tf.global_variables_initializer()
    sess.run(init)

    train_dict = {X: x_train, Y: y_train, BATCH_SIZE: x_train.size}
    test_dict = {X: x_test, Y: y_test, BATCH_SIZE: x_test.size}

    for epoch in range(training_epochs):
        sess.run(train_op, feed_dict=train_dict)

        if epoch % 50 == 0:
            cost_val_train = sess.run(cost, feed_dict=train_dict)
            cost_val_test = sess.run(cost, feed_dict=test_dict)
            print("epoch:", epoch, "cost train:", cost_val_train, "test:", cost_val_test)

    mean_val, sigma_val, ampl_val, background_val = sess.run([mean_param, sigma_param, ampl_param, background_param])
    cost_val_train = sess.run(cost, feed_dict=train_dict)
    cost_val_test = sess.run(cost, feed_dict=test_dict)
    print("cost train:", cost_val_train, "test:", cost_val_test)
    print("input  mean:", mean_input, "sigma:", sigma_input, "ampl:", ampl_input)
    print("train  mean:", mean_val, "sigma:", sigma_val, "ampl:", ampl_val)

y_learned = f_normal(mean_val, sigma_val, ampl_val, background_val, x_input)
plt.scatter(x_input, y_learned, s=3)

plt.show()
