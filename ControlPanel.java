/*Name:Xiaoyu Zheng
 * Project number:4
 * Lab Section: Mon 12:30
 * Lab TAs: Camllo, Jack Venuti
 * I did not work with anyone on this assignment
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ControlPanel extends JPanel implements ActionListener {
	static int t1 = 30;
	static JLabel lives;
	static int live = 10;
	static JLabel time;
	static JLabel Score;
	Timer timer;
	static int score = 0;
	static Boolean SecondLevel = false;
	static Boolean arc = false;
	JButton TwoPlayer;

	public ControlPanel() {
// basic sets and sets for score, lives and time.
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setSize(new Dimension(1000, 50));
		add(Box.createRigidArea(new Dimension(getWidth() * 4 / 32, getHeight())));
		lives = new JLabel("LIVES: " + live);
		lives.setForeground(Color.white);
		add(lives);
		add(Box.createRigidArea(new Dimension(getWidth() * 2 / 16, getHeight())));
		time = new JLabel("TIME: " + t1);
		time.setForeground(Color.white);
		add(time);
		add(Box.createRigidArea(new Dimension(getWidth() * 2 / 16, getHeight())));
		TwoPlayer = new JButton("Two Player Mode");
		add(TwoPlayer);
		TwoPlayer.addActionListener(this);
		add(Box.createRigidArea(new Dimension(getWidth() * 1 / 16, getHeight())));
		Score = new JLabel("Score: " + score);
		Score.setForeground(Color.white);
		add(Score);
		timer = new Timer(100, this);
		timer.start();
		setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.green);
		//another way to show time
		if (arc == false) {
			g.drawArc(getWidth() * 7 / 17, getHeight() * 1 / 4, getHeight() / 2, getHeight() / 2, 0, 12 * t1);
		} else {
			g.drawArc(getWidth() * 7 / 17, getHeight() * 1 / 4, getHeight() / 2, getHeight() / 2, 0, 360 * t1 / 110);
		}
		g.setColor(Color.BLACK);
	}
//lose lives. Called by Canvas 
	public static void lose() {
		live--;
		lives.setText("LIVES: " + live);
		if (live == 0) {
			Canvas.Stop();
		}
	}
//scores increase as time goes.
	//called by Canvas
	public static void count() {

		t1--;
		score++;
		Score.setText("Score: " + score);
		if (t1 >= 0) {
			time.setText("TIME: " + t1);
		}
		if (t1 == 0) {
			score += 50;
		}
		if (t1 == 0 && SecondLevel == true) {
			Canvas.Stop();
		}

	}

	public static void level2() {
		t1 = 111;
		SecondLevel = true;
		arc = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if jbutton pressed, Two Player Model will get activated.
		if (e.getSource().equals(TwoPlayer)) {
			Canvas.TwoPlayerModel();
			setFocusable(true);
		}
		TwoPlayer.setText("Two Player Model: " + Boolean.toString(Canvas.getTwoPlayer()));

		repaint();
	}
//get scores or lose
	public static void bonus() {
		score += 10;
	}

	public static void ghost() {
		score -= 20;
		;
	}

}
