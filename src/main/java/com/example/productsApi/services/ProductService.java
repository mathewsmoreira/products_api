package com.example.productsApi.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productsApi.controllers.ProductController;
import com.example.productsApi.dtos.ProductRecordDto;
import com.example.productsApi.models.ProductModel;
import com.example.productsApi.repositories.ProductRepository;
import com.example.productsApi.services.exceptions.EntityNotFoundExceptions;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	//this method captured the value send with POST method and save in the data base
	public ProductModel save (ProductRecordDto productRecordDto) {
		ProductModel productModel = new ProductModel();
		BeanUtils.copyProperties(productRecordDto, productModel);
		
		return productRepository.save(productModel);
	}

	//this method captured the value send with GET method and search by id in the data base and return one product
	public ProductModel findById(UUID id) {
		Optional<ProductModel> productO = Optional.of(productRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundExceptions("Product not found id = '" + id+ "'")));
		
		//implementation of HATEOS
		return productO.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
		
	}
	
	//this method captured the value send with GET method and search in the data base and return all products
	public List<ProductModel> findAllProduct(){
		List<ProductModel> productList = productRepository.findAll();
		if(!productList.isEmpty()) {
			//implementation of HATEOS
			for (ProductModel productModel : productList) {
				productModel.add(linkTo(methodOn(ProductController.class).getOneProduct(productModel.getIdProduct())).withSelfRel());
			}
			
		}
		
		return productList;
	}
	
	//this method captured the value send with PUT method, first it will search in the data-base to be sure that the id exists
	//if not it will throw a exception that will be treat at the ControllerExceptionHandler.
	public ProductModel updateProduct(UUID id,ProductRecordDto productRecordDto){
		ProductModel productO = productRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundExceptions("Failed to update product id = '"+ id+"' product not found in the data base."));
		BeanUtils.copyProperties(productRecordDto, productO);
		return productRepository.save(productO);
	}
	
	//this method captured the value send with DELETE method, first it will search in the data-base to be sure that the id exists
	//if not it will throw a exception that will be treat at the ControllerExceptionHandler.
	public Object deleteProduct (UUID id) {
		ProductModel productO = productRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundExceptions("Failed to delete product id = '"+id+ "' product not found in the data base."));
		
		productRepository.deleteById(id);
		return new String("Product deleted successfully.");
	}
}
