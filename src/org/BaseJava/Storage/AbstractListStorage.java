package org.BaseJava.Storage;

import org.BaseJava.Model.Resume;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractListStorage extends AbstractStorage<Integer> {
    protected ArrayList<Resume> storage;

    @Override
    protected void toDelete(String uuid, Integer searchKey) {
        storage.remove((searchKey).intValue());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public ArrayList<Resume> getSortedAll() {
        ArrayList<Resume> tempAr = new ArrayList<>(storage);
        Collections.sort(tempAr);
        return tempAr;
    }


    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    protected Resume toGet(Integer searchKey) {
        return storage.get(searchKey);
    }
}
