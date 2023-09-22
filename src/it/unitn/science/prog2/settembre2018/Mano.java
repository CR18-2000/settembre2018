/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.science.prog2.regazzoni.settembre2018;

import java.util.ArrayList;
import java.util.Collections;

/**
 * classe che definisce le carte che hanno in mano i giocatori
 * @author crist
 */
public class Mano extends ArrayList<Carta>{
    
    Giocatore giocatore;
    Mazzo mazzo;
    
    /**
     * sceglie 4 carte a caso dal mazzo che andranno a formare la mano del giocatore
     * @param g è il giocatore che possiede queste carte
     * @param mazzo è il mazzo usato per giocare
     */
    
    Mano(Giocatore g, Mazzo mazzo) {
        this.mazzo=mazzo;
        giocatore = g;
        for (int i=0; i<4; i++) {
            Carta c = mazzo.pescaUnaCarta();
            this.add(c);
        }
        Collections.sort(this);
    }

    /**
     * stampa il nome del giocatore seguito dalle carte che ha in mano
     * @return 
     */
    
    @Override
    public String toString() {
        String a =giocatore.nome;
        a+="    ";
        for (Carta c : this) a+=c.toString();
        return a;
    }
    
}
