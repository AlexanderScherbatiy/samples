import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf

learning_rate = 0.0007
training_epochs = 10000

observation_number = 1001
polynome_degree = 3


def polynomial(w, x):
    y = 0
    x_pow = 1
    for a in w:
        y += a * x_pow
        x_pow *= x
    return y


def shuffle_and_split_indices(size, split_rate):
    split_index = int(split_rate * size)
    indices = np.random.permutation(size)
    return indices[:split_index], indices[split_index:]


w_input = np.array([6.0, -20.0, 3.0, 2.0])

x_input = np.linspace(-5, 5, observation_number)
y_input = polynomial(w_input, x_input)
y_input += np.random.normal(0, 2.0, y_input.shape)  # Add noise

idx_train, idx_test = shuffle_and_split_indices(observation_number, 0.8)

x_train, x_test = x_input[idx_train], x_input[idx_test]
y_train, y_test = y_input[idx_train], y_input[idx_test]

plt.scatter(x_train, y_train, s=3)
plt.scatter(x_test, y_test, marker='x')


def model(w, X):
    terms = []
    for i in range(int(w.shape[0])):
        term = tf.multiply(w[i], tf.pow(X, i))
        terms.append(term)
    return tf.add_n(terms)


BATCH_SIZE = tf.placeholder(tf.float32)
X = tf.placeholder(tf.float32, name='X')
Y = tf.placeholder(tf.float32, name='Y')

w = tf.Variable(tf.random_normal([polynome_degree + 1]), name='weights')
Y_pred = model(w, X)

cost = tf.reduce_sum(tf.square(Y_pred - Y)) / (2 * BATCH_SIZE)
train_op = tf.train.GradientDescentOptimizer(learning_rate).minimize(cost)

tf.summary.scalar("cost", cost)
merged_summary = tf.summary.merge_all()

with tf.Session() as sess:
    init = tf.global_variables_initializer()
    sess.run(init)

    writer = tf.summary.FileWriter("tensorboard", sess.graph)

    train_dict = {X: x_train, Y: y_train, BATCH_SIZE: x_train.size}
    test_dict = {X: x_test, Y: y_test, BATCH_SIZE: x_test.size}

    for epoch in range(training_epochs):
        sess.run(train_op, feed_dict=train_dict)

        if epoch % 50 == 0:
            w_val = sess.run(w)
            cost_val, summary = sess.run([cost, merged_summary], feed_dict=train_dict)
            print("epoch:", epoch, "cost:", cost_val, "learned w:", w_val)

            writer.add_summary(summary, epoch)

    w_val = sess.run(w)
    train_cost_val = sess.run(cost, feed_dict=train_dict)
    test_cost_val = sess.run(cost, feed_dict=test_dict)

    print("train cost:", train_cost_val, 'test cost:', test_cost_val)

print("real w:", w_input, "learned w:", w_val)

y_learned = polynomial(w_val, x_input)
plt.scatter(x_input, y_learned, s=3)

plt.show()
