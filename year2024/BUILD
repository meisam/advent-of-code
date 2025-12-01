load(
    "@rules_python//python:defs.bzl",
    "py_binary",
    "py_test",
)
load(
    "@rules_rust//rust:defs.bzl",
    "rust_binary",
    "rust_library",
    "rust_test",
)
load(
    "@rules_go//go:def.bzl",
    "go_binary",
    "go_library",
    "go_test",
)
load(
    "@rules_scala//scala:scala.bzl",
    "scala_binary",
    "scala_library",
    "scala_test",
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
        ":day03go",
    ],
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
scala_library(
    name = "day02scala",
    srcs = ["Day02.scala"],
    deps = [
        "//parser:location_scala",
        "//parser:parser_scala",
        "//parser:stack_trace_scala",
        "//parser:string_parser_scala",
    ],
)

scala_test(
    name = "day02scala_test",
    srcs = ["Day02Test.scala"],
    deps = [
        ":day02scala",
        "@maven//:org_scalacheck_scalacheck_3",
        "@maven//:org_scalatestplus_scalacheck_1_18_3",
    ],
)

scala_binary(
    name = "day02scala_main",
    srcs = ["Day02Main.scala"],
    data = [
        "day02.input",
        "day02.input.head",
        "day02.input.test",
    ],
    main_class = "day02.main",
    deps = [":day02scala"],
)

go_binary(
    name = "day0go",
    srcs = ["day02.go"],
    data = [
        "day02.input",
        "day02.input.head",
        "day02.input.test",
    ],
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
scala_library(
    name = "day01scala",
    srcs = ["Day01.scala"],
    deps = [
        "//parser:location_scala",
        "//parser:parser_scala",
        "//parser:stack_trace_scala",
        "//parser:string_parser_scala",
    ],
)

scala_binary(
    name = "day01scala_main",
    srcs = ["Day01Main.scala"],
    data = [
        "day01.input",
    ],
    main_class = "day01.main",
    deps = [":day01scala"],
)

scala_test(
    name = "day01scala_test",
    srcs = ["Day01Test.scala"],
    deps = [
        ":day01scala",
        "@maven//:org_scalacheck_scalacheck_3",
        "@maven//:org_scalatestplus_scalacheck_1_18_3",
    ],
)

go_binary(
    name = "day01go",
    srcs = ["day01.go"],
    data = [
        "day01.input",
    ],
)

py_binary(
    name = "day01py",
    srcs = ["day01py.py"],
    data = [
        "day01.input",
    ],
    deps = [
    ],
)

rust_binary(
    name = "day01rust_main",
    srcs = ["day01_main.rs"],
    deps = [
        ":day01rust",
    ],
)

rust_library(
    name = "day01rust",
    srcs = ["day01.rs"],
    crate_name = "year2024_day01",
)

rust_test(
    name = "day01rust_test",
    srcs = ["day01_test.rs"],
    deps = [
        ":day01rust",
        "@crates//:proptest",
    ],
)
