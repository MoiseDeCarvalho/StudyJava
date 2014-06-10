package it.moise.study;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * La classe elenca l'insieme delle schede di rete che sono collegate al pc
 * @author hp envy
 *
 */
public class GetAllNIC {

	public GetAllNIC() {
		
	}

	public static void main(String[] args) {
		try {
			// dammi le NIC presenti
			Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
			if (nics != null){
				while (nics.hasMoreElements()){
					NetworkInterface nic = nics.nextElement();
					System.out.println ("Nome NIC: " + nic.getDisplayName() + "(" + nic.getName() + ")");
					Enumeration<NetworkInterface> sub_nics = nic.getSubInterfaces();
					// sottointerfacce
					if (sub_nics != null){
						for (NetworkInterface sub_nic : Collections.list(sub_nics)){
							System.out.println("\tNome SUB_NIC: " + sub_nic.getDisplayName() + "(" + sub_nic.getName() + ")");
						}
					}
					else
						System.out.println("Non sono presenti sub-interfacce");
					}
				}
			else
				System.out.println("Nessuna interfaccia di rete trovata");
			}
		catch(SocketException ex){ System.out.println(ex.getMessage());		}

	}

}
