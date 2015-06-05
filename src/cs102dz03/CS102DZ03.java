/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102dz03;

import java.awt.event.ActionListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.Timer;

/**
 *
 * @author Lazar
 */
public class CS102DZ03 extends Application {
    
    private Pane root;
    private Label lblBrojPitanja, lblOdgovor, lblStatistika, lblPercentage;
    private TextArea txtPitanje;
    private TextField txtOdgovor;
    private Button btnSubmit, btnExit;
    private ProgressBar pBar;
    
    private Logika mozak;
    Timer timer;
    
    @Override
    public void start(Stage primaryStage) {
        lblBrojPitanja = new Label();
        lblOdgovor = new Label("Odgovor:");
        lblStatistika = new Label();
        txtPitanje = new TextArea();
        txtOdgovor = new TextField("");
        btnSubmit = new Button("Potvrdi");
        btnExit = new Button("Izađi");
        pBar = new ProgressBar();
        lblPercentage = new Label();
                      
        root = new Pane();
        
        mozak = new Logika();
        
        setElementsOnPane();
        
        mozak.setTxtPitanje(txtPitanje);
        mozak.setTxtOdgovor(txtOdgovor);
        mozak.setLblStatistika(lblStatistika);
        mozak.setProgressBar(pBar);
        mozak.setLblPercentage(lblPercentage);
        
        Scene scene = new Scene(root, 700, 240);
        primaryStage.setTitle("Ko zna - zna");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                
                Platform.runLater(() ->
                {   
                    mozak.protekloVreme++;
                    if(mozak.protekloVreme == 13)
                    {
                        handleSledecePitanje();
                    }
                });
                
            }
        });
        
        //mozak.startTimer();
        mozak.handleProsloVremeIliDatOdgovor();
        txtPitanje.setText(mozak.getTrenutnoPitanje().getPitanje());
        lblBrojPitanja.setText("Redni broj pitanja: " + (mozak.getBilaPitanjaSize()));
        timer.start();
        
        ProgressBarThread pBarThrd = new ProgressBarThread("ProgressBar nit", pBar, mozak);
        pBarThrd.start();
        
        Timer ugradniTimer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                
                Platform.runLater(() -> {
                    int preostaloVreme = 13 - mozak.protekloVreme;
                    String sekundiFormatirano;
                    if( preostaloVreme >= 5 )
                        sekundiFormatirano = " sekundi";
                    else if( preostaloVreme == 1 )
                        sekundiFormatirano = " sekund";
                    else
                        sekundiFormatirano = " sekunde";
                        
                    lblPercentage.setText("Preostalo vreme: " + preostaloVreme + sekundiFormatirano);
                });
                
            }
        });
        ugradniTimer.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void handleSledecePitanje()
    {
        mozak.handleProsloVremeIliDatOdgovor();
        txtPitanje.setText(mozak.getTrenutnoPitanje().getPitanje());
        lblBrojPitanja.setText("Redni broj pitanja: " + mozak.getBilaPitanjaSize());
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
        
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleSledecePitanje();
            }
        });
        
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
        
        txtOdgovor.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER))
                    handleSledecePitanje();
            }
        });
        
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
    
    private void setPBarOnPane()
    {
        pBar.setLayoutX(25);
        pBar.setLayoutY(200);
        pBar.setPrefWidth(650);
        pBar.setPrefHeight(25);
        pBar.setProgress(0);
        pBar.setVisible(true);
        
        lblPercentage.setLayoutX(250);
        lblPercentage.setLayoutY(205);
        lblPercentage.setPrefWidth(170);
        
        root.getChildren().add(pBar);
        root.getChildren().add(lblPercentage);
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
        setPBarOnPane();
    }
}
