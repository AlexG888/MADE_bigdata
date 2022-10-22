#!/usr/bin/env python
"""mapper"""

import sys
import csv
from statistics import mean, variance


if __name__ == '__main__':
    reader = csv.reader(sys.stdin)
    prices = []
    for row in reader:
        try:
            prices.append(float(row[9]))
        except ValueError:
            continue
    print(len(prices), mean(prices), variance(prices))