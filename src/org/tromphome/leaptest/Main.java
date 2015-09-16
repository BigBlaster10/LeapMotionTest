package org.tromphome.leaptest;

import org.tromphome.gui.MainFrame;
import org.tromphome.gui.MainPanel;

public class Main {

	
	public static void main(String[] args) {
        
        new MainFrame();

		(new Thread(new MainThread())).start();
		

        

        
       
        
        //controller.removeListener(listener);

    }
	
	
	public static class MainThread implements Runnable{
		
		
		long currentTime = 0;
		
		@Override
		public void run() {
			while(true){
				if(currentTime + 30 <= System.currentTimeMillis()){
					currentTime = System.currentTimeMillis();
					MainPanel.panel.repaint();
					
				}
			}
			
			
			
		}
		
		
		
		
	}
	
	
	
}
