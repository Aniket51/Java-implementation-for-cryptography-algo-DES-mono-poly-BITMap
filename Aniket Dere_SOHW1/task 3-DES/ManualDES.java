package welcomeProjects;
import java.util.Arrays;
import java.util.BitSet;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

public class ManualDES {

    // Initial Permutation Table
	private static final int[] IP = {
		    58, 50, 42, 34, 26, 18, 10, 2,
		    60, 52, 44, 36, 28, 20, 12, 4,
		    62, 54, 46, 38, 30, 22, 14, 6,
		    64, 56, 48, 40, 32, 24, 16, 8,
		    57, 49, 41, 33, 25, 17, 9, 1,
		    59, 51, 43, 35, 27, 19, 11, 3,
		    61, 53, 45, 37, 29, 21, 13, 5,
		    63, 55, 47, 39, 31, 23, 15, 7
		};


    // Final Permutation Table
	private static final int[] FP = {
		    40, 8, 48, 16, 56, 24, 64, 32,
		    39, 7, 47, 15, 55, 23, 63, 31,
		    38, 6, 46, 14, 54, 22, 62, 30,
		    37, 5, 45, 13, 53, 21, 61, 29,
		    36, 4, 44, 12, 52, 20, 60, 28,
		    35, 3, 43, 11, 51, 19, 59, 27,
		    34, 2, 42, 10, 50, 18, 58, 26,
		    33, 1, 41, 9, 49, 17, 57, 25
		};


    // Permutation Choice 1
	private static final int[] PC1 = {
		    57, 49, 41, 33, 25, 17, 9,
		    1, 58, 50, 42, 34, 26, 18,
		    10, 2, 59, 51, 43, 35, 27,
		    19, 11, 3, 60, 52, 44, 36,
		    63, 55, 47, 39, 31, 23, 15,
		    7, 62, 54, 46, 38, 30, 22,
		    14, 6, 61, 53, 45, 37, 29,
		    21, 13, 5, 28, 20, 12, 4
		};


    // Permutation Choice 2
	private static final int[] PC2 = {
		    14, 17, 11, 24, 1, 5,
		    3, 28, 15, 6, 21, 10,
		    23, 19, 12, 4, 26, 8,
		    16, 7, 27, 20, 13, 2,
		    41, 52, 31, 37, 47, 55,
		    30, 40, 51, 45, 33, 48,
		    44, 49, 39, 56, 34, 53,
		    46, 42, 50, 36, 29, 32
		};


    // Expansion Table
	private static final int[] E = {
		    32, 1, 2, 3, 4, 5,
		    4, 5, 6, 7, 8, 9,
		    8, 9, 10, 11, 12, 13,
		    12, 13, 14, 15, 16, 17,
		    16, 17, 18, 19, 20, 21,
		    20, 21, 22, 23, 24, 25,
		    24, 25, 26, 27, 28, 29,
		    28, 29, 30, 31, 32, 1
		};


    // S-Boxes
	private static final int[][][] SBOX = {
		    // S1
			{
		        {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
		        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
		        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
		        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
		    },
		    // S2
		    {
		        {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
		        {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
		        {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
		        {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
		    },
		    // S3
		    {
		        {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
		        {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
		        {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
		        {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
		    },
		    // S4
		    {
		        {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
		        {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
		        {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
		        {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
		    },
		    // S5
		    {
		        {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
		        {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
		        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
		        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
		    },
		    // S6
		    {
		        {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
		        {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
		        {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
		        {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
		    },
		    // S7
		    {
		        {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
		        {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
		        {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
		        {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
		    },
		    // S8
		    {
		        {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
		        {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
		        {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
		        {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
		    }
		};



    // Permutation Function
	private static final int[] P = {
		    16, 7, 20, 21,
		    29, 12, 28, 17,
		    1, 15, 23, 26,
		    5, 18, 31, 10,
		    2, 8, 24, 14,
		    32, 27, 3, 9,
		    19, 13, 30, 6,
		    22, 11, 4, 25
		};


    // Left Shifts
	private static final int[] SHIFTS = {
		    1, 1, 2, 2, 
		    2, 2, 2, 2, 
		    1, 2, 2, 2, 
		    2, 2, 2, 1
		};


    // Utility method to permute key
	private static BitSet permute(BitSet key, int[] table) {
	    BitSet permutedKey = new BitSet(table.length);

	    for (int i = 0; i < table.length; i++) {
	        if (key.get(table[i] - 1)) {  // Subtract 1 because DES tables are 1-indexed
	            permutedKey.set(i);
	        }
	    }

	    return permutedKey;
	}


    // Key schedule generation
	private static BitSet[] generateSubKeys(BitSet key) {
	    BitSet[] subKeys = new BitSet[16];

	    // Apply PC-1 to the key (56 bits)
	    BitSet key56 = permute(key, PC1);

	    // Split into two 28-bit halves
	    BitSet left = key56.get(0, 28);
	    BitSet right = key56.get(28, 56);

	    // Generate 16 sub-keys
	    for (int i = 0; i < 16; i++) {
	        // Left shift both halves
	        left = leftShift(left, SHIFTS[i], 28);
	        right = leftShift(right, SHIFTS[i], 28);

	        // Combine halves and apply PC-2 to get the 48-bit sub-key
	        BitSet combined = combineHalves(left, right);
	        subKeys[i] = permute(combined, PC2);
	    }

	    return subKeys;
	}

	private static BitSet leftShift(BitSet bs, int n, int size) {
	    BitSet shifted = new BitSet(size);
	    for (int i = 0; i < size; i++) {
	        if (bs.get((i + n) % size)) {
	            shifted.set(i);
	        }
	    }
	    return shifted;
	}

	private static BitSet combineHalves(BitSet left, BitSet right) {
	    BitSet combined = new BitSet(56);
	    combined.or(left);
	    for (int i = 0; i < 28; i++) {
	        if (right.get(i)) {
	            combined.set(i + 28);
	        }
	    }
	    return combined;
	}


    // Feistel function
	private static BitSet feistel(BitSet right, BitSet key) {
	    // Expand the right half-block from 32 to 48 bits using the E-table
	    BitSet expandedRight = permute(right, E);

	    // XOR the expanded right half-block with the round key
	    expandedRight.xor(key);

	    // Apply S-boxes to the result
	    BitSet outputFromSBoxes = applySBoxes(expandedRight);

	    // Apply the P-table permutation
	    BitSet result = permute(outputFromSBoxes, P);

	    return result;
	}

	private static BitSet applySBoxes(BitSet input) {
	    BitSet output = new BitSet(32);

	    for (int i = 0; i < 8; i++) {
	        // Select 6 bits for each S-box
	        BitSet sixBitSegment = input.get(i * 6, (i + 1) * 6);

	        // Determine row and column for the S-box
	        int row = (sixBitSegment.get(0) ? 2 : 0) + (sixBitSegment.get(5) ? 1 : 0);
	        int column = 0;
	        for (int j = 1; j <= 4; j++) {
	            if (sixBitSegment.get(j)) {
	                column += 1 << (4 - j);
	            }
	        }

	        // Get the value from the S-box
	        int value = SBOX[i][row][column];

	        // Set the 4 output bits
	        for (int k = 0; k < 4; k++) {
	            if ((value & (1 << (3 - k))) != 0) {
	                output.set(i * 4 + k);
	            }
	        }
	    }

	    return output;
	}


    // DES round
	private static void desRound(BitSet left, BitSet right, BitSet key) {
	    // Store the original right half
	    BitSet originalRight = (BitSet) right.clone();

	    // Apply the Feistel function to the right half and the round key
	    BitSet feistelResult = feistel(right, key);

	    // XOR the Feistel function result with the left half
	    left.xor(feistelResult);

	    // The new right half becomes the original left half
	    right.clear();
	    right.or(left);

	    // The new left half becomes the original right half
	    left.clear();
	    left.or(originalRight);
	}

	private static BitSet fromByteArray(byte[] bytes) {
	    BitSet bits = new BitSet(bytes.length * 8);

	    for (int i = 0; i < bytes.length; i++) {
	        for (int j = 0; j < 8; j++) {
	            if ((bytes[i] & (1 << (7 - j))) != 0) {
	                bits.set(i * 8 + j);
	            }
	        }
	    }

	    return bits;
	}


	private static byte[] toByteArray(BitSet bits) {
	    // Ensure the length is a multiple of 8 bytes (64 bits)
	    int byteLength = (bits.length() + 63) / 64 * 8;
	    byte[] bytes = new byte[byteLength];

	    for (int i = 0; i < bits.length(); i++) {
	        if (bits.get(i)) {
	            bytes[i / 8] |= 1 << (7 - (i % 8));
	        }
	    }

	    return bytes;
	}
	private static byte[] padPlainText(byte[] plaintext) {
	    int paddingLength = 8 - (plaintext.length % 8);
	    paddingLength = paddingLength == 0 ? 8 : paddingLength;
	    byte[] padded = Arrays.copyOf(plaintext, plaintext.length + paddingLength);
	    Arrays.fill(padded, plaintext.length, padded.length, (byte) paddingLength);
	    return padded;
	}
	private static byte[] removePadding(byte[] decryptedText) {
	    if (decryptedText.length == 0) {
	        return decryptedText;
	    }

	    int paddingLength = decryptedText[decryptedText.length - 1];
	    if (paddingLength < 1 || paddingLength > 8) {
	        throw new IllegalArgumentException("Invalid padding length");
	    }

	    for (int i = decryptedText.length - paddingLength; i < decryptedText.length; i++) {
	        if (decryptedText[i] != paddingLength) {
	            throw new IllegalArgumentException("Invalid padding format");
	        }
	    }

	    return Arrays.copyOf(decryptedText, decryptedText.length - paddingLength);
	}




	public static byte[] encrypt(byte[] plaintext, byte[] keyBytes) {
	    BitSet key = fromByteArray(keyBytes);
	    BitSet[] subKeys = generateSubKeys(key);

	    BitSet input = fromByteArray(plaintext);
	    // Apply initial permutation
	    input = permute(input, IP);

	    BitSet left = input.get(0, 32);
	    BitSet right = input.get(32, 64);

	    for (int i = 0; i < 16; i++) {
	        desRound(left, right, subKeys[i]);
	    }

	    // Swap left and right before final combination
	    BitSet combined = new BitSet(64);
	    for (int i = 0; i < 32; i++) {
	        if (right.get(i)) {
	            combined.set(i);
	        }
	        if (left.get(i)) {
	            combined.set(i + 32);
	        }
	    }

	    // Apply final permutation
	    combined = permute(combined, FP);

	    return toByteArray(combined);
	}
	public static byte[] decrypt(byte[] ciphertext, byte[] keyBytes) {
	    BitSet key = fromByteArray(keyBytes);
	    BitSet[] subKeys = generateSubKeys(key);

	    BitSet input = fromByteArray(ciphertext);
	    // Apply initial permutation
	    input = permute(input, IP);

	    BitSet left = input.get(0, 32);
	    BitSet right = input.get(32, 64);

	    // Apply the rounds in reverse order for decryption
	    for (int i = 15; i >= 0; i--) {
	        desRound(left, right, subKeys[i]);
	    }

	    // Swap left and right before final combination
	    BitSet combined = new BitSet(64);
	    for (int i = 0; i < 32; i++) {
	        if (right.get(i)) {
	            combined.set(i);
	        }
	        if (left.get(i)) {
	            combined.set(i + 32);
	        }
	    }

	    // Apply final permutation
	    combined = permute(combined, FP);

	    return toByteArray(combined);
	}



	
	public static void main(String[] args) {
	    byte[] key = "12345678".getBytes();
	    byte[] plaintext = "I remember as a child, and as a young budding naturalist, spending all my time observing and testing the world around me moving pieces, altering the flow of things, and documenting ways the world responded to me. Now, as an adult and a professional naturalist, I’ve approached language in the same way, not from an academic point of view but as a curious child still building little mud dams in creeks and chasing after frogs. So this book is an odd thing: it is a naturalist’s walk through the language-making landscape of the English language, and following in the naturalist’s tradition it combines observation, experimentation, speculation, and documentation activities we don’t normally associate with language. This book is about testing, experimenting, and playing with language. It is a handbook of tools and techniques for taking words apart and putting them back together again in ways that I hope are meaningful and legitimate (or even illegitimate)1. This book is about peeling back layers in search of the language-making energy of the human spirit2. It is about the gaps in meaning that we urgently need to notice and name the places where our dreams and ideals are no longer fulfilled by a society that has become fast-paced and hyper-commercialized3. Language is meant to be a playful, ever-shifting creation but we have been taught, and most of us continue to believe, that language must obediently follow precisely prescribed rules that govern clear sentence structures, specific word orders, correct spellings, and proper pronunciations4. If you make a mistake or step out of bounds there are countless, self-appointed language experts who will promptly push you back into safe terrain and scold you for your errors. And in case you need reminding, there are hundreds of dictionaries and grammar books to ensure that you remember the “right” way to use English.".getBytes();
	    byte[] paddedPlaintext = padPlainText(plaintext);

	    // Encrypt each 8-byte block separately
	    ByteArrayOutputStream encryptedStream = new ByteArrayOutputStream();
	    for (int i = 0; i < paddedPlaintext.length; i += 8) {
	        byte[] block = Arrays.copyOfRange(paddedPlaintext, i, i + 8);
	        byte[] encryptedBlock = encrypt(block, key);
	        encryptedStream.write(encryptedBlock, 0, encryptedBlock.length);
	    }
	    byte[] encrypted = encryptedStream.toByteArray();
	    String hexEncrypted = toHexString(encrypted);
	    System.out.println("Encrypted Text (Hex): " + hexEncrypted);

	    // Decrypt each 8-byte block separately
	    ByteArrayOutputStream decryptedStream = new ByteArrayOutputStream();
	    for (int i = 0; i < encrypted.length; i += 8) {
	        byte[] block = Arrays.copyOfRange(encrypted, i, i + 8);
	        byte[] decryptedBlock = decrypt(block, key);
	        decryptedStream.write(decryptedBlock, 0, decryptedBlock.length);
	    }
	    byte[] decrypted = decryptedStream.toByteArray();
	    byte[] unpaddedDecrypted = removePadding(decrypted);
	    System.out.println("Decrypted Text: " + new String(unpaddedDecrypted));
	}


	private static String toHexString(byte[] bytes) {
	    BigInteger bi = new BigInteger(1, bytes);
	    return String.format("%0" + (bytes.length << 1) + "X", bi);
	}

}
