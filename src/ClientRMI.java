import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;

import core.AdminToken;
import core.BasicEquation;
import core.Equation;
import core.EquationsProvider;
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
				BasicEquation be = new BasicEquation(25,2385);
				SummativeEquation se = new SummativeEquation(be,be);
				SummativeEquation se2 = new SummativeEquation(se,se);
				
				MultiplicativeEquation me = new MultiplicativeEquation(be,se);
				
				be.printUserReadable();
				System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(be, 2)));
				
				se.printUserReadable();
				System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(se, 5)));
				
				se2.printUserReadable();
				System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(se2, 6)));
				
				me.printUserReadable();
				System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(me, 3)));
				
				Vector<Equation> list = new EquationsProvider().getList();
				
				for(Equation e : list){
					e.printUserReadable();					
					System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(e, 1)));
				}		
				
				
				//BAD PSK
				svr2.interruptThread(new AdminToken("rtCCTYgssdw?"), 0);
				
				//GOOD PSK BAD INDEX
				svr2.interruptThread(new AdminToken("rtCCTYgeUV&aP5w?"), 12);
				
				//GOOD PSK GOOD INDEX
				svr2.interruptThread(new AdminToken("rtCCTYgeUV&aP5w?"), 0);
			}				
		} catch (Exception e) {		
			System.out.println(e.getMessage());
			e.printStackTrace();
		
		}
	}

}
