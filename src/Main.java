import Model.Jeu;
import View.*;

public class Main {


    public static void main(String[] args) {
        Jeu jeux = new Jeu();
        View view = new View(jeux);
    }
}