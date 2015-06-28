from weightedQuickUnionUF import WeightedQuickUnionUF

class Percolation:
    def __init__(self, n):
        if n <= 0:
            raise ValueError("n should be greater than 0")

        self._grid = [[False for _ in range(n)] for _ in range(n)]
        self._size = n

        # a quick find data structure containing sites for
        # virtual tops and virtual bottoms
        self._qf = WeightedQuickUnionUF(self._size*self._size + 2)

        # a quick find data structure only containing a site for
        # virtual top (to eliminate backwash)
        self._nf = WeightedQuickUnionUF(self._size * self._size + 1)

        self._top = self._size * self._size
        self._bottom = self._top + 1

    def _validate(self, i , j):
        """Check if indexes passed are valid, if invalid, throw an IndexError"""
        if i <= 0 or i > self._size or j <= 0 or j > self._size:
            raise IndexError(str(i) + ", " + str(j))

    def open(self, i, j):
        """ Open site (row i, column j) if it is not open already"""
        self._validate(i, j)

        x = i - 1
        y = j - 1
        if self.is_open(i, j):
            return

        self._grid[x][y] = True

        # corner case
        # if grid is 1 x 1
        if self._size == 1:
            self._qf.union(self._convert(x, y), self._top)
            self._qf.union(self._convert(x, y), self._bottom)
            self._nf.union(self._convert(x, y), self._top)
            return

        if x == 0:
            # only check the site below
            if self.is_open(i+1, j):
                self._qf.union(self._convert(x, y), self._convert(x+1, y))
                self._nf.union(self._convert(x, y), self._convert(x+1, y))
            self._qf.union(self._convert(x, y), self._top)

        elif x == self._size - 1:
            # only check the site above
            if self.is_open(i-1, j):
                self._qf.union(self._convert(x, y), self._convert(x-1, y))
                self._nf.union(self._convert(x, y), self._convert(x-1, y))
            self._qf.union(self._convert(x, y), self._bottom)

        else:
            # check both sites above and below
            if self.is_open(i-1, j):
                self._qf.union(self._convert(x, y), self._convert(x-1, y))
                self._nf.union(self._convert(x, y), self._convert(x-1, y))

            if self.is_open(i+1, j):
                self._qf.union(self._convert(x, y), self._convert(x+1, y))
                self._nf.union(self._convert(x, y), self._convert(x+1, y))

        if y == 0:
            # check only the right site
            if self.is_open(i, j+1):
                self._qf.union(self._convert(x, y), self._convert(x, y+1))
                self._nf.union(self._convert(x, y), self._convert(x, y+1))
        elif y == self._size - 1:
            if self.is_open(i, j-1):
                self._qf.union(self._convert(x, y), self._convert(x, y-1))
                self._nf.union(self._convert(x, y), self._convert(x, y-1))
        else:
            if self.is_open(i, j-1):
                self._qf.union(self._convert(x, y), self._convert(x, y-1))
                self._nf.union(self._convert(x, y), self._convert(x, y-1))

            if self.is_open(i, j+1):
                self._qf.union(self._convert(x, y), self._convert(x, y+1))
                self._nf.union(self._convert(x, y), self._convert(x, y+1))


    def _convert(self, i, j):
       return self._size * i + j

    def is_open(self, i, j):
        self._validate(i, j)

        return self._grid[i-1][j-1]

    def is_full(self, i, j):
        self._validate(i, j)

        return self._nf.connected(self._convert(i-1, j-1), self._top)

    def percolates(self):
        return self._qf.connected(self._top, self._bottom)

if __name__ == '__main__':
    n = int(raw_input("Enter the size: "))
    p = Percolation(n)
    print "Enter 'quit' to quit"
    i = ''
    while i != 'quit':
        x, y = map(int, raw_input().split())
        if not p.is_open(x, y):
            p.open(x, y)
        if p.percolates():
            print "Percolates"
        else:
            print "Does not percolate"
