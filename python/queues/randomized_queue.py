from random import randint, shuffle

class RandomizedQueue:

    def __init__(self):
        self._size = 0
        self._queue = []
        self._capacity = 0

    def is_empty(self):
        return self.size() == 0

    def size(self):
        return self._size

    def enqueue(self, item):
        if item == None:
            raise Error("Can't add None to queue")

        if self._size == self._capacity:
            # Python list appends are constant amortized time
            self._queue.append(item)
            self._capacity += 1
        else:
            self._queue[self._size] = item
        self._size += 1

    def dequeue(self):
        if self.is_empty():
            raise Error("Empty Queue")

        x = randint(0, self._size-1)
        val = self._queue[x]
        self._queue[x] = self._queue[self._size-1]
        self._size -= 1

        return val

    def sample(self):
        if self.is_empty():
            raise Error("Empty queue")

        x = randint(0, size-1)
        return self._queue[x]

    class RQIterator:
        def __init__(self, size, queue):
            self._size = size
            self._i = 0
            self._queue = queue[:size]
            shuffle(self._queue)

        def next(self):
            if self._i == self._size:
                raise StopIteration
            else:
                x = self._queue[self._i]
                self._i += 1
                return x

    def __iter__(self):
        return RandomizedQueue.RQIterator(self._size, self._queue)

if __name__ == '__main__':
    rq = RandomizedQueue()
    for i in range(10):
        rq.enqueue(i)
    for i in rq:
        print i
    print
    for i in range(10):
        print rq.dequeue()

