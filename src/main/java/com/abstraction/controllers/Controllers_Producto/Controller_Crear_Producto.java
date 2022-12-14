package com.abstraction.controllers.Controllers_Producto;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_IniciarSesion.Controller_Iniciar_Sesion;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.entities.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Controller_Crear_Producto {

    public IProducto_facade facade;

    public void initialize(IProducto_facade facade){
        this.facade = facade;
    }

    @FXML
    void OnActionCrearBoton(ActionEvent event) {
        if(!textReferenciaProducto.getText().isBlank()&& !textCantidadesExistentes.getText().isBlank()&&!textNombreProducto.getText().isBlank()&&!textPrecioProducto.getText().isBlank()&&!textDescripcion.getText().isBlank()){
            if(facade.crearProducto(
                    new Producto(parseLong(textReferenciaProducto.getText()),
                            textNombreProducto.getText(),
                            parseFloat(textPrecioProducto.getText()),
                            parseInt(textCantidadesExistentes.getText()),
                            textDescripcion.getText(),
                            0)
            )) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Exito en el proceso");
                alert.setTitle("Producto Creado correctamente");
                alert.setContentText("Se ha creado el producto especificado correctamente.");
                alert.show();
            }
            else{
                if(Integer.valueOf(textCantidadesExistentes.getText())<0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Fallo en el proceso");
                    alert.setTitle("Producto no se puede poner en 0");
                    alert.setContentText("Error desconocido. Verifique que la cantidad de productos existentes no este en 0");
                    alert.show();
                }

            }
        }
        else if(textReferenciaProducto.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fallo en el proceso");
            alert.setTitle("Producto no creado");
            alert.setContentText("No se ha podio crear el producto correctamente, indique la referencia del producto.");
            alert.show();
        }
        else if(textNombreProducto.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fallo en el proceso");
            alert.setTitle("Producto no creado");
            alert.setContentText("No se ha podio crear el producto correctamente, indique el nombre del producto.");
            alert.show();
        }
        else if(textPrecioProducto.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fallo en el proceso");
            alert.setTitle("Producto no creado");
            alert.setContentText("No se ha podio crear el producto correctamente, indique el precio del producto.");
            alert.show();
        }
        else if(textCantidadesExistentes.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fallo en el proceso");
            alert.setTitle("Producto no creado");
            alert.setContentText("No se ha podio crear el producto correctamente, indique la cantidad de existencias del producto.");
            alert.show();
        }
        else if(textDescripcion.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fallo en el proceso");
            alert.setTitle("Producto no creado");
            alert.setContentText("No se ha podio crear el producto correctamente, indique la descripcion del producto.");
            alert.show();
        }

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
    void onActionProductos(ActionEvent event) throws IOException {
        cargarListaProductos();
    }

    @FXML
    void OnActionRegresar(ActionEvent event) throws IOException {
        cargarListaProductos();
    }


    /**
     *
     * Cambios de pantalla
     *
     */

    // Cambio a Lista_productos
    void cargarListaProductos (){
        try{
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
        }
        catch(IOException e){
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
    void cargarListaPedidos(){
        try{
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
        }
        catch(IOException e){
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Lista_facturas
    void cargarListaFacturas(){
        try{
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
        }
        catch(IOException e){
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Dashboard
    void cargarDashboard(){
        try{
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
        }
        catch(IOException e){
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Lista_cotizaciones
    void cargarListaCotizaciones(){
        try{
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
        }
        catch(IOException e){
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
     * Getters y Setters
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Button getBotonCerrarSesion() {
        return botonCerrarSesion;
    }

    public void setBotonCerrarSesion(Button botonCerrarSesion) {
        this.botonCerrarSesion = botonCerrarSesion;
    }

    public TextField getTextCantidadesExistentes() {
        return textCantidadesExistentes;
    }

    public void setTextCantidadesExistentes(TextField textCantidadesExistentes) {
        this.textCantidadesExistentes = textCantidadesExistentes;
    }

    public TextField getTextDescripcion() {
        return textDescripcion;
    }

    public void setTextDescripcion(TextField textDescripcion) {
        this.textDescripcion = textDescripcion;
    }

    public TextField getTextNombreProducto() {
        return textNombreProducto;
    }

    public void setTextNombreProducto(TextField textNombreProducto) {
        this.textNombreProducto = textNombreProducto;
    }

    public TextField getTextPrecioProducto() {
        return textPrecioProducto;
    }

    public void setTextPrecioProducto(TextField textPrecioProducto) {
        this.textPrecioProducto = textPrecioProducto;
    }

    public TextField getTextReferenciaProducto() {
        return textReferenciaProducto;
    }

    public void setTextReferenciaProducto(TextField textReferenciaProducto) {
        this.textReferenciaProducto = textReferenciaProducto;
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
    private Button botonFacturacion;

    @FXML
    private Button botonPedidos;

    @FXML
    private Button botonPerfil;

    @FXML
    private Button botonProductos;

    @FXML
    private Button crearProductoBoton;

    @FXML
    private Button regresarBoton;

    @FXML
    private TextField textCantidadesExistentes;

    @FXML
    private TextField textDescripcion;

    @FXML
    private TextField textNombreProducto;

    @FXML
    private TextField textPrecioProducto;

    @FXML
    private TextField textReferenciaProducto;
}
