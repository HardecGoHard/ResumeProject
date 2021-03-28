package org.BaseJava.Storage;

import org.BaseJava.Model.Resume;

import java.io.*;

public class StreamStorage extends  AbstractFileStorage{
    public StreamStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(BufferedOutputStream outStream, Resume resume) throws IOException {
        try(ObjectOutputStream os = new ObjectOutputStream(outStream)) {
            os.writeObject(resume);
        }
    }

    @Override
    protected Resume doRead(BufferedInputStream inStream) throws IOException {
        return null;
    }
}
