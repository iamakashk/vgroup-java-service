package com.vgroup.assesment.helper;

import java.util.ArrayList;
import java.util.List;

public class PermutationHelper {

	public static ArrayList<String> generatePermutation(String str) {

		// If string is empty
		if (str.length() == 0) {

			// Return an empty arraylist
			ArrayList<String> empty = new ArrayList<>();
			empty.add("");
			return empty;
		}

		// Take first character of str
		char ch = str.charAt(0);

		// Take sub-string starting from the
		// second character
		String subStr = str.substring(1);

		// Recurvise call
		ArrayList<String> prevResult = generatePermutation(subStr);

		// Store the generated permutations
		// into the resultant arraylist
		ArrayList<String> Res = new ArrayList<>();

		for (String val : prevResult) {
			for (int i = 0; i <= val.length(); i++) {
				Res.add(val.substring(0, i) + ch + val.substring(i));
			}
		}

		// Return the resultant arraylist
		return Res;
	}

	public static String swapString(String a, int i, int j) {
		char[] b = a.toCharArray();
		char ch;
		ch = b[i];
		b[i] = b[j];
		b[j] = ch;
		return String.valueOf(b);
	}
}
