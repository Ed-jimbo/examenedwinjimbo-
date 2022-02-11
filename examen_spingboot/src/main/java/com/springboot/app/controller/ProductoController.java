package com.springboot.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.springboot.app.entity.Producto;
import com.springboot.app.service.ProductoService;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

	private ProductoService productoService;
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Producto producto)  {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<?> read (@PathVariable(value = "id") Long productoid) {
    	Optional<Producto> oProducto = productoService.findById(productoid);
    	
    	if(!oProducto.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok(oProducto);
    }
    
    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Producto productoD, @PathVariable(value = "id") Long productoId) {
    	Optional<Producto> producto=productoService.findById(productoId);
    	
    	if(!producto.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	
    	producto.get().setDescripcion(productoD.getDescripcion());
    	producto.get().setPrecio(productoD.getPrecio());
    	producto.get().setCantidad(productoD.getCantidad());
    	
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto.get()));
    }
    
    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable(value = "id") Long productoId){
    	if(!productoService.findById(productoId).isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	
    	productoService.deleteById(productoId);
    	return ResponseEntity.ok().build();    
    	}
    //read
    @GetMapping
    public List<Producto> readAll(){
    	List<Producto> usuarios= StreamSupport
    			.stream(productoService.findAll().spliterator(), false)
    			.collect(Collectors.toList());
    	return usuarios;
    }
}
