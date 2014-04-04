package fransonsr.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

public class SortingTest {

    @Ignore
    @Test
    public void generateRandomList() throws Exception {

        List<String> list = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(getClass()
            .getResourceAsStream("/wordsEn.txt")))) {
            String line = null;

            do {
                line = in.readLine();
                if (line != null) {
                    list.add(line);
                }
            }
            while (line != null);
        }

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int nextInt = random.nextInt(list.size());

            System.out.println(list.get(nextInt));
            list.remove(nextInt);
        }
    }

    @Test
    public void sortFile_asc_Collections_Comparator() throws Exception {
        final Comparator<String> ascComparator = new Comparator<String>() {
            public int compare(String string1, String string2) {
                return string1.compareTo(string2);
            }
        };

        System.out.println("sortFile_asc_Collections_Comparator:\n");

        List<String> list = readListFromFile();

        dumpList(list, new ListModifier() {
            @Override
            public void modify(List<String> list) {
                Collections.sort(list, ascComparator);
            }
        });
    }

    @Test
    public void sortFile_desc_Collections_Comparator() throws Exception {
        final Comparator<String> descComparator = new Comparator<String>() {
            public int compare(String string1, String string2) {
                return string2.compareTo(string1);
            }
        };

        System.out.println("sortFile_desc_Collections_Comparator:\n");

        List<String> list = readListFromFile();

        dumpList(list, new ListModifier() {
            @Override
            public void modify(List<String> list) {
                Collections.sort(list, descComparator);
            }
        });
    }

    @Test
    public void sortFile_asc_Collections_sort() throws Exception {

        System.out.println("sortFile_asc_Collections_sort:\n");

        List<String> list = readListFromFile();

        dumpList(list, new ListModifier() {
            @Override
            public void modify(List<String> list) {
                Collections.sort(list);
            }
        });
    }

    @Test
    public void sortFile_asc_Collections_reverse() throws Exception {

        System.out.println("sortFile_asc_Collections_reverse:\n");

        List<String> list = readListFromFile();

        dumpList(list, new ListModifier() {
            @Override
            public void modify(List<String> list) {
                Collections.sort(list);
                Collections.reverse(list);
            }
        });
    }

    private static interface ListModifier {
        void modify(List<String> list);
    }

    private void dumpList(List<String> list, ListModifier listModifier) {
        StringBuilder buff = new StringBuilder()
            .append("Original (100 lines):\n")
            .append(dumpList(list, 100));

        System.out.println(buff.toString());

        listModifier.modify(list);

        buff = new StringBuilder()
            .append("Modfied (100 lines):\n")
            .append(dumpList(list, 100));

        System.out.println(buff.toString());
    }

    private String dumpList(List<String> list, int size) {
        list = list.subList(0, size);
        StringBuilder buff = new StringBuilder();
        for (String line : list) {
            buff.append("\t").append(line).append("\n");
        }

        return buff.toString();
    }

    private List<String> readListFromFile() throws IOException {
        List<String> list = new LinkedList<String>();

        // read the list
        try (InputStream inputStream = getClass().getResourceAsStream("/unsortedList.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    list.add(line);
                }
            }
            while (line != null);
        }
        return list;
    }

}
