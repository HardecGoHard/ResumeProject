package org.Java.Serializator;

import org.Java.Model.Resume;
import org.Java.util.ParserJSON;

import java.io.*;

public class SerializeJSON implements IStreamIOSerializator {
    @Override
    public void doWrite(BufferedOutputStream outStream, Resume resume) throws IOException {
        try (Writer writer = new OutputStreamWriter(outStream)) {
            ParserJSON.write(writer, resume);
        }

    }

    @Override
    public Resume doRead(BufferedInputStream inStream) throws IOException {
        try (Reader read = new InputStreamReader(inStream)) {
            return ParserJSON.reader(read, Resume.class);
        }
    }
}
