package Dao;

import java.util.List;



import Entities.produit;

public interface IProduitDao {
	public produit save(produit p);
	public  List<produit>  produitsParMC(String  mc);
	/*public List<produit> Allproduits();
	public produit getProduit(String Réf); 
	public  produit  updateProduit(produit  p); 
	public  void  deleteProduit(String Réf);

}
			interface 	ConnectionDao {
	public model save(model user);
	public model getUser(String m, String p);*/

}
