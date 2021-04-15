package org.BaseJava.Serializator;

import org.BaseJava.Model.Resume;

import java.io.*;

public class SerializeObject implements IStreamIOSerializator{

    public void doWrite(BufferedOutputStream outStream, Resume resume) throws IOException {
        try(ObjectOutputStream os = new ObjectOutputStream(outStream)) {
            os.writeObject(resume);
        }
    }

    public Resume doRead(BufferedInputStream inStream) throws IOException {
        Resume resume = null;
        try(ObjectInputStream os = new ObjectInputStream(inStream)) {
            try {
                resume = (Resume) os.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return resume;
    }
}
