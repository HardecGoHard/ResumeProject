package org.BaseJava.Storage;

import org.BaseJava.Model.Resume;
import org.BaseJava.util.Config;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SqlStorageTest {
    private static Config config = Config.get();
    private static final SqlStorage SQL_STORAGE = new SqlStorage(config.getDataBaseUrl(),
            config.getDataBaseUser(), config.getDataBasePassword());

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    protected static final Resume RESUME_4;
    static {
        RESUME_1 = new Resume("Иван Иванов", "uuid1");
        RESUME_2 = new Resume("Кирилл Кириллин", "uuid2");
        RESUME_3 = new Resume("Игнат Петухов", "uuid3");
        RESUME_4 = new Resume("Вячеслав Миронов", "uuid4");
    }

    @Before
    public void setUp()  {
        SQL_STORAGE.clear();
        SQL_STORAGE.save(RESUME_1);
        SQL_STORAGE.save(RESUME_2);
        SQL_STORAGE.save(RESUME_3);
        SQL_STORAGE.save(RESUME_4);
    }
    @Test
    public void update() {
        Resume rr = new Resume("Сергей Яровенко", "uuid3");
        SQL_STORAGE.update(rr);
        assertEquals(rr, SQL_STORAGE.get(rr.getUuid()));
    }

    @Test
    public void get() {
        assertEquals(SQL_STORAGE.get(RESUME_1.getUuid()),RESUME_1);
        assertEquals(SQL_STORAGE.get(RESUME_2.getUuid()),RESUME_2);
        assertEquals(SQL_STORAGE.get(RESUME_3.getUuid()),RESUME_3);
    }

    @Test
    public void delete() {
        SQL_STORAGE.delete(RESUME_1.getUuid());
        assertEquals(3,SQL_STORAGE.getSize());
    }

    @Test
    public void clear() {
        SQL_STORAGE.clear();
        assertEquals(0, SQL_STORAGE.getSize());
    }

    @Test
    public void getSortedAll() {
        List<Resume> list = new ArrayList<>();
        list.add(RESUME_4);
        list.add(RESUME_1);
        list.add(RESUME_3);
        list.add(RESUME_2);
        assertEquals(list,SQL_STORAGE.getSortedAll());
    }

    @Test
    public void getSize() {
        assertEquals(4,SQL_STORAGE.getSize());
    }
}