package org.Java.Storage;

import org.Java.Serializator.SerializeJSON;

public class StreamPathStorageTest extends AbstractStorageTest {

    public StreamPathStorageTest() {
        super(new StreamPathStorage(DIRECTORY.toPath(), new SerializeJSON()));
    }
}