package org.BaseJava.Storage;

import org.BaseJava.Model.Resume;
import org.junit.Test;

public class SortedListStorageTest extends AbstractStorageTest {
    public SortedListStorageTest() {
        super(new SortedListStorage());
    }

    @Test
    public void save(){
        Resume resume = new Resume("Герыч");
        strorage.save(resume);
        assertSize(5);
    }
}
