package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.entity.Producto;


@Repository
public interface ProductoR extends JpaRepository<Producto, Long>{

}
