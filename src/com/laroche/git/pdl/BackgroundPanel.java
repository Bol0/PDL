package com.laroche.git.pdl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class BackgroundPanel extends JPanel{
	
	Image image;

	public BackgroundPanel(){
		try{
			image = (new ImageIcon("drawables/softBack0.jpg")).getImage();
		}catch(Exception e){
			//nik
			System.out.println(e);
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(image != null)
			g.drawImage(image, 0,0,450,this.getHeight()+100,this);
	}
}