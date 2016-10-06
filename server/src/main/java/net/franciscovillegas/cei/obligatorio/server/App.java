package net.franciscovillegas.cei.obligatorio.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.franciscovillegas.cei.obligatorio.common.Server;
import net.franciscovillegas.cei.obligatorio.server.entities.User;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
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
			new Memento();
			
			//JPA
			EntityManagerFactory emf;
			emf = Persistence.createEntityManagerFactory("jpaDS");
			EntityManager em = (EntityManager) emf.createEntityManager();

			em.getTransaction().begin();
			User user = new User();
			em.persist(user);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
