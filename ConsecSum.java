/**
 * You have an unsorted array arr of non-negative integers and a number s. Find a longest contiguous subarray 
 * in arr that has a sum equal to s. Return two integers that represent its inclusive bounds. 
 * If there are several possible answers, return the one with the smallest left bound. If there are no answers, return [-1].

Your answer should be 1-based, meaning that the first position of the array is 1 instead of 0.

Example

For s = 12 and arr = [1, 2, 3, 7, 5], the output should be
findLongestSubarrayBySum(s, arr) = [2, 4].

The sum of elements from the 2nd position to the 4th position (1-based) is equal to 12: 2 + 3 + 7.

For s = 15 and arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], the output should be
findLongestSubarrayBySum(s, arr) = [1, 5].

The sum of elements from the 1st position to the 5th position (1-based) is equal to 15: 1 + 2 + 3 + 4 + 5.

For s = 15 and arr = [1, 2, 3, 4, 5, 0, 0, 0, 6, 7, 8, 9, 10], the output should be
findLongestSubarrayBySum(s, arr) = [1, 8].

The sum of elements from the 1st position to the 8th position (1-based) is equal to 15: 1 + 2 + 3 + 4 + 5 + 0 + 0 + 0.
 * 
 * 
 */

import java.util.HashMap;

public class ConsecSum {
    public static void main(String[] args) {
        int[] a = {10, 5, 2, 7, 1, 9};
		int target = 7;
		
        //maxLengthSubArray(a, target);
        longSubSum(a, target);
    }

    static int[] longSubSum(int[] a, int target){
		int start = 0;
		int end = -1;

		HashMap<Integer, Integer> hm = new HashMap<>();
		int[] cumsum = new int[a.length];
		cumsum[0] = a[0];
		hm.put(cumsum[0], 0);
		int maxlen = 0;
		if(cumsum[0] == target){
			maxlen = 1;
			end = 0;
		}
		int cursum = 0;
		for(int i = 1; i < a.length; i++){
			cumsum[i] = a[i]+cumsum[i-1];
			cursum = cumsum[i];
			hm.putIfAbsent(cursum, i);

			//this will only be triggered when cumulative
			//sum exactly matches the target. In case of all positive
			//aray, it'll be triggered once. In case of mixed array, it may
			//be triggered more than once
			if(cursum == target){
				maxlen = i+1;
				end = i;
			}

			int resid = cursum - target;

			if(hm.containsKey(resid)){
				int bindx = hm.get(resid);
				if(maxlen < (i - bindx)){
					maxlen = i - bindx;
					end = i;
					start = end - maxlen+1;
				}
			}

		}

		if(maxlen == 1){
			return new int[]{end, end};
		}

		if(maxlen == 0){
			return new int[]{-1};
		}

		return new int[]{start, end};
    }

    public static void maxLengthSubArray(int[] A, int target)
	{
		// create an empty Hash Map to store ending index of first
		// sub-array having some sum
		//sum is key, end-index is value
		HashMap<Integer, Integer> map = new HashMap<>();

		// insert (0, -1) pair into the set to handle the case when
		// sub-array with sum S starts from index 0
		//map.put(0, -1);

		//cumulative sum upto current index
		int sumUpto = 0;

		// len stores the maximum length of sub-array with sum S
		int maxlen = 0;

		// stores ending index of maximum length sub-array having sum S
		//int ending_index = -1;

		// traverse the given array
		for (int i = 0; i < A.length; i++)
		{
			// sum of elements so far
			sumUpto = sumUpto+A[i];

			//if cumulative sum adds up to target sum perfectly
			//maxlen is one more than current index because indices are zero based
			if(sumUpto == target){
				maxlen = i+1;
			}
			// if sum is seen for first time, insert sum with its index
			// Mostly useful when the array has negtive numbers where sum might decrease to
			//a sum already seen in the map
			if(!map.containsKey(sumUpto)){
				map.put(sumUpto, i);
			}

			//map.putIfAbsent(sum, i);

			// update length and ending index of maximum length sub-array
			// having sum S
			if (map.containsKey(sumUpto - target))
			{
				int sumIndex = map.get(sumUpto - target);
				if(maxlen < i - sumIndex ){
					maxlen = i - sumIndex;
					
				}

				
			}
		}

		System.out.println(maxlen);
		// print the sub-array
		//System.out.println("[" + (ending_index - maxlen + 1) + ", " + ending_index + "]");
	}
}
