def bubble_sort(list):
    N = len(list)
    for i in range(N):
        for j in range(0, N - i - 1):
            if list[j] > list[j + 1]:
                value = list[j + 1]
                list[j + 1] = list[j]
                list[j] = value
