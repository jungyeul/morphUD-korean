import java.io.*;
import java.util.StringTokenizer;
import java.util.Hashtable;

public class Sejong2Universal {

	//public static void main (String[] args) throws IOException {
    public static String convert(String pos) {
        if (pos.contains("+")) pos = multiConvert(pos);
        else pos = uniConvert(pos);
        return  pos;
    }
	public static String multiConvert(String pos) {
		/*
		   String fileName = args[0];
		   BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(new File (fileName))));
		 */
		String str = pos;
		//		str = d.readLine();
		String univ = new String(); univ = "ooOoo";
		//		while (str != null) {
		univ = "ooOoo";
		str = str.trim();
		if (!str.contains("+")) {
			univ = uniConvert(str);
			//System.out.println(str + "\t" + univ);
		}
		else {
			String delim = "\\+";
			String[] entry;
			entry = str.split(delim);

			if (str.contains("ETN+VCP") && entry[entry.length-1].startsWith("J")) univ = "NOUN"; //VCP+ETN+JKB+JX
			else if (str.contains("ETN+VCP")) univ = "VERB";
			else if (str.contains("ETN")) univ = "NOUN";
			else if (str.contains("XSV")) univ = "VERB";
			else if (str.contains("XSA")) univ = "ADJ";
			else if (str.contains("XSN")) univ = "NOUN";
			else if (str.contains("VCP") || str.contains("VCN")) univ = "VERB";
			else if (str.contains("VV") || str.contains("VX")) univ = "VERB";
			else if (str.contains("VA")) univ = "ADJ";
			else if (str.contains("NNP")) univ = "PROPN";
			else if (str.contains("NNG") || str.contains("NNB") || str.contains("NR") || str.contains("XR")) univ = "NOUN";
			else if (str.contains("SN")) univ = "NUM";
			else if (str.contains("NP")) univ = "PRON";
			else if (str.contains("MAJ")) univ = "CONJ";
			else if (str.contains("MAG")) univ = "ADV";
			else if (str.contains("IC")) univ = "INTJ";
			else if (str.contains("Q\"")) univ = "X";
			else if (entry[0].startsWith("S") && entry[entry.length-1].startsWith("J")) univ = "ADP";
			else if (entry[0].startsWith("S") && entry[entry.length-1].startsWith("E")) univ = "PRT";
			else if (str.contains("MM")) univ = "DET";
			else if (str.contains("SH") || str.contains("SL")) univ = "X";
			else {
				String s = entry[0].substring(0, 1);
				boolean flag = true;
				for (int i=0; i< entry.length;i++) {
					String t = entry[i].substring(0, 1);
					if (!t.equals(s)) flag =  false;
				}
				if (flag) {
					if (s.equals("J")) univ = "ADP";
					else if (s.equals("E")) univ = "PRT";
					else if (s.equals("S")) {
						if (str.contains("SW")) univ = "SYM";
						else univ = "PUNCT";
					}
				}
			}
			if (univ.equals("ooOoo")) {
				if (str.contains("NA")) univ = "X";
				else if (str.contains("+Q+")) univ = "X";
				else if (str.contains("J")) univ = "ADP";
				else if (str.contains("EC") || str.contains("EF")) univ = "PRT";
				else univ = "X";
			}
		}
		//			str = d.readLine();
		//		}
		//		d.close();
		return univ;
	}

	static String uniConvert(String sj) {
		String str = sj;
		String univ = new String();

		if (str.equals("NNG") || str.equals("NNB") || str.equals("NR") ||  str.equals("XR"))  univ = "NOUN";
        else if (str.equals("NNP")) univ = "PROPN";
		else if (str.equals("NP")) univ = "PRON";
        else if (str.equals("PRON")) univ = "PRON";
		else if (str.equals("MAG")) univ = "ADV";
		else if (str.equals("MAJ")) univ = "CCONJ";
		else if (str.equals("MM")) univ = "DET";
		else if (str.equals("VA")) univ = "ADJ";
		else if (str.startsWith("V") && !str.equals("VA")) univ = "VERB";
		else if (str.equals("SH") || str.equals("SL")) univ = "X";
		else if (str.equals("SW")) univ = "X";
		else if (str.startsWith("E") || (str.startsWith("X")&&!str.equals("XR"))) univ = "PRT";
		else if (str.startsWith("J")) univ = "ADP";
		else if (str.equals("SN")) univ = "NUM";
		else if (str.equals("NA") || str.equals("NF") || str.equals("NV")) univ = "X";
		else if (str.equals("SF") || (str.equals("SP"))|| str.equals("SS")) univ = "PUNCT";
		else if (str.equals("SE") || str.equals("SO")) univ = "SYM";
		else if (str.equals("IC")) univ = "INTJ";
		else {  
			univ = "X";
		}
		return univ;
	}


	public static void sjmain (String[] args) throws IOException {

		String fileName = args[0];
		BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(new File (fileName))));

		String str = new String();

		str = d.readLine();

		while (str != null) {

			str = str.trim();
			if (str.length()>0) {
				String delim = "\t";

				String[] entry;
				entry = str.split(delim);
				String sejong = entry[4]; 

				if (sejong.contains("/")) {
					String delimPlus = "\\+";
					String[] entryPlus; 
					entryPlus = sejong.split(delimPlus);
					String sejongPOS = new String();
					for (int j=0; j<entryPlus.length; j++) {
						String tooo = entryPlus[j];
						tooo = tooo.substring(tooo.indexOf("/")+1, tooo.length()).trim();
						if (j==0) sejongPOS = tooo;
						else sejongPOS += "+" + tooo;
					}
					sejong = sejongPOS;
				}
				//System.out.println(sejong);
				String univ = convert(sejong); //System.out.println(univ);

				   for (int i=0; i<entry.length; i++) {
				   String tok = entry[i];
				   if (i==3) tok = univ;
				   if (i==4) tok = sejong;
				   if (i==7) {
if (tok.equals("fixed")) tok = "aux";
}
				   if (i<9) System.out.print(tok + "\t");
				   else if (i==9) System.out.println(tok);
				   }
			}
			else System.out.println();

			str = d.readLine();
		}

		//Hashtable<String,Integer> hash = new Hashtable<String,Integer>();
		d.close();
	}

}


