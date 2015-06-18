/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102dz03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.Timer;

/**
 *
 * @author Lazar
 */
public class Logika {
    
    private TextArea txtPitanje;
    private TextField txtOdgovor;
    private Label lblStatistika, lblPercentage;
    private ProgressBar pBar;
    
    protected Podaci podaci;
    private ArrayList< Integer > bilaPitanja;
    protected int protekloVreme;
    private PitanjeSP trenutnoPitanje;
    
    private boolean krajIgre; // za ProgressBarThread
    
    private Timer timer;

    public Logika() {
        inicijalna();
    }
    
    public Logika(TextArea txtPitanje, TextField txtOdgovor, Label lblStatistika,
            ProgressBar pBar, Label lblPercentage)
    {
        inicijalna();
        setTxtPitanje(txtPitanje);
        setTxtOdgovor(txtOdgovor);
        setLblStatistika(lblStatistika);
        setProgressBar(pBar);
        setLblPercentage(lblPercentage);
    }
    
    private void inicijalna()
    {
        podaci = new Podaci();
        bilaPitanja = new ArrayList<>();
        protekloVreme = 0;
        
        pokupiPodatke();        
        
        txtPitanje = new TextArea();
        txtOdgovor = new TextField();
        lblStatistika = new Label();
        pBar = new ProgressBar();
        lblPercentage = new Label();
        
        krajIgre = false;
    }
    
    public void setTxtPitanje(TextArea txtPitanje)
    {
        this.txtPitanje = txtPitanje;
    }
    
    public void setTxtOdgovor(TextField txtOdgovor)
    {
        this.txtOdgovor = txtOdgovor;
    }
    
    public void setLblStatistika(Label lblStatistika)
    {
        this.lblStatistika = lblStatistika;
    }
    
    public void setProgressBar(ProgressBar pBar)
    {
        this.pBar = pBar;
    }
    
    public void setLblPercentage(Label lblPercentage)
    {
        this.lblPercentage = lblPercentage;
    }
    
    private void pokupiPodatke()
    {
        try{
            FileInputStream fis = new FileInputStream("pitanja.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
            
            String zaglavlje = reader.readLine(), linija;
            
            while( (linija = reader.readLine()) != null )
            {
                int i, j;
                
                for(i = 0; i < linija.length(); i++)
                    if(linija.charAt(i) == '\t')
                        break;
                String pitanje = linija.substring(0, i);
                
                String odgovor = linija.substring(i+1, linija.length());
                
                PitanjeSP pitanjeSP = new PitanjeSP(pitanje, odgovor);
                
                podaci.addPitanje(pitanjeSP);
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }
    
    private PitanjeSP dajPitanje()
    {
        Random r = new Random();
        int idx;
        while(biloPitanje(idx = r.nextInt(podaci.getPitanjaSize()))) ;
        bilaPitanja.add(idx);
        return podaci.getPitanje(idx);
    }
    
    private boolean biloPitanje(int idx)
    {
        return bilaPitanja.contains(idx) ? true : false;
    }
    
    public void setLabelText(Label lbl, String text)
    {
        lbl.setText(text);
    }
    
    public void setTextAreaText(TextArea txt, String text)
    {
        txt.setText(text);
    }
    
    public void proveriOdgovor(PitanjeSP pitanje, String odgovor)
    {
        if(pitanje.getOdgovor().toUpperCase().equals(odgovor.toUpperCase()) ||
                konvertujSrpskuLatinicuUEnglesku(pitanje.getOdgovor().toUpperCase()).equals(odgovor.toUpperCase()))
            podaci.incrementTacni();
        else
            podaci.incrementNetacni();
        
        setLabelText(lblStatistika, "Statistika  |  Tačnih: " + podaci.getTacni() +
                "   :   Netačnih: " + podaci.getNetacni());
        
//        System.out.println("Dat odg: " + odgovor + "\tTacan odg: " + pitanje.getOdgovor() + "\t\t" +
//                (pitanje.getOdgovor().toUpperCase().equals(odgovor.toUpperCase()) ? "TACNO" : "NETACNO"));
    }
    
    public void handleProsloVremeIliDatOdgovor()
    {
        if(bilaPitanja.size() < 10)
        {
            if(bilaPitanja.size() > 0)
                proveriOdgovor(trenutnoPitanje, txtOdgovor.getText());
            
            trenutnoPitanje = dajPitanje();
            protekloVreme = 0;
        }
        else
        {
            krajIgre = true;
        }
        
        txtOdgovor.setText("");
        txtOdgovor.requestFocus();
    }
    /* JOptionPane ne moze biti pozvan u JavaFX thread-u kao sto se poziva inace
    private void showMessageBox(String poruka, String naslov, Object[] opcije)
    {
        int tipOpcije;
        switch(opcije.length)
        {
            case 1:
                tipOpcije = JOptionPane.OK_OPTION;
                break;
            case 2:
                tipOpcije = JOptionPane.YES_NO_OPTION;
                break;
            default:
                tipOpcije = JOptionPane.YES_NO_CANCEL_OPTION;
        }
        
        int n = JOptionPane.showOptionDialog(null, poruka, naslov, tipOpcije,
                JOptionPane.QUESTION_MESSAGE, null, opcije, opcije[0]);
        
        switch(n)
        {
            case 0:
                if(opcije.length == 1)
                {
                    krajIgre = true;
                    //System.exit(0);
                }
                break;
        }
    }
    */
    public void startTimer()
    {
        timer.start();
        handleProsloVremeIliDatOdgovor();
    }
    
    public void handleBtnSubmit()
    {
        handleProsloVremeIliDatOdgovor();
    }

    public PitanjeSP getTrenutnoPitanje() {
        return trenutnoPitanje;
    }
    
    public int getBilaPitanjaSize()
    {
        return bilaPitanja.size();
    }

    public int getProtekloVreme() {
        return protekloVreme;
    }

    public boolean isKrajIgre() {
        return krajIgre;
    }
    
    private String konvertujSrpskuLatinicuUEnglesku(String temp)
    {
        temp = temp.replaceAll("Š", "S");
        temp = temp.replaceAll("Đ", "DJ");
        temp = temp.replaceAll("Ž", "Z");
        temp = temp.replaceAll("Č", "C");
        temp = temp.replaceAll("Ć", "C");
        
        return temp;
    }
    
}
