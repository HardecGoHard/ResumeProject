package org.BaseJava.Serializator;

import org.BaseJava.Model.Resume;
import org.BaseJava.util.ParserJSON;

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
