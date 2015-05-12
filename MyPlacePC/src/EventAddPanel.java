import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.jdesktop.swingx.JXDatePicker;

public class EventAddPanel extends JPanel {
	
	/**
	 * 
	 */
	
	
	
	public static Attivita attivitaPassata;
	
	private BorderLayout border_layout;
	
	public static JPanel p, plabels, ptext;
	public static JButton bttdatePicker;
	public static JTextField txtData;
	public static JTextArea txtNote;
	public static JScrollPane jscrollPane;
	public static JXDatePicker jdp;
	
	EventAddPanel(long idAttivita){
		
		
		
		
		
		 border_layout = new BorderLayout(); // creo il layout manager e lo assegno al container
         setLayout(border_layout);
         JScrollPane jSAtti = new JScrollPane(impostaLayout());
         add(jSAtti, BorderLayout.CENTER);
         
      
         if (idAttivita>=0)
         {
        	 try {
        		 attivitaPassata = DataSource.getAttivita(idAttivita);
     			
        		 visualizzaAttivita(attivitaPassata);
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
         }
         
    	
		
	}
	
	public static void visualizzaAttivita(Attivita atti)
	{		Date data  = new Date(atti.getpDataAttivita());
			jdp = new JXDatePicker(data, getDefaultLocale());
			
			//txtData.setText(atti.getpDataAttivita()+"");
		 	txtNote.setText(atti.getpDescrizioneAttività());
	       
	      
	}
	public static JPanel impostaLayout()
	{
		int columnwidhttxt =  35;
			txtData = new JTextField(columnwidhttxt);
	     
			txtNote =  new JTextArea();
			txtNote.setLineWrap(true);
	    	txtNote.setWrapStyleWord(true);
	    	
	    	Date data  = new Date(System.currentTimeMillis());
	    	jdp = new JXDatePicker(data, getDefaultLocale());
	    	
	    	
	    	
	    	JPanel jCliente = new JPanel(new GridBagLayout());
	    	jCliente.add(jdp);
	    
	    	
	    	
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL ; c.insets = new Insets(5,0,0,10);
			c.anchor = GridBagConstraints.FIRST_LINE_START;
					int gridwidthtxt = 5;
   	
   
   	c.gridx = 0;c.gridy = 0;
   	jCliente.add(new JLabel(DString.attData), c);
	/*
   c.gridx = 2;c.gridy = 0;c.gridwidth =1;   //2 columns wide
   	jCliente.add(txtData, c);
   	/*
   	/*
   	c.gridx = 5;c.gridy = 0;c.gridwidth =1;   //2 columns wide
   	jCliente.add(bttdatePicker, c);
   	*/
	c.gridx = 2;c.gridy = 0;c.gridwidth =3;   //2 columns wide
   	jCliente.add(jdp,c);
   	
   	c.gridx = 0;c.gridy = 1;c.gridwidth = 1;
   	jCliente.add(new JLabel(DString.attDescrizione), c);
   
   	c.gridx = 2;c.gridy = 1;c.gridwidth = 5;   //2 columns wide
   	jCliente.add(txtNote, c);
   	
	
   	
   	txtNote.setRows(10);
   	txtNote.setColumns(30);
   	txtNote.setLineWrap(true);
   	txtNote.setWrapStyleWord(true);
   	  Border border = BorderFactory.createLineBorder(Color.GRAY);
   	txtNote.setBorder(border);
   	jCliente.add(txtNote, c);
	return jCliente;
	}
	   
	
	

}
