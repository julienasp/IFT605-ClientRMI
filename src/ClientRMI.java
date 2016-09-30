import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.LinkedList;

import core.Equation;


public class ClientRMI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (System.getSecurityManager() == null) {			
			System.setSecurityManager(new SecurityManager());			
		}
		try {		
				
			IRemoteEquation svr = (IRemoteEquation)Naming.lookup("rmi://localhost:1099/ServeurRMI");
			System.out.println(svr);			
			if (svr instanceof IRemoteEquation) {
				LinkedList<Equation> list = new EquationsProvider<Equation>().getList();
				
				list.stream().forEach(e -> {
					e.printUserReadable();
					try {
						System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(e, 1)));
					} catch (RemoteException e1) {
						System.out.println("Remote communication error");
						e1.printStackTrace();
					}
				});
			}				
		} catch (Exception e) {		
			System.out.println(e.getMessage());
			e.printStackTrace();
		
		}
	}

}
