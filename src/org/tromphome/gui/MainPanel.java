package org.tromphome.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.tromphome.leaptest.SampleListener;
import org.tromphome.utils.Location;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Finger.Joint;
import com.leapmotion.leap.Finger.Type;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Image;
import com.leapmotion.leap.Vector;

public class MainPanel extends JPanel{

	public static MainPanel panel;
	
	
	public static class OldGesture{
		
		private ArrayList<Location> locs = new ArrayList<Location>();
		int id;
		
		public static ArrayList<OldGesture> gestures = new ArrayList<OldGesture>();
		
		public OldGesture(Location loc, int id){
			locs.add(loc);
			this.id = id;
			gestures.add(this);
		}
		
		public ArrayList<Location> getLocations(){
			return locs;
		}
		
		public int getID(){
			return id;
		}
		
		public void addLocation(Location loc){
			locs.add(loc);
		}
		
		public static OldGesture getGesture(int id){
			for(OldGesture gest : gestures){
				if(id == gest.getID()) return gest;
			}
			return null;
		}
		
		public static boolean contains(int id){
			if(getGesture(id) == null) return false;
			return true;
		}
		
		public static void remove(int id){
			OldGesture gest = getGesture(id);
			gestures.remove(gest);
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
		g2d.setColor(Color.BLACK);
		if(SampleListener.frame == null) return;
		Frame frame = SampleListener.frame;
 	    
	
		
		
		for(Gesture gest : frame.gestures()){
			Location loc = Location.fromVector(frame.fingers().fingerType(Type.TYPE_INDEX).get(0).tipPosition());
			if(OldGesture.contains(gest.id())){
				OldGesture og = OldGesture.getGesture(gest.id());

				if(gest.state().equals(State.STATE_STOP)){
					OldGesture.remove(og.getID());
					System.out.println("dddd");
				}
				
				og.addLocation(Location.fromVector(frame.fingers().fingerType(Type.TYPE_INDEX).get(0).tipPosition()));

				
				

				
				
				
				if(og.getLocations().size() <= 1) continue;
				Location oldLoc = null;
				g2d.setColor(Color.BLUE);
				for(Location currLoc : og.getLocations()){
					if(oldLoc == null){
						oldLoc = currLoc;
						continue;
					}
					g2d.drawLine(oldLoc.getX()*3 + 800, oldLoc.getZ()*3 + 400, currLoc.getX()*3 + 800, currLoc.getZ()*3 + 400);
					oldLoc = currLoc;

				}
				
				
			}else{
				new OldGesture(loc, gest.id());
			}
		}

		
		Image image = MainFrame.controller.images().get(0);
		//System.out.println(image.data().length);
		byte[] data = image.data();
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, image.width(), image.height());
		
		
		int i = 0;
		for(int w = 0; w < image.height(); w++){
			for(int h = 0; h < image.width(); h++){
				
				int value = data[i];
				
				int rgb = value;
				rgb = (rgb << 8) + value;
				rgb = (rgb << 8) + value;
				
				g2d.setColor(new Color(rgb));
				g2d.drawRect(h, w, 1, 1);
				i++;
			}
		}
		
		g2d.setColor(Color.black);
		
		if(frame.hands().isEmpty()) return;
		
		for(Hand hand : frame.hands()){
			Vector v =  hand.palmPosition();
	 	    Location loc = Location.fromVector(v);
			
	 	    //System.out.println((int) (1.0 * 20/loc.getY()*100));
	 	    
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
