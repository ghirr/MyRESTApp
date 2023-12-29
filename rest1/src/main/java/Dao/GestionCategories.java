package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.categorie;
import Entities.produit;

public class GestionCategories {
	
	
	
	
	public static boolean save(categorie c) {
		Connection conn=SingletonConnection.getConnection();
		try {

		PreparedStatement ps= conn.prepareStatement("INSERT INTO categories (designation) VALUES(?);");

		ps.setString(1, c.getDesignation());
		ps.executeUpdate();

		ps.close();
		} catch (SQLException e)
		{
		e.printStackTrace();
		return false;
	}
		return true;}
	
	
	public static List<categorie> Allcategories() {
		// TODO Auto-generated method stub
		Connection conn = SingletonConnection.getConnection();
		List<categorie> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM categories;");
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				list.add(new categorie(set.getInt("codeCategorie"),set.getString("designation")));
				
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static categorie getCategorie(String designation) {
		Connection conn = SingletonConnection.getConnection();
		categorie c = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM categories WHERE designation=?;");
			ps.setString(1,designation);
			ResultSet set = ps.executeQuery();
			if(set.next()) {
				c = new categorie(set.getInt("codeCategorie"),set.getString("designation"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static String deleteCategorie(String Réf) {
		// TODO Auto-generated method stub
		Connection conn = SingletonConnection.getConnection();
		String s = null;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM categories WHERE designation=?;");
			ps.setString(1,Réf);
			int update = ps.executeUpdate();
			if(update > 0)
				 s="delete avec succées";
			else 
				s="failed to delete";
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return s;
	}
	
	public static categorie updateProduit(categorie c) {
		// TODO Auto-generated method stub
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE categories  SET designation=? WHERE codeCategorie = ?;");
			ps.setString(1,c.getDesignation());
			ps.setInt(2,c.getCodeCategorie());
			int update = ps.executeUpdate();
			if(update > 0)
				System.out.println("update avec succées");
			else System.out.println("failed to update");
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return c;
	}

}
