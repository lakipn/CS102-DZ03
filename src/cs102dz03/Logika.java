/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102dz03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javax.swing.Timer;

/**
 *
 * @author Lazar
 */
public class Logika {
    
    private TextArea txtPitanje;
    
    protected Podaci podaci;
    private ArrayList< Integer > bilaPitanja;
    private int protekloVreme;
    
    private Timer timer;

    public Logika() {
        inicijalna();
    }
    
    public Logika(TextArea txtPitanje)
    {
        inicijalna();
        setTxtPitanje(txtPitanje);
    }
    
    private void inicijalna()
    {
        podaci = new Podaci();
        bilaPitanja = new ArrayList<>();
        protekloVreme = 0;
        
        pokupiPodatke();
        
        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                protekloVreme++;
                if(protekloVreme == 10)
                    handleProsloVreme();
            }
        });
        
        txtPitanje = new TextArea();
    }
    
    public void setTxtPitanje(TextArea txtPitanje)
    {
        this.txtPitanje = txtPitanje;
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
        while(biloPitanje(idx = r.nextInt())) ;
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
        if(pitanje.getOdgovor().toUpperCase().equals(odgovor.toUpperCase()))
            podaci.incrementTacni();
        else
            podaci.incrementNetacni();
    }
    
    public void handleProsloVreme()
    {
        if(bilaPitanja.size() < 10)
            System.out.println("");
    }
    
    
    
}
