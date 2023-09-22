/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.science.prog2.regazzoni.settembre2018;

import java.awt.Font;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * classe che contiene seme e valore della carta
 * @author crist
 */
public class Carta implements Comparable {
    
    int valore;
    String seme;
    
    /**
     * 
     * @param val è il valore della carta
     * @param seme è il seme della carta
     */
    
    Carta(int val, String seme) {
        this.seme=seme;
        this.valore=val;
    }
    
    /**
     * 
     * @return uno stackpane contenente un rettangolo azzurro con sopra scritto 
     * il valore e il seme della carta che rappresenta
     */
    
    public StackPane rappresentazioneGrafica () {
        StackPane sp = new StackPane ();
        Rectangle rect = new Rectangle (50, 50);
        rect.setFill(Color.AQUAMARINE);
        rect.setStroke(Color.BLACK);
        sp.getChildren().add(rect);
        Text t = new Text (this.toString());
        sp.getChildren().add(t);
        return sp;
    }
    
    /**
     * stampa in console valore e seme della carta
     * @return 
     */
    
    @Override
    public String toString () {
        return (valore+"-"+seme+" ");
    }

    /**
     * hashcode di supporto al metodo equals
     * @return 
     */
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.valore;
        return hash;
    }
    
    /**
     * confronta due oggetti
     * due carte sono uguali se hanno lo stesso valore
     * @param obj
     * @return 
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.valore != other.valore) {
            return false;
        }
        return true;
    }

    /**
     * confronta due oggetti per ordinare le carte per valore
     * se il valore è uguale ordina per seme
     * @param o
     * @return 
     */
    
    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        
        final Carta other = (Carta) o;
        if (this.equals(other)==true) {
            if (this.seme.compareTo(other.seme)<0) return -1;
            else if (this.seme.compareTo(other.seme)>0) return 1;
            else return 0;
        }
        else {
            if (this.valore<other.valore) return -1;
            else return 1;
        }
    }
    
    
}
