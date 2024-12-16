package com.example;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/products")
public class ProductResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProduct(){
		return ProductRepository.getAllProducts();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void addProduct(Product product){
		ProductRepository.addProduct(product.getName(),product.getPrice());
	}

	@DELETE
	@Path("/deleteByName/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProductByName(@PathParam("name") String name){
		ProductRepository.deleteProductByName(name);
	}

	@DELETE
	@Path("/deleteById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProductById(@PathParam("id")int id){
		ProductRepository.deleteProductById(id);
	}

	@PATCH
	@Path("/name={name}&price={price}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateProductByName(@PathParam("name")String name,
									@PathParam("price")double price){
		ProductRepository.updateProductByName(name,price);
	}

	@PATCH
	@Path("/updateProductByName")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateProductByNameV2(Product product){
		ProductRepository.updateProductByName(
				product.getName(),product.getPrice()
		);
	}





}
