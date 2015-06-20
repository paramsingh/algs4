arr = [x for x in range(10)]

while True:
    try:
        p, q =  map(int, raw_input().split())
    except:
        break

    if arr[p] == arr[q]:
        continue
    else:
        ip = arr[p]
        iq = arr[q]
        for index, x in enumerate(arr):
            if x == ip:
                arr[index] = iq
    print ' '.join(map(str, arr))

print arr
