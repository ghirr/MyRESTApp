package clientfront;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import Entities.produit;

public class client {

    public static void main(String[] args) {
        Client client = Client.create(new DefaultClientConfig());
       
       //write your own url
        URI uri = UriBuilder.fromUri("http://localhost:8090/rest1/").build();
       
       
        ClientResponse reponse = client.resource(uri).path("api").path("/produits").get(ClientResponse.class);
        String bodyRepHttp = reponse.getEntity(String.class);

       
        ObjectMapper mapper = new ObjectMapper();
            JsonNode produits;
            JsonNode productsNode;
            try {
                produits = mapper.readTree(bodyRepHttp);
                productsNode = produits.get("produit");
                System.out.println(productsNode);
               
                //transforming the productsNode into type String to use it in the readValue()
                String repString = mapper.writeValueAsString(productsNode);
               
                produit[] lesProduits = mapper.readValue(repString, produit[].class);
                for(produit elt:lesProduits) {
                    System.out.println(elt.getDesignation());
                }
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
           
       
       
    }

}