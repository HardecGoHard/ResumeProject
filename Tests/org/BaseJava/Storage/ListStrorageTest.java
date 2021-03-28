package org.BaseJava.Storage;

import org.BaseJava.Model.Resume;
import org.junit.Test;

public class ListStrorageTest extends AbstractStorageTest {

    public ListStrorageTest() {
        super(new ListArrayStorage());
    }

    @Test
    public void save(){
        Resume resume = new Resume("Test Name");
        strorage.save(resume);
        assertSize(5);
    }
}
