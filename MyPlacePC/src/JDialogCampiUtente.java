import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JCheckBox;


public class JDialogCampiUtente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtfree4,txtfree3,txtfree2,txtfree1;
	private JCheckBox chkfree1,chkfree2,chkfree3,chkfree4;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogCampiUtente dialog = new JDialogCampiUtente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogCampiUtente() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			
			
			JLabel lblDescrizione = new JLabel(DString.jdDescrizioneCapi);
			GridBagConstraints gbc_lblDescrizione = new GridBagConstraints();
			gbc_lblDescrizione.anchor = GridBagConstraints.WEST;
			gbc_lblDescrizione.gridwidth = 3;
			gbc_lblDescrizione.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescrizione.gridx = 1;
			gbc_lblDescrizione.gridy = 0;
			contentPanel.add(lblDescrizione, gbc_lblDescrizione);
		}
		{
			JLabel lblVisualizza = new JLabel(DString.jdvisualizzaCampi);
			GridBagConstraints gbc_lblVisualizza = new GridBagConstraints();
			gbc_lblVisualizza.insets = new Insets(0, 0, 5, 0);
			gbc_lblVisualizza.gridx = 6;
			gbc_lblVisualizza.gridy = 0;
			contentPanel.add(lblVisualizza, gbc_lblVisualizza);
		}
		{
			JLabel label = new JLabel("1.");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 1;
			contentPanel.add(label, gbc_label);
		}
		{
			txtfree1 = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 5;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 1;
			contentPanel.add(txtfree1, gbc_textField);
			txtfree1.setColumns(10);
			txtfree1.setText(DData.hashProprieta.get(1).getpTxtProprieta());
		}
		{
			chkfree1 = new JCheckBox("");
			GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
			gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxNewCheckBox.gridx = 6;
			gbc_chckbxNewCheckBox.gridy = 1;
			contentPanel.add(chkfree1, gbc_chckbxNewCheckBox);
			chkfree1.setSelected(DData.hashProprieta.get(1).getpBooProprieta());
		}
		{
			JLabel label = new JLabel("2.");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 2;
			contentPanel.add(label, gbc_label);
		}
		{
			txtfree2 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.gridwidth = 5;
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 2;
			contentPanel.add(txtfree2, gbc_textField_1);
			txtfree2.setColumns(10);
			txtfree2.setText(DData.hashProprieta.get(2).getpTxtProprieta());
		}
		{
			chkfree2 = new JCheckBox("");
			GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
			gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxNewCheckBox_1.gridx = 6;
			gbc_chckbxNewCheckBox_1.gridy = 2;
			contentPanel.add(chkfree2, gbc_chckbxNewCheckBox_1);
			chkfree2.setSelected(DData.hashProprieta.get(2).getpBooProprieta());
		}
		{
			JLabel label = new JLabel("3.");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.gridx = 0;
			gbc_label.gridy = 3;
			contentPanel.add(label, gbc_label);
		}
		{
			txtfree3 = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.gridwidth = 5;
			gbc_textField_2.insets = new Insets(0, 0, 5, 5);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 3;
			contentPanel.add(txtfree3, gbc_textField_2);
			txtfree3.setColumns(10);
			txtfree3.setText(DData.hashProprieta.get(3).getpTxtProprieta());
		}
		{
			chkfree3 = new JCheckBox("");
			GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
			gbc_chckbxNewCheckBox_2.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxNewCheckBox_2.gridx = 6;
			gbc_chckbxNewCheckBox_2.gridy = 3;
			contentPanel.add(chkfree3, gbc_chckbxNewCheckBox_2);
			chkfree3.setSelected(DData.hashProprieta.get(3).getpBooProprieta());
		}
		{
			JLabel label = new JLabel("4.");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(0, 0, 0, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 4;
			contentPanel.add(label, gbc_label);
		}
		{
			txtfree4 = new JTextField();
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.gridwidth = 5;
			gbc_textField_3.insets = new Insets(0, 0, 0, 5);
			gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_3.gridx = 1;
			gbc_textField_3.gridy = 4;
			contentPanel.add(txtfree4, gbc_textField_3);
			txtfree4.setColumns(10);
			txtfree4.setText(DData.hashProprieta.get(4).getpTxtProprieta());
		}
		{
			chkfree4 = new JCheckBox("");
			GridBagConstraints gbc_chckbxNewCheckBox_3 = new GridBagConstraints();
			gbc_chckbxNewCheckBox_3.gridx = 6;
			gbc_chckbxNewCheckBox_3.gridy = 4;
			contentPanel.add(chkfree4, gbc_chckbxNewCheckBox_3);
			chkfree4.setSelected(DData.hashProprieta.get(4).getpBooProprieta());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(DString.gSalva);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// una variabile proprieta per ogni classe perche altrimento l la hasmap di dava sempre l'ulitma
						
						Proprieta pro1 = new Proprieta();
						pro1.setPIdPropieta(1);pro1.setpTxtProprieta(txtfree1.getText());
						pro1.setpBooProprieta(chkfree1.isSelected());pro1.setPLongProprieta(0);
						DataSource.aggiornaProprieta(1, txtfree1.getText(), chkfree1.isSelected(), 0);
						DData.hashProprieta.put(1, pro1);
					
						
						Proprieta pro2 = new Proprieta();
						pro2.setPIdPropieta(2);pro2.setpTxtProprieta(txtfree2.getText());
						pro2.setpBooProprieta(chkfree2.isSelected());pro2.setPLongProprieta(0);
						DataSource.aggiornaProprieta(2, txtfree2.getText(), chkfree2.isSelected(), 0);
						DData.hashProprieta.put(2, pro2);
					
						
						Proprieta pro3 = new Proprieta();
						pro3.setPIdPropieta(3);pro3.setpTxtProprieta(txtfree3.getText());
						pro3.setpBooProprieta(chkfree3.isSelected());pro3.setPLongProprieta(0);
						DataSource.aggiornaProprieta(3, txtfree3.getText(), chkfree3.isSelected(), 0);
						DData.hashProprieta.put(3, pro3);
						
						
						Proprieta pro4 = new Proprieta();
						pro4.setPIdPropieta(4);pro4.setpTxtProprieta(txtfree4.getText());
						pro4.setpBooProprieta(chkfree4.isSelected());pro4.setPLongProprieta(0);
						DataSource.aggiornaProprieta(4, txtfree4.getText(), chkfree4.isSelected(), 0);
						DData.hashProprieta.put(4, pro4);
						
						ClientiPanel.aggiornaCampiUtente();
						dispose();
					}
				});
				
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(DString.gAnnulla);
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
