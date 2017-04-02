/*Name:Xiaoyu Zheng
 * Project number:4
 * Lab Section: Mon 12:30
 * Lab TAs: Camllo, Jack Venuti
 * I did not work with anyone on this assignment
 */
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame{
	static int lives=10;
	int time;
	ControlPanel subpanel;
public Frame(){
	//set frame and separate it into two panels
	super();
	setLayout(new BorderLayout());
	setSize(new Dimension(1000,700));
	Canvas canvas=new Canvas();
	canvas.setPreferredSize(new Dimension(canvas.getWidth(),canvas.getHeight()));
	add(canvas,BorderLayout.SOUTH);	
	addKeyListener(canvas);
	subpanel=new ControlPanel();
	subpanel.setPreferredSize(new Dimension(1000,50));
	add(subpanel,BorderLayout.NORTH);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setFocusable(true);
	pack();
	
}


}
