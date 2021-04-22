package org.Java.Storage;

import org.Java.Exception.StorageException;
import org.Java.Model.Resume;
import org.Java.Serializator.IStreamIOSerializator;

import java.io.*;
import java.util.*;

public class StreamFileStorage extends AbstractStorage<File> {
    protected File directory;
    protected IStreamIOSerializator serializator;

    public StreamFileStorage(File directory, IStreamIOSerializator serializator) {
        Objects.requireNonNull(directory, "The directory must be not null");
        Objects.requireNonNull(serializator, "The serializator must be not null");
        if (directory.isFile()) {
            throw new StorageException("The directory cant't be file", directory.getName());
        } else if (directory.isDirectory()) {
            this.directory = directory;
        }
        this.serializator = serializator;
    }


    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isSearchKeyExsist(File file) {
        for (File file2 : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().equals(file2.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void toSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Could not generate new File", file.getAbsolutePath());
        }
        toUpdate(resume, file);
    }

    @Override
    protected void toUpdate(Resume resume, File file) {
        try {
            serializator.doWrite(new BufferedOutputStream(new FileOutputStream(file)), resume);
        } catch (IOException e) {
            throw new StorageException("IO Exception " + e.getMessage(), file.getName());
        }
    }

    @Override
    protected void toDelete(String uuid, File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    public void clear() {
        Arrays.stream(directory.listFiles()).parallel().forEach(file -> toDelete(file.getName(), file));
    }

    @Override
    public List<Resume> getSortedAll() {
        List<Resume> list = getAll();
        list.sort((o1, o2) -> (o1.compareTo(o2)));
        return list;
    }

    public List<Resume> getAll() {
        File[] fiels = directory.listFiles();
        List<Resume> listResume = new ArrayList<>();
        if (fiels == null) {
            throw new StorageException("Could not get list file", directory.getName());
        } else {
            for (File file : fiels) {
                try {
                    listResume.add(serializator.doRead(new BufferedInputStream(new FileInputStream(file))));
                } catch (IOException e) {
                    throw new StorageException("Storage IO ", file.getAbsolutePath());
                }
            }
        }
        return listResume;
    }

    @Override
    public int getSize() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Directory read error", null);
        }
        return list.length;
    }

    @Override
    protected Resume toGet(File file) {
        try {
            return serializator.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getAbsolutePath());
        }
    }

    protected boolean isExsist() {
        return directory.exists();
    }
}
