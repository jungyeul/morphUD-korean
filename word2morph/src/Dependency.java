
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Dependency {

    private static int findRoot(String[] xpos, String uposPhrase, String loc) {
        String[] upos = new String[xpos.length];
        int rootNum = -999;

        int possibleRoot = xpos.length;
        int nonFuncMorph = xpos.length;
        int match = 0;
        for (int i = 0; i < xpos.length; i++) {
            upos[i] = uniConvertGSD(xpos[i], uposPhrase, xpos);

            if (upos[i].equals("CCONJ") || upos[i].equals("DET") || upos[i].equals("AUX")
                    || upos[i].equals("INTJ") || upos[i].equals("PART") || upos[i].equals("PUNCT")
                    || upos[i].equals("SCONJ") || upos[i].equals("SYM") || upos[i].equals("ADP"))
                possibleRoot--;
            if (upos[i].equals("ADP") || upos[i].equals("PART") || upos[i].equals("CCONJ")) nonFuncMorph--;
            if (upos[i].equals(uposPhrase))
                match++;

        }
        if (match > 1) {
            if (uposPhrase.equals("NOUN") || uposPhrase.equals("PROPN") || uposPhrase.equals("NUM")) {
                for (int i = 0; i < xpos.length; i++) {
                    if (upos[i].equals(uposPhrase)) rootNum = i;
                }
                if (rootNum != -999) return rootNum;
            } else if (uposPhrase.equals("VERB")) {
                System.out.println("verb in findroot - check " + loc);
                for (int i = 0; i < xpos.length; i++) {
                    if (upos[i].equals(uposPhrase)) return i;
                }
            } else if (!(uposPhrase.equals("PART") || uposPhrase.equals("CCONJ") || uposPhrase.equals("ADP"))) {
                System.out.println("findRoot Warning: too many roots; not noun/verb at " + loc);
                for (int i = 0; i < xpos.length; i++) {
                    if (upos[i].equals(uposPhrase)) rootNum = i;
                }
                if (rootNum != -999) return rootNum;
            } else {
                System.out.println("findRoot Warning: too many roots; PART/ADP at " + loc);
                for (int i = 0; i < xpos.length; i++) {
                    if (upos[i].equals(uposPhrase)) return i;
                }
            }

            if (rootNum == -999) {
                System.out.println("Error: no root found while match > 1");
                return 0;
            }
            // return -2;
        } else if (match == 1) {
            for (int i = 0; i < xpos.length; i++) {
                if (upos[i].equals(uposPhrase)) rootNum = i;
            }
            if (rootNum != -999) return rootNum;
            System.out.println("Error: no root found while match = 1");
            return 0;
        } else { // no match
            for (int i = 0; i < xpos.length; i++) {
                if (upos[i].equals("VERB")) return i; // once find a verb, return
                if (upos[i].equals("NOUN") || upos[i].equals("PROPN") || upos[i].equals("NUM")) rootNum = i;
            }
            if (rootNum != -999) return rootNum; // prior noun
            if (nonFuncMorph > 0) {
                for (int i = 0; i < xpos.length; i++) {
                    if (!upos[i].equals("ADP") && !upos[i].equals("PART") && !upos[i].equals("CCONJ")) rootNum = i;
                }
                if (rootNum != -999) return rootNum;
                System.out.println("Error: no root found while match < 1");
                return 0;
            } else {
                System.out.println("Warning: NO MATCH!");
                return 0;
            }
        }
        //if (match < 1 && possibleRoot < 1)
        //return -3;
        //if (match < 1 && possibleRoot > 1)
        //return -4;

        return -1; // TODO
    }

    private static int findRootKaist(String[] xpos, String uposPhrase, String loc) {
        String[] upos = new String[xpos.length];
        int rootNum = -999;

        int possibleRoot = xpos.length;
        int nonFuncMorph = xpos.length;
        int match = 0;
        for (int i = 0; i < xpos.length; i++) {
            upos[i] = uniConvert17Kaist(xpos[i]);

            if (upos[i].equals("CCONJ") || upos[i].equals("DET") || upos[i].equals("AUX")
                    || upos[i].equals("INTJ") || upos[i].equals("PART") || upos[i].equals("PUNCT")
                    || upos[i].equals("SCONJ") || upos[i].equals("SYM") || upos[i].equals("ADP"))
                possibleRoot--;
            if (upos[i].equals("ADP") || upos[i].equals("PART") || upos[i].equals("CCONJ") || upos[i].equals("SCONJ"))
                nonFuncMorph--;
            if (upos[i].equals(uposPhrase))
                match++;

        }
        if (match > 1) {
            if (uposPhrase.equals("NOUN") || uposPhrase.equals("PROPN") || uposPhrase.equals("NUM")) {
                for (int i = 0; i < xpos.length; i++) {
                    if (upos[i].equals(uposPhrase)) rootNum = i;
                }
                if (rootNum != -999) return rootNum;
            } else if (uposPhrase.equals("VERB")) {
                System.out.println("verb in findroot - check " + loc);
                for (int i = 0; i < xpos.length; i++) {
                    if (upos[i].equals(uposPhrase)) return i;
                }
            } else if (!(uposPhrase.equals("PART") || uposPhrase.equals("CCONJ") || uposPhrase.equals("SCONJ") || uposPhrase.equals("ADP"))) {
                System.out.println("findRoot Warning: too many roots; not noun/verb at " + loc);
                for (int i = 0; i < xpos.length; i++) {
                    if (upos[i].equals(uposPhrase)) rootNum = i;
                }
                if (rootNum != -999) return rootNum;
            } else {
                System.out.println("findRoot Warning: too many roots; PART/ADP at " + loc);
                for (int i = 0; i < xpos.length; i++) {
                    if (upos[i].equals(uposPhrase)) return i;
                }
            }
            if (rootNum == -999) {
                System.out.println("Error: no root found while match > 1");
                return 0;
            }
            // return -2;
        } else if (match == 1) {
            for (int i = 0; i < xpos.length; i++) {
                if (upos[i].equals(uposPhrase)) rootNum = i;
            }
            if (rootNum != -999) return rootNum;
            System.out.println("Error: no root found while match = 1");
            return 0;
        } else { // no match
            for (int i = 0; i < xpos.length; i++) {
                if (upos[i].equals("VERB")) return i; // once find a verb, return
                if (upos[i].equals("NOUN") || upos[i].equals("PROPN") || upos[i].equals("NUM")) rootNum = i;
            }
            if (rootNum != -999) return rootNum; // prior noun
            if (nonFuncMorph > 0) {
                for (int i = 0; i < xpos.length; i++) {
                    if (!upos[i].equals("ADP") && !upos[i].equals("PART") && !upos[i].equals("CCONJ") && !upos[i].equals("SCONJ"))
                        rootNum = i;
                }
                if (rootNum != -999) return rootNum;
                System.out.println("Error: no root found while match < 1");
                return 0;
            } else {
                return 0;
            }
        }
        //if (match < 1 && possibleRoot < 1)
        //return -3;
        //if (match < 1 && possibleRoot > 1)
        //return -4;

        return -1; // TODO
    }

    private static String canonical(String str, String xpos) {
        // TODO variety of forms
        String output;
        if (str.equals("아") && xpos.equals("EC")) {
            output = "어";
        } else if (str.equals("았") && xpos.equals("EP")) {
            output = "었";
        } else if (str.equals("ㄴ") && xpos.equals("ETM")) {
            output = "은";
        } else if (str.equals("ㄹ") && xpos.equals("ETM")) {
            output = "을";
        } else if (str.equals("ㄹ지") && xpos.equals("EC")) {
            output = "을지";
        } else if (str.equals("ㄹ지라도") && xpos.equals("ECtest")) {
            output = "을지라도"; // extra; TODO do I need to segment this?
        } else if (str.equals("아서") && xpos.equals("EC")) {
            output = "어서";
        } else if (str.equals("아야") && xpos.equals("EC")) {
            output = "어야";
        } else if (str.equals("면서") && xpos.equals("EC")) {
            output = "으면서";
        } else if (str.equals("ㄴ다") && xpos.equals("EF")) {
            output = "는다";
        } else if (str.equals("ㄴ다고") && xpos.equals("EC")) {
            output = "은다고";
        } else if (str.equals("와") && xpos.equals("JC")) {
            output = "과";
        } else if (str.equals("나") && xpos.equals("JC")) {
            output = "이나";
        } else if (str.equals("와") && xpos.equals("JKB")) {
            output = "과";
        } else if (str.equals("로") && xpos.equals("JKB")) {
            output = "으로";
        } else if (str.equals("를") && xpos.equals("JKO")) {
            output = "을";
        } else if (str.equals("가") && xpos.equals("JKS")) {
            output = "이";
        } else if (str.equals("는") && xpos.equals("JX")) {
            output = "은";
        } else if (str.equals("ㄴ") && xpos.equals("JX")) {
            output = "은";
        } else {
            output = str;
        }
        return output;
    }

    private static String canonicalKaist(String str, String xpos) {
        // TODO variety of forms
        xpos = xpos.toUpperCase();
        String output;
        if (str.equals("아") && xpos.startsWith("EC")) {
            output = "어";
        } else if (str.equals("았") && xpos.equals("EP")) {
            output = "었";
        } else if (str.equals("ㄴ") && xpos.equals("ETM")) {
            output = "은";
        } else if (str.equals("ㄹ") && xpos.equals("ETM")) {
            output = "을";
        } else if (str.equals("ㄹ지") && xpos.startsWith("EC")) {
            output = "을지";
        } else if (str.equals("ㄹ지라도") && xpos.equals("ECtest")) {
            output = "을지라도"; // extra; TODO do I need to segment this?
        } else if (str.equals("아서") && xpos.startsWith("EC")) {
            output = "어서";
        } else if (str.equals("아야") && xpos.startsWith("EC")) {
            output = "어야";
        } else if (str.equals("면서") && xpos.startsWith("EC")) {
            output = "으면서";
        } else if (str.equals("ㄴ다") && xpos.equals("EF")) {
            output = "는다";
        } else if (str.equals("ㄴ다고") && xpos.startsWith("EC")) {
            output = "은다고";
        } else if (str.equals("와") && xpos.equals("JCJ")) {
            output = "과";
        } else if (str.equals("나") && xpos.equals("JCJ")) {
            output = "이나";

        } else if (str.equals("와") && xpos.equals("JCT")) {
            output = "과";
        } else if (str.equals("로") && xpos.equals("JCA")) {
            output = "으로";
        } else if (str.equals("를") && xpos.equals("JCO")) {
            output = "을";
        } else if (str.equals("가") && xpos.equals("JCS")) {
            output = "이";
        } else if (str.equals("는") && xpos.equals("JXT")) {
            output = "은";
        } else if (str.equals("ㄴ") && xpos.equals("JXT")) {
            output = "은";
        } else {
            output = str;
        }
        return output;
    }

    private static void printEmptyLine(File file) {
        try {
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write("\n");
            bufferWritter.close();
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void printLine(String str, File file) {
        try {
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(str + "\n");
            // bufferWritter.write("\n");
            bufferWritter.close();
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static String uniConvert17Kaist(String sj) {
        String str = sj.toUpperCase();
        String univ = new String();

        if (str.equals("PAD") || str.equals("PAA"))
            univ = "ADJ";
        else if (str.equals("MAG") || str.equals("MAJ") || str.equals("MAD"))
            univ = "ADV";
        else if (str.equals("II"))
            univ = "INTJ";
        else if (str.equals("NCPA") || str.equals("NCPS") || str.equals("NCN") || str.equals("NCR"))
            univ = "NOUN";
        else if (str.equals("NQPA") || str.equals("NQPB") || str.equals("NQPC") || str.equals("NQQ") || str.equals("NQ"))
            univ = "PROPN";
        else if (str.equals("PVD") || str.equals("PVG"))
            univ = "VERB";
        else if (str.equals("NBN") || str.equals("NBS") || str.equals("NBU") || str.equals("JCS") || str.equals("JCC") || str.equals("JCM") || str.equals("JCO") || str.equals("JCA") || str.equals("JCV") || str.equals("JCR") || str.equals("JXC") || str.equals("JXF") || str.equals("JGT") || str.equals("JP") || str.equals("JXT") || str.equals("JCT"))
            univ = "ADP";
        else if (str.equals("PX") || str.equals("EP"))
            univ = "AUX";
        else if (str.equals("JCJ") || str.equals("ECC"))
            univ = "CCONJ";
        else if (str.equals("MMD") || str.equals("MMA"))
            univ = "DET";
        else if (str.equals("NNC") || str.equals("NNO"))
            univ = "NUM";
        else if (str.equals("EF") || str.equals("ECX") || str.equals("ETN") || str.equals("ETM") || str.equals("XP") || str.startsWith("XS"))
            univ = "PART";
        else if (str.equals("NPP") || str.equals("NPD"))
            univ = "PRON";
        else if (str.equals("ECS"))
            univ = "SCONJ";
        else if (str.equals("SF") || str.equals("SE") || str.equals("SP") || str.equals("SD") || str.equals("SL") || str.equals("SR"))
            univ = "PUNCT";
        else if (str.equals("SY") || str.equals("SU"))
            univ = "SYM";
        else if (str.equals("F")) { // diff from paper
            univ = "X";
        } else {
            univ = "Error!!!!";
        }
        //univ = univ.toLowerCase();
        return univ;
    }

    private static String uniConvertGSD(String sj, String upos, String[] allsj) {
        String str = sj;
        String univ = new String();

        if (str.equals("NNG") || str.equals("XR"))
            univ = "NOUN";
        else if (str.equals("NNP")) {
            if (upos.equals("PROPN")) {
                univ = "PROPN";
            } else if (upos.equals("NOUN")) {
                univ = "NOUN";
            } else {
                // System.out.println("uniConvert NNP check; will assign PROPN anyway");
                univ = "PROPN"; // NOUN?
            }
        } else if (str.equals("NNB")) {
            if (upos.equals("NOUN")) {
                univ = "NOUN";
            } else if (upos.equals("ADP")) {
                univ = "ADP";
            } else {
                // System.out.println("uniConvert NNB check; will assign ADP anyway");
                univ = "ADP";
            }
        } else if (str.equals("NR")) {
            if (upos.equals("NOUN")) {
                univ = "NOUN";
            } else if (upos.equals("NUM")) {
                univ = "NUM";
            } else {
                // System.out.println("uniConvert NR check; will assign NUM anyway");
                univ = "NUM";
            }
        } else if (str.equals("NP"))
            univ = "PRON";
        else if (str.equals("PRON"))
            univ = "PRON";
        else if (str.equals("MAG"))
            univ = "ADV";
        else if (str.equals("MAJ")) {
            if (upos.equals("ADV")) {
                univ = "ADV";
            } else if (upos.equals("CCONJ")) {
                univ = "CCONJ";
            } else {
                // System.out.println("uniConvert MAJ check; will assign ADV anyway");
                univ = "ADV";
            }
        } else if (str.equals("MM"))
            univ = "DET";
        else if (str.equals("VA"))
            univ = "ADJ";
        else if (str.equals("VV"))
            univ = "VERB";
        else if (str.startsWith("V") && !str.equals("VA") && !str.equals("VV") && !str.equals("VX")) {
            /*
             * if (upos.equals("VERB")) { univ = "VERB"; } else
             */
            if (upos.equals("AUX")) {
                univ = "AUX";
            } else {
                // System.out.println("uniConvert V- check; will assign AUX anyway");
                univ = "AUX";
            }
        } else if (str.equals("VX")) {
            boolean hasVV = false;
            for (int i = 0; i < allsj.length; i++) {
                if (allsj[i].equals("VV")) hasVV = true;
            }
            if (upos.equals("AUX")) {
                univ = "AUX";
            } else if (hasVV) {
                univ = "AUX";
            } else {
                // System.out.println("uniConvert SH/SL check; will assign PROPN anyway");
                univ = "VERB";
            }
        } else if (str.equals("SH") || str.equals("SL")) {
            if (upos.equals("X")) {
                univ = "X";
            } else if (upos.equals("PROPN")) {
                univ = "PROPN";
            } else {
                // System.out.println("uniConvert SH/SL check; will assign PROPN anyway");
                univ = "PROPN";
            }
        } else if (str.equals("SW")) {
            if (upos.equals("PUNCT")) {
                univ = "PUNCT";
            } else {
                univ = "SYM";
            }
        } // TODO some are PUNCT in GSD
        else if ((str.startsWith("E") && !str.equals("EP") && !str.equals("EC"))
                || (str.startsWith("X") && !str.equals("XR")))
            univ = "PART";
        else if (str.startsWith("EP")) {
            if (upos.equals("PART")) {
                univ = "PART";
            } else if (upos.equals("AUX")) {
                univ = "AUX";
            } else {
                // System.out.println("uniConvert EP check; will assign AUX anyway");
                univ = "AUX";
            }
        } else if (str.startsWith("EC")) {
            if (upos.equals("PART")) {
                univ = "PART";
            } else if (upos.equals("CCONJ")) {
                univ = "CCONJ";
            } else if (upos.equals("SCONJ")) {
                univ = "SCONJ";
            } else {
                // System.out.println("uniConvert EC check; will assign CCONJ anyway");
                univ = "CCONJ";
            }
        } else if (str.startsWith("J") && !str.equals("JC"))
            univ = "ADP";
        else if (str.equals("JC")) {
            if (upos.equals("ADP")) {
                univ = "ADP";
            } else if (upos.equals("CCONJ")) {
                univ = "CCONJ";
            } else {
                // System.out.println("uniConvert JC check; will assign CCONJ anyway");
                univ = "CCONJ";
            }
        } else if (str.equals("SN"))
            univ = "NUM";
        else if (str.equals("NA") || str.equals("NF") || str.equals("NV"))
            univ = "X";
        else if (str.equals("SF") || (str.equals("SP")) || str.equals("SS"))
            univ = "PUNCT";
        else if (str.equals("SE") || str.equals("SO")) {
            if (upos.equals("SYM")) {
                univ = "SYM";
            } else if (upos.equals("PUNCT")) {
                univ = "PUNCT";
            } else {
                // System.out.println("uniConvert SE/SO check; will assign PUNCT anyway");
                univ = "PUNCT";
            }
        } else if (str.equals("IC"))
            univ = "INTJ";
        else {
            univ = "X";
        }
        return univ;
    }

    private static String[] indexMatch(Sentence sentence) {
        int index = 1;
        String[] match = new String[sentence.getSize() + 1];
        for (int i = 0; i < sentence.getSize(); i++) {
            Row thisRow = sentence.getNthRow(i);
            if (thisRow.isInUse()) {
                String[] splitWords = thisRow.getContent()[2].split("\\+");
                String[] splitXpos = thisRow.getContent()[4].split("\\+");
                if (splitXpos.length != splitWords.length) {
                    match[i] = Integer.toString(index);
                    index++;
                } else if (splitXpos.length == 1) {
                    match[i] = Integer.toString(index);
                    index++;
                } else if (splitXpos.length < 1) {
                    System.out.println("indexMatch error.");
                } else {
                    int range = splitXpos.length;
                    match[i] = Integer.toString(index) + "-" + Integer.toString(index + range - 1);
                    index += range;
                }
            }
            // System.out.println(i + " " + match[i]);
        }
        return match;
    }

    private static String deprel(String xpos, String rootXpos, String upos, String rootUpos) {
        String output;
        if (rootUpos.equals("NOUN") || rootUpos.equals("PROPN")) {
            if (upos.equals("NOUN") || upos.equals("PROPN")) {
                output = "compound";
            } else if (upos.equals("ADP")) {
                output = "case";
            } else {
                output = "aux";
            }
        } else if (rootUpos.equals("VERB") || rootUpos.equals("ADJ") || rootUpos.equals("AUX")) {
            output = "aux";
        } else {
            if (upos.equals("ADP")) {
                output = "case";
            } else {
                output = "aux";
            }
        }
        return output;
    }

    public static void convertDependencyGSDNewForm(Sentence sentence, int mult, String filename, boolean phrase, int formType) {
        File file = new File(filename);
        String spacetab = "\t";
        int index = 1;
        String[] indexMatch = indexMatch(sentence);
        for (int i = 0; i < sentence.getSize(); i++) {
            Row thisRow = sentence.getNthRow(i);
            if (thisRow.isInUse()) {
                String depNumOrig = thisRow.getContent()[6]; // for ref
                String depNum = indexMatch[Integer.parseInt(depNumOrig) + 1]; // TODO
                if (depNumOrig.equals("0")) depNum = "0";
                if (depNum.contains("-")) {
                    Row depRow = sentence.getNthRow(Integer.parseInt(depNumOrig) + 1);
                    String[] splitIndex = depNum.split("-");
                    String firstSplit = splitIndex[0];
                    String[] depSplitXpos = depRow.getContent()[4].split("\\+");
                    String depLocation = "sentence " + (sentence.getSentID() + 1) + " , row " + depRow.getContent()[0];
                    int rootNum = findRoot(depSplitXpos, thisRow.getContent()[3], depLocation);

                    depNum = Integer.toString(Integer.parseInt(firstSplit) + rootNum);
                }
                String location = "sentence " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0];
                String words = thisRow.getContent()[2];
                String[] splitWords = words.split("\\+");
                /*
                 * if (words.contains("++")) { System.out.println("Error: contains ++"); }
                 */
                String[] splitXpos = thisRow.getContent()[4].split("\\+");
                if (splitWords.length != splitXpos.length) { // + case
                    if (thisRow.getContent()[4].equals("+SW")) {
                        String canonicalRow3 = canonical(thisRow.getContent()[2], thisRow.getContent()[4]);
                        String output = Integer.toString(index) + spacetab + thisRow.getContent()[1] + spacetab + thisRow.getContent()[2] + spacetab + thisRow.getContent()[3] + spacetab + "SW" + spacetab + "_" + spacetab + depNum + spacetab + thisRow.getContent()[7] + spacetab + "_" + spacetab + thisRow.getContent()[9]; // + spacetab + (i - 1) + "__" + indexMatch[i];
                        printLine(output, file);
                        index++;
                    } else {
                        System.out.println("Error: mismatch: sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0] + ", not +SW");
                    }

                } else if (splitXpos.length == 1) { // only 1 morph
                    String output;
                    String canonicalRow3 = canonical(thisRow.getContent()[2], thisRow.getContent()[4]);
                    if (canonicalRow3.contains("ㄴ") || canonicalRow3.contains("ㄹ"))
                        System.out.println("canonical contains ㄴ/ㄹ: sent " + (sentence.getSentID() + 1)
                                + " , row " + thisRow.getContent()[0]);
                    if (!canonicalRow3.equals(thisRow.getContent()[2]))
                        System.out.println("one-word diff canonical: sent " + (sentence.getSentID() + 1)
                                + " , row " + thisRow.getContent()[0]);
                    if (!thisRow.getContent()[1].equals(thisRow.getContent()[2])) {
                        System.out.println("Warning: one-word 2nd 3rd diff: sent " + (sentence.getSentID() + 1)
                                + " , row " + thisRow.getContent()[0]);
                        output = Integer.toString(index) + spacetab + thisRow.getContent()[1] + spacetab
                                + thisRow.getContent()[2] + spacetab + thisRow.getContent()[3] + spacetab
                                + thisRow.getContent()[4] + spacetab + "_" + spacetab + depNum + spacetab + thisRow.getContent()[7] + spacetab + "_" + spacetab + thisRow.getContent()[9]; // + spacetab + (i - 1) + "__" + indexMatch[i];
                    } else {
                        output = Integer.toString(index) + spacetab + thisRow.getContent()[1] + spacetab
                                + thisRow.getContent()[2] + spacetab + thisRow.getContent()[3] + spacetab
                                + thisRow.getContent()[4] + spacetab + "_" + spacetab + depNum + spacetab + thisRow.getContent()[7] + spacetab + "_" + spacetab + thisRow.getContent()[9]; // + spacetab + (i - 1) + "__" + indexMatch[i];
                        // TODO canonicalRow3 may be used to replace thisRow.getContent()[2] in the future
                    }
                    printLine(output, file);
                    index++;
                } else { // 2+ morph
                    String phraseIndex = Integer.toString(index) + "-" + Integer.toString(index + splitXpos.length - 1);
                    String phraseOutput = phraseIndex + spacetab + thisRow.getContent()[1] + spacetab + "_" + spacetab + "_" + spacetab + "_" + spacetab + "_" + spacetab + "_" + spacetab + "_" + spacetab + "_" + spacetab + "_";// + spacetab + thisRow.getContent()[3] + spacetab + thisRow.getContent()[0] + spacetab + thisRow.getContent()[6] + spacetab + thisRow.getContent()[7];
                    if (phrase) printLine(phraseOutput, file);

                    int rootNum = findRoot(splitXpos, thisRow.getContent()[3], location);
                    //if (test == -1) System.out.println("findRoot Warning: no root found at sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0]);
                    //if (test == -2) System.out.println("findRoot Warning: too many root found at sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0]);
                    if (rootNum == -1)
                        System.out.println("findRoot Error!! null root at sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0]);
                    //if (test == -3) System.out.println("findRoot Warning: no root found at sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0]);
                    //if (test == -4) System.out.println("findRoot Warning: check root selection at sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0]);

                    int rootIndex = index + rootNum;

                    for (int j = 0; j < splitXpos.length; j++) {

                        String rootMark = "_";
                        String depNumRoot;
                        String depRel;
                        String upos = uniConvertGSD(splitXpos[j], thisRow.getContent()[3], splitXpos);
                        String uposRoot = uniConvertGSD(splitXpos[rootNum], thisRow.getContent()[3], splitXpos);
                        if (j == rootNum) {
                            rootMark = "word_root";
                            depNumRoot = depNum;
                            depRel = thisRow.getContent()[7];
                        } else {
                            rootMark = "_";
                            depNumRoot = Integer.toString(rootIndex);
                            depRel = deprel(splitXpos[j], splitXpos[rootNum], upos, uposRoot); //TODO
                        }

                        String canonicalRow3 = canonical(splitWords[j], splitXpos[j]);
                        if (canonicalRow3.contains("ㄴ") || canonicalRow3.contains("ㄹ"))
                            System.out.println("canonical contains ㄴ/ㄹ: sent " + (sentence.getSentID() + 1)
                                    + " , row " + thisRow.getContent()[0]);

                        // new format starts here
                        String morphFormatted = splitWords[j];
                        if (formType == 1) {
                            if (j != 0) morphFormatted = "+" + morphFormatted;
                        } else if (formType == 2) {
                            if (j == 0) morphFormatted = morphFormatted + "+";
                            else if (j == splitXpos.length - 1) morphFormatted = "+" + morphFormatted;
                            else morphFormatted = "+" + morphFormatted + "+";
                        }

                        String output = Integer.toString(index) + spacetab + morphFormatted + spacetab + canonicalRow3 + spacetab + upos + spacetab + splitXpos[j] + spacetab + "_" + spacetab + depNumRoot + spacetab + depRel + spacetab + "_" + spacetab + "_"; // + spacetab + (i - 1) + "__" + indexMatch[i] + spacetab + rootMark ;
                        if (j == splitXpos.length - 1)
                            output = Integer.toString(index) + spacetab + morphFormatted + spacetab + canonicalRow3 + spacetab + upos + spacetab + splitXpos[j] + spacetab + "_" + spacetab + depNumRoot + spacetab + depRel + spacetab + "_" + spacetab + thisRow.getContent()[9];
                        printLine(output, file);
                        index++;
                    }
                }

            } else {
                thisRow.printRowWithName2(filename);
                printEmptyLine(file);
            }
        }
        printEmptyLine(file);
    }

    public static void convertDependencyKaistNewForm(Sentence sentence, int mult, String filename, boolean phrase, int formType) {
        File file = new File(filename);
        String spacetab = "\t";
        int index = 1;
        String[] indexMatch = indexMatch(sentence);
        for (int i = 0; i < sentence.getSize(); i++) {
            Row thisRow = sentence.getNthRow(i);
            if (thisRow.isInUse()) {
                String depNumOrig = thisRow.getContent()[6]; // for ref
                String depNum = indexMatch[Integer.parseInt(depNumOrig) + 1]; // TODO
                if (depNumOrig.equals("0")) depNum = "0";
                if (depNum.contains("-")) {
                    Row depRow = sentence.getNthRow(Integer.parseInt(depNumOrig) + 1);
                    String[] splitIndex = depNum.split("-");
                    String firstSplit = splitIndex[0];
                    String[] depSplitXpos = depRow.getContent()[4].split("\\+");
                    String depLocation = "sentence " + (sentence.getSentID() + 1) + " , row " + depRow.getContent()[0];
                    int rootNum = findRootKaist(depSplitXpos, thisRow.getContent()[3], depLocation);

                    depNum = Integer.toString(Integer.parseInt(firstSplit) + rootNum);
                }
                String location = "sentence " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0];
                String words = thisRow.getContent()[2];
                String[] splitWords = words.split("\\+");
                /*
                 * if (words.contains("++")) { System.out.println("Error: contains ++"); }
                 */
                String[] splitXpos = thisRow.getContent()[4].split("\\+");
                if (splitWords.length != splitXpos.length) { // + case
                    if (thisRow.getContent()[4].equals("+SW")) {
                        String canonicalRow3 = canonicalKaist(thisRow.getContent()[2], thisRow.getContent()[4]);
                        String output = Integer.toString(index) + spacetab + thisRow.getContent()[1] + spacetab + thisRow.getContent()[2] + spacetab + thisRow.getContent()[3] + spacetab + "SW" + spacetab + "_" + spacetab + depNum + spacetab + thisRow.getContent()[7] + spacetab + "_" + spacetab + thisRow.getContent()[9]; // + spacetab + (i - 1) + "__" + indexMatch[i];
                        printLine(output, file);
                        index++;
                    } else {
                        System.out.println("Error: mismatch: sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0] + ", not +SW");
                    }

                } else if (splitXpos.length == 1) { // only 1 morph
                    String output;
                    String canonicalRow3 = canonicalKaist(thisRow.getContent()[2], thisRow.getContent()[4]);
                    if (canonicalRow3.contains("ㄴ") || canonicalRow3.contains("ㄹ"))
                        System.out.println("canonical contains ㄴ/ㄹ: sent " + (sentence.getSentID() + 1)
                                + " , row " + thisRow.getContent()[0]);
                    if (!canonicalRow3.equals(thisRow.getContent()[2]))
                        System.out.println("one-word diff canonical: sent " + (sentence.getSentID() + 1)
                                + " , row " + thisRow.getContent()[0]);
                    if (!thisRow.getContent()[1].equals(thisRow.getContent()[2])) {
                        System.out.println("Warning: one-word 2nd 3rd diff: sent " + (sentence.getSentID() + 1)
                                + " , row " + thisRow.getContent()[0]);
                        output = Integer.toString(index) + spacetab + thisRow.getContent()[1] + spacetab
                                + thisRow.getContent()[2] + spacetab + thisRow.getContent()[3] + spacetab
                                + thisRow.getContent()[4] + spacetab + "_" + spacetab + depNum + spacetab + thisRow.getContent()[7] + spacetab + "_" + spacetab + thisRow.getContent()[9]; // + spacetab + (i - 1) + "__" + indexMatch[i];
                    } else {
                        output = Integer.toString(index) + spacetab + thisRow.getContent()[1] + spacetab
                                + thisRow.getContent()[2] + spacetab + thisRow.getContent()[3] + spacetab
                                + thisRow.getContent()[4] + spacetab + "_" + spacetab + depNum + spacetab + thisRow.getContent()[7] + spacetab + "_" + spacetab + thisRow.getContent()[9]; // + spacetab + (i - 1) + "__" + indexMatch[i];
                        // TODO canonicalRow3 may be used to replace thisRow.getContent()[2] in the future
                    }
                    printLine(output, file);
                    index++;
                } else { // 2+ morph
                    String phraseIndex = Integer.toString(index) + "-" + Integer.toString(index + splitXpos.length - 1);
                    String phraseOutput = phraseIndex + spacetab + thisRow.getContent()[1] + spacetab + "_" + spacetab + "_" + spacetab + "_" + spacetab + "_" + spacetab + "_" + spacetab + "_" + spacetab + "_" + spacetab + "_";// + spacetab + thisRow.getContent()[3] + spacetab + thisRow.getContent()[0] + spacetab + thisRow.getContent()[6] + spacetab + thisRow.getContent()[7];
                    if (phrase) printLine(phraseOutput, file);

                    int rootNum = findRootKaist(splitXpos, thisRow.getContent()[3], location);
                    //if (test == -1) System.out.println("findRoot Warning: no root found at sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0]);
                    //if (test == -2) System.out.println("findRoot Warning: too many root found at sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0]);
                    if (rootNum == -1)
                        System.out.println("findRoot Error!! null root at sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0]);
                    //if (test == -3) System.out.println("findRoot Warning: no root found at sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0]);
                    //if (test == -4) System.out.println("findRoot Warning: check root selection at sent " + (sentence.getSentID() + 1) + " , row " + thisRow.getContent()[0]);

                    int rootIndex = index + rootNum;

                    for (int j = 0; j < splitXpos.length; j++) {

                        String rootMark = "_";
                        String depNumRoot;
                        String depRel;
                        String upos = uniConvert17Kaist(splitXpos[j]);
                        String uposRoot = uniConvert17Kaist(splitXpos[rootNum]);
                        if (j == rootNum) {
                            rootMark = "word_root";
                            depNumRoot = depNum;
                            depRel = thisRow.getContent()[7];
                        } else {
                            rootMark = "_";
                            depNumRoot = Integer.toString(rootIndex);
                            depRel = deprel(splitXpos[j], splitXpos[rootNum], upos, uposRoot); //TODO
                        }

                        String canonicalRow3 = canonicalKaist(splitWords[j], splitXpos[j]);
                        if (canonicalRow3.contains("ㄴ") || canonicalRow3.contains("ㄹ"))
                            System.out.println("canonical contains ㄴ/ㄹ: sent " + (sentence.getSentID() + 1)
                                    + " , row " + thisRow.getContent()[0]);
                        // new format starts here
                        String morphFormatted = splitWords[j];
                        if (formType == 1) {
                            if (j != 0) morphFormatted = "+" + morphFormatted;
                        } else if (formType == 2) {
                            if (j == 0) morphFormatted = morphFormatted + "+";
                            else if (j == splitXpos.length - 1) morphFormatted = "+" + morphFormatted;
                            else morphFormatted = "+" + morphFormatted + "+";
                        }

                        String output = Integer.toString(index) + spacetab + morphFormatted + spacetab + canonicalRow3 + spacetab + upos + spacetab + splitXpos[j] + spacetab + "_" + spacetab + depNumRoot + spacetab + depRel + spacetab + "_" + spacetab + "_"; // + spacetab + (i - 1) + "__" + indexMatch[i] + spacetab + rootMark ;
                        if (j == splitXpos.length - 1)
                            output = Integer.toString(index) + spacetab + morphFormatted + spacetab + canonicalRow3 + spacetab + upos + spacetab + splitXpos[j] + spacetab + "_" + spacetab + depNumRoot + spacetab + depRel + spacetab + "_" + spacetab + thisRow.getContent()[9];
                        printLine(output, file);
                        index++;
                    }
                }

            } else {
                thisRow.printRowWithName2(filename);
                printEmptyLine(file);
            }
        }
        printEmptyLine(file);
    }


}
