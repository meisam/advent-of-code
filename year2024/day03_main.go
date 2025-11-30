package main

import (
	"bufio"
	"fmt"
	"os"
	lib "github.com/meisamf/aoc/2024/day03go"
)

func main() {
	if len(os.Args) != 2 {
		fmt.Println("Usage: <path to input file>", os.Args[0])
		os.Exit(1)
		return
	}
	file, err := os.Open(os.Args[1])
	if err != nil {
		fmt.Println("Error opening file:", os.Args[1])
		os.Exit(2)
		return
	}
	defer file.Close()
	scanner := bufio.NewScanner(file)
	var text string = ""
	for scanner.Scan() {
		text += scanner.Text()
	}
	fmt.Println("XXX: mulres=", lib.ProductSum(text))
}