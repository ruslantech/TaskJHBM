package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userService = new UserDaoHibernateImpl();
    public void createUsersTable() {
        userService.createUsersTable();
    }

    public void dropUsersTable() {
        userService.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userService.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        userService.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = userService.getAllUsers();
        return userList;

    }

    public void cleanUsersTable() {
        userService.cleanUsersTable();
    }
}
