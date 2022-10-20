package com.abstraction.business;

import com.abstraction.entities.Cotizacion;
import com.abstraction.entities.CotizacionProducto;

import java.util.ArrayList;

public interface ICotizacion_facade {
    boolean crearCotizacion(ArrayList<CotizacionProducto> prods);
    Cotizacion verCotizacion(Long id);
    ArrayList<Cotizacion> listarCotizaciones();
    boolean actualizarCotizacion(Cotizacion cotizacion);
    boolean archivarCotizacion(Long id);
    boolean crearPedido(Cotizacion cotizacion);
}