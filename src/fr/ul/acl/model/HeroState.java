package fr.ul.acl.model;

import fr.ul.acl.Resources;

import static fr.ul.acl.Resources.POINT_DE_VIE_HEROS;

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
        this.life = POINT_DE_VIE_HEROS;
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

        if (state == State.INJURED && isLifeFull())
            throw new IllegalArgumentException("You can't INJURE the hero because he has full life");
        if (state == State.DEAD)
            this.life = 0;

        this.state = state;

    }

    /**
     * life Getter
     * @return nombre de vies restées.
     */
    public int getLife() {
        return this.life;
    }

    /**
     * life Setter
     * @param life le nombre de vie.
     */
    public void setLife(int life){
        if(life <= 0)
            setState(State.DEAD);
        else
            this.life = life;

        if(!isLifeFull() && state != State.INJURED)
            setState(State.INJURED);
    }

    /**
     * cette methode verifier si life est bien rempli. ( == MAX_Life)
     * @return true si life est maximale, false sinon.
     */
    private boolean isLifeFull() {
        return (this.life == POINT_DE_VIE_HEROS);
    }

    /**
     * Cette methode permet de décrémenter le nombre de vies (par exemple due a une attaque).
     * S'il ne reste plus de vies (0), l'état est changée a DEAD, sinon elle est changée a INJURED.
     */
    public void die() {
        this.setState(State.DEAD);
    }

    /**
     * cette methode restitue l'etat normal de l'heros apres l'etat INVINCIBLE
     */
    public void resetState() {
        State s = getState();
        if(s == State.DEAD || s == State.WIN)
            return;

        if(this.life == POINT_DE_VIE_HEROS)
            setState(State.HEALTHY);
        else
            setState(State.INJURED);
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
