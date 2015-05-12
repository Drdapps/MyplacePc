import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;


public class JDialogLingua extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JComboBox jCLingua;
	public static boolean resultDialogLingua = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogLingua dialog = new JDialogLingua();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogLingua() {
		setBounds(100, 100, 450, 104);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("Select Language");
			contentPanel.add(lblNewLabel, BorderLayout.WEST);
		}
		{
			 jCLingua = new JComboBox();
			 jCLingua.addItem("English"); 
	            jCLingua.addItem("Italiano"); 
			contentPanel.add(jCLingua,BorderLayout.CENTER);
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
					DGen.savePreferencesInt(DString.PLINGUA, jCLingua.getSelectedIndex());
					resultDialogLingua = true;
					DGen.savePreferencesInt(DString.PPRIMAVOLTA, 1);
					
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
