import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import core.AdminToken;

public class AdminClientRMI {

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
			IRemoteAdminHandler svr = (IRemoteAdminHandler) registry.lookup("ServeurRMI");
			System.out.println(svr);			
			if (svr instanceof IRemoteAdminHandler) {				
				//BAD PSK
				svr.interruptThread(new AdminToken("rtCCTYgssdw?"), 0);
				
				//GOOD PSK BAD INDEX
				svr.interruptThread(new AdminToken("rtCCTYgeUV&aP5w?"), 12);
				
				//GOOD PSK GOOD INDEX
				svr.interruptThread(new AdminToken("rtCCTYgeUV&aP5w?"), 0);
				
			}				
		} catch (Exception e) {		
			System.out.println(e.getMessage());
			e.printStackTrace();
		
		}
	}	
}

