package miniDESWithBlockOfOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MiniDESWithOFB {

	public static void main(String[] args) throws Exception {

		System.out.println("***********************ENCRYPTION AND DECRYPTION IN OFB MODE****************************");

		String firstName = "KALYANBRAT"; // firstname should be 10 chars long
		String UTAID = "1001365715";
		String plainText = firstName + " " + UTAID + "."; // firstname<space>UTAID<dot>
		System.out.println("INPUT PLAINTEXT IS '" + plainText + "'");

		// System.out.println("AS PER JULIAN CALENDER DAY MY BIRTHDAY IS 72 ");
		int givenKey = 72;
		int round = 1;
		String keyBits = BasicMethods.decimalToBinaryConversion(givenKey);

		System.out.println("KEY SELECTED FOR ROUND : " + round + " IS " + BasicMethods.selectKey(keyBits, round));

		String binaryText = BasicMethods.plainTextToBinaryConverter(plainText);

		HashMap<Integer, String> plainTextBlocks = new HashMap<Integer, String>();
		String encryptedBlock = null;
		String decryptedBlock = null;
		HashMap<Integer, String> encryptedText = new HashMap<Integer, String>();
		HashMap<Integer, String> decryptedText = new HashMap<Integer, String>();

		plainTextBlocks = BasicMethods.createBlocks(binaryText);
		System.out.println("PLAINTEXT OF 12 BITS: " + plainTextBlocks);

		String initializationVector = BasicMethods.generateRandomNumber();
		String IV = initializationVector;
		System.out.println("INITIALIZATION VECTOR: " + initializationVector);
		
		for (Integer key : plainTextBlocks.keySet()) {

			System.out.println("\nCURRENT BLOCK : " + plainTextBlocks.get(key));
			String encryptionOutput = EncryptionLogics.DESBasicEncryption(initializationVector, keyBits);
			Character[] cipherOutput = BasicMethods.XOR(BasicMethods.convertToCharacterArray(encryptionOutput),
					BasicMethods.convertToCharacterArray(plainTextBlocks.get(key)));
			encryptedBlock = BasicMethods.convertCharacterArrToString(cipherOutput);
			initializationVector = encryptionOutput;

			encryptedText.put(key, encryptedBlock);
		}

		System.out.println("ENCRYPTED BLOCKS OF 12 BITS :" + encryptedText);

		System.out.println("\nENCRYPTED COMPLETE TEXT: ");
		for (Integer key : encryptedText.keySet())
			System.out.print(encryptedText.get(key));

		// Encrypted text is feed to Decryption Method with the provided key.
		// Key is then taken accordingly within the method.
		initializationVector = IV;

		for (Integer key : encryptedText.keySet()) {

			System.out.println("\nCURRENT BLOCK : " + encryptedText.get(key));
			String encryptedOP = EncryptionLogics.DESBasicEncryption(initializationVector, keyBits);
			Character[] plainOutput = BasicMethods.XOR(BasicMethods.convertToCharacterArray(encryptedText.get(key)),
					BasicMethods.convertToCharacterArray(encryptedOP));
			decryptedText.put(key, (BasicMethods.convertCharacterArrToString(plainOutput)));
			initializationVector = encryptedOP;
		}

		System.out.println("DECRYPTED BLOCKS OF 12 BITS :" + decryptedText);

		System.out.println("\nDECRYPTED COMPLETE TEXT: ");
		for (Integer key : decryptedText.keySet())
			System.out.print(decryptedText.get(key));

	}
}
