package org.Java.Storage;

import org.Java.Serializator.SerializeObject;

public class StreamFileStorageTest extends AbstractStorageTest {
    public StreamFileStorageTest() {
        super(new StreamFileStorage(DIRECTORY, new SerializeObject()));
    }
}
