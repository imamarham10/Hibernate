import java.util.Scanner;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.*;

public class UserDB {
	public static void main(String[] args) {
		System.out.println("Welcome to Employee Management System");
		System.out.println("\n\n1. Insert an user");
		System.out.println("2. Update an user");
		System.out.println("3. Delete an user by Id");
		System.out.println("4. Get records of all employees");
		System.out.println("5. Find an user by Id");
		System.out.println("6. Find an user by name");
		System.out.println("7. Find an user by phone");
		System.out.println("8. Find an user by email");
		System.out.println("9. Verify Employee with Id & Password");
		System.out.println("10. Find all employees phone no.");
		System.out.println("11. Find all employees email");
		System.out.println("12. Verify User with email & Password");
		System.out.println("13. Verify user with Email & password");
		System.out.println("\n\n\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice");
		int n = sc.nextInt();
		switch(n) {
		case 1: insertUser();
		break;
		case 2: updateUser();
		break;
		case 3: deleteUserById();
		break;
		case 4: getAllUsers();
		break;
		case 5: findUserById();
		break;
		case 6: getUserByName();
		break;		
		case 7: getUserByPhone();
		break;
		case 8: getEmployeesByEmail();
		break;
		case 9: verifyUserByIdAndPassword();
		break;
		case 10: fetchAllUserPhone();
		break;
		case 11: fetchAllUserEmail();
		break;
		case 12: verifyUserByEmailAndPassword();
		break;
		case 13: verifyUserByPhoneAndPassword();
		break;
		default: System.out.println("Wrong input");
		break;
		}
		sc.close();
	}

	private static void findUserById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the user id to fetch:");
		int id = sc.nextInt();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<User> q = s.createQuery("select u from User u where id=?1");
		q.setParameter(1, id);
		User u = q.getSingleResult();
		if(u!=null) {
			System.out.println("\n\n\n\n");
			System.out.println("User ID: "+u.getId());
			System.out.println("User Name: "+u.getName());
			System.out.println("User Phone: "+u.getPhone());
			System.out.println("User Email: "+u.getEmail());
			System.out.println("============================================");
		}else {
			System.out.println("---------------------------------------------------");
			System.out.println("No record found with such name!");
		}
		// TODO Auto-generated method stub

	}

	private static void verifyUserByEmailAndPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the user email:");
		String email = sc.next();
		System.out.println("Enter the user password");
		String password = sc.next();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<User> q = s.createQuery("select u from User u where email=?1 and password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			User u = q.getSingleResult();
			if(u!=null) {
				System.out.println("\n\n\n\n");
				System.out.println("Verified!\n");
				System.out.println("User ID: "+u.getId());
				System.out.println("User Name: "+u.getName());
				System.out.println("User Phone: "+u.getPhone());
				System.out.println("User Email: "+u.getEmail());
				System.out.println("============================================");
			}
		}catch(NoResultException exp) {
			System.out.println("---------------------------------------------------");
			System.out.println("Invalid credentials!");
		}
		// TODO Auto-generated method stub

	}

	private static void fetchAllUserEmail() {
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<String> q = s.createQuery("select u.email from User u");
		List<String> userList = q.getResultList();
		if(userList.size()>0) {
			System.out.println("\n\n\n\n");
			for(String u: userList) {
				System.out.println(u);	
			}
		}else {
			System.out.println("No records found!");
		}
		// TODO Auto-generated method stub

	}

	private static void fetchAllUserPhone() {
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<Long> q = s.createQuery("select u.phone from User u");
		List<Long> userList = q.getResultList();
		if(userList.size()>0) {
			System.out.println("\n\n\n\n");
			for(Long u: userList) {
				System.out.println(u);	
			}
		}else {
			System.out.println("No records found!");
		}
		// TODO Auto-generated method stub

	}

	private static void verifyUserByIdAndPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the user id:");
		int id = sc.nextInt();
		System.out.println("Enter the user password");
		String password = sc.next();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<User> q = s.createQuery("select u from User u where id=?1 and password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			User u = q.getSingleResult();
			if(u!=null) {
				System.out.println("\n\n\n\n");
				System.out.println("Verified!\n");
				System.out.println("User ID: "+u.getId());
				System.out.println("User Name: "+u.getName());
				System.out.println("User Phone: "+u.getPhone());
				System.out.println("User Email: "+u.getEmail());
				System.out.println("============================================");
			}
		}catch(NoResultException exp) {
			System.out.println("---------------------------------------------------");
			System.out.println("Invalid credentials!");
		}
		// TODO Auto-generated method stub

	}

	private static void getEmployeesByEmail() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the user name to fetch:");
		String email = sc.next();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<User> q = s.createQuery("select u from User u where email=?1");
		q.setParameter(1, email);
		try {
			User u = q.getSingleResult();
			if(u!=null) {
				System.out.println("\n\n\n\n");
				System.out.println("User ID: "+u.getId());
				System.out.println("User Name: "+u.getName());
				System.out.println("User Phone: "+u.getPhone());
				System.out.println("User Email: "+u.getEmail());
				System.out.println("============================================");
			}
		}catch(NoResultException exp) {
			System.out.println("---------------------------------------------------");
			System.out.println("No record found with such name!");
		}
		// TODO Auto-generated method stub

	}

	private static void getUserByPhone() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the user phone to fetch:");
		long phone = sc.nextLong();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<User> q = s.createQuery("select u from User u where phone=?1");
		q.setParameter(1, phone);
		try {
			User u = q.getSingleResult();
			if(u!=null) {
				System.out.println("\n\n\n\n");
				System.out.println("User ID: "+u.getId());
				System.out.println("User Name: "+u.getName());
				System.out.println("User Phone: "+u.getPhone());
				System.out.println("User Email: "+u.getEmail());
				System.out.println("============================================");
			}
		}catch(NoResultException exp) {
			System.out.println("---------------------------------------------------");
			System.out.println("No record found with such name!");
		}
		// TODO Auto-generated method stub

	}

	private static void verifyUserByPhoneAndPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the user phone:");
		long phone = sc.nextLong();
		System.out.println("Enter the user password");
		String password = sc.next();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<User> q = s.createQuery("select u from User u where phone=?1 and password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			User u = q.getSingleResult();
			if(u!=null) {
				System.out.println("\n\n\n\n");
				System.out.println("Verified!\n");
				System.out.println("User ID: "+u.getId());
				System.out.println("User Name: "+u.getName());
				System.out.println("User Phone: "+u.getPhone());
				System.out.println("User Email: "+u.getEmail());
				System.out.println("============================================");
			}
		}catch(NoResultException exp) {
			System.out.println("---------------------------------------------------");
			System.out.println("Invalid credentials!");
		}
		// TODO Auto-generated method stub

	}

	private static void getUserByName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the user name to fetch:");
		String name = sc.next();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<User> q = s.createQuery("select u from User u where name=?1");
		q.setParameter(1, name);
		try {
			User u = q.getSingleResult();
			if(u!=null) {
				System.out.println("\n\n\n\n");
				System.out.println("User ID: "+u.getId());
				System.out.println("User Name: "+u.getName());
				System.out.println("User Phone: "+u.getPhone());
				System.out.println("User Email: "+u.getEmail());
				System.out.println("============================================");
			}
		}catch(NoResultException exp) {
			System.out.println("---------------------------------------------------");
			System.out.println("No record found with such name!");
		}
		// TODO Auto-generated method stub

	}

	private static void getAllUsers() {
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<User> q = s.createQuery("select u from User u");
		List<User> userList = q.getResultList();
		if(userList.size()>0) {
			System.out.println("\n\n\n\n");
			for(User u: userList) {
				System.out.println("User ID: "+u.getId());
				System.out.println("User Name: "+u.getName());
				System.out.println("User Phone: "+u.getPhone());
				System.out.println("User Email: "+u.getEmail());
				System.out.println("============================================");
			}
		}else {
			System.out.println("No records found!");
		}
		// TODO Auto-generated method stub

	}

	private static void deleteUserById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the user id to update:");
		int id = sc.nextInt();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		User user = s.get(User.class, id);
		if(user!=null) {
			s.delete(user);
			t.commit();
		}else {
			System.err.println("Invalid Id");
		}
		sc.close();
		// TODO Auto-generated method stub

	}

	private static void updateUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the user id to update:");
		int id = sc.nextInt();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		User u = s.get(User.class, id);
		if(u!=null) {
			System.out.println("Enter name: ");
			String name = sc.next();
			System.out.println("Enter email: ");
			String email = sc.next();
			System.out.println("Enter phone number: ");
			long phone = sc.nextLong();
			System.out.println("Enter password: ");
			String password = sc.next();

			u.setName(name);
			u.setEmail(email);
			u.setPhone(phone);
			u.setPassword(password);
			t.commit();

		}else {
			System.err.println("Invalid id!");
		}
		sc.close();
		// TODO Auto-generated method stub

	}

	private static void insertUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name: ");
		String name = sc.next();
		System.out.println("Enter email: ");
		String email = sc.next();
		System.out.println("Enter phone number: ");
		long phone = sc.nextLong();
		System.out.println("Enter password: ");
		String password = sc.next();

		User u = new User();
		u.setName(name);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(password);

		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session s = factory.openSession();
		int id = (Integer)s.save(u);
		Transaction t = s.beginTransaction();
		t.commit();
		System.out.println("User added with id: "+id);
		sc.close();
		// TODO Auto-generated method stub

	}
}
