import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class jDialogTipologiaNew extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			jDialogTipologiaNew dialog = new jDialogTipologiaNew();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public jDialogTipologiaNew() {
		setBounds(300, 100, 450, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		{
			JButton btnNewButton = new JButton(new ImageIcon(getClass().getResource(DData.iconaNuova)));
			btnNewButton.setAlignmentY(Component.TOP_ALIGNMENT);
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					jDialogAddTipologia dialog = new jDialogAddTipologia(-1, "", Color.RED.getRGB());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setModal(true);
					dialog.setVisible(true);
					
				}
			});
			contentPanel.add(btnNewButton);
		}
		{
			JPanelTipologia panel = new JPanelTipologia();
			contentPanel.add(panel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(DString.gOk);
			
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				/*
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
							dispose();					}
				});
				buttonPane.add(cancelButton);
				*/
			}
		}
	}

}
