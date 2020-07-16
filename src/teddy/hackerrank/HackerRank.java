package teddy.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class HackerRank {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		// int n = scanner.nextInt();
		// for (int i = 0; i < n; i++) {
		// String s = scanner.nextLine();
		// scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		//
		// String evenStr = "";
		// String oddStr = "";
		// for (int j = 0; j < s.length(); j+=2) {
		// evenStr += s.charAt(j);
		// }
		// for (int j = 1; j < s.length(); j+=2) {
		// oddStr += s.charAt(j);
		// }
		// System.out.println(evenStr + " " + oddStr);
		// }

//		String s = "welcometojava";
//		int k = 3;
//		String str = getSmallestAndLargest(s, k);
//		System.out.println(str);

		int[] arr = {5, 4, 4, 2, 2, 8};
		int[] ret = cutTheSticks(arr);
	}

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";

        // Complete the function
        // 'smallest' must be the lexicographically smallest substring of length 'k'
        // 'largest' must be the lexicographically largest substring of length 'k'
        List<String> sub = new ArrayList<String>();
        for (int i = 0; i <= s.length() - k; i++) {
            sub.add(s.substring(i, i+k));
        }
        if (sub.size() > 0) {
            smallest = sub.get(0);
            largest = sub.get(0);
        }
        for (int i = 1; i < sub.size(); i++) {
        	if (compareString(smallest, sub.get(i)) == 1) // smallest > sub.get(i)
        		smallest = sub.get(i);
        	if (compareString(largest, sub.get(i)) == -1) // largest < sub.get(i)
        		largest = sub.get(i);
        }

        return smallest + "\n" + largest;
    }

    public static int compareString(String s1, String s2) {
    	int result = 0; // equal

    	int len1 = s1.length();
    	int len2 = s2.length();
    	for (int i = 0; i < len1 && i < len2; i++) {
    		if (s1.charAt(i) < s2.charAt(i)) {
    			result = -1; // s1 < s2 in Lexicographical Order
    			break;
    		} else if (s1.charAt(i) > s2.charAt(i)) {
    			result = 1; // s1 > s2 in Lexicographical Order
    			break;
    		}
    	}

    	// if goes here, means all characters in s1 and s2 are the same
    	// so check the length of s1 & s2
    	if (len1 < len2)
    		result = -1;
    	else if (len1 > len2)
    		result = 1;

    	return result;
    }

    // https://www.hackerrank.com/challenges/2d-array/problem
    // 2D array - find the maximum 'hourglass' sum
    public static int findMaxiumHourglass(int[][] arr) {
    	int maxSum = Integer.MIN_VALUE;
    	for (int i = 0; i < arr.length - 2; i++) {
    		for (int j = 0; j < arr[i].length - 2; j++ ) {
    			int sum = 0;
    			// first line
    			sum += arr[i][j];
    			sum += arr[i][j+1];
    			sum += arr[i][j+2];
    			// second line
    			sum += arr[i+1][j+1];
    			// third line
    			sum += arr[i+2][j];
    			sum += arr[i+2][j+1];
    			sum += arr[i+2][j+2];

    			if (sum > maxSum) {
    				maxSum = sum;
    			}
    		}
    	}
    	return maxSum;
    }

	// https://www.hackerrank.com/challenges/between-two-sets/problem
	// Between 2 Set
	public static int getTotalX(int[] a, int[] b) {
		int ret = 0;

		int min = a[a.length - 1];
		int max = b[b.length - 1];

		// loop for every element in [min, max] with the increase step min
		for (int val = min; val <= max; val += min) {
			boolean pass = true;

			// all elements of the first array are all factors of 'val'?
			for (int j = 0; j < a.length && pass; j++) {
				if (val % a[j] != 0) {
					pass = false;
					break;
				}
			}

			// 'val' is a factor of all elements of t he second array?
			for (int j = 0; j < b.length && pass; j++) {
				if (b[j] % val != 0) {
					pass = false;
				}
			}

			// if pass is true mean 'val' is the factor
			if (pass)
				ret++;
		}

		return ret;
	}

    // https://www.hackerrank.com/challenges/cut-the-sticks/problem
    public static int[] cutTheSticks(int[] arr) {
    	ArrayList<Integer> tmpRet = new ArrayList<Integer>();
    	// find the minimum value
    	int count = 0;
    	do {
    		count = 0;
	    	int minVal = 0;
	    	for (int i = 0; i < arr.length; i++) {
	    		if (arr[i] > 0 && (minVal == 0 || minVal > arr[i])) {
	    			minVal = arr[i];
	    		}
	    	}

	    	if (minVal > 0) {
		    	for (int i = 0; i < arr.length; i++) {
		    		if (arr[i] != 0) {
		    			count++;
		    			arr[i] -= minVal;
		    		}
		    	}
		    	tmpRet.add(count);
	    	}
    	} while (count > 0);

    	// convert array list to primitive int array
    	int[] ret = new int[tmpRet.size()];
    	for (int i = 0; i < tmpRet.size(); i++) {
    		ret[i] = tmpRet.get(i);
    	}
    	return ret;
    }

//    1. Progress the Problem
//
//    **Input**
//
//    + an array of strings to search
//    + queries : an array of query strings
//
//    **Output**
//
//    + an integer array occurs for each queries
//
//    **explicit**:
//
//    + each string has 1 ~ 20 characters
//    + string must be equal, not include -> string must have the same length
//
//    -----
//    2. Examples / Test Case
//
//    2.1 two examples at hacker rank
//
//    -----
//    3. Data structure
//
//    **Solution 1:**
//
//    + using an array to store the output
//    + for each query, iterate through the strings array to find out how many occurrences
//
//    -> Complexity: O(n*m)
//
//    Quiz: is there any better solution?
//    Using a map <key, value>?
//
//    **Solution 2:**
//    + convert strings array -> map
//    + iterate through the queries, for each query, how many occurrences in the map?
//
//    -> Complexity: O(1)
//
//    -----
//    4. Algorithm
//    HashMap<String, Integer> map = new HashMap<String, Integer>();
//
//    -----
//    5. Code with intent

	/**
	 *
	 * https://www.hackerrank.com/challenges/sparse-arrays/problem
	 *
	 * @param strings
	 * @param queries
	 * @return
	 */
	static int[] matchingStrings(String[] strings, String[] queries) {
        int[] results = new int[queries.length];

		// generate hash map
		HashMap<String, Integer> strHashMap = new HashMap<String, Integer>();
		for (int i = 0; i < strings.length; i++) {
			String key = strings[i];
			if (strHashMap.containsKey(key)) {
				strHashMap.put(key, strHashMap.get(key) + 1);
			} else { // not exist in hash map
				strHashMap.put(key, 1);
			}
		}

		// iterate through queries
		for (int i = 0; i < queries.length; i++) {
			String key = queries[i];
			Integer value = strHashMap.get(key);
			if (value == null)
				results[i] = 0;
			else
				results[i] = value.intValue();
		}

		return results;

	}

	/**
	 * Linked list
	 *
	 * @author nguyen.truong
	 *
	 */
	class SinglyLinkedListNode {
		SinglyLinkedListNode next;
		int data;
	}

	class DoublyLinkedListNode {
		DoublyLinkedListNode next;
		DoublyLinkedListNode prev;
		int data;
	}

	/**
	 * https://www.hackerrank.com/challenges/compare-two-linked-lists/problem
	 *
	 * @param head1
	 * @param head2
	 * @return true both linked lists are same, otherwise return false
	 */
	static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

		while (head1 != null) {
			if (head2 == null) // head1 is longer than head2
				return false;

			if (head1.data != head2.data) // difference in data
				return false;

			head1 = head1.next;
			head2 = head2.next;
		}
		if (head2 != null) // head1 is shorter than head2
			return false;

		return true; // equal

	}

	/**
	 * https://www.hackerrank.com/challenges/get-the-value-of-the-node-at-a-specific-position-from-the-tail/
	 *
	 * @param head
	 * @param positionFromTail
	 * @return
	 */
	static int getNodeFromTail(SinglyLinkedListNode head, int positionFromTail) {

		// solution 1: using an array
//		ArrayList<Integer> valueArr = new ArrayList<Integer>();
//
//		while (head != null) {
//			valueArr.add(head.data);
//			head = head.next;
//		}
//
//		return valueArr.get(valueArr.size() - positionFromTail - 1);

		// solution 2: using 2 node
		SinglyLinkedListNode current = head;
		SinglyLinkedListNode fromTail = head;
		int idx = 0;

		while (current != null) {
			current = current.next;
			if (idx++ > positionFromTail) {
				fromTail = fromTail.next;
			}
		}

		return fromTail.data;
	}

	/**
	 * https://www.hackerrank.com/challenges/delete-duplicate-value-nodes-from-a-sorted-linked-list/problem
	 *
	 * @param head
	 * @return
	 */
	static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode head) {
		SinglyLinkedListNode current = head;
		SinglyLinkedListNode next;

		while (current != null) {

			next = current.next;
			if (next != null && next.data == current.data) {
				// delete the next node, which is has duplicate data
				current.next = next.next;
				next.next = null;
			} else {
				current = current.next;
			}
		}

		return head;
	}

	/**
	 * https://www.hackerrank.com/challenges/merge-two-sorted-linked-lists/problem?h_r=next-challenge&h_v=legacy
	 *
	 * @param head1
	 * @param head2
	 * @return
	 */
	static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
		SinglyLinkedListNode head = head1;

		return head;
	}

	/**
	 * https://www.hackerrank.com/challenges/reverse-a-doubly-linked-list/problem
	 *
	 * @param head
	 * @return
	 */
	static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {

	}

}
