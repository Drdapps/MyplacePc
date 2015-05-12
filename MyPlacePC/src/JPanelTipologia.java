import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Component; 
import java.awt.Dimension;
import java.awt.GridBagLayout; 
import java.awt.GridLayout;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList; 
import java.util.Date;
import java.util.EventObject; 
import java.util.List; 
import java.util.Locale;
import java.util.Locale.Category;

import javax.swing.AbstractCellEditor; 
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon; 
import javax.swing.JButton; 
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane; 
import javax.swing.JSplitPane;
import javax.swing.JTable; 
import javax.swing.JTextArea; 
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities; 
import javax.swing.event.CellEditorListener; 
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel; 
import javax.swing.table.DefaultTableCellRenderer; 
import javax.swing.table.TableCellEditor; 
import javax.swing.table.TableCellRenderer; 
import javax.swing.table.TableColumn;
  
  
  
  
public class JPanelTipologia extends JPanel { 
      
   
	/**
	 * 
	 */
	
	
	
	
	public static JTable tableTipologia; // JTable
	
	private BorderLayout contentPanel;
     
	static Object[][] dataTipologia=null;
	
   
	public static int rowSelected;
	
	
    public JPanelTipologia() { 
      
    	 BorderLayout border_layout1 = new BorderLayout(); // creo il layout manager e lo assegno al container
        setLayout(border_layout1);
    			
        //String col_names_attivita[] = {DString.attid,DString.attData,DString.attDescrizione,};
        
        		dataTipologia = aggiornaDatiTabellaTipologia();
        		tableTipologia = new JTable(new MyTableModelReferenti( dataTipologia));
    			MultiButtonRendererReferenti rendererReferenti = new MultiButtonRendererReferenti(); 
    			tableTipologia.getColumnModel().getColumn(DData.numeroColonnaConPulsantiTipologia).setCellRenderer(rendererReferenti); 
    			tableTipologia.getColumnModel().getColumn(DData.numeroColonnaConPulsantiTipologia).setCellEditor(new MultiButtonEditorReferenti()); 
    			tableTipologia.setRowHeight(rendererReferenti.getTableCellRendererComponent(tableTipologia, null, true, true, 0, 0).getPreferredSize().height);
    			

    			tableTipologia.getColumnModel().getColumn(DData.numeroColonnaConColore).setCellRenderer(new ColorCellRenderer()); 
    			
              
    			
               aggiornaDimensioniColonneReferenti();
               
                  
              
              
                JScrollPane panelscroll = new JScrollPane(tableTipologia);
       add(panelscroll, BorderLayout.CENTER); // aggiungo lo split pane al container
                //add(bttAddAttività,BorderLayout.NORTH);
                  
                  
               
            } 
    
    
   
    public static void refreshTabellaTipologia ()
    {	
    	
		MyTableModelReferenti model = (MyTableModelReferenti) tableTipologia.getModel(); 
		model.dataTabella = aggiornaDatiTabellaTipologia();
		
    	model.fireTableDataChanged();
    	
    }
    public static void fitToContentWidth(final JTable table, final int column) {
    	  int width = 0;
    	  for (int row = 0; row < table.getRowCount(); ++row) {
    	   final Object cellValue = table.getValueAt(row, column);
    	   final TableCellRenderer renderer = table.getCellRenderer(row, column);
    	   final Component comp 
    	    = renderer.getTableCellRendererComponent(table, cellValue, false, false, row, column);
    	   width = Math.max(width, comp.getPreferredSize().width);
    	  }
    	  final TableColumn tc = table.getColumn(table.getColumnName(column));
    	  width += table.getIntercellSpacing().width * 2;
    	  tc.setPreferredWidth(width);
    	  tc.setMinWidth(width);
    	 }
    
    public void aggiornaDimensioniColonneReferenti()
    {	
    	
    	
    	
    	tableTipologia.getColumnModel().getColumn(0).setMinWidth(0);
    	tableTipologia.getColumnModel().getColumn(0).setMaxWidth(0);
    	tableTipologia.getColumnModel().getColumn(0).setWidth(0);
    	
    	tableTipologia.getColumnModel().getColumn(3).setMinWidth(50);
    	tableTipologia.getColumnModel().getColumn(3).setMaxWidth(50);
    	tableTipologia.getColumnModel().getColumn(3).setWidth(50);
    	
    	tableTipologia.getColumnModel().getColumn(2).setMinWidth(25);
    	tableTipologia.getColumnModel().getColumn(2).setMaxWidth(25);
    	tableTipologia.getColumnModel().getColumn(2).setWidth(25);
    	
    	
    	
    }
   public static Object[][] aggiornaDatiTabellaTipologia()
    {	
	   
	   List <Tipologia> listaTipologia = new ArrayList<Tipologia>();
    	
    	try {
    		
    		// listaTipologia = DataSource.getAllReferenti(idCLiente);
    		
    		listaTipologia = DataSource.getAlltipologia();
    		
    		
    		//listaClienti = DataSource.getAllClienti(ricerca, 0, 0, 0); //ricerca tipologia lati e longi
		} catch (Exception e) {
			// TODO: handle exception
			
		}
    	Object[][] dataDataBase = new Object[listaTipologia.size()][10];
    	if (listaTipologia.size()>0)
    	{
    		int i =0;
    		
    	for(i=0;i<listaTipologia.size();i++)
		    	{	
    				dataDataBase[i][0]= listaTipologia.get(i).getpIdTipologia();
    				dataDataBase[i][1]= listaTipologia.get(i).getpDescrizioneTipologia();
    				dataDataBase[i][2]= listaTipologia.get(i).getpColoreTipologia();
    				dataDataBase[i][3]= "";
    				
    			}
    	
    	
    		
    		
    	}
    	return dataDataBase;
    	
    }
      
 
  
    class MyTableModelReferenti extends AbstractTableModel { 
  
        //private List<Referenti> data; 
     
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public  Object[][] dataTabella;
    	
        public MyTableModelReferenti(Object[][] dataTabella) {
            
            this.dataTabella = dataTabella;
        } 
        
        public void tableChanged(TableModelEvent e)
        {	
            try
            {	super.fireTableChanged(e);
                
            }
            catch(IndexOutOfBoundsException ex)
            {
                // Ignore
            }
        }
  
        @Override
        public String getColumnName(int column) { 
            String value = null; 
            switch (column) { 
                case 0:
                	value = "ID";
                   
                    break; 
                case 1: 
                    value = DString.tipDescrizioneTipo; 
                    break; 
                case 2: 
                	//value = DString.tipoColore;
                	value = "";
                	break;
                case 3: 
                	value = "";
                	break;
               
            } 
            return value; 
        } 
  
        @Override
        public Class<?> getColumnClass(int columnIndex) { 
        	
        	return getValueAt(0, columnIndex).getClass();
        } 
  
        @Override
        public int getRowCount() { 
        	return dataTabella.length;
        } 
  
        @Override
        public int getColumnCount() { 
            return DData.numeroDiColonneTipologia; 
        } 
  
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) { 
        	 return dataTabella[rowIndex][columnIndex];
        } 
  
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) { 
              
            if (columnIndex == DData.numeroColonnaConPulsantiTipologia) { 
  
                
                if ("editR".equals(aValue)) 
                {long idSelezionato = (long) JPanelTipologia.tableTipologia.getValueAt(rowIndex, 0);
                 String descrizioneTipologia = (String) JPanelTipologia.tableTipologia.getValueAt(rowIndex, 1);
                 int coloreSelezionato = (int) JPanelTipologia.tableTipologia.getValueAt(rowIndex, 2);
               	//aggiornareferente
                 		
                		jDialogAddTipologia dialog = new jDialogAddTipologia(idSelezionato, descrizioneTipologia, coloreSelezionato );
                		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                		dialog.setModal(true);
                		dialog.setVisible(true);
                
                
                } 
               // if ("newR".equals(aValue)) {System.out.println("NEW"); } 
                if ("deleteR".equals(aValue)) {
                	
                	long idSelezionato = (long) JPanelTipologia.tableTipologia.getValueAt(rowIndex, 0);
                
                	System.out.println("CANCELLA TIPO/" + idSelezionato + "/" + DData.GenChiediCOnfermaPrimacancellare);
                	if (DData.GenChiediCOnfermaPrimacancellare==true)
        	    	{   	if (DGen.showConfirm(DString.gAttenzione, DString.msgCancellaTipologia, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)  
        	    	   			{	
                				
        	    				//datasource.getsortingmodulre (imposta tipologia)
        	    				//ciclo for per elimianre tutti i clienti
        	    				
        	    				DataSource.cancellaTipologi(idSelezionato);
        	    				
		        	    		List <posizioneGps> listaClientiSelezionati = new ArrayList<posizioneGps>();
		        				listaClientiSelezionati = DataSource.getAllClientiRicerca("", idSelezionato, mydbhelper.COLUMN_NAME, mydbhelper.CRESCENTE, DData.latitudinePartenza, DData.longitudinePartenza,1);
		        				int i =0;
		        				for (i=0;i<listaClientiSelezionati.size();i++){
		        					DataSource.cancellaClienteTotale(listaClientiSelezionati.get(i).getpId());
		        					
		        				}
        			    		refreshTabellaTipologia();
        			    		
        			    		
        			    		ListClient.JcomboElencoTipologie.setModel(new DefaultComboBoxModel(DGen.itemComboTipologieCompresaTutte()));
        			    	
        			    		ListClient.refreshTabella();
        			    		ClientiPanel.jComboTipologia.setModel(new DefaultComboBoxModel(DGen.itemComboTipologieEsclusaTutte()));
        			    		
        	    	   			}
        	    
                	
        	    	} else{DataSource.cancellaTipologi(idSelezionato);
    				
			    	    		List <posizioneGps> listaClientiSelezionati = new ArrayList<posizioneGps>();
			    				listaClientiSelezionati = DataSource.getAllClientiRicerca("", idSelezionato, mydbhelper.COLUMN_NAME, mydbhelper.CRESCENTE, DData.latitudinePartenza, DData.longitudinePartenza,1);
			    				int i =0;
			    				for (i=0;i<listaClientiSelezionati.size();i++){
			    					DataSource.cancellaClienteTotale(listaClientiSelezionati.get(i).getpId());
			    					
			    				}
					    		refreshTabellaTipologia();
					    		
					    		
					    		ListClient.JcomboElencoTipologie.setModel(new DefaultComboBoxModel(DGen.itemComboTipologieCompresaTutte()));
					    	
					    		ListClient.refreshTabella();
					    		ClientiPanel.jComboTipologia.setModel(new DefaultComboBoxModel(DGen.itemComboTipologieEsclusaTutte()));
					    		
        	    		
        	    	}
                
                }
                fireTableCellUpdated(rowIndex, columnIndex); 
                //remove(value); 
  
            } 
        } 
            
            
            
        //aggiungi valore all tabella 
            
        public void add(Referenti value) { 
        	/*
            int startIndex = getRowCount(); 
            data.add(value); 
            fireTableRowsInserted(startIndex, getRowCount() - 1); 
            */
        } 
  
        public void remove(Referenti value) { 
        	/*
            int startIndex = data.indexOf(value); 
            System.out.println("startIndexR = " + startIndex); 
            data.remove(value); 
            fireTableRowsInserted(startIndex, startIndex); 
            */
        } 
  
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) { 
              
            return columnIndex == DData.numeroColonnaConPulsantiTipologia; 
        } 
    } 
  
    class MultiButtonPanelReferenti extends JPanel { 
  
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//private JButton bttnewR; 
        private JButton btteditR,  bttdeleteR ; 
          
        private String state; 
  
        public MultiButtonPanelReferenti() { 
            setLayout(new GridBagLayout()); 
           
            Dimension dim = new Dimension(30,30);
            btteditR = new JButton(new ImageIcon(getClass().getResource(DData.iconaEdit))); 
            btteditR.setMaximumSize(dim);
            btteditR.setMinimumSize(dim);
            btteditR.setActionCommand("editR"); 
            /*
            bttnewR = new JButton(new ImageIcon(getClass().getResource(DData.iconaNuova))); 
            bttnewR.setMaximumSize(dim);
            bttnewR.setMinimumSize(dim);
            bttnewR.setActionCommand("newR"); 
            */
            bttdeleteR = new JButton(new ImageIcon(getClass().getResource(DData.iconaCancella)));
            bttdeleteR.setMaximumSize(dim);
            bttdeleteR.setMinimumSize(dim);
            bttdeleteR.setActionCommand("deleteR"); 
  
            add(btteditR); 
           // add(bttnewR); 
            add(bttdeleteR); 
  
            ActionListener listener = new ActionListener() { 
                @Override
                public void actionPerformed(ActionEvent e) { 
                    state = e.getActionCommand(); 
                  
                } 
            }; 
  
            btteditR.addActionListener(listener); 
            //bttnewR.addActionListener(listener); 
            bttdeleteR.addActionListener(listener); 
        } 
  
        public void addActionListener(ActionListener listener) { 
            btteditR.addActionListener(listener); 
           // bttnewR.addActionListener(listener); 
            bttdeleteR.addActionListener(listener); 
        } 
  
        public String getState() { 
            return state; 
        } 
    } 
  
    class MultiButtonRendererReferenti extends DefaultTableCellRenderer { 
  
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private MultiButtonPanelReferenti multiButtonPanel; 
  
        public MultiButtonRendererReferenti() { 
            multiButtonPanel = new MultiButtonPanelReferenti(); 
        } 
  
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
            if (isSelected) { 
                multiButtonPanel.setBackground(table.getSelectionBackground()); 
            } else { 
                multiButtonPanel.setBackground(table.getBackground()); 
            } 
            return multiButtonPanel; 
        } 
    } 
  
    class MultiButtonEditorReferenti extends AbstractCellEditor implements TableCellEditor { 
  
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private MultiButtonPanelReferenti multiButtonPanel; 
  
        public MultiButtonEditorReferenti() { 
            multiButtonPanel = new MultiButtonPanelReferenti(); 
            multiButtonPanel.addActionListener(new ActionListener() { 
                @Override
                public void actionPerformed(ActionEvent e) { 
                    SwingUtilities.invokeLater(new Runnable() { 
                        @Override
                        public void run() { 
                            stopCellEditing(); 
                        } 
                    }); 
                } 
            }); 
        } 
  
        @Override
        public Object getCellEditorValue() { 
            return multiButtonPanel.getState(); 
        } 
  
        @Override
        public boolean isCellEditable(EventObject e) { 
            return true; 
        } 
  
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { 
            if (isSelected) { 
                multiButtonPanel.setBackground(table.getSelectionBackground()); 
            } else { 
                multiButtonPanel.setBackground(table.getBackground()); 
            } 
            return multiButtonPanel; 
        } 
    } 
    
    
    public class ColorCellRenderer extends DefaultTableCellRenderer { 
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) { 

          //Cells are by default rendered as a JLabel. 
          JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col); 

          //Get the status for the current row. 
          MyTableModelReferenti tableModel = (MyTableModelReferenti) table.getModel(); 
          
          //OPPO 
           try {
        	   
             	  l.setBackground(new Color(Integer.parseInt(value.toString()))); //cosi colore sfondo   
                  l.setForeground(new Color(Integer.parseInt(value.toString()))); //colore testo coincidono e no si vedono 
               
              
		} catch (Exception e) {
			l.setForeground(Color.RED);
		}
           
          
           
          //importante mettere questo perchè così dalla 
           

        //Return the JLabel which renders the cell. 
        return l; 

      } 
  }  
}
  
  
  
  
    
