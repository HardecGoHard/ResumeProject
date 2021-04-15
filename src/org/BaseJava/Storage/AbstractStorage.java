package org.BaseJava.Storage;

import com.google.gson.InstanceCreator;
import org.BaseJava.Exception.ExsistArrayStorageException;
import org.BaseJava.Exception.NonExsistArrayStorageException;
import org.BaseJava.Model.Resume;

import java.lang.reflect.Type;
import java.util.logging.Logger;


public abstract class AbstractStorage<K> implements IStorage {

    protected static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract K getSearchKey(String uuid);

    protected abstract boolean isSearchKeyExsist(K searchKey);

    protected abstract void toSave(Resume resume, K searchKey);

    protected abstract void toUpdate(Resume resume, K searchKey);

    protected abstract void toDelete(String uuid, K searchKey);

    public abstract void clear();

    public abstract Object getSortedAll();

    public abstract int getSize();

    protected abstract Resume toGet(K searchKey);

    @Override
    public void save(Resume resume) {
        LOGGER.info("Save " + resume);
        K searchKey = getSearchKey(resume.getUuid());
        if (!isSearchKeyExsist(searchKey)) {
            toSave(resume, searchKey);
        } else {
            LOGGER.warning("Save exception " + resume);
            throw new ExsistArrayStorageException("Данное резюме уже существует", resume.getUuid());
        }
    }


    @Override
    public void update(Resume resume) {
        LOGGER.info("Update " + resume);
        K searchKey = getSearchKey(resume.getUuid());
        if (isSearchKeyExsist(searchKey)) {
            toUpdate(resume, searchKey);
        } else {
            LOGGER.warning("update exception " + resume);
            throw new NonExsistArrayStorageException("Данное резюме не сущесвтует", resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        LOGGER.info("Get " + uuid);
        K searchKey = getSearchKey(uuid);
        if (!isSearchKeyExsist(searchKey)) {
            LOGGER.warning("get exception " + uuid);
            throw new NonExsistArrayStorageException("Данное резюме не сущесвтует", uuid);
        }
        return toGet(searchKey);
    }


    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete " + uuid);
        K searchKey = getSearchKey(uuid);
        if (isSearchKeyExsist(searchKey)) {
            toDelete(uuid, searchKey);
        } else {
            LOGGER.warning("delete exception " + uuid);
            throw new NonExsistArrayStorageException("Данное резюме не сущесвтует", uuid);
        }
    }

}

