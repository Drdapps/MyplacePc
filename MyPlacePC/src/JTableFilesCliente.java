

import java.awt.BorderLayout; 
import java.awt.Component; 
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.SQLException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Date;
import java.util.EventObject; 
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
  
  
  
  
public class JTableFilesCliente extends JPanel { 
      
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTable tableFiles; // JTable
	private BorderLayout border_layout;
     
	
	static Object[][] dataFile= null;
   //pulsanti sopra
	
	
	public static JPanel UILpanel_top;
	
	
	//
  
    public JTableFilesCliente() { 
    	//pulsanti sopra tabella
    	
    	  UILpanel_top = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
        //UILpanel_top.add(bttRicerca);
        
       
    	
    	//
    	 border_layout = new BorderLayout(); // creo il layout manager e lo assegno al container
        setLayout(border_layout);
    			
    	
                
                  
       
        	String col_names_file[] = {DString.idFile,DString.fileNome,DString.fileDimensione, DString.fileData, DString.fileDesc};
              tableFiles = new JTable(new MyTableModelFile(col_names_file, dataFile));  
              
                
                MultiButtonRendererFile rendererFile = new MultiButtonRendererFile(); 
                tableFiles.getColumnModel().getColumn(DData.numeroColonnaConPulsantiFile).setCellRenderer(rendererFile); 
                tableFiles.getColumnModel().getColumn(DData.numeroColonnaConPulsantiFile).setCellEditor(new MultiButtonEditorFile()); 
                 ///agiungere testo multiwra, 
                jTextAreaRendererFiles renderTesto = new jTextAreaRendererFiles(); 
                tableFiles.getColumnModel().getColumn(DData.numeroColonnaConJtextAreaFile).setCellRenderer(renderTesto); 
                tableFiles.getColumnModel().getColumn(DData.numeroColonnaConJtextAreaFile).setCellEditor(new JTextAreaEditor()); 
                //tableAttivita.setPreferredScrollableViewportSize(tableAttivita.getPreferredSize());  
                refreshTabellaFile();
                aggiornaDimensioniColonneFile();
                
                tableFiles.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						
						if (arg0.getValueIsAdjusting()) //evita di chiamre l'evento due volta quando perde selezione e quando acquisisce con questo valore rimane l'ultimo se metto !arg0 rimane il primo
						{ 
							
				       
									int viewRow = tableFiles.getSelectedRow();
									int column = tableFiles.getSelectedColumn();
									String nome = "";
									try {
										int rigaSelezionato = tableFiles.getSelectedRow();
										if (rigaSelezionato == -1) rigaSelezionato=0;
										nome = (String) tableFiles.getValueAt(rigaSelezionato, 3);
										long idTable = (long) tableFiles.getValueAt(rigaSelezionato, 5);
										
										
										DData.idClienteSelezionato = (long) tableFiles.getValueAt(rigaSelezionato, 5);
										
									} catch (Exception e) {
										System.err.println("Jtable file errore 208/"+e.getMessage() + "/" + nome + "/" + tableFiles.getValueAt(tableFiles.getSelectedRow(), 5));
									}
						
										if (DData.idClienteSelezionato>=0) 
										 {	 if (tableFiles.getSelectedColumn()>0)
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
                
               // ((JTable) tableAttivita).setIntercellSpacing(new Dimension(dimA)); 
               
                JScrollPane panelscroll = new JScrollPane();
                
              
               JScrollPane panel_table_bottom = new JScrollPane(tableFiles);
              
               add(UILpanel_top,BorderLayout.NORTH);
                add(panel_table_bottom, BorderLayout.CENTER); // aggiungo lo split pane al container
                
                  
                  
               
            } 
    
    
    public static void refreshTabellaFile ()
    {	
    	
    	MyTableModelFile model = (MyTableModelFile) tableFiles.getModel(); 
    	
		model.dataTabella = aggiornaDatiTabellaFile();
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
    
  
    public void aggiornaDimensioniColonneFile()
    {	//totale deve essere 600
    	DGen.hideColumn(tableFiles, 0);
    
    	DGen.deffixWidth(tableFiles, 1, 200);
    	DGen.deffixWidth(tableFiles, 2, 50);
    
    	DGen.deffixWidth(tableFiles, 3, 60);
    	
    	
    	DGen.fixWidth(tableFiles, 4, 300);
    	DGen.deffixWidth(tableFiles, 5, 90);
    }
    
    
    public static Object[][] aggiornaDatiTabellaFile()
    {	List <FileSincronizzati> listaFile = new ArrayList<FileSincronizzati>();
    	
   
    
    	try {
    		//paremtri per costruzione query
    		   		
    		
    		//int selezioneFatto=1;  //00ad fare;1=fatto;2=tutti
    		
    		
    		listaFile = DataSource.getAllFileSincronizzati(DData.idClienteSelezionato);
    		System.err.println("JTABLE NUMERO FILE/" + listaFile.size());
    		
    		//listaClienti = DataSource.getAllClienti(ricerca, 0, 0, 0); //ricerca tipologia lati e longi
		} catch (Exception e) {
			// TODO: handle exception
		}
    	Object[][] dataDataBase = new Object[listaFile.size()][DData.numeroColonneTotalFile];
    	if (listaFile.size()>0)
    	{
    		int i =0;
    		NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        	Locale fmtLocale = Locale.getDefault(Category.FORMAT);
        	NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
        	 DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, getDefaultLocale());
        	 
    	for(i=0;i<listaFile.size();i++)
		    	{	
    				dataDataBase[i][0]= listaFile.get(i).getpIdFile();
    				dataDataBase[i][1]= listaFile.get(i).getpNomeFileOriginario();
    				long dimensione = listaFile.get(i).getpDimensioneFile()/ 1024;
    				dataDataBase[i][2]= dimensione;
    				dataDataBase[i][3]= dateFormatter.format(new Date(listaFile.get(i).getpDataFile())); 
    				dataDataBase[i][4]= listaFile.get(i).getpDescrizione();
    				dataDataBase[i][5]= listaFile.get(i).getpIdCliente(); //metto nella colonno 5 l'idcliente è la colonna dei pulsanti
    				
    				
    			}
    	
    	}
    	return dataDataBase;
    	
    }
}
       

  
    
      
  
    
  
class MyTableModelFile extends AbstractTableModel { 
	  
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String columns_nome[];
	public  Object[][] dataTabella;

  public MyTableModelFile(String columns_headers[], Object[][] dataTabella) {
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
              value = DString.idFile; 
              break; 
          case 1: 
              value = DString.fileNome; 
              break; 
          case 2:
            	value = DString.fileDimensione;
            	break;    
          case 3:
        	  	value = DString.fileData;
        	  	break;
          case 4: 
              value = DString.fileDesc; 
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
      return DData.numeroColonneTotalFile; 
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
	 
    	  
      if (columnIndex == DData.numeroColonnaConPulsantiFile){ 

      
          if ("editA".equals(aValue)) {
          	
          	long idSelezionato = (long) JTableFilesCliente.tableFiles.getValueAt(rowIndex, 0);
          	
              System.err.println("idselezionato/"+idSelezionato);
              
              int result =  DGen.FilesAdd(idSelezionato);
              if (result==JOptionPane.OK_OPTION)
				{ //aggiungireferente
            	  FileSincronizzati fileS = null;
            	  String pathFileVecchi0 = "";
            	  
							try {
								fileS = DataSource.getFile(idSelezionato);
								pathFileVecchi0 = DGen.pathFileLocale(fileS);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
            	  String oldMd5 = fileS.getpMd5();
            	  fileS =  DGen.recuperaFileDaMascehra(idSelezionato);
            	  File fileTmp = new File(DGen.pathFileLocale(fileS));
            	  fileS.setpMd5(DGen.calcolaMd5(DGen.pathFileLocale(fileS)));
            	  fileS.setpDataFile(fileTmp.lastModified());
            	  fileS.setpDimensioneFile(fileTmp.length());
            	  DataSource.aggiornaFileID(fileS);
            	  JTableFilesCliente.refreshTabellaFile();
            	  
            	  
            	  if (!oldMd5.equals(fileS.getpMd5())) 
            		  {
            		  File fileDelete =  new File(pathFileVecchi0);
          		  		fileDelete.delete();
            		  	DGen.copyFileToUpload(fileS);
            		  	
            		  	
            		  }
            	  
            	  
				}
              
              /////
              
              
            /////
				
          } 
          
       if ("visualizzA".equals(aValue))
       {
    	   long idSelezionato = (long) JTableFilesCliente.tableFiles.getValueAt(rowIndex, 0);
    	   String nomeFile =   (String) JTableFilesCliente.tableFiles.getValueAt(rowIndex, 1);
    	   String pathname = DData.pathSetting+DData.pathInDropbox + File.separator + idSelezionato+File.separator+nomeFile;
    	   File file = new File(pathname);
    	   try {
    		   		FileSincronizzati fileS = DataSource.getFile(idSelezionato);
			
    		   			
			    	   System.out.println("PATH ___1" + pathname + "/" + file.exists());
			    	   //percorsoDaCOntrollare deve essere una directory
			    	   Path percorsoDaCOntrollare =  Paths.get(DData.pathSetting+DData.pathInDropbox + File.separator + idSelezionato);
			    	
			    	 
			    		   WatchService watcher;
							try {
								watcher = percorsoDaCOntrollare.getFileSystem().newWatchService();
								MyWatchQueueReader fileWatcher = new MyWatchQueueReader(watcher, fileS);
								Thread th = new Thread(fileWatcher, "FileWatcher");
								th.start();
						        // register a file
						        
						        percorsoDaCOntrollare.register(watcher,StandardWatchEventKinds.ENTRY_MODIFY);
						       // th.join(); tolto altrimenti mi bloccava i programma
						      
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    	   } catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}  
    		   

    		   
    		 
    		
	    	   try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
       else 
		    	   {if ("deleteA".equals(aValue)) {
		        	  long idSelezionato = (long) JTableFilesCliente.tableFiles.getValueAt(rowIndex, 0);
		            	boolean cancella = true;
		    			if (DData.GenChiediCOnfermaPrimacancellare==true)
		    	    	{   	if (DGen.showConfirm(DString.gAttenzione, "1 " + DString.msgcancellazioneFiles, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)  
		    	    	   		{cancella = false	;
		    	    	   		}
		    	    	}
		    			
		    			if (cancella)
		    			{
		    				
		    				
		    					try {
		    						FileSincronizzati fileDaEliminare =DataSource.getFile(idSelezionato);
		    						DGen.deleteCartellaEFile(fileDaEliminare);
			    						
			    					} catch (SQLException e) {
			    						// TODO Auto-generated catch block
			    						e.printStackTrace();
			    					}
		    					System.out.println("JTABLEFILES IDCANCELLA_" + idSelezionato);
			        				DataSource.cancellaFiles(idSelezionato);
			        				JTableFilesCliente.refreshTabellaFile();
			        				
		        				
		        				
		        				
		        	  	          fireTableCellUpdated(rowIndex, columnIndex); 
							
							
		    				
		    				
		    			}
		          } else {
			          dataTabella[rowIndex][columnIndex] = aValue;
			          fireTableCellUpdated(rowIndex, columnIndex); 
			          //remove(value); 
		          }
		   } 
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
	 
	  boolean result = false;
	  switch (columnIndex) {
	case 2:
		result=true;
		break;
		case DData.numeroColonnaConPulsantiFile:
		result=true;
		break;
		
	default:
		break;
	}
	  
	  return result;
      
  } 
  
  
  
} 

class MultiButtonPanelFile extends JPanel { 

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JButton bttnewA; 
  private JButton btteditA,  bttdeleteA , bttvisualizzaA; 
    
  private String state; 

  public MultiButtonPanelFile() { 
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
      
      bttvisualizzaA = new JButton(new ImageIcon(getClass().getResource(DData.iconaVisualizza)));
      bttvisualizzaA.setMaximumSize(dim);
      bttvisualizzaA.setMinimumSize(dim);
      bttvisualizzaA.setActionCommand("visualizzA"); 
      
      add(bttvisualizzaA); 
      add(btteditA); 
      //add(bttnewA); 
      add(bttdeleteA); 

      ActionListener listener = new ActionListener() { 
          @Override
          public void actionPerformed(ActionEvent e) { 
              state = e.getActionCommand(); 
              System.out.println("Jtable todo StateA = " + state); 
          } 
      }; 

      btteditA.addActionListener(listener); 
      //bttnewA.addActionListener(listener); 
      bttdeleteA.addActionListener(listener); 
      bttvisualizzaA.addActionListener(listener);
  } 

  public void addActionListener(ActionListener listener) { 
      btteditA.addActionListener(listener); 
      //bttnewA.addActionListener(listener); 
      bttdeleteA.addActionListener(listener); 
      bttvisualizzaA.addActionListener(listener); 
  } 

  public String getState() { 
      return state; 
  } 
} 
class MultiButtonRendererFile extends DefaultTableCellRenderer { 

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MultiButtonPanelFile multiButtonPanel; 

  public MultiButtonRendererFile() { 
      multiButtonPanel = new MultiButtonPanelFile(); 
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
class MultiButtonEditorFile extends AbstractCellEditor implements TableCellEditor { 

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MultiButtonPanelFile multiButtonPanel; 

  public MultiButtonEditorFile() { 
      multiButtonPanel = new MultiButtonPanelFile(); 
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

class jTextAreaRendererFiles implements TableCellRenderer   
{   
  JScrollPane scrollPane;   
  JTextArea textArea;   
   
  public jTextAreaRendererFiles()   
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
      int acapo = DGen.contaACapo(textArea.getText());
      int lineafinale = lines+acapo;
      
      int height = fontHeight * lineafinale+10 + lines; //lines inserito per aumentare il margine se ci sono molte linee 
       
      table.setRowHeight(row, height);   
      return scrollPane;   
  }   
  
 
}  

class MyWatchQueueReader implements Runnable {
	  
    /** the watchService that is passed in from above */
    private WatchService myWatcher;
    private FileSincronizzati fileSS;
   
    public MyWatchQueueReader(WatchService myWatcher, FileSincronizzati fileS) {
        this.myWatcher = myWatcher;
       this.fileSS = fileS;
    }

    /**
     * In order to implement a file watcher, we loop forever 
     * ensuring requesting to take the next item from the file 
     * watchers queue.
     */
    @Override
    public void run() {
        try {
            // get the first event before looping
            WatchKey key = myWatcher.take();
            while(key != null) {
                // we have a polled event, now we traverse it and 
                // receive all the states from it
            	
            	WatchEvent<?> eventFine = null;
                for (WatchEvent event : key.pollEvents()) {
                    System.out.printf("Received "+ event.kind() + "/" + event.context() + "/" + event.count()+"\n" );
                    if (event.kind()==StandardWatchEventKinds.ENTRY_MODIFY) eventFine = event;
                }
                key.reset();
                System.out.printf("Received FIneale "+ eventFine.kind() + "/" + eventFine.context() + "/" + eventFine.count()+"\n" );
                if (eventFine.context().toString().equals(fileSS.getpNomeFileOriginario())) 
                	{System.out.println("BECCATO "+fileSS.getpNomeFileOriginario());
                		fileSS.setpPathOriginario(DGen.pathFileLocale(fileSS));
                		DGen.copyFileToUpload(fileSS);
                		File ff =  new File(DGen.pathFileLocale(fileSS));
                    	fileSS.setpMd5(DGen.calcolaMd5(DGen.pathFileLocale(fileSS)));
                    	fileSS.setpDataFile(ff.lastModified());
                    	fileSS.setpDimensioneFile(ff.length());
                    	fileSS.setpDataUploadFile(System.currentTimeMillis());
                    	
                    	DataSource.aggiornaFileID(fileSS);
					
                	
                	
                	}
                key = myWatcher.take();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping thread");
    }
}
