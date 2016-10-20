package net.franciscovillegas.cei.obligatorio.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import net.franciscovillegas.cei.obligatorio.common.Server;
import net.franciscovillegas.cei.obligatorio.server.entities.Address;
import net.franciscovillegas.cei.obligatorio.server.entities.Task;
import net.franciscovillegas.cei.obligatorio.server.entities.User;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		EntityManager em = null;
		try {
			System.setProperty("java.security.policy", "file:///Users/fvillegas/Projects/cei/java.policy");
			LocateRegistry.createRegistry(1099);
			ServerImpl obj = new ServerImpl();
			Server stub = (Server) UnicastRemoteObject.exportObject(obj, 0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry(1099);
			registry.bind("server", stub);

			// JDBC
			System.out.println("Server ready");
			// new Memento();

			// JPA
			EntityManagerFactory emf;
			emf = Persistence.createEntityManagerFactory("jpaDS");
			em = (EntityManager) emf.createEntityManager();

			em.getTransaction().begin();
			User user = new User();
			Address address = new Address("la direccion");
			em.persist(address);
			em.persist(user);

			user.setAddress(address);

			em.getTransaction().commit();

			User u = em.find(User.class, user.getId());
			// u esta manejado por el EM
			em.close();
			// u deja de estar manejado por el EM
			em = (EntityManager) emf.createEntityManager();
			em.getTransaction().begin();
			u = em.merge(u);
			// u vuelve a estar manejado por un EM
			u.setName("algo");
			System.out.println(u.getTareas());
			if (u.getTareas() == null) {
				u.setTareas(new ArrayList<Task>());
			}
			Task task = new Task();
			u.getTareas().add(task);
			em.persist(task);
			em.getTransaction().commit();
			
			u = em.find(User.class, user.getId());

			Query query = em.createNamedQuery("findUserByName");
			query.setParameter("name", "algo");
			u = (User) query.getSingleResult();
			u.getTareas();
			System.out.println("---->2");
			
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		// System.exit(0);
	}
}
