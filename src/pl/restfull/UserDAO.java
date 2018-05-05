package pl.restfull;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.restfull.errors.DataNotFoundException;

public class UserDAO {

	static List<User> users = new ArrayList<>();

	public static List<User> getUsers() {

		// users.add(new User(1, "John", "Doe", "john11@gmail.com"));
		// users.add(new User(2, "Adam", "Kowalski", "adam1984@poczta.pl"));

		return users;

	}

	public static void addUser(User user) {
		users.add(user);
	}

	public static void updateUserEmail(int id, String email) {
		users.get(id).setEmail(email);
	}

	public static void deleteUser(int id) {

		users.remove(id);

	}

	public static List<User> getAllUsers() {

		SessionFactory sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(User.class)
			.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();

		try {

			users = session.createCriteria(User.class).list();

			session.getTransaction().commit();

		} catch (Exception ex) {

			// ..

		} finally {

			session.close();

			sessionFactory.close();

		}

		return users;

	}

	public static void addUserHibernate(String name, String password, String email) {
		
		SessionFactory sessionFactory = new Configuration()
		.configure("hibernate.cfg.xml")
		.addAnnotatedClass(User.class)
		.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();
		
		try {

			User user = new User();
			
			user.setName(name);
			user.setPassword(password);
			user.setEmail(email);
			
			session.save(user);
			
			session.getTransaction().commit();

		} catch (Exception ex) {

			// ..

		} finally {

			session.close();

			sessionFactory.close();

		}
		
	}

	public static void updateUserEmailHibernate(int id, String email) {
		
		SessionFactory sessionFactory = new Configuration()
		.configure("hibernate.cfg.xml")
		.addAnnotatedClass(User.class)
		.buildSessionFactory();

		Session session = sessionFactory.openSession();				// inna metoda niz dotychczas !!
		
		try {

			User user = new User();
			
			user = (User) session.get(User.class, id);
			
			user.setEmail(email);
			
			session.saveOrUpdate(user);				// inna metoda niz dotychczas !!
			
			session.beginTransaction();
			
			session.getTransaction().commit();

		} catch (Exception ex) {

			// ..

		} finally {

			session.close();

			sessionFactory.close();

		}
		
	}

	public static void deleteUserHibernate(int id) {
		
		SessionFactory sessionFactory = new Configuration()
		.configure("hibernate.cfg.xml")
		.addAnnotatedClass(User.class)
		.buildSessionFactory();

		Session session = sessionFactory.openSession();				// inna metoda niz dotychczas !!
		
		try {

			User user = new User();
			
			user = (User) session.get(User.class, id);
			
			session.delete(user);				// inna metoda niz dotychczas !!
			
			session.beginTransaction();
			
			session.getTransaction().commit();

		} catch (Exception ex) {

			// ..

		} finally {

			session.close();

			sessionFactory.close();

		}
		
	}

	public static List<User> getUser(int id) {
		
		SessionFactory sessionFactory = new Configuration()
		.configure("hibernate.cfg.xml")
		.addAnnotatedClass(User.class)
		.buildSessionFactory();

		Session session = sessionFactory.openSession();				// inna metoda niz dotychczas !!
		
		List<User> result = new ArrayList<>();
		
		User user = new User();
		
		try {
			
			user = (User) session.get(User.class, id);
			
			result.add(user);
			
			session.beginTransaction();
			
			session.getTransaction().commit();

		} catch (Exception ex) {

			// ..

		} finally {

			session.close();

			sessionFactory.close();

		}
		
		if (user == null) {
			throw new DataNotFoundException("User with id " + id + " not found ! ");
		}
		
		return result;
		
	}

}
