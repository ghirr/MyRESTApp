package Metier;

import java.util.List;

import Dao.GestionCategories;
import Dao.GestionProduit;
import Entities.categorie;
import Entities.produit;

public class gazouz {
	
	
public static boolean ajouterCategorie(categorie c) {
		
		return GestionCategories.save(c);
		
	}
	public static List<categorie> listerCategorie(){
		return GestionCategories.Allcategories();
	}

	public static categorie getCategorieByDesignation(String designation){
		return GestionCategories.getCategorie(designation);
	}
	
	public static String deleteCategory(String designation) {
		return GestionCategories.deleteCategorie(designation);
	}
	
	public static categorie modifierCategorie(categorie c) {
		return GestionCategories.updateProduit(c);
	}
	

}
