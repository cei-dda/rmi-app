package net.franciscovillegas.cei.obligatorio.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {

	public String sayHello() throws RemoteException;
	
}
