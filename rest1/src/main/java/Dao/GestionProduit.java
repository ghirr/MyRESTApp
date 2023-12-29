package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.categorie;
import Entities.produit;

public class GestionProduit {
private ArrayList<produit> listeProduit;

public GestionProduit() {
	listeProduit=new ArrayList<>();
}



public static boolean save(produit p) {
	Connection conn=SingletonConnection.getConnection();
	String query = "INSERT INTO produits (designation, quantiteEnStock, prixUnitaire,categorie_id) VALUES (?, ?, ?,?)";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, p.getDesignation());
        ps.setDouble(2, p.getQuantiteEnStock());
        ps.setDouble(3, p.getPrixUnitaire());
        ps.setInt(4, p.getCategorieId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
	}


public static List<produit> lister() {
	// TODO Auto-generated method stub
	Connection conn = SingletonConnection.getConnection();
	List<produit> list = new ArrayList<>();
	try {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM produits ;");
		ResultSet set = ps.executeQuery();
		produit p;
		while(set.next()) {
			list.add(new produit(set.getInt("codeProduit"),set.getString("designation"),set.getDouble("prixUnitaire"),
					set.getDouble("quantiteEnStock"),set.getInt("categorie_id")));
			
		}
		ps.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
}
/*
@Override
public List<produit> produitsParMC(String mc) {
	// TODO Auto-generated method stub
	Connection conn = SingletonConnection.getConnection();
	List<produit> list = new ArrayList<>();
	try {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM produits WHERE nom_Produit LIKE ?;");
		ps.setString(1,"%"+mc+"%");
		ResultSet set = ps.executeQuery();
		produit p;
		while(set.next()) {
			list.add(new produit(set.getString("id_Produit"),set.getString("nom_Produit"),Float.parseFloat(set.getString("prix")),
					set.getString("fournisseur")));
			
		}
		ps.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
}
*/

public static produit getProduit(int code) {
	Connection conn = SingletonConnection.getConnection();
	produit p = null;
	try {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM produits WHERE codeProduit=?;");
		ps.setInt(1,code);
		ResultSet set = ps.executeQuery();
		if(set.next()) {
			p =new produit(set.getInt("codeProduit"),set.getString("designation"),set.getDouble("prixUnitaire"),
					set.getDouble("quantiteEnStock"),set.getInt("categorie_id"));
			return p;
		}
		ps.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return p;
}

public static produit updateProduit(produit p) {
	// TODO Auto-generated method stub
	Connection conn = SingletonConnection.getConnection();
	try {
		PreparedStatement ps = conn.prepareStatement("UPDATE produits  SET designation = ?, prixUnitaire = ?, quantiteEnStock = ?,categorie_id=? WHERE codeProduit = ?;");
		ps.setString(1,p.getDesignation());
		ps.setDouble(2,p.getPrixUnitaire());
		ps.setDouble(3,p.getQuantiteEnStock());
		ps.setInt(4,p.getCategorieId());
		ps.setInt(5,p.getCodeProduit());
		int update = ps.executeUpdate();
		if(update > 0)
			System.out.println("update avec succees");
		else System.out.println("failed to update");
		ps.close();
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
	return p;
}

public static void deleteProduit(int code) {
	// TODO Auto-generated method stub
	Connection conn = SingletonConnection.getConnection();
	try {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM produits  WHERE codeProduit = ?;");
		ps.setInt(1,code);
		int update = ps.executeUpdate();
		if(update > 0)
			System.out.println("delete avec succees");
		else 
			System.out.println("failed to delete");
		ps.close();
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
}
public static List<produit> listerParCategorie(String designation) {
	// TODO Auto-generated method stub
		Connection conn = SingletonConnection.getConnection();
		List<produit> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM produits WHERE categorie_id = (SELECT codeCategorie"
					+ "    FROM categories"
					+ "    WHERE designation = ? ) ;");
			ps.setString(1, designation);
			ResultSet set = ps.executeQuery();
			produit p;
			while(set.next()) {
				list.add(new produit(set.getInt("codeProduit"),set.getString("designation"),set.getDouble("prixUnitaire"),
						set.getDouble("quantiteEnStock"),set.getInt("categorie_id")));
				
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
}

}