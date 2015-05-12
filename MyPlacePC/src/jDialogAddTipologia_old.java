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
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class jDialogAddTipologia_old extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton bttColore;

	/**
	 * Launch the application.
	 */
	/*
	

	/**
	 * Create the dialog.
	 */
	public jDialogAddTipologia_old(final long idTipologia, final String tipoDescrizione) {
		setBounds(300, 100, 300, 125);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		{
			JLabel lblNewLabel = new JLabel(DString.tipNuova);
			contentPanel.add(lblNewLabel);
			
		}
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setColumns(10);
			textField.setText(tipoDescrizione);
			
		}
		{
			bttColore = new JButton(" ");
			bttColore.setBackground(Color.RED);
			bttColore.setPreferredSize(new Dimension(150, 150));
			contentPanel.add(bttColore);
			bttColore.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
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
