package com.mintyn.inventory.api.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class IntegrationTest {
	
	/**
	 * Create Product
	 */
	public void createProduct() {
		
        try {

			String name = "Techno F" + ThreadLocalRandom.current().nextInt(1, 10000);
			int stockCount = ThreadLocalRandom.current().nextInt(100, 1000);
			int price = ThreadLocalRandom.current().nextInt(1500, 20000);
			String description = "The lastest model of " + name;
			
			String url = "http://localhost/inventory/create";            
			String USER_AGENT = "Mozilla/5.0";
	            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
            	
                HttpPost request = new HttpPost(url);
                
                // add request header
                request.addHeader("User-Agent", USER_AGENT);
                request.addHeader("name", name);
                request.addHeader("stockCount", String.valueOf(stockCount));
                request.addHeader("price", String.valueOf(price));
                request.addHeader("description", description);
                
                HttpResponse response = client.execute(request);
                
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                
                String line = "";
                
                while ((line = rd.readLine()) != null) {
                    
                    result.append(line);
                    
                }                
                
                System.out.println("\n Integration Test Create Product:- To Create Product Send 'POST' URL : " + url);
                System.out.println(result.toString());                
                
                client.close();                   
                                               
            }
                                                                                               
            
        } catch (IOException | UnsupportedOperationException ex) {

            System.out.println("Could not retrieve transaction properties " + ex.getMessage());
            
        }		
		  	    	
    }

	/**
	 * Update Product
	 */
	public void updateProduct() {
		
        try {

			int productId = ThreadLocalRandom.current().nextInt(1, 20);
			int stockCount = ThreadLocalRandom.current().nextInt(100, 1000);
			int price = ThreadLocalRandom.current().nextInt(1500, 20000);
			
			String url = "http://localhost/inventory/update/" + productId;            
			String USER_AGENT = "Mozilla/5.0";
	            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
            	
                HttpPut request = new HttpPut(url);
                
                // add request header
                request.addHeader("User-Agent", USER_AGENT);
                request.addHeader("stockCount", String.valueOf(stockCount));
                request.addHeader("price", String.valueOf(price));
                
                HttpResponse response = client.execute(request);
                
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                
                String line = "";
                
                while ((line = rd.readLine()) != null) {
                    
                    result.append(line);
                    
                }                
                
                System.out.println("\n Integration Test Update Product:- For Product Update Send 'PUT' URL : " + url);
                System.out.println(result.toString());                
                
                client.close();                   
                                               
            }
                                                                                               
            
        } catch (IOException | UnsupportedOperationException ex) {

            System.out.println("Could not retrieve transaction properties " + ex.getMessage());
            
        }		
		  	    	
    }
	
	/**
	 * Get Product Paginated Product List
	 */
	public void getProductListPaginated() {
		
        try {

			int pageNumber = ThreadLocalRandom.current().nextInt(1, 5);
			
			String url = "http://localhost/inventory/list/" + pageNumber;            
			String USER_AGENT = "Mozilla/5.0";
	            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
            	
                HttpGet request = new HttpGet(url);
                
                // add request header
                request.addHeader("User-Agent", USER_AGENT);
                
                HttpResponse response = client.execute(request);
                
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                
                String line = "";
                
                while ((line = rd.readLine()) != null) {
                    
                    result.append(line);
                    
                }                
                
                System.out.println("\n Integration Test List Product Paginated:- For Paginated Product List Send 'GET' URL : " + url);
                System.out.println(result.toString());                
                
                client.close();                   
                                               
            }
                                                                                               
            
        } catch (IOException | UnsupportedOperationException ex) {

            System.out.println("Could not retrieve transaction properties " + ex.getMessage());
            
        }		
		  	    	
    }

	/**
	 * Order Product
	 */
	public void orderProduct() {
		
        try {

			int productId = ThreadLocalRandom.current().nextInt(1, 20);
			String name = "Sunday Omada";
			String phone = "080" + new Random().nextInt(99999999);
			int quantity = ThreadLocalRandom.current().nextInt(1, 10);
			
			String url = "http://localhost/inventory/order/" + productId;            
			String USER_AGENT = "Mozilla/5.0";
	            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
            	
                HttpPost request = new HttpPost(url);
                
                // add request header
                request.addHeader("User-Agent", USER_AGENT);
                request.addHeader("name", name);
                request.addHeader("phone", phone);
                request.addHeader("quantity", String.valueOf(quantity));
                
                HttpResponse response = client.execute(request);
                
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                
                String line = "";
                
                while ((line = rd.readLine()) != null) {
                    
                    result.append(line);
                    
                }                
                
                System.out.println("\n Integration Test Order Product:- For Product Order Send 'POST' URL : " + url);
                System.out.println(result.toString());                
                
                client.close();                   
                                               
            }
                                                                                               
            
        } catch (IOException | UnsupportedOperationException ex) {

            System.out.println("Could not retrieve transaction properties " + ex.getMessage());
            
        }		
		  	    	
    }	

	/**
	 * Order Report
	 */
	public void orderReport() {
		
        try {

			String startDate = "02/12/2021";
			String endDate = "03/12/2021";
			
			String url = "http://localhost/inventory/report";            
			String USER_AGENT = "Mozilla/5.0";
	            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
            	
                HttpGet request = new HttpGet(url);
                
                // add request header
                request.addHeader("User-Agent", USER_AGENT);
                request.addHeader("startDate", startDate);
                request.addHeader("endDate", endDate);
                
                HttpResponse response = client.execute(request);
                
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                
                String line = "";
                
                while ((line = rd.readLine()) != null) {
                    
                    result.append(line);
                    
                }                
                
                System.out.println("\n Integration Test Order Reporting:- For Order Reporting Send 'GET' URL : " + url);
                System.out.println(result.toString());                
                
                client.close();                   
                                               
            }
                                                                                               
            
        } catch (IOException | UnsupportedOperationException ex) {

            System.out.println("Could not retrieve transaction properties " + ex.getMessage());
            
        }		
		  	    	
    }	
	
}
