from deque import Deque

def test_addition():
    print "Testing addition..."
    dq = Deque()
    assert dq.size() == 0

    for i in range(10):
        dq.add_last(i)

    assert dq.size() == 10

    for i in range(10):
        x = dq.remove_first()
        assert x == i

    assert dq.size() == 0

    for i in range(10):
        x = dq.add_first(i)

    for i in range(10):
        x = dq.remove_last()
        assert x == i

def test_iteration():
    print "Testing iteration..."
    dq = Deque()
    for i in range(10):
        dq.add_last(i)

    a = range(10)
    index = 0
    for i in dq:
        assert i == a[index]
        index += 1

if __name__ == "__main__":
    test_addition()
    test_iteration()
