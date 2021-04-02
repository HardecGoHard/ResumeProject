package org.BaseJava.Storage;

import static org.junit.Assert.*;

public class StreamPathStorageTest extends AbstractStorageTest{

    public StreamPathStorageTest() {
        super(new StreamPathStorage(DIRECTORY.toPath()));
    }
}