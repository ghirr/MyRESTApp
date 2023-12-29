package Controlers;



import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import Entities.categorie;
import Entities.produit;
import Metier.gazouz;
import Metier.metierProduit;

@Path("/api")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class service {
	@Path("/categories")
	@POST
	
	public categorie ajouter(categorie c) {
		if( gazouz.ajouterCategorie(c)) {
			return c;
		}
		return null;
	}
	@Path("/categories")
	@GET
	
	public List<categorie> listerCategories() {
		return gazouz.listerCategorie();
	}
	@Path("/categories/{designation}")
	@GET
	
	public categorie getCategorie(@PathParam("designation") String designation) {
		return gazouz.getCategorieByDesignation(designation);
	}
	
	@Path("/categories/{designation}")
	@DELETE
	
	public String deleteCategorie(@PathParam("designation") String designation) {
		return gazouz.deleteCategory(designation);
	}
	
	@Path("/categories")
	@PUT
	
	public categorie modifier(categorie c) {
		return gazouz.modifierCategorie(c);
	}
	
	@Path("/produits")
	@POST
	
	public produit ajouter(produit p) {
		if( metierProduit.ajouterProduit(p)) {
			return p;
		}
		return null;
	}
	
	@Path("/produits")
	@GET
	
	public List<produit> lister() {
		return metierProduit.listerProduit();
	}
	
	@Path("/produits/{codeProduit}")
	@GET
	
	public produit getCategorie(@PathParam("codeProduit") int code) {
		return metierProduit.getProduit(code);
	}

	@Path("/produits/{codeProduit}")
	@DELETE
	
	public void delete(@PathParam("codeProduit") int code) {
		 metierProduit.deleteProduit(code);
	}
	
	@Path("/produits")
	@PUT
	
	public produit update(produit p) {
		return metierProduit.updateProduit(p);
	}
	
	@Path("/produits/cats/{designation}")
	@GET
	
	public List<produit> listerParCategory(@PathParam("designation") String d) {
		return metierProduit.listerProduitParCategorie(d);
	}

}
