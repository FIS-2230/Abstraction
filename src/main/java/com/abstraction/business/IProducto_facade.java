package com.abstraction.business;

import com.abstraction.entities.Producto;

import java.util.ArrayList;

public interface IProducto_facade {
    boolean crearProducto(Producto producto);
    Producto verProducto(Long id);
    boolean actualizarProducto(Producto producto);
    boolean eliminarProducto(Long id);
    ArrayList<Producto> listarProductos();
    ArrayList<Producto> findByPrecio(float precioMin, float precioMax);
}