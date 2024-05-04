package demoJDBC;

import java.util.ArrayList;
import java.util.List;

import JDBCProjet.beans.Client;
import JDBCProjet.connexion.Connexion;
import JDBCProjet.service.ClientService;

public class Test {

	public static void main(String[] args) {
	
		Connexion.getConn();
		ClientService c = new ClientService();
		c.create(new Client("sami","rami"));
		List<Client>l=c.findAll();
//		for(Client lc:l)
//		{
//			System.out.println(lc.toString());
//		}
		Client c1=c.findById(1);
		System.out.println(c1.toString());
		
//		c.delete(new Client(5,"sami","ali"));
		c.update(new Client(6,"arij","mohamed"));
		
	}
	

}
