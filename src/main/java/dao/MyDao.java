package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mysql.cj.Query;

import dto.Customer;

public class MyDao {
	EntityManagerFactory e = Persistence.createEntityManagerFactory("dev");
	EntityManager m = e.createEntityManager();
	EntityTransaction t = m.getTransaction();

	public void save(Customer customer) {
		t.begin();
		m.persist(customer);
		t.commit();
	}

	public Customer fetchByemail(String email) {

		List<Customer> list = m.createQuery("select x from Customer x where email=?1").setParameter(1, email)
				.getResultList();
		if (list.isEmpty()) {
			return null;
		} else
			return list.get(0);
	}

	public Customer fetchBymobile(long mobile) {
		List<Customer> list = m.createQuery("select x from Customer x where mobile=?1").setParameter(1, mobile)
				.getResultList();
		if (list.isEmpty()) {
			return null;
		} else
			return list.get(0);
	}
}
