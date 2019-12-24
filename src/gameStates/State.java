package gameStates;
import java.awt.Graphics;

public abstract class State {
	private State CurrentState = null;
	
	public State() {
		
	}
	
	public void setState(State St) {
		CurrentState = St;
	}

	public State getState() {
		return CurrentState;
	}
	
	public abstract void Update();

	public abstract void Render(Graphics g);
	
}
