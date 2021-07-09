from typing import List

class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        if n < 3:
            return []
        result = []
        nums.sort()
        for i in range(n):
            if nums[i] > 0:
                return result
            if i > 0 and nums[i] == nums[i-1]:
                continue
            l = i + 1
            r = n - 1
            while(l < r):
                if nums[i] + nums[l] + nums[r] == 0:
                    result.append([nums[i], nums[l], nums[r]])
                    while(l < r and nums[l] == nums[l+1]):
                        l += 1
                    while(l < r and nums[r] == nums[r-1]):
                        r -= 1
                    l += 1
                    r -= 1
                elif nums[i] + nums[l] + nums[r] > 0:
                    r -= 1
                else:
                    l += 1
        return result

solution = Solution()
res = solution.threeSum([-1,0,1,2,-1,-4])
print(res)  