/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102dz03;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author Lazar
 */
public class ProgressBarThread implements Runnable {
    
    private Thread t;
    private String threadName;
    private ProgressBar pBar;
    private Logika logika;
    
    private int biloProtekloVreme;

    public ProgressBarThread() {
        threadName = "ProgressBar thread";
        pBar = new ProgressBar();
        logika = new Logika();
        biloProtekloVreme = -1;
    }
    
    public ProgressBarThread(String threadName, ProgressBar pBar, Logika logika)
    {
        setThreadName(threadName);
        setpBar(pBar);
        setLogika(logika);
        biloProtekloVreme = -1;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void setpBar(ProgressBar pBar) {
        this.pBar = pBar;
    }

    public void setLogika(Logika logika) {
        this.logika = logika;
    }
    
    @Override
    public void run() {
        //pBar.setProgress(0.5);
        while(true)
        {
            if(biloProtekloVreme != logika.getProtekloVreme())
            {
                biloProtekloVreme = logika.getProtekloVreme();
                double prog = (1.0 * (biloProtekloVreme + 1)) / 13;
                pBar.setProgress(prog);
            }
            try {
                //System.out.println("Proteklo: " + logika.getProtekloVreme() + " sekundi.");
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProgressBarThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void start()
    {
        if(t == null)
        {
            t = new Thread(this, threadName);
            t.start();
        }
        else
            System.out.println("Nit " + threadName + " je već pokrenuta.");
    }
    
}
