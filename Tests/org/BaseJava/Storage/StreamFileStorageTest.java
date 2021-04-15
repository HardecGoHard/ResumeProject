package org.BaseJava.Storage;

import org.BaseJava.Serializator.SerializeJSON;
import org.BaseJava.Serializator.SerializeObject;

public class StreamFileStorageTest extends AbstractStorageTest {
    public StreamFileStorageTest() {
        super(new StreamFileStorage(DIRECTORY, new SerializeObject()));
    }
}
