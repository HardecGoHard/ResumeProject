package org.BaseJava.Storage;

public class StreamStorageTest extends AbstractStorageTest {
    public StreamStorageTest() {
        super(new StreamStorage(directory));
    }
}
