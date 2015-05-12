
import java.awt.BorderLayout; 
import java.awt.Component; 
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.sql.SQLException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Date;
import java.util.EventObject; 
import java.util.HashMap;
import java.util.List; 
import java.util.Locale;
import java.util.Locale.Category;

import javax.swing.AbstractCellEditor; 
import javax.swing.ImageIcon; 
import javax.swing.JButton; 
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import javax.swing.JTextArea; 
import javax.swing.JTextField;
import javax.swing.SwingUtilities; 
import javax.swing.event.CellEditorListener; 
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel; 
import javax.swing.table.DefaultTableCellRenderer; 
import javax.swing.table.TableCellEditor; 
import javax.swing.table.TableCellRenderer; 
import javax.swing.table.TableColumn;

import org.sqlite.Function.Aggregate;
  
  
  
  
public class JTableOfferte extends JPanel { 
      
   
	/**
	 * 
	 */

	public static JTable tableOfferte; // JTable
	private BorderLayout border_layout;
	 private long idSelezionatoAncheNonDaMouse; //prende il valore dell'id selezionato verra assegnato solo se c'è un click da maouse per evitare di aggiornare se aggiorno senza click
		
	
	static Object[][] dataOff= null;
   //pulsanti sopra
	public JButton bttCancellaTestoOfferte; //,bttRicerca
	
	public static JTextField txtRicercaOfferte;
	
	public static JComboBox JcomboSelezioneOfferte, JcomboOrdinamentoOfferte;
	
	public static JPanel UILpanel_top;
	
	public  static long	idCliente = -1; //per tutte le offerte
	
	//
	public static  HashMap<Integer, Integer> jtableOffertaTotaleHeigh = new HashMap<Integer, Integer>();
	
	
    public JTableOfferte(boolean completo) { 
    	//pulsanti sopra tabella
    	
    		
    	  UILpanel_top = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	  
    	  txtRicercaOfferte = new JTextField("", 15);
    	  txtRicercaOfferte.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				MyTableModelOfferta model2 = (MyTableModelOfferta) tableOfferte.getModel(); 
		    	
				model2.dataTabella = aggiornaDatiTabellaOfferta(idCliente,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
		    	model2.fireTableDataChanged();
				
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				
				
				
				MyTableModelOfferta model2 = (MyTableModelOfferta) tableOfferte.getModel(); 
		    	
				model2.dataTabella = aggiornaDatiTabellaOfferta(idCliente,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
		    	model2.fireTableDataChanged();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
      
        bttCancellaTestoOfferte = new JButton(new ImageIcon(getClass().getResource(DData.iconaCancellaTesto)));
        bttCancellaTestoOfferte.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
        bttCancellaTestoOfferte.setToolTipText(DString.ttCancellaRicerca);
        bttCancellaTestoOfferte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				txtRicercaOfferte.setText("");
				refreshTabellaOfferta(idCliente,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
				
			}
		});
        JcomboOrdinamentoOfferte = new JComboBox(DString.jOffertaSorting);
       // JcomboOrdinamento.setSelectedIndex(1);//seleziona data crescente
        JcomboOrdinamentoOfferte.setPrototypeDisplayValue("Data attività Descres"); //imposta la larghezza massima
        JcomboOrdinamentoOfferte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshTabellaOfferta(idCliente,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
					
			}
		});
        JcomboSelezioneOfferte = new JComboBox(DString.jOffertaSelezione);
        JcomboSelezioneOfferte.setMaximumRowCount(16);
        JcomboSelezioneOfferte.setPrototypeDisplayValue("Data attività Descresc"); //imposta la larghezza massima
        JcomboSelezioneOfferte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshTabellaOfferta(idCliente,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
				
				
			}
		});
       
       
        
        UILpanel_top.add(JcomboOrdinamentoOfferte);
        UILpanel_top.add(JcomboSelezioneOfferte);
        UILpanel_top.add(txtRicercaOfferte);
        UILpanel_top.add(bttCancellaTestoOfferte);
        
        //UILpanel_top.add(bttRicerca);
        
       
    	
    	//
    	 border_layout = new BorderLayout(); // creo il layout manager e lo assegno al container
        setLayout(border_layout);
    			
    	
                
                  
               // String col_names_attivita[] = {DString.attid,DString.attData,DString.attFatto,DString.cliNome,DString.attDescrizione,};
              
        	//String col_names_attivita[] = {DString.offid,DString.offCliente,DString.offDescrizione, DString.offImporto,DString.offData, DString.offpercentuale, DString.offNote};
        String col_names_offerta[] = {"1","2","3"};
        	tableOfferte = new JTable(new MyTableModelOfferta(col_names_offerta, dataOff));  
              
                
                MultiButtonRendererOfferta rendererOfferta = new MultiButtonRendererOfferta(); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConPulsantiOfferta).setCellRenderer(rendererOfferta); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConPulsantiOfferta).setCellEditor( new MultiButtonEditorOfferta()); 
                 ///agiungere testo multiwra, 
                jTextAreaRendererOfferta renderTestoDesc = new jTextAreaRendererOfferta(); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConJtextAreaoffertaDesc).setCellRenderer(renderTestoDesc); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConJtextAreaoffertaDesc).setCellEditor(new JTextAreaEditor()); 
                
                jTextAreaRendererOfferta renderTestoNote = new jTextAreaRendererOfferta(); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConJtextAreaoffertaNota).setCellRenderer(renderTestoNote); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConJtextAreaoffertaNota).setCellEditor(new JTextAreaEditor()); 
                
                
            
                refreshTabellaOfferta(idCliente,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
				aggiornaDimensioniColonneOfferta();
                tableOfferte.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						DData.idClienteSelezionato = idSelezionatoAncheNonDaMouse;
						if (DData.idClienteSelezionato>=0) 
						 {	 if (tableOfferte.getSelectedColumn()>0)
								 {
							 
							 ClientiPanel.aggiornaCliente( DData.idClienteSelezionato);
							 try {
								 
								
								
							} catch (Exception e1) {
								//per evitare eoore di selecto row -1 se aggiorna da panel clienti
								System.err.println("errore nella selezione del cliente da ClientiPanel /" + DData.idClienteSelezionato);
								
								
							}
							 
							 	 }
						 	
						 }else{
							 ClientiPanel.aggiornaCliente( DData.idClienteSelezionato);
						 }
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
                tableOfferte.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						
						if (arg0.getValueIsAdjusting()) //evita di chiamre l'evento due volta quando perde selezione e quando acquisisce con questo valore rimane l'ultimo se metto !arg0 rimane il primo
						{ 
							
				       
									int viewRow = tableOfferte.getSelectedRow();
									int column = tableOfferte.getSelectedColumn();
									String nome = "";
									try {
										int rigaSelezionato = tableOfferte.getSelectedRow();
										if (rigaSelezionato == -1) rigaSelezionato=0;
										nome = (String) tableOfferte.getValueAt(rigaSelezionato, 1);
										long idTable = (long) tableOfferte.getValueAt(rigaSelezionato, 7);
										
										
										idSelezionatoAncheNonDaMouse = (long) tableOfferte.getValueAt(rigaSelezionato, DData.numeroColonnaConPulsantiOfferta);
										
									} catch (Exception e) {
										System.err.println("Jtable todo errore 208/"+e.getMessage() + "/" + nome + "/" + tableOfferte.getValueAt(tableOfferte.getSelectedRow(),7));
									}
						
										
						
						
						
						 }
						
					}
				});
                //Dimension dimA = new Dimension(15,1);
           
               
                JScrollPane panelscroll = new JScrollPane();
                
              
               JScrollPane panel_table_bottom = new JScrollPane(tableOfferte);
              
               add(UILpanel_top,BorderLayout.NORTH);
                add(panel_table_bottom, BorderLayout.CENTER); // aggiungo lo split pane al container
                
                  
                  
               
            } 
    
    public static void refreshTabellaOffertaDaAltraTabella(long idCliente)
    {
    	int ordinamento=1; //data di chiusura decrescente;
    	int selezione = 3; //tutte
    	String ricerca = "";
    	refreshTabellaOfferta(idCliente, ordinamento, selezione, ricerca);
    }
    
    public static void refreshTabellaOfferta (long idCliente, int ordimaneto, int selezione, String ricerca)
    {	
    	
    	MyTableModelOfferta model = (MyTableModelOfferta) tableOfferte.getModel(); 
    	
		model.dataTabella = aggiornaDatiTabellaOfferta(idCliente, JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
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
    
  
    public void aggiornaDimensioniColonneOfferta()
    {	//totale deve essere 600
    	DGen.hideColumn(tableOfferte, 0);
    
    	DGen.deffixWidth(tableOfferte, 1, 100);
    	DGen.deffixWidth(tableOfferte, 2, 150);
    
    	DGen.deffixWidth(tableOfferte, 3, 60);
    	
    	
    	DGen.deffixWidth(tableOfferte, 4, 30);
    	DGen.deffixWidth(tableOfferte, 5, 55);
    	DGen.fixWidth(tableOfferte, 6, 100);
    	DGen.deffixWidth(tableOfferte, 7, 60);
    }
    
    
    public static Object[][] aggiornaDatiTabellaOfferta(long idcliente, int ordimanetoInt, int selezioneFatto, String ricerca)
    {	List <Offerta> listaOfferta = new ArrayList<Offerta>();
    	
    System.out.println("AGGIORNA DATI TABELLA OFFERTA");
    
    	try {
    		//paremtri per costruzione query
    		String ordinamento="";
    		String colonna="";
    		switch (ordimanetoInt) {
			case 0:  
				ordinamento = DData.crescente; 
				colonna = mydbhelper.OFFERTA_DATACHIUSURA;
				break;
			case 1:  
				ordinamento = DData.decrescente; 
				colonna = mydbhelper.OFFERTA_DATACHIUSURA;
				break;
			case 2:  
				ordinamento = DData.crescente; 
				colonna=mydbhelper.OFFERTA_IMPORTO;
				break;
			case 3:  
				ordinamento = DData.decrescente; 
				colonna=mydbhelper.OFFERTA_IMPORTO;
				break;
			case 4:  
				ordinamento = DData.crescente; 
				colonna=mydbhelper.OFFERTA_PERCENTUALECHIUSURA;
				break;
			case 5:  
				ordinamento = DData.decrescente; 
				colonna=mydbhelper.OFFERTA_PERCENTUALECHIUSURA;
				break;
			default:
				break;
			}
    		
    		
    		//int selezioneFatto=1;  //00ad fare;1=fatto;2=tutti
    		
    		
    		listaOfferta = DataSource.getOffertaClienteTutti(idcliente,ordinamento, colonna, selezioneFatto, ricerca);
    		
    		
    		//listaClienti = DataSource.getAllClienti(ricerca, 0, 0, 0); //ricerca tipologia lati e longi
		} catch (Exception e) {
			// TODO: handle exception
		}
    	Object[][] dataDataBase = new Object[listaOfferta.size()][DData.numeroColonneTotalOfferta];
    	if (listaOfferta.size()>0)
    	{
    		int i =0;
    		NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        	Locale fmtLocale = Locale.getDefault(Category.FORMAT);
        	NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
        	 DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, getDefaultLocale());
        	 
    	for(i=0;i<listaOfferta.size();i++)
		    	{	
    				dataDataBase[i][0]= listaOfferta.get(i).getpIdOfferta();
    				dataDataBase[i][1]= listaOfferta.get(i).getpNomeCliente();
    				dataDataBase[i][2]= listaOfferta.get(i).getpDescrizioneOfferta();
    				dataDataBase[i][3]= listaOfferta.get(i).getpImportoOfferta();
    				dataDataBase[i][4]= listaOfferta.get(i).getpPercentuale();
    				dataDataBase[i][5]= dateFormatter.format(new Date(listaOfferta.get(i).getpDataChiusura())); 
    				dataDataBase[i][6]= listaOfferta.get(i).getpNota();
    				dataDataBase[i][7]= listaOfferta.get(i).getpIdClienteOfferta(); //metto nella colonno 7 l'idcliente è la colonna dei pulsanti
    				
    				
    			}
    	
    	}
    	return dataDataBase;
    	
    }
}
       

  
    
      
  
    
  
class MyTableModelOfferta extends AbstractTableModel { 
	  
	  /**
	 * 
	 */

	public String columns_nome[];
	public  Object[][] dataTabella;

  public MyTableModelOfferta(String columns_headers[], Object[][] dataTabella) {
	
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
  
  public void removeRow(int row){
  	
 	 
 	 List<Object[]> lo = new ArrayList<Object[]>(Arrays.asList(dataTabella));
 	 lo.remove(row);
 	 dataTabella = lo.toArray(new Object[][]{});
 	 
 	 fireTableDataChanged();
 	 }
  
  @Override
  public String getColumnName(int column) { 
	  
      String value = null; 
      switch (column) { 
          case 0: 
              value = DString.offid; 
              break; 
          case 1: 
              value = DString.offCliente; 
              break; 
          case 2:
            	value = DString.offDescrizione;
            	break;    
          case 3:
        	  	value = DString.offImporto;
        	  	break;
          case 4: 
              value = DString.offpercentuale; 
              break;
          case 5: 
              value = DString.offData; 
              break;
          case 6: 
              value = DString.offNote; 
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
      return DData.numeroColonneTotalOfferta; 
  } 

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) { 
	  
  	return dataTabella[rowIndex][columnIndex];
  } 
  
  public void removeSelectedRows(JTable table, int row){
	   TabellaList model = (TabellaList) table.getModel();
	  
	   model.removeRow(row);
	   
	 
	}
  
  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) { 
	 
      //or serve se vuoglio aggiungere altra colonna con pulsanti 
	  /*
      if (columnIndex==2) //cliccato su vistato
      {
    	  long idSelezionato = (long) JTableTodo.tableAttivita.getValueAt(rowIndex, 0); //recuero ID
    	  Attivita atti;
		try {
			atti = DataSource.getAttivita(idSelezionato);
			int attiFatta = 0;
			if ((boolean) aValue) attiFatta = 1; //se è vero allo restituisco 1 else 0
			 System.err.println("setValueAt/"+ aValue + "/" +attiFatta);
			atti.setpFattaAttivita(attiFatta);
			DataSource.aggiornaAttivitaID(atti); //aggiorna attivita
			long massimaAttivita = DataSource.massimaDataattivita(atti.getpIdCliente());
			DataSource.aggiornaClientedaAttivita(atti.getpIdCliente(), massimaAttivita);
			ListClient.refreshTabella(); //aggiorna tabella lista
			//non aggiornao la tabella todo
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JTableTodo.refreshTabellaAttivita(JTableTodo.JcomboOrdinamento.getSelectedIndex(),
				JTableTodo.JcomboSelezione.getSelectedIndex(), JTableTodo.txtRicerca.getText());
		fireTableCellUpdated(rowIndex, columnIndex);
			
      }*/
	  
      if (columnIndex == DData.numeroColonnaConPulsantiOfferta){ 

      
          if ("editA".equals(aValue)) {
          	
          	long idSelezionato = (long) JTableOfferte.tableOfferte.getValueAt(rowIndex, 0);
          	String nomeCliente = (String) JTableOfferte.tableOfferte.getValueAt(rowIndex, 1);
              System.err.println("idselezionato/"+idSelezionato);
              
  				//aggiornarofferta
              long idCliente = -1;
			try {
				idCliente = DataSource.getOfferta(idSelezionato).getpIdClienteOfferta();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              
              int result =  DGen.OffertaAdd(idSelezionato,nomeCliente,idCliente);
              if (result==JOptionPane.OK_OPTION)
				{ 
            	  Offerta offe = DGen.recuperateOffertaDaMaschera(idSelezionato,-1);
            	
            	  DataSource.aggiornaOffertaID(offe);
            	 
					JTableOfferte.refreshTabellaOfferta(-1,
									JTableOfferte.JcomboOrdinamentoOfferte.getSelectedIndex(),
											JTableOfferte.JcomboSelezioneOfferte.getSelectedIndex(), 
											JTableOfferte.txtRicercaOfferte.getText());
					
					
					JTableOfferteSingoloCliente.refreshTabellaOfferta(DData.idClienteSelezionato,JTableOfferte.JcomboOrdinamentoOfferte.getSelectedIndex(),
											JTableOfferte.JcomboSelezioneOfferte.getSelectedIndex(), JTableOfferte.txtRicercaOfferte.getText());
					
					
            	  
				}
          } 
          //if ("newA".equals(aValue)) {System.out.println("NEWA"); } 
          if ("deleteA".equals(aValue)) {
          	//da riportare anche in jtabelloToAggiornato
        	  System.err.println("JTABLEOFFERTETUTTE/");
          	long idSelezionato = (long) JTableOfferte.tableOfferte.getValueAt(rowIndex, 0);
          	boolean cancella = true;
  			if (DData.GenChiediCOnfermaPrimacancellare==true)
  	    	{   	if (DGen.showConfirm(DString.gAttenzione, "1 " + DString.msgcancellazioneOfferta, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)  
  	    	   		{cancella = false	;
  	    	   		}
  	    	}
  		  System.err.println("JTABLEOFFERTETUTTE/" + cancella+ "/" + idSelezionato);
  			if (cancella)
  			{	
  				
  				System.out.println("offerte tutte cancella/" +dataTabella.length);
  				DataSource.cancellaOfferta(idSelezionato);
  				
  				JTableOfferteSingoloCliente.refreshTabellaOffertaDaAltraTabella(idSelezionato);
  				JTableOfferte.refreshTabellaOfferta(-1,
						JTableOfferte.JcomboOrdinamentoOfferte.getSelectedIndex(),
								JTableOfferte.JcomboSelezioneOfferte.getSelectedIndex(), 
								JTableOfferte.txtRicercaOfferte.getText());
  				System.err.println("JATABLE OFFERTE TUTTE/" + dataTabella.length + "/" + aValue);
  				 //dataTabella[rowIndex][columnIndex] = aValue;
  	  		
  	  	          fireTableCellUpdated(rowIndex, columnIndex); 
  			}
  			

          } 
      }      
  } 

  public void add(Offerta value) { 
     /* int startIndex = getRowCount(); 
      data.add(value); 
      fireTableRowsInserted(startIndex, getRowCount() - 1); 
      */
  } 

  public void remove(Offerta value) { 
    /* int startIndex = data.indexOf(value); 
      System.out.println("startIndexA = " + startIndex); 
      data.remove(value); 
      fireTableRowsInserted(startIndex, startIndex); 
      
      */
  } 

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) { 
	 
	  boolean result = false;
	  switch (columnIndex) {
	  	case 2: //la colonno 2 ed editable
		//result=true;
		break;
		case DData.numeroColonnaConPulsantiOfferta:
		result=true;
		break;
		
	default:
		break;
	}
	  
	  return result;
      
  } 
  
  
  
} 

class MultiButtonPanelOfferta extends JPanel { 

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JButton bttnewA; 
  private JButton btteditA,  bttdeleteA ; 
    
  private String state; 

  public MultiButtonPanelOfferta() { 
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
              System.out.println("Jtable offerte StateA = " + state); 
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
class MultiButtonRendererOfferta extends DefaultTableCellRenderer { 

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MultiButtonPanelOfferta multiButtonPanel; 

  public MultiButtonRendererOfferta() { 
      multiButtonPanel = new MultiButtonPanelOfferta(); 
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
class MultiButtonEditorOfferta extends AbstractCellEditor implements TableCellEditor { 

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MultiButtonPanelOfferta multiButtonPanel; 

  public MultiButtonEditorOfferta() { 
      multiButtonPanel = new MultiButtonPanelOfferta(); 
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

class jTextAreaRendererOfferta implements TableCellRenderer   
{   
  JScrollPane scrollPane;   
  JTextArea textArea;   
  
  
  public jTextAreaRendererOfferta()   
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
  
  public int altezzaJtextArea (JTextArea jtxt, int fontHeight)
  {	int height = 0;
  		int textLength = jtxt.getText().length();
			int lines = countLines(jtxt);
			int acapo= DGen.contaACapo(jtxt.getText());
			int lineafinale = lines+acapo;
			height = fontHeight * lineafinale+10 + lines;//lines inserito per aumentare il margine se ci sono molte linee 
	
  
  	return height;
  }
 
  public Component getTableCellRendererComponent(JTable table,   
                                                 Object value,   
                                                 boolean isSelected,   
                                                 boolean hasFocus,   
                                                 int row, int column)   
  {   textArea.setLineWrap(true); 
  		textArea.setWrapStyleWord(true); 
  		textArea.setText((String)value);   
  //imposto variabili
  int fontHeight = textArea.getFontMetrics(textArea.getFont()).getHeight(); 
  
  int textLength,lines,acapo = 0,lineafinale,height = 0; 
 
 
 
  switch (column) {
	case 2:
		height =  altezzaJtextArea(textArea, fontHeight);
		System.out.println("JOFFERTA:"+ column + "/" + height + "/" + "/" + textArea.getText());
		JTableOfferte.jtableOffertaTotaleHeigh.put(row, height);
		
		break;
	case 6:
		//controllo altezza delle 2
		
		int height2 = JTableOfferte.jtableOffertaTotaleHeigh.get(row);
		int height6 = altezzaJtextArea(textArea, fontHeight);
		height =  Math.max(height2, height6);
		System.out.println("JOFFERTA:"+ column + "/" + height + "/" + height2 +"/" + height6+"/" + textArea.getText());
		break;
		
	default:
		height =  altezzaJtextArea(textArea, fontHeight);
		break;
	}
  
 
  table.setRowHeight(row, height);   
  return scrollPane;   

  }   
}  
class JTextAreaEditorOfferta implements TableCellEditor   
{   
  JTextArea textArea;   
  JScrollPane scrollPane;   
   
  public JTextAreaEditorOfferta()   
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

