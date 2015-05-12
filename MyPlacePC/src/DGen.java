
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;









public class DGen {
	public static boolean aggiungiAcalendario = false;
	
	 public static int contaACapo(String text)
     
     {		int count =0;
     		//carateri per testo a capo
  
     	 
           Pattern pattern = Pattern.compile("\n");
           Matcher  matcher = pattern.matcher(text);

          
           while (matcher.find())count++;
           
     				
           return count;

     }
	
	public static String annoMese (long dataInMillis)
	{String result="";
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(dataInMillis);
		int anno = c.get(Calendar.YEAR);
		int mese = c.get(Calendar.MONTH)+1;  //i mesi vanno da 0 a 11
		String meseString = mese+"";
		if (meseString.length()==1) meseString="0"+meseString;
		result= anno+"_"+ meseString;
				
	return result;
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
		  NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
		  double myNumber = nf.parse(str).doubleValue();
	   
	  }  
	  catch(NumberFormatException | ParseException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static long numeroLocale(String str)  
	{   long myNumber =0;
	  try  
	  {  
		  NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
		  myNumber = nf.parse(str).longValue();
	   
	  }  
	  catch(NumberFormatException | ParseException nfe)  
	  {  
	  ;  
	  }  
	  return myNumber;  
	}
	   public static String DoubleToString (double dou,int dec){
			
			return String.format("%."+dec+"f", dou);
			
		}
	
	public static boolean numeroPari(int x)
	{	boolean result = true;
		if ( (x & 1) == 0 ) { result = true; } else { result = false;}
		return result;
	}
	
	
	public static void hideColumn(JTable table, int  index) {
	    TableColumn column = table.getColumnModel().getColumn(index);
	    column.setMinWidth(0);
	    column.setMaxWidth(0);
	  
	    column.setPreferredWidth(0);
	    
	}

	public static void deffixWidth(final JTable table, final int columnIndex, final int width) {
	    TableColumn column = table.getColumnModel().getColumn(columnIndex);
	    
	   column.setMinWidth(width);
	    column.setMaxWidth(width);
	    column.setPreferredWidth(width);
	}
	public static void fixWidth(final JTable table, final int columnIndex, final int width) {
	    TableColumn column = table.getColumnModel().getColumn(columnIndex);
	    
	    //column.setMinWidth(width);
	    //column.setMaxWidth(width);
	    column.setPreferredWidth(width);
	}
	public static void alignamentRight (final JTable table, final int columnIndex)
	{
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
		table.getColumnModel().getColumn(columnIndex).setCellRenderer( rightRenderer );
	}
	
	public static void showMessage(String title, String message)
	{
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
	}
	
	 public static  int showConfirm(String Title, String message, int configurazione)
	    {
	        // finestra di dialogo di tipo confirm
	        return JOptionPane.showConfirmDialog(null, message, Title,
	                configurazione, JOptionPane.WARNING_MESSAGE);
	       // configurazione è 
	        // di sotto i volore che possono essere restiutiti con JOptionPane.YES_NO_CANCEL_OPTION
	        /*if (n == JOptionPane.YES_OPTION)  JOptionPane.OK_CANCEL_OPTION , JOptionPane.YES_NO_CANCEL_OPTION
	            JOptionPane.showMessageDialog(null, "Scelto il pulsante YES");
	        else if (n == JOptionPane.NO_OPTION)
	            JOptionPane.showMessageDialog(null, "Scelto il pulsante NO");
	        else if (n == JOptionPane.CANCEL_OPTION)
	            JOptionPane.showMessageDialog(null, "Scelto il pulsante CANCEL");
	        else if (n == JOptionPane.CLOSED_OPTION)
	            JOptionPane.showMessageDialog(null, "Chiusa la finestra di dialogo");*/
	       
	    }
	 public static String[] itemComboTipologieCompresaTutte()
	    {		
				List <Tipologia> listaTipologia = new ArrayList<Tipologia>();
				listaTipologia = DataSource.getAlltipologia();
				String elencoDescrizioni[] = new String[(listaTipologia.size()+2)];
				int i = 0;
				elencoDescrizioni[0] = DString.tipTutteTipologie;
				DData.HashIDComboToIDTipologia.put(0+"",-1+"");
				elencoDescrizioni[1] = DString.tipSeleziona;
				DData.HashIDComboToIDTipologia.put(1+"",-2+"");
				for(i=0;i<listaTipologia.size();i++)
					
				{	
					DData.HashIDTipologiaToDescrizione.put(""+listaTipologia.get(i).getpIdTipologia(), listaTipologia.get(i).getpDescrizioneTipologia());
					DData.HashIDComboToIDTipologia.put(i+2+"",listaTipologia.get(i).getpIdTipologia()+"");
					elencoDescrizioni[i+2] = listaTipologia.get(i).getpDescrizioneTipologia();
					
				}
				return elencoDescrizioni;

	    }
	 public static String[] itemComboTipologieEsclusaTutte()
	    {		
				List <Tipologia> listaTipologia = new ArrayList<Tipologia>();
				listaTipologia = DataSource.getAlltipologia();
				
				String elencoDescrizioni[] = new String[(listaTipologia.size())];
				int i = 0;
				
				
				for(i=0;i<listaTipologia.size();i++)
					
				{	
					
					DData.HashIDTipologiaToIDComboSenza.put(""+listaTipologia.get(i).getpIdTipologia(), i+"");
					DData.HashIDComboToIDTipologiaSenza.put(""+i, ""+listaTipologia.get(i).getpIdTipologia());
					elencoDescrizioni[i] = listaTipologia.get(i).getpDescrizioneTipologia();
					
				}
				return elencoDescrizioni;

	    }
	 
	 public static void cancellaSelezionati(){
			//-----------------------------------
			List <posizioneGps> listaClientiSelezionati = new ArrayList<posizioneGps>();
			listaClientiSelezionati = DataSource.getAllClientiSelezionati();
			if (listaClientiSelezionati.size()==0) 
			{	//JOptionPane.showMessageDialog(button, label + ": Ouch!"+ rigaSelezionata + "/" + idSelezionato);
				//DGen.showConfirm(DString.gAttenzione, DString.msgNessunSelezionato, JOptionPane.OK_CANCEL_OPTION);
				DGen.showMessage(DString.gAttenzione, DString.msgNessunSelezionato);
			}else{	
						if (DGen.showConfirm(DString.gAttenzione, listaClientiSelezionati.size()+" " + DString.msgcancellazione, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)  
				    	{	int i = 0;
				    	
							for (i=0;i<listaClientiSelezionati.size();i++)
								{	DataSource.cancellaClienteTotale(listaClientiSelezionati.get(i).getpId());
								//se elimio il cliente visualizzato in clienti panel lo elimino 	
								if (DData.idClienteSelezionato==listaClientiSelezionati.get(i).getpId()) 
								 	{
								 		ClientiPanel.aggiornaCliente(-1);
								 	}
								}
							
								ListClient.refreshTabella();
				    	
				    	}
			}
			//-----------------------------------
		}
	 
	 public static Referenti recuperateReferenteDaMaschera(long idrefe)
	    {Referenti result = new Referenti();
	    	if (idrefe>=0)
	    	{	try {
					result=DataSource.getReferente(idrefe);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	
			result.setpNomeRefente(ReferenteAddPanel.txtNome.getText());
			result.setpCognomeReferente(ReferenteAddPanel.txtCognome.getText());
			result.setpFunzioneReferente(ReferenteAddPanel.txtFunzione.getText());
			result.setpTelReferente(ReferenteAddPanel.txtTel.getText());
			result.setpFaxReferente(ReferenteAddPanel.txtFax.getText());
			
			result.setpCelReferentea(ReferenteAddPanel.txtCell.getText());
			
			result.setpMailReferentea(ReferenteAddPanel.txtEmail.getText());
			
			result.setpMemoReferente(ReferenteAddPanel.txtNote.getText());
			
	    return result;
	    }
	 public static Offerta recuperateOffertaDaMaschera(long idoffe, long idCliente)
	    {Offerta result = new Offerta();
	    if (idoffe>=0)
			try {
				result=DataSource.getOfferta(idoffe);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//result.setpDataAttivita(Long.parseLong(AttivitaAddPanel.txtData.getText()));
	    	System.out.println("RECUEPRA OFFERTA "+ idoffe);
	    	Date data = OffertaAddPanel.jdp.getDate();
	    	result.setpIdClienteOfferta(idCliente);
	    	result.setpDataChiusura(data.getTime());
	    	result.setpDescrizioneOfferta(OffertaAddPanel.txtDecrizione.getText());
	    	double importo = 0;
	    	double dimporto = 0;
	    	String importoStringa = OffertaAddPanel.txtImporto.getText();
	    	importoStringa = importoStringa.replace(',', '.');
	    			try {
	    				dimporto = Double.valueOf(importoStringa);
	    				
	    		
			} catch (Exception e) {
				importo = 0;
				System.err.println("recupera offerta da masehra importo/"+ e.getMessage());
			}
	    			System.out.println("RECUEPRA OFFERTA 1 "+ data.getTime()+"/" + OffertaAddPanel.txtDecrizione.getText()); 	
	    	result.setpImportoOfferta(dimporto);
	    	result.setpNomeCliente(OffertaAddPanel.lblNomeCliente.getText());
	    	result.setpNota(OffertaAddPanel.txtNote.getText());
	    	long perc = 0;
	    	try {
	    		perc = Long.parseLong(OffertaAddPanel.spnPercentuale.getSelectedItem().toString());
			} catch (Exception e) {
				System.err.println("recupera offerta da masehra perce/"+ e.getMessage());
			}
			result.setpAcliente(OffertaAddPanel.chkACliente.isSelected());
			result.setpPercentuale(perc);
			
			System.out.println("DGGEN RECUPERA OFFERTA DA MASCHERA/" + OffertaAddPanel.chkACliente.isSelected() + "/" + result.getpAcliente() );
	    return result;
	    }
	 
	 
	 public static Attivita recuperateAttivitaDaMaschera(long idatti)
	    {Attivita result = new Attivita();
	    if (idatti>=0)
			try {
				result=DataSource.getAttivita(idatti);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//result.setpDataAttivita(Long.parseLong(AttivitaAddPanel.txtData.getText()));
	    	Date data = AttivitaAddPanel.jdp.getDate();
	    	
	    	result.setpDataAttivita(data.getTime());
			result.setpDescrizioneAttivita(AttivitaAddPanel.txtNote.getText());
			
			if (AttivitaAddPanel.jchk.isSelected())
			{
				result.setpFattaAttivita(1);
			}else{
				result.setpFattaAttivita(0);
			}
			
			result.setpTipoAttivita(AttivitaAddPanel.jComboTipoAttivita.getSelectedIndex());
			
			aggiungiAcalendario = AttivitaAddPanel.jchkAddAllarm.isSelected();
			
	    
	    return result;
	    }
	 
	 	public static FileSincronizzati recuperaFileDaMascehra(long idFile)
	 	{	FileSincronizzati result =  new FileSincronizzati();
	 		if (idFile>0)
	 		{
	 			try {
					result=DataSource.getFile(idFile);
					//result.setpNomeFileOModificato(result.getpIdFile()+"_"+ FileAddPanel.txtNomeFile.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 		}
	 		
	 		result.setpNomeFileOriginario(FileAddPanel.txtNomeFile.getText());
	 		//result.setpMd5(calcolaMd5(DGen.pathFileLocale(result)));
	 		File filetTMP =  new File(FileAddPanel.txtpathFile.getText());
	 		result.setpMd5(calcolaMd5(FileAddPanel.txtpathFile.getText()));
	 		result.setpDimensioneFile(filetTMP.length());
	 		result.setpDataFile(filetTMP.lastModified());
	 		result.setpPathOriginario(FileAddPanel.txtpathFile.getText());
	 		result.setpDescrizione(FileAddPanel.txtNote.getText());
	 		System.out.println("RECUPERA DA MASCHERA/" + FileAddPanel.txtNomeFile.getText()+"/" + result.getpMd5());
	 		return result;
	 	}
	    
	    public static int ReferenteAdd(long idReferente)
		{	
	    	ReferenteAddPanel p = new ReferenteAddPanel(idReferente);
			 int i = JOptionPane.showOptionDialog(null, p, DString.RefTitolo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{DString.gSalva, DString.gAnnulla}, "default");
		       return i;
		}
	    
	    public static int OffertaAdd(long idOffe, String nomeCliente, long idCliente)
		{	
	    	OffertaAddPanel p = new OffertaAddPanel(idOffe, nomeCliente, idCliente);
			 int i = JOptionPane.showOptionDialog(null, p, DString.offtitolo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{DString.gSalva, DString.gAnnulla}, "default");
		     return i;
		}
	    
	    public static int AttivitaAddRecursive(long idAtti)
		{	System.out.println("NUOVO DIALOGO------AttivitaAddRecursive");
	    	AttivitaAddPanel p = new AttivitaAddPanel(idAtti);
			 int i = JOptionPane.showOptionDialog(null, p, DString.atttitolo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{DString.gSalva, DString.attsalvaaggiungi, DString.gAnnulla}, "default");
			 if ((i==JOptionPane.OK_OPTION)||(i==JOptionPane.NO_OPTION))
				{ //aggiungireferente
				 System.out.println("NUOVO DIALOGO------AttivitaAddRecursive OK / NOOPTION");
					Attivita atti =  DGen.recuperateAttivitaDaMaschera(idAtti);
					
					atti.setpIdCliente(DData.idClienteSelezionato);
					;
					if (idAtti>=0)
					{
						DataSource.aggiornaAttivitaID(atti);
					}else{
						DataSource.aggiungiAttivita(atti);
					}
					System.out.println("MASSIMA ADD /"+DGen.stampaOraProva(DataSource.massimaDataattivita(DataSource.massimaDataattivita(DData.idClienteSelezionato))));
					long massimaAttivita = DataSource.massimaDataattivita(DData.idClienteSelezionato);
						// se data ultima attiva minaore attivita massima allora non fare niente alltrimenti aggiorna:
						if (massimaAttivita >atti.getpDataAttivita())
						{
							
						}else{
							if (atti.getpFattaAttivita()==1)
							{
									ClientiPanel.cliente.setpDataAttività(atti.getpDataAttivita());
									DataSource.aggiornaClientedaAttivita(DData.idClienteSelezionato, atti.getpDataAttivita(),atti.getpTipoAttivita());
									ListClient.refreshTabella();
							}
						}
						
					
						
						JTableReferentiAttivita.refreshTabellaAttivita(DData.idClienteSelezionato);
						JTableTodo.refreshTabellaAttivita(JTableTodo.JcomboOrdinamento.getSelectedIndex(),
								JTableTodo.JcomboSelezione.getSelectedIndex(), JTableTodo.txtRicerca.getText());
						
						if (aggiungiAcalendario)
						{	
							
							JDialogAggiungiEvento jDEvento = new JDialogAggiungiEvento(atti.getpIdCliente(), atti.getpDataAttivita(), atti.getpDescrizioneAttività());
							jDEvento.setModal(true);
							jDEvento.setVisible(true);
							aggiungiAcalendario=false;
						}
						
						if (i==JOptionPane.NO_OPTION)
						{//fai ripartire nuova attivià
							System.out.println("NUOVO DIALOGO------");
							AttivitaAddRecursive(-1);
						}
				}
		     return i;
		}
	    
	    public static int AttivitaAdd(long idAtti)
		{	
	    	AttivitaAddPanel p = new AttivitaAddPanel(idAtti);
			 int i = JOptionPane.showOptionDialog(null, p, DString.atttitolo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{DString.gSalva, DString.attsalvaaggiungi, DString.gAnnulla}, "default");
			 System.err.println("AGGIUNTA PANNELLA RESTITUISCE NUMERO/"+i);
		     return i;
		}
	    
	    public static int FilesAdd(long idFile)
	  		{	
	  	    	FileAddPanel p = new FileAddPanel(idFile);
	  			 int i = JOptionPane.showOptionDialog(null, p, DString.fileTitolo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{DString.gSalva, DString.gAnnulla}, "default");
	  		     return i;
	  		}
	    
	    
	    public static int About ()
		{	About p =new About();
	    	
			int i = JOptionPane.showOptionDialog(null, p, DString.gtitoloApplicazione, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, "default");
			//int i = JOptionPane.showConfirmDialog(null, "Mesasgio", DString.gtitoloApplicazione, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(DGen.class.getClass().getResource(DData.iconaLauncer)));
		     return i;
		}
	    
	    
	    //distanza tra due coordinate
	    public static double distance(double lat1, double lon1, double lat2, double lon2) {
	        double result = 0;
	        float[] distancetemp = new float[1];
			Location.distanceBetween(lat1, lon1, lat2, lon2, distancetemp);
	       
	        result = distancetemp[0];
	        
	        return result;
	    	/*
	    	double theta = lon1 - lon2;
	        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	        dist = Math.acos(dist);
	        dist = rad2deg(dist);
	        dist = dist * 60 * 1.1515;
	        if (unit == 'K') {
	          dist = dist * 1.609344;
	        } else if (unit == 'M') {
	          dist = dist * 0.8684;
	          }
	        return (dist);
	        */
	      }

	      /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	      /*::  This function converts decimal degrees to radians             :*/
	      /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    public static  double deg2rad(double deg) {
	        return (deg * Math.PI / 180.0);
	      }

	      /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	      /*::  This function converts radians to decimal degrees             :*/
	      /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    public static  double rad2deg(double rad) {
	        return (rad * 180.0 / Math.PI);
	      }
	    
	    
	    public static void aggiornaDistanza(double latiNew, double longiNew)
	    {	//converti i long in double
	    	double latiNewD = Double.parseDouble(latiNew+"");
	    	double longiNewD = Double.parseDouble(longiNew+"");
	    	double zeroD = Double.parseDouble(0+"");
	    	//cacloa le proiezioni  proizioneLongiNew
	    	double proiezioneLatiNew = distance(latiNewD, longiNewD,zeroD,longiNewD);
	    	double proizioneLongiNew = distance(latiNewD, longiNewD,latiNewD,zeroD);
	    	System.err.println(proiezioneLatiNew +"/" + proizioneLongiNew );
	    	//System.err.println("DATASOURCE"+ proiezioneLatiNew+ "/" + proizioneLongiNew);
	    	
	    	DataSource.aggiornaDistanzaProiezioni(proiezioneLatiNew, proizioneLongiNew);
	    }
	    
	    
	    public static void  controllaDirectoryHomedirAltimentiCrea(String path)
	    {	 final File homeDir = new File(System.getProperty("user.home"));
	    	final File dir = new File(homeDir, path);
	    		if (!dir.exists() && !dir.mkdirs()) {
	    				//direcotry non creata
	    			System.err.println("DGEN.controllaDirectoryHomedirAltimentiCrea- directory non creata");
	    }
	    	
	    }
	    
	    public static void savePreferencesString(String key, String value)
	    {
	    	Preferences prefs1 = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
    		prefs1.put(key, value);
	    }
	    public static String getPreferencesString(String key, String defValue)
	    {
	    	Preferences prefs = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
			return prefs.get(key, defValue);
	    }
	    public static void savePreferencesBoolean(String key, Boolean value)
	    {
	    	Preferences prefs1 = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
    		prefs1.putBoolean(key, value);
	    }
	    public static Boolean getPreferencesBoolean(String key, Boolean defValue)
	    {
	    	Preferences prefs = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
			return prefs.getBoolean(key, defValue);
	    }
	    public static void savePreferencesLong(String key, long value)
	    {
	    	Preferences prefs1 = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
    		prefs1.putLong(key, value);
	    }
	    public static Long getPreferencesLong(String key, Long defValue)
	    {
	    	Preferences prefs = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
			return prefs.getLong(key, defValue);
	    }
	    public static void savePreferencesInt(String key, int value)
	    {
	    	Preferences prefs1 = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
    		prefs1.putInt(key, value);
	    }
	    public static int getPreferencesInt(String key, int defValue)
	    {
	    	Preferences prefs = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
			return prefs.getInt(key, defValue);
	    }
	    public static void savePreferencesDouble(String key, double value)
	    {
	    	Preferences prefs1 = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
    		prefs1.putDouble(key, value);
	    }
	    public static double getPreferencesDouble(String key, double defValue)
	    {
	    	Preferences prefs = Preferences.userRoot().node(LayoutPC.class.getClass().getName());
			return prefs.getDouble(key, defValue);
	    }
	    
	    public static void aggiornaFileSetting()
	    {	System.err.println("DGEN aggiornafile setting/" + System.currentTimeMillis());
	    	DGen.writeData(System.currentTimeMillis()+"", DData.pathSetting+"/" + DData.NOME_FILE_SETTING);
	    }
	    
	    public static void writeData(String data, String NOME_FILE) {
	    	File file = new File(NOME_FILE);
			
	 
			try (FileOutputStream fop = new FileOutputStream(file)) {
	 
				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
	 
				// get the content in bytes
				byte[] contentInBytes = data.getBytes();
	 
				fop.write(contentInBytes);
				fop.flush();
				fop.close();
	 
				
	 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    
	   
	    public static Image imageDaResource(String path)
	    { Image image = null;
	    	try {
				image = ImageIO.read(About.class.getResourceAsStream(path));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return image;
	    }
	    
	    public static BufferedImage resizeImage(BufferedImage originalImage, int IMG_WIDTH, int IMG_HEIGHT){
			BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, originalImage.getType()); //cosi no cambia il tipo dell'immagine else inserire int type in dichiarazione
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
			g.dispose();
			
			return resizedImage;
		    }
	   
	    public static long giorniAttivita(long datainmillis) {
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis(datainmillis);
			return date.get(Calendar.YEAR)* 365 + date.get(Calendar.DAY_OF_YEAR);
		}
	    public static int mesiPassati(long datainmillis)
	    {
	    	long giorniAtt = giorniAttivita(datainmillis);
	    	long giornoOggi = giorniAttivita(System.currentTimeMillis());
	    	return (int) ((giorniAtt - giornoOggi)/30);
	    }
	   public static String stampaOraProva(long datainmillis)
	   {
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
			return sdf.format(new Date(datainmillis)); 
	   }
	   
	   public static String stampaOraMinutiProva(long datainmillis)
	   {
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy HH:mm");
			return sdf.format(new Date(datainmillis)); 
	   }
	   
	   
	   public static boolean fileExist(String pathFile)
	   {
		   return  new File(pathFile).isFile();
	   }


	   public static String calcolaMd5(String path)
		{String result=""; 
				
			char[] hexDigits = "0123456789abcdef".toCharArray();
			try {
				
				FileInputStream fis = new FileInputStream(path);
				byte[] bytes = new byte[4096];
				int read = 0;
				MessageDigest  digest = MessageDigest.getInstance("MD5");
				while ((read = fis.read(bytes)) != -1) {
	                digest.update(bytes, 0, read);
	            }
	 
	            byte[] messageDigest = digest.digest();
	 
	            StringBuilder sb = new StringBuilder(32);
	 
	            for (byte b : messageDigest) {
	                sb.append(hexDigits[(b >> 4) & 0x0f]);
	                sb.append(hexDigits[b & 0x0f]);
	            }
	 
	            result = sb.toString();
				
				
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return result;
		}
	    
	   public static void cancellaLocked()
	   {
		   Path pathll =  Paths.get(DData.pathSetting + File.separator + DLock.FILELOCKED);
		   try {
			  if (Files.exists(pathll)) Files.delete(pathll);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	   }
	   public static void deleteCartellaEFile(FileSincronizzati file)
	   {
		   String pathFileEliminare = DGen.pathFileLocale(file);
			File fileFisicoDaEliminare =  new File(pathFileEliminare);
			fileFisicoDaEliminare.delete();
			String pathCartellaDaEliminare = DGen.pathCartellaLocale(file);
			File cartellaDaEliminare = new File(pathCartellaDaEliminare);
			cartellaDaEliminare.delete();
	   }
	   public static String fileExtension(String path)
	   {
		   String extension = "";

		    int i = path.lastIndexOf('.');
		    if (i > 0) {
		        extension = path.substring(i+1);
		    }
		    return extension;
	   }
	   
	   public static void copyFileToTmp(FileSincronizzati file)
	   {String destinationPath =DGen.pathFileLocale(file);
	    String folderDestinatio =  DGen.pathCartellaLocale(file);
	    System.out.println("DGEN COPYFileTmp / " + destinationPath);
	   //controllo che ci sia la cartella se non c'è la creo
	    File theDir = new File(folderDestinatio);
	    if (!theDir.exists())
	    {
	    	theDir.mkdir();
	    }
	    File source = new File(DGen.pathFileLocale(file));
	   
		   File destination = new File (DGen.pathCartellaLocale(file)+File.separator+"tmp." + fileExtension(DGen.pathFileLocale(file)));
		   
		   try {
			Files.copy(source.toPath(), destination.toPath(),StandardCopyOption.REPLACE_EXISTING);
			System.out.println("FILE COPIATO__TMP" + source.getPath() + "__" + destinationPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	   
	   public static void copyFileTmpToDouload(FileSincronizzati file)
	   {String destinationPath =DGen.pathFileLocale(file);
	    String folderDestinatio =  DGen.pathCartellaLocale(file);
	    System.out.println("DGEN COPYFileTmp / " + destinationPath);
	   //controllo che ci sia la cartella se non c'è la creo
	    File theDir = new File(folderDestinatio);
	    if (!theDir.exists())
	    {
	    	theDir.mkdir();
	    }
	    File source = new File(folderDestinatio + File.separator+"tmp." + DGen.fileExtension(destinationPath));
	   
		   File destination = new File (DGen.pathFileLocale(file));
		   System.out.println("DGEN COPY TEMP XXXX_" + source + "___" + destination) ;
		   try {
			Files.copy(source.toPath(), destination.toPath(),StandardCopyOption.REPLACE_EXISTING);
			System.out.println("FILE COPIATO__TMP" + source.getPath() + "__" + destinationPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	   
	   public static void copyFileToUpload(FileSincronizzati file)
	   {String destinationPath =DGen.pathFileLocale(file);
	    String folderDestinatio =  DGen.pathCartellaLocale(file);
	   File cartellaFileDropBox = new File(DGen.pathCartelleFileDropbox());
	   if (!cartellaFileDropBox.exists())
	    {
		   cartellaFileDropBox.mkdir();
	    }
	    
	    
	   //controllo che ci sia la cartella se non c'è la creo
	    File theDir = new File(folderDestinatio);
	  
	    if (!theDir.exists())
	    {
	    	theDir.mkdir();
	    }
	    System.out.println("DGEN COPYFile / " + destinationPath + "__" + folderDestinatio + "__" + theDir.exists());
	   File source = new File(file.getpPathOriginario());
	   File destination = new File (destinationPath);
	   try {
		Files.copy(source.toPath(), destination.toPath(),StandardCopyOption.REPLACE_EXISTING);
		System.out.println("FILE COPIATO__" + source.getPath() + "__" + destinationPath);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	   }
	   
	   public static String pathCartelleFileDropbox ()
	   {
		   return DData.pathSetting+DData.pathInDropbox;
	   }
	   
	   public static String pathFileLocale(FileSincronizzati file)
		{String result = "";
		result = DData.pathSetting+DData.pathInDropbox  + File.separator + file.getpIdFile() + File.separator + file.getpNomeFileOriginario();
		
		return result;
		}
		
		public static String pathCartellaLocale(FileSincronizzati file)
		{String result = "";
		result = DData.pathSetting+DData.pathInDropbox  + File.separator + file.getpIdFile();
		
		return result;
		}
		
	 
		 public static String readSavedData (String NOME_FILE) {
				
		        String datax = "0" ;
		        BufferedReader br = null;
		        try {
		        	 br = new BufferedReader(new FileReader(NOME_FILE));
		            StringBuilder sb = new StringBuilder();
		            String line = br.readLine();

		            while (line != null) {
		                sb.append(line);
		               // sb.append(System.lineSeparator());
		                line = br.readLine();
		            }
		            datax = sb.toString();
		        } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
		            try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		       return datax ;
		    }
	  public static void cancellaCredenzialiCalendario()
	  {
		  System.out.println("PATHHOME"+ System.getProperty("user.home"));
		  
			String path = System.getProperty("user.home") + "\\.store\\calendar_sample\\StoredCredential";
			File file = new File(path);
			
			 Path pathpath = Paths.get(path);
		        FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(pathpath, FileOwnerAttributeView.class);
		       
			     try {
				UserPrincipalLookupService lookupservice=FileSystems.getDefault().getUserPrincipalLookupService();
		        UserPrincipal userPrincipal = lookupservice.lookupPrincipalByName(System.getProperty("user.name"));
		        Files.setOwner(pathpath,userPrincipal);
		        boolean boo = file.delete();
				  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			     
		  
	  }
	  
	  public static String restituisciTipoAttivita(int posizione)
	  {String result = "";
	  	try {
	  		result = DString.jAttivitaTipo[posizione];
		} catch (Exception e) {
			// TODO: handle exception
		}	
	  
			  	
		  return result;
	  }
	  
	  
}

