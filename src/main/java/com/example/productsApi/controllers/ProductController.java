package com.example.productsApi.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.productsApi.dtos.ProductRecordDto;
import com.example.productsApi.models.ProductModel;
import com.example.productsApi.repositories.ProductRepository;
import com.example.productsApi.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//this method is to save information that was received from HTTP POST method
	@PostMapping("/products")
	public ResponseEntity<ProductModel> saveProductModel(@RequestBody @Valid ProductRecordDto productRecordDto) {
		// the return is a responseEntity with code 200, and the data from the object that was saved
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productRecordDto));
	}
	
	@GetMapping("/products")
	public ResponseEntity<java.util.List<ProductModel>> getAllProducts(){
		
		return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProduct());
	}
	
	//this method is used to find the information of one product that the user send via HTTP GET method
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
		// the return is a responseEntity with code 200 and the data from the object that was saved
		// if the id is not found in the data base it will return a responseEntity with code 404.
		return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
	}
	
	@PutMapping("/products/{id}")
	//this method is used to update the information of one product that the user send via HTTP PUT method
	public ResponseEntity<Object> updateProducts(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto) {
		// the return is a responseEntity with code 200 and the data from the object that was saved
		// if the id is not found in the data base it will return a responseEntity with code 404.
		return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id,productRecordDto));
	}
	
	@DeleteMapping("/products/{id}")
	//this method is used to delete the information of one product that the user send via HTTP DELETE method
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
		// the return is a responseEntity with code 200 and the data from the object that was saved
		// if the id is not found in the data base it will return a responseEntity with code 404.
		return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
	}
	
	
	

}
