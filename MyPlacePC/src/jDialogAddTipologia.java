import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;


public class jDialogAddTipologia extends JDialog {
	public jDialogAddTipologia() {
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JButton bttColore;
	

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public jDialogAddTipologia(final long idTipologia, final String tipoDescrizione, int colore){ 
        setBounds(300, 300, 300, 150); 
        DData.coloreTipologiaSelezionato = colore;
        getContentPane().setLayout(new BorderLayout()); 
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); 
        getContentPane().add(contentPanel, BorderLayout.CENTER); 
        SpringLayout sl_contentPanel = new SpringLayout(); 
        contentPanel.setLayout(sl_contentPanel); 
          
        JLabel lblNewLabel = new JLabel(DString.tipNuova);
        sl_contentPanel.putConstraint(SpringLayout.EAST, lblNewLabel, 155, SpringLayout.WEST, contentPanel); 
        contentPanel.add(lblNewLabel); 
          
        textField = new JTextField(); 
        sl_contentPanel.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, textField); 
        sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel, -6, SpringLayout.NORTH, textField); 
        sl_contentPanel.putConstraint(SpringLayout.NORTH, textField, 25, SpringLayout.NORTH, contentPanel); 
        sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 5, SpringLayout.WEST, contentPanel); 
        sl_contentPanel.putConstraint(SpringLayout.SOUTH, textField, -19, SpringLayout.SOUTH, contentPanel); 
        contentPanel.add(textField); 
         textField.setText(tipoDescrizione);
        textField.setColumns(30); 
        { 
            final JButton btnNewButton = new JButton(""); 
            btnNewButton.setBackground(new Color(DData.coloreTipologiaSelezionato)); 
            btnNewButton.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent arg0) { 
                    //JColorChooser tcc = new JColorChooser(Color.RED); 
                    Color tcc = JColorChooser.showDialog(jDialogAddTipologia.this, 
                             "Choose Background Color", 
                             new Color(DData.coloreTipologiaSelezionato)); 
                    System.out.println("ADDTIPO =" + DData.coloreTipologiaSelezionato);
                  
                     //converte il Color in int di RGB 
                     btnNewButton.setBackground(tcc); 
                     
                     DData.coloreTipologiaSelezionato = tcc.getRGB();
                       
                      
                } 
            }); 
            sl_contentPanel.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, btnNewButton); 
            sl_contentPanel.putConstraint(SpringLayout.NORTH, btnNewButton, 25, SpringLayout.NORTH, contentPanel); 
            sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnNewButton, -19, SpringLayout.SOUTH, contentPanel); 
            sl_contentPanel.putConstraint(SpringLayout.WEST, btnNewButton, 239, SpringLayout.WEST, contentPanel); 
            sl_contentPanel.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, contentPanel); 
            contentPanel.add(btnNewButton); 
        } 
          
        { 
            JPanel buttonPane = new JPanel(); 
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
            getContentPane().add(buttonPane, BorderLayout.SOUTH); 
            { 
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						
						if (tipoDescrizione.length()==0)
						{//cliente nuovo
							System.err.println("OK nuovo/"+idTipologia+"/" + textField.getText() + "/" + DData.coloreTipologiaSelezionato);
							//System.err.println("OK nuovo" +  + bttColore.getBackground().getRGB());
							
							DataSource.aggiungiTipologia(textField.getText(), DData.coloreTipologiaSelezionato);
							JPanelTipologia.refreshTabellaTipologia();

    			    		ListClient.JcomboElencoTipologie.setModel(new DefaultComboBoxModel(DGen.itemComboTipologieCompresaTutte()));
    			    	
    			    		ListClient.refreshTabella();
    			    		ClientiPanel.jComboTipologia.setModel(new DefaultComboBoxModel(DGen.itemComboTipologieEsclusaTutte()));
    			    		
						
						}else{
							System.err.println("OK aggiorna/"+idTipologia+"/" + textField.getText());
							DataSource.aggiornaTipologiaID(idTipologia, textField.getText(),DData.coloreTipologiaSelezionato,0);
							JPanelTipologia.refreshTabellaTipologia();

    			    		ListClient.JcomboElencoTipologie.setModel(new DefaultComboBoxModel(DGen.itemComboTipologieCompresaTutte()));
    			    	
    			    		ListClient.refreshTabella();
    			    		ClientiPanel.jComboTipologia.setModel(new DefaultComboBoxModel(DGen.itemComboTipologieEsclusaTutte()));
    			    		
						}
						
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
