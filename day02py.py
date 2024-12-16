"""
Advent of Code 2024 - Day 02 - Part 2
"""

from functools import reduce
from itertools import pairwise
from typing import Generator
import operator

_INPUT_FILE = "/home/meisam/workspace/advent-of-code/aoc-2024/day02.input"


def skipping_iter(values: list[int], skip_index: int) -> Generator[int, int, int]:
    """
    Iterates over the list of values, but skips the value at index skip_index.

    If skip index is out of the valid range, it iterates over all values
    without skipping value.
    """
    for i, v in enumerate(values):
        if i != skip_index:
            yield v

def is_safe_skipping(values: list[int], skip_index: int) -> bool:
    """
    Return true if the given values are safe.
    
    Ignores the value at index skip_index.
    Safe is defined at
    https://adventofcode.com/2024/day/2
    """
    x = [ v for v in skipping_iter(values, skip_index)]
    diffs = [a-b for a, b in pairwise(x)]
    increasing = reduce(operator.and_, ((0 < d <= 3) for d in diffs), True)
    decreasing = reduce(operator.and_, ((0 > d >= -3) for d in diffs), True)
    return increasing or decreasing

def is_safe(values: list[int]) -> bool:
    """
    Return true if the given values are safe while ignoring at most one value.

    Safe is defined at
    https://adventofcode.com/2024/day/2
    """
    if next((True for skip_index in range(len(values) + 1)
                 if is_safe_skipping(values, skip_index)), False):
        print(values)
        return True
    return False

def main():
    """
    Main function.
    """
    with open(_INPUT_FILE, 'r', encoding="utf-8") as f:
        lines  = [l.strip().split(" ") for l in f]
        all_values = [list(map(int, line)) for line in lines]
        safe_cnt = sum(is_safe(values) for values in all_values)
        print('safe_cnt', safe_cnt)

if __name__ == "__main__":
    main()