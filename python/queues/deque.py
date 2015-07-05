class Deque:
    class Node:
        def __init__(self):
            self.item = None
            self.nxt = None
            self.prev = None

    def __init__(self):
        self._first = None
        self._last = None
        self._size = 0

    def is_empty(self):
        return self._size == 0

    def size(self):
        return self._size

    def add_first(self, item):
        # Check if item is valid
        if item == None:
            raise ValueError("Can't add None to deque")
        x = Deque.Node()
        x.item = item
        x.nxt = self._first
        if self.is_empty():
            self._last = x
        else:
            self._first.prev = x
        self._first = x
        self._size += 1

    def add_last(self, item):
        # Check if item is valid
        if item == None:
            raise ValueError("Can't add None to deque")
        x = Deque.Node()
        x.item = item
        if self.is_empty():
            self._first = x
        else:
            self._last.nxt = x
        x.prev = self._last
        self._last = x

        self._size += 1

    def remove_first(self):
        if self.is_empty():
            raise Exception("Can't remove from an empty deque")

        val = self._first.item
        # if there is only one element in deque
        if self._first == self._last:
            self._first = None
            self._last = None
        else:
            new_first = self._first.nxt
            new_first.prev = None
            self._first = new_first

        self._size -= 1
        return val

    def remove_last(self):
        if self.is_empty():
            raise Exception("Can't remove from an empty deque")

        val = self._last.item
        if self._first == self._last:
            self._first = self._last = None
        else:
            new_last = self._last.prev
            new_last.nxt = None
            self._last = new_last

        self._size -= 1
        return val

    class DequeIterator:
        def __init__(self, first):
            self._cur = first

        def next(self):
            if self._cur.nxt:
                val = self._cur.item
                self._cur = self._cur.nxt
                return val
            else:
                raise StopIteration()

    def __iter__(self):
        return Deque.DequeIterator(self._first)
