package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    PreparedStatement preparedStatement = null;
    Statement statement;

    {
        try {
            statement = util.getConnection().createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            statement.executeUpdate("CREATE TABLE users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)");
            System.out.println("Таблица была успешно создана");
        } catch (SQLException ex) {
        }
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("DROP TABLE users");
            System.out.println("Таблица была успешно удалена");
        } catch (SQLException ex) {
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            preparedStatement = util.getConnection().prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name +" добавлен в базу данных");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (util.getConnection() != null) {
                util.getConnection().close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            preparedStatement = util.getConnection().prepareStatement("DELETE FROM users WHERE id = ?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (util.getConnection() != null) {
                util.getConnection().close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (util.getConnection() != null) {
                util.getConnection().close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            preparedStatement = util.getConnection().prepareStatement("DELETE FROM users");
            preparedStatement.executeUpdate();
            System.out.println("Таблица была успешно очищена");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
