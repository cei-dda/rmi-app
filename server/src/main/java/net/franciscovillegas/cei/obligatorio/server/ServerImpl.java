package net.franciscovillegas.cei.obligatorio.server;

import java.rmi.RMISecurityManager;

import net.franciscovillegas.cei.obligatorio.common.Server;

public class ServerImpl implements Server {

	@SuppressWarnings("deprecation")
	public ServerImpl() {
		System.setProperty("java.security.policy","file:///Users/fvillegas/projects/cei/java.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
	}
	
}
