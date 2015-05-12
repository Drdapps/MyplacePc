

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Locale.Category;
import java.util.prefs.Preferences;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.Environment;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;

public class LayoutPC extends JFrame
{

    	private JButton btt0,btt1,btt2,btt3,btt4;
    	private JTable jtt;
    	private JPanel panel_top;
    	private JScrollPane panel_middle;
    	ListClient listaClienti, listaClientiTemp;
    	BoxLayout box_layout, box_layoutTemp;
    	static JTableReferentiAttivita window;

	    public LayoutPC()
	    {	 
	        super("");
	        
	       
	        DData.dimensioniframe = getPreferredSize();
	        System.out.println("LARG" + getPreferredSize().width+"/" +  getPreferredSize().height);
	       
	        ImageIcon icon = new ImageIcon ( getClass().getResource(DData.iconaLauncer)); 
	        setIconImage(icon.getImage());
	        
	        ClientiPanel TabTop = new ClientiPanel();
	        
	    	JTableReferentiAttivita TabBut = new JTableReferentiAttivita();
	    	
	        
	        // aggiungo i pannelli ad uno splitpane
	    	JSplitPane split_pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, TabTop, TabBut);
	        split_pane.setDividerLocation(DData.distanzaSplitClientiReferenti);
	        
	        
	        ListClient listaClienti = new ListClient();
	        JTableTodo jTableTodo = new JTableTodo();
	        JTableOfferte jTableOfferte = new JTableOfferte(true);
	        
	        ////77
	        JTabbedPane tabbedPane = new JTabbedPane();
	        

	        
	        tabbedPane.addTab(DString.jTabbedList, null, listaClienti,"");
	        tabbedPane.addTab(DString.jTabbedTodo, null, jTableTodo,"");
	        tabbedPane.addTab(DString.jTabbedOfferte, null, jTableOfferte,"");
	        
	        
	        ///777
	        
	       // JSplitPane split_pane_totale = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listaClienti, split_pane);
	        JSplitPane split_pane_totale = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, split_pane);
	        split_pane_totale.setDividerLocation(DData.distanzaElencoClientiPanelSinistra);
	        
	        add(split_pane_totale, BorderLayout.CENTER); 
	        split_pane_totale.setOneTouchExpandable(true);
	        setExtendedState(JFrame.MAXIMIZED_BOTH);
	        //add(listaClienti, BorderLayout.CENTER);
	        setTitle(DString.gtitoloApplicazione);
	        
	        
	        setJMenuBar(JMenuBarClienti());
	        
	        Runtime.getRuntime().addShutdownHook(new Thread()
	        {
	            @Override
	            public void run()
	            {	
	            	if (DLock.isLocked==false)
					{
	            		System.out.println("MAIN SHUTDOWN_ LOCKED VALUE" + DLock.isLocked);
	            		DGen.cancellaLocked();
					}
	              
	            }
	        });
	        
	    }
	   
	public static void inizializzaVariabili()
	{	
		Preferences prefs = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
		
		DData.pathSetting = DGen.getPreferencesString(DString.PPATHDB_DIR, "");
		
		mydbhelper.pathDataBase = DGen.getPreferencesString(DString.PPATHDB, "");
		 if (DGen.getPreferencesInt(DString.PUNITA, 0)==0)
		 { DData.GenUnitaDiMisuraMetri =true;
		 }else{
			 DData.GenUnitaDiMisuraMetri = false;
		 }
		
		DData.latitudinePartenza = DGen.getPreferencesDouble(DString.PLATI, 0);
		DData.longitudinePartenza = DGen.getPreferencesDouble(DString.PLATI, 0);
		DData.GenChiediCOnfermaPrimacancellare = DGen.getPreferencesBoolean(DString.PCONFERMADELETE, true);
		//prova scrittura settings.txt
		List <Proprieta> listaPro = new ArrayList<Proprieta>();
    	try {
    		listaPro = DataSource.getAllProprieta();
    		for(int i=0; i< listaPro.size();i++)
    		{
    			if (listaPro.get(i).getpIdPropieta()==5)
    			{
    				DLock.lockedDaDatabase = listaPro.get(i).getpBooProprieta();
    			}else
	    				{ if  (listaPro.get(i).getpIdPropieta()==6) {
	    						DData.versioneDatabase = listaPro.get(i).getpLongProprieta();
	    				}else{
	    						DData.hashProprieta.put(i+1,listaPro.get(i));
	    				}
    			}
    		}
    		
    		System.err.println("MAIN INIZIALIZZA VARIBILI/" + DLock.lockedDaDatabase );
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
	}
	public static boolean primaVolta()
	{boolean result = false;
		if (DGen.getPreferencesInt(DString.PPRIMAVOLTA, 0)==0)
		{
			result = true;
		}
		return result;
	}
	    
	public static String dropbBoxFolder()
	{String result ="";
	
	String appDataPath = System.getenv("APPDATA");
	String dbPath  = appDataPath + File.separator + "Dropbox" + File.separator+ "host.db";
	String line;
	 int i =0;
	try (
	    InputStream fis = new FileInputStream(dbPath);
	    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	    BufferedReader br = new BufferedReader(isr);
	   
	) {
		String tmp = "";
	    while ((line = br.readLine()) != null) {
	    	if (i==1) tmp = line;
	        i++;
	       
	    }
	 
	    byte[] decodedByteArray =  Base64.decodeBase64(tmp);
	    //Verify the decoded string
	    String resulttemp = new String(decodedByteArray);
	   
	    result = resulttemp;
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
				
					
					//	byte[] dbBase64Text = Convert.FromBase64String(lines[1]);
					//	string folderPath = System.Text.ASCIIEncoding.ASCII.GetString(dbBase64Text);
					//	Console.WriteLine(folderPath);
	
	if (DData.linguaScelta==1)
	{
		result = result + File.separator + "Applicazioni" +File.separator + "mycustomer" + File.separator + "clienti.db";
	}

	return result;
		
	}
	
	
    public static void main(String args[]) throws MalformedURLException
    {	
    	inizializzaVariabili();
    	System.err.println("DROPBOX FOLDER__" +dropbBoxFolder()  );
    
    	if (primaVolta())
    	
    	{	
    		JDialogLingua JCLingua = new JDialogLingua();
    		JCLingua.setModal(true);
    		JCLingua.setVisible(true);
    		
    		
    		if (JCLingua.resultDialogLingua==true)
    		{	DString.iniString();
    			DGen.showMessage(DString.gAttenzione, DString.msgSelezionaFilePrimaVolta);
    			scegliFile();
    			JDialogOpzioni JDo = new JDialogOpzioni();
    			JDo.setModal(true);
    			JDo.setVisible(true);
    		}else{
//    			finisci
    			System.exit(0);
    		}
    	}else{
    		
    		
    		
    		
    		
    		
    		Thread download = new Thread(){
    		    public void run(){
    		    	
    		    
    		    		System.out.println("LAYOUT PC CONTROLLO VERSIONE INIZIO");
    		    		Scanner scanner;
						try {
							scanner = new Scanner(new URL(DData.linkVerificaaggiornamento).openStream());
						
    		    		int i = 0;
    		    		while(scanner.hasNextInt()){
    		    		   i = scanner.nextInt();
    		    		}
    		    		System.out.println("LAYOUT PC CONTROLLO VERSIONE INIZIO/" + i + "/" + DData.releaseAttuale);
    		    	
    		    		if (i>DData.releaseAttuale)
    		    		{	
    		    			 int j = JOptionPane.showOptionDialog(null, DString.downloadInfo, DString.gAttenzione, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{DString.gAnnulla, DString.gDownload}, "default");
    		    			 if (j==1)
    		    			 {
    		    				 if(Desktop.isDesktopSupported())
    		  				   {	 if (DData.linkdownload.length()>0){
    		                         try {
    		                      	  
    		  						Desktop.getDesktop().browse(new URI(DData.linkdownload));
    		  								} catch (IOException e) {
    		  									// TODO Auto-generated catch block
    		  									e.printStackTrace();
    		  								} catch (URISyntaxException e) {
    		  									// TODO Auto-generated catch block
    		  									e.printStackTrace();
    		  								} 
    		                         }
    		  				   }
    		    			 }
    		   		      
    		    			System.err.println("Realese nuova" + i + "/" + DData.releaseAttuale+"/" + j);
    		    		}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
    		    		
    					 
    				
    		        
    		    }
    		};
    		download.start();//start the thread
    		
    		
    		DString.iniString();
    	
    		
    		
    		if (DLock.lockedDaDatabase) // SE NEL DATABASE il LOCKED = TRUE ALLARE DA IL CONTROLLO 
    		{			String pathLocked = DData.pathSetting + File.separator + DLock.FILELOCKED;
    					File locked = new File (pathLocked);
			    		if (locked.exists())
			    		{	
			    			long dataInMillisLocked = 0;
			    			try {
			    				String dataL = DGen.readSavedData(pathLocked);
			    				
			    				dataInMillisLocked = Long.parseLong(dataL);
							} catch (Exception e) {
								
							}
			    			DLock.myLocked=false;
			    			DLock.isLocked = true;
			    			
			    			
			    			NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
			            	Locale fmtLocale = Locale.getDefault(Category.FORMAT);
			            	NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
			            	DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT,  Locale.getDefault());
			            	
			            	 String dataS =  dateFormatter.format(new Date(dataInMillisLocked));
			    			String messaggio =  DString.msglockedFile1 + dataS  + DString.msglockedFile2;
			    			Object[] options1 = { DString.gOk, DString.lockForza};
			    			int result = JOptionPane.showOptionDialog(null, messaggio, DString.gAttenzione,
			    	                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
			    	                null, options1, null);
			    			
			    			if (result==JOptionPane.OK_OPTION)
			    			{
			    				DLock.myLocked=false;
			        			DLock.isLocked = true;
			    			}else{
			    				DLock.myLocked=true;
			        			DLock.isLocked = false;
			        			DGen.writeData(System.currentTimeMillis()+"", pathLocked);
			        			System.out.println("________MAIN _LOCKED__" + pathLocked);
			        			//trovare tutti i dgen.aggiornaFileSetting - controllore anche modifica database
			    			}
			    			//JOptionPane.showMessageDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION);
			    			System.out.println("___LOCKED____" + dataInMillisLocked);
			    		}else{
			    			
			    			DLock.myLocked=true;
			    			DLock.isLocked = false;
			    			
			    			DGen.writeData(System.currentTimeMillis()+"", pathLocked);
			    		}
    		}else{
    			DLock.myLocked=true;
    			DLock.isLocked = false;
    		}
    		
    		
    	}
    	
    
    	
    	//inizializza variabilir
    	//SPOSTATO PRIMA ERA IN BASSO MA PER CARICARE IL locked
		try {
			System.err.println("SBPOATH/"+ mydbhelper.pathDataBase + "__/___"+ DataSource.pragmaDatabaseVersione());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	if (DataSource.databaseConnesso(mydbhelper.pathDataBase))
    	{	
    		System.err.println("Layoutpc 34");
    		inizializzaVariabili();
	       LayoutPC window = new LayoutPC();
	       Dimension dimensionischermo = Toolkit.getDefaultToolkit().getScreenSize();
	       
	       window.setSize((int)(dimensionischermo.width), (int)(dimensionischermo.height*0.9));
	       
	        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        window.setVisible(true);
	        window.setLocationRelativeTo(null);
    	}else{
    		
    		DGen.showMessage(DString.gAttenzione, DString.msgDataBaseNonCollegato);
    			//DGen.controllaDirectoryHomedirAltimentiCrea(DString.gtitoloApplicazione);
    			
    		   
    		
    		if (scegliFile())
    		{	Preferences prefs1 = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
    		
    			prefs1.put(DString.PPATHDB, mydbhelper.pathDataBase);
    			inizializzaVariabili();
    			LayoutPC window = new LayoutPC();
    		       Dimension dimensionischermo = Toolkit.getDefaultToolkit().getScreenSize();
    		        window.setSize((int)(dimensionischermo.width), (int)(dimensionischermo.height*0.9));
    		       
    		        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		        window.setVisible(true);
    		        window.setLocationRelativeTo(null);
    		}
    				
    	}
    	//controllo sulla presenza degli ultima campi inseriti
    	if (DData.versioneDatabase!=mydbhelper.DATABASE_VERSION)
    	{
    		DGen.showMessage(DString.gAttenzione, DString.msgDataBaseNonAggiornato);
    	}
    	/*
    	if (DataSource.databaseAggiornato18(mydbhelper.pathDataBase))
    			{
    				System.out.println("DATABASE AGGIORNATO MAIN 417");
    			}else{
    				DGen.showMessage(DString.gAttenzione, DString.msgDataBaseNonAggiornato);
    			}
    	
		*/
    	
    	
      
      
    }
    
 // componenti dei menu
    private static JMenuBar menu_bar;
    private static JMenu file_menu;
    private static JMenu setting_menu;
    private static JMenu help_menu;
    private static JMenuItem mApri, mEsci, mPreferennza, mPosizione, mTipologia,mCampiUtente,mCancellaCredenzialiCalendario, mHelp, mAbout,mExporta;
  

    public static JMenuBar JMenuBarClienti()
    {
        
        
        //creo i menu;
        file_menu = new JMenu(DString.mFile);
        setting_menu = new JMenu(DString.mSetting);
        help_menu = new JMenu(DString.mAbout);
       
        
        //menu file
       mApri = new JMenuItem(DString.mApri, new ImageIcon(LayoutPC.class.getResource(DData.iconaMenuApri)));
       mApri.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if (scegliFile())
			{
				ListClient.refreshTabella();
			}
			
		}
       });
       mExporta = new JMenuItem(DString.mEsporta);
       mExporta.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		
			DEsportaExcel.taskEsportaClienti();
			
		}
       });
       mEsci = new JMenuItem(DString.mEsci, new ImageIcon(LayoutPC.class.getResource(DData.iconaMenuEsci)));
       mEsci.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);
			}
		});
       file_menu.add(mApri);
       file_menu.add(mExporta);
       file_menu.add(mEsci);
       
        //menu preferenze
       mPreferennza = new JMenuItem(DString.mSetting); 
       mPreferennza.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JDialogOpzioni jOpz = new JDialogOpzioni();
			jOpz.setModal(true);
			jOpz.setVisible(true);
			inizializzaVariabili();
			
			
			
		}
	});
       mPosizione = new JMenuItem(DString.mCampbiaPosizioneRiferimento);
       mPosizione.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			JDialogNuovaPosizione jDPos = new JDialogNuovaPosizione(DGen.getPreferencesDouble(DString.PLATI, 0L),DGen.getPreferencesDouble(DString.PLONGI, 0L));
			jDPos.setModal(true);
			jDPos.setVisible(true);
			
			
		}
	});
     	mTipologia = new JMenuItem(DString.mTipologia);
     	mTipologia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jDialogTipologiaNew jd = new jDialogTipologiaNew();
				jd.setModal(true);
				jd.setVisible(true);
				
			}
		});
     	
     	mCampiUtente = new JMenuItem(DString.mCampiUtenti);
     	mCampiUtente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialogCampiUtente jd = new JDialogCampiUtente();
				jd.setModal(true);
				jd.setVisible(true);
				
			}
		});
     	mCancellaCredenzialiCalendario = new JMenuItem(DString.mCancellaCredenziali);
     	mCancellaCredenzialiCalendario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DGen.cancellaCredenzialiCalendario();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
		});
       setting_menu.add(mPreferennza);
       setting_menu.add(mPosizione);
       setting_menu.add(mTipologia);
       setting_menu.add(mCampiUtente);
       setting_menu.add(mCancellaCredenzialiCalendario);
       
        //menu help
       mHelp = new JMenuItem(DString.mHelp); 
       mAbout = new JMenuItem(DString.mAbout); 
       mAbout.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			About about = new About();
	        	
	        	JOptionPane.showMessageDialog(null, about, DString.gtitoloApplicazione, JOptionPane.PLAIN_MESSAGE);

			
		}
	});
      // help_menu.add(mHelp);
       help_menu.add(mAbout); 
       
       
       //creo la  menubar
       menu_bar = new JMenuBar(); // creo la menu bar
        // aggiungo alla menu bar i menu
        menu_bar.add(file_menu);
        menu_bar.add(setting_menu);
        menu_bar.add(help_menu);
        
        return menu_bar;
        // aggiungo al JFrame la barra dei menu
    }    
    
    public static boolean scegliFile()
    {	boolean result= false;
    	final JFileChooser fc = new JFileChooser();
    	FileNameExtensionFilter filterDB = new FileNameExtensionFilter("SQLite db Only", "db");
    	fc.addChoosableFileFilter(filterDB);
    	fc.setFileFilter(filterDB);
    	String dropboxFolder = dropbBoxFolder();
    	System.out.println("LAYOUT PC SCEGLI FILE DB__-"+ dropboxFolder);
    	if (dropboxFolder.length()>0) fc.setCurrentDirectory(new File(dropboxFolder));
		 int returnVal = fc.showOpenDialog(null);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	          
	          DGen.savePreferencesString(DString.PPATHDB, mydbhelper.prefissoPathDatabase+file.getPath());
	           DGen.savePreferencesString(DString.PPATHDB_DIR, file.getParent());
	           mydbhelper.pathDataBase = mydbhelper.prefissoPathDatabase+file.getPath();
	         
	        
	           result = true;
	         
	        } else {
	            
	        }
    	
    	
		return result;
    	
    }
    
}
