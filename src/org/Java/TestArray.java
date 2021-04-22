package org.Java;

import java.io.*;
import java.util.*;
import java.util.List;

public class TestArray {
    static List<Thread> threads = new ArrayList<>();
    static volatile int count = 0;
    public static void main(String[] args) {
        try(InputStream is = new FileInputStream(new File("..\\ResumeBaseProject\\config\\prop.properties"))){
            Properties nnn = new Properties();
            nnn.load(is);
        System.out.println((new File("../ResumeBaseProject/storage")).getAbsolutePath());
    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
