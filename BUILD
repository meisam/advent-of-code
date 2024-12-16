load("@rules_go//go:def.bzl", "go_binary")
load("@rules_python//python:defs.bzl", "py_binary")
load("@bazel_skylib//rules:build_test.bzl", "build_test")  # needed for rust
load("@rules_rust//rust:defs.bzl", "rust_binary")

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
