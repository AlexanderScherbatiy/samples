import tensorflow as tf
import numpy as np

mnist = tf.keras.datasets.fashion_mnist

(training_images, training_labels), (test_images, test_labels) = mnist.load_data()

training_images = training_images / 255.0
test_images = test_images / 255.0

model = tf.keras.models.Sequential([tf.keras.layers.Flatten(),
                                    tf.keras.layers.Dense(128, activation=tf.nn.relu),
                                    tf.keras.layers.Dense(10, activation=tf.nn.softmax)])

model.compile(optimizer=tf.train.AdamOptimizer(),
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

model.fit(training_images, training_labels, epochs=5, batch_size=100)

[loss, metrics] = model.evaluate(test_images, test_labels)

print("test data loss:", loss)
print("test data metrics:", metrics)

classifications = model.predict(test_images)

np.random.seed(47)
ids = [np.random.randint(0, len(test_labels)) for _ in range(10)]

import matplotlib.pyplot as plt

for id in ids:
    label = test_labels[id]
    predicted_label = np.argmax(classifications[id])
    matched = label == predicted_label
    sign = "(+)" if matched else "(-)"
    print(sign, "id: ", id, "label:", label, "predicted label:", predicted_label)
    if not matched:
        plt.imshow(training_images[0])

plt.show()
