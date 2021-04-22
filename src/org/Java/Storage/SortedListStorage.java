package org.Java.Storage;

import org.Java.Model.Resume;

import java.util.ArrayList;
import java.util.Collections;


public class SortedListStorage extends AbstractListStorage {

    public SortedListStorage() {
        storage = new ArrayList<>();
    }

    public SortedListStorage(int size) {
        storage = new ArrayList<>(size);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume tempResume = new Resume(uuid, "Жора");
        return Collections.binarySearch(storage, tempResume, (o1, o2) -> o1.getUuid().compareTo(o2.getUuid()));
    }

    @Override
    protected boolean isSearchKeyExsist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void toSave(Resume resume, Integer searchKey) {
        storage.add(Math.abs(searchKey) - 1, resume);
    }

    @Override
    protected void toUpdate(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

}
