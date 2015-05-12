import java.awt.BorderLayout; 
import java.awt.FlowLayout; 
  
import javax.swing.JButton; 
import javax.swing.JDialog; 
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.border.EmptyBorder; 
  
import java.awt.GridBagLayout; 
  
import javax.swing.JLabel; 
  
import java.awt.GridBagConstraints; 
  
import javax.swing.JTextField; 
  
import java.awt.Insets; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
  
public class JDialogNuovaPosizione extends JDialog { 
  
    private final JPanel contentPanel = new JPanel(); 
    private JTextField txtLati; 
    private JTextField txtLongi; 
  
    /** 
     * Launch the application. 
     */
   
    /** 
     * Create the dialog. 
     */
    public JDialogNuovaPosizione(double latitudine, double longitudine) { 
        setBounds(300, 100, 300, 150); 
        getContentPane().setLayout(new BorderLayout()); 
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        getContentPane().add(contentPanel, BorderLayout.CENTER); 
        GridBagLayout gbl_contentPanel = new GridBagLayout(); 
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0}; 
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0}; 
        gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE}; 
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE}; 
        contentPanel.setLayout(gbl_contentPanel); 
        { 
            JLabel lblNewLabel = new JLabel(DString.dpLati); 
            GridBagConstraints gbc_lblNewLabel = new GridBagConstraints(); 
            gbc_lblNewLabel.anchor = GridBagConstraints.SOUTH; 
            gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5); 
            gbc_lblNewLabel.gridx = 0; 
            gbc_lblNewLabel.gridy = 0; 
            contentPanel.add(lblNewLabel, gbc_lblNewLabel); 
        } 
        { 
            txtLati = new JTextField(); 
            GridBagConstraints gbc_textField = new GridBagConstraints(); 
            gbc_textField.insets = new Insets(0, 0, 5, 0); 
            gbc_textField.fill = GridBagConstraints.HORIZONTAL; 
            gbc_textField.gridx = 3; 
            gbc_textField.gridy = 0; 
            contentPanel.add(txtLati, gbc_textField); 
            txtLati.setColumns(10); 
          
            txtLati.setText(latitudine+"");
        } 
        { 
            JLabel lblNewLabel_1 = new JLabel(DString.dpLongi); 
            GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints(); 
            gbc_lblNewLabel_1.anchor = GridBagConstraints.SOUTH; 
            gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5); 
            gbc_lblNewLabel_1.gridx = 0; 
            gbc_lblNewLabel_1.gridy = 1; 
            contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1); 
        } 
        { 
            txtLongi = new JTextField(); 
            GridBagConstraints gbc_textField_1 = new GridBagConstraints(); 
            gbc_textField_1.fill = GridBagConstraints.HORIZONTAL; 
            gbc_textField_1.gridx = 3; 
            gbc_textField_1.gridy = 1; 
            contentPanel.add(txtLongi, gbc_textField_1); 
            txtLongi.setColumns(10); 
            txtLongi.setText(longitudine+"");
           
        } 
        { 
            JPanel buttonPane = new JPanel(); 
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
            getContentPane().add(buttonPane, BorderLayout.SOUTH); 
            { 
                JButton okButton = new JButton(DString.dApplica); 
                okButton.addActionListener(new ActionListener() { 
                      
                    @Override
                    public void actionPerformed(ActionEvent arg0) { 
                    	
                         if (controlloErroreCoordinate()==false)
                         {		
                        	 	DData.latitudinePartenza = Double.parseDouble(txtLati.getText());
                        	 	DData.longitudinePartenza= Double.parseDouble(txtLongi.getText());
		                    	DGen.savePreferencesDouble(DString.PLATI, DData.latitudinePartenza);
		                    	DGen.savePreferencesDouble(DString.PLONGI, Double.parseDouble(txtLongi.getText()));
		                    	
		                    	DGen.aggiornaDistanza(Double.parseDouble(txtLati.getText()), Double.parseDouble(txtLongi.getText()));
		                    	
		                    	ListClient.refreshTabella();
		                    	dispose(); 
		                    	
                         }else{
                        	 int i = DGen.showConfirm(DString.gAttenzione, DString.msgErroreCoordinate, JOptionPane.YES_NO_OPTION);
             				if (i==JOptionPane.YES_OPTION)
             				{
             					dispose(); 
             				}
                         }
                     
                    }

					private boolean controlloErroreCoordinate() {
						
				        {
				        	boolean result = false;
				    		
				        	double lati = 0;
				    		double longi = 0;
				    		try {
				    			//longi = Long.parseLong(txtlongi.getText());
				    			longi = Double.parseDouble(txtLongi.getText());
				    		} catch (Exception e) {
				    			result = true;
				    			
				    		
				    		}
				    		try {
				    			lati = Double.parseDouble(txtLati.getText());
				    		} catch (Exception e) {
				    			result = true;
				    			
				    		}
				    		
				    		return result;
				        }
					} 
                }); 
                //okButton.setActionCommand("OK"); 
                buttonPane.add(okButton); 
                getRootPane().setDefaultButton(okButton); 
            } 
            { 
                JButton cancelButton = new JButton(DString.dAnnulla); 
                //cancelButton.setActionCommand("Cancel"); 
                cancelButton.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) { 
                          
                          dispose(); 
                    } 
                }); 
                buttonPane.add(cancelButton); 
            } 
        } 
        
    } 
   
  
} 
