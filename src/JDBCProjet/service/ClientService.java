package JDBCProjet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBCProjet.beans.Client;
import JDBCProjet.connexion.Connexion;
import JDBCProjet.dao.IDao;

public class ClientService implements IDao<Client>{

	@Override
	public boolean create(Client o) {
		try {
			Connection conn=Connexion.getConn();
		    Statement stmt = conn.createStatement();
		    
		    String query = "INSERT INTO CLIENT(nom,prenom) VALUES("+"'"+o.getNom()+"','"+o.getPrenom()+"')";
		    
		    try {
		      stmt.execute(query);
		      return true;
		    }
		    catch (SQLException e) {
		    System.err.println("Error executing query: " +e.getMessage());
		    return false;
		    }
		}
		catch (SQLException e) {
		System.err.println("Error creating SQL statement: "+e.getMessage());
	}
		return false;
		
	}
	@Override
	public List<Client> findAll() {
		try {
			Connection conn=Connexion.getConn();
		    Statement stmt = conn.createStatement();
		    try {
		    	String query = "select * from client";
			    ResultSet rs=stmt.executeQuery(query);
			    List<Client> l=new ArrayList<Client>();
			    while(rs.next())
			    {
			    	l.add(new Client(rs.getInt(1), rs.getString(2),rs.getString(3)));
			    	
			    }
			    return l;
		    }
		    catch (SQLException e) {
		    System.err.println("Error executing query: " +e.getMessage());
		    return null;
		    }
		}
		catch (SQLException e) {
		System.err.println("Error creating SQL statement: "+e.getMessage());
		return null;
	}
		
		
	}

	@Override
	public Client findById(int id) {
		try {
			Connection conn=Connexion.getConn();
		    PreparedStatement stmt = conn.prepareStatement("select * from client where id=?");
		    stmt.setInt(1, id);
		    ResultSet rs=stmt.executeQuery();
		    rs.next();
		    return new Client(rs.getInt(1), rs.getString(2),rs.getString(3));
		    }
		    catch (SQLException e) {
		    System.err.println("Error executing query: " +e.getMessage());
		    return null;
		    }
	}

	@Override
	public boolean delete(Client o) {
		try {
			Connection conn=Connexion.getConn();
		    PreparedStatement stmt = conn.prepareStatement("delete from client where id=?");
		    stmt.setInt(1, o.getId());
		    stmt.executeUpdate();
		    return true;
		    
		    }
		    catch (SQLException e) {
		    System.err.println("Error executing query: " +e.getMessage());
		    return false;
		    }
	}

	@Override
	public boolean update(Client o) {
		try {
			Connection conn=Connexion.getConn();
		    PreparedStatement stmt = conn.prepareStatement("update  client set nom=? where id=?");
		    stmt.setString(1, o.getNom());
		    stmt.setInt(2, o.getId());
		    stmt.executeUpdate();
		    return true;
		    
		    }
		    catch (SQLException e) {
		    System.err.println("Error executing query: " +e.getMessage());
		    return false;
		    }
	}

}
