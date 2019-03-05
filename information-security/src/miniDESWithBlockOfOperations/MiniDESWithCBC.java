package miniDESWithBlockOfOperations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MiniDESWithCBC {

	public static void main(String[] args) throws Exception {
		
		System.out.println("***********************ENCRYPTION AND DECRYPTION IN CBC MODE****************************");

		String firstName = "KALYANBRAT";			 // firstname should be 10 chars long 	
		String UTAID = "1001365715";
		String plainText = firstName + " " + UTAID + "."; 		// firstname<space>UTAID<dot>
		System.out.println("INPUT PLAINTEXT IS '" + plainText + "'");

		// System.out.println("AS PER JULIAN CALENDER DAY MY BIRTHDAY IS 72 ");
		int givenKey = 72;
		int round = 1;
		String keyBits = BasicMethods.decimalToBinaryConversion(givenKey);

		System.out.println("KEY SELECTED FOR ROUND : " + round + " IS "
				+ BasicMethods.selectKey(keyBits, round));

		String binaryText = BasicMethods.plainTextToBinaryConverter(plainText);

		HashMap<Integer, String> plainTextBlocks = new HashMap<Integer, String>();
		String encryptedBlock = null;
		String decryptedBlock = null;
		HashMap<Integer, String> encryptedText = new HashMap<Integer, String>();
		HashMap<Integer, String> decryptedText = new HashMap<Integer, String>();
		
		plainTextBlocks = BasicMethods.createBlocks(binaryText);
		System.out.println("PLAIN TEXT BLOCKS OF 12 BITS ARE : " + plainTextBlocks);
		
		String initializationVector = BasicMethods.generateRandomNumber();
		String IV = initializationVector;
		System.out.println("INITIALIZATION VECTOR: " + initializationVector);

		for (Integer key : plainTextBlocks.keySet()) {
			
			System.out.println("\nCURRENT BLOCK : " + plainTextBlocks.get(key));
			Character[] encryptionInput = BasicMethods.XOR(BasicMethods.convertToCharacterArray(plainTextBlocks.get(key)), BasicMethods.convertToCharacterArray(initializationVector));
			encryptedBlock = EncryptionLogics.DESBasicEncryption(BasicMethods.convertCharacterArrToString(encryptionInput), keyBits);
			initializationVector = encryptedBlock;
			
			encryptedText.put(key, encryptedBlock);
		}
		System.out.println("ENCRYPTED BLOCKS OF 12 BITS :" + encryptedText);
		
		initializationVector = IV;
		
		System.out.println("\nENCRYPTED COMPLETE TEXT: ");
		for (Integer key : encryptedText.keySet())
			System.out.print(encryptedText.get(key));
		
		//Encrypted text is feed to Decryption Method with the provided key. Key is then taken accordingly within the method.
		for (Integer key : encryptedText.keySet()) {
			
			System.out.println("\nCURRENT BLOCK : " + key);
			decryptedBlock = DecryptionLogics.DESBasicDecryption(encryptedText.get(key), keyBits);
			Character[] decryptedBlockCBC = BasicMethods.XOR(BasicMethods.convertToCharacterArray(decryptedBlock), BasicMethods.convertToCharacterArray(initializationVector));
			decryptedText.put(key, BasicMethods.convertCharacterArrToString(decryptedBlockCBC));
			initializationVector = encryptedText.get(key);
		}
		System.out.println("DECRYPTED BLOCKS OF 12 BITS: " + decryptedText);
		
		System.out.println("\nDECRYPTED COMPLETE TEXT: ");
		for (Integer key : decryptedText.keySet())
			System.out.print(decryptedText.get(key));
			
	}
}
