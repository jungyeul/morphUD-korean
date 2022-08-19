
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Row {

    static final String punctuations = "!\"#$%&'()*+,-./:;<=>?@[]^_`{|}~";
    static final String punctuationsEnd = "!.?．";
    int num;
    boolean isNER;
    boolean isInUse;
    boolean isPhrase;
    boolean isPunct;
    String word;
    String[] content;
    String type;
    String POS;
    String NER;
    int match = -99;
    boolean isEndPunct;

    public Row(boolean isNER, String[] content) {

        this.isNER = isNER;
        if (isNER) {
            this.content = content;
            this.isInUse = true;
            try {
                this.num = Integer.parseInt(content[0]);
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }
            this.isPhrase = false;
            this.isPunct = (punctuations.contains(content[1]));
            this.isEndPunct = (punctuationsEnd.contains(content[1]));
            this.word = content[1];
            this.NER = content[2];
            this.type = null;
            this.POS = null;
        } else {
            this.content = content;
            this.isInUse = (!content[0].contains("#"));
            this.isPhrase = content[0].contains("-");

            if (isInUse && (!isPhrase)) {
                this.word = content[1];
                this.type = content[3];
                this.POS = content[4];
                try {
                    this.num = Integer.parseInt(content[0]);
                } catch (NumberFormatException e) {
                    // Auto-generated catch block
                    // e.printStackTrace();
                    // TODO System.out.println("NumberFormatException at Row-integer");
                }
                this.isPunct = (content[3].equals("PUNCT"));
                this.isEndPunct = (punctuationsEnd.contains(content[1]));
            } else if (isPhrase) {
                this.word = content[1];
                this.type = content[3];
                this.POS = null;
                this.num = -10;
                this.isPunct = false;
            } else {
                this.word = null;
                this.type = content[1];
                this.POS = null;
                this.num = -99;
                this.isPunct = false;
            }
            this.NER = null;
        }

    }

    public void printRow() {
        boolean first = true;
        String output = "";
        for (int i = 0; i < content.length; i++) {
            if (content[i] != null) {
                if (first) {
                    output = output + content[i];
                    first = false;
                } else if (isInUse) {
                    output = output + "\t" + content[i];
                } else {
                    output = output + " " + content[i];
                }
            }
        }
        File file = new File("train_data_annotated.txt");
        try {
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(output);
            bufferWritter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void printRowWithName2(String name) {
        boolean first = true;
        String output = "";
        for (int i = 0; i < content.length; i++) {
            if (content[i] != null) {
                if (first) {
                    output = output + content[i];
                    first = false;
                } else if (isInUse) {
                    output = output + "\t" + content[i];
                } else {
                    output = output + " " + content[i];
                }
            }
        }
        File file = new File(name);
        try {
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(output);
            bufferWritter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public void setInUse(boolean isInUse) {
        this.isInUse = isInUse;
    }

    public boolean isPhrase() {
        return isPhrase;
    }

    public void setPhrase(boolean isPhrase) {
        this.isPhrase = isPhrase;
    }

    public boolean isPunct() {
        return isPunct;
    }

    public void setPunct(boolean isPunct) {
        this.isPunct = isPunct;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPOS() {
        return POS;
    }

    public void setPOS(String pOS) {
        POS = pOS;
    }

    public void writeCol5() {
        if (isInUse) {
            if (!isPhrase && content[10].equals("_")) {
                content[10] = "O";
            }
            content[5] = content[10];
            content[10] = null;
            // this.NER = NER;
            //System.out.println("dne");
        }
    }

    public void writeCol4() {
        if (isInUse) {
            //if (!isPhrase && content[10].equals("_")) {content[10] = "O";}
            content[4] = content[5];
            content[5] = "_";
            // this.NER = NER;
            //System.out.println("dne");
        }
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public boolean isEndPunct() {
        return isEndPunct;
    }

    public void setEndPunct(boolean isEndPunct) {
        this.isEndPunct = isEndPunct;
    }

}
