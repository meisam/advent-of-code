"""
Advent of Code 2024 - Day 03 - Part 1
- https://adventofcode.com/2024/day/3
"""

import re

_REGEX = '''mul\(\d+,\d+\)'''

def extract_nums(s: str) -> tuple[int, int]:
    a, b = s[4:-1].split(',')
    return int(a), int(b)

def mul_result(input: str) -> int:
    return sum(((a * b)
                for (a, b) in (extract_nums(match.group(0))
                               for match in re.finditer(_REGEX, input))))
