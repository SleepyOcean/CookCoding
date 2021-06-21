from typing import List


class Solution:
    def maxArea(self, height: List[int]) -> int:
        m = 0
        n = 1
        size = len(height)
        max = 0
        while(m < n and n < size):
            while(n < size):
                newMax = (n - m) * min(height[m], height[n])
                max = max(max, newMax)
                n += 1
            m += 1
            n = m + 1
        return max


solution = Solution()
maxArea = solution.maxArea([1,8,6,2,5,4,8,3,7])
print(maxArea)  