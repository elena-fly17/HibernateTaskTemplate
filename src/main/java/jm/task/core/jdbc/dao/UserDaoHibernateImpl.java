package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sf;

    static {
        sf = Util.getSessionFactoryHibernate();
    }

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session ses = sf.getCurrentSession()) {
            Transaction transaction = ses.beginTransaction();
            Query query = ses.createSQLQuery("create table if not exists users " +
                    "(id bigint not null auto_increment, " +
                    "name varchar(25) not null, " +
                    "lastName varchar(45) not null, " +
                    "age tinyint not null, " +
                    "primary key (id))");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session ses = sf.getCurrentSession()) {
            Transaction transaction = ses.beginTransaction();
            Query query = ses.createSQLQuery("drop table if exists users");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session ses = sf.getCurrentSession()) {
            User user = new User(name, lastName, age);
            Transaction transaction = ses.beginTransaction();
            ses.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session ses = sf.getCurrentSession()) {
            Transaction transaction = ses.beginTransaction();
            User user = ses.get(User.class, id);
            ses.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try (Session ses = sf.getCurrentSession()) {
            Transaction transaction = ses.beginTransaction();
            allUsers = ses.createQuery("from User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session ses = sf.getCurrentSession()) {
            Transaction transaction = ses.beginTransaction();
            ses.createQuery("delete User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}