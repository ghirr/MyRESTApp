package Entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class categorie {
	
	private int codeCategorie;
	private String designation;
	private ArrayList<produit> productList;
	
	
	public categorie() {};
	public categorie(int codeCategorie, String designation) {
		super();
		this.codeCategorie = codeCategorie;
		this.designation = designation;
		this.productList = new ArrayList<>();
	}
	public int getCodeCategorie() {
		return codeCategorie;
	}
	public void setCodeCategorie(int codeCategorie) {
		this.codeCategorie = codeCategorie;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	

}
