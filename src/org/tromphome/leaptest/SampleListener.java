package org.tromphome.leaptest;

import org.tromphome.gui.MainPanel;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

public class SampleListener extends Listener {

	
	public static Frame frame;
	
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    public void onFrame(Controller controller) {
    	   frame = controller.frame();

    	   if(frame.hands().isEmpty()) return; 
    	   
    	   Vector v =  frame.hands().get(0).palmPosition();
    	   MainPanel.panel.repaint();
    }
    
    
    
    /*
     * System.out.println("Frame id: " + frame.id()
    	                   + ", timestamp: " + frame.timestamp()
    	                   + ", hands: " + frame.hands().count()
    	                   + ", fingers: " + frame.fingers().count()
    	                   + ", tools: " + frame.tools().count()
    	                   + ", gestures " + frame.gestures().count());
     */
}