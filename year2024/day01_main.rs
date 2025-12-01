use std::env;
use std::io::{self, BufRead, BufReader};
use year2024_day01::{parse_line, tally_distances};

fn main() -> io::Result<()> {
    let [_, input_path]: [String; 2] = env::args()
    .collect::<Vec<String>>()
    .try_into()
    .expect("expecting two arguments");

    let file = std::fs::File::open(input_path)
    .expect("expecting a valid file path");
    let (col1, col2): (Vec<i64>, Vec<i64>) = BufReader::new(file)
        .lines()
        .map(|line_result| line_result.expect("expecting a valid line"))
        .map(parse_line)
        .unzip();

    let totaldiff = tally_distances(col1, col2);
    println!("total diff: {}", totaldiff);
    Ok(())
}