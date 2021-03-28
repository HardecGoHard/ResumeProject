package org.BaseJava.Storage;

import org.BaseJava.Exception.ExsistArrayStorageException;
import org.BaseJava.Model.Resume;

import java.util.ArrayList;
import java.util.Iterator;


public class ListArrayStorage extends AbstractListStorage {

    public ListArrayStorage() {
        storage = new ArrayList<>();
    }

    public ListArrayStorage(int size) {
        storage = new ArrayList<>(size);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isSearchKeyExsist(Integer searchKey) {
        return (Integer) searchKey != -1;
    }

    @Override
    protected void toSave(Resume resume, Integer searchKey) {
        storage.add(resume);
    }

    @Override
    protected void toUpdate(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

}
