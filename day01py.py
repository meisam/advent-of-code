input = "/home/meisam/workspace/advent-of-code/aoc-2024/day01.input"

from collections import Counter
def main():
    with open(input, 'r') as f:
        all_as, all_bs = zip(*((int(line[:5]), int(line[-5:]))
                          for l in f for line in [l.strip()]))
        sorted_as = sorted(all_as)
        sorted_bs = sorted(all_bs)
    diff = sum((abs(a-b) for a, b in zip(sorted_as, sorted_bs)))

    cnts = Counter(sorted_bs)
    inner_product = sum(((a * cnts[a]) for a in sorted_as))
    print("Innerproduct: ", inner_product)

if __name__ == "__main__":
    main()