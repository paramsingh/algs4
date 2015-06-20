def find(x, arr):
    while x != arr[x]:
        x = arr[x]
    return x

arr = [x for x in range(10)]
size = [1 for x in range(10)]
while True:
    try:
        p, q = map(int, raw_input().split())
    except:
        break
    print p, q

    i = find(p, arr)
    j = find(q, arr)
    if i == j:
        continue
    if size[i] < size[j]:
        arr[i] = j
        size[j] += size[i]
    else:
        arr[j] = i
        size[i] += size[j]

    print arr

print arr
