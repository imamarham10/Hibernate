import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SaveEmployee {
	public static void main(String[] args) {
		Employee e = new Employee();
		e.setId(3);
		e.setName("Arham");
		e.setPhone(985610105);
		e.setDesignation("Software Engineer");
		e.setSalary(56000);
		e.setPassword("Arham@123");
		

		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session s = factory.openSession(); 
		int id = (Integer)s.save(e);

		s.delete(e);
		Transaction t = s.beginTransaction();
		t.commit();
//		System.out.println("Employee saved with id: " + id);
		
		
		
		
	}
}
