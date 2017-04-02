/*Name:Xiaoyu Zheng
 * Project number:4
 * Lab Section: Mon 12:30
 * Lab TAs: Camllo, Jack Venuti
 * I did not work with anyone on this assignment
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Canvas extends JPanel implements KeyListener, ActionListener {
	int Px, Py, Px1, Py1;
	static Timer timer;
	double Vx, Vy;
	double Bx, By;
	int BallSize;
	int i = 0;
	Rectangle obstacle1, obstacle2;
	int BX1, BY1, BX2, BY2, BX3, BY3, BX4, BY4, BX5, BY5;
	int size;
	BufferedImage box1, box2, box3, box4, box5;
	Boolean b1 = true, b2 = true, b3 = true, b4 = true, b5 = true;
	static BufferedImage ghost1;
	static BufferedImage ghost2;
	int Ghost1 = 0, Ghost2 = 0;
	static Boolean TwoPlayer = false;
	Boolean MoveRight = false, MoveLeft = false;
	Boolean R1Left = false, R1Right = false, R2Left = false, R2Right = false;
	int turn=1;

	public Canvas() {

		setSize(new Dimension(1000, 650));
		// the location of the first platform to rebound a ball
		Px = 7 * getWidth() / 16;
		Py = 10 * getHeight() / 11;
		// the location of the second platform to rebound a ball
		Px1 = 3 * getWidth() / 16;
		Py1 = 10 * getHeight() / 11;
		timer = new Timer(40, this);
		// initial velocity for ball
		Vx = 10;
		Vy = -5;
		// initial ball location
		Bx = getWidth() / 2;
		By = getHeight() * 1 / 8;
		timer.start();
		this.setFocusable(true);
		addKeyListener(this);
		// load five secret boxes
		try {
			box1 = ImageIO.read(new File("Image//!.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			box2 = ImageIO.read(new File("Image//!.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			box3 = ImageIO.read(new File("Image//!.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			box4 = ImageIO.read(new File("Image//?.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			box5 = ImageIO.read(new File("Image//?.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// getSize (they are square)
		size = box1.getWidth();
		// location for these secret boxes
		BX1 = (int) (Math.random() * getWidth() * 3 / 5 + getWidth() / 5);
		BX2 = (int) (Math.random() * getWidth() * 3 / 5 + getWidth() / 5);
		BX3 = (int) (Math.random() * getWidth() * 3 / 5 + getWidth() / 5);
		BX4 = (int) (Math.random() * getWidth() * 3 / 5 + getWidth() / 5);
		BX5 = (int) (Math.random() * getWidth() * 3 / 5 + getWidth() / 5);
		BY1 = (int) (Math.random() * getHeight() * 7 / 11);
		BY2 = (int) (Math.random() * getHeight() * 7 / 11);
		BY3 = (int) (Math.random() * getHeight() * 7 / 11);
		BY4 = (int) (Math.random() * getHeight() * 7 / 11);
		BY5 = (int) (Math.random() * getHeight() * 7 / 11);
	}

	@Override
	public void paintComponent(Graphics g) {
		// set background
		BufferedImage img = null;
		//draw a ball
		BallSize = 25;
		Shape oval = new Ellipse2D.Double(Bx, By, BallSize, BallSize);
		
		try {
			img = ImageIO.read(new File("Image//background.jpg"));
		} catch (IOException e) {
		}
		
		g.drawImage(img, 0, 0, null);
		Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.white);
        g2.fill(oval);
		g2.draw(oval);
		
		//first platform to rebound the ball
		//if it's not two Player Model
		if(TwoPlayer==false){
			requestFocusInWindow();
		g2.setPaint(Color.white);
		Rectangle rectangle = new Rectangle(Px, Py, getWidth() / 6, getHeight() / 32);
		g2.fill(rectangle);
		g2.draw(rectangle);
		
		
		if (oval.intersects(rectangle)) {
          
			Vy = -Vy;
			if (Vy < 0) {
				Vy -= 0.5;
			}

		}	
		//if it's two player model and the first player's turn
		}else if(turn==1&&TwoPlayer==true){
			requestFocusInWindow();
			g2.setPaint(Color.white);
			Rectangle rectangle = new Rectangle(Px, Py, getWidth() / 6, getHeight() / 32);
			g2.fill(rectangle);
			g2.draw(rectangle);
			
			//intersect and then change turn
			if (oval.intersects(rectangle)) {
	          
	        	  turn=2;
	        
				Vy = -Vy;
				if (Vy < 0) {
					Vy -= 0.5;
				}

			}
		}
		//draw the second platform to rebound a ball
		//if it's two player model and the second player's turn
		if (TwoPlayer == true&&turn==2) {
			requestFocusInWindow();
			Rectangle rectangle1 = new Rectangle(Px1, Py1, getWidth() / 6, getHeight() / 32);
			g2.setPaint(Color.LIGHT_GRAY);
			g2.fill(rectangle1);
			g2.draw(rectangle1);
			//every time the ball is rebounded, the velocity of the ball will increase
			//intersect and then change turn
			if (oval.intersects(rectangle1)) {
                turn=1;
				Vy = -Vy;
				if (Vy < 0) {
					Vy -= 0.5;
				}
			}
			setFocusable(true);
		}
		
		
		//when it's time, I will show the message "Level 2"
		if (i >= 300 && i <= 400) {
			g.drawString("Level 2", 20, 20);
		}
		if (i == 400) {
			if (Vy >= 0) {
				Vy += 2;
			} else {
				Vy -= 2;
			}
		}

		if (i > 500) {
			//when it's time, it will show one piece of message"Secret box"
			if (i < 1000) {
				g.drawString("Secret Box", 20, 20);
			}
	        //Box 1 exists until you hit box1 and get some bonus scores
			if (b1 == true) {
				if (Bx >= BX1 - BallSize && Bx <= BX1 + size && By <= BY1 + size && By >= BY1 - BallSize) {
					Vy = -Vy;
					Vx = -Vx;
					b1 = false;
					ControlPanel.bonus();
					g.drawString("Score: +10", 20, 40);
				} else {
					g.drawImage(box1, BX1, BY1, null);
				}
			}
			//Box 2 exists until you hit box1 and get some bonus scores
			if (b2 == true) {
				if (Bx >= BX2 - BallSize && Bx <= BX2 + size && By <= BY2 + size && By >= BY2 - BallSize) {
					Vy = -Vy;
					Vx = -Vx;
					b2 = false;
					ControlPanel.bonus();
					g.drawString("Score: +10", 20, 40);
				} else {
					g.drawImage(box2, BX2, BY2, null);
				}
			}
			//Box 3 exists until you hit box1 and get surprise.
			if (b3 == true) {
				if (Bx >= BX3 - BallSize && Bx <= BX3 + size && By <= BY3 + size && By >= BY3 - BallSize) {
					Vy = -Vy;
					Vx = -Vx;
					b3 = false;
					ControlPanel.bonus();
					Random();
					g.drawString("Random Surprise", 20, 40);
				} else {
					g.drawImage(box3, BX3, BY3, null);
				}
			}
			//Box 4 exists until you hit box1 and get surprise.
			if (b4 == true) {
				if (Bx >= BX4 - BallSize && Bx <= BX4 + size && By <= BY4 + size && By >= BY4 - BallSize) {
					Vy = -Vy;
					Vx = -Vx;
					b4 = false;
					Random();
					g.drawString("Random Surprise", 20, 40);
				} else {
					g.drawImage(box4, BX4, BY4, null);
				}
			}
			//Box 5 exists until you hit box1 and get surprise.
			if (b5 == true) {
				if (Bx >= BX5 - BallSize && Bx <= BX5 + size && By <= BY5 + size && By >= BY5 - BallSize) {
					Vy = -Vy;
					Vx = -Vx;
					b5 = false;
					Random();
					g.drawString("Random Surprise", 20, 40);
				} else {
					g.drawImage(box5, BX5, BY5, null);
				}
			}
			//Boolean Ghost1 and Ghost2 are determined by a Random() method.
			if (Ghost1 != 0) {
				if (i <= Ghost1 + 10) {
					g.drawImage(ghost1, 0, 0, null);
					g.drawString("Score: -20", 20, 40);
				}
			}
			if (Ghost2 != 0) {
				if (i <= Ghost2 + 10) {
					g.drawImage(ghost2, 0, 0, null);
				}
			}
			if (i >= 1380) {
				g.drawString("The End", 20, 20);
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
//set control keys
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			R1Right = true;

			break;
		case KeyEvent.VK_LEFT:
			R1Left = true;

			break;
		default:
			break;
		}
		switch (e.getKeyChar()) {
		case 'd':
			R2Right = true;

			break;
		case 'a':
			R2Left = true;

			break;
		default:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			R1Right = false;

			break;
		case KeyEvent.VK_LEFT:
			R1Left = false;

			break;
		default:
			break;
		}
		switch (e.getKeyChar()) {
		case 'd':
			R2Right = false;

			break;
		case 'a':
			R2Left = false;

			break;
		default:
			break;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		setFocusable(true);
		//if keys pressed, platforms will get moved.
		if (R1Right == true) {
			Px += 15;
		}
		if (R1Left == true) {
			Px -= 15;
		}
		if (R2Right == true) {
			Px1 += 15;
		}
		if (R2Left == true) {
			Px1 -= 15;
		}

		i++;
		Graphics g = getGraphics();
		Vy += 9.8 / 20;
		Bx += Vx;
		By += Vy;
		//hit the left edge, the right edge, the ground or the top, change the ball direction
		if (Bx >= getWidth() - BallSize) {
			Vx = -Vx;
		} else if (Bx <= 0) {
			Vx = -Vx;
		}
//hit the ground, lose scores
		if (By >= getHeight() - BallSize) {
			Vy = -Vy;
			ControlPanel.lose();

		} else if (By <= 0) {
			Vy = -Vy;
		}
		repaint();
//as times goes, scores increase
		for (int x = 0; x <= 300; x += 10) {
			if (x == i) {
				ControlPanel.count();
			}
		}
		if (i == 300) {
			ControlPanel.level2();
		}
		if (i >= 300) {
			for (int x = 300; x <= 1400; x += 10) {
				if (x == i) {
					ControlPanel.count();
				}
			}

		}
	}

	public static void Stop() {
		timer.stop();
	}
//give results for three secret boxes
	public void Random() {
		double random = Math.random();
		if (random <= 0.4) {
			ControlPanel.bonus();
		}
		if (random > 0.4 && random <= 0.7) {
			ControlPanel.ghost();
			try {
				ghost1 = ImageIO.read(new File("Image//ghost1.jpg"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			Ghost1 = i;
		}
		if (random > 0.7 && random <= 1) {
			ControlPanel.ghost();
			try {
				ghost2 = ImageIO.read(new File("Image//Trap.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Ghost2 = i;
		}

	}
//it will call by ControlPanel to activate two player model
	public static void TwoPlayerModel() {
		if (TwoPlayer == false) {
			TwoPlayer = true;
		} else {
			TwoPlayer = false;
		}

	}

	public static Boolean getTwoPlayer() {
		return TwoPlayer;
	}
}
