package frametools;

import java.util.HashMap;

public class FrameUtilities {

	/**
	 * Check if string `s` is a valid anagram of string `t`.
	 * O(n) time and O(1) space
	 * @param s 
	 * @param t
	 * @return
	 */
	public boolean isValidAnagram(String s, String t) {
		HashMap<Character, Integer> chars = new HashMap<>();

		if (s.length() != t.length())
			return false;

		for (int i = 0; i < s.length(); i++) {
			char sc = s.charAt(i);
			char st = t.charAt(i);
			chars.put(sc, chars.getOrDefault(sc, 0) + 1);
			chars.put(st, chars.getOrDefault(st, 0) - 1);
		}

		for (int i = 0; i < s.length(); i++) {
			if (chars.get(s.charAt(i)) != 0)
				return false;
		}

		return true;
	}

	/**
	 * Perform a palindrome check on a given input string. Only compares
	 * alpha-numeric characters from input. O(n) time and O(1) space
	 * 
	 * @param s Input string
	 * @return Boolean value representing if a string is palindromic.
	 */
	public boolean isPalindrome(String s) {
		if (s.length() == 0 || s == null) {
			return false;
		}

		String res = "";

		for (int i = 0; i < s.length(); i++) {
			// if character is alpha-numeric, add it to formatted string
			if (Character.isLetter(s.charAt(i)) || Character.isDigit(i)) {
				res += Character.toLowerCase(s.charAt(i));
			}
		}

		int left = 0, right = res.length() - 1;

		while (left < right) {
			if (res.charAt(left) != res.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}

		return true;
	}

	/**
	 * Perform a binary search on an integer array. O(log(n)) time and O(1) space
	 * 
	 * @param nums   Array of integers
	 * @param target Target integer value
	 * @return
	 */
	public int binarySearch(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		int mid = 0;

		while (left <= right) {
			mid = left + (right - left) / 2;
			if (target == nums[mid]) {
				return mid;
			} else if (target < nums[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return -1;
	}

	/**
	 * Reverse a character array in-place. O(n) time and O(1) space
	 * 
	 * @param s     Character array
	 * @param left  Start position
	 * @param right End position
	 */
	public void reverse(char[] s, int left, int right) {
		while (left < right) {
			char temp = s[left];
			s[left++] = s[right];
			s[right--] = temp;
		}
	}

	/**
	 * Reverse a string and return the reversed string.
	 * 
	 * @param s     Input string
	 * @param left  Start position
	 * @param right End position
	 */
	public String reverse(String s, int left, int right) {
		char[] chars = s.toCharArray();
		reverse(chars, 0, chars.length - 1);
		return new String(chars);
	}
}
