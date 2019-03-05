package miniDESWithBlockOfOperations;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class BasicMethods {

	public static String selectKey(String s, int offset) {
		int i = offset % s.length();
		int keyLength = 8;

		if (offset == 1)
			return (s.substring(i - 1, keyLength));
		else
			return s.substring(i - 1) + s.substring(0, i - 2);
	}

	public static void printCharacterArray(Character[] inputGiven) {
		Character[] input = inputGiven;
		for (int position = 0; position < input.length; position++)
			System.out.print(input[position]);
	}

	public static String SBOX1Value(String input) {
		// System.out.println("inside SBOX1 value");
		String output = "";
		if ("0000".equals(input)) {
			output = "101";
		} else if ("0001".equals(input))
			output = "010";
		else if ("0010".equals(input))
			output = "001";
		else if ("0011".equals(input))
			output = "110";
		else if ("0100".equals(input))
			output = "011";
		else if ("0101".equals(input))
			output = "100";
		else if ("0110".equals(input))
			output = "111";
		else if ("0111".equals(input))
			output = "000";

		else if ("1000".equals(input))
			output = "001";
		else if ("1001".equals(input))
			output = "100";
		else if ("1010".equals(input))
			output = "110";
		else if ("1011".equals(input))
			output = "010";
		else if ("1100".equals(input))
			output = "000";
		else if ("1101".equals(input))
			output = "111";
		else if ("1110".equals(input))
			output = "101";
		else if ("1111".equals(input))
			output = "011";

		return output;
	}

	public static String SBOX2Value(String input) {
		// System.out.println("inside SBOX2 value");
		String output = "";
		if ("0000".equals(input))
			output = "100";
		else if ("0001".equals(input))
			output = "000";
		else if ("0010".equals(input))
			output = "110";
		else if ("0011".equals(input))
			output = "101";
		else if ("0100".equals(input))
			output = "111";
		else if ("0101".equals(input))
			output = "001";
		else if ("0110".equals(input))
			output = "011";
		else if ("0111".equals(input))
			output = "010";

		else if ("1000".equals(input))
			output = "101";
		else if ("1001".equals(input))
			output = "011";
		else if ("1010".equals(input))
			output = "000";
		else if ("1011".equals(input))
			output = "111";
		else if ("1100".equals(input))
			output = "110";
		else if ("1101".equals(input))
			output = "010";
		else if ("1110".equals(input))
			output = "001";
		else if ("1111".equals(input))
			output = "100";

		return output;
	}

	public static Character[] convertToCharacterArray(String input) {
		char[] interChar = input.toCharArray();
		Character[] outputCharObj = new Character[interChar.length];
		for (int i = 0; i < interChar.length; i++)
			outputCharObj[i] = interChar[i];
		return outputCharObj;
	}

	public static String findLeftSplit(String text) {
		String L0 = "";
		for (int i = 0; i < text.length() / 2; i++)
			L0 = L0 + text.charAt(i);
		// System.out.println(L0);
		return L0;
	}

	public static String findRightSplit(String text) {
		String R0 = "";
		;
		for (int i = text.length() / 2; i < text.length(); i++)
			R0 = R0 + text.charAt(i);
		return R0;
	}

	public static Character[] XOR(Character[] expandedRightBits, Character[] key) throws Exception {
		String XORedRightBits = "";
		try {
			for (int position = 0, keyPosition = 0; position < expandedRightBits.length; position++, keyPosition++) {
				if (expandedRightBits[position] == key[keyPosition])
					XORedRightBits = XORedRightBits + 0;
				else
					XORedRightBits = XORedRightBits + 1;
			}
			// System.out.println("XORed rightbits : " + XORedRightBits);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertToCharacterArray(XORedRightBits);
	}

	public static Character[] expansionFunction(String rightBits) throws Exception {
		Character expanedRightBits[] = new Character[8];
		try {
			for (int position = 0; position <= rightBits.length() - 1; position++) {
				// System.out.print(Character.toString(rightBits.charAt(position)));

				switch (position) {
				case 0:
					expanedRightBits[0] = rightBits.charAt(position);
					break;
				case 1:
					expanedRightBits[1] = rightBits.charAt(position);
					break;
				case 2:
					expanedRightBits[3] = rightBits.charAt(position);
					expanedRightBits[5] = rightBits.charAt(position);
					break;
				case 3:
					expanedRightBits[2] = rightBits.charAt(position);
					expanedRightBits[4] = rightBits.charAt(position);
					break;
				case 4:
					expanedRightBits[6] = rightBits.charAt(position);
					break;
				case 5:
					expanedRightBits[7] = rightBits.charAt(position);
					break;
				}
			}
			/*
			 * for (int position = 0; position < expanedRightBits.length;
			 * position++) { System.out.print(expanedRightBits[position]); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return expanedRightBits;
	}

	// http://stackoverflow.com/questions/11242208/splitting-string-in-java-into-fixed-length-chunks
	public static HashMap<Integer, String> createBlocks(String binaryText) {
		int blockSize = 12;
		int j = 1;
		HashMap<Integer, String> binaryBlocks = new HashMap<Integer, String>();
		// Dividing input array into multiple blocks of 12 bits
		for (int i = 0; i <= binaryText.length() / blockSize; i++) {

			String chunk = binaryText.substring(i * blockSize, Math.min((i + 1) * blockSize, binaryText.length()));
			// System.out.println(chunk.length());
			if (chunk.length() < 12) {
				int paddingRequired = 12 - chunk.length();
				// System.out.println("Padding required :" + paddingRequired);
				for (int x = 0; x < paddingRequired; x++)
					chunk = chunk + '0';
			}
			// System.out.println("Chunk length after padding : " +
			// chunk.length());
			binaryBlocks.put(j, chunk);
			j++;
		}
		// System.out.println(binaryBlocks);
		return binaryBlocks;
	}

	public static String plainTextToBinaryConverter(String plainText) {
		String binaryText = "";
		for (int position = 0; position < plainText.length(); position++) {
			String currentLetter = Character.toString(plainText.charAt(position));
			// System.out.print(currentLetter);
			int decimalForm = plaintextToDecimalMapping(currentLetter);
			// System.out.print(" + " + decimalForm);
			binaryText = binaryText + decimalToBinaryConversion(decimalForm);
		}
		return binaryText;
	}

	public static Integer plaintextToDecimalMapping(String plainText) {
		int decimalValue = 1;
		HashMap<String, Integer> plaintextHolder = new HashMap<String, Integer>();
		for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
			String letters = Character.toString(alphabet);
			plaintextHolder.put(letters, decimalValue);
			decimalValue++;
		}
		for (int i = 0, j = 27; i < 10; i++, j++) {
			plaintextHolder.put(Integer.toString(i), j);
		}
		plaintextHolder.put(".", 37);
		plaintextHolder.put(" ", 38);

		/*
		 * for(String key : plaintextHolder.keySet()) System.out.println(key +
		 * " + " + plaintextHolder.values());
		 */
		// System.out.print(" + " + plaintextHolder.get(plainText) + " + ");
		return plaintextHolder.get(plainText);
	}

	public static String decimalToBinaryConversion(int decimalInput) {
		int reminder = 0;
		String plainText = "";
		Stack<Integer> binaryNumber = new Stack<Integer>();
		while (decimalInput != 0) {
			reminder = decimalInput % 2;
			decimalInput = decimalInput / 2;
			binaryNumber.push(reminder);
		}
		// System.out.println("Binary Representation is : ");
		while (!(binaryNumber.isEmpty())) {
			// System.out.print(binaryNumber.pop());
			plainText = plainText + binaryNumber.pop();
		}
		if (plainText.length() < 9) {
			int paddingRequired = 9 - plainText.length();
			for (int x = 0; x < paddingRequired; x++)
				plainText = plainText + '0';
		}

		// System.out.print(plainText);
		return plainText;
	}

	public static String convertCharacterArrToString(Character[] input) {
		Character[] charArray = input;

		StringBuilder sb = new StringBuilder(charArray.length);
		for (Character c : charArray)
			sb.append(c.charValue());

		String str = sb.toString();
		// System.out.println("ARRAY CONVERTED TO STRING: " + str);

		return str;
	}

	static String generateRandomNumber() {
		Random rand = new Random();
		int a = rand.nextInt(2047) + 2048;
		System.out.println(a);
		String randomNo = BasicMethods.decimalToBinaryConversion(a);
		return randomNo;
	}

	static int generateNonceCounter() {
		Random rand = new Random();
		int randomNo = rand.nextInt(2047) + 2048;
		return randomNo;
	}

}
