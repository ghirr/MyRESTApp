package Metier;

import java.util.List;

import Dao.GestionProduit;
import Entities.produit;

public class metierProduit {
	
	public static boolean ajouterProduit(produit p) {
		
		return GestionProduit.save(p);
		
	}
	 public static List<produit> listerProduit(){
		return GestionProduit.lister();
	} 
	 
	public static produit getProduit(int code) {
		return GestionProduit.getProduit(code);
	}
	
	public static void deleteProduit(int code) {
		 GestionProduit.deleteProduit(code);
	}
	public static produit updateProduit(produit p) {
		return GestionProduit.updateProduit(p);
	}
	public static List<produit> listerProduitParCategorie(String c){
		return GestionProduit.listerParCategorie(c);
	}

}
