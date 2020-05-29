package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        try {
            URL url = Main.class.getResource("harry.txt");
            Scanner scanner = new Scanner(new File(url.getPath()));
            String longestWord = "";
            int harryCounter = 0;
            int linesCounter = 0;
            LinkedHashSet<Integer> hashCodes = new LinkedHashSet<>();


            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.length() == 0) {
                    continue;
                }

                hashCodes.add(line.hashCode());
                String[] words = line.split("\\W+");

                Set<String> uniqueWords = new LinkedHashSet<String>(Arrays.asList(words));

                if (uniqueWords.contains("Harry")) {
                    harryCounter++;
                }

                String longestWordInLine = Collections.max(uniqueWords, new Comparator<String>() {
                    @Override
                    public int compare(String s, String t1) {
                        return s.length() > t1.length() ? 1 : -1;
                    }
                });

                if (longestWordInLine.length() > longestWord.length()) {
                    longestWord = longestWordInLine;
                }

                linesCounter++;
            }

            scanner.close();

            int intersections = linesCounter - hashCodes.size();

            System.out.println(String.format("Longest word is %s", longestWord));
            System.out.println(String.format("Quantity of lines with Harry word is %s", harryCounter));
            System.out.println(String.format("Array of hashes of lines is %s", hashCodes));
            System.out.println(String.format("Intersections are %s", intersections));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
