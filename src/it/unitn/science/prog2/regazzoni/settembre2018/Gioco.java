/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.science.prog2.regazzoni.settembre2018;

import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * permette di giocare tramire dei pulsanti che fanno progredire il gioco
 * e la visualizzazione delle carte in modalità grafica
 * viene anche decretato chi vince
 * @author crist
 */
public class Gioco extends BorderPane {
    
    Mano giocatore1;
    Mano giocatore2;
    Button pesca1;
    Button elimina1;
    Button pescaMazzo1;
    Button pesca2;
    Button elimina2;
    Button pescaMazzo2;
    boolean scartataCarta1;
    boolean scartataCarta2;
    GridPane gp;
    
    /**
     * vengono creati i pulsanti, che quando vengono premuti richiamano funzioni private
     * viene inoltre creata l'interfaccia grafica delle carte, che viene aggiornata tramite 
     * una funzione privata
     * @param mano1 è la mano del primo giocatore, ossia le 4 carte che ha a inizio partita
     * @param mano2 è la mano del secondo giocatore
     */
    
    Gioco (Mano mano1, Mano mano2) {
        scartataCarta1=false;
        scartataCarta2=false;
        giocatore1=mano1;
        giocatore2=mano2;
        gp = new GridPane ();
        gp.setHgap(5);
        gp.setVgap(50);
        gp.setAlignment(Pos.CENTER);
        for (int i=0; i<4; i++) {
            Carta carta = giocatore1.get(i);
            gp.add(carta.rappresentazioneGrafica(), i, 0);
        }
        for (int i=0; i<4; i++) {
            Carta carta = giocatore2.get(i);
            gp.add(carta.rappresentazioneGrafica(), i, 1);
        }
        this.setCenter(gp);
        HBox hb = new HBox ();
        Label nome = new Label (giocatore1.giocatore.nome + "  ");
        hb.getChildren().add(nome);
        pesca1 = new Button ("Pesca una carta dall'avversario");
        EventHandler<ActionEvent> eventHandlerP = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                pescaDaGiocatore2();
            } 
        };
        pesca1.addEventHandler(ActionEvent.ACTION, eventHandlerP);
        elimina1 = new Button ("Elimina coppie");
        EventHandler<ActionEvent> eventHandlerE = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eliminaCoppie1();
            }
        };
        elimina1.addEventHandler(ActionEvent.ACTION, eventHandlerE);
        elimina1.setDisable(true);
        pescaMazzo1 = new Button ("Pesca una carta dal mazzo");
        EventHandler<ActionEvent> eventHandlerM = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pescaDalMazzo1();
            }  
        };
        pescaMazzo1.addEventHandler(ActionEvent.ACTION, eventHandlerM);
        pescaMazzo1.setDisable(true);
        hb.getChildren().addAll(pesca1, elimina1, pescaMazzo1);
        hb.setAlignment(Pos.CENTER);
        this.setTop(hb);
        HBox hb2 = new HBox ();
        Label nome2 = new Label (giocatore2.giocatore.nome + "  ");
        hb2.getChildren().add(nome2);
        pesca2 = new Button ("Pesca una carta dall'avversario");
        EventHandler<ActionEvent> eventHandlerP2 = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                pescaDaGiocatore1();
            } 
        };
        pesca2.addEventHandler(ActionEvent.ACTION, eventHandlerP2);
        pesca2.setDisable(true);
        elimina2 = new Button ("Elimina coppie");
        EventHandler<ActionEvent> eventHandlerE2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eliminaCoppie2();
            }
        };
        elimina2.addEventHandler(ActionEvent.ACTION, eventHandlerE2);
        elimina2.setDisable(true);
        pescaMazzo2 = new Button ("Pesca una carta dal mazzo");
        EventHandler<ActionEvent> eventHandlerM2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pescaDalMazzo2();
            }  
        };
        pescaMazzo2.addEventHandler(ActionEvent.ACTION, eventHandlerM2);
        pescaMazzo2.setDisable(true);
        hb2.getChildren().addAll(pesca2, elimina2, pescaMazzo2);
        hb2.setAlignment(Pos.CENTER);
        this.setBottom(hb2);
    }
    
    private void pescaDaGiocatore2 () {
        Carta c;
        Random random = new Random();
        int carteDisponibili = giocatore2.size();
        int indice = random.nextInt(carteDisponibili);
        c=giocatore2.get(indice);
        giocatore1.add(c);
        giocatore2.remove(c);
        Collections.sort(giocatore1);
        System.out.println(giocatore1);
        System.out.println(giocatore2);
        System.out.println("====");
        pesca1.setDisable(true);
        elimina1.setDisable(false);
        update();
        controlloVittoria();
    }
    
    private void eliminaCoppie1 () {
        boolean daEliminare=false;
        Iterator<Carta> i = (giocatore1).iterator();
        while (i.hasNext()) {
            Carta c = i.next();
            if (daEliminare==true) {
                                    i.remove();
                                    daEliminare=false;
                                    }
            else if ((giocatore1).indexOf(c)!=(giocatore1).lastIndexOf(c)){
                i.remove();
                daEliminare=true;
                scartataCarta1=true;
            }
        }
        System.out.println(giocatore1);
        System.out.println(giocatore2);
        System.out.println("====");
        elimina1.setDisable(true);
        pescaMazzo1.setDisable(false);
        update();
        controlloVittoria();
    }
    
    private void pescaDalMazzo1 () {
        if (scartataCarta1==true) System.out.println(giocatore1.giocatore.nome + ": ho scartato, non pesco");
        else {
            Carta c = giocatore1.mazzo.pescaUnaCarta();
            giocatore1.add(c);
            System.out.println(giocatore1);
        }
        System.out.println(giocatore2);
        System.out.println("====");
        scartataCarta1=false;
        pescaMazzo1.setDisable(true);
        pesca2.setDisable(false);
        update();
        controlloVittoria();
    }
    
    private void pescaDaGiocatore1 () {
        Random random = new Random();
        Carta c;
        int carteDisponibili = giocatore1.size();
        int indice = random.nextInt(carteDisponibili);
        c = giocatore1.get(indice);
        giocatore2.add(c);
        giocatore1.remove(indice);
        Collections.sort(giocatore2);
        System.out.println(giocatore1);
        System.out.println(giocatore2);
        System.out.println("====");
        pesca2.setDisable(true);
        elimina2.setDisable(false);
        update();
        controlloVittoria();
    }
    
    private void eliminaCoppie2() {
        boolean daEliminare = false;
        Iterator<Carta> i = giocatore2.iterator();
        while (i.hasNext()) {
            Carta c = i.next();
            if (daEliminare) {
                i.remove();
                daEliminare = false;
            }
            else if (giocatore2.indexOf(c)!=giocatore2.lastIndexOf(c)) {
                i.remove();
                daEliminare = true;
                scartataCarta2 = true;
            }
        }
        System.out.println(giocatore1);
        System.out.println(giocatore2);
        System.out.println("====");
        elimina2.setDisable(true);
        pescaMazzo2.setDisable(false);
        update();
        controlloVittoria();
    }
    
    private void pescaDalMazzo2 () {
        System.out.println(giocatore1);
        if (scartataCarta2) System.out.println(giocatore2.giocatore.nome + " ho scartato, non pesco");
        else {
            Carta c = giocatore2.mazzo.pescaUnaCarta();
            giocatore2.add(c);
            System.out.println(giocatore2);
        }
        System.out.println("====");
        scartataCarta2 = false;
        pescaMazzo2.setDisable(true);
        pesca1.setDisable(false);
        update();
        controlloVittoria();
    }
    
    private void vittoria (Mano mano) {
        System.out.println("Ha vinto il giocatore " + mano.giocatore.nome);
        Label label = new Label ("Ha vinto il giocatore " + mano.giocatore.nome);
        Scene scene = new Scene (label);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("FINE DEL GIOCO");
        stage.showAndWait();
        System.exit(0);
    }
    
    private void controlloVittoria () {
        if (giocatore1.isEmpty()) vittoria (giocatore1);
        if (giocatore2.isEmpty()) vittoria (giocatore2);
    }
    
    private void update () {
        gp.getChildren().clear();
        int nCarte1 = giocatore1.size();
        int nCarte2 = giocatore2.size();
        for (int i=0; i<nCarte1; i++) {
            Carta c = giocatore1.get(i);
            gp.add(c.rappresentazioneGrafica(), i, 0);
        }
        for (int i=0; i<nCarte2; i++) {
            Carta c = giocatore2.get(i);
            gp.add(c.rappresentazioneGrafica(), i, 1);
        }
    }
    
}
