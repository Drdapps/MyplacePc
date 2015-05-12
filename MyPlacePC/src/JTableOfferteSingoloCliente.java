

import java.awt.BorderLayout; 
import java.awt.Component; 
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
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
  
  
  
  
public class JTableOfferteSingoloCliente extends JPanel { 
      
   
	/**
	 * 
	 */

	public static JTable tableOfferte; // JTable
	private BorderLayout border_layout;
     
	
	static Object[][] dataOff= null;
   //pulsanti sopra
	public JButton bttCancellaTestoOfferte; //,bttRicerca
	
	public static JTextField txtRicercaOfferte;
	
	public static JComboBox JcomboSelezioneOfferte, JcomboOrdinamentoOfferte;
	
	public static JPanel UILpanel_top;
	
	public static  HashMap<Integer, Integer> jtableOffertaSingolaHeigh = new HashMap<Integer, Integer>();
	
	//
  
    public JTableOfferteSingoloCliente(boolean completo) { 
    	//pulsanti sopra tabella
    	
    	
    			
    			System.out.println("OFFERTE SING AGGIORNA 83/" +DData.idClienteSelezionato );
    	  UILpanel_top = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	  
    	  txtRicercaOfferte = new JTextField("", 15);
    	  txtRicercaOfferte.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				MyTableModelOffertsaSingola model2 = (MyTableModelOffertsaSingola) tableOfferte.getModel(); 
		    	
				model2.dataTabella = aggiornaDatiTabellaOfferta(DData.idClienteSelezionato,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
		    	model2.fireTableDataChanged();
				
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				
				
				
				MyTableModelOffertsaSingola model2 = (MyTableModelOffertsaSingola) tableOfferte.getModel(); 
		    	
				model2.dataTabella = aggiornaDatiTabellaOfferta(DData.idClienteSelezionato,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
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
				refreshTabellaOfferta(DData.idClienteSelezionato,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
			
			}
		});
        JcomboOrdinamentoOfferte = new JComboBox(DString.jOffertaSorting);
       // JcomboOrdinamento.setSelectedIndex(1);//seleziona data crescente
        JcomboOrdinamentoOfferte.setPrototypeDisplayValue("Data attività Descres"); //imposta la larghezza massima
        JcomboOrdinamentoOfferte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshTabellaOfferta(DData.idClienteSelezionato,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
				
			}
		});
        JcomboSelezioneOfferte = new JComboBox(DString.jOffertaSelezione);
        JcomboSelezioneOfferte.setMaximumRowCount(16);
        JcomboSelezioneOfferte.setPrototypeDisplayValue("Data attività Descresc"); //imposta la larghezza massima
        JcomboSelezioneOfferte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				refreshTabellaOfferta(DData.idClienteSelezionato,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
				
				
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
        	tableOfferte = new JTable(new MyTableModelOffertsaSingola(col_names_offerta, dataOff));  
              
                
                MultiButtonRenderersOffertaSingola rendererOfferta = new MultiButtonRenderersOffertaSingola(); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConPulsantiOfferta).setCellRenderer(rendererOfferta); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConPulsantiOfferta).setCellEditor( new MultiButtonEditorsOffertaSingola()); 
                 ///agiungere testo multiwra, 
                jTextAreaRendererOffertaSingola renderTestoDesc = new jTextAreaRendererOffertaSingola(); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConJtextAreaoffertaDesc).setCellRenderer(renderTestoDesc); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConJtextAreaoffertaDesc).setCellEditor(new JTextAreaEditor()); 
                
                jTextAreaRendererOffertaSingola renderTestoNote = new jTextAreaRendererOffertaSingola(); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConJtextAreaoffertaNota).setCellRenderer(renderTestoNote); 
                tableOfferte.getColumnModel().getColumn(DData.numeroColonnaConJtextAreaoffertaNota).setCellEditor(new JTextAreaEditor()); 
                
                
            
                refreshTabellaOfferta(DData.idClienteSelezionato,JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
                
                aggiornaDimensioniColonneOffertaSingola();
                
                tableOfferte.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						
						if (arg0.getValueIsAdjusting()) //evita di chiamre l'evento due volta quando perde selezione e quando acquisisce con questo valore rimane l'ultimo se metto !arg0 rimane il primo
						{ 
							
				       
									int viewRow = tableOfferte.getSelectedRow();
									int column = tableOfferte.getSelectedColumn();
									String nome = "";
									try {
										System.out.println("jTAbleTodo 201 /"+ tableOfferte.getSelectedRow() + "/" + 5);
										int rigaSelezionato = tableOfferte.getSelectedRow();
										if (rigaSelezionato == -1) rigaSelezionato=0;
										nome = (String) tableOfferte.getValueAt(rigaSelezionato, 1);
										long idTable = (long) tableOfferte.getValueAt(rigaSelezionato, 7);
										
										DData.idClienteSelezionato = (long) tableOfferte.getValueAt(rigaSelezionato, DData.numeroColonnaConPulsantiOfferta);
									
									} catch (Exception e) {
										System.err.println("Jtable todo errore 208/"+e.getMessage() + "/" + nome + "/" + tableOfferte.getValueAt(tableOfferte.getSelectedRow(),7));
									}
						
										System.out.println("JTABLE TODO id selezionato/"+DData.idClienteSelezionato);
										if (DData.idClienteSelezionato>=0) 
										 {	 if (tableOfferte.getSelectedColumn()>0)
												 {
											 
											 //ClientiPanel.aggiornaCliente( DData.idClienteSelezionato);
											 try {
												 
												
												
											} catch (Exception e) {
												//per evitare eoore di selecto row -1 se aggiorna da panel clienti
												System.err.println("errore nella selezione del cliente da ClientiPanel /" + DData.idClienteSelezionato);
												
												
											}
											 
											 	 }
										 	
										 }else{
											 ClientiPanel.aggiornaCliente( DData.idClienteSelezionato);
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
    	System.out.println("OFFERTA SINGOLO CLIENTE REFERSH");
    	MyTableModelOffertsaSingola model = (MyTableModelOffertsaSingola) tableOfferte.getModel(); 
    	
		model.dataTabella = aggiornaDatiTabellaOfferta(idCliente, JcomboOrdinamentoOfferte.getSelectedIndex(),JcomboSelezioneOfferte.getSelectedIndex(), txtRicercaOfferte.getText());
		System.out.println("OFFERTE SINGOLO CLIENTE ="+ idCliente+  "/" + ricerca + "/" + model.dataTabella.length);
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
    
  
    public void aggiornaDimensioniColonneOffertaSingola()
    {	//totale deve essere 600
    	DGen.hideColumn(tableOfferte, 0);
    
    	DGen.deffixWidth(tableOfferte, 1, 0);
    	DGen.deffixWidth(tableOfferte, 2, 250);
    
    	DGen.deffixWidth(tableOfferte, 3,60);
    	DGen.deffixWidth(tableOfferte, 4, 30);
    	DGen.deffixWidth(tableOfferte, 5, 55);
    	DGen.fixWidth(tableOfferte, 6, 200);
    	DGen.deffixWidth(tableOfferte, 7, 60);
    }
    
    
    public static Object[][] aggiornaDatiTabellaOfferta(long idcliente, int ordimanetoInt, int selezioneFatto, String ricerca)
    {	List <Offerta> listaOfferta = new ArrayList<Offerta>();
    	
   
   
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
    		
    		
    		listaOfferta = DataSource.getOffertaClienteNome(idcliente,ordinamento, colonna, selezioneFatto, ricerca);
    		
    		
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
       

  
    
      
  
    
  
class MyTableModelOffertsaSingola extends AbstractTableModel { 
	  
	  /**
	 * 
	 */

	public String columns_nome[];
	public  Object[][] dataTabella;

  public MyTableModelOffertsaSingola(String columns_headers[], Object[][] dataTabella) {
	
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
	 
     
	  
      if (columnIndex == DData.numeroColonnaConPulsantiOfferta){ 

      
          if ("editA".equals(aValue)) {
          	
          	long idSelezionato = (long) JTableOfferteSingoloCliente.tableOfferte.getValueAt(rowIndex, 0);
        	String nomeCliente = (String) JTableOfferteSingoloCliente.tableOfferte.getValueAt(rowIndex, 1);
              System.err.println("idselezionato/"+idSelezionato);
              
  				//aggiornarofferta
              int result =  DGen.OffertaAdd(idSelezionato, nomeCliente, DData.idClienteSelezionato);
              if (result==JOptionPane.OK_OPTION)
				{ 
						Offerta offe = DGen.recuperateOffertaDaMaschera(idSelezionato, DData.idClienteSelezionato);
						DataSource.aggiornaOffertaID(offe);
	         	 
						JTableOfferte.refreshTabellaOfferta(-1,
										JTableOfferte.JcomboOrdinamentoOfferte.getSelectedIndex(),
												JTableOfferte.JcomboSelezioneOfferte.getSelectedIndex(), 
												JTableOfferte.txtRicercaOfferte.getText());
						
	
						JTableOfferteSingoloCliente.refreshTabellaOfferta(offe.getpIdClienteOfferta(),JTableOfferteSingoloCliente.JcomboOrdinamentoOfferte.getSelectedIndex(),
								JTableOfferteSingoloCliente.JcomboSelezioneOfferte.getSelectedIndex(), JTableOfferteSingoloCliente.txtRicercaOfferte.getText());
						System.out.println("OFFERTE SING AGGIORNA 556/" +offe.getpIdClienteOfferta() );
						
					
					
            	  	
				}
          } 
          //if ("newA".equals(aValue)) {System.out.println("NEWA"); } 
          if ("deleteA".equals(aValue)) {
        	  System.err.println("JTABLEOFFSINGOLA/" + rowIndex + "/" );
        		long idSelezionato = (long) JTableOfferteSingoloCliente.tableOfferte.getValueAt(rowIndex, 0);
              	boolean cancella = true;
      			if (DData.GenChiediCOnfermaPrimacancellare==true)
      	    	{   	if (DGen.showConfirm(DString.gAttenzione, "1 " + DString.msgcancellazioneOfferta, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)  
      	    	   		{cancella = true	;
      	    	   		}else{
      	    	   			cancella = false;
      	    	   		}
      	    	}
      			System.err.println("JTABLEOFFSINGOLA/" + cancella + "/" + idSelezionato);
      			if (cancella)
      			{	
      				
      					DataSource.cancellaOfferta(idSelezionato);
          				JTableOfferteSingoloCliente.refreshTabellaOffertaDaAltraTabella(DData.idClienteSelezionato);
          				
          				System.out.println("OFFERTE SING AGGIORNA 574/" +DData.idClienteSelezionato );
          				JTableOfferte.refreshTabellaOfferta(-1,
    							JTableOfferte.JcomboOrdinamentoOfferte.getSelectedIndex(),
    									JTableOfferte.JcomboSelezioneOfferte.getSelectedIndex(), 
    									JTableOfferte.txtRicercaOfferte.getText());
          				
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
	case 2:
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


class MultiButtonRenderersOffertaSingola extends DefaultTableCellRenderer { 

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MultiButtonPanelOfferta multiButtonPanel; 

  public MultiButtonRenderersOffertaSingola() { 
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
class MultiButtonEditorsOffertaSingola extends AbstractCellEditor implements TableCellEditor { 

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MultiButtonPanelOfferta multiButtonPanel; 

  public MultiButtonEditorsOffertaSingola() { 
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
  class jTextAreaRendererOffertaSingola implements TableCellRenderer   
  {   
    JScrollPane scrollPane;   
    JTextArea textArea;   
    
    
    public jTextAreaRendererOffertaSingola()   
    {   
        textArea = new JTextArea();   
        scrollPane = new JScrollPane(textArea);   
    }   
    
    private int countLines(JTextArea textArea) {
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
  		//System.out.println("JOFFERTA SINGOLA CLIENTE:"+ column + "/" + height + "/" + "/" + textArea.getText());
  		JTableOfferteSingoloCliente.jtableOffertaSingolaHeigh.put(row, height);
  		
  		break;
  	case 6:
  		//controllo altezza delle 2
  		
  		int height2 = JTableOfferteSingoloCliente.jtableOffertaSingolaHeigh.get(row);
  		int height6 = altezzaJtextArea(textArea, fontHeight);
  		height =  Math.max(height2, height6);
  		//System.out.println("JOFFERTA  SINGOLA CLIENTE:"+ column + "/" + height + "/" + height2 +"/" + height6+"/" + textArea.getText());
  		break;
  		
  	default:
  		height =  altezzaJtextArea(textArea, fontHeight);
  		break;
  	}
    
   
    table.setRowHeight(row, height);   
    return scrollPane;   

    }   
  }  
  



