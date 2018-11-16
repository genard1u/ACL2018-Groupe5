package fr.ul.acl.model;

public class GameState {
	
	public enum State {Running, LostLife, GameOver, Won, Pause};
	
	private State state;

    public GameState() {
        state = State.Running;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
