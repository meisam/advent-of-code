import unittest
from year2024.day03py import extract_nums,mul_result

class TestDay02(unittest.TestCase):

    def test_extract_nums(self):
        self.assertEqual(extract_nums('mul(2,3)'),(2, 3))
        self.assertEqual(extract_nums('mul(22,3)'),(22, 3))
        self.assertEqual(extract_nums('mul(2,33)'),(2, 33))
        self.assertEqual(extract_nums('mul(22,33)'),(22, 33))

    def test_valid_mul_result(self):
        self.assertEqual(mul_result('mul(2,3)'),6)
        self.assertEqual(mul_result('mul(44,46)'),2024)
        self.assertEqual(mul_result('mul(123,4)'),492)
    
    def test_invalid_reult(self):
        #  do nothing for mul(4*, mul(6,9!, ?(12,34), or mul ( 2 , 4 )
        self.assertEqual(mul_result('mul(4*'), 0)
        self.assertEqual(mul_result('mul(6,9!'), 0)
        self.assertEqual(mul_result('?(12,34)'), 0)
        self.assertEqual(mul_result('( 2 , 4 )'), 0)

    def test_smoke(self):
        self.assertEqual(mul_result(
            '''xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))'''
        ), 161)

    def test_part1_answer(self):
        with open('year2024/day03.input', 'r', encoding='utf-8') as f:
            self.assertEqual(mul_result(f.read()), 155955228)  # <-- Answer to part 1

if __name__ == '__main__':
    unittest.main()
