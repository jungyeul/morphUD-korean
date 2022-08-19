
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Sentence {

    int sentID;
    Row[] allRows;
    int size;
    String NERInOldData;

    public Sentence(int ID, Row[] allRows) {
        this.sentID = ID;
        this.allRows = allRows;
        this.size = 0;
        this.NERInOldData = "# ner = ";
    }

    public int getSentID() {
        return sentID;
    }

    public void setSentID(int sentID) {
        this.sentID = sentID;
    }

    public Row[] getAllRows() {
        return allRows;
    }

    public void setAllRows(Row[] allRows) {
        this.allRows = allRows;
    }

    public void setNewLine(Row row) {
        allRows[size] = row;
        size++;
    }

    public void print() {
        File file = new File("train_data_annotated.txt");
        for (int i = 0; i < 2; i++) {
            allRows[i].printRow();
            // System.out.println();
            // File file = new File("train_data_annotated.txt");
            try {
                FileWriter fileWritter = new FileWriter(file.getName(), true);
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.write("\n");
                bufferWritter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(NERInOldData);
            bufferWritter.write("\n");
            bufferWritter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 2; i < size; i++) {
            allRows[i].printRow();
            // System.out.println();
            // File file = new File("train_data_annotated.txt");
            try {
                FileWriter fileWritter = new FileWriter(file.getName(), true);
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.write("\n");
                bufferWritter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // print an empty line
        // System.out.println();
        // File file = new File("train_data_annotated.txt");
        try {
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write("\n");
            bufferWritter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Row getNthRow(int n) {
        return allRows[n];
    }


}
