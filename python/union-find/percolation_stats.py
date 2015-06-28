#!/usr/bin/python2
from percolation import Percolation
# using numpy for mean and stddev as they were provided in the stdlib
# for the assignment
import numpy as np
import sys
from random import randint

class PercolationStats:
    def __init__(self, n, t):
        if n <= 0 or t <= 0:
            raise ValueError("N and T should be greater than 0")

        self._vals = []
        self._t = t

        for _ in range(t):
            p = Percolation(n)
            count = 0.0
            while not p.percolates():
                x = np.random.random_integers(1, n)
                y = np.random.random_integers(1, n)
                if p.is_open(x, y):
                    continue
                else:
                    p.open(x, y)
                    count += 1
            ans = count / (n * n)
            self._vals.append(ans)

    def mean(self):
        return np.mean(self._vals)

    def stddev(self):
        return np.std(self._vals)

    def confidence_lo(self):
        mu = self.mean()
        sigma = self.stddev()

        return (mu - (1.96 * sigma / (self._t ** 0.5)))

    def confidence_hi(self):
        mu = self.mean()
        sigma = self.stddev()

        return (mu + (1.96 * sigma / (self._t ** 0.5)))


if __name__ == '__main__':
    n = int(sys.argv[1])
    t = int(sys.argv[2])
    print "n = %d, t = %d" % (n, t)
    ps = PercolationStats(n, t)

    print "mean\t= %f" % ps.mean()
    print "stddev\t= %f" % ps.stddev()
    print "95%% confidence interval\t= %f, %f" % (ps.confidence_lo(), ps.confidence_hi())
