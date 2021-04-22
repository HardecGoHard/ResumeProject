package org.Java.Serializator;

import org.Java.Model.Resume;

import java.io.*;

 public interface IStreamIOSerializator {

    void doWrite(BufferedOutputStream outStream, Resume resume) throws IOException;

    Resume doRead(BufferedInputStream inStream) throws IOException;
}
