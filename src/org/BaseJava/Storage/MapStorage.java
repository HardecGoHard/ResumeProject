package org.BaseJava.Storage;

import org.BaseJava.Model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {
    Map<String, Resume> mapResumeStorage = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry :
                mapResumeStorage.entrySet()) {
            if (entry.getKey().equals(uuid))
                return entry.getKey();
        }
        return null;
    }

    @Override
    protected boolean isSearchKeyExsist(String searchKey) {
        return searchKey != null;
    }

    @Override
    protected void toSave(Resume resume, String searchKey) {
        mapResumeStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void toUpdate(Resume resume, String searchKey) {
        mapResumeStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void toDelete(String uuid, String searchKey) {
        mapResumeStorage.remove(uuid);
    }

    @Override
    public void clear() {
        mapResumeStorage.clear();
    }

    @Override
    public Map getSortedAll() {
        Map tempResumeStorage = new HashMap(mapResumeStorage);
        return tempResumeStorage;
    }

    @Override
    public int getSize() {
        return mapResumeStorage.size();
    }

    @Override
    protected Resume toGet(String searchKey) {
        return mapResumeStorage.get(searchKey);
    }
}
