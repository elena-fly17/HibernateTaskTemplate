package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        SessionFactory sf = Util.getSessionFactoryHibernate();
        Session ses = null;
        Transaction transaction = null;

        try {
            ses = sf.getCurrentSession();
            transaction = ses.beginTransaction();
            Query query = ses.createSQLQuery("create table if not exists users " +
                    "(id bigint not null auto_increment, " +
                    "name varchar(25) not null, " +
                    "lastName varchar(45) not null, " +
                    "age tinyint not null, " +
                    "primary key (id))");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            ses.close();
            sf.close();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sf = Util.getSessionFactoryHibernate();
        Session ses = null;
        Transaction transaction = null;

        try {
            ses = sf.getCurrentSession();
            transaction = ses.beginTransaction();
            Query query = ses.createSQLQuery("drop table if exists users");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            ses.close();
            sf.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sf = Util.getSessionFactoryHibernate();
        Session ses = null;
        Transaction transaction = null;

        try {
            ses = sf.getCurrentSession();
            User user = new User(name, lastName, age);
            transaction = ses.beginTransaction();
            ses.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            ses.close();
            sf.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sf = Util.getSessionFactoryHibernate();
        Session ses = null;
        Transaction transaction = null;

        try {
            ses = sf.getCurrentSession();
            transaction = ses.beginTransaction();
            User user = ses.get(User.class, id);
            ses.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            ses.close();
            sf.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sf = Util.getSessionFactoryHibernate();
        Session ses = null;
        Transaction transaction = null;
        List<User> allUsers = new ArrayList<>();

        try {
            ses = sf.getCurrentSession();
            transaction = ses.beginTransaction();
            allUsers = ses.createQuery("from User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            ses.close();
            sf.close();
        }

        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sf = Util.getSessionFactoryHibernate();
        Session ses = null;
        Transaction transaction = null;

        try {
            ses = sf.getCurrentSession();
            transaction = ses.beginTransaction();
            ses.createQuery("delete User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            ses.close();
            sf.close();
        }
    }
}
