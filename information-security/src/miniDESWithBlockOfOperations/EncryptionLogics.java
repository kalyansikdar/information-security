package miniDESWithBlockOfOperations;

public class EncryptionLogics {
	
	public static String DESBasicEncryption(String binaryText, String key) throws Exception {
		String encryptedTextBlock = null;
		try {
			int roundNumber = 1;
			String keyFirstRound = BasicMethods.selectKey(key, roundNumber);
			Character[] keyForRound1 = BasicMethods.convertToCharacterArray(keyFirstRound);

			System.out.println("\nENCRYPTION : ROUND 1: ");

			String leftBits = "";
			String rightBits = "";
			String currentBlock = binaryText;
			System.out.println("Current Block : " + currentBlock);

			leftBits = BasicMethods.findLeftSplit(currentBlock);
			rightBits = BasicMethods.findRightSplit(currentBlock);

			Character[] L0 = BasicMethods.convertToCharacterArray(leftBits);

			System.out.print("\nLEFT HAND OF PLAIN TEXT : ");
			for (int position = 0; position < L0.length; position++)
				System.out.print(L0[position]);

			Character[] R0 = BasicMethods.convertToCharacterArray(rightBits);

			System.out.print("\nRIGHT HAND OF PLAIN TEXT: ");
			for (int position = 0; position < R0.length; position++)
				System.out.print(R0[position]);

			Character[] ER0 = BasicMethods.expansionFunction(rightBits);

			System.out.print("\n\nEXPANDED RIGHT BITS: ");
			for (int position = 0; position < ER0.length; position++)
				System.out.print(ER0[position]);

			Character[] feed2SBOX = BasicMethods.XOR(ER0, keyForRound1);

			System.out.print("\n\nFEED TO SBOX : ");
			for (int position = 0; position < ER0.length; position++)
				System.out.print(feed2SBOX[position]);

			String feedToSBOX = BasicMethods.convertCharacterArrToString(feed2SBOX);

			String S1Feed = BasicMethods.findLeftSplit(feedToSBOX);
			String S2Feed = BasicMethods.findRightSplit(feedToSBOX);

			String S1 = BasicMethods.SBOX1Value(S1Feed);
			String S2 = BasicMethods.SBOX2Value(S2Feed);

			String FR0K1 = S1 + S2;

			System.out.println("\nS1 :" + S1);
			System.out.println("S2 :" + S2);

			System.out.println("\nFR0K1 : " + FR0K1);

			Character[] funcOfR0K1 = BasicMethods.convertToCharacterArray(FR0K1);

			Character[] R1 = BasicMethods.XOR(funcOfR0K1, L0);

			System.out.print("\nR1: ");
			for (int position = 0; position < R1.length; position++)
				System.out.print(R1[position]);
			
			Character[] L1 = R0;
			
			System.out.print("\nL1: ");
			for (int position = 0; position < L1.length; position++)
				System.out.print(L1[position]);

			System.out.println(
					"\n************** FIRST ROUND OF ENCRYPTION COMPLETE****************");
			System.out.println("SECOND ROUND STARTS: ");

			System.out.print("\nR1: ");
			for (int position = 0; position < R1.length; position++)
				System.out.print(R1[position]);

			System.out.print("\nL1: ");
			for (int position = 0; position < L1.length; position++)
				System.out.print(L1[position]);

			roundNumber = roundNumber + 1;
			System.out.println("\nROUND NUMBER : " + roundNumber);
			
			Character[] keyRound2 = BasicMethods.convertToCharacterArray(
					BasicMethods.selectKey(key, roundNumber));
			
			System.out.println("\nKEY FOR THE ROUND : " );
			for (int position = 0; position < keyRound2.length; position++)
				System.out.print(keyRound2[position]);

			Character[] ER2 = BasicMethods.expansionFunction(
					BasicMethods.convertCharacterArrToString(R1));

			System.out.print("\n\nEXPANDED RIGHT BITS FOR ROUND 2: ");
			for (int position = 0; position < ER2.length; position++)
				System.out.print(ER2[position]);

			Character[] feed2SBOXRound2 = BasicMethods.XOR(ER2, keyRound2);

			System.out.print("\n\nFEED TO SBOX : ");
			for (int position = 0; position < ER2.length; position++)
				System.out.print(feed2SBOXRound2[position]);

			String feedToSBOXRound2 = BasicMethods.convertCharacterArrToString(
					feed2SBOXRound2);

			S1Feed = BasicMethods.findLeftSplit(feedToSBOXRound2);
			S2Feed = BasicMethods.findRightSplit(feedToSBOXRound2);

			S1 = BasicMethods.SBOX1Value(S1Feed);
			S2 = BasicMethods.SBOX2Value(S2Feed);

			System.out.println("\nS1 :" + S1);
			System.out.println("S2 :" + S2);

			String FR1K2 = S1 + S2;
			System.out.println("\nFR1K2 : " + FR1K2);

			Character[] funcOfR1K2 = BasicMethods.convertToCharacterArray(FR1K2);

			Character[] R2 = BasicMethods.XOR(funcOfR1K2, L1);

			System.out.print("\nR2: ");
			for (int position = 0; position < R2.length; position++)
				System.out.print(R2[position]);

			Character[] L2 = R1;

			System.out.print("\nL2: ");
			for (int position = 0; position < L2.length; position++)
				System.out.print(L2[position]);

			System.out.print("\n\nENCRYPTED TEXT IS : ");
			for (int position = 0; position < R2.length; position++)
				System.out.print(R2[position]);
			for (int position = 0; position < L2.length; position++)
				System.out.print(L2[position]);
			
			//Character[] con = R2 + L2;
			
			String encryptedR2 = BasicMethods.convertCharacterArrToString(R2);
			String encryptedL2 = BasicMethods.convertCharacterArrToString(L2);
			
			encryptedTextBlock = encryptedR2.concat(encryptedL2);
			System.out.println("\nENCRYPTED TEXT BLOCK: " + encryptedTextBlock);
			
			System.out.println("\n\n - - - - ENCRYPTION OF THE BLOCK IS COMPLETED - - - -");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedTextBlock;
	}

}
