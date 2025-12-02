load(
    "@rules_scala//scala:scala.bzl",
    "scala_binary",
    "scala_library",
    "scala_test",
)

# DAY 01
scala_library(
    name = "day01scala",
    srcs = ["Day01.scala"],
    deps = [
    ],
)

scala_binary(
    name = "day01scala_main",
    srcs = ["Day01Main.scala"],
    main_class = "year2025.day01.main",
    deps = [
        ":day01scala",
    ],
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

# DAY 02
scala_library(
    name = "day02scala",
    srcs = ["Day02.scala"],
    deps = [
    ],
)

scala_binary(
    name = "day02scala_main",
    srcs = ["Day02Main.scala"],
    main_class = "year2025.day02.main",
    deps = [
        ":day02scala",
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
