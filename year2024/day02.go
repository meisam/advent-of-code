// https://adventofcode.com/2024/day/2
package main
import (
	"bufio"
	"fmt"
	"iter"
	"os"
	"strings"
	"strconv"
)

func StringToIntArray(values []string) ([]int, error) {
	var ret []int
	for _, s := range values {
		v, err := strconv.Atoi(s)
		if err != nil {
			return nil, err
		}
		ret = append(ret, v)
	}
	return ret, nil
}

func Range(end int) iter.Seq[int] {
	return func(yield func(int) bool) {
		for i := 0; i < end; i++ {
			if !yield(i) {
				return
			}
		}
	}
}

func SkippingIterator(values []int, skip int) iter.Seq2[int, int] {
	return func(yield func(int, int) bool) {
		var offset int = 0
		for i, v := range values {
			if i == skip {
				offset = 1
				continue
			}
			if !yield(i - offset, v) {
				return
			}
		}
	}
}

func safe(values []int, skip int) bool {
	var increasing bool = true
	var decreasing bool = true
	var last int = 0
	for i, current := range SkippingIterator(values, skip) {
		if (i == 0) {
			last = current
			continue
		}
		increasing = increasing && (current > last) && (current < last + 4)
		decreasing = decreasing && (current < last) && (current > last - 4)
		last = current
		if !increasing && ! decreasing {
				return false
		}
	}
	return increasing || decreasing
}

func main() {
	input := "day02.input"
	file, err := os.Open(input)
	if err != nil {
		fmt.Println("Error opening file:", input)
		return
	}
	defer file.Close()
	scanner := bufio.NewScanner(file)
	var safecnt int = 0
	for scanner.Scan() {
		report := strings.Split(scanner.Text(), " ")
		values, err := StringToIntArray(report)
		if err != nil {
			fmt.Println("Failed to get the values out of the report", report)
		}
		var isSafe bool = false
		for skip := range Range(1 + len(values)) {
			if safe(values, skip) {
				isSafe = true
				break
			}
		}
		if isSafe{
			safecnt += 1
		}
	}
	fmt.Println("Safe cnt", safecnt)
}