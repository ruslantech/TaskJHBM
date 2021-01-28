package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        
        UserDaoHibernateImpl userService = new UserDaoHibernateImpl();

        userService.createUsersTable();
        System.out.println();
        userService.saveUser("Andrey", "Malahov", (byte) 30);
        userService.saveUser("John", "Smith", (byte) 35);
        userService.saveUser("Vasya", "Pupkin", (byte) 25);
        userService.saveUser("Denis", "Kovalov", (byte) 27);
        System.out.println();
        List<User> userList = userService.getAllUsers();
        for (User u : userList) {
            System.out.println(u);
        }
        System.out.println();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
