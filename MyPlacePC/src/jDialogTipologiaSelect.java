import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;




public class jDialogTipologiaSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	
	//
	private JTable tableTipologia;
    static Object[][] dataTipologia=null;
	public static int rowSelected;
	//

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			jDialogTipologiaSelect dialog = new jDialogTipologiaSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public jDialogTipologiaSelect() {
		 BorderLayout border_layout1 = new BorderLayout(); // creo il layout manager e lo assegno al container
	        setLayout(border_layout1);
		setBounds(100, 100, 300, 450);
		setTitle(DString.tipSeleziona);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{	
			dataTipologia = aggiornaDatiTabellaTipologia();
    		tableTipologia = new JTable(new MyTableModelTipologiaSeleziona( dataTipologia));
			

			tableTipologia.getColumnModel().getColumn(DData.selTipoColonnaConColore).setCellRenderer(new ColorCellRenderer());
			tableTipologia.setRowHeight(25);
			
			aggiornaDimensioniColonneTipologiaSeleziona();
			JScrollPane panelscroll = new JScrollPane(tableTipologia);
            
			contentPanel.add(panelscroll);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(DString.gSeleziona);
				
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						for(int i =0; i<dataTipologia.length;i++)
						{
							boolean valore =(boolean) dataTipologia[i][0] ;
							long idtipo = (long) dataTipologia[i][1];
							DataSource.settaTipologiaSelezionata(valore, idtipo);
							ListClient.resultOK=true;
						}
						// TODO Auto-generated method stub
						dispose();
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(DString.gAnnulla);
				
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
			}
		}
	}
	
	 public void aggiornaDimensioniColonneTipologiaSeleziona()
	    {	
	    	
		 tableTipologia.getColumnModel().getColumn(0).setMinWidth(25);
	    	tableTipologia.getColumnModel().getColumn(0).setMaxWidth(25);
	    	tableTipologia.getColumnModel().getColumn(0).setWidth(25);
	    	
	    	tableTipologia.getColumnModel().getColumn(1).setMinWidth(0);
	    	tableTipologia.getColumnModel().getColumn(1).setMaxWidth(0);
	    	tableTipologia.getColumnModel().getColumn(1).setWidth(0);
	    	
	    	
	    	
	    	tableTipologia.getColumnModel().getColumn(3).setMinWidth(25);
	    	tableTipologia.getColumnModel().getColumn(3).setMaxWidth(25);
	    	tableTipologia.getColumnModel().getColumn(3).setWidth(25);
	    	
	    	
	    	
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
	    		boolean selezionato;
	    	for(i=0;i<listaTipologia.size();i++)
			    	{	selezionato=false;
	    				if (listaTipologia.get(i).getpSelezionaTipologia()==1) selezionato=true;
	    				dataDataBase[i][0]= selezionato;
	    				dataDataBase[i][1]= listaTipologia.get(i).getpIdTipologia();
	    				dataDataBase[i][2]= listaTipologia.get(i).getpDescrizioneTipologia();
	    				dataDataBase[i][3]= listaTipologia.get(i).getpColoreTipologia();
	    				
	    				
	    			}
	    	
	    	
	    		
	    		
	    	}
	    	return dataDataBase;
	    	
	    }


	    class MyTableModelTipologiaSeleziona extends AbstractTableModel { 
	  
	        //private List<Referenti> data; 
	     
	    	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public  Object[][] dataTabella;
	    	
	        public MyTableModelTipologiaSeleziona(Object[][] dataTabella) {
	            
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
	                	value = "";
	                   
	                    break; 
	                case 1: 
	                    value = "ID"; 
	                    break; 
	                case 2: 
	                	//value = DString.tipoColore;
	                	value = DString.tipDescrizioneTipo;
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
	            return DData.selTipoNumeroColonne; 
	        } 
	  
	        @Override
	        public Object getValueAt(int rowIndex, int columnIndex) { 
	        	 return dataTabella[rowIndex][columnIndex];
	        } 
	  
	        @Override
	        public void setValueAt(Object aValue, int rowIndex, int columnIndex) { 
	        	  if (columnIndex == DData.selTipocheck) 
	        	  {
	        		  dataTabella[rowIndex][columnIndex] = aValue;
	        	         
	               	
	              //	DataSource.settaPerId(Boolean.parseBoolean(aValue.toString()), (long) getValueAt(rowIndex, 1));
	                   fireTableCellUpdated(rowIndex, columnIndex);
	        		  
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
	              
	            return columnIndex == DData.selTipocheck; 
	        } 
	    } 
	  
	   
	 


	    public class ColorCellRenderer extends DefaultTableCellRenderer { 
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) { 

	          //Cells are by default rendered as a JLabel. 
	          JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col); 

	          //Get the status for the current row. 
	          MyTableModelTipologiaSeleziona tableModel = (MyTableModelTipologiaSeleziona) table.getModel(); 
	          
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
