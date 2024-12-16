// https://adventofcode.com/2024/day/3
package main
import (
	"bufio"
	"fmt"
	"iter"
	"os"
	"strings"
)

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