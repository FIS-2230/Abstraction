package com.abstraction.persistence;


import com.abstraction.entities.Cotizacion;
import com.abstraction.entities.Pedido;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICotizacionDAO {
    public boolean create(Cotizacion cotizacion);
    public boolean edit(Long numero, Cotizacion cotizacion);
    public boolean archivar (Cotizacion cotizacion);
    public Cotizacion findById(Long numero);
    public ArrayList<Cotizacion> findAll();
    public Integer count();
    public Long nextId();
}
