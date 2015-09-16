package org.tromphome.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.swing.JPanel;

import org.tromphome.leaptest.SampleListener;
import org.tromphome.utils.Location;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Finger.Joint;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Image;
import com.leapmotion.leap.Vector;

public class MainPanel extends JPanel{

	public static MainPanel panel;
	
	
	
	
	@Override
	public void paintComponent(Graphics g){
		
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.BLUE);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(Color.BLACK);
		if(SampleListener.frame == null) return;
		Frame frame = SampleListener.frame;
 	    
		if(frame.hands().isEmpty()) return;

		
		Image image = MainFrame.controller.images().get(0);
		System.out.println(image.data().length);
		
		
		
		IntBuffer intBuf =  ByteBuffer.wrap(image.data()).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
				 int[] array = new int[intBuf.remaining()];
				 intBuf.get(array);
		
		BufferedImage bi = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
		bi.setRGB(0, 0, image.width(), image.height(), array, 0, 100);
		
		g2d.drawImage(bi, 0, 0, null);
		
		for(Hand hand : frame.hands()){
			Vector v =  hand.palmPosition();
	 	    Location loc = Location.fromVector(v);
			
	 	    System.out.println((int) (1.0 * 20/loc.getY()*100));
	 	    
			g2d.fillOval(loc.getX()*3 + 800, loc.getZ()*3 + 400, getSize(40, loc), getSize(40, loc));
			
			
			v =  hand.arm().center();
	 	    loc = Location.fromVector(v);
			g2d.fillOval(loc.getX()*3 + 800, loc.getZ()*3 + 400, getSize(40, loc), getSize(40, loc));

			
			
			
			for(Finger finger : hand.fingers()){
				v = finger.tipPosition();
				loc = Location.fromVector(v);
				g2d.fillRect(loc.getX()*3 + 800, loc.getZ()*3 + 400, getSize(20, loc), getSize(20, loc));

				
				v = finger.jointPosition(Joint.JOINT_DIP);
				loc = Location.fromVector(v);
				g2d.fillRect(loc.getX()*3 + 800, loc.getZ()*3 + 400, getSize(10, loc), getSize(10, loc));

				v = finger.jointPosition(Joint.JOINT_MCP);
				loc = Location.fromVector(v);
				g2d.fillRect(loc.getX()*3 + 800, loc.getZ()*3 + 400, getSize(10, loc), getSize(10, loc));
				
				v = finger.jointPosition(Joint.JOINT_PIP);
				loc = Location.fromVector(v);
				g2d.fillRect(loc.getX()*3 + 800, loc.getZ()*3 + 400, getSize(20, loc), getSize(20, loc));
				
			
				
			}
			
			
		}
		

		
		g2d.dispose();
	}
	
	public int getSize(int s, Location loc){
		return s;
	}
	

	
	
}
