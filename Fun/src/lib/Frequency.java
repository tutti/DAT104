package lib;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Frequency {

	private double[] singleFreq;
	private double[][] doubleFreq;
	private double[][][] tripleFreq;
	private double[][][][] quadFreq;
	
	private Random rnd = new Random();

	private String fileContents;

	private char nextTextChar(Scanner s) {
		s.useDelimiter("");
		char c = '!';
		while (c < 'a' || c > 'z') {
			if (!s.hasNext()) {
				return '!';
			}
			c = s.next().charAt(0);
		}
		return c;
	}

	public Frequency(String filnavn) {
		readFile(filnavn);
		loadSingleFreq();
		loadDoubleFreq();
		loadTripleFreq();
		loadQuadFreq();
	}

	private void readFile(String filnavn) {
		try {
			Scanner s = new Scanner(new File(filnavn));
			fileContents = s.useDelimiter("\\A")
					.next().toLowerCase();
			s.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void loadSingleFreq() {
		singleFreq = new double[26];
		int antall = 0;
		for (int i = 0; i < fileContents.length(); ++i) {
			char c = fileContents.charAt(i);
			if (c >= 'a' && c <= 'z') {
				singleFreq[c - 'a']++;
				antall++;
			}
		}
		for (int i = 0; i < 26; ++i) {
			singleFreq[i] /= antall;
		}
	}

	private void loadDoubleFreq() {
		doubleFreq = new double[26][26];
		int[] antall = new int[26];
		Scanner scanner = new Scanner(fileContents);
		char c1 = nextTextChar(scanner);
		char c2 = nextTextChar(scanner);
		while (c2 != '!') {
			doubleFreq[c1 - 'a'][c2 - 'a']++;
			antall[c1 - 'a']++;
			c1 = c2;
			c2 = nextTextChar(scanner);
		}
		for (int i = 0; i < 26; ++i) {
			for (int j = 0; j < 26; ++j) {
				doubleFreq[i][j] /= antall[i];
			}
		}
	}

	private void loadTripleFreq() {
		tripleFreq = new double[26][26][26];
		int[][] antall = new int[26][26];
		Scanner scanner = new Scanner(fileContents);
		char c1 = nextTextChar(scanner);
		char c2 = nextTextChar(scanner);
		char c3 = nextTextChar(scanner);
		while (c3 != '!') {
			tripleFreq[c1 - 'a'][c2 - 'a'][c3 - 'a']++;
			antall[c1 - 'a'][c2 - 'a']++;
			c1 = c2;
			c2 = c3;
			c3 = nextTextChar(scanner);
		}
		for (int i = 0; i < 26; ++i) {
			for (int j = 0; j < 26; ++j) {
				for (int k = 0; k < 26; ++k) {
					tripleFreq[i][j][k] /= antall[i][j];
				}
			}
		}
	}

	private void loadQuadFreq() {
		quadFreq = new double[26][26][26][26];
		int[][][] antall = new int[26][26][26];
		Scanner scanner = new Scanner(fileContents);
		char c1 = nextTextChar(scanner);
		char c2 = nextTextChar(scanner);
		char c3 = nextTextChar(scanner);
		char c4 = nextTextChar(scanner);
		while (c4 != '!') {
			quadFreq[c1 - 'a'][c2 - 'a'][c3 - 'a'][c4 - 'a']++;
			antall[c1 - 'a'][c2 - 'a'][c3 - 'a']++;
			c1 = c2;
			c2 = c3;
			c3 = c4;
			c4 = nextTextChar(scanner);
		}
		for (int i = 0; i < 26; ++i) {
			for (int j = 0; j < 26; ++j) {
				for (int k = 0; k < 26; ++k) {
					for (int l = 0; l < 26; ++l) {
						quadFreq[i][j][k][l] /= antall[i][j][k];
					}
				}
			}
		}
	}
	
	private int sample(double[] tbl) {
		double val = rnd.nextDouble();
		int pos = 0;
		while (val > tbl[pos] && pos < tbl.length) {
			val -= tbl[pos];
			++pos;
		}
		return pos;
	}
	
	public String singleSample(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			char c = (char)(sample(singleFreq) + 'a');
			sb.append(c);
		}
		return sb.toString();
	}
	
	private void doubleSampleStep(StringBuilder sb) {
		if (sb.length() == 0) {
			char c = (char)(sample(singleFreq) + 'a');
			sb.append(c);
			return;
		}
		char c1 = sb.charAt(sb.length() - 1);
		char c = (char)(sample(doubleFreq[c1 - 'a']) + 'a');
		sb.append(c);
	}
	
	public String doubleSample(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			doubleSampleStep(sb);
		}
		return sb.toString();
	}
	
	private void tripleSampleStep(StringBuilder sb) {
		if (sb.length() < 2) {
			doubleSampleStep(sb);
			return;
		}
		char c1 = sb.charAt(sb.length() - 2);
		char c2 = sb.charAt(sb.length() - 1);
		char c = (char)(sample(tripleFreq[c1 - 'a'][c2 - 'a']) + 'a');
		sb.append(c);
	}
	
	public String tripleSample(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			tripleSampleStep(sb);
		}
		return sb.toString();
	}
	
	private void quadSampleStep(StringBuilder sb) {
		if (sb.length() < 3) {
			tripleSampleStep(sb);
			return;
		}
		char c1 = sb.charAt(sb.length() - 3);
		char c2 = sb.charAt(sb.length() - 2);
		char c3 = sb.charAt(sb.length() - 1);
		char c = (char)(sample(quadFreq[c1 - 'a'][c2 - 'a'][c3 - 'a']) + 'a');
		sb.append(c);
	}
	
	public String quadSample(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			quadSampleStep(sb);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Frequency f = new Frequency("pokemon.txt");
		System.out.println(f.singleSample(100));
		System.out.println(f.doubleSample(100));
		System.out.println(f.tripleSample(100));
		System.out.println(f.quadSample(100));
	}

}
