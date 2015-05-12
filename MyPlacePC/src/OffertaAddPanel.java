import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdesktop.swingx.JXDatePicker;

public class OffertaAddPanel extends JPanel {
	
	/**
	 * 
	 */
	
	
	
	public static Offerta offertaPassata;
	
	private BorderLayout border_layout;
	
	public static JPanel p, plabels, ptext;
	public static JLabel lblCliente, lbldescrizione, lblImporto, lblpercentuale, lbldata, lblNote,lblNomeCliente;
	public static JButton bttdatePicker;
	public static JTextField txtData, txtImporto ;
	public static JComboBox spnPercentuale;
	public static JCheckBox chkACliente;
	public static JTextArea txtDecrizione, txtNote;
	public static JScrollPane jscrollPane;
	public static JXDatePicker jdp;
	
	public static Offerta  Offe;
	public static boolean giaCliente;
	
	OffertaAddPanel(long idOfferta, String nomeCliente, long idCliente){
		
		try {
		
   		 offertaPassata = DataSource.getOfferta(idOfferta);
   		 if (idOfferta==-1)
   		 {
   			 long giaClienteLong = DataSource.getClientID(idCliente).getpSpare1();
   	   	  System.out.println("JDIALOG OFFERTA A CLIENTE/" + idOfferta + "/" +giaClienteLong );
   	   		 giaCliente = (giaClienteLong==1L)?true:false;
   		 }else{
   			 giaCliente = offertaPassata.getpAcliente();
   		 }
   		
		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
		
		 border_layout = new BorderLayout(); // creo il layout manager e lo assegno al container
         setLayout(border_layout);
         JScrollPane jSOffe = new JScrollPane(impostaLayout());
         add(jSOffe, BorderLayout.CENTER);
         
         System.out.println("JDIALOG OFFERTA A CLIENTE/" + giaCliente + "/" + offertaPassata.getpNomeCliente() + "/" );
         if (idOfferta>=0)
         {
        	 try {
        		 offertaPassata = DataSource.getOfferta(idOfferta);
        		
        		 
        		 visualizzaOfferta(offertaPassata);
        		
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
         }else{
        	 lblNomeCliente.setText(nomeCliente);
        	 chkACliente.setSelected(giaCliente);
        	 spnPercentuale.setSelectedIndex(1);
         }
         txtImporto.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				
				double value=0;
				String importoStringa = txtImporto.getText();
			
				importoStringa = importoStringa.replace(",", ".");
				
				try {
					value =Double.valueOf(importoStringa);
				} catch (Exception e2) {
					DGen.showMessage(DString.gAttenzione, DString.msgErroreImporto);
				}
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
						
				
			}
		});
    	
		
	}
	
	public static void visualizzaOfferta(Offerta offe)
	{		Date data  = new Date(offe.getpDataChiusura());
			System.out.println("JDIALOG OFFERTA A CLIENTE 1/" + offe.getpNomeCliente() + "/" + offe.getpAcliente()  );
			jdp.setDate(data);
			int itemSPinner = (int) (offe.getpPercentuale()/10);
			spnPercentuale.setSelectedIndex(itemSPinner);
			lblNomeCliente.setText(offe.getpNomeCliente());
			txtDecrizione.setText(offe.getpDescrizioneOfferta());
			txtImporto.setText(DGen.DoubleToString(offe.getpImportoOfferta(),2));
			txtNote.setText(offe.getpNota());
			System.out.println("JDIALOG OFFERTA A CLIENTE/" + offe.getpNomeCliente() + "/" + offe.getpAcliente()  );
			chkACliente.setSelected(offe.getpAcliente());
			
		 		 	
		 	
	       
	      
	}
	public static JPanel impostaLayout()
	{	
		String comboPercentuale []={"0","10","20", "30", "40", "50", "60" , "70", "80", "90", "100"};
		spnPercentuale = new JComboBox(comboPercentuale);
		
		
		int columnwidhttxtNota =  35;
		int columnwidhttxtimporto =  10;
			lblNomeCliente = new JLabel();
			txtData = new JTextField(columnwidhttxtNota);
			txtImporto = new  JTextField(columnwidhttxtimporto);
			chkACliente = new JCheckBox();
			
			txtNote =  new JTextArea();
			txtNote.setLineWrap(true);
	    	txtNote.setWrapStyleWord(true);
	    	
	    	txtDecrizione =  new JTextArea();
	    	txtDecrizione.setLineWrap(true);
	    	txtDecrizione.setWrapStyleWord(true);
	    	
	    	Date data  = new Date(System.currentTimeMillis());
	    	if (offertaPassata.getpIdOfferta()>0)
	    	{
	    	data  = new Date(offertaPassata.getpDataChiusura());
	    	}
	    	jdp = new JXDatePicker(data, getDefaultLocale());
	    	
	    	jdp.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
				
					
				}
			});
	    	
	    	
	    	
	    	JPanel jCliente = new JPanel(new GridBagLayout());
	    	jCliente.add(jdp);
	    
	    	
	    	
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL ; c.insets = new Insets(5,0,0,10);
			c.anchor = GridBagConstraints.FIRST_LINE_START;
				
   	
   
	c.gridx = 0;c.gridy = 0;
	jCliente.add(new JLabel(DString.offAzienda), c);	
	
	c.gridx = 2;c.gridy = 0;
	jCliente.add(lblNomeCliente, c);	
					
	
	
	///
	
	c.gridx = 0;c.gridy = 1;
	jCliente.add(new JLabel(DString.offCliente), c);	
	c.gridx = 2;c.gridy = 1;
 	jCliente.add(chkACliente, c);
	//chkACliente
	
 	c.gridx = 0;c.gridy = 2;
	jCliente.add(new JLabel(DString.offDescrizione), c);
	c.gridx = 2;c.gridy = 2;c.gridwidth = 5;   
   	jCliente.add(txtDecrizione, c);
   	txtDecrizione.setRows(3);
   	txtDecrizione.setColumns(30);
   	txtDecrizione.setLineWrap(true);
   	txtDecrizione.setWrapStyleWord(true);
   	  Border border = BorderFactory.createLineBorder(Color.GRAY);
   	txtDecrizione.setBorder(border);
   	
   	
   	c.gridx = 0;c.gridy = 3;
   	jCliente.add(new JLabel(DString.offData), c);
	
	c.gridx = 2;c.gridy = 3;c.gridwidth =3;   //2 columns wide
   	jCliente.add(jdp,c);
   	
   	
   	
   	
   	//c.gridx = 2;c.gridy = 1;
   	//jCliente.add(jchk, c);
   	
	c.gridx = 0;c.gridy = 4;c.gridwidth = 1;
   	jCliente.add(new JLabel(DString.offpercentuale), c);
   	
   	c.gridx = 2;c.gridy = 4;c.gridwidth = 3;
   	jCliente.add(spnPercentuale, c);
   	
   	
   	c.gridx = 0;c.gridy = 5;c.gridwidth = 1;
   	jCliente.add(new JLabel(DString.offImporto), c);
   	
   	c.gridx = 2;c.gridy = 5;c.gridwidth = 3;
   	jCliente.add(txtImporto, c);
   	
   	
   	c.gridx = 0;c.gridy = 6;c.gridwidth = 1;
   	jCliente.add(new JLabel(DString.offNote), c);
   
   	c.gridx = 2;c.gridy = 6;c.gridwidth = 5;   //2 columns wide
   	jCliente.add(txtNote, c);
   	
	
   	
   	txtNote.setRows(10);
   	txtNote.setColumns(30);
   	txtNote.setLineWrap(true);
   	txtNote.setWrapStyleWord(true);
   	  
   	txtNote.setBorder(border);
   	jCliente.add(txtNote, c);
	return jCliente;
	}
	   
	
	

}
