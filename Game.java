// Daijon Roberts, 09/29/2022, Assignment 3

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	View view;
	Controller controller;
	Mario mario;

	public Game()
	{
		mario = new Mario();
		model = new Model(mario);
  controller = new Controller(model);
		view = new View(controller, model);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		this.setTitle("A4 - Side Scroller");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run()
	{
		while(true)
		{
		controller.update();
		model.update();
		view.repaint(); // Indirectly calls View.paintComponent
		Toolkit.getDefaultToolkit().sync(); // Updates screen

		// Go to sleep for 50 milliseconds
		try
		{
			Thread.sleep(40);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		}
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
}
