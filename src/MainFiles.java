import java.io.*;
import java.util.Arrays;

public class MainFiles {
    public static void main(String[] args) throws InterruptedException {
        MyClass myClass = new MyClass();
        System.out.println(myClass.name1);
        System.out.println(myClass.name2);
        Thread thread = new Thread(() -> {
            myClass.swap();
        });
        //thread.start();
        Thread thread1 = new Thread(() -> {
            myClass.swap();
        });
        Thread thread2 = new Thread(() -> {
            myClass.swap();
        });
        Thread thread3 = new Thread(() -> {
            myClass.swap();
        });
        thread.start();
        myClass.swap();
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println(myClass.name1);
        System.out.println(myClass.name2);
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
 class MyClass
{
    public String name1 = "Оля";
    public String name2 = "Лена";
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void swap()
    {
        String s = name1;
        name1 = name2;
        name2 = s;
    }
}