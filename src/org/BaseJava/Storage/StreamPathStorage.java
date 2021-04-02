package org.BaseJava.Storage;

import org.BaseJava.Model.Resume;

import java.io.*;
import java.nio.file.Path;

public class StreamPathStorage extends AbstractPathStorage {

    public StreamPathStorage(Path path) {
        super(path);
    }

    @Override
    protected void doWrite(BufferedOutputStream outStream, Resume resume) throws IOException {
        try(ObjectOutputStream os = new ObjectOutputStream(outStream)) {
            os.writeObject(resume);
        }
    }

    @Override
    protected Resume doRead(BufferedInputStream inStream) throws IOException {
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
