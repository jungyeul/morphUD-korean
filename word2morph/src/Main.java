// Notes go here
// For output of the models, please change the name to "ko_" + treebank + "-ud-" + "out" + "-morphUD_" + formtype + ".conllu"

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    private static void printIgnorePrompt(Boolean start) {
        if (start) System.out.println("Please ignore the following prompt (for debug uses). ");
        System.out.println("----------------------------------------------------------------------------------------");
    }

	public static void dependencyWordToMorph(int lines) {
    	String gsdKaist = askSingleInput("Please enter the treebank you want to convert (gsd or kaist): \n", new String[] {"gsd", "kaist", "GSD", "KAIST", "Kaist"}).toLowerCase();
		int formType = Integer.parseInt(askSingleInput("Please enter the type of form you'd like to obtain (0 for plain morphUD, and 1 for +morph morphUD: )\n", new String[] {"0", "1"}));
		String type = askSingleInput("Please enter the type of dataset you'd like to convert, from \"train\", \"dev\", and \"test\": \n", new String[] {"train", "dev", "test", "out-word", "out-word-udpipe", "out-merge"});
		String typeOut = new String();
		if (formType == 0) {
			typeOut = type + "-morph";
		} else if (formType == 1) {
			typeOut = type + "-+morph";
		} else {
			typeOut = type + "undefined";
		}
		boolean phrase = true;
        int finalSentNum = 0;
        printIgnorePrompt(true);
		try {
			Scanner scnr = new Scanner(new File("ko_" + gsdKaist + "-ud-" + type + ".conllu"));
			String line = scnr.nextLine(); // first line

			for (int i = 0; i < lines; i++) {
				Sentence newSent = new Sentence(i, new Row[700]);

				while (!line.isEmpty()) {
					String[] splited = line.split("\\s+");

					String[] plugin = new String[200];
					for (int j = 0; j < splited.length; j++) {
						plugin[j] = splited[j];
					}
					Row thisRow = new Row(false, plugin);

					newSent.setNewLine(thisRow);
					// thisRow.printRow();  delete
					line = scnr.nextLine();
				}

				//trainUdpiped[i] = newSent;
				if (gsdKaist.equals("gsd")) {
					Dependency.convertDependencyGSDNewForm(newSent, i, new String("ko_gsd-ud-" + typeOut + ".conllu"), phrase, formType);
				} else if (gsdKaist.equals("kaist")) {
					Dependency.convertDependencyKaistNewForm(newSent, i, new String("ko_kaist-ud-" + typeOut + ".conllu"), phrase, formType);
				}
				line = scnr.nextLine(); // skip blank line
                finalSentNum = i;
			}
			//
			scnr.close();
            System.out.println("Some sentences not converted. ");
		} catch (FileNotFoundException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchElementException e) {
            System.out.println(finalSentNum + " sentences converted.");
        }
        printIgnorePrompt(false);

		System.out.println("word -> morph method completed.");
	}

    private static String askSingleInput(String prompt, String[] allowed) {
        Scanner in = new Scanner(System.in);
        System.out.print(prompt);
        String out = null;
        Boolean pass = false;
        while (!pass) {
            out = in.nextLine().trim().toLowerCase();
            for (int i = 0; i < allowed.length; i++) {
                if (allowed[i].equals(out) || allowed[i].equals("alwaysPass")) pass = true;
                // if (allowed[i].equals("alwaysPass") && out.equals("")) out = "1000000";
            }
            if (!pass) System.out.println("Invalid input, please try again. ");
        }
        in.reset();
        //in.close();

        return out;
    }

    public static void main(String[] args) {

        System.out.println("This script only converts wordUD to morphUD. ");

        // String modes = askSingleInput("1 = word -> morph, 2 = morph -> word, 3 = test the round trip, 4 = merging, 5 = find cycles. \nPlease select the corresponding mode: \n", new String[] {"1", "2", "3", "4", "5"});
        int lines = Integer.parseInt(askSingleInput("Please enter a number that's greater than the number of sentences to be converted: \n", new String[] {"alwaysPass"}));

        dependencyWordToMorph(lines);

        // else if (mode[0].equals("-1")) testScanner();

    }

}
