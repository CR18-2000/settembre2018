/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.science.prog2.regazzoni.settembre2018;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * classe pincipale
 * @author crist
 */
public class Settembre2018 extends Application {
    
    /**
     * crea un mazzo di carte e due mani per i due giocatori
     * poi fa iniziare il gioco
     * @param primaryStage 
     */
    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane root = new BorderPane();
        Mazzo mazzo = new Mazzo();
        Mano gioc1 = new Mano (new Giocatore ("Pippo"), mazzo);
        System.out.println(gioc1);
        Mano gioc2 = new Mano (new Giocatore ("Pluto"), mazzo);
        System.out.println(gioc2);
        Gioco g = new Gioco (gioc1, gioc2);
        Scene scene = new Scene(g, 500, 250);
        
        primaryStage.setTitle("Giochiamo!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
