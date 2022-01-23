package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = "create table if not exists users" +
                "(id bigint not null auto_increment, " +
                "name varchar(25) not null, " +
                "lastName varchar(45) not null, " +
                "age tinyint not null, " +
                "primary key(id))";
        Connection con = Util.dbOpenConnectionJDBC();

        try {
            Statement st = con.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.dbCloseConnectionJDBC();
        }
    }

    public void dropUsersTable() {
        String sql = "drop table if exists users";
        Connection con = Util.dbOpenConnectionJDBC();

        try {
            Statement st = con.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.dbCloseConnectionJDBC();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert users (name, lastName, age) values (?, ?, ?)";
        Connection con = Util.dbOpenConnectionJDBC();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.dbCloseConnectionJDBC();
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id=?";
        Connection con = Util.dbOpenConnectionJDBC();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.dbCloseConnectionJDBC();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "select * from users";
        Connection con = Util.dbOpenConnectionJDBC();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.dbCloseConnectionJDBC();
        }

        return list;
    }

    public void cleanUsersTable() {
        String sql = "truncate users";
        Connection con = Util.dbOpenConnectionJDBC();

        try {
            Statement st = con.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.dbCloseConnectionJDBC();
        }
    }
}
