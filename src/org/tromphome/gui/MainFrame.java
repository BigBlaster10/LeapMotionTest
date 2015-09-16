package org.tromphome.gui;

import javax.swing.JFrame;

import org.tromphome.leaptest.SampleListener;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;

public class MainFrame extends JFrame{

	public static Controller controller;
	public static Listener listener;

	public MainFrame(){
		
		

        
        
		
		
		this.setSize(1000, 750);
		
		MainPanel panel = new MainPanel();
		
		this.add(panel);
		MainPanel.panel = panel;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
		//controller.SetPolicy(Controller.PolicyFlag.POLICY_OPTIMIZE_HMD);
		controller = new Controller();
		listener = new SampleListener();
		controller.addListener(listener);
		controller.setPolicy(Controller.PolicyFlag.POLICY_IMAGES);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
	}
	
}
