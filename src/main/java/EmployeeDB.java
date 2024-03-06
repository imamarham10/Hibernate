import java.util.List;
import java.util.Scanner;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class EmployeeDB {
	static void insertEmployee() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name: ");
		String name = sc.next();
		System.out.println("Enter designation: ");
		String designation = sc.next();
		System.out.println("Enter salary: ");
		double salary = sc.nextDouble();
		System.out.println("Enter phone number: ");
		long phone = sc.nextLong();
		System.out.println("Enter password: ");
		String password = sc.next();

		Employee e = new Employee();
		e.setName(name);
		e.setDesignation(designation);
		e.setSalary(salary);
		e.setPhone(phone);
		e.setPassword(password);

		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session s = factory.openSession();
		int id = (Integer)s.save(e);
		Transaction t = s.beginTransaction();
		t.commit();
		System.out.println("Employee added with id: "+id);
		sc.close();
	}
	static void updateEmployee() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter employee id to be updated: ");
		int id = sc.nextInt();
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		Employee e = s.get(Employee.class, id); //Persistent State
		if(e!=null) {
			System.out.println("Enter name: ");
			String name = sc.next();
			System.out.println("Enter designation: ");
			String designation = sc.next();
			System.out.println("Enter salary: ");
			double salary = sc.nextDouble();
			System.out.println("Enter phone number: ");
			long phone = sc.nextLong();
			System.out.println("Enter password: ");
			String password = sc.next();
			e.setName(name);
			e.setSalary(salary);
			e.setDesignation(designation);
			e.setPassword(password);
			e.setPhone(phone);
			t.commit();
			System.out.println("Employee updated with id: "+id);

		}else {
			System.out.println("Invalid ID");
		}
		sc.close();		
	}
	static void deleteEmployee() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter employee id to be deleted: ");
		int id = sc.nextInt();
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session s = factory.openSession();
		Employee e = s.get(Employee.class, id);
		if(e!=null) {
			s.delete(e);
			Transaction t = s.beginTransaction();
			t.commit();
			System.out.println("Employee deleted with id: " + id);
		}else {
			System.err.println("Invalid ID");
		}
		sc.close();

	}

	static void getEmployee() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the id to view the record");
		int id = sc.nextInt();
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session s = factory.openSession();
		Employee e = s.get(Employee.class, id);
		if(e!=null) {
			System.out.println("---------------------------------------------------");
			System.out.println("Name\tDesignation\t\tSalary");
			System.out.println(e.getName()+"\t"+e.getDesignation()+"\t"+e.getSalary());
		}else {
			System.out.println("---------------------------------------------------");
			System.out.println("No record found with such id!");
		}
		sc.close();
	}

	static void getEmployeesByName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name: ");
		String name = sc.next();
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session s = factory.openSession();

		//		CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
		//		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		//		Root<Employee> root = criteriaQuery.from(Employee.class);
		//		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));
		//		List<Employee> employees = s.createQuery(criteriaQuery).getResultList();
		Query<Employee> q = s.createQuery("select e from Employee e where name = ?1");
		q.setParameter(1, name);
		List<Employee>emps= q.getResultList();
		if(emps.size()>0) {
			System.out.println("\n\n\n\n");
			for(Employee e: emps) {
				System.out.println("Employee ID: "+e.getId());
				System.out.println("Employee Name: "+e.getName());
				System.out.println("Employee Designation: "+e.getDesignation());
				System.out.println("Employee Salary: "+e.getSalary());
				System.out.println("============================================");
			}
		}
		sc.close();
	}

	static void getEmployeesByDesignation() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the designation: ");
		String designation = sc.next();
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session s = factory.openSession();

		Query<Employee> q = s.createQuery("select e from Employee e where designation = ?1");
		q.setParameter(1, designation);
		List<Employee>emps= q.getResultList();
		if(emps.size()>0) {
			System.out.println("\n\n\n\n");
			for(Employee e: emps) {
				System.out.println("Employee ID: "+e.getId());
				System.out.println("Employee Name: "+e.getName());
				System.out.println("Employee Designation: "+e.getDesignation());
				System.out.println("Employee Salary: "+e.getSalary());
				System.out.println("============================================");
			}
		}
		sc.close();
	}

	static void getEmployeesBySalary() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter salary: ");
		double salary = sc.nextDouble();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<Employee> q = s.createQuery("select e from Employee e where salary = ?1");
		q.setParameter(1, salary);
		List<Employee>emps = q.getResultList();
		if(emps.size()>0) {
			System.out.println("\n\n\n\n");
			for(Employee e: emps) {
				System.out.println("Employee ID: "+e.getId());
				System.out.println("Employee Name: "+e.getName());
				System.out.println("Employee Designation: "+e.getDesignation());
				System.out.println("Employee Salary: "+e.getSalary());
				System.out.println("============================================");
			}
		}
		sc.close();
		
	}
	static void getAllEmployees() {
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<Employee> q = s.createQuery("select e from Employee e");
		List<Employee> emps = q.getResultList();
		if(emps.size()>0) {
			System.out.println("\n\n\n\n");
			for(Employee e: emps) {
				System.out.println("Employee ID: "+e.getId());
				System.out.println("Employee Name: "+e.getName());
				System.out.println("Employee Designation: "+e.getDesignation());
				System.out.println("Employee Salary: "+e.getSalary());
				System.out.println("============================================");
			}
		}
	}

	static void verifyEmployeeByPhoneAndPassword() {
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter phone number: ");
		long phone = sc.nextLong();
		System.out.println("Enter password: ");
		String password = sc.next();
		Query<Employee>q = s.createQuery("select e from Employee e where phone=?1 and password =:ps");
		q.setParameter(1, phone);
		q.setParameter("ps", password);
		try {
			Employee e = q.getSingleResult();
			System.out.println("============================================");
			System.out.println("Employee ID: "+e.getId());
			System.out.println("Employee Name: "+e.getName());
			System.out.println("Employee Designation: "+e.getDesignation());
			System.out.println("Employee Salary: "+e.getSalary());

		}catch(NoResultException e) {
			System.err.println("Invalid Credentials!");
		}
	}
	static void verifyEmployeeByIdAndPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter id: ");
		int id = sc.nextInt();
		System.out.println("Enter password: ");
		String password = sc.next();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<Employee>q = s.createQuery("select e from Employee e where id=?1 and password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		
		try {
			Employee e = q.getSingleResult();
			System.out.println("============================================");
			System.out.println("Employee ID: "+e.getId());
			System.out.println("Employee Name: "+e.getName());
			System.out.println("Employee Designation: "+e.getDesignation());
			System.out.println("Employee Salary: "+e.getSalary());
			
		}catch(NoResultException exp) {
			System.err.println("Invalid Credentials");
		}
	}
	static void fetchEmployeesPhone() {
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<Long> q = s.createQuery("select e.phone from Employee e");
		List<Long> emps = q.getResultList();
		if(emps.size()>0) {
			System.out.println("\n\n\n\n");
			for(Long e: emps) {
				System.out.println(e);			}
		}
	}
	
	static void fetchEmployeesName() {
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<String> q = s.createQuery("select e.name from Employee e");
		List<String> emps = q.getResultList();
		if(emps.size()>0) {
			System.out.println("\n\n\n\n");
			for(String e:emps) {
				System.out.println(e);			}
		}
	}
	static void findEmployeeSalaryById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter id: ");
		int id = sc.nextInt();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<Double>q = s.createQuery("select e.salary from Employee e where id=?1");
		q.setParameter(1, id);	
		try {
			double salary = q.getSingleResult();
			System.out.println("============================================");
			System.out.println("Salary of employee with id "+id+" is: " + salary);
			
		}catch(NoResultException exp) {
			System.err.println("Invalid Credentials");
		}
	}
	
	static void findEmployeePhoneBySalary() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Salary: ");
		double salary = sc.nextInt();
		Session s = new Configuration().configure().buildSessionFactory().openSession();
		Query<Long>q = s.createQuery("select e.phone from Employee e where salary=?1");
		q.setParameter(1,salary);	
		try {
			long phone = q.getSingleResult();
			System.out.println("============================================");
			System.out.println("Phone number of employee with salary "+salary+" is: " + phone);
			
		}catch(NoResultException exp) {
			System.err.println("Invalid Credentials");
		}
		sc.close();
	}
	
	
	public static void main(String[] args) {
		System.out.println("Welcome to Employee Management System");
		System.out.println("\n\n1. Insert an employee");
		System.out.println("2. Update an employee");
		System.out.println("3. Delete an employee");
		System.out.println("4. Get records of all employees");
		System.out.println("5. Verify Employee with Phone no. & Password");
		System.out.println("6. Find an employee by name");
		System.out.println("7. Find an employee by designation");
		System.out.println("8. Find an employee by salary");
		System.out.println("9. Verify Employee with Id & Password");
		System.out.println("10. Find all employees phone no.");
		System.out.println("11. Find all employees name");
		System.out.println("12. Find an employee salary with id");
		System.out.println("13. Find an employee phone no. with salary");
		System.out.println("\n\n\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice");
		int n = sc.nextInt();

		switch(n) {
		case 1: insertEmployee();
		break;
		case 2: updateEmployee();
		break;
		case 3: deleteEmployee();
		break;
		case 4: getAllEmployees();
		break;
		case 5: verifyEmployeeByPhoneAndPassword();
		break;
		case 6: getEmployeesByName();
		break;		
		case 7: getEmployeesByDesignation();
		break;
		case 8: getEmployeesBySalary();
		break;
		case 9: verifyEmployeeByIdAndPassword();
		break;
		case 10: fetchEmployeesPhone();
		break;
		case 11: fetchEmployeesName();
		break;
		case 12: findEmployeeSalaryById();
		break;
		case 13: findEmployeePhoneBySalary();
		break;
		default: System.out.println("Wrong input");
		break;
		}
		sc.close();
	}
	/*
	 * 1. Find Employees by name
	 * 2. Find Employee by designation
	 * 3. Find Employees by salary
	 * 4. Verify Employee by ID and password
	 * 5. Fetch the phone numbers from employee table
	 * 6. Fetch the names from the employee table
	 * 7. Fetch the salary from employee table where id=?
	 * 8. Fetch the phone numbers from employee table where salary=?
	 *
	 */
}
