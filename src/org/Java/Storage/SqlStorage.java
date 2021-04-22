package org.Java.Storage;

import org.Java.Model.ContactType;
import org.Java.Model.Resume;
import org.Java.Model.ResumeSectionType;
import org.Java.Model.Section;
import org.Java.util.ParserJSON;
import org.Java.util.SqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlStorage implements IStorage {
    private SqlUtil sqlUtil;

    public SqlStorage(String url, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sqlUtil = new SqlUtil(() -> DriverManager.getConnection(url, user, password));
    }

    @Override
    public void save(Resume resume) {
        sqlUtil.sqlTransaction(connection -> {
            try {
                try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume" +
                        "(uuid, full_name)  VALUES (?,?)")) {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    ps.execute();
                }
                insertContact(connection, resume);
                insertSection(connection, resume);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        });
    }


    @Override
    public void update(Resume resume) {
        sqlUtil.requestSQL("UPDATE resume " +
                "SET full_name =? WHERE uuid =?", ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            return ps.execute();
        });
        sqlUtil.sqlTransaction(connection -> {
            try {
                deleteContact(connection, resume.getUuid());
                insertContact(connection, resume);
                deleteSection(connection, resume.getUuid());
                insertSection(connection, resume);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = new Resume();
        sqlUtil.sqlTransaction(connection -> {
            try {
                try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume WHERE uuid = ?")) {
                    ps.setString(1, uuid);
                    ResultSet resultSet = ps.executeQuery();
                    if (!resultSet.next()) {
                        throw new SQLException();
                    }
                    resume.setFullName(resultSet.getString("full_name"));
                    resume.setUuid(uuid);
                }

                selectContact(connection,resume);
                selectSection(connection,resume);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        });
        return resume;
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
        Map<String, Resume> resumeMap = new HashMap();
        sqlUtil.sqlTransaction(connection ->
        {
            try {
                try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume")) {
                    ResultSet resultSet = ps.executeQuery();
                    while (resultSet.next()){
                        String uuid = resultSet.getString("uuid");
                        Resume resume = new Resume(resultSet.getString("full_name"),uuid);
                        selectContact(connection,resume);
                        selectSection(connection,resume);
                        resumeMap.put(uuid, resume);
                        resumeMap.put(uuid, resume);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        });
        return new ArrayList<>(resumeMap.values());
    }


    @Override
    public int getSize() {
        return sqlUtil.requestSQL("SELECT COUNT(*) FROM resume", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertContact(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact " +
                "(value, type, resume_uuid)  VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContactEnumMap().entrySet()) {
                ps.setString(1, entry.getValue());
                ps.setString(2, entry.getKey().toString());
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSection(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO section" +
                "(resume_uuid, type, content)  VALUES (?,?,?)")) {
            for (Map.Entry<ResumeSectionType, Section> entry : resume.getResumeSectionEnumMap().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getKey().toString());
                ps.setString(3, ParserJSON.write(Section.class, entry.getValue()));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContact(Connection connection, String uuid) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM contact " +
                "WHERE resume_uuid =? ")) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }

    private void deleteSection(Connection connection, String uuid) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM section " +
                "WHERE resume_uuid =? ")) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }

    private void selectContact(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                resume.setContact(ContactType.valueOf(resultSet.getString("type")),
                        resultSet.getString("value"));
            }
        }
    }

    private void selectSection(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section WHERE resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                resume.setResumeSection(ResumeSectionType.valueOf(resultSet.getString("type")),
                        ParserJSON.reader(resultSet.getString("content"), Section.class));
            }
        }
    }

}
