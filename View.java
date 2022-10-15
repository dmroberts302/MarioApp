// Daijon Roberts, 09/29/2022, Assignment 3

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	int scrollPos;
	int framePos = 0;
	BufferedImage pipe_image;
	BufferedImage[] mario_images;


	// Constructor
	View(Controller c, Model m)
	{
		model = m;
		this.scrollPos = 0;
		c.setView(this);

		try
		{
				this.pipe_image = ImageIO.read(new File("pipe.png"));
				this.mario_images = new BufferedImage[5];
				this.mario_images[0] = ImageIO.read(new File("mario1.png"));
				this.mario_images[1] = ImageIO.read(new File("mario2.png"));
				this.mario_images[2] = ImageIO.read(new File("mario3.png"));
				this.mario_images[3] = ImageIO.read(new File("mario4.png"));
				this.mario_images[4] = ImageIO.read(new File("mario5.png"));
		}
		catch(Exception e)
		{
    			e.printStackTrace(System.err);
    			System.exit(1);
		}
	}

	// This method is called automatically when the panel needs to be drawn
	public void paintComponent(Graphics g)
	{
		scrollPos = model.mario.x - 100;
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
		//	Draw the pipes
		for(int i = 0; i < model.pipes.size(); i++)
		{
			Pipe p = model.pipes.get(i);
			g.drawImage(pipe_image, p.x - this.scrollPos, p.y, null);
		}

		// Draw boundarie line
		g.setColor(Color.gray);
		g.drawLine(0, 470, 2000, 470);
		g.drawLine(0, 471, 2000, 471);
		g.drawLine(0, 472, 2000, 472);
		g.drawLine(0, 473, 2000, 473);
		g.drawLine(0, 474, 2000, 474);

		// Draw Mario
		g.drawImage(mario_images[framePos], model.mario.x - this.scrollPos, model.mario.y, null);
	}
}
