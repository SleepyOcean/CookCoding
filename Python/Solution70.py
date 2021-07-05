

class Solution70:
    def climbStairs(self, n: int) -> int:
        if n == 0 or n == 1: return 1
        result, first, second = 0, 0, 1
        for i in range(n):
            result = first + second
            first = second
            second = result
        return result


solution = Solution70()
result = solution.climbStairs(4)
print(result)