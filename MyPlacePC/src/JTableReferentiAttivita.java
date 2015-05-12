import java.awt.BorderLayout; 
import java.awt.Component; 
import java.awt.Dimension;
import java.awt.GridBagLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.io.CharConversionException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList; 
import java.util.Date;
import java.util.EventObject; 
import java.util.List; 
import java.util.Locale;
import java.util.Locale.Category;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractCellEditor; 
import javax.swing.ImageIcon; 
import javax.swing.JButton; 
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable; 
import javax.swing.JTextArea; 
import javax.swing.SwingUtilities; 
import javax.swing.event.CellEditorListener; 
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel; 
import javax.swing.table.DefaultTableCellRenderer; 
import javax.swing.table.TableCellEditor; 
import javax.swing.table.TableCellRenderer; 
import javax.swing.table.TableColumn;
  
  
  
  
public class JTableReferentiAttivita extends JPanel { 
      
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTable tableReferenti, tableAttivita; // JTable
	private BorderLayout border_layout;
     
	static Object[][] dataRef=null;
	static Object[][] dataAtt= null;
   
  
    public JTableReferentiAttivita() { 
      
    	 border_layout = new BorderLayout(); // creo il layout manager e lo assegno al container
        setLayout(border_layout);
    			
    	
    			dataRef = aggiornaDatiTabellaReferenti(DData.idClienteSelezionato);
    			tableReferenti = new JTable(new MyTableModelReferenti( dataRef));
            	MultiButtonRendererReferenti rendererReferenti = new MultiButtonRendererReferenti(); 
                tableReferenti.getColumnModel().getColumn(DData.numeroColonnaConPulsantiReferenti).setCellRenderer(rendererReferenti); 
            	tableReferenti.getColumnModel().getColumn(DData.numeroColonnaConPulsantiReferenti).setCellEditor(new MultiButtonEditorReferenti()); 
                tableReferenti.setRowHeight(rendererReferenti.getTableCellRendererComponent(tableReferenti, null, true, true, 0, 0).getPreferredSize().height); 
               refreshTabellaReferenti(DData.idClienteSelezionato);
                aggiornaDimensioniColonneReferenti();
                 //Dimension dim = new Dimension(10,1);
                //((JTable) tableReferenti).setIntercellSpacing(new Dimension(dim));
                
               
                
                  
                String col_names_attivita[] = {DString.attid,DString.attData,DString.atttitolo,DString.attFatto,DString.attDescrizione,};
              
                
                tableAttivita = new JTable(new MyTableModelAttivita(col_names_attivita, dataAtt));  
              
                
                MultiButtonRendererAttivita rendererAttivita = new MultiButtonRendererAttivita(); 
                tableAttivita.getColumnModel().getColumn(DData.numeroColonnaConPulsantiAttivita).setCellRenderer(rendererAttivita); 
                tableAttivita.getColumnModel().getColumn(DData.numeroColonnaConPulsantiAttivita).setCellEditor(new MultiButtonEditorAttivita()); 
                 ///agiungere testo multiwra, 
                jTextAreaRenderer renderTesto = new jTextAreaRenderer(); 
                tableAttivita.getColumnModel().getColumn(DData.numeroColonnaConJtextArea).setCellRenderer(renderTesto); 
                tableAttivita.getColumnModel().getColumn(DData.numeroColonnaConJtextArea).setCellEditor(new JTextAreaEditor()); 
                //tableAttivita.setPreferredScrollableViewportSize(tableAttivita.getPreferredSize());  
                refreshTabellaAttivita(DData.idClienteSelezionato);
                aggiornaDimensioniColonneAttivita();
                //Dimension dimA = new Dimension(15,1);
                
               // ((JTable) tableAttivita).setIntercellSpacing(new Dimension(dimA)); 
               
                JScrollPane panelscroll = new JScrollPane();
                
                JScrollPane panel_table_top = new JScrollPane(tableReferenti);
               JScrollPane panel_table_bottom = new JScrollPane(tableAttivita);
               JTableOfferteSingoloCliente jOfferte = new JTableOfferteSingoloCliente(false); //false per la gestione a singolo cliente
              JScrollPane panel_table_offerte = new JScrollPane(jOfferte);
              JTableFilesCliente jFiles = new JTableFilesCliente();
              JScrollPane panel_table_file =  new JScrollPane(jFiles);
               
               		JTabbedPane tabbedPane = new JTabbedPane();
		   	        tabbedPane.addTab(DString.jTabbedAttivita, null, panel_table_bottom,"");
		   	     tabbedPane.addTab(DString.jTabbedOfferte, null, panel_table_offerte,"");
		   	  tabbedPane.addTab(DString.jTabbedFiles, null, panel_table_file,"");
		   	        
                // aggiungo i pannelli ad uno splitpane
                JSplitPane split_pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel_table_top, tabbedPane);
                split_pane.setDividerLocation(DData.distanzaSplitReferentiAttivita);
            
                add(split_pane, BorderLayout.CENTER); // aggiungo lo split pane al container
                split_pane.setOneTouchExpandable(true);
                  
                  
               
            } 
    
    
    public static void refreshTabellaAttivita (long idCLiente)
    {	
    	
		MyTableModelAttivita model = (MyTableModelAttivita) tableAttivita.getModel(); 
		model.dataTabella = aggiornaDatiTabellaAttivita(idCLiente);
    	model.fireTableDataChanged();
    }
    public static void refreshTabellaReferenti (long idCLiente)
    {	
    	
		MyTableModelReferenti model = (MyTableModelReferenti) tableReferenti.getModel(); 
		model.dataTabella = aggiornaDatiTabellaReferenti(idCLiente);
		
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
    	DGen.hideColumn(tableReferenti, 0);
    	DGen.fixWidth(tableReferenti, 1, 50);
    	
    	DGen.fixWidth(tableReferenti, 2, 50);
    	
    	DGen.fixWidth(tableReferenti, 3, 50);
    	DGen.fixWidth(tableReferenti, 4, 40);
    	DGen.fixWidth(tableReferenti, 5, 40);
    	DGen.fixWidth(tableReferenti, 6, 40);
    	DGen.fixWidth(tableReferenti, 7, 40);
    	//DGen.fixWidth(tableReferenti, 8, 40);
    	//DGen.fixWidth(tableReferenti, 9, 60);
    	DGen.deffixWidth(tableReferenti, 9, 60);
    	
    }
    public void aggiornaDimensioniColonneAttivita()
    {	
    	DGen.hideColumn(tableAttivita, 0);
    
    	DGen.deffixWidth(tableAttivita, 1, 60);
    	
    
    	DGen.deffixWidth(tableAttivita, 2, 65);
    	DGen.deffixWidth(tableAttivita, 3,40);
    	
    	
    	//DGen.deffixWidth(tableAttivita, 3, 580);
    	DGen.deffixWidth(tableAttivita, 5, 60);
    }
    
    public static Object[][] aggiornaDatiTabellaReferenti(long idCLiente)
    {	List <Referenti> listaReferenti = new ArrayList<Referenti>();
    	
    	try {
    		
    		listaReferenti = DataSource.getAllReferenti(idCLiente);
    		
    		//listaClienti = DataSource.getAllClienti(ricerca, 0, 0, 0); //ricerca tipologia lati e longi
		} catch (Exception e) {
			// TODO: handle exception
		}
    	Object[][] dataDataBase = new Object[listaReferenti.size()][10];
    	
    	if (listaReferenti.size()>0)
    	{
    		int i =0;
    		
    	for(i=0;i<listaReferenti.size();i++)
		    	{	
    				
    				
    				
    				
    				
    				dataDataBase[i][0]= listaReferenti.get(i).getpIdReferente();
    				dataDataBase[i][1]= listaReferenti.get(i).getpNomeReferente();
    				dataDataBase[i][2]= listaReferenti.get(i).getpCognomeReferente();
    				
    				dataDataBase[i][3]= listaReferenti.get(i).getpFunzioneReferente();
    				dataDataBase[i][4]= listaReferenti.get(i).getpTelRefernte();
    				dataDataBase[i][5]= listaReferenti.get(i).getpFaxReferente();
    				dataDataBase[i][6]= listaReferenti.get(i).getpCelReferente();
    				
    				dataDataBase[i][7]= listaReferenti.get(i).getpMaileferente();
    				dataDataBase[i][8]= listaReferenti.get(i).getpMemoReferente();
    				dataDataBase[i][9]= "";
    				
    			}
    	
    	
    		
    		
    	}
    	return dataDataBase;
    	
    }
    public static Object[][] aggiornaDatiTabellaAttivita(long idCLiente)
    {	List <Attivita> listaattivita = new ArrayList<Attivita>();
    	
    	try {
    		
    		listaattivita = DataSource.getAllAttivita(idCLiente);
    		
    		//listaClienti = DataSource.getAllClienti(ricerca, 0, 0, 0); //ricerca tipologia lati e longi
		} catch (Exception e) {
			// TODO: handle exception
		}
    	Object[][] dataDataBase = new Object[listaattivita.size()][DData.numeroColonneTotalAttivita];
    	if (listaattivita.size()>0)
    	{
    		int i =0;
    		NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        	Locale fmtLocale = Locale.getDefault(Category.FORMAT);
        	NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
        	 DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, getDefaultLocale());
        	 
    	for(i=0;i<listaattivita.size();i++)
		    	{	
    				dataDataBase[i][0]= listaattivita.get(i).getpIdAttivita();
    				dataDataBase[i][1]= dateFormatter.format(new Date(listaattivita.get(i).getpDataAttivita())); 
    				dataDataBase[i][2]= DGen.restituisciTipoAttivita(listaattivita.get(i).getpTipoAttivita());
    				if (listaattivita.get(i).getpFattaAttivita()==0)
    				{
    					dataDataBase[i][3]= false;
    				}else{
    					dataDataBase[i][3]= true;
    				}
    				
    				dataDataBase[i][4]= listaattivita.get(i).getpDescrizioneAttività();
    				dataDataBase[i][5]= "";
    				
    			}
    	
    	}
    	return dataDataBase;
    	
    }
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
                	value = "";
                    //value = DString.RefID; 
                    break; 
                case 1: 
                    value = DString.RefNome; 
                    break; 
                case 2: 
                    value = DString.RefCognome; 
                    break; 
                case 3: 
                    value = DString.RefFunzione; 
                    break; 
                case 4: 
                     value = DString.RefTele; 
                     break;
                case 5: 
                	 value = DString.RefFax; 
                 break; 
                case 6: 
                    value = DString.RefCell;
                 break; 
                case 7: 
                    value = DString.RefEmail;
                 break; 
                case 8: 
                    value = DString.RefNota;
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
            return 10; 
        } 
  
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) { 
        	 return dataTabella[rowIndex][columnIndex];
        } 
  
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) { 
              
            if (columnIndex == DData.numeroColonnaConPulsantiReferenti) { 
  
                
                if ("editR".equals(aValue)) 
                {long idSelezionato = (long) JTableReferentiAttivita.tableReferenti.getValueAt(rowIndex, 0);
                System.err.println("idselezionato edit /"+idSelezionato);
    				//aggiornareferente
                int result =  DGen.ReferenteAdd(idSelezionato);
               
                if (result==JOptionPane.OK_OPTION)
				{ //aggiungireferente
					
					Referenti refe =  DGen.recuperateReferenteDaMaschera(idSelezionato);
					System.err.println("prima aggiungi" + refe.getpIdReferente());
					DataSource.aggiornaReferentedaID(refe);
					
					JTableReferentiAttivita.refreshTabellaReferenti(refe.getpIdClienteReferente());
					
				}
                
                
                } 
               // if ("newR".equals(aValue)) {System.out.println("NEW"); } 
                if ("deleteR".equals(aValue)) {
                	long idSelezionato = (long) JTableReferentiAttivita.tableReferenti.getValueAt(rowIndex, 0);
        			if (DData.GenChiediCOnfermaPrimacancellare==true)
        	    	{   	if (DGen.showConfirm(DString.gAttenzione, "1 " + DString.msgcancellazione, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)  
        	    	   	{	
                				
        	    				DataSource.cancellaReferente(idSelezionato);
        			    		JTableReferentiAttivita.refreshTabellaReferenti(DData.idClienteSelezionato);
        			    		
        			    		
        			    	}
        	    	}else{
        	    		
        	    		DataSource.cancellaReferente(idSelezionato);
			    		JTableReferentiAttivita.refreshTabellaReferenti(DData.idClienteSelezionato);
			    		
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
            System.err.println("editableRR"+ columnIndex + "/" + DData.numeroColonnaConPulsantiReferenti); 
              
            return columnIndex == DData.numeroColonnaConPulsantiReferenti; 
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
                    System.out.println("State = " + state); 
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
  
  
  
    class MyTableModelAttivita extends AbstractTableModel { 
  
    	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public String columns_nome[];
      	public  Object[][] dataTabella;
  
        public MyTableModelAttivita(String columns_headers[], Object[][] dataTabella) {
            this.columns_nome = columns_headers;
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
                    value = DString.attid; 
                    break; 
                case 1: 
                    value = DString.attData; 
                    break; 
                case 2: 
                    value = DString.atttitolo; 
                    break; 
                case 3:
                	value = DString.attFatto;
                	break;  
                case 4: 
                    value = DString.attDescrizione; 
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
            return DData.numeroColonneTotalAttivita; 
        } 
  
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) { 
        	return dataTabella[rowIndex][columnIndex];
        } 
  
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) { 
            //or serve se vuoglio aggiungere altra colonna con pulsanti 
              
            if (columnIndex == DData.numeroColonnaConPulsantiAttivita){ 
  
            
                if ("editA".equals(aValue)) {
                	
                	long idSelezionato = (long) JTableReferentiAttivita.tableAttivita.getValueAt(rowIndex, 0);
                    System.err.println("idselezionato/"+idSelezionato);
                    
        				//aggiornarattivitae
                    int result =  DGen.AttivitaAddRecursive(idSelezionato);
                    /*
                    if (result==JOptionPane.OK_OPTION)
    				{ 	Attivita atti =  DGen.recuperateAttivitaDaMaschera(idSelezionato);
    					DataSource.aggiornaAttivitaID(atti);
    					long massimaAttivita = DataSource.massimaDataattivita(atti.getpIdCliente());
    					//atti.getpdataattività restituisce il valore della data dell'atttività attuale
	    				DataSource.aggiornaClientedaAttivita(atti.getpIdCliente(), massimaAttivita);
    					ListClient.refreshTabella();
    					JTableTodo.refreshTabellaAttivita(JTableTodo.JcomboOrdinamento.getSelectedIndex(),
									JTableTodo.JcomboSelezione.getSelectedIndex(), JTableTodo.txtRicerca.getText());
    					JTableReferentiAttivita.refreshTabellaAttivita(atti.getpIdCliente());
    				}
                    */
                } 
                //if ("newA".equals(aValue)) {System.out.println("NEWA"); } 
                if ("deleteA".equals(aValue)) {
                	
                	long idSelezionato = (long) JTableReferentiAttivita.tableAttivita.getValueAt(rowIndex, 0);
                
        			if (DData.GenChiediCOnfermaPrimacancellare==true)
        	    	{   	if (DGen.showConfirm(DString.gAttenzione, "1 " + DString.msgcancellazione, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)  
        	    	   	{	
        	    		DataSource.cancellaAttivita(idSelezionato);
        	    		System.out.println(" JTABLE REF CANCELLA ATTIVATA/" + idSelezionato + DGen.stampaOraProva(DataSource.massimaDataattivita(idSelezionato)));
			    		long nuovaMassimaattivita = DataSource.massimaDataattivita(DData.idClienteSelezionato);
			    		int tipoattivita = DataSource.tipoUltimaAttivita(DData.idClienteSelezionato);
			    		DataSource.aggiornaClientedaAttivita(DData.idClienteSelezionato, nuovaMassimaattivita, tipoattivita );
			    		ListClient.refreshTabella();
			    		JTableReferentiAttivita.refreshTabellaAttivita(DData.idClienteSelezionato);
			    		JTableTodo.refreshTabellaAttivita(JTableTodo.JcomboOrdinamento.getSelectedIndex(),
								JTableTodo.JcomboSelezione.getSelectedIndex(), JTableTodo.txtRicerca.getText());
        			    		
        			    	}
        	    	}else{
        	    		
        	    		DataSource.cancellaAttivita(idSelezionato);
        	    		System.out.println("CANCELLA ATTIVATA/" + DData.idClienteSelezionato + DGen.stampaOraProva(DataSource.massimaDataattivitaTabellaAttivita(idSelezionato)));
			    		long nuovaMassimaattivita = DataSource.massimaDataattivitaTabellaAttivita(DData.idClienteSelezionato);
			    		int tipoattivita = DataSource.tipoUltimaAttivita(DData.idClienteSelezionato);
			    		DataSource.aggiornaClientedaAttivita(DData.idClienteSelezionato, nuovaMassimaattivita, tipoattivita);
			    		ListClient.refreshTabella();
			    		JTableReferentiAttivita.refreshTabellaAttivita(DData.idClienteSelezionato);
			    		
		
        	    	}
                } 
                 
                fireTableCellUpdated(rowIndex, columnIndex); 
                //remove(value); 
  
            } 
                  
        } 
  
        public void add(Attivita value) { 
           /* int startIndex = getRowCount(); 
            data.add(value); 
            fireTableRowsInserted(startIndex, getRowCount() - 1); 
            */
        } 
  
        public void remove(Attivita value) { 
          /* int startIndex = data.indexOf(value); 
            System.out.println("startIndexA = " + startIndex); 
            data.remove(value); 
            fireTableRowsInserted(startIndex, startIndex); 
            
            */
        } 
  
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) { 
            return columnIndex == DData.numeroColonnaConPulsantiAttivita; 
        } 
    } 
  
    class MultiButtonPanelAttivita extends JPanel { 
  
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//private JButton bttnewA; 
        private JButton btteditA,  bttdeleteA ; 
          
        private String state; 
  
        public MultiButtonPanelAttivita() { 
            setLayout(new GridBagLayout()); 
          
            Dimension dim = new Dimension(30,30);
            btteditA = new JButton(new ImageIcon(getClass().getResource(DData.iconaEdit))); 
            btteditA.setMaximumSize(dim);
            btteditA.setMinimumSize(dim);
            btteditA.setActionCommand("editA"); 
        /*    bttnewA = new JButton(new ImageIcon(getClass().getResource(DData.iconaNuova))); 
            bttnewA.setMaximumSize(dim);
            bttnewA.setMinimumSize(dim);
            bttnewA.setActionCommand("newA"); */
            bttdeleteA = new JButton(new ImageIcon(getClass().getResource(DData.iconaCancella)));
            bttdeleteA.setMaximumSize(dim);
            bttdeleteA.setMinimumSize(dim);
            bttdeleteA.setActionCommand("deleteA"); 
            
            add(btteditA); 
            //add(bttnewA); 
            add(bttdeleteA); 
  
            ActionListener listener = new ActionListener() { 
                @Override
                public void actionPerformed(ActionEvent e) { 
                    state = e.getActionCommand(); 
                    System.out.println("StateA = " + state); 
                } 
            }; 
  
            btteditA.addActionListener(listener); 
            //bttnewA.addActionListener(listener); 
            bttdeleteA.addActionListener(listener); 
        } 
  
        public void addActionListener(ActionListener listener) { 
            btteditA.addActionListener(listener); 
            //bttnewA.addActionListener(listener); 
            bttdeleteA.addActionListener(listener); 
        } 
  
        public String getState() { 
            return state; 
        } 
    } 
    class MultiButtonRendererAttivita extends DefaultTableCellRenderer { 
  
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private MultiButtonPanelAttivita multiButtonPanel; 
  
        public MultiButtonRendererAttivita() { 
            multiButtonPanel = new MultiButtonPanelAttivita(); 
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
    class MultiButtonEditorAttivita extends AbstractCellEditor implements TableCellEditor { 
  
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private MultiButtonPanelAttivita multiButtonPanel; 
  
        public MultiButtonEditorAttivita() { 
            multiButtonPanel = new MultiButtonPanelAttivita(); 
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
      
    class jTextAreaRenderer implements TableCellRenderer   
    {   
        JScrollPane scrollPane;   
        JTextArea textArea;   
         
        public jTextAreaRenderer()   
        {   
            textArea = new JTextArea();   
            scrollPane = new JScrollPane(textArea);   
        }   
        
        private static int countLines(JTextArea textArea) {
      	  int noLines = 0;
      	  try {  // meso il try erchè altrimenti ogni tanto da errore se non ci sono caratteri
      		  AttributedString text = new AttributedString(textArea.getText());
      		    FontRenderContext frc = textArea.getFontMetrics(textArea.getFont())
      		        .getFontRenderContext();
      		    AttributedCharacterIterator charIt = text.getIterator();
      		    LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(charIt, frc);
      		    float formatWidth = (float) textArea.getSize().width;
      		    lineMeasurer.setPosition(charIt.getBeginIndex());

      		  
      		    while (lineMeasurer.getPosition() < charIt.getEndIndex()) {
      		      lineMeasurer.nextLayout(formatWidth);
      		      noLines++;
      		    }
      	} catch (Exception e) {
      		noLines = 1;
      	}
      	  

      	    return noLines;
      	  }
        
       
        public Component getTableCellRendererComponent(JTable table,   
                                                       Object value,   
                                                       boolean isSelected,   
                                                       boolean hasFocus,   
                                                       int row, int column)   
        {   textArea.setLineWrap(true); 
            textArea.setWrapStyleWord(true); 
            
            textArea.setText((String)value);   
            //si imposta il setFont perche altrimenti ha un carattere 
            //textArea.setFont(DData.FONT); 
           // textArea.setFont(new java.awt.Font("Tahoma", 0, 11)); 
            int fontHeight = textArea.getFontMetrics(textArea.getFont()).getHeight(); 
            int textLength = textArea.getText().length(); 
            //int lines = (textLength / (table.getColumnModel().getColumn(column).getWidth()/8));//+1, cause we need at least 1 row.  
            int lines = countLines(textArea);
            //int len = textArea.getText().split(System.getProperty("line.separator")).length;
            int acapo = DGen.contaACapo(textArea.getText());
            int lineafinale = lines+acapo;
           // System.out.println("CELL RENDERE/" + fontHeight + "/" + textLength + "/" + lines + "___"  + countLines(textArea));
            int height = fontHeight * lineafinale+10 + lines; //lines inserito per aumentare il margine se ci sono molte linee 
             
            table.setRowHeight(row, height);   
            return scrollPane;   
        }   
    }   
    class JTextAreaEditor implements TableCellEditor   
    {   
        JTextArea textArea;   
        JScrollPane scrollPane;   
         
        public JTextAreaEditor()   
        {   
            textArea = new JTextArea();   
            scrollPane = new JScrollPane(textArea);   
        }   
         
        public Component getTableCellEditorComponent(JTable table,   
                                                     Object value,   
                                                     boolean isSelected,   
                                                     int row, int column)   
        {   
            textArea.setLineWrap(true); 
            textArea.setWrapStyleWord(true); 
            textArea.setText((String)value);   
            
              
             
            return scrollPane;   
        }   
         
        public void addCellEditorListener(CellEditorListener l) { }   
        public void cancelCellEditing() { }   
        public Object getCellEditorValue()   
        {   
            return textArea.getText();   
        }   
        public boolean isCellEditable(EventObject anEvent)   
        {   
            return true;   
        }   
        public void removeCellEditorListener(CellEditorListener l) { }   
        public boolean shouldSelectCell(EventObject anEvent)   
        {   
            return true;   
        }   
        public boolean stopCellEditing()   
        {   
            return true;   
        }   
    }   
  
    
