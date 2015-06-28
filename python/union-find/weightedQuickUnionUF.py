class WeightedQuickUnionUF:

    def __init__(self, n):
        """ Initializes an empty union-find data structure with n isolated components"""
        self._count = n
        self._parent = [x for x in range(n)]
        self._size = [1 for x in range(n)]

    def count(self):
        return self._count

    def find(self, p):
        self.validate(p)
        while p != self._parent[p]:
            p = self._parent[p]
        return p

    def validate(self, p):
        n = len(self._parent)
        if p < 0 or p >= n:
            raise IndexError("Index %s is not between 0 and %s" % (p, n))

    def connected(self, p, q):
        return self.find(p) == self.find(q)

    def union(self, p, q):
        rootP = self.find(p)
        rootQ = self.find(q)

        if rootP == rootQ:
            return

        # make smaller root point to larger one
        if self._size[rootP] < self._size[rootQ]:
            self._parent[rootP] = rootQ
            self._size[rootQ] += self._size[rootP]
        else:
            self._parent[rootQ] = rootP
            self._size[rootP] += self._size[rootQ]

        self._count -= 1

n = int(raw_input())
uf = WeightedQuickUnionUF(n)
print "Enter quit to stop"
line = raw_input()
while line != 'quit':
    p, q = map(int, line.split())
    if uf.connected(p, q):
        continue

    uf.union(p, q)
    print "%d  %d" % (p, q)
    line = raw_input()
print "%d components" % uf.count()
