#!/usr/bin/python2
from randomized_queue import RandomizedQueue
import sys
k = int(sys.argv[1])
r = sys.stdin.read().split()
rq = RandomizedQueue()
for x in r:
    rq.enqueue(x)

for i in range(k):
    print rq.dequeue()

