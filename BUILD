load("@rules_go//go:def.bzl", "go_binary")
load("@rules_python//python:defs.bzl",
    "py_binary",
    "py_test")
load("@bazel_skylib//rules:build_test.bzl", "build_test")  # needed for rust
load("@rules_rust//rust:defs.bzl", "rust_binary")

# DAY 03
go_binary(
    name = "day03go",
    srcs = ["day02.go"],
    data = [
        "day03.input",
        "day03.input.head",
        "day03.input.test",
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
        "day03py_test.py"
    ],
    deps = [
        ":day03py"
    ],
    data = [
        "day03.input",
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
