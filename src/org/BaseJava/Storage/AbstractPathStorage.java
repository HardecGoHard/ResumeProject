package org.BaseJava.Storage;

import org.BaseJava.Exception.StorageException;
import org.BaseJava.Model.Resume;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path path;

    public AbstractPathStorage(Path path) {
        Objects.requireNonNull(path, "Path can't be null");
        if (!Files.isDirectory(path)) {
            throw new StorageException("The directory cant't be file", path.toString());
        } else {
            this.path = path;
        }
    }

    protected abstract void doWrite(BufferedOutputStream outStream, Resume resume) throws IOException;

    protected abstract Resume doRead(BufferedInputStream inStream) throws IOException;

    @Override
    protected Path getSearchKey(String uuid) {
        return path.resolve(uuid);
    }


    @Override
    protected boolean isSearchKeyExsist(Path searchKey) {
        boolean match;
        try {
            match = Files.list(path).anyMatch(path -> path.getFileName().equals(searchKey.getFileName()));
        } catch (IOException e) {
            throw new StorageException("SearchKey error", searchKey.getFileName().toString());
        }
        return match;
    }

    @Override
    protected void toSave(Resume resume, Path searchKey) {
        try {
            Files.createFile(searchKey);
        } catch (IOException e) {
            throw new StorageException("create file exception" + e.getMessage(), searchKey.toString());
        }
        toUpdate(resume, searchKey);
    }

    @Override
    protected void toUpdate(Resume resume, Path searchKey) {
        try {
            doWrite(new BufferedOutputStream(new FileOutputStream(searchKey.toString())), resume);
        } catch (IOException e) {
            throw new StorageException("IO Exception " + e.getMessage(), searchKey.toString());
        }
    }

    @Override
    protected void toDelete(String uuid, Path searchKey) {
        try {
            Files.delete(searchKey);
        } catch (IOException e) {
            throw new StorageException("delete file error" + e.getMessage(), searchKey.toString());
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(path).forEach(path1 -> {
                try {
                    Files.delete(path1);
                } catch (IOException e) {
                    throw new StorageException("delete file error" + e.getMessage(), path1.toString());
                }
            });
        } catch (IOException e) {
            throw new StorageException("clear path error" + e.getMessage(), path.toString());
        }
    }

    public List<Resume> getAll() {
        ArrayList<Resume> list= new ArrayList<>();
        try {
            Files.list(path).forEach(path1 -> {list.add(toGet(path1));
                System.out.println(path1.toAbsolutePath());});
        } catch (IOException e) {
            throw new StorageException(" lambda Stream error", path.toString());
        }
        return list;
    }

    @Override
    public List<Resume> getSortedAll() {
        List<Resume> list= getAll();
        list.sort((o1, o2) -> o1.compareTo(o2));
        return list;
    }

    @Override
    public int getSize() {
        try {
           return (int)Files.list(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    protected Resume toGet(Path searchKey) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(searchKey.toString())));
        } catch (IOException e) {
          throw new StorageException("File read error", searchKey.toString());
        }
    }
}
