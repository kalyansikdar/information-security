package miniDes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MiniDES {

	private static final String[][] String = null;
	public static void main(String[] args) throws Exception {
		
		 String firstName = "KALYANBRAT"; String UTAID = "1001365715"; String
		  plainText = firstName + " " + UTAID + ".";
		  System.out.println("INPUT PLAINTEXT IS '" + plainText + "'");
		  
		  System.out.println("AS PER JULIAN CALENDER DAY MY BIRTHDAY IS 72 ");
		  int key = 72;
		  
		  String K1 = decimalToBinaryConversion(key);
		  System.out.println("KEY IN DECIMAL FORM: " + K1);
		  System.out.println("PLAINTEXT IN DECIMAL FORM : "); 
		  String binaryText = plainTextToBinaryConverter(plainText); //
		  System.out.println(binaryText); 
		  Encryption(binaryText, K1);
		  
		 
		//XOR(10101101,10101010);
	}
	public static void Encryption(String binaryText, String K1) throws Exception {
		try {
			String keyInDecimal = decimalToBinaryConversion(72);
			char[] keyDecimal = keyInDecimal.toCharArray();
			Character[] keyDecimalObj = new Character[keyDecimal.length];
		    for (int i = 0; i < keyDecimal.length; i++) 
		    	keyDecimalObj[i]=keyDecimal[i];
		    
			int binaryTextLength = 0;
			binaryTextLength = binaryText.length();
			System.out.println("Binary Text Length: " + binaryTextLength);
			int numberOfBlocks = 98 / 12 + 1;
			System.out.println("Number of Blocks : " + numberOfBlocks);
			int paddingNeeded = 12 - binaryTextLength % 12;
			System.out.println("Padding Needed :" + paddingNeeded);
			HashMap<Integer, String> binaryBlocks = new HashMap<Integer, String>();

			binaryBlocks = createBlocks(binaryText);
			// System.out.println(binaryBlocks);
			for (Integer key : binaryBlocks.keySet()) {
				String L0 = "";
				String R0 = "";
				String currentBlock = binaryBlocks.get(key);
				System.out.println("Current Block : " + currentBlock);

				L0 = findLeftSplit(currentBlock);
				R0 = findRightSplit(currentBlock);
				
				System.out.println("Left Hand of the binary String: " + L0);
				System.out.println("Right Hand of the binary String: " + R0);
				
				System.out.println("Expanded righthand bits: ");
				
				ArrayList<String> output = encryptionRound(L0, R0, K1);
						System.out.println(output);
					System.out.println(output.get(0));
				String K2 = "00100000";
				System.out.println("ROUND TWO...");
			    ArrayList<String> output2 = encryptionRound(output.get(0), output.get(1), K2);
			    
			    System.out.println("Encrypted Text: " + output2.get(1)+output.get(0));
				//break;
			}
			/*
			 * for (int i = 0; i <= (binaryText.length()) / 2; i++) L0 = L0 +
			 * binaryText.charAt(i); System.out.println("L0: " + L0); for (int i
			 * = (binaryText.length()) / 2; i <= binaryText.length() - 1; i++)
			 * R0 = R0 + binaryText.charAt(i); System.out.println("R0 : " + R0);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<String> encryptionRound(String L0, String R0, String K1) throws Exception{
		System.out.println("Left Hand of the binary String: " + L0);
		System.out.println("Right Hand of the binary String: " + R0);
		
		System.out.println("Expanded righthand bits: ");
		
		Character[] expandedRightBits = MiniDES.expansionFunction(R0);
		for (int position = 0; position < expandedRightBits.length; position++)
			System.out.print(expandedRightBits[position]);
		System.out.println();
		
		Character[] keyDecimalObj = MiniDES.convertToCharacterArray(K1);
		//xor
		String expandXORKey = MiniDES.XOR(expandedRightBits,keyDecimalObj);
		System.out.println("Expanded and XORed with Key:" + expandXORKey);
		
		Character[] feedToSBOX1 = MiniDES.convertToCharacterArray(MiniDES.findLeftSplit(expandXORKey));
		Character[] feedToSBOX2 = MiniDES.convertToCharacterArray(MiniDES.findRightSplit(expandXORKey));
		
		String feedToSB1 = MiniDES.findLeftSplit(expandXORKey);
		String feedToSB2 = MiniDES.findRightSplit(expandXORKey);
		System.out.println("Feed to SBOX1: " + feedToSB1);
		System.out.println("Feed to SBOX2: " + feedToSB2);
					
		String funcofkey1 = MiniDES.SBOX1Value(feedToSB1);
		System.out.println(funcofkey1);
		String funcofkey2 = MiniDES.SBOX2Value(feedToSB2);
		System.out.println(funcofkey2);
		
		String funcofkey = funcofkey1+funcofkey2;
		System.out.println("FUNC OF KEY: " + funcofkey);
		
		String R1= MiniDES.XOR(MiniDES.convertToCharacterArray(funcofkey), MiniDES.convertToCharacterArray(L0));
		System.out.println("R1: " + R1);
		
		String L1=R0;
		System.out.println("L1: " + L1);
		
		/*char[] K2Binary = K1.toCharArray();
		Character[] K2Obj = new Character[K2Binary.length];
		System.out.print("K2: ");

		for (int i = 0; i < K2Binary.length-1; i++){ 
	    	K2Obj[i]=K2Binary[i+1];
	    System.out.print(K2Obj[i]);
		}*/
		ArrayList<String> output = new ArrayList<String>();
		output.add(L1);
		output.add(R1);
		
		//output.add((String)K2Obj.toString());
		
		return output;
	    }
	
	public static String SBOX1Value(String input){
		System.out.println("inside SBOX1 value");
		String output = "";
		if ("0000".equals(input)){ System.out.println("GOT IT");
			output="101";}
		else if ("0001".equals(input))
			output="010";
		else if ("0010".equals(input))
			output="001";
		else if("0011".equals(input))
			output="110";
		else if("0100".equals(input))
			output="011";
		else if("0101".equals(input))
			output="100";
		else if("0110".equals(input))
			output="111";
		else if("0111".equals(input))
			output="000";

		else if("1000".equals(input))
			output="001";
		else if("1001".equals(input))
			output="100";
		else if("1010".equals(input))
			output="110";
		else if("1011".equals(input))
			output="010";
		else if("1100".equals(input))
			output="000";
		else if("1101".equals(input))
			output="111";
		else if("1110".equals(input))
			output="101";
		else if("1111".equals(input))
			output="011";

		return output;
	}
	public static String SBOX2Value(String input){
		System.out.println("inside SBOX1 value");
		String output = "";
		if ("0000".equals(input))
			output="100";
		else if ("0001".equals(input))
			output="000";
		else if ("0010".equals(input))
			output="110";
		else if("0011".equals(input))
			output="101";
		else if("0100".equals(input))
			output="111";
		else if("0101".equals(input))
			output="001";
		else if("0110".equals(input))
			output="011";
		else if("0111".equals(input))
			output="010";

		else if("1000".equals(input))
			output="101";
		else if("1001".equals(input))
			output="011";
		else if("1010".equals(input))
			output="000";
		else if("1011".equals(input))
			output="111";
		else if("1100".equals(input))
			output="110";
		else if("1101".equals(input))
			output="010";
		else if("1110".equals(input))
			output="001";
		else if("1111".equals(input))
			output="100";

		return output;
	}
	
	public static Character[] convertToCharacterArray(String input){
			char[] interChar = input.toCharArray();
			Character[] outputCharObj = new Character[interChar.length];
			    for (int i = 0; i < interChar.length; i++) 
			    	outputCharObj[i]=interChar[i];
			return outputCharObj;
	}
	public static String findLeftSplit(String text)
	{
		String L0="";
		for (int i = 0; i < text.length() / 2; i++)
			L0 = L0 + text.charAt(i);
		//System.out.println(L0);
		return L0;
	}
	
	public static String findRightSplit(String text)
	{
		String R0="";;
		for (int i = text.length() / 2; i < text.length(); i++)
			R0 = R0 + text.charAt(i);
		return R0;
	}
	
	public static String XOR(Character[] expandedRightBits, Character[] key) throws Exception{
		String XORedRightBits = "";
		try{
			for (int position = 0, keyPosition=0; position < expandedRightBits.length; position++, keyPosition++)
			{
				if(expandedRightBits[position]==key[keyPosition])
					XORedRightBits=XORedRightBits + 0;
				else 
					XORedRightBits = XORedRightBits + 1;
			}
			System.out.println("XORed rightbits : " + XORedRightBits);		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return XORedRightBits;
	}

	public static Character[] expansionFunction(String rightBits) throws Exception {
		Character expanedRightBits[] = new Character[8];
		try {
			for (int position = 0; position <= rightBits.length()-1; position++) {
				//System.out.print(Character.toString(rightBits.charAt(position)));
				
				switch (position) {
					case 0 :
						expanedRightBits[0] = rightBits.charAt(position);
						break;
					case 1 :
						expanedRightBits[1] = rightBits.charAt(position);
						break;
					case 2 :
						expanedRightBits[3] = rightBits.charAt(position);
						expanedRightBits[5] = rightBits.charAt(position);
						break;
					case 3 :
						expanedRightBits[2] = rightBits.charAt(position);
						expanedRightBits[4] = rightBits.charAt(position);
						break;
					case 4 :
						expanedRightBits[6] = rightBits.charAt(position);
						break;
					case 5 :
						expanedRightBits[7] = rightBits.charAt(position);
						break;
				}
			}
			/*for (int position = 0; position < expanedRightBits.length; position++) {
				System.out.print(expanedRightBits[position]);
			}*/
			
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

			String chunk = binaryText.substring(i * blockSize,
					Math.min((i + 1) * blockSize, binaryText.length()));
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
			String currentLetter = Character
					.toString(plainText.charAt(position));
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
		if(plainText.length() < 9){
			int paddingRequired = 9 - plainText.length();
			for (int x = 0; x < paddingRequired; x++)
				plainText = plainText + '0';
		}
			
		// System.out.print(plainText);
		return plainText;
	}

}
