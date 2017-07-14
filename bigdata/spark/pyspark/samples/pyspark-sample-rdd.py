from pyspark import SparkContext


def main():
    sc = SparkContext()

    N = 100
    rdd = sc.parallelize(range(0, N))
    rdd.cache()
    assert rdd.count() == N, "Wrong RDD count!"

    rdd1 = rdd.map(lambda v: (v, v * v))
    rdd2 = rdd.map(lambda v: (v, v * v * v))

    rddJoin = rdd1.join(rdd2)
    rddJoin.cache()
    assert rddJoin.count() == N, "Wrong RDD Join count!"

    rddJoinFilter = rddJoin.filter(lambda (x, (y, z)): x * x == y and x * x * x == z)
    assert rddJoinFilter.count() == N, "Wrong RDD Join Filter count!"

    sc.stop()


main()
