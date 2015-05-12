import java.awt.BorderLayout; 
import java.awt.FlowLayout; 
  
import javax.swing.JButton; 
import javax.swing.JCheckBox; 
import javax.swing.JDialog; 
import javax.swing.JPanel; 
import javax.swing.border.EmptyBorder; 
  
import com.jgoodies.forms.layout.FormLayout; 
import com.jgoodies.forms.layout.ColumnSpec; 
import com.jgoodies.forms.layout.RowSpec; 
import com.jgoodies.forms.factories.FormFactory; 
  
import javax.swing.JComboBox; 
import javax.swing.JLabel; 
import javax.swing.JRadioButton; 
  
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
  
public class JDialogOpzioni extends JDialog { 
  
    private final JPanel contentPanel = new JPanel(); 
    public JComboBox jCLingua,JCUnitaMisura; 
    public JRadioButton jRBDeleteConfirm; 
    /** 
     * Launch the application. 
     */
    public static void main(String[] args) { 
        try { 
            JDialogOpzioni dialog = new JDialogOpzioni(); 
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
            dialog.setVisible(true); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
  
    /** 
     * Create the dialog. 
     */
    public JDialogOpzioni() { 
    	
        setBounds(400, 100, 450, 230); 
        getContentPane().setLayout(new BorderLayout()); 
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); 
        getContentPane().add(contentPanel, BorderLayout.CENTER); 
        contentPanel.setLayout(new FormLayout(new ColumnSpec[] { 
                FormFactory.RELATED_GAP_COLSPEC, 
                FormFactory.DEFAULT_COLSPEC, 
                FormFactory.RELATED_GAP_COLSPEC, 
                FormFactory.DEFAULT_COLSPEC, 
                FormFactory.RELATED_GAP_COLSPEC, 
                FormFactory.DEFAULT_COLSPEC, 
                FormFactory.RELATED_GAP_COLSPEC, 
                ColumnSpec.decode("default:grow"), 
                FormFactory.RELATED_GAP_COLSPEC, 
                FormFactory.DEFAULT_COLSPEC, 
                FormFactory.RELATED_GAP_COLSPEC, 
                FormFactory.DEFAULT_COLSPEC, 
                FormFactory.RELATED_GAP_COLSPEC, 
                FormFactory.DEFAULT_COLSPEC, 
                FormFactory.RELATED_GAP_COLSPEC, 
                FormFactory.DEFAULT_COLSPEC, 
                FormFactory.RELATED_GAP_COLSPEC, 
                ColumnSpec.decode("default:grow"),}, 
            new RowSpec[] { 
                FormFactory.RELATED_GAP_ROWSPEC, 
                FormFactory.DEFAULT_ROWSPEC, 
                FormFactory.RELATED_GAP_ROWSPEC, 
                FormFactory.DEFAULT_ROWSPEC, 
                FormFactory.RELATED_GAP_ROWSPEC, 
                FormFactory.DEFAULT_ROWSPEC, 
                FormFactory.RELATED_GAP_ROWSPEC, 
                FormFactory.DEFAULT_ROWSPEC, 
                FormFactory.RELATED_GAP_ROWSPEC, 
                FormFactory.DEFAULT_ROWSPEC, 
                FormFactory.RELATED_GAP_ROWSPEC, 
                FormFactory.DEFAULT_ROWSPEC,})); 
        { 
            JLabel lblNewLabel = new JLabel(DString.dOpzioniLingua); 
            contentPanel.add(lblNewLabel, "6, 4, 2, 1"); 
        } 
        { 
            jCLingua = new JComboBox(); 
            jCLingua.addItem("English"); 
            jCLingua.addItem("Italiano"); 
            jCLingua.setSelectedIndex(DGen.getPreferencesInt(DString.PLINGUA, 0));
            jCLingua.addActionListener(new ActionListener() { 
                  
                @Override
                public void actionPerformed(ActionEvent e) { 
                
                    
                              
                      
                } 
            }); 
            contentPanel.add(jCLingua, "8, 4, 8, 1, fill, default"); 
        } 
        { 
            JLabel lblNewLabel_1 = new JLabel(DString.dopzioniUnitaMisura); 
            contentPanel.add(lblNewLabel_1, "6, 8"); 
        } 
        { 
            JCUnitaMisura = new JComboBox(); 
            JCUnitaMisura.addItem("Km"); 
            JCUnitaMisura.addItem("Miles"); 
            JCUnitaMisura.setSelectedIndex(DGen.getPreferencesInt(DString.PUNITA, 0));
            JCUnitaMisura.addActionListener(new ActionListener() { 
                  
                @Override
                public void actionPerformed(ActionEvent e) { 
                	
                   
                      
                } 
            }); 
            contentPanel.add(JCUnitaMisura, "8, 8, 8, 1, fill, default"); 
        } 
        { 
            jRBDeleteConfirm = new JRadioButton(DString.dOpzioniConfermePrimaCancellare); 
            jRBDeleteConfirm.setSelected(DGen.getPreferencesBoolean(DString.PCONFERMADELETE, true));  
            
            contentPanel.add(jRBDeleteConfirm, "8, 12"); 
        } 
        { 
            JPanel buttonPane = new JPanel(); 
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
            getContentPane().add(buttonPane, BorderLayout.SOUTH); 
            { 
                JButton okButton = new JButton(DString.dApplica); 
                okButton.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent arg0) { 
                        // do something 
                    	DGen.savePreferencesInt(DString.PLINGUA, jCLingua.getSelectedIndex());
                    	DGen.savePreferencesInt(DString.PUNITA, JCUnitaMisura.getSelectedIndex());
                    
                        DGen.savePreferencesBoolean(DString.PCONFERMADELETE, jRBDeleteConfirm.isSelected());
                       
                          dispose(); 
                    } 
                }); 
                //okButton.setActionCommand("OK"); 
                buttonPane.add(okButton); 
                getRootPane().setDefaultButton(okButton); 
            } 
            { 
                JButton cancelButton = new JButton(DString.dAnnulla); 
                cancelButton.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) { 
                          
                          dispose(); 
                    } 
                }); 
                cancelButton.setActionCommand("Cancel"); 
                buttonPane.add(cancelButton); 
            } 
        } 
    } 
  
} 