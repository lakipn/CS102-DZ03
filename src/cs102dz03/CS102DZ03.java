/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102dz03;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Lazar
 */
public class CS102DZ03 extends Application {
    
    private Pane root;
    private Label lblBrojPitanja, lblOdgovor, lblStatistika;
    private TextArea txtPitanje;
    private TextField txtOdgovor;
    private Button btnSubmit, btnExit;
    
    private Logika mozak;
    
    @Override
    public void start(Stage primaryStage) {
        lblBrojPitanja = new Label("Redni broj pitanja: 1");
        lblOdgovor = new Label("Odgovor: ");
        lblStatistika = new Label("Statistika  |  Tačnih: 0   :   Netačnih: 0");
        txtPitanje = new TextArea("Koliko očiju ima baba ?");
        txtOdgovor = new TextField("");
        btnSubmit = new Button("Potvrdi");
        btnExit = new Button("Izađi");
                      
        root = new Pane();
        
        mozak = new Logika();
        
        setElementsOnPane();
        
        Scene scene = new Scene(root, 700, 200);
        primaryStage.setTitle("Ko zna - zna");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void setLblBrojPitanjaOnPane()
    {
        lblBrojPitanja.setLayoutX(25);
        lblBrojPitanja.setLayoutY(10);
        lblBrojPitanja.setFont(Font.font("Arial", 14)); 
        
        root.getChildren().add(lblBrojPitanja);
    }
    
    private void setTxtPitanjeOnPane()
    {
        txtPitanje.setLayoutX(25);
        txtPitanje.setLayoutY(40);
        txtPitanje.setPrefWidth(650);
        txtPitanje.setPrefHeight(100);
        txtPitanje.setFont(Font.font("Arial", 20));
        
        root.getChildren().add(txtPitanje);
    }
    
    private void setBtnSubmitOnPane()
    {
        btnSubmit.setLayoutX(25);
        btnSubmit.setLayoutY(150);
        btnSubmit.setPrefWidth(100);
        btnSubmit.setPrefHeight(30);
        btnSubmit.setFont(Font.font("Arial", 20));
        
        root.getChildren().add(btnSubmit);
    }
    
    private void setLblOdgovorOnPane()
    {
        lblOdgovor.setLayoutX(140);
        lblOdgovor.setLayoutY(157);
        lblOdgovor.setFont(Font.font("Arial", 18)); 
        
        root.getChildren().add(lblOdgovor);
    }
    
    private void setTxtOdgovorOnPane()
    {
        txtOdgovor.setLayoutX(225);
        txtOdgovor.setLayoutY(150);
        txtOdgovor.setPrefWidth(350);
        txtOdgovor.setPrefHeight(30);
        txtOdgovor.setFont(Font.font("Arial", 20));
        
        root.getChildren().add(txtOdgovor);
    }
    
    private void setBtnExitOnPane()
    {
        btnExit.setLayoutX(595);
        btnExit.setLayoutY(150);
        btnExit.setPrefWidth(80);
        btnExit.setPrefHeight(30);
        btnExit.setFont(Font.font("Arial", 20));
        
        btnExit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        root.getChildren().add(btnExit);
    }
    
    private void setLblStatistikaOnPane()
    {
        lblStatistika.setLayoutX(430);
        lblStatistika.setLayoutY(10);
        lblStatistika.setPrefWidth(240);
        lblStatistika.setFont(Font.font("Arial", 14));
        lblStatistika.setAlignment(Pos.CENTER_RIGHT);
        
        root.getChildren().add(lblStatistika);
    }
    
    private void setElementsOnPane()
    {
        setLblBrojPitanjaOnPane();
        setLblStatistikaOnPane();
        setTxtPitanjeOnPane();
        setLblOdgovorOnPane();
        setTxtOdgovorOnPane();
        setBtnSubmitOnPane();
        setBtnExitOnPane();
    }
}
