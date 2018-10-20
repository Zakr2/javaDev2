package dev.java.db;

import dev.java.db.DAOs.*;
import dev.java.db.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        return DriverManager.getConnection(url, user, pass);
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        UserDAO userDAO=new UserDAO(connection,Table.USER);
        User user=new User();
        user.setEmail("zakrevskii_evgenii@mail.ru");
        user.setPassword("322");
        user.setName("Evgeniy");
        user.setSurname("Zakrevskiy");
        userDAO.create(user);

//        VacancyDAO vacancyDAO = new VacancyDAO(connection, Table.VACANCY);
//        Vacancy vacancy = new Vacancy();
//        vacancy.setName("Java Developer");
//        vacancy.setMinExperienceInYears(1);
//        vacancy.setMaxExperienceInYears(3);
//        vacancy.setMinSalaryInDollars(200);
//        vacancy.setMaxSalaryInDollars(500);
//        vacancyDAO.create(vacancy);

    }
}
