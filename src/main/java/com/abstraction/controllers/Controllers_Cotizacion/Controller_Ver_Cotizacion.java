package com.abstraction.controllers.Controllers_Cotizacion;

import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Controller_Ver_Cotizacion {

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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
    private TextField fechaCotizacionText;

    @FXML
    private TextField nombreClienteText;

    @FXML
    private TextField nombreCotizacionText;

    @FXML
    private TableColumn<?, ?> nombreProductoColumna;

    @FXML
    private TableColumn<?, ?> numProductosColumna;

    @FXML
    private TextField numeroDeCotizacionText;

    @FXML
    private TextField precioTotalText;

    @FXML
    private TableColumn<?, ?> precioUnitarioColumna;

    @FXML
    private TableColumn<?, ?> referenciaColumna;

    @FXML
    private TableColumn<?, ?> subTotalColumna;

    @FXML
    void onActionCerrarSesion(ActionEvent event) {

    }

    @FXML
    void onActionCotizaciones(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupListaCotizaciones.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Lista_Cotizaciones controller_lista_cotizaciones = fxmlLoader.getController();
        controller_lista_cotizaciones.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionGenerarPedido(ActionEvent event) {
        //Acción Implementada en la Facade
    }

    public TextField getFechaCotizacionText() {
        return fechaCotizacionText;
    }

    public void setFechaCotizacionText(TextField fechaCotizacionText) {
        this.fechaCotizacionText = fechaCotizacionText;
    }

    public TextField getNombreClienteText() {
        return nombreClienteText;
    }

    public void setNombreClienteText(TextField nombreClienteText) {
        this.nombreClienteText = nombreClienteText;
    }

    public TextField getNombreCotizacionText() {
        return nombreCotizacionText;
    }

    public void setNombreCotizacionText(TextField nombreCotizacionText) {
        this.nombreCotizacionText = nombreCotizacionText;
    }

    public TextField getNumeroDeCotizacionText() {
        return numeroDeCotizacionText;
    }

    public void setNumeroDeCotizacionText(TextField numeroDeCotizacionText) {
        this.numeroDeCotizacionText = numeroDeCotizacionText;
    }

    public TextField getPrecioTotalText() {
        return precioTotalText;
    }

    public void setPrecioTotalText(TextField precioTotalText) {
        this.precioTotalText = precioTotalText;
    }

    @FXML
    void onActionDashBoard(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_DashBoard/MockupDASHBOARD.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_DashBoard controller_dashBoard = fxmlLoader.getController();
        controller_dashBoard.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionFacturacion(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Facturas/mockupListaFacturas.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Lista_Facturas controller_lista_facturas = fxmlLoader.getController();
        controller_lista_facturas.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionPedidos(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Pedidos/mockupListaPedidos.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Lista_Pedidos controller_lista_pedidos = fxmlLoader.getController();
        controller_lista_pedidos.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionPerfil(ActionEvent event) {

    }

    @FXML
    void onActionProducto(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Productos/mockupProductos.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Lista_Productos controller_lista_productos = fxmlLoader.getController();
        controller_lista_productos.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionRegresar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupListaCotizaciones.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Lista_Cotizaciones controller_lista_cotizaciones = fxmlLoader.getController();
        controller_lista_cotizaciones.setStage(stage);
        stage.show();
        this.stage.close();
    }

}