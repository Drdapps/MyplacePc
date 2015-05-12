import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;


public class DialogAddTipologia_new_old extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JButton bttColore;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public DialogAddTipologia_new_old(final long idTipologia, final String tipoDescrizione) {
		setBounds(300, 100, 300, 135);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel_1 = new JLabel(DString.tipNuova);
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 0;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 4;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 0;
			gbc_textField.gridy = 2;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
			textField.setText(tipoDescrizione);
		}
		{
			bttColore = new JButton("");
			bttColore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//jColorChooser
				}
			});
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton.gridx = 4;
			gbc_btnNewButton.gridy = 2;
			bttColore.setBackground(Color.RED);
			bttColore.setPreferredSize(new Dimension(DData.dimPulsantiy, DData.dimPulsantiy));
			contentPanel.add(bttColore);
			contentPanel.add(bttColore, gbc_btnNewButton);
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
							System.err.println("OK nuovo/"+idTipologia+"/" + textField.getText());
							
							DataSource.aggiungiTipologia(textField.getText(), bttColore.getBackground().getRGB());
							JPanelTipologia.refreshTabellaTipologia();

    			    		ListClient.JcomboElencoTipologie.setModel(new DefaultComboBoxModel(DGen.itemComboTipologieCompresaTutte()));
    			    	
    			    		ListClient.refreshTabella();
    			    		ClientiPanel.jComboTipologia.setModel(new DefaultComboBoxModel(DGen.itemComboTipologieEsclusaTutte()));
    			    		
						
						}else{
							System.err.println("OK aggiorna/"+idTipologia+"/" + textField.getText());
							DataSource.aggiornaTipologiaID(idTipologia, textField.getText(),bttColore.getBackground().getRGB(),0);
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
