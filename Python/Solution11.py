from typing import List


class Solution:
    def maxArea(self, height: List[int]) -> int:
        m, n, res = 0, len(height) - 1, 0
        while(m < n):
            if height[m] < height[n]:
                res = max(res, height[m] * (n - m))
                m += 1
            else:
                res = max(res, height[n] * (n - m))
                n -= 1
        return res


solution = Solution()
maxArea = solution.maxArea([1,8,6,2,5,4,8,3,7])
print(maxArea)  