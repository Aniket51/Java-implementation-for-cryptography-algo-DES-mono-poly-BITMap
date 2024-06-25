package welcomeProjects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FindOutSingleCharacter {

	public static void main(String[] args) {
		String sentence = "Y HUCUCRUH QI Q SXYBT, QDT QI Q OEKDW RKTTYDW DQJKHQBYIJ, IFUDTYDW QBB CO JYCU ERIUHLYDW QDT JUIJYDW JXU MEHBT QHEKDT CU CELYDW FYUSUI, QBJUHYDW JXU VBEM EV JXYDWI, QDT TESKCUDJYDW MQOI JXU MEHBT HUIFEDTUT JE CU. DEM, QI QD QTKBJ QDT Q FHEVUIIYEDQB DQJKHQBYIJ, Y’LU QFFHEQSXUT BQDWKQWU YD JXU IQCU MQO, DEJ VHEC QD QSQTUCYS FEYDJ EV LYUM RKJ QI Q SKHYEKI SXYBT IJYBB RKYBTYDW BYJJBU CKT TQCI YD SHUUAI QDT SXQIYDW QVJUH VHEWI. IE JXYI REEA YI QD ETT JXYDW: YJ YI Q DQJKHQBYIJ’I MQBA JXHEKWX JXU BQDWKQWU-CQAYDW BQDTISQFU EV JXU UDWBYIX BQDWKQWU, QDT VEBBEMYDW YD JXU DQJKHQBYIJ’I JHQTYJYED YJ SECRYDUI ERIUHLQJYED, UNFUHYCUDJQJYED, IFUSKBQJYED, QDT TESKCUDJQJYED QSJYLYJYUI MU TED’J DEHCQBBO QIIESYQJU MYJX BQDWKQWU.Encrypted: JXYI REEA YI QREKJ JUIJYDW, UNFUHYCUDJYDW, QDT FBQOYDW MYJX BQDWKQWU. YJ YI Q XQDTREEA EV JEEBI QDT JUSXDYGKUI VEH JQAYDW MEHTI QFQHJ QDT FKJJYDW JXUC RQSA JEWUJXUH QWQYD YD MQOI JXQJ Y XEFU QHU CUQDYDWVKB QDT BUWYJYCQJU (EH ULUD YBBUWYJYCQJU)1. JXYI REEA YI QREKJ FUUBYDW RQSA BQOUHI YD IUQHSX EV JXU BQDWKQWU-CQAYDW UDUHWO EV JXU XKCQD IFYHYJ2. YJ YI QREKJ JXU WQFI YD CUQDYDW JXQJ MU KHWUDJBO DUUT JE DEJYSU QDT DQCU JXU FBQSUI MXUHU EKH THUQCI QDT YTUQBI QHU DE BEDWUH VKBVYBBUT RO Q IESYUJO JXQJ XQI RUSECU VQIJ-FQSUT QDT XOFUH-SECCUHSYQBYPUT3. BQDWKQWU YI CUQDJ JE RU Q FBQOVKB, ULUH-IXYVJYDW SHUQJYED RKJ MU XQLU RUUD JQKWXJ, QDT CEIJ EV KI SEDJYDKU JE RUBYULU, JXQJ BQDWKQWU CKIJ ERUTYUDJBO VEBBEM FHUSYIUBO FHUISHYRUT HKBUI JXQJ WELUHD SBUQH IUDJUDSU IJHKSJKHUI, IFUSYVYS MEHT EHTUHI, SEHHUSJ IFUBBYDWI, QDT FHEFUH FHEDKDSYQJYEDI4. YV OEK CQAU Q CYIJQAU EH IJUF EKJ EV REKDTI JXUHU QHU SEKDJBUII, IUBV-QFFEYDJUT BQDWKQWU UNFUHJI MXE MYBB FHECFJBO FKIX OEK RQSA YDJE IQVU JUHHQYD QDT ISEBT OEK VEH OEKH UHHEHI. QDT YD SQIU OEK DUUT HUCYDTYDW, JXUHU QHU XKDTHUTI EV TYSJYEDQHYUI QDT WHQCCQH REEAI JE UDIKHU JXQJ OEK HUCUCRUH JXU “HYWXJ” MQO JE KIU UDWBYIX.";

		System.out.println("Ciphered Text from database");
		// String sentence =" Z IVDVDSVI RJ R TYZCU, REU RJ R PFLEX SLUUZEX ERKLIRCZJK,
		// JGVEUZEX RCC DP KZDV FSJVIMZEX REU KVJKZEX KYV NFICU RIFLEU DV DFMZEX
		// GZVTVJ";
		System.out.println();  // [y][HUCUCRUH][QI]
		System.out.println(sentence);
		String[] words = sentence.split("\\s+");
		int[] wordFrequency = findSingleCharacterWordFrequency(words);
		HashSet<String> uniqueLetter = new HashSet<String>();
		// System.out.println("Single-character word frequencies:");
		for (int i = 0; i < wordFrequency.length; i++) {
			if (words[i].length() == 1) {

				uniqueLetter.add(words[i]);
				 System.out.println(words[i] + ": " + wordFrequency[i]);
			}
		}
		 System.out.println("Unique letter size " + uniqueLetter.size());
		ManualMonoalphabeticSubstitutionCipher manualCipger = new ManualMonoalphabeticSubstitutionCipher();
		// System.out.println("Position One" +
		// uniqueLetter.toString().toUpperCase().charAt(1));
		String Key = generateKey(uniqueLetter.toString().toUpperCase().charAt(1));
		System.out.println();
		System.out.println("Key 1 " + Key);
		System.out.println();
		manualCipger.initializeKeys(Key);
		String descriptedText = manualCipger.decrypt(sentence);
		// System.out.println("descripted text with 1st iteration "+descriptedText );
		String[] splitWords = descriptedText.split("\\s+");

		for (String i : splitWords) {
			// System.out.println("SPlted descrpted " + i);
		}
		int tripleWord[] = checkSecondaryLet(splitWords);
		HashSet<String> tripleletter = new HashSet<String>();
		for (int i = 0; i < tripleWord.length; i++) {
			if (splitWords[i].length() == 3) {

				tripleletter.add(splitWords[i]);
				// System.out.println(splitWords[i] + ": " + tripleWord[i]);
			}
		}
		boolean output = compareWithThreeLettersFromPara(tripleletter);
		if (output == true) {
			System.out.println("Decripted text" + descriptedText);
			System.out.println();
		} else {
			String Key2 = null;
			// System.out.println("false ");
			if (uniqueLetter.size() >= 2) {
				Key2 = generateKey(uniqueLetter.toString().toUpperCase().charAt(4));
			} else {
				Key2 = generateKeyForI(uniqueLetter.toString().toUpperCase().charAt(1));
			}
			// Key = generateKey(uniqueLetter.toString().toUpperCase().charAt(4));
			// System.out.println("Key 2 " + Key2);
			manualCipger.initializeKeys(Key2);
			String descriptedTex = manualCipger.decrypt(sentence);
			System.out.println("Descripted Text using another iteration" + descriptedTex);
		}

	}

	private static String generateKeyForI(char charAt) {

		int temp = (int) charAt;
		// System.out.println("temp" + temp);
		// char a = (char) 73;
		// System.err.println(a);
		Map<Character, Character> storeKey = new HashMap<>();
		int intial = 0;
		char temChar = '\0';
		StringBuilder plaintext = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			int tem = (int) charAt + i;
			// System.out.println("tem " + tem);

			// System.out.println("temChar "+temChar);
			if (tem <= 90) {
				temChar = (char) (charAt + i);
			} else {

				temChar = (char) (65 + intial);
				intial++;

			}
			char value = (char) (65 + i);
			storeKey.put(value, temChar);
			plaintext.append(temChar);
		}

		// System.out.println(plaintext.toString());
		return plaintext.toString();
	}

	private static boolean compareWithThreeLettersFromPara(HashSet<String> tripleletter) {
		// System.out.println("tripple word" + tripleletter);
		String listOfthreeLetters[] = { "AND", "THE", "NOT", "BUT", "ODD", "MUD", "OUR", "ARE", "YOU", "USE" };
		for (String i : tripleletter) {
			for (int j = 0; j < listOfthreeLetters.length; j++) {
				if (i.equalsIgnoreCase(listOfthreeLetters[j]) || i.contains(listOfthreeLetters[j])) {
					// System.out.println("Match found");
					return true;
				}
			}

		}
		return false;

	}

	public static int[] checkSecondaryLet(String[] splitedword) {
		int[] wordFrequency = new int[splitedword.length];
		for (String i : splitedword) {
			// System.out.println("SPlted inside " + i);
		}

		for (int i = 0; i < splitedword.length; i++) {
			// Initialize frequency to 1

			if (splitedword[i].length() == 3) {
				wordFrequency[i]++;
				// Mark repeated word as empty to avoid counting it again
			}

		}

		return wordFrequency;
	}

	public static String generateKey(char charAt) {
		// TODO Auto-generated method stub
		// System.out.println((int)('Z'+1));
		// System.out.println("Char At" + charAt);
		int temp = (int) charAt;
		// System.out.println("temp" + temp);
		char a = (char) 65;
		
		//  ascii A=65
		// System.err.println(a);
		Map<Character, Character> storeKey = new HashMap<>();
		int intial = 0;
		char temChar = '\0';
		StringBuilder plaintext = new StringBuilder();
		for (int i = 0; i < 26; i++) { //Q= 81+0
			int tem = (int) charAt + i;
			// System.out.println("tem " + tem);

			// System.out.println("temChar "+temChar);
			if (tem <= 90) {
				temChar = (char) (charAt + i);
			} else {

				temChar = (char) (65 + intial);
				intial++;

			}
			char value = (char) (65 + i);
			storeKey.put(value, temChar);
			plaintext.append(temChar);
		}

		// System.out.println(plaintext.toString());
		return plaintext.toString();
	}

	public static int[] findSingleCharacterWordFrequency(String[] words) {
		int[] wordFrequency = new int[words.length];

		for (int i = 0; i < words.length; i++) {
			wordFrequency[i] = 1; // Initialize frequency to 1
			for (int j = i + 1; j < words.length; j++) {
				if (words[i].equalsIgnoreCase(words[j]) && words[i].length() == 1) {
					wordFrequency[i]++;
					words[j] = ""; // Mark repeated word as empty to avoid counting it again
				}
			}
		}

		return wordFrequency;
	}

}
