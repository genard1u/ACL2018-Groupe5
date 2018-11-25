package fr.ul.acl.model;

/**
 * la class état de l'heros.
 * Permet de gérer tous les diff. états de l'heros tous au long de la partie du Jeu.
 */
public class HeroState {

    /**
     * L'enumeration des differents états de l'heros.
     */
    public enum State {HEALTHY, DEAD, INJURED, WIN, INVINCIBLE};

    private State state;
    private int life;

    /**
     * Constructeur.
     * Par default l'état initial est toujours HEALTHY.
     */
    public HeroState(){
        this.state = State.HEALTHY;
        this.life = 3;
    }

    /**
     * State Getter
     * @return l'etat actuelle de l'heros.
     */
    public State getState(){
        return this.state;
    }

    /**
     * Cette methode compare l'état fournie en parametre avec l'état actuel
     * @param state l'état q'on doit comparer avec.
     * @return True si les états sont les même, non sinon.
     */
    public boolean is(State state) {
        return (this.getState() == state);
    }

    /**
     * State Setter
     * On ne peut pas affecter un état DEAD si l'héros possède d'autres vies.
     * donc cela sera géré par la methode responsable de nombre de vies.
     * @param state l'etat de l'heros
     */
    public void setState(State state){
        assert state != State.DEAD;
        this.state = state;
    }

    /**
     * life Getter
     * @return nombre de vies restées.
     */
    public int getRemainingLives() {
        return this.life;
    }

    /**
     * Cette methode permet de décrémenter le nombre de vies (par exemple due a une attaque).
     * S'il ne reste plus de vies (0), l'état est changée a DEAD, sinon elle est changée a INJURED.
     */
    public void killOneLife() {
        assert life != 0;
        this.life--;
        if (this.life == 0)
            this.state = State.DEAD;
        else
            this.state = State.INJURED;
    }

    /**
     * factory method de HeroState
     * Cette methode permet l'instanciation des objets HeroState.
     * @return l'objet HeroState instancié
     */
    public static HeroState createState(){
        return new HeroState();
    }

}
