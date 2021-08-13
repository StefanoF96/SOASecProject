package test.soasec.jaxws.service;

import soasec.jaxws.service.ServiceMethodsImpl;
import soasec.jaxws.service.ServiceStub.*;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;



public class MyServiceTest {
	
	
	public static void main(String[] args) throws AxisFault, RemoteException, Exception {
		
		ServiceMethodsImpl.addMessage(1,2,"Jello World");
		Message m = ServiceMethodsImpl.getAllMessages()[0];
		System.out.println(m.toString());

	}

}
