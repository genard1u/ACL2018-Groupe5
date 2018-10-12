import Model.Jeu;
import View.*;

public class Main {


    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        View view = new View(jeu);

        controleur.start();
    }
}