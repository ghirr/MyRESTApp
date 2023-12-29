	package test;
	
	import java.util.List;


import Dao.GestionProduit;
import Dao.GestionCategories;
import Entities.categorie;
import Entities.produit;
import Metier.metierProduit;
	
	public class testMetier {
	
		public static void main(String[] args) {
			/*             Gestion Produit                 */
			/* produit produit = new produit(80,"acer",69.69f,"zayedh");
			
			System.out.println(" ---------------------ajouterProduit-----------------------------");
			 GestionProduit.save(produit);
		        
			System.out.println("");
			
			
			 // Test the getProduitById method
			System.out.println(" ---------------------getProduitById-----------------------------");
		       
			 produit produitById = GestionProduit.getProduit("52");
		      if( produitById==null){
		    	 System.out.println("Erreur "); 
		      }
		    	  
		      else { System.out.println(produitById.toString() );
		      }
		      
		      produit p1 = new produit(53,"achref",16.69f,"DEL");
		   System.out.println("");
		   System.out.println(" ---------------------updateProduit-----------------------------");
		        // Test the updateProduit method
		      
		       /* GestionProduit.updateProduit(p1);*/
		        
		      
				System.out.println("");
		      System.out.println(" ---------------------deleteProduit-----------------------------");
		     

		      
		     List<produit> list= GestionProduit.lister();
		     for (produit p:list) {
		    	System.out.println(p.getDesignation()); 
		    	System.out.println(p.getPrixUnitaire()); 
		     }
		     /*
		     
		     categorie c=new categorie(5,"airpods") ;
		     System.out.println(GestionCategories.save(c)); 
		    /*   // Test the deleteProduit method
		        GestionProduit.deleteProduit("DELLL");
		        */
		     /* List<produit> produits=GestionProduit.Allproduits();
		      System.out.println(produits.toString());
		      System.out.println(produits.size());
		      System.out.println(produits);*/
		     /* model user=new model("admin","islem24762048@gmail.com","islem");
		      GestionUser g=new GestionUser();
		      g.save(user);*/

		    }
		      
	
		}
	
	
