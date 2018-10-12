import Controleur.Controleur;
import Model.Jeu;
import View.*;

public class Main {


    public static void main(String[] args) {
        Jeu jeu = new Jeu(8,8);
        Controleur ctrl = new Controleur(jeu);
        View view = new View(jeu, ctrl);

        ctrl.start();

    }
}