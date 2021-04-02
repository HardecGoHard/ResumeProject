package org.BaseJava.Storage;

public class StreamFileStorageTest extends AbstractStorageTest {
    public StreamFileStorageTest() {
        super(new StreamFileStorage(DIRECTORY));
    }
}
