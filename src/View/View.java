package View;

import Model.Jeu;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private Jeu jeu;

    public View(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
