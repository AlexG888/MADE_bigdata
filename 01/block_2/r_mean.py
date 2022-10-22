#!/usr/bin/env python
"""reducer"""

import sys


if __name__ == '__main__':
    cur_chunk_size, cur_mean = 0, 0
    for row in sys.stdin:
        chunk_size, chunk_mean = map(float, row.strip().split())
        cur_mean = (chunk_size * chunk_mean + cur_chunk_size * cur_mean) / (chunk_size + cur_chunk_size)
        cur_chunk_size += chunk_size
    print(cur_mean)