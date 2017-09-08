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
public class Main extends Application {
    
    private Pane root;
    private Label lblBrojPitanja, lblOdgovor, lblStatistika, lblPercentage;
    private TextArea txtPitanje;
    private TextField txtOdgovor;
    private Button btnSubmit, btnExit;
    private ProgressBar pBar;
    
    private Logika mozak;
    Timer timer;

    /**
     * Start metoda.
     * @param primaryStage
     */
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
                    
                    if(mozak.isKrajIgre())
                    {
                        Dialog.showDialog(root.getScene().getWindow());
                        timer.stop();
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
        SetControlsOnPane
                .setLabelOnPane(root, lblBrojPitanja, 25, 10,
                        Font.font("Arial", 14), null, 0);
    }
    
    private void setTxtPitanjeOnPane()
    {   
        SetControlsOnPane
                .setTextAreaOnPane(root, txtPitanje, 25, 40, 650, 100,
                        Font.font("Arial", 20));
    }
    
    private void setBtnSubmitOnPane()
    {   
        SetControlsOnPane
                .setButtonOnPane(root, btnSubmit, 25, 150, 100, 30,
                        Font.font("Arial", 20), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleSledecePitanje();
            }
        });
    }
    
    private void setLblOdgovorOnPane()
    {
        SetControlsOnPane
                .setLabelOnPane(root, lblOdgovor, 140, 157,
                        Font.font("Arial", 18), null, 0);
    }
    
    private void setTxtOdgovorOnPane()
    {   
        SetControlsOnPane
                .setTextFieldOnPane(root, txtOdgovor, 225, 150, 350, 30,
                        Font.font("Arial", 20), new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER))
                    handleSledecePitanje();
            }
        });
    }
    
    private void setBtnExitOnPane()
    {
        SetControlsOnPane
                .setButtonOnPane(root, btnExit, 595, 150, 80, 30,
                Font.font("Arial", 20), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }
    
    private void setLblStatistikaOnPane()
    {   
        SetControlsOnPane
                .setLabelOnPane(root, lblStatistika, 430, 10,
                Font.font("Arial", 14), Pos.CENTER_RIGHT, 240);
    }
    
    private void setPBarOnPane()
    {
        SetControlsOnPane
                .setProgressBarOnPane(root, pBar, lblPercentage, 25, 200, 650,
                        25, 0, 250, 205, 170);
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
