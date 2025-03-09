load(
    "@rules_python//python:defs.bzl",
    "py_binary",
    "py_test",
)
load("@rules_rust//rust:defs.bzl", "rust_binary")
load(
    "@rules_go//go:def.bzl",
    "go_binary",
    "go_library",
    "go_test",
)

# DAY 03
go_library(
    name = "day03go",
    srcs = ["day03.go"],
    importpath = "github.com/meisamf/aoc/2024/day03go",
    visibility = ["//visibility:public"],
)

go_binary(
    name = "day03go_main",
    srcs = ["day03_main.go"],
    deps = [":day03go"],
)

go_test(
    name = "day03go_test",
    size = "small",
    srcs = ["day03_test.go"],
    data = [
        "day03.input",
    ],
    deps = [
        ":day03go"
    ]
)

py_binary(
    name = "day03py",
    srcs = ["day03py.py"],
    deps = [
    ],
)

py_test(
    name = "day03py_test",
    srcs = [
        "day03py_test.py",
    ],
    data = [
        "day03.input",
    ],
    deps = [
        ":day03py",
    ],
)

# DAY 02
go_binary(
    name = "day0go",
    srcs = ["day02.go"],
    data = [
        "day02.input",
        "day02.input.head",
        "day02.input.test",
    ]
)

py_binary(
    name = "day02py",
    srcs = ["day02py.py"],
    deps = [
    ],
)

# rust_binary(
#     name = "day02rust",
#     srcs = ["day02.rs"],
# )


# DAY 01
go_binary(
    name = "day01go",
    srcs = ["day01.go"],
)

py_binary(
    name = "day01py",
    srcs = ["day01py.py"],
    deps = [
    ],
)

rust_binary(
    name = "day01rust",
    srcs = ["day01.rs"],
)
