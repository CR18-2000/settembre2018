/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.science.prog2.regazzoni.settembre2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * mazzo di carte
 * @author crist
 */
public class Mazzo extends ArrayList<Carta>{
    
    Mazzo() {
        for (int i=1; i<5; i++) this.add(new Carta(i, "C"));
        for (int i=1; i<5; i++) this.add(new Carta(i, "Q"));
        for (int i=1; i<5; i++) this.add(new Carta(i, "F"));
        for (int i=1; i<5; i++) this.add(new Carta(i, "P"));
        this.add(new Carta(0, "J"));
    }
    
    /**
     * mescola le carte
     */
    
    private void mescola() {
        Collections.shuffle(this);
    }
    
    /**
     * riordina le carte
     */
    
    private void riordina () {
        Collections.sort(this);
    }
    
    /**
     * stampa il mazzo
     * @return 
     */

    @Override
    public String toString() {
        String a = "";
        //for (Carta c : this) a.concat(c.toString());
        for (Carta c : this) a+=c+" ";
        return a;
    }
    
    /**
     * prende una carta a caso tra quelle rimaste nel mazzo e la ritorna
     * @return 
     */
    
    public Carta pescaUnaCarta () {
        int carteDisponibili = this.size();
        Random random = new Random();
        int indice = random.nextInt(carteDisponibili);
        Carta c = this.get(indice);
        this.remove(indice);
        return c;
    }
    
    /**
     * main test
     * mescola e riordina le carte
     * @param args 
     */
    
    public static void main (String args[]) {
        Mazzo mazzo = new Mazzo();
        mazzo.mescola();
        System.out.println(mazzo);
        mazzo.riordina();
        System.out.println(mazzo);
    }
    
    
    
}
