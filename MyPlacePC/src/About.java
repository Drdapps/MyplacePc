import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class About extends JPanel{
	
	private Image bgImage  = null;
	
	
	
	About()
	{	
		
		//BUONO
		 //Image image = DGen.imageDaResource(DData.iconaLauncer);
		//BackgroundPanel backPanel =  new BackgroundPanel(image);
		 //BackgroundPanel backPanel =  new BackgroundPanel(image, BackgroundPanel.ACTUAL, 1.0f, 0.5f);
			//mette un gradiente
		//GradientPaint paint = new GradientPaint(0, 0, Color.BLUE, 600, 0, Color.RED);
			//backPanel.setPaint(paint);
		//add(backPanel);
		setBounds(300, 100, 300, 300);
		
		AboutTesto aboutTesto  = new AboutTesto();
		add(aboutTesto);
		
		
		
	
	}
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(bgImage, 0, 0, null);
	}
	
	public class AboutTesto extends JPanel{
		
		
		
		AboutTesto()
		{	
			float carattereGrande = 35.0f;   //25
			float caratterePiccolo = 18.0f;    //18
			float carattereMedio = 25.0f;
			
			int distanzaLayOut = 10;
				
			BufferedImage imgini = (BufferedImage) DGen.imageDaResource(DData.iconaLauncer);
			BufferedImage imageBackground = DGen.resizeImage(imgini, 350, 350);
			
			JPanel jpanel = new BackgroundPanel(imageBackground);
			
			jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
			
			JLabel j0 = new JLabel("Release 1.01 Beta");
			j0.setForeground(Color.RED);
			j0.setFont (j0.getFont ().deriveFont (carattereGrande));
			j0.setAlignmentX(Component.CENTER_ALIGNMENT);
			jpanel.add(j0);
			jpanel.add(Box.createVerticalStrut(distanzaLayOut));
			JLabel ja = new JLabel("Develeped by");
			ja.setAlignmentX(Component.CENTER_ALIGNMENT);
			jpanel.add(ja);
			jpanel.add(Box.createVerticalStrut(distanzaLayOut));
			JLabel jb = new JLabel("Drd");
			jb.setFont (jb.getFont ().deriveFont (carattereGrande));
			jb.setAlignmentX(Component.CENTER_ALIGNMENT);
			jpanel.add(jb);
			jpanel.add(Box.createVerticalStrut(distanzaLayOut));
			JLabel jc = new JLabel("2014");
			jc.setFont (jc.getFont ().deriveFont (carattereMedio));
			jc.setAlignmentX(Component.CENTER_ALIGNMENT);
			jpanel.add(jc);
			jpanel.add(Box.createVerticalStrut(distanzaLayOut));
			
			JLabel jd = new JLabel("drdsoftwareapps@gmail.com");
			jd.setFont (jd.getFont ().deriveFont (carattereMedio));
			jd.setAlignmentX(Component.CENTER_ALIGNMENT);
			jpanel.add(jd);
	        BorderLayout border_panel = new BorderLayout();
	         add(jpanel, BorderLayout.CENTER);
	       
	       }
	}
	
	
}

