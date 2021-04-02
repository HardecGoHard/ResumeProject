package org.BaseJava;

import org.BaseJava.Model.Resume;
import org.BaseJava.Storage.ListArrayStorage;

import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.FilterInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TestArray {
    final static ListArrayStorage ARRAY_LIST_STORAGE = new ListArrayStorage();

    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\Hardec\\Desktop\\ResumeBaseProject\\storage\\");

        System.out.println(path.toAbsolutePath());
    }
}
