package org.BaseJava.Storage;

import org.BaseJava.Model.Resume;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MapStrorageTest extends AbstractStorageTest {
    public MapStrorageTest() {
        super(new MapStorage());
    }
    @Test
    public void getSortedAll(){
        Map<String,Resume> array = new HashMap();
        array.put(RESUME_1.getUuid(),RESUME_1);
        array.put(RESUME_2.getUuid(),RESUME_2);
        array.put(RESUME_3.getUuid(),RESUME_3);
        array.put(RESUME_4.getUuid(),RESUME_4);
        assertSize(4);
        assertEquals(array,strorage.getSortedAll());
    }
}
