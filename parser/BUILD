load(
    "@rules_scala//scala:scala.bzl",
    "scala_library",
)

package(default_visibility = ["//visibility:public"])

scala_library(
    name = "parser_scala",
    srcs = ["Parser.scala"],
    deps = [],
)

scala_library(
    name = "location_scala",
    srcs = ["Location.scala"],
)

scala_library(
    name = "stack_trace_scala",
    srcs = ["StackTrace.scala"],
    deps = [
        ":location_scala",
    ],
)

# string parser
scala_library(
    name = "string_parser_scala",
    srcs = ["StringParser.scala"],
    deps = [
        ":location_scala",
        ":parser_scala",
        ":stack_trace_scala",
    ],
)
