package com.abstraction.controllers.Controllers_Cotizacion;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.ObservableClasses.CotProductoObservable;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_IniciarSesion.Controller_Iniciar_Sesion;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.entities.Cotizacion;
import com.abstraction.entities.CotizacionProducto;
import com.abstraction.entities.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Controller_Ver_Cotizacion {
    public ICotizacion_facade facade;
    Cotizacion cotizacion;

    public void initialize(ICotizacion_facade facade, Cotizacion cotizacion){
        this.facade = facade;
        this.mostrarCotizacion(cotizacion);
        this.cotizacion = cotizacion;
    }

    public void mostrarCotizacion(Cotizacion cotizacion){

        String pattern = "dd/MM/YYYY";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateToString = df.format(cotizacion.getFecha());

        nombreCotizacionText.setText(cotizacion.getNombre());
        nombreClienteText.setText(cotizacion.getNombreCliente());
        numeroDeCotizacionText.setText(String.valueOf(cotizacion.getNumero()));
        fechaCotizacionText.setText(dateToString);
        precioTotalText.setText(String.valueOf(cotizacion.getPrecio()));

        actualizarTabla(cotizacion);
    }

    public void actualizarTabla(Cotizacion cotizacion){
        ArrayList<CotizacionProducto> productos = cotizacion.getProductos();
        if(productos == null) return;
        final ObservableList<CotProductoObservable> data = FXCollections.observableArrayList();

        for(CotizacionProducto p : productos){
            data.add(new CotProductoObservable(
                    p.getProducto().getReferencia(),
                    p.getProducto().getNombre(),
                    p.getCantidad(),
                    p.getProducto().getPrecio(),
                    p.getSubtotal()
            ));
        }

        referenciaColumna.setCellValueFactory(new PropertyValueFactory<CotProductoObservable, String>("referencia"));
        nombreProductoColumna.setCellValueFactory(new PropertyValueFactory<CotProductoObservable, String>("nombre"));
        numProductosColumna.setCellValueFactory(new PropertyValueFactory<CotProductoObservable, String>("cantidad"));
        precioUnitarioColumna.setCellValueFactory(new PropertyValueFactory<CotProductoObservable, String>("precioUnitario"));
        subTotalColumna.setCellValueFactory(new PropertyValueFactory<CotProductoObservable, String>("subtotal"));

        tableViewVerCotizacion.setItems(data);
    }

    @FXML
    void onActionCerrarSesion(ActionEvent event) {
        cargarCerrarSesion();
    }

    @FXML
    void onActionCotizaciones(ActionEvent event) throws IOException {
        cargarListaCotizaciones();
    }

    @FXML
    void onActionGenerarPedido(ActionEvent event) {
        ArrayList<CotizacionProducto> productos = cotizacion.getProductos();
        Producto producto;
        boolean cantidadesSuficientes = true;
        String faltantes = "No hay cantidades suficientes de:";
        for(CotizacionProducto productoCot : productos){
            producto = productoCot.getProducto();
            if(producto.getExistencias()-productoCot.getCantidad() < 0){
                cantidadesSuficientes = false;
                faltantes = faltantes + " " + producto.getNombre();
            }
        }
        if(!cantidadesSuficientes){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fallo en el proceso");
            alert.setTitle("No se pudo generar el pedido");
            alert.setContentText(faltantes);
            alert.show();
        }
        else if(facade.crearPedido(cotizacion)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Exito en el proceso");
            alert.setTitle("Pedido generado correctamente");
            alert.setContentText("Se ha generado un pedido a partir de la cotizacion dada");
            alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fallo en el proceso");
            alert.setTitle("No se pudo generar el pedido");
            alert.setContentText("Error desconocido.");
            alert.show();
        }
    }

    @FXML
    void onActionDashBoard(ActionEvent event) throws IOException {
        cargarDashboard();
    }

    @FXML
    void onActionFacturacion(ActionEvent event) throws IOException {
        cargarListaFacturas();
    }

    @FXML
    void onActionPedidos(ActionEvent event) throws IOException {
        cargarListaPedidos();
    }

    @FXML
    void onActionPerfil(ActionEvent event) throws IOException {
        cargarPerfil();
    }

    @FXML
    void onActionProducto(ActionEvent event) throws IOException {
        cargarListaProductos();
    }

    @FXML
    void onActionRegresar(ActionEvent event) throws IOException {
        cargarListaCotizaciones();
    }

    /**
     * Cambios de pantalla
     */

    // Cambio a Lista_productos
    void cargarListaProductos() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Productos/mockupProductos.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Lista_Productos controller_lista_productos = fxmlLoader.getController();
            controller_lista_productos.initialize((IProducto_facade) this.facade);
            controller_lista_productos.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Cambio a perfil
    void cargarPerfil() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Perfil_Aux/mockupVerPerfil.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Ver_Perfil controller_ver_perfil = fxmlLoader.getController();
            controller_ver_perfil.initialize((FacadeGeneral) this.facade);
            controller_ver_perfil.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Cambio a Lista_pedidos
    void cargarListaPedidos() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Pedidos/mockupListaPedidos.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Lista_Pedidos controller_lista_pedidos = fxmlLoader.getController();
            controller_lista_pedidos.initialize((IPedido_facade) this.facade);
            controller_lista_pedidos.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Lista_facturas
    void cargarListaFacturas() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Facturas/mockupListaFacturas.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Lista_Facturas controller_lista_facturas = fxmlLoader.getController();
            controller_lista_facturas.initialize((IFactura_facade) this.facade);
            controller_lista_facturas.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Dashboard
    void cargarDashboard() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_DashBoard/MockupDASHBOARD.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_DashBoard controller_dashBoard = fxmlLoader.getController();
            controller_dashBoard.initialize((IDashboard_facade) this.facade);
            controller_dashBoard.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Lista_cotizaciones
    void cargarListaCotizaciones() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupListaCotizaciones.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Lista_Cotizaciones controller_lista_cotizaciones = fxmlLoader.getController();
            controller_lista_cotizaciones.initialize((ICotizacion_facade) this.facade);
            controller_lista_cotizaciones.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }
    //cargar cerrar sesion
    void cargarCerrarSesion() {
        try {
            URL fxmlLocation = getClass().getResource("/presentation/View_IniciarSesion/mockupIniciarSesion.fxml");
            System.out.println(fxmlLocation);
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Iniciar_Sesion controladorIniciarSesion= fxmlLoader.getController();
            controladorIniciarSesion.setStage(stage);
            controladorIniciarSesion.initialize(new FacadeGeneral());
            stage.show();
        }
        catch (Exception e){
            System.out.println("y tho");
            e.printStackTrace();
        }
    }
    /**
     *
     * Getters y Setters
     */

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * FXML Elements
     */
    private Stage stage;

    @FXML
    private Button botonCerrarSesion;

    @FXML
    private Button botonCotizaciones;

    @FXML
    private Button botonDashBoard;

    @FXML
    private Button botonGenerarPedido;

    @FXML
    private Button botonFacturacion;

    @FXML
    private Button botonPedidos;

    @FXML
    private Button botonPerfil;

    @FXML
    private Button botonProducto;

    @FXML
    private Button botonRegresar;

    @FXML
    private Text fechaCotizacionText;

    @FXML
    private Text nombreClienteText;

    @FXML
    private Text nombreCotizacionText;


    @FXML
    private TableColumn<CotProductoObservable, String> nombreProductoColumna;

    @FXML
    private TableColumn<CotProductoObservable, String> numProductosColumna;

    @FXML
    private Text numeroDeCotizacionText;

    @FXML
    private Text precioTotalText;
    @FXML
    private TableColumn<CotProductoObservable, String> precioUnitarioColumna;

    @FXML
    private TableColumn<CotProductoObservable, String> referenciaColumna;

    @FXML
    private TableColumn<CotProductoObservable, String> subTotalColumna;

    @FXML
    private TableView<CotProductoObservable> tableViewVerCotizacion;
}
