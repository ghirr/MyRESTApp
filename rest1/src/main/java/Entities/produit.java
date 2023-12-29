package Entities;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class produit {
	
	private int codeProduit;
	private String designation;
	private double prixUnitaire;
	private double quantiteEnStock;
	private categorie cat;
	private int categorieId;
	
	public produit() {};
	
	public produit(int codeProduit, String designation, Double prixUnitaire, double quantiteEnStock,int c) {
		this.codeProduit = codeProduit;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.quantiteEnStock = quantiteEnStock;
		this.categorieId=c;
	}
	

	public int getCodeProduit() {
		return codeProduit;
	}

	public void setCodeProduit(int codeProduit) {
		this.codeProduit = codeProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}


	public double getQuantiteEnStock() {
		return quantiteEnStock;
	}


	public void setQuantiteEnStock(double quantiteEnStock) {
		this.quantiteEnStock = quantiteEnStock;
	}


	public int getCategorieId() {
		return categorieId;
	}


	public void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
	}


	
	

	

}
