package dev.java.db.DAOs;

import dev.java.db.model.Table;
import dev.java.db.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO extends AbstractDAO<User>{

    public UserDAO(Connection connection, Table table) {
        super(connection, table);
    }

    @Override
    public boolean delete(User entity) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM " + table + " WHERE email='" + entity.getEmail() + "'";
            return statement.executeUpdate(sql) != 0;
        }
    }

    @Override
    public boolean create(User entity) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO " + table +
                    " (email, password, name, surname,user_state) " +
                    "VALUES ('";
            sql += entity.getEmail() + "', '" +
                    entity.getPassword() + "', '" +
                    entity.getName() + "', '" +
                    entity.getSurname() + "', '" +
                    entity.getState() + "')";
            return statement.executeUpdate(sql) != 0;
        }
    }

    @Override
    public boolean update(User entity) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "UPDATE " + table +" SET " +
                    "email='" + entity.getEmail() + "', " +
                    "password='" + entity.getPassword() + "', " +
                    "name='" + entity.getName() + "', " +
                    "surname='" + entity.getSurname() + "', " +
                    "user_state='" + entity.getState() + "'" +
                    "WHERE id=" + entity.getId();
            return statement.executeUpdate(sql) != 0;
        }
    }

    @Override
    protected void setFields(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setState(User.State.valueOf(resultSet.getString("user_state")));
    }
}
