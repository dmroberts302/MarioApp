// Daijon Roberts, 09/29/2022, Assignment 3

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean exit;
	boolean keyS;
	boolean keyL;
	boolean keyE = false;
	boolean start = true;
	boolean collided;
	boolean keySpace;
	

	//	constructor
	Controller(Model m)
	{
		model = m;
	}

	// set view
	void setView(View v)
	{
		view = v;
	}

	// executes code based on an action
	public void actionPerformed(ActionEvent e)
	{
	}

	// sets x and y coordinates in model based on a mouse click
	public void mousePressed(MouseEvent e)
	{
		// Run only in edit mode
		if (keyE) model.addPipe(e.getX() + view.scrollPos, e.getY());
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {  
		if (e.getY() < 100) {
			//System.out.println("break here");
		}
	 }

	// sets boolean variables code based on a key press
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_ESCAPE: exit = true; break;
			case KeyEvent.VK_Q: exit = true; break;
			case KeyEvent.VK_S: keyS = true; break;
			case KeyEvent.VK_L: keyL = true; break;
			case KeyEvent.VK_SPACE: keySpace = true; break;

			
			// Play & Edit mode 
			case KeyEvent.VK_E: 
								keyE	= !keyE;
								if (keyE) {
									System.out.println("Edit Mode");
								}
								else
									System.out.println("Play Mode");
								break;
		}
	}

	// sets boolean variables code based on a key release
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_ESCAPE: exit = false; break;
			case KeyEvent.VK_Q: exit = false; break;
			case KeyEvent.VK_S: keyS = false; break;
			case KeyEvent.VK_L: keyL = false; break;
			case KeyEvent.VK_SPACE: 
			model.mario.canJump = false;
			keySpace = false; break;

		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	// runs while the game is running
	void update()
	{

		// load starting map
		if (start){
			model.load("map.json");
			start = false;
		}

		// Key Functionalities 
		if(keyS) model.save("map.json");
		if(keyL) model.load("map.json");
		if(exit) System.exit(0);
		
		// get previous coordinates incase of collision 
		model.mario.prevCoordinates();
		if(keyRight){
			//if (notCollided)
			view.scrollPos += 4;
			// move mario based on moving to the right
			model.mario.x += 4;
			view.framePos = (view.framePos + 1) % 5;
		} 
		if(keyLeft) {
			//if (notCollided)
			view.scrollPos -= 4;
			// move mario based on moving to the left
			model.mario.x -= 4;
			view.framePos = (view.framePos + 1) % 5;
		} 

		if (keySpace) {
			if (model.mario.counter <= 30 && model.mario.canJump)
				model.mario.y -= 20;
		}


	}

}
