package com.abstraction.persistence.impl;

import com.abstraction.entities.Factura;
import com.abstraction.persistence.IFacturaDAO;
import com.abstraction.persistence.IPedidoDAO;
import com.abstraction.persistence.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FacturaDAO implements IFacturaDAO {

    private final MySQL mysql;

    public FacturaDAO(){
        this.mysql = new MySQL();
    }
    @Override
    public boolean create(Factura factura) {
        try{
            this.mysql.conectar();
            String query = "INSERT INTO factura(numero, Pedido_numero, fecha, valor, abonosTotal) VALUES("+
                    "'" + factura.getNumero()+ "'," +
                    "'" + factura.getPedidoFactura() + "'," +
                    "'" + factura.getFecha() + "'," +
                    "'" + factura.getValorTotal() + "'," +
                    "'" + factura.getAbonoTotal() + "'" +
                    ");";
            System.out.println(query);

            Statement stmt = this.mysql.getConnection().createStatement();
            int code = stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            if (code == 1) {
                System.out.println("Se creo la factura");
                return true;
            }
            return false;

        } catch (SQLException ex){
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }

    @Override
    public boolean edit(Long numero, Factura factura) {

        String pattern = "DD/MM/YYYY";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateToString = df.format(factura.getFecha());

        try{
            this.mysql.conectar();
            String query = "UPDATE factura SET "+
                    "numero = '" + factura.getNumero() + "'," +
                    "Pedido_numero = '" + factura.getPedidoFactura() + "'," +
                    "TO_DATE('" + dateToString + "','" + pattern + "')," +
                    "valor = '" + factura.getValorTotal() + "'" +
                    "abonosTotal = '" + factura.getAbonoTotal()+ "'" +
                    " WHERE numero = '" + numero + "';";
            System.out.println(query);

            Statement stmt = this.mysql.getConnection().createStatement();
            int code = stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            switch (code) {
                case 1:
                    System.out.println("Se edito la factura");
                    return true;
                default:
                    return false;
            }

        } catch (SQLException ex){
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }

    @Override
    public boolean delete(Long numero) {
        try{
            String query = "DELETE FROM factura WHERE numero = '" + numero + "';";
            System.out.println(query);

            this.mysql.conectar();
            Statement stmt = this.mysql.getConnection().createStatement();
            int code = stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            switch (code) {
                case 1:
                    System.out.println("Se elimino la factura");
                    return true;
                default:
                    return false;
            }

        } catch (SQLException ex){
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }

    @Override
    public Factura findById(Long numero) {
        try{
            this.mysql.conectar();
            String query = "SELECT * FROM factura WHERE numero = '"+ numero +"';";
            System.out.println(query);

            IPedidoDAO pedidoDAO = new PedidoDAO();

            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            if(rs.first())
            {
                Factura factura = new Factura(rs.getLong("numero"),
                        rs.getDate("fecha"),
                        rs.getFloat("valorTotal"),
                        rs.getFloat("abonoTotal"),
                        pedidoDAO.findById(rs.getLong("Pedido_numero")));

                rs.close();
                stmt.close();
                this.mysql.desconectar();

                return factura;
            }
            else
            {
                rs.close();
                stmt.close();
                this.mysql.desconectar();

                return null;
            }

        } catch (SQLException ex){
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }

    @Override
    public ArrayList<Factura> findAll() {
        try{

            ArrayList<Factura> facturas = new ArrayList<>();
            IPedidoDAO pedidoDAO = new PedidoDAO();

            this.mysql.conectar();
            String query = "SELECT * FROM factura;";
            System.out.println(query);
            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return null;
            rs.previous();
            do
            {
                rs.next();
                Factura factura = new Factura(rs.getLong("numero"),
                        rs.getDate("fecha"),
                        rs.getFloat("valorTotal"),
                        rs.getFloat("abonoTotal"),
                        pedidoDAO.findById(rs.getLong("Pedido_numero")));                facturas.add(factura);
            }
            while(!rs.isLast());

            rs.close();
            stmt.close();
            this.mysql.desconectar();

            return facturas;

        } catch (SQLException ex){
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }

    @Override
    public Integer count() {
        try{

            Integer n = 0;

            this.mysql.conectar();
            String query = "SELECT * FROM factura;";
            System.out.println(query);
            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            do
            {
                rs.next();
                n++;
            }
            while(!rs.isLast());

            rs.close();
            stmt.close();
            this.mysql.desconectar();

            return n;

        } catch (SQLException ex){
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }

    }

}