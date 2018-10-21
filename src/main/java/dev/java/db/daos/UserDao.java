package dev.java.db.daos;

import dev.java.db.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    public UserDao(Connection connection) {
        super(connection);
    }

    private final static String SQL_SELECT_ALL = "SELECT * FROM user";
    private final static String SQL_INSERT =
            "INSERT INTO user " +
                    "(email, password,name, surname,user_state) " +
                    "VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE =
            "UPDATE user " +
                    "SET email=?, password=?, name=?, surname=?, user_state=?" +
                    "WHERE id=?";

    @Override
    public List<User> getAllEntities() throws SQLException {
        List<User> allUserList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet userTableRow = statement.executeQuery(SQL_SELECT_ALL);
            while (userTableRow.next()) {
                User user = new User();
                setUserFields(userTableRow, user);
                allUserList.add(user);
            }
            userTableRow.close();
        }
        return allUserList;
    }

    @Override
    public boolean createEntity(User entity) throws SQLException {
        try (PreparedStatement insertPrepareStatement = connection.prepareStatement(SQL_INSERT,
                Statement.RETURN_GENERATED_KEYS)) {
            setValuesForInsertIntoPrepareStatement(insertPrepareStatement, entity);
            int status =  insertPrepareStatement.executeUpdate();
            if (status > 0) {
                ResultSet id = insertPrepareStatement.getGeneratedKeys();
                if (id.next()) {
                    entity.setId(id.getLong(1));
                    id.close();
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    @Override
    public boolean updateEntity(User entity) throws SQLException {
        try (PreparedStatement updatePrepareStatement = connection.prepareStatement(SQL_UPDATE)) {
            setValuesForUpdateIntoPrepareStatement(updatePrepareStatement, entity);
            return updatePrepareStatement.executeUpdate() > 0;
        }
    }

    private void setValuesForUpdateIntoPrepareStatement(PreparedStatement prepareStatement, User user) throws SQLException {
        setValuesForInsertIntoPrepareStatement(prepareStatement, user);
        prepareStatement.setLong(6, user.getId());
    }


    protected void setUserFields(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setState(User.State.valueOf(resultSet.getString("user_state")));
    }

    private void setValuesForInsertIntoPrepareStatement(PreparedStatement prepareStatement, User user)
            throws SQLException {
        prepareStatement.setString(1, user.getEmail());
        prepareStatement.setString(2, user.getPassword());
        prepareStatement.setString(3, user.getName());
        prepareStatement.setString(4, user.getSurname());
        prepareStatement.setObject(5, user.getState());
    }
}
