import java.io.*;

public class MainFiles {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Hardec\\Desktop");
        printDeepDirectory(file, 0);

    }

    private static void printDeepDirectory(File dir, int value) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                int rezVal = value + file.getName().length();
                if (file.isFile()) {
                    System.out.println(String.format("%" + rezVal + "s","-"+file.getName()));
                } else {
                    System.out.println(String.format("%" + rezVal + "s","+"+file.getName())+":");
                    printDeepDirectory(file,rezVal+1);
                }
            }
        }
    }
}
