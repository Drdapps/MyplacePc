import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import net.sourceforge.jdatepicker.JDatePicker;

import org.jdesktop.swingx.JXDatePicker;

public class AttivitaAddPanel extends JPanel {
	
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
	public static JCheckBox jchk;
	public static JCheckBox jchkAddAllarm;
	public static JComboBox jComboTipoAttivita;
	public static Attivita atti;
	
	
	AttivitaAddPanel(long idAttivita){
		
		 try {
			attivitaPassata = DataSource.getAttivita(idAttivita);
			System.out.println("ATTIVITA ADD PANEL PASSATO FATTO/"+attivitaPassata.getpTipoAttivita());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
		
		
		
		 border_layout = new BorderLayout(); // creo il layout manager e lo assegno al container
		 
         setLayout(border_layout);
         JScrollPane jSAtti = new JScrollPane(impostaLayout());
         add(jSAtti, BorderLayout.CENTER);
         
      
         if (idAttivita>=0)
         {
        	 try {
        		 attivitaPassata = DataSource.getAttivita(idAttivita);
        			System.out.println("ATTIVITA ADD PANEL PASSATO FATTO1/"+attivitaPassata.getpTipoAttivita());
        		 visualizzaAttivita(attivitaPassata);
        		
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
         }
         
    	
		
	}
	
	public static void visualizzaAttivita(Attivita atti)
	{		Date data  = new Date(atti.getpDataAttivita());
			
			
			
			jdp.setDate(data);
		
		 	txtNote.setText(atti.getpDescrizioneAttività());
		 	
		 	
		 	if (attivitaPassata.getpFattaAttivita()==0)
	    	{	System.out.println("AGGIOENA CHECK false/"+attivitaPassata.getpFattaAttivita());
		 		jchk.setSelected(false);
	    	}else{
	    		System.out.println("AGGIOENA CHECK TRUE/+attivitaPassata.getpFattaAttivita()");
	    		jchk.setSelected(true);
	    	}
		 	System.err.println("ATTIICTAADDPANEL TIPO/  " + attivitaPassata.getpTipoAttivita());
		 	jComboTipoAttivita.setSelectedIndex(attivitaPassata.getpTipoAttivita());
		 	
		 	
		 	
	       
	      
	}
	public static JPanel impostaLayout()
	{
		int columnwidhttxt =  100;
			txtData = new JTextField(columnwidhttxt);
	     
			txtNote =  new JTextArea();;
		
			txtNote.setLineWrap(true);
	    	txtNote.setWrapStyleWord(true);
	    	
	    	Date data  = new Date(System.currentTimeMillis());
	    	if (attivitaPassata.getpIdAttivita()>0)
	    	{
	    	data  = new Date(attivitaPassata.getpDataAttivita());
	    	}
	    	jdp = new JXDatePicker(data, getDefaultLocale());
	    	
	    	jdp.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (jdp.getDate().getTime()>(System.currentTimeMillis()+(1000*60*60))) //se la data è maggiore della data di oggi + 1 ora allore e domani
					{
						jchk.setSelected(false);
					}else{
						jchk.setSelected(true);
					}
					
				}
			});
	    	
	    	jchk = new JCheckBox();
	    	jchk.setSelected(true);
	    	
	    	jchkAddAllarm = new JCheckBox();
	    	jchkAddAllarm.setSelected(false);
	    	
	    	jComboTipoAttivita = new JComboBox(DString.jAttivitaTipo);
	    	
	    	
	    	JPanel jCliente = new JPanel(new GridBagLayout());
	    	jCliente.add(jdp);
	    
	    	
	    	
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL ; c.insets = new Insets(5,0,0,10);
			c.anchor = GridBagConstraints.FIRST_LINE_START;
				
   	
   
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
   	//tipo attivita
	c.gridx = 0;c.gridy = 1;
   	jCliente.add(new JLabel(DString.atttitolo), c);
	c.gridx = 2;c.gridy = 1;
	jCliente.add(jComboTipoAttivita, c);
   	
   	//fatto
   	c.gridx = 0;c.gridy = 2;
   	jCliente.add(new JLabel(DString.attFatto), c);
   	
   	c.gridx = 2;c.gridy = 2;
   	jCliente.add(jchk, c);
   	
   	//attAggiungiCalendario
	c.gridx = 0;c.gridy = 3;c.gridwidth = 1;
   	jCliente.add(new JLabel(DString.attAggiungiCalendario), c);
   	
   	c.gridx = 2;c.gridy = 3;
   	jCliente.add(jchkAddAllarm, c);
   	
   	
   	c.gridx = 0;c.gridy = 4;c.gridwidth = 1;
   	jCliente.add(new JLabel(DString.attDescrizione), c);
   
   	c.gridx = 2;c.gridy = 4;c.gridwidth = 5;   //2 columns wide
   	jCliente.add(txtNote, c);
   	
	
   	
   	txtNote.setRows(20);
   	txtNote.setColumns(50);
   	txtNote.setLineWrap(true);
   	txtNote.setWrapStyleWord(true);
   	  Border border = BorderFactory.createLineBorder(Color.GRAY);
   	txtNote.setBorder(border);
   	jCliente.add(txtNote, c);
	return jCliente;
	}
	   
	
	

}
