package fransonsr.sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

import fransonsr.utils.MD5Hash;

public class SoringTest {

    @Ignore
    @Test
    public void generateRandomList() throws Exception {
        Random random = new Random();

        for(int i = 0; i < 10000; i++) {
            int nextInt = random.nextInt(100000);

            MD5Hash hash = new MD5Hash(Integer.toString(nextInt).toCharArray());

            System.out.println(hash);
        }
    }

    @Test
    public void sortFile_asc() throws Exception {
        sortFile(new Comparator<String>() {
            public int compare(String string1, String string2) {
                return string1.compareTo(string2);
            }
        });
    }

    @Test
    public void sortFile_desc() throws Exception {
        sortFile(new Comparator<String>() {
            public int compare(String string1, String string2) {
                return string2.compareTo(string1);
            }
        });
    }

    private void sortFile(Comparator<String> comparator) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/unsortedList.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        List<String> list = new LinkedList<String>();

        // read the list
        try {
            String line;
            do {
                line = reader.readLine();
                if(line != null) {
                    list.add(line);
                }
            }
            while(line != null);
        }
        finally {
            reader.close();
        }

        // sort the list
        Collections.sort(list, comparator);

        // save the file
        File outputFile = File.createTempFile("sorted-asc", null);

        System.out.println("Filename: " + outputFile.getCanonicalPath());

        FileWriter writer = new FileWriter(outputFile);

        try {
            for(String line : list) {
                writer.append(line).append("\n");
            }
        }
        finally {
            writer.close();
        }
    }

}
