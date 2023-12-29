package clientfront;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.WebResource;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import Entities.categorie;
import Entities.produit;

public class RestClient {

    private static final String BASE_URL = "http://localhost:8090/rest1/api";

    private static final Client client = Client.create(new DefaultClientConfig());

    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri(BASE_URL).build();

        while (true) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
            case 1:
                viewAllProducts(baseUri);
                break;
            case 2:
                createNewProduct(baseUri);
                break;
            case 3:
                updateProduct(baseUri);
                break;
            case 4:
                deleteProduct(baseUri);
                break;
            case 5:
            	listProductsByCategory(baseUri);
            	break;
            case 6:
                viewAllCategories(baseUri);
                break;
            case 7:
                createNewCategory(baseUri);
                break;
            case 8:
                updateCategory(baseUri);
                break;
            case 9:
                deleteCategory(baseUri);
                break;
            case 10:
                // Exit the program
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        }
    }

    private static void printMenu() {
    	System.out.println("\nMenu:");
        System.out.println("1. View all products");
        System.out.println("2. Create a new product");
        System.out.println("3. Update a product");
        System.out.println("4. Delete a product");
        System.out.println("5. View Products by Categorie");
        System.out.println("6. View all categories");
        System.out.println("7. Create a new category");
        System.out.println("8. Update a category");
        System.out.println("9. Delete a category");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void deleteProduct(URI baseUri) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the product ID to delete: ");
        int productId = scanner.nextInt();

        try {
            WebResource webResource = client.resource(baseUri).path("/produits/" + productId);
            ClientResponse deleteProductResponse = webResource.delete(ClientResponse.class);

            if (deleteProductResponse.getStatus() == 204) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Error deleting product. Status: " + deleteProductResponse.getStatus());
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private static void viewAllProducts(URI baseUri) {
        try {
            WebResource webResource = client.resource(baseUri).path("/produits");
            ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);


            if (response.getStatus() == 200) {
                String jsonOutput = response.getEntity(String.class);

                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode root = objectMapper.readTree(jsonOutput);
                JsonNode produitNode = root.get("produit");

                if (produitNode.isArray()) {
                    List<produit> productList = objectMapper.readValue(produitNode,
                            new org.codehaus.jackson.type.TypeReference<List<produit>>() {});

                    System.out.println("All Products:");
                    for (produit product : productList) {
                        System.out.println("Product Code: " + product.getCodeProduit());
                        System.out.println("Product Name: " + product.getDesignation());
                        System.out.println("Product Price: " + product.getPrixUnitaire());
                        System.out.println("Product Quantity: " + product.getQuantiteEnStock());
                        System.out.println("Category Code: " + product.getCategorieId());
                        System.out.println("--------------------");
                    }
                } else {
                    System.out.println("Invalid response format. 'produit' field is not an array.");
                }
            } else {
                System.out.println("Error getting all products. Status: " + response.getStatus());
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private static void listProductsByCategory(URI baseUri) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the category designation to list products: ");
    String categoryDesignation = scanner.next();

    try {
        WebResource webResource = client.resource(baseUri).path("/produits/cats/" + categoryDesignation);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        System.out.println("Response status: " + response.getStatus());

        String jsonOutput = response.getEntity(String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode root = objectMapper.readTree(jsonOutput);
        JsonNode produitNode = root.get("produit");

        if (produitNode.isArray()) {
            List<produit> productList = objectMapper.readValue(produitNode,
                    new org.codehaus.jackson.type.TypeReference<List<produit>>() {});
            if (productList.isEmpty()) {
                System.out.println("No products found in Category '" + categoryDesignation + "'.");
            } else {
                System.out.println("Products in Category '" + categoryDesignation + "':");
                for (produit product : productList) {
                    System.out.println("Product Code: " + product.getCodeProduit());
                    System.out.println("Product Name: " + product.getDesignation());
                    System.out.println("Product Price: " + product.getPrixUnitaire());
                    System.out.println("Product Quantity: " + product.getQuantiteEnStock());
                    System.out.println("Category Code: " + product.getCategorieId());
                    System.out.println("--------------------");
                }
            }
        } else if (produitNode.isObject()) {
            produit singleProduct = objectMapper.readValue(produitNode, produit.class);
            System.out.println("Product in Category '" + categoryDesignation + "':");
            System.out.println("Product Code: " + singleProduct.getCodeProduit());
            System.out.println("Product Name: " + singleProduct.getDesignation());
            System.out.println("Product Price: " + singleProduct.getPrixUnitaire());
            System.out.println("Product Quantity: " + singleProduct.getQuantiteEnStock());
            System.out.println("Category Code: " + singleProduct.getCategorieId());
            System.out.println("--------------------");
        } else {
            System.out.println("Error listing products by category. Status: " + response.getStatus());
        }
    } catch (Exception e) {
        handleException(e);
    }
}

    private static void updateProduct(URI baseUri) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the product ID to update: ");
        int productId = scanner.nextInt();

        produit updatedProduct = new produit();
        updatedProduct.setCodeProduit(productId);

        System.out.print("Enter new product name: ");
        updatedProduct.setDesignation(scanner.next());

        // Validate and handle potential InputMismatchException for product price
        double newPrice = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                System.out.print("Enter new product price: ");
                newPrice = scanner.nextDouble();
                validPrice = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for the product price.");
                scanner.next(); // Consume the invalid input
            }
        }
        updatedProduct.setPrixUnitaire(newPrice);

        // Validate and handle potential InputMismatchException for product quantity
        double newQuantity = 0;
        boolean validQuantity = false;
        while (!validQuantity) {
            try {
                System.out.print("Enter new product quantity: ");
                newQuantity = scanner.nextDouble();
                validQuantity = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for the product quantity.");
                scanner.next(); // Consume the invalid input
            }
        }
        updatedProduct.setQuantiteEnStock(newQuantity);
        

        System.out.println("Available Categories:");
	    viewAllCategories(baseUri);
        System.out.print("Enter new category code (categorieId): ");
        updatedProduct.setCategorieId(scanner.nextInt());

        try {
            WebResource webResource = client.resource(baseUri).path("/produits");
            ClientResponse updateProductResponse = webResource.type(MediaType.APPLICATION_JSON)
                    .put(ClientResponse.class, updatedProduct);

            if (updateProductResponse.getStatus() == 200) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Error updating product. Status: " + updateProductResponse.getStatus());
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private static void createNewProduct(URI baseUri) {
    	 produit newProduct = new produit();
    	    Scanner scanner = new Scanner(System.in);

    	    System.out.print("Enter product name: ");
    	    newProduct.setDesignation(scanner.next());

    	    System.out.print("Enter product price: ");
    	    newProduct.setPrixUnitaire(scanner.nextDouble());

    	    System.out.print("Enter product quantity: ");
    	    newProduct.setQuantiteEnStock(scanner.nextDouble());
    	    

    	    System.out.println("Available Categories:");
    	    viewAllCategories(baseUri);
    	    System.out.print("Enter category code (categorieId): ");
    	    newProduct.setCategorieId(scanner.nextInt());

    	    try {
    	        WebResource webResource = client.resource(baseUri).path("/produits");
    	        ClientResponse createProductResponse = webResource.type(MediaType.APPLICATION_JSON)
    	                .post(ClientResponse.class, newProduct);

    	        if (createProductResponse.getStatus() == 200) {
    	            System.out.println("Product created successfully.");
    	        } else {
    	            System.out.println("Error creating product. Status: " + createProductResponse.getStatus());
    	        }
    	    } catch (Exception e) {
    	        handleException(e);
    	    }
    }
    
    // Categories 
    
    private static void viewAllCategories(URI baseUri) {
        try {
            WebResource webResource = client.resource(baseUri).path("/categories");
            ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

            

            if (response.getStatus() == 200) {
                String jsonOutput = response.getEntity(String.class);

                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode root = objectMapper.readTree(jsonOutput);
                JsonNode categoryNode = root.get("categorie");

                if (categoryNode.isArray()) {
                    List<categorie> categoryList = objectMapper.readValue(categoryNode,
                            new org.codehaus.jackson.type.TypeReference<List<categorie>>() {});

                    System.out.println("All Categories:");
                    for (categorie category : categoryList) {
                        System.out.println("Category Code: " + category.getCodeCategorie());
                        System.out.println("Category Designation: " + category.getDesignation());
                        System.out.println("--------------------");
                    }
                } else {
                    System.out.println("Invalid response format. 'categorie' field is not an array.");
                }
            } else {
                System.out.println("Error getting all categories. Status: " + response.getStatus());
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private static void createNewCategory(URI baseUri) {
        categorie newCategory = new categorie();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter category designation: ");
        newCategory.setDesignation(scanner.next());

        try {
            WebResource webResource = client.resource(baseUri).path("/categories");
            ClientResponse createCategoryResponse = webResource.type(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, newCategory);

            if (createCategoryResponse.getStatus() == 200) {
                System.out.println("Category created successfully.");
            } else {
                System.out.println("Error creating category. Status: " + createCategoryResponse.getStatus());
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private static void updateCategory(URI baseUri) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the category ID to update: ");
        int categoryId = scanner.nextInt();

        categorie updatedCategory = new categorie();
        updatedCategory.setCodeCategorie(categoryId);

        System.out.print("Enter new category designation: ");
        updatedCategory.setDesignation(scanner.next());

        try {
        	WebResource webResource = client.resource(baseUri).path("/categories");
        	ClientResponse updateCategoryResponse = webResource.type(MediaType.APPLICATION_JSON)
        	        .put(ClientResponse.class, updatedCategory);

            if (updateCategoryResponse.getStatus() == 200) {
                System.out.println("Category updated successfully.");
            } else {
                System.out.println("Error updating category. Status: " + updateCategoryResponse.getStatus());
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private static void deleteCategory(URI baseUri) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the category Designation to delete: ");
        String categoryId = scanner.next();

        try {
            WebResource webResource = client.resource(baseUri).path("/categories/" + categoryId);
            ClientResponse deleteCategoryResponse = webResource.delete(ClientResponse.class);

            if (deleteCategoryResponse.getStatus() == 200) {
                System.out.println("Category deleted successfully.");
            } else {
                System.out.println("Error deleting category. Status: " + deleteCategoryResponse.getStatus());
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    
    private static void handleException(Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
        e.printStackTrace();  // Consider logging the stack trace instead
    }
}
