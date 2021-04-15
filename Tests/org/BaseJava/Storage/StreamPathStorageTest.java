package org.BaseJava.Storage;

import org.BaseJava.Serializator.SerializeJSON;
import org.BaseJava.Serializator.SerializeObject;

import static org.junit.Assert.*;

public class StreamPathStorageTest extends AbstractStorageTest {

    public StreamPathStorageTest() {
        super(new StreamPathStorage(DIRECTORY.toPath(), new SerializeJSON()));
    }
}