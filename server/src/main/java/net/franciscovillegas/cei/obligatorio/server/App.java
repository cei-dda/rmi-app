package net.franciscovillegas.cei.obligatorio.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import net.franciscovillegas.cei.obligatorio.common.Server;

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

			System.out.println("Server ready");
			new Memento();
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
