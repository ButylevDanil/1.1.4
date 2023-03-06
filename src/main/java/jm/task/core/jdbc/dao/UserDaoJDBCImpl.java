package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS `sys`.`UsersTable` (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45) NULL, lastName VARCHAR(45) NULL, age INT NULL)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement();){
            statement.execute("DROP TABLE IF EXISTS `sys`.`UsersTable`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement();){
            statement.execute("INSERT INTO `sys`.`UsersTable` SET name = '" + name + "', lastName = '" + lastName + "', age = " + age);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement();){
            statement.execute("DELETE FROM `sys`.`UsersTable` WHERE id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM `sys`.`UsersTable`");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement();){
            statement.execute("TRUNCATE TABLE `sys`.`UsersTable`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
