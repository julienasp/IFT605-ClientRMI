import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import core.AdminToken;
import core.BasicEquation;
import core.MultiplicativeEquation;
import core.SummativeEquation;


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
				
			Registry registry = LocateRegistry.getRegistry("localhost");
			IRemoteEquation svr = (IRemoteEquation) registry.lookup("ServeurRMI");
			IRemoteAdminHandler svr2 = (IRemoteAdminHandler) registry.lookup("ServeurRMI");
			System.out.println(svr);			
			if (svr instanceof IRemoteEquation) {				
				BasicEquation be = new BasicEquation(2,2);
				SummativeEquation se = new SummativeEquation(be,be);
				SummativeEquation se2 = new SummativeEquation(se,se);
				
				MultiplicativeEquation me = new MultiplicativeEquation(be,se);
				
				be.printUserReadable();
				System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(be, 1)));
				
				se.printUserReadable();
				System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(se, 1)));
				
				se2.printUserReadable();
				System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(se2, 1)));
				
				me.printUserReadable();
				System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(me, 1)));
				
				svr2.interruptThread(new AdminToken("rtCCTYgssdw?"), "dfdfd");
			}				
		} catch (Exception e) {		
			System.out.println(e.getMessage());
			e.printStackTrace();
		
		}
	}

}
