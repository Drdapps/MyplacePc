import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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


public class ReferenteAddPanel extends JPanel {
	
	/**
	 * 
	 */
	public static Referenti referentePassato;
	private static final long serialVersionUID = 1L;
	private BorderLayout border_layout;
	
	public static JPanel p, plabels, ptext;
	
	public static JTextField txtNome, txtCognome, txtFunzione, txtTel, txtFax, txtCell, txtEmail;
	public static JTextArea txtNote;
	public static JScrollPane jscrollPane;
	ReferenteAddPanel(long idReferente){
		
		
		
		
		
		 border_layout = new BorderLayout(); // creo il layout manager e lo assegno al container
         setLayout(border_layout);
         JScrollPane jSReferente = new JScrollPane(impostaLayout());
         add(jSReferente, BorderLayout.CENTER);
         
         if (idReferente>=0)
         {
        	 try {
     			referentePassato = DataSource.getReferente(idReferente);
     			visualizzaReferente(referentePassato);
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
         }else{
        	 txtEmail.setText(ClientiPanel.txtCapMail.getText());
        	 txtTel.setText(ClientiPanel.txttel.getText());
        	 txtFax.setText(ClientiPanel.txtfax.getText());
         }
    	
		
	}
	
	public static void visualizzaReferente(Referenti referente)
	{
		
		 txtNome.setText(referente.getpNomeReferente());
	       txtCognome.setText(referente.getpCognomeReferente());
	       txtFunzione.setText(referente.getpFunzioneReferente());
	       txtTel.setText(referente.getpTelRefernte());
	       txtFax.setText(referente.getpFaxReferente());
	       txtCell.setText(referente.getpCelReferente());
	       txtEmail.setText(referente.getpMaileferente());
	       txtNote.setText(referente.getpMemoReferente());
	      
	}
	public static JPanel impostaLayout()
	{
		int columnwidhttxt =  35;
		 txtNome = new JTextField(columnwidhttxt);
	       txtCognome = new JTextField(columnwidhttxt);
	       txtFunzione = new JTextField(columnwidhttxt);
	       txtTel= new JTextField(columnwidhttxt);
	       txtFax= new JTextField(columnwidhttxt);
	       txtCell= new JTextField(columnwidhttxt);
	       txtEmail= new JTextField(columnwidhttxt);
	       txtNote =  new JTextArea();
	       txtNote.setLineWrap(true);
	    	txtNote.setWrapStyleWord(true);
		
	    	
		
		JPanel jCliente = new JPanel(new GridBagLayout());
   	GridBagConstraints c = new GridBagConstraints();
   	c.fill = GridBagConstraints.HORIZONTAL ; c.insets = new Insets(5,0,0,10);
   	c.anchor = GridBagConstraints.FIRST_LINE_START;
   	int gridwidthtxt = 5;
   	
   	
   	c.gridx = 0;c.gridy = 0;
   	jCliente.add(new JLabel(DString.RefNome), c);
   	
   	c.gridx = 2;c.gridy = 0;c.gridwidth =5;   //2 columns wide
   	jCliente.add(txtNome, c);
   	
   	
   	c.gridx = 0;c.gridy = 1;c.gridwidth = 1;
   	jCliente.add(new JLabel(DString.RefCognome), c);
   
   	c.gridx = 2;c.gridy = 1;c.gridwidth = 5;   //2 columns wide
   	jCliente.add(txtCognome, c);
   	
   	
   	c.gridx = 0;c.gridy = 2;
   	jCliente.add(new JLabel(DString.RefFunzione), c);
   
   	c.gridx = 2;c.gridy = 2;c.gridwidth = 5;   //2 columns wide
   	jCliente.add(txtFunzione, c);
   	
   	
   	c.gridx = 0;c.gridy = 3;
   	jCliente.add(new JLabel(DString.RefTele), c);
   	
   	c.gridx = 2;c.gridy = 3;c.gridwidth = 5;   //2 columns wide
   	jCliente.add(txtTel, c);
   	
   	
   	c.gridx = 0;c.gridy = 4;
   	jCliente.add(new JLabel(DString.RefFax), c);
   
   	c.gridx = 2;c.gridy = 4;c.gridwidth = 5;   //2 columns wide
   	jCliente.add(txtFax, c);
   	
   	
   	c.gridx = 0;c.gridy = 5;
   	jCliente.add(new JLabel(DString.RefCell), c);
   	
   	c.gridx = 2;c.gridy = 5;c.gridwidth = 5;   //2 columns wide
   	jCliente.add(txtCell, c);
   	
   	
   	
   	c.gridx = 0;c.gridy = 6;
   	jCliente.add(new JLabel(DString.RefEmail), c);
   	c.gridx = 2;c.gridy = 6;c.gridwidth = 5;   //2 columns wide
   	jCliente.add(txtEmail, c);
   	
   	
   	
   	c.gridx = 0;c.gridy = 7;
   	jCliente.add(new JLabel(DString.RefNota), c);
   	c.gridx = 2;c.gridy = 7;c.gridwidth = 5;   //2 columns wide
   	txtNote.setRows(10);
   	txtNote.setLineWrap(true);
   	txtNote.setWrapStyleWord(true);
   	  Border border = BorderFactory.createLineBorder(Color.GRAY);
   	txtNote.setBorder(border);
   	jCliente.add(txtNote, c);
	return jCliente;
	}
	   
	
	

}
