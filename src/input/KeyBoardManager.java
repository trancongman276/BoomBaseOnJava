package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import object.Control1;

public class KeyBoardManager implements KeyListener {
	
	private Control1 move;
	
	public Control1 getMove() {
		return move;
	}

	public void setMove(Control1 move) {
		this.move = move;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode() == KeyEvent.VK_D) {
			move = Control1.R;
		}else
		
		if(arg0.getKeyCode() == KeyEvent.VK_W) {
			move = Control1.U;
		}else
		
		if(arg0.getKeyCode() == KeyEvent.VK_A) {
			move = Control1.L;
		}else

		if(arg0.getKeyCode() == KeyEvent.VK_S) {
			move = Control1.D;
		}else
			move = null;
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			move = Control1.placeBoom;
		}
	}

}