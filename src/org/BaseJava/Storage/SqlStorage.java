package org.BaseJava.Storage;

import org.BaseJava.Exception.StorageException;
import org.BaseJava.Model.Resume;
import org.BaseJava.util.Config;
import org.BaseJava.util.SqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements IStorage {
    private SqlUtil sqlUtil;

    public SqlStorage(String url, String user, String password) {
        sqlUtil = new SqlUtil(() -> DriverManager.getConnection(url, user, password));
    }

    @Override
    public void save(Resume resume) {
        sqlUtil.requestSQL("INSERT INTO resume" +
                "(uuid, full_name)  VALUES (?,?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            return ps.execute();
        });
    }

    @Override
    public void update(Resume resume) {
        sqlUtil.requestSQL("UPDATE resume " +
                "SET full_name=? WHERE uuid =?", ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            return ps.execute();
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlUtil.requestSQL("SELECT * FROM resume WHERE uuid =?", ps ->
        {
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                throw new SQLException();
            }
            return new Resume(resultSet.getString("full_name"), uuid);
        });
    }

    @Override
    public void delete(String uuid) {
        sqlUtil.requestSQL("DELETE FROM resume WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            return ps.execute();
        });
    }

    @Override
    public void clear() {
        sqlUtil.requestSQL("DELETE FROM resume", ps -> ps.execute());
    }

    @Override
    public List<Resume> getSortedAll() {
        List<Resume> list = new ArrayList<>();
        return sqlUtil.requestSQL("SELECT * FROM resume ORDER BY full_name, uuid", ps ->
        {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new Resume(resultSet.getString("full_name"), resultSet.getString("uuid")));
            }
            return list;
        });
    }

    @Override
    public int getSize() {
        return sqlUtil.requestSQL("SELECT COUNT(*) FROM resume", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next()? rs.getInt(1): 0;
        });
    }
}
