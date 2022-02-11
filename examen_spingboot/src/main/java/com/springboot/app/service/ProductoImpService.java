package com.springboot.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.entity.Producto;
import com.springboot.app.repository.ProductoR;

@Service
public class ProductoImpService implements ProductoService{

	@Autowired
	private ProductoR productoR;
	@Override
	@Transactional(readOnly = true)
	public Iterable<Producto> findAll() {
		
		return productoR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pageable) {
		
		return productoR.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Long id) {
		
		return productoR.findById(id);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		
		return productoR.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		productoR.deleteById(id);
	}

}
