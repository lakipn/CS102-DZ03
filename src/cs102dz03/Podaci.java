/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102dz03;

import java.util.ArrayList;

/**
 *
 * @author Lazar
 */
public class Podaci {
    
    private int tacni, netacni;
    private ArrayList<PitanjeSP> pitanja;

    public Podaci() {
        tacni = 0;
        netacni = 0;
        pitanja = new ArrayList<>();
    }
    
    public PitanjeSP getPitanje(int idx)
    {
        return pitanja.get(idx);
    }
    
    public void addPitanje(PitanjeSP pitanje)
    {
        pitanja.add(pitanje);
    }
    
    public void incrementTacni()
    {
        tacni++;
    }
    
    public void incrementNetacni()
    {
        netacni++;
    }
    
    public int getTacni()
    {
        return tacni;
    }
    
    public int getNetacni()
    {
        return netacni;
    }
    
    public int getPitanjaSize()
    {
        return pitanja.size();
    }
    
}
