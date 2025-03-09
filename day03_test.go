// https://adventofcode.com/2024/day/3
package day

import (
    "testing"
    lib "github.com/meisamf/aoc/2024/day03go"
)

func TestProductSum(t *testing.T) {
    tests := []struct {
        name  string
        text  string
        want  int
    }{
        {
            name: "empty string",
            text: "",
            want: 0,
        },
        {
            name: "no symbols",
            text: "123\n456\n789",
            want: 0,
        },
        {
            name: "single symbol",
            text: "123*\n456\n789",
            want: 0, // Based on current definition, it will be 0
        },
        {
            name: "single number adjacent to symbol",
            text: "...\n.1*\n...",
            want: 1, // Example
        },
        {
            name: "number with multiple adjacent symbols",
            text: ".%.\n.1*\n...",
            want: 1, // Example
        },
        {
            name: "number with multiple adjacent digits",
            text: ".%.\n.12*\n...",
            want: 12, // Example
        },

        {
            name: "complex",
            text: "467..114..\n...*......\n..35..633.\n......#...\n617*......\n.....+.58.\n..592.....\n......755.\n...$.*....\n.664.598..",
            want: 4361, // The expected result for the provided example in the problem description.
        },
                {
            name: "complex2",
            text: "12.......*..\n+.........34\n.......-12..\n..78........\n..*....65...\n...........6\n12.+..58..2.\n..7..*.....+",
            want: 386, // The expected result for the provided example in the problem description.
        },
                {
            name: "complex3",
            text: "..........\n.24......$\n..........\n..45...*\n..........\n",
            want: 69, // The expected result for the provided example in the problem description.
        },
        {
            name: "complex4",
            text: "12.......*..\n+.........34",
            want: 46, // The expected result for the provided example in the problem description.
        },
    }
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            if got := lib.ProductSum(tt.text); got != tt.want {
                t.Errorf("ProductSum() = %v, want %v", got, tt.want)
            }
        })
    }
}
