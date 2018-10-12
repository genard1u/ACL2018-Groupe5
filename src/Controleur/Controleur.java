package Controleur;

import Model.Dynamique;
import Model.Jeu;

import java.util.Scanner;

/**
 *
 * @author walidone
 */
public class Controleur {

    private Jeu jeux;
    private int derection;

    public Controleur(Jeu jeu) {
        this.jeux = jeu;
    }

    public Jeu getJeux() {
        return jeux;
    }
    public void setJeux(Jeu jeux) {
        this.jeux = jeux;
    }
     
     public void getDirection(){

        Scanner s = new Scanner(System.in);

        System.out.print("Tapez (S/Z/Q/D) : ");

        char c =s.nextLine().toLowerCase().charAt(0);
        if(c=='z'){
          jeux.deplacement(Dynamique.NORD);
        }
        if(c=='s'){
          jeux.deplacement(Dynamique.SUD);
        }
        if(c=='d'){
          jeux.deplacement(Dynamique.EST);
        }
        if(c=='q'){
          jeux.deplacement(Dynamique.OUEST);
        }
        return ;
     }
     
     public void start(){
        while(true){
            getDirection();
        }
     }
     
}
