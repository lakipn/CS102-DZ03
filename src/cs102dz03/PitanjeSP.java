/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102dz03;

/**
 *
 * @author Lazar
 */
public class PitanjeSP {

    String pitanje, odgovor;

    public PitanjeSP(String pitanje, String odgovor) {
        this.pitanje = pitanje;
        this.odgovor = odgovor;
    }

    public String getPitanje() {
        return pitanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    @Override
    public String toString() {
        return pitanje + " " + odgovor;
    }
    
}
