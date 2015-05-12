
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Category;

import javax.jws.WebParam.Mode;
import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class ListClient extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton bttDelete, bttCancellaTesto; //,bttRicerca
	
	public static JTextField txtRicerca;
	
	public static JComboBox JcomboElencoTipologie, JcomboOrdinamento , JcomboMesi;
	public static JTable jtt;
	public static JPanel UILpanel_top;
	public static JScrollPane UILpanel_middle;
	public static JPanel elencoClienti;
	public static boolean nonChiamareDialogSelezionaTipologia = false;
	
	public static int rowSelected;
	
	public int numeroChiamateCambiaValore=0; //su setvalue la funzione viene chiamato 2 volte la prima sul vecchiio valore
	public static boolean resultOK = false; //volore selezione multipla
	
	private BorderLayout border_layout;
	static Object[][] dataDataBase;
	
	public boolean colonnaVistata = false;
	
    public ListClient()
    {	//
    	 border_layout = new BorderLayout(); // creo il layout manager e lo assegno al container
         setLayout(border_layout);
         final String columns_headers[] = {DString.listSelect, DString.listIDcliente, DString.unitaDiMisura + " " + DString.listDistanza,
       	DString.listNome, DString.listTipologia, DString.listData, " "};
       //inizializo tutti i check della lista a false
         DataSource.selezionoTutti(false);
         
         UILpanel_top = new JPanel(new FlowLayout(FlowLayout.LEFT));

    	bttDelete = new JButton(new ImageIcon(getClass().getResource(DData.iconaCancella)));
    	bttDelete.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttDelete.setToolTipText(DString.ttCancellaTutto);
    	bttDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Task task = new Task();
				   // task.addPropertyChangeListener(this);
				    task.execute();
				//DGen.cancellaSelezionati();
				
			}
		});
        txtRicerca = new JTextField("", 8);
        txtRicerca.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				refreshTabella();
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				refreshTabella();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        /*
        bttRicerca = new JButton(new ImageIcon(getClass().getResource(DData.iconaCerca)));
        bttRicerca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				refreshTabella();
		       
			}
		});
       */
        bttCancellaTesto = new JButton(new ImageIcon(getClass().getResource(DData.iconaCancellaTesto)));
        bttCancellaTesto.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
        bttCancellaTesto.setToolTipText(DString.ttCancellaRicerca);
        bttCancellaTesto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				txtRicerca.setText("");
				refreshTabella();
				
			}
		});
        JcomboOrdinamento = new JComboBox(DString.jCListSorting);
        JcomboOrdinamento.setPrototypeDisplayValue("Data attività Descres"); //imposta la larghezza massima
        JcomboOrdinamento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshTabella();
				
			}
		});
        JcomboElencoTipologie = new JComboBox(DGen.itemComboTipologieCompresaTutte());
        JcomboElencoTipologie.setMaximumRowCount(16);
        JcomboElencoTipologie.setPrototypeDisplayValue("Data attività Descresc"); //imposta la larghezza massima
        JcomboElencoTipologie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshTabella();
		      
				
			}
		});
        JcomboElencoTipologie.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				nonChiamareDialogSelezionaTipologia = true;
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				  
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				 
			}
		});
       List<String> elencoMesiLista =DataSource.raggruppaMesi() ;
       int len = elencoMesiLista.size();
       String[] elencoMesi = new String[len];
       for (int i=0;i<len;i++) elencoMesi[i]=elencoMesiLista.get(i);
       JcomboMesi = new JComboBox(elencoMesi);
      
       JcomboMesi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshTabella();
		       
				
			}
		});
       
        UILpanel_top.add(bttDelete);
        UILpanel_top.add(JcomboOrdinamento);
        UILpanel_top.add(txtRicerca);
        UILpanel_top.add(bttCancellaTesto);
        
        //UILpanel_top.add(bttRicerca);
        UILpanel_top.add(JcomboMesi);
        UILpanel_top.add(JcomboElencoTipologie);
      
       
       
        //tabella mancono gli header
   	
        long idtipologia = -1;
        String colonnaOrdinamento=mydbhelper.COLUMN_NAME;
        String tipoOrdinamento=mydbhelper.CRESCENTE;
        //
        posizioneGps poiTemp = new posizioneGps();
		poiTemp.setpPosizioneGPSsenzaID(0L, 0L, "", 0, 0, 0, "", "", "", 0, 0, null, "", "", "", "", "", "", 0, 160000, -100000,"","","","",0,-1,-1,"","");
		//
     
        dataDataBase = aggiornaDatiTabella(txtRicerca.getText(),idtipologia,colonnaOrdinamento,tipoOrdinamento,0,0,1);
      
        ClientiPanel.aggiornaCliente((long) dataDataBase[0][1]);
        jtt = new JTable(new TabellaList(columns_headers, dataDataBase));
        jtt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				
				
				if (arg0.getValueIsAdjusting()) //evita di chiamre l'evento due volta quando perde selezione e quando acquisisce con questo valore rimane l'ultimo se metto !arg0 rimane il primo
				{ 
					
		       
							int viewRow = jtt.getSelectedRow();
							int column = jtt.getSelectedColumn();
							try {
								
								DData.idClienteSelezionato = (long) jtt.getValueAt(jtt.getSelectedRow(), 1);
							} catch (Exception e) {
								//DData.idClienteSelezionato = -1;
							}
				
							
								if (DData.idClienteSelezionato>=0) 
								 {	 if (jtt.getSelectedColumn()>0)
										 {
									 ClientiPanel.aggiornaCliente( DData.idClienteSelezionato);
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
        
        
        aggiornaDimensioniColonne();
        
      // ((JTable) jtt).setAutoCreateRowSorter(true); // permette il sorter sulle colonne tolto perche se cancello da errore
        
        Dimension dim = new Dimension(15,1);
        ((JTable) jtt).setIntercellSpacing(new Dimension(dim));
        jtt.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        jtt.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));
        jtt.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = jtt.columnAtPoint(e.getPoint());
               /* String name = jtt.getColumnName(col);
                System.out.println("Column index selected " + col + " " + name);*/

        		long idTipo = Long.parseLong(DData.HashIDComboToIDTipologia.get(JcomboElencoTipologie.getSelectedIndex()+""));
        		String ricerca  =  txtRicerca.getText();
        		 
                if (col==0)
                {	if (colonnaVistata==false)
                	{
                		colonnaVistata = true;
                		DataSource.selezionoTuttiMostrati(true,ricerca ,idTipo);
                		refreshTabella();
                	}else{
                		DataSource.selezionoTuttiMostrati(false,ricerca ,idTipo);
                		refreshTabella();
                		colonnaVistata = false;
                	}
                }
            }
        });
        //inserisco la tabella in una ScrollPanel
        //aggiunge pop up menu
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem setPosizione = new JMenuItem(DString.optionMenu);
        setPosizione.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	
         	   long idSelezionato = (long) jtt.getValueAt(rowSelected, 1);
         	   posizioneGps cliente = DataSource.getClientID(idSelezionato);
         	
         	    JDialogNuovaPosizione jDPos = new JDialogNuovaPosizione(cliente.getpLatitudine(),cliente.getpLongitudine());
                jDPos.setModal(true);
                jDPos.setVisible(true);
            }
        });
        popupMenu.add(setPosizione);
       // tableAttivita.setComponentPopupMenu(popupMenu);
        jtt.addMouseListener( new MouseAdapter()
        {
            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    JTable source = (JTable)e.getSource();
                   rowSelected = source.rowAtPoint( e.getPoint() );
                    int column = source.columnAtPoint( e.getPoint() );

                    if (! source.isRowSelected(rowSelected))
                        source.changeSelection(rowSelected, column, false, false);
                    
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        
        UILpanel_middle = new JScrollPane(jtt);
       
        JPanel panelListaTotale = new JPanel(new BorderLayout());
        panelListaTotale.add(UILpanel_top,BorderLayout.NORTH);
        panelListaTotale.add(UILpanel_middle,BorderLayout.CENTER);
        add(panelListaTotale, BorderLayout.CENTER); 
       
      
    }
    public static String restituisciColonnaOrdinamento(int riga)
    {	String result=mydbhelper.COLUMN_NAME;
    	switch (riga) {
		case 0: result=mydbhelper.COLUMN_NAME;
			break;
		case 1: result=mydbhelper.COLUMN_NAME;
			break;
		case 2:result=mydbhelper.COLUMN_DATA_IN_MILLIS;
			break;
		case 3:result=mydbhelper.COLUMN_DATA_IN_MILLIS;
			break;
		case 4:result=mydbhelper.COLUMN_DISTANZA;
			break;
		case 5:result=mydbhelper.COLUMN_DISTANZA;
			break;
		case 6:result=mydbhelper.COLUMN_DATAATTIVITA;
		break;
		case 7:result=mydbhelper.COLUMN_DATAATTIVITA;
		break;
		default:
			break;
		}
    	return result;
    	
    }
     public void aggiornaDimensioniColonne()
    {
    	DGen.fixWidth(jtt, 0, 40);
    	DGen.fixWidth(jtt, 1, 50);
    	DGen.alignamentRight(jtt, 1);
    	DGen.fixWidth(jtt, 2, 60);
    	DGen.alignamentRight(jtt, 2);
    	DGen.fixWidth(jtt, 3, 180);
    	DGen.fixWidth(jtt, 4, 120);
    	DGen.fixWidth(jtt, 5, 80);
    	//DGen.alignamentRight(jtt, 5);
    	DGen.fixWidth(jtt, 6, 30);
    }
    
    public static void refreshTabella ()
    {	
    	
    	
    	
    	String colonna = restituisciColonnaOrdinamento(JcomboOrdinamento.getSelectedIndex());
		String ordinamento = mydbhelper.CRESCENTE;
		if (!DGen.numeroPari(JcomboOrdinamento.getSelectedIndex())) ordinamento = mydbhelper.DECRESCENTE;
		long mesi = DData.HashIDComboMesi.get(JcomboMesi.getSelectedIndex());
		long idTipo = Long.parseLong(DData.HashIDComboToIDTipologia.get(JcomboElencoTipologie.getSelectedIndex()+""));
		switch ((int)idTipo) {
		
		case -2: //seleziona tipologie
			if (nonChiamareDialogSelezionaTipologia==true)
			{
					resultOK = false;
					jDialogTipologiaSelect jd = new jDialogTipologiaSelect();
					jd.setModal(true);
					jd.setVisible(true);
					
					if (resultOK ==true)
					{
						TabellaList model1 = (TabellaList) jtt.getModel(); 
						model1.dataTabella = aggiornaDatiTabella(txtRicerca.getText(),idTipo,colonna,ordinamento,DData.latitudinePartenza,DData.longitudinePartenza, mesi );
						model1.fireTableDataChanged();
						
					}
					nonChiamareDialogSelezionaTipologia = false;
			}else{
				TabellaList model1 = (TabellaList) jtt.getModel(); 
				model1.dataTabella = aggiornaDatiTabella(txtRicerca.getText(),idTipo,colonna,ordinamento,DData.latitudinePartenza,DData.longitudinePartenza, mesi );
				model1.fireTableDataChanged();
				
			}
					break;

		default:
			//
			TabellaList model = (TabellaList) jtt.getModel(); 
			
			model.dataTabella = aggiornaDatiTabella(txtRicerca.getText(),idTipo,colonna,ordinamento,DData.latitudinePartenza,DData.longitudinePartenza, mesi );
	    	model.fireTableDataChanged();
		break;
		}
		
		
    }
    
    public static Object[][] aggiornaDatiTabella(String ricerca, Long tipologia, String colonnaOrdinamento, String ordinamento, double lati, double longi, long mesi)
    {	List <posizioneGps> listaClienti = new ArrayList<posizioneGps>();
    	
    	try {
    		
    		listaClienti = DataSource.getAllClientiRicerca(ricerca, tipologia, colonnaOrdinamento, ordinamento, lati, longi,mesi);
    		//listaClienti = DataSource.getAllClienti(ricerca, 0, 0, 0); //ricerca tipologia lati e longi
		} catch (Exception e) {
			
			// TODO: handle exception
		}
    	
    	
    	
    	//provbabilmente else non serve a segut cambiamento poistiva controolo database in ingresso
    	if (listaClienti.size()>0)
    	{int i=0;
	    	Object[][] dataDataBase = new Object[listaClienti.size()][7];
	    	NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
	    	Locale fmtLocale = Locale.getDefault(Category.FORMAT);
	    	NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
	    	formatter.setMaximumFractionDigits(1);
	    	formatter.setMinimumFractionDigits(1);
	    	double distanza = 0;
	    	
	    	//foramtta con data corta locale
	    	 DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, getDefaultLocale());
	    	
	    	
	    	for(i=0;i<listaClienti.size();i++)
			    	{	
	    				
	    				
	    				distanza = Math.sqrt(listaClienti.get(i).getpDistanza())/1000;
	    				
	    				
	    				dataDataBase[i][0]= listaClienti.get(i).getpSettato();
	    				dataDataBase[i][1]= listaClienti.get(i).getpId();
	    				
	    				if (DData.GenUnitaDiMisuraMetri=true)
	    				{	
	    					dataDataBase[i][2]= formatter.format(distanza);
	    				}else{
	    					dataDataBase[i][2]= formatter.format(distanza * 0.62137);
	    					
	    				}
	    				
	    				dataDataBase[i][3]= listaClienti.get(i).getpNome();
	    				dataDataBase[i][4]= DData.HashIDTipologiaToDescrizione.get(listaClienti.get(i).getpTipo()+""); //listaClienti.get(i).getpTipo(); //mettere il nome non tipologia
	    				
	    				//dataDataBase[i][5]= dateFormatter.format(new Date(listaClienti.get(i).getpDataInMillis())); 
	    				if (listaClienti.get(i).getpDataAttività()>0)
	    				{	dataDataBase[i][5]= dateFormatter.format(new Date(listaClienti.get(i).getpDataAttività()));
	    				}else{
	    					dataDataBase[i][5] = "";
	    				}
	    				dataDataBase[i][6]= "";
	    			}
	    	if (DData.idClienteSelezionato<0) DData.idClienteSelezionato = listaClienti.get(0).getpId();
	    	System.out.println("LIST CLIENTE AGGIORNA INIZIALE IDSELEZIONE/" +  DData.idClienteSelezionato + "/" +listaClienti.get(0).getpId());
	    	return dataDataBase;
    		
    		
    	}else{
    		NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
	    	Locale fmtLocale = Locale.getDefault(Category.FORMAT);
	    	NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
	    	formatter.setMaximumFractionDigits(1);
	    	formatter.setMinimumFractionDigits(1);
	    	double distanza = 0;
	    	
	    	//foramtta con data corta locale
	    	 DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, getDefaultLocale());
	    	 Object[][] dataDataBase = new Object[1][7];
			
			
			dataDataBase[0][0]= false;
			dataDataBase[0][1]= -1L;
			
			if (DData.GenUnitaDiMisuraMetri=true)
			{	
				dataDataBase[0][2]= formatter.format(distanza);
			}else{
				dataDataBase[0][2]= formatter.format(distanza * 0.62137);
				
			}
			
			dataDataBase[0][3]= " ";
			
			dataDataBase[0][4]= "";
			
			dataDataBase[0][5]= dateFormatter.format(new Date(0)); 
			
			dataDataBase[0][6]= "";
			
			return dataDataBase;
    	}
    	
    	
    	
    	
    }
    
    
}
class Task extends SwingWorker<Void, Void> {
    /*
     * Main task. Executed in background thread.
     */
 jDialogProgress jdp;
    @Override
    public Void doInBackground() {
    
    	 SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                	 jdp = new jDialogProgress();
                	 jdp.setResizable(false);
                	 jdp.pack();
                	 jdp.setLocationRelativeTo(null);
     		    	jdp.setModal(true);
     		    	jdp.setVisible(true);
     		    	
                  
                }
            });
    	 if (DLock.isLocked==false)
			{
    		 	DGen.cancellaSelezionati();
			
			}else{
				DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
			}
    	
    	
    	
      return null;
    }

    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done() {
    	
    	jdp.setVisible(false);
    }
  }



class TabellaList extends AbstractTableModel implements TableModelListener{

	
	public String columns_nome[];
	public  Object[][] dataTabella;

     public TabellaList(String columns_headers[], Object[][] dataTabella) {
        this.columns_nome = columns_headers;
        this.dataTabella = dataTabella;
       
    	 
     }
     @Override
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
     public int getColumnCount() {
         return columns_nome.length;
     }

     @Override
     public String getColumnName(int col) {
         return columns_nome[col];
     }

     @Override
     public int getRowCount() {
         return dataTabella.length;
     }

     @Override
     public Object getValueAt(int row, int col) {
         
         return dataTabella[row][col];
     }
     //si mettono i numeri delle colonna da potermodificare
     public boolean isCellEditable(int rowIndex, int columnIndex)
     {
     	switch (columnIndex) {
         case 0:
         	return true;
         case 6:
         	return true;
         default:
             return false;
     	}
         //return columnIndex == 0;
     }
     public void setValueAt(Object aValue, int rowIndex, int columnIndex)
     {
         if (columnIndex == 0)
         {
        	
         	dataTabella[rowIndex][columnIndex] = aValue;
         
         	
         	DataSource.settaPerId(Boolean.parseBoolean(aValue.toString()), (long) getValueAt(rowIndex, 1));
             fireTableCellUpdated(rowIndex, columnIndex);
             
         }
        
     }
     

     // consentiamo di chiamare il corretto cell renderer
     public Class<?> getColumnClass(int columnIndex)
     {	
         return getValueAt(0, columnIndex).getClass();
     }
	
}
//per aggiungere pulsante a  tabella
class ButtonRenderer extends JButton implements TableCellRenderer {

	  public ButtonRenderer() {
	    setOpaque(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else {
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    //setText("oppo");
	    setIcon(new ImageIcon(getClass().getResource(DData.iconaCancella)));
	  
	   //setIcon(new ImageIcon(ListClient.class.getResource("/icon/delete-icon.png")));
	    //setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}

	/**
	 * @version 1.0 11/09/98
	 */

	class ButtonEditor extends DefaultCellEditor {
	  protected JButton button;

	  private String label = "PREMUTO";

	  private boolean isPushed;
	  
	  private Object editorValue;

	  public ButtonEditor(JCheckBox checkBox) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	     

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				fireEditingStopped();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			
		}
	    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else {
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    //label = (value == null) ? "" : value.toString();
	    label = value+"";
	   
	    button.setText(label);
	    isPushed = true;
	    this.editorValue = value;
	  
	    return button;
	  }
	  
	  public void removeSelectedRows(JTable table, int row){
		   TabellaList model = (TabellaList) table.getModel();
		  
		   model.removeRow(row);
		   
		 
		}
	  
	  public Object getCellEditorValue() {
		  
		  
	    if (isPushed) {
	      // 
	      // 
	    	JTable jt = (JTable) button.getParent();
	    
	    	int rigaSelezionata = jt.getSelectedRow();
	    	
	    	long  idSelezionato = (long) jt.getValueAt(jt.getSelectedRow(), 1);
	    	if (DData.GenChiediCOnfermaPrimacancellare==true)
	    	{   	if (DGen.showConfirm(DString.gAttenzione, "1 " + DString.msgcancellazione, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)  
			    	{	DData.idClienteSelezionato = -1;
	    				
	    				if (DLock.isLocked==false)
						{	removeSelectedRows(jt, rigaSelezionata);
	    					DataSource.cancellaClienteTotale(idSelezionato);
				    		ClientiPanel.aggiornaCliente(-1);
						
						}else{
							DGen.showMessage(DString.gAttenzione, DString.lockDatabaseSoloLettura);
						}
			    		
			    		
			    		
			    		
			    	}
	    	}else{
	    		removeSelectedRows(jt, rigaSelezionata);
	    		DataSource.cancellaClienteTotale(idSelezionato);
	    		ClientiPanel.aggiornaCliente(-1);
	    		
	    	}
	    	//mettere cancella item idselezionato
	    	//JOptionPane.showMessageDialog(button, label + ": Ouch!"+ rigaSelezionata + "/" + idSelezionato);
	    	
	    	
	    	
	    	
	    
	    }
	    isPushed = false;
	    return new String(label);
	    
		  
	  }
	 

	  public boolean stopCellEditing() {
	    isPushed = false;
	    
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	 
	  
	  
	  
	 
	}
	
	//esempio dati per jtable
	 /*
    Date dataa = new Date(System.currentTimeMillis());
    Date datan = new Date(System.currentTimeMillis()+100000000);
    Date datao = new Date(System.currentTimeMillis()-100000000);
	 	
    Object data1[] = {false, 3, 2.4, "PIppo1","Cliente",dataa,""};
    Object data2[] = {false, 2,12.4, "PIppo2","Cliente", datan,""};
    Object data3[] = {false, 13, 6.3, "PIppo3","Cliente", datao,""};
    Object data4[] = {false,101,112.4, "PIppo4","Prospect",datan,""};
    Object[][] dataTabella =
        {
            data1,data2,data3,data4,data1,data1,data1
            
        };
       */