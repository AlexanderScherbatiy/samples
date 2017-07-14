import customsort


def test_sort(sort_func, list):
    sort_func(list)
    for i in range(len(list) - 1):
        assert list[i] <= list[i + 1], "List items are not sorted!"


list = [3, 5, 2, 4, 1]
test_sort(customsort.bubble_sort, list)
