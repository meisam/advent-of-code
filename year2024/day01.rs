pub fn parse_line(line: String) -> (i64, i64) {
    let (col1, col2) = line.split_once("   ")
    .expect("expecting two values in each line");
    (col1.parse::<i64>().expect("invalid value in col 1"),
     col2.parse::<i64>().expect("invalid value in col 2"))
}

pub fn tally_distances(mut col1: Vec<i64>, mut col2: Vec<i64>) -> i64 {
    col1.sort();
    col2.sort();
    col1
        .into_iter()
        .zip(col2)
        .map(|(col1, col2)| (col1 - col2).abs())
        .sum::<i64>()  // i64 to ensure overflow doesn't occure
}
