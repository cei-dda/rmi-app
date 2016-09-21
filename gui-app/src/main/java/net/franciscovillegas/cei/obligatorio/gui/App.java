package net.franciscovillegas.cei.obligatorio.gui;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import net.franciscovillegas.cei.obligatorio.common.Server;

/**
 * Hello world!
 *
 */
public class App {
	
	private Server server;
	
	public App() throws RemoteException, NotBoundException {
		System.out.println(System.currentTimeMillis());
		System.setProperty("java.security.policy","file:///Users/fvillegas/Projects/cei/dda2016/rmi-application/java.policy");
		System.out.println("Hello World!");
		Registry registry = LocateRegistry.getRegistry(1099);
		this.server = (Server) registry.lookup("server");
		String response = server.sayHello();
		System.out.println("response: " + response);
		
		//Jugador j = server.login("pepe");
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException {
		new App();
	}
}
