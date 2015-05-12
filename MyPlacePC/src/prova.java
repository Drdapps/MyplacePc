import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;


public class prova extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Create the panel.
	 */
	public prova() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("ID");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, this);
		add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -71, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 119, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 10, SpringLayout.NORTH, this);
		add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Azienda");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 13, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -6, SpringLayout.WEST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 318, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, -353, SpringLayout.EAST, this);
		add(lblNewLabel_2);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.EAST, textField, -10, SpringLayout.EAST, this);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Indirizzo");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 6, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 10, SpringLayout.WEST, this);
		add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 72, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_3, -16, SpringLayout.WEST, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 6, SpringLayout.SOUTH, comboBox);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Citta");
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -16, SpringLayout.WEST, lblNewLabel_4);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 6, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_4, 10, SpringLayout.WEST, lblNewLabel_2);
		add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 5, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 19, SpringLayout.EAST, lblNewLabel_4);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Nazione");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_6, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, -6, SpringLayout.WEST, lblNewLabel_6);
		add(lblNewLabel_6);
		
		textField_4 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_4, 35, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -5, SpringLayout.NORTH, textField_4);
		springLayout.putConstraint(SpringLayout.WEST, textField_4, 566, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, textField_4, -10, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_6, -6, SpringLayout.WEST, textField_4);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Tel:");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_5, 10, SpringLayout.WEST, this);
		add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_5, -16, SpringLayout.WEST, textField_3);
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 6, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, textField_3, 0, SpringLayout.WEST, textField_1);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Fax");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_7, 192, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, textField_3, -6, SpringLayout.WEST, lblNewLabel_7);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_7, -3, SpringLayout.NORTH, lblNewLabel_5);
		add(lblNewLabel_7);
		
		textField_5 = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_7, -3, SpringLayout.WEST, textField_5);
		springLayout.putConstraint(SpringLayout.WEST, textField_5, 218, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, textField_5, 7, SpringLayout.SOUTH, textField_1);
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("www");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_8, 61, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_4, -6, SpringLayout.NORTH, lblNewLabel_8);
		springLayout.putConstraint(SpringLayout.EAST, textField_5, -28, SpringLayout.WEST, lblNewLabel_8);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_8, 0, SpringLayout.EAST, lblNewLabel_2);
		add(lblNewLabel_8);
		
		textField_6 = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_6, -6, SpringLayout.NORTH, textField_6);
		springLayout.putConstraint(SpringLayout.NORTH, textField_6, 61, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textField_6, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_6, -10, SpringLayout.EAST, this);
		add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Longitudine");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_9, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_5, -14, SpringLayout.NORTH, lblNewLabel_9);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_9, 94, SpringLayout.NORTH, this);
		add(lblNewLabel_9);
		
		textField_7 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField_7, 72, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_9, -1, SpringLayout.WEST, textField_7);
		springLayout.putConstraint(SpringLayout.NORTH, textField_7, 12, SpringLayout.SOUTH, textField_3);
		springLayout.putConstraint(SpringLayout.EAST, textField_7, 0, SpringLayout.EAST, lblNewLabel_7);
		add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Longitudine");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_10, 11, SpringLayout.SOUTH, textField_5);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_10, 309, SpringLayout.WEST, this);
		add(lblNewLabel_10);
		
		textField_8 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_8, 10, SpringLayout.SOUTH, textField_6);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_10, -6, SpringLayout.WEST, textField_8);
		springLayout.putConstraint(SpringLayout.WEST, textField_8, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_8, -169, SpringLayout.EAST, this);
		add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Nota");
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_9, -9, SpringLayout.NORTH, lblNewLabel_11);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_11, 117, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_11, 0, SpringLayout.WEST, lblNewLabel_3);
		add(lblNewLabel_11);
		
		JTextArea textArea = new JTextArea();
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_10, -29, SpringLayout.NORTH, textArea);
		springLayout.putConstraint(SpringLayout.SOUTH, textArea, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, textArea, 6, SpringLayout.SOUTH, lblNewLabel_11);
		springLayout.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.EAST, textArea, 0, SpringLayout.EAST, textField);
		add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("12345");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, textField_1);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -6, SpringLayout.WEST, comboBox);
		add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		add(panel);

	}
}
