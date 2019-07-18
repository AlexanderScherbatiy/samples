from functools import reduce

import cProfile, pstats, io


# Run:
# > python3 sample2.py
# > python3 -m pstats out2.profile
#
# Result:
#
#          500101 function calls in 0.655 seconds
#
#    Random listing order was used
#
#    ncalls  tottime  percall  cumtime  percall filename:lineno(function)
#       100    0.046    0.000    0.353    0.004 {built-in method _functools.reduce}
#         1    0.000    0.000    0.000    0.000 {method 'disable' of '_lsprof.Profiler' objects}
#       100    0.000    0.000    0.353    0.004 sample2.py:14(mul_reduce)
#    499800    0.307    0.000    0.307    0.000 sample2.py:15(<lambda>)
#       100    0.302    0.003    0.302    0.003 sample2.py:6(mul_loop)


def mul_loop(list):
    res = 1

    for i in list:
        res *= i
    return res


def mul_reduce(list):
    res = reduce(lambda x, y: x * y, list)
    return res


def test_mul():
    list = range(1, 5000)

    pr = cProfile.Profile()
    pr.enable()
    for i in range(100):
        mul_loop(list)
        mul_reduce(list)
    pr.disable()
    pr.dump_stats("out2.profile")


test_mul()
