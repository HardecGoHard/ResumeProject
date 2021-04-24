package org.Java.Storage;

import org.Java.Model.Resume;

public interface IStorage {
    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    void clear();

    Object getSortedAll();

    int getSize();
}
