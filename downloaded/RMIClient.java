package com.dce.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.dce.rmi.rmiservice.IRemote;

public class RMIClient {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry(1099);
			IRemote remoteService = (IRemote) registry.lookup("calc");
			int addRet = remoteService.add(5,8);
			System.out.println("接口服务返回"+addRet);
			
			int subRet = remoteService.sub(15,8);
			System.out.println("接口服务返回"+subRet);
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
