/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl2018.groupe5;

import java.util.Scanner;

/**
 *
 * @author walidone
 */
public class Controleur {
    private Jeux jeux;
    private int derection;

    public Jeux getJeux() {
        return jeux;
    }

    public void setJeux(Jeux jeux) {
        this.jeux = jeux;
    }
     
     void getDirection(){
         Scanner s = new Scanner(System.in);
         char c =s.nextLine().charAt(0);
         if(c=="z"){
          jeux.deplacement(Dynamique.NORD);  
         }
         if(c=="s"){
          jeux.deplacement(Dynamique.SUD);
         }
         if(c=="d"){
          jeux.deplacement(Dynamique.EST);
         }
         if(c=="q"){
          jeux.deplacement(Dynamique.OUEST);
         }
         return 0;
     }
     
     void start(){
          while(true){
        getDirection();
        }
     }
     
}
