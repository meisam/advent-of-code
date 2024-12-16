// https://adventofcode.com/2024/day/1
package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strings"
	"strconv"
)


func main() {
	file, err := os.Open("day01.input");
	if err != nil {
		fmt.Println("Error opening file: ", err)
		return
	}

	defer file.Close()

	scanner := bufio.NewScanner(file)

	var as []int
	var bs []int 
	for scanner.Scan() {
		line := strings.Split(scanner.Text(), "   ")
		if len(line) != 2 {
			fmt.Println("Line %v with len %d has incorrect data", line, len(line))
			return
		}
		a, err := strconv.Atoi(line[0])
		if err != nil {
			fmt.Println("Failed to parse %v", line)
			return
		}
		b, err := strconv.Atoi(line[1])
		if err != nil {
			fmt.Println("Failed to parse %v", line)
			return
		}
		as = append(as, a)
		bs = append(bs, b)
	}
	sort.Ints(as)
	sort.Ints(bs)

	var sum int64 = 0
	for i, a := range as {
		b := bs[i]
		var d = a - b
		if a < b {
			d = b - a
		}
		sum += int64(d)
	}
	fmt.Println("Sum= ", sum)

	counts := make(map[int]int64)
	for _, b := range bs {
		if cnt, found := counts[b]; found {
			counts[b] = cnt + 1
		} else {
			counts[b] = 1
		}
	}

	var innerproduct int64 = 0
	for _, a := range as {
		if cnt, found := counts[a]; found {
			innerproduct += int64(cnt) * int64(a)
		}
	}
	fmt.Println("InnerProduct=", innerproduct)
}
