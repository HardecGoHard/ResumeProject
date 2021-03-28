package org.BaseJava.Storage;

import javafx.beans.binding.ObjectExpression;
import org.BaseJava.Model.Resume;

import java.util.ArrayList;
import java.util.Objects;

public interface IStorage {
    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    void clear();

    Object getSortedAll();

    int getSize();
}
