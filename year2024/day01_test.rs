use year2024_day01::{parse_line, tally_distances};
use proptest::prelude::*;

#[test]
fn test_parse_line() {
    assert_eq!(parse_line("3   4".to_string()), (3, 4));
    assert_eq!(parse_line("10   20".to_string()), (10, 20));
}

#[test]
fn test_tally_distances_same_single_value() {
    let col1 = vec![1];
    let col2 = vec![1];
    assert_eq!(tally_distances(col1, col2), 0);
}

#[test]
fn test_tally_distances_different_single_value() {
    let col1 = vec![5];
    let col2 = vec![7];
    assert_eq!(tally_distances(col1, col2), 2);
}

#[test]
fn test_tally_distances_reversed_values() {
    let col1 = vec![1, 2, 3, 4, 5];
    let col2 = vec![5, 4, 3, 2, 1];
    assert_eq!(tally_distances(col1, col2), 0);
}

#[test]
fn test_tally_distances_reversed_values_off_by_one() {
    let col1 = vec![1, 2, 3, 4, 5];
    let col2 = vec![4, 3, 2, 1, 0];
    assert_eq!(tally_distances(col1, col2), 5);
}

proptest! {
    #[test]
    fn test_parse_line_prop(a in 0..1_000_000_000i64, b in 0..1_000_000_000i64) {
        let line = format!("{}   {}", a, b);
        prop_assert_eq!(parse_line(line), (a, b));
    }

    #[test]
    fn test_tally_distances_non_negative(
        col1 in prop::collection::vec(0..1_000_000_000i64, 0..100),
        col2 in prop::collection::vec(0..1_000_000_000i64, 0..100)
    ) {
        let result = tally_distances(col1, col2);
        prop_assert!(result >= 0);
    }
    
    #[test]
    fn test_tally_distances_symmetry(
        col1 in prop::collection::vec(0..1_000_000_000i64, 0..100),
        col2 in prop::collection::vec(0..1_000_000_000i64, 0..100)
    ) {
         let res1 = tally_distances(col1.clone(), col2.clone());
         let res2 = tally_distances(col2, col1);
         prop_assert_eq!(res1, res2);
    }

    #[test]
    fn test_tally_distances_permutation_invariance(
        col1 in prop::collection::vec(0..1_000_000_000i64, 0..50),
        col2 in prop::collection::vec(0..1_000_000_000i64, 0..50)
    ) {
        // Since tally_distances sorts the inputs, the order shouldn't matter.
        // We can verify this by reversing the inputs and checking if the result is the same.
        // (Reversing is a simple permutation).
        
        let mut col1_rev = col1.clone();
        col1_rev.reverse();
        
        let mut col2_rev = col2.clone();
        col2_rev.reverse();
        
        let res1 = tally_distances(col1.clone(), col2.clone());
        let res2 = tally_distances(col1_rev, col2_rev);
        
        prop_assert_eq!(res1, res2);
    }
}
