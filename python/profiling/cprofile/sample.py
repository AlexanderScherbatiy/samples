from functools import reduce


# Run:
# > python3 -m cProfile -s cumtime sample.py
#
# Result:
#         500106 function calls in 0.658 seconds
#
#    Ordered by: cumulative time
#
#    ncalls  tottime  percall  cumtime  percall filename:lineno(function)
#         1    0.000    0.000    0.658    0.658 {built-in method builtins.exec}
#         1    0.000    0.000    0.658    0.658 sample.py:1(<module>)
#         1    0.000    0.000    0.658    0.658 sample.py:22(test_mul)
#       100    0.000    0.000    0.356    0.004 sample.py:17(mul_reduce)
#       100    0.047    0.000    0.356    0.004 {built-in method _functools.reduce}
#    499800    0.308    0.000    0.308    0.000 sample.py:18(<lambda>)
#       100    0.302    0.003    0.302    0.003 sample.py:9(mul_loop)
#         1    0.000    0.000    0.000    0.000 <frozen importlib._bootstrap>:997(_handle_fromlist)
#         1    0.000    0.000    0.000    0.000 {built-in method builtins.hasattr}
#         1    0.000    0.000    0.000    0.000 {method 'disable' of '_lsprof.Profiler' objects}

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

    for i in range(100):
        mul_loop(list)
        mul_reduce(list)


test_mul()
