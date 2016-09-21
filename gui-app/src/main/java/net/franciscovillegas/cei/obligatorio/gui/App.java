package net.franciscovillegas.cei.obligatorio.gui;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import net.franciscovillegas.cei.obligatorio.common.Observer;
import net.franciscovillegas.cei.obligatorio.common.Server;

/**
 * Hello world!
 *
 */
public class App extends UnicastRemoteObject implements Observer {
	
	private Server server;
	
	public App() throws RemoteException, NotBoundException {
		System.out.println(System.currentTimeMillis());
		System.setProperty("java.security.policy","file:///Users/fvillegas/Projects/cei/dda2016/rmi-application/java.policy");
		System.out.println("Hello World!");
		Registry registry = LocateRegistry.getRegistry(1099);
		this.server = (Server) registry.lookup("server");
		String response = server.sayHello();
		System.out.println("response: " + response);
		server.addObserver(this);
		//Jugador j = server.login("pepe");
	}
	
	public void sendMessage(String message) throws RemoteException {
		this.server.sendMessage(message);
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException {
		App app = new App();
		app.sendMessage("hola");
	}

	public void notify(String messaje) {
		System.out.println(messaje);
	}
}
