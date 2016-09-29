
import java.rmi.Naming;
import java.rmi.Remote;

import core.BasicEquation;
//import core.MultiplicativeEquation;
//import core.SummativeEquation;


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
				
			Remote r = Naming.lookup("rmi://localhost:1099/ServeurRMI");
			System.out.println(r);			
			if (r instanceof IRemoteEquation) {				
				BasicEquation be = new BasicEquation(1,2);
				//SummativeEquation se = new SummativeEquation(be,be);
				//SummativeEquation se2 = new SummativeEquation(se,se);
				
				//MultiplicativeEquation me = new MultiplicativeEquation(be,se);
				
				be.printUserReadable();
				//System.out.println("Client: La valeur pour l'équation ci-dessous avec un x=1 est: " + Double.toString(svr.getEquationValue(be, 1)));
			}				
		} catch (Exception e) {		
			System.out.println(e.getMessage());	
		
		}
	}

}
