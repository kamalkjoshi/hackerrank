package org.test;

public class NumberConversion {

	public static String toHexString(int i) {
		return toUnsignedString0(i, 4);
	}

	public static String toOctalString(int i) {
		return toUnsignedString0(i, 3);
	}

	public static String toBinaryString(int i) {
		return toUnsignedString0(i, 1);
	}

	/**
	 * Convert the integer to an unsigned number.
	 */
	private static String toUnsignedString0(int val, int shift) {
		// assert shift > 0 && shift <=5 : "Illegal shift value";
		int mag = Integer.SIZE - Integer.numberOfLeadingZeros(val);
		int chars = Math.max(((mag + (shift - 1)) / shift), 1);
		char[] buf = new char[chars];

		formatUnsignedInt(val, shift, buf, 0, chars);

		return new String(buf);
	}

	/**
	 * All possible chars for representing a number as a String
	 */
	final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z'};

	/**
	 * Format a long (treated as unsigned) into a character buffer.
	 * 
	 * @param val
	 *            the unsigned int to format
	 * @param shift
	 *            the log2 of the base to format in (4 for hex, 3 for octal, 1
	 *            for binary)
	 * @param buf
	 *            the character buffer to write to
	 * @param offset
	 *            the offset in the destination buffer to start at
	 * @param len
	 *            the number of characters to write
	 * @return the lowest character location used
	 */
	static int formatUnsignedInt(int val, int shift, char[] buf, int offset,
			int len) {
		int charPos = len;
		int radix = 1 << shift;
		int mask = radix - 1;
		do {
			buf[offset + --charPos] = digits[val & mask];
			val >>>= shift;
		} while (val != 0 && charPos > 0);

		return charPos;
	}

	public static void main(String[] args) {

		for (int i = 1; i < 500;) {
			System.out.println(i + " Binary: " + toBinaryString(i) + " ,Octal: "
					+ toOctalString(i) + " ,Hex:" + toHexString(i));
			i = i * 2;
		}
	}
}
