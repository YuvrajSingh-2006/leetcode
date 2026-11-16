class Solution(object):
    def mySqrt(self, x):
        left = 0
        right = x
        
        while left <= right:
            mid = left +(right-left) // 2
            
            if mid == 0 or mid <= x //mid:
                left = mid+1
            else:
                right = mid-1
        return right
        