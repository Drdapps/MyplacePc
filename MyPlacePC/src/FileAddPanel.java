import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.Locale.Category;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sourceforge.jdatepicker.JDatePicker;

import org.jdesktop.swingx.JXDatePicker;

public class FileAddPanel extends JPanel {
	
	/**
	 * 
	 */
	
	
	
	public static FileSincronizzati filePassato;
	
	private BorderLayout border_layout;
	
	public static JPanel p, plabels, ptext;
	public static JButton bttfilePicker;
	public static JTextField txtNomeFile;
	public static JLabel txtDataFile;
	public static JLabel txtDimensioneFile;
	public static JTextField txtpathFile;
	public static JTextArea txtNote;
	
	public static JScrollPane jscrollPane;
	
	public static FileSincronizzati file;
	
	
	FileAddPanel(long idFile){
		
		 try {
			 filePassato = DataSource.getFile(idFile);
			System.out.println("PASSATO FATTO/"+filePassato.getpIdFile());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
		
		
		
		 border_layout = new BorderLayout(); // creo il layout manager e lo assegno al container
         setLayout(border_layout);
         JScrollPane jSOffe = new JScrollPane(impostaLayout());
         add(jSOffe, BorderLayout.CENTER);
         
      
         if (idFile>=0)
         {
        	 try {
        		 filePassato = DataSource.getFile(idFile);
     			
        		 visualizzaFile(filePassato);
        		
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
         }
         
    	
		
	}
	
	public static void visualizzaFile(FileSincronizzati file)
	{		//Date data  = new Date(atti.getpDataAttivita());
			
			
			
		txtNomeFile.setText(file.getpNomeFileOriginario());
		
			NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
			Locale fmtLocale = Locale.getDefault(Category.FORMAT);
			NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
			DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, getDefaultLocale());
		
		
			txtDataFile.setText(dateFormatter.format(new Date(file.getpDataFile())));
		 	txtNote.setText(file.getpDescrizione());
		 	long dimensione = file.getpDimensioneFile()/1024;
		 	txtDimensioneFile.setText(dimensione +"k");
		 	if (file.getpIdFile()>0)
		 	{	String destinationPath = DData.pathSetting+DData.pathInDropbox +File.separator + file.getpNomeFileOModificato();
		 		txtpathFile.setText(destinationPath);
		 		
		 	}else{
		 		
		 	}
		 	
		 	
		 	
		 	
		 	
		 	
		 	
	       
	      
	}
	public static JPanel impostaLayout()
	{
		int columnwidhttxt =  35;
			txtpathFile =  new JTextField(0);
			txtNomeFile = new JTextField(columnwidhttxt);
			txtDataFile = new JLabel();
			txtDimensioneFile = new JLabel();
			txtNote =  new JTextArea();
			txtNote.setLineWrap(true);
	    	txtNote.setWrapStyleWord(true);
	    	
	    	bttfilePicker =  new JButton(new ImageIcon(FileAddPanel.class.getResource(DData.iconaCartella)));
	    	bttfilePicker.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					scegliFile();
					
				}
			});
	    	
	    	
	    	JPanel jCliente = new JPanel(new GridBagLayout());
	    	
	    
	    	
	    	
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL ; c.insets = new Insets(5,0,0,10);
			c.anchor = GridBagConstraints.FIRST_LINE_START;
					int gridwidthtxt = 5;
   	
					
					
   	c.gridx = 0;c.gridy = 0;
   	jCliente.add(new JLabel(DString.fileNome), c);
	/*
   c.gridx = 2;c.gridy = 0;c.gridwidth =1;   //2 columns wide
   	jCliente.add(txtData, c);
   	/*
   	/*
   	c.gridx = 5;c.gridy = 0;c.gridwidth =1;   //2 columns wide
   	jCliente.add(bttdatePicker, c);
   	*/
	c.gridx = 1;c.gridy = 0;c.gridwidth =2;   //2 columns wide
   	jCliente.add(txtNomeFile,c);
   	
   	c.gridx = 3;c.gridy = 0;c.gridwidth =1;   //2 columns wide
   	jCliente.add(bttfilePicker,c);
   	
   	
   	c.gridx = 0;c.gridy = 1;
   	jCliente.add(new JLabel(DString.fileData), c);
   	
   	c.gridx = 1;c.gridy = 1;
   	jCliente.add(txtDataFile, c);
   	
   	
	c.gridx = 0;c.gridy = 2;
   	jCliente.add(new JLabel(DString.fileDimensione), c);
   	
   	c.gridx = 1;c.gridy = 2;
   	jCliente.add(txtDimensioneFile, c);
   	
   	
   	c.gridx = 0;c.gridy = 3;c.gridwidth = 1;
   	jCliente.add(new JLabel(DString.fileDesc), c);
   
   	c.gridx = 1;c.gridy = 3;c.gridwidth = 4;   //2 columns wide
   	jCliente.add(txtNote, c);
   	
   	c.gridx = 0;c.gridy = 5;c.gridwidth=0;
   	txtpathFile.setVisible(false);
	jCliente.add(txtpathFile, c);
   	
   	txtNote.setRows(10);
   	txtNote.setColumns(30);
   	txtNote.setLineWrap(true);
   	txtNote.setWrapStyleWord(true);
   	  Border border = BorderFactory.createLineBorder(Color.GRAY);
   	txtNote.setBorder(border);
   	jCliente.add(txtNote, c);
	return jCliente;
	}
	   
	
	public static boolean scegliFile()
    {	boolean result= false;
    	final JFileChooser fc = new JFileChooser();
    	/*
    	FileNameExtensionFilter filterDB = new FileNameExtensionFilter("SQLite db Only", "db");
    	fc.addChoosableFileFilter(filterDB);
    	fc.setFileFilter(filterDB);
    	*/
		 int returnVal = fc.showOpenDialog(null);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	          
	            	txtNomeFile.setText(file.getName());
	            	txtpathFile.setText(file.getPath());
	            	long dimensione = file.length()/1024;
	    		 	txtDimensioneFile.setText(dimensione +"k");
	    		 	NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
	    			Locale fmtLocale = Locale.getDefault(Category.FORMAT);
	    			NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
	    			DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, getDefaultLocale());
	    		
	    		
	    			txtDataFile.setText(dateFormatter.format(new Date(file.lastModified())));
	            	
	            	
	            	
	        
	           result = true;
	         
	        } else {
	            
	        }
    	
    	
		return result;
    	
    }

}
