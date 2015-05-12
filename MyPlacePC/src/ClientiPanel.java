import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;


public class ClientiPanel extends JPanel{

	public static final long serialVersionUID = 1L;
	public static JButton bttadd,bttsave,bttdelete, bttaddreferente, bttaddattivita, bttaddCoordinate,bttaddAlarm,bttmap, bttwww,bttemail, bttaddofferta;
	public static JButton bttAddfile;
	static public JComboBox jComboTipologia;
	public JLabel lblIDcliente;
	public static JLabel lblIDclienteNumero;
	public static JLabel lblChkCliente;
	public static JCheckBox chkCliente;
	public JLabel lblNome;
	public JLabel lblIndirizzio;
	public JLabel lblCitta;
	public JLabel lblNazione;
	public JLabel lblTel, lblCapMail;
	public JLabel lblFax;
	public JLabel lblwww;
	public JLabel lblLati;
	public JLabel lblLongi;
	public JLabel lblNota;
	public static JLabel lblfree1;
	public static JLabel lblfree2;
	public static JLabel lblfree3;
	public static JLabel lblfree4;
	static public JTextField  txtNome, txtIndirizzio, txtcitta,txtnazione,txttel,txtfax, txtwww,txtlati,txtlongi, txtCapMail;
	static public JTextField txtfree1,txtfree2,txtfree3,txtfree4;
	static public JTextArea txtNote;
	static posizioneGps cliente; //cliente visualizzato
	static boolean clienteModificato = false;
	
	BorderLayout border_layout;
	public static JPanel combinato,  up, lineID,lineIndirizzo, lineTelFaxWww,jCliente;
	public static JScrollPane jSCliente;
	public static  JFrame topFrame;
	public static boolean visible = true;
	public static jDialogProgress jdp;

    public ClientiPanel()
    {	
    	 border_layout = new BorderLayout(); // creo il layout manager e lo assegno al container
         setLayout(border_layout);
    	
    	//inizializzo gli elementi dai inserire nel layout
    	inizializzaComponenti();
    	//combinato = new JPanel();
    	//combinato.setLayout(new BoxLayout(combinato, BoxLayout.PAGE_AXIS));
    	up = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	up.add(bttadd);
    	
    	up.add(bttsave);
    	up.add(bttdelete);
    	
    	up.add(bttaddreferente);
    	up.add(bttaddattivita);
    	up.add(bttaddCoordinate);
    	up.add(bttaddAlarm);
    	up.add(bttaddofferta);
    	up.add(bttAddfile);
    	
    	
    	
    
    
		
		
    	
    	jCliente = new JPanel(new GridBagLayout());
    	
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.HORIZONTAL ; c.insets = new Insets(2,0,0,5);
    	c.anchor = GridBagConstraints.FIRST_LINE_START;
    	
    	//prima riga
    	c.gridx = 0; c.gridy = 0;
    	jCliente.add(lblChkCliente, c);
    	c.gridx = 1;c.gridy = 0;
    	jCliente.add(chkCliente, c);
    	c.gridx = 2;c.gridy = 0;c.gridwidth = 2;   //2 columns wide
    	jCliente.add(jComboTipologia, c);
    
    	c.gridx = 4;c.gridy = 0;c.gridwidth = 1;
    	jCliente.add(lblNome, c);
    	c.gridx = 5;c.gridy = 0;c.gridwidth = 4;
    	jCliente.add(txtNome, c);
    	
    	
    
    	c.gridx = 10; c.gridy = 0;
    	jCliente.add(lblIDcliente, c);
    	c.gridx = 11;c.gridy = 0;
    	jCliente.add(lblIDclienteNumero, c);
    	
    	c.gridx = 0;c.gridy = 1;c.gridwidth = 1;
    	jCliente.add(lblIndirizzio, c);
    	c.gridx = 1;c.gridy = 1;c.gridwidth = 3;
    	jCliente.add(txtIndirizzio, c);
    	//terza riga
    	c.gridx = 4;c.gridy = 1;c.gridwidth = 1;
    	jCliente.add(lblCitta, c);
    	c.gridx = 5;c.gridy = 1;c.gridwidth = 2;
    	jCliente.add(txtcitta, c);
    	c.gridx = 7;c.gridy = 1;c.gridwidth = 1;
    	jCliente.add(lblNazione, c);
    	c.gridx = 8;c.gridy = 1;c.gridwidth = 1;
    	jCliente.add(txtnazione, c);
    	
    	
    	
    	//quarta riga
    	c.gridx = 0;c.gridy = 2;c.gridwidth = 1;
    	jCliente.add(lblTel, c);
    	c.gridx = 1;c.gridy = 2;c.gridwidth = 1;
    	jCliente.add(txttel, c);
    	c.gridx = 2;c.gridy = 2;c.gridwidth = 1;
    	jCliente.add(lblFax, c);
    	c.gridx = 3;c.gridy = 2;c.gridwidth = 1;
    	jCliente.add(txtfax, c);
    	
    	c.gridx = 4;c.gridy = 2;c.gridwidth = 1;
    	jCliente.add(lblwww, c);
    	c.gridx = 5;c.gridy = 2;c.gridwidth = 3;
    	
    	jCliente.add(txtwww, c);
    	c.gridx = 8;c.gridy = 2;c.weightx =1;
    	c.fill = GridBagConstraints.NONE;
    	c.anchor = GridBagConstraints.WEST;
    	jCliente.add(bttwww, c);
    	
    	c.fill = GridBagConstraints.HORIZONTAL;
    	
    	//Quinta riga
    
    	  	
    	///
    	c.gridx = 0;c.gridy = 3;c.gridwidth = 1;
    	jCliente.add(lblLati, c);
    	c.gridx = 1;c.gridy = 3;c.gridwidth = 1;
    	jCliente.add(txtlati, c);
    	c.gridx = 2;c.gridy = 3;c.gridwidth = 1;
    	jCliente.add(lblLongi, c);
    	c.gridx = 3;c.gridy = 3;c.gridwidth = 1;
    	jCliente.add(txtlongi, c);
    	

    	//Aggiungo email
    	c.gridx = 4;c.gridy = 3;c.gridwidth = 1;
    	jCliente.add(lblCapMail, c);
    	c.gridx = 5;c.gridy = 3;c.gridwidth = 3;
    	jCliente.add(txtCapMail, c);
    	c.gridx = 8;c.gridy = 3;c.weightx =1;
    	c.fill = GridBagConstraints.NONE;
    	c.anchor = GridBagConstraints.WEST;
    	jCliente.add(bttemail, c);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	//sesta riga
    	c.gridx = 0;c.gridy = 7;c.gridwidth = 1;
    	jCliente.add(lblNota, c);
    	txtNote.setSize(300, Integer.MAX_VALUE);
    	c.gridx = 1;c.gridy = 7;c.gridwidth = 8;c.gridheight = 3;
    	jCliente.add(txtNote, c);
    	/*
    	Proprieta pro = new Proprieta();
    	pro.setpBooProprieta(false);
    	DData.hashProprieta.put(1, pro);
    	DData.hashProprieta.put(2, pro);
    	DData.hashProprieta.put(3, pro);
    	DData.hashProprieta.put(4, pro);*/
    	int contaVis =0;
    	int xx = 0; int yy = 0;
    	
    	if (DData.hashProprieta.get(1).getpBooProprieta())
    	{contaVis++;
    		xx = resistuisceXpos(contaVis);
    		yy = resistuisceYpos(contaVis);
    	}else{ xx=0;yy=0;}
    	//settima riga
    	c.gridx = xx;c.gridy = yy;c.gridwidth = 1;
    	jCliente.add(lblfree1, c);
    	c.gridx = xx+1;c.gridy = yy;c.gridwidth = restituisceLarghezza(contaVis);
    	jCliente.add(txtfree1, c);
    	if(yy==0)
    	{	txtfree1.setVisible(false);
    		lblfree1.setVisible(false);
    	}
    	
    	if (DData.hashProprieta.get(2).getpBooProprieta())
    	{contaVis++;
    	
    		xx = resistuisceXpos(contaVis);
    		yy = resistuisceYpos(contaVis);
    	}else{ xx=0;yy=0;}
    	
    	c.gridx = xx;c.gridy = yy;c.gridwidth = 1;
    	jCliente.add(lblfree2, c);
    	c.gridx = xx+1;c.gridy = yy;c.gridwidth = restituisceLarghezza(contaVis);;
    	jCliente.add(txtfree2, c);
    	if(yy==0)
    	{	txtfree2.setVisible(false);
    		lblfree2.setVisible(false);
    	}
    	//ottaVA riga
    	if (DData.hashProprieta.get(3).getpBooProprieta())
    	{contaVis++;
    		xx = resistuisceXpos(contaVis);
    		yy = resistuisceYpos(contaVis);
    	}else{ xx=0;yy=0;}
    	c.gridx = xx;c.gridy = yy;c.gridwidth = 1;
    	jCliente.add(lblfree3, c);
    	c.gridx = xx+1;c.gridy = yy;c.gridwidth = restituisceLarghezza(contaVis);;
    	jCliente.add(txtfree3, c);
    	if(yy==0)
    	{	txtfree3.setVisible(false);
    		lblfree3.setVisible(false);
    	}
    	
    	if (DData.hashProprieta.get(4).getpBooProprieta())
    	{contaVis++;
    		
    		xx = resistuisceXpos(contaVis);
    		yy = resistuisceYpos(contaVis);
    	}else{ xx=0;yy=0;}
    	
    	c.gridx = xx;c.gridy = yy;c.gridwidth = 1;
    	jCliente.add(lblfree4, c);
    	c.gridx = xx+1;c.gridy = yy;c.gridwidth = restituisceLarghezza(contaVis);;
    	jCliente.add(txtfree4, c);
    	if(yy==0)
    	{	txtfree4.setVisible(false);
    		lblfree4.setVisible(false);
    	}
    	
    	lineID = new JPanel(new FlowLayout(FlowLayout.LEADING));
    	lineID.add(jCliente);
    	jSCliente = new JScrollPane(lineID);
    	add(up, BorderLayout.NORTH);
        add(jSCliente, BorderLayout.CENTER);
     	
        
    }
    
    public static void aggiornaCampiUtente()
    
    {
    	int contaVis =0;
    	int xx = 0; int yy = 0;
    	
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.HORIZONTAL ; c.insets = new Insets(2,0,0,5);
    	c.anchor = GridBagConstraints.FIRST_LINE_START;
    	
    	
    	if (DData.hashProprieta.get(1).getpBooProprieta())
    	{contaVis++;
    		xx = resistuisceXpos(contaVis);
    		yy = resistuisceYpos(contaVis);
    	}else{ xx=0;yy=0;}
    	//settima riga
    	c.gridx = xx;c.gridy = yy;c.gridwidth = 1;
    	jCliente.add(lblfree1, c);
    	c.gridx = xx+1;c.gridy = yy;c.gridwidth = restituisceLarghezza(contaVis);
    	jCliente.add(txtfree1, c);
    	if(yy==0)
    	{	txtfree1.setVisible(false);
    		lblfree1.setVisible(false);
    	}
    	
    	lblfree1.setText(DData.hashProprieta.get(1).getpTxtProprieta());
    	if (DData.hashProprieta.get(2).getpBooProprieta())
    	{contaVis++;
    	
    		xx = resistuisceXpos(contaVis);
    		yy = resistuisceYpos(contaVis);
    	}else{ xx=0;yy=0;}
    	
    	c.gridx = xx;c.gridy = yy;c.gridwidth = 1;
    	jCliente.add(lblfree2, c);
    	c.gridx = xx+1;c.gridy = yy;c.gridwidth = restituisceLarghezza(contaVis);;
    	jCliente.add(txtfree2, c);
    	if(yy==0)
    	{	txtfree2.setVisible(false);
    		lblfree2.setVisible(false);
    	}
    	lblfree2.setText(DData.hashProprieta.get(2).getpTxtProprieta());
    	//ottaVA riga
    	if (DData.hashProprieta.get(3).getpBooProprieta())
    	{contaVis++;
    		xx = resistuisceXpos(contaVis);
    		yy = resistuisceYpos(contaVis);
    	}else{ xx=0;yy=0;}
    	c.gridx = xx;c.gridy = yy;c.gridwidth = 1;
    	jCliente.add(lblfree3, c);
    	c.gridx = xx+1;c.gridy = yy;c.gridwidth = restituisceLarghezza(contaVis);;
    	jCliente.add(txtfree3, c);
    	if(yy==0)
    	{	txtfree3.setVisible(false);
    		lblfree3.setVisible(false);
    	}
    	lblfree3.setText(DData.hashProprieta.get(3).getpTxtProprieta());
    	if (DData.hashProprieta.get(4).getpBooProprieta())
    	{contaVis++;
    		
    		xx = resistuisceXpos(contaVis);
    		yy = resistuisceYpos(contaVis);
    	}else{ xx=0;yy=0;}
    	
    	c.gridx = xx;c.gridy = yy;c.gridwidth = 1;
    	jCliente.add(lblfree4, c);
    	c.gridx = xx+1;c.gridy = yy;c.gridwidth = restituisceLarghezza(contaVis);;
    	jCliente.add(txtfree4, c);
    	if(yy==0)
    	{	txtfree4.setVisible(false);
    		lblfree4.setVisible(false);
    	}
    	lblfree4.setText(DData.hashProprieta.get(4).getpTxtProprieta());
    }
    
    
    public static int x1=0; 
    public static int x2=4; 
    public static int y1=10; 
    public static int y2=13; 
      
    public static int resistuisceXpos(int contaVisib) 
    {   int result = 1; 
        int correzioneTExt=0; 
        
        switch (contaVisib) { 
        case 1:result = x1 + correzioneTExt    ;break; 
        case 2: result = x2 + correzioneTExt   ;break; 
        case 3: result = x1  + correzioneTExt ;break; 
        case 4: result = x2 + correzioneTExt  ;break; 

        default: result =0;  
            break; 
        } 
        return result; 
    } 
     public static int restituisceLarghezza(int contaVis)
     {int result = 3;
     	switch (contaVis) {
		case 2:result = 4;break;
		case 4:result = 4;break;
		default:break;
		}
    	return result;
     }
    public static int resistuisceYpos(int contaVisib) 
    {   int result = 1; 
        switch (contaVisib) { 
        case 1:result = y1    ;break; 
        case 2: result = y1    ;break; 
        case 3: result = y2   ;break; 
        case 4: result = y2   ;break; 

        default:result =0;  
            break; 
        } 
        return result; 
    } 
    
     public void inizializzaComponenti()
    {	
    	bttmap = new JButton(new ImageIcon(getClass().getResource(DData.iconaCancellaTesto)));
    	bttmap.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttmap.setToolTipText(DString.ttRicercaCoordinate);
    	bttmap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 	/*
					ReferenteAddPanel p = new ReferenteAddPanel(idReferente);
					int i = JOptionPane.showOptionDialog(null, p, DString.RefTitolo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{DString.gSalva, DString.gAnnulla}, "default");
			       return i;
			       */
            
			}
		});
    	
    	
    	bttaddAlarm = new JButton(new ImageIcon(getClass().getResource(DData.iconaAggiungiEvento)));
    	bttaddAlarm.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttaddAlarm.setToolTipText(DString.ttInserisciEvento);
    	bttaddAlarm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
				JDialogAggiungiEvento jDEvento = new JDialogAggiungiEvento(cliente.getpId(), System.currentTimeMillis(),"");
				jDEvento.setModal(true);
				jDEvento.setVisible(true);
            
			}
		});
    	JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    	bttaddCoordinate = new JButton(new ImageIcon(getClass().getResource(DData.iconaRecuperaCoordinate)));
    	bttaddCoordinate.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttaddCoordinate.setToolTipText(DString.ttRicercaCoordinate);
    	bttaddCoordinate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
					//String joe = System.getProperty("user.name");
					
					
			     
				Task task = new Task();
			   // task.addPropertyChangeListener(this);
			    task.execute();
			   
			}
				
		});
    	
    	bttadd = new JButton(new ImageIcon(getClass().getResource(DData.iconaNuova)));
    	bttadd.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttadd.setToolTipText(DString.ttNuovoCli);
    	//bttadd = new JButton(new ImageIcon(DData.iconaNuova));
    	bttadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				aggiornaCliente(-1);
				//bttadd.setEnabled(false);
		       
			}
		});
    	
    	bttsave =  new JButton(new ImageIcon(getClass().getResource(DData.iconaSave)));
    	bttsave.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttsave.setToolTipText(DString.ttSalvaCliente);
    	bttsave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lblIDclienteNumero.getText().length()>0) 
    			{	
    				long idCliente = (long) Double.parseDouble(lblIDclienteNumero.getText());
    				
    				aggiornaCliente(idCliente);
    			}else{
    				if (clienteVuoto()==false)
    				{
    					aggiornaCliente(-1);
    					bttaddreferente.setEnabled(true);
    					bttAddfile.setEnabled(true);
    					bttaddattivita.setEnabled(true);
    					bttaddCoordinate.setEnabled(true);
    					bttadd.setEnabled(true);
    					bttaddofferta.setEnabled(true);
    				}
    			}
				
			}
		});
    	bttdelete = new JButton(new ImageIcon(getClass().getResource(DData.iconaCancella)));
    	bttdelete.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttdelete.setToolTipText(DString.ttCancellaCliente);
    	bttdelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lblIDclienteNumero.getText().length()>0) 
    			{	
    				long idCliente = (long) Double.parseDouble(lblIDclienteNumero.getText());
    				int i=DGen.showConfirm(DString.gAttenzione, DString.msgcancellazione, JOptionPane.YES_NO_OPTION);
    				if (i==JOptionPane.YES_OPTION)
    				{
    					DataSource.cancellaClienteTotale(idCliente);
    					cliente = null;
    					DData.idClienteSelezionato = -1;
    					aggiornaCampiTxt(-1);
    					
    					ListClient.refreshTabella();
    				}
    				
    			}
				
			}
		});
    	
    	bttAddfile = new JButton(new ImageIcon(getClass().getResource(DData.iconaAttach)));
    	bttAddfile.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttAddfile.setToolTipText(DString.ttAddFile);
    	bttAddfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int result = DGen.FilesAdd(-1);
				if (result==JOptionPane.OK_OPTION)
				{ //aggiungireferente
					 
					 FileSincronizzati fileS =  DGen.recuperaFileDaMascehra(-1);
					 fileS.setpIdCliente(DData.idClienteSelezionato);
					 int newRow = DataSource.aggiungiFile(fileS);
					 System.out.println("CLIENTPANEL ADD FILE___" + newRow);
					 fileS.setpIdFile(newRow);
					
					 fileS.setpNomeFileOModificato(newRow+"_" + fileS.getpNomeFileOriginario());
					 //DataSource.aggiornaFileNomeModificato(fileS);
	            	  JTableFilesCliente.refreshTabellaFile();
	            	  DGen.copyFileToUpload(fileS);
					
				}
			}
		});
    	
    	bttaddreferente = new JButton(new ImageIcon(getClass().getResource(DData.iconaAddReferenti)));
    	bttaddreferente.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttaddreferente.setToolTipText(DString.ttAddRef);
    	bttaddreferente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int result = DGen.ReferenteAdd(-1);
				
				if (result==JOptionPane.OK_OPTION)
				{ //aggiungireferente
					 
					Referenti refe =  DGen.recuperateReferenteDaMaschera(-1);
					refe.setpIdClienteReferente(DData.idClienteSelezionato);
					DataSource.aggiungiReferente(refe);
					JTableReferentiAttivita.refreshTabellaReferenti(DData.idClienteSelezionato);
					
				}
				
			}
		});
    	
    	bttemail= new JButton(new ImageIcon(getClass().getResource(DData.iconaVisualizzaWWW)));
    	bttemail.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy-5));
    	bttemail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				   if(Desktop.isDesktopSupported())
				   {	 if (txtCapMail.getText().length()>0){
							   Desktop desktop;
							   if (Desktop.isDesktopSupported() 
							       && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
							     URI mailto;
								try {
									mailto = new URI("mailto:"+ txtCapMail.getText());
									 desktop.mail(mailto);
								} catch (URISyntaxException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    
							   } else {
							     // TODO fallback to some Runtime.exec(..) voodoo?
							     throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
							   }
						
                       }
				   }
			}
		});
    	bttwww = new JButton(new ImageIcon(getClass().getResource(DData.iconaVisualizzaWWW)));
    	bttwww.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy-5));
    	bttwww.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				   if(Desktop.isDesktopSupported())
				   {	 if (txtwww.getText().length()>0){
                       try {
                    	  
						Desktop.getDesktop().browse(new URI(txtwww.getText()));
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
		});
    	
    	System.out.println("ICONA/" + getClass().getResource(DData.iconaOfferte));
    	bttaddofferta = new JButton(new ImageIcon(getClass().getResource(DData.iconaOfferte)));
    	bttaddofferta.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttaddofferta.setToolTipText(DString.ttaddofferta);
    	bttaddofferta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				long idSelezionato = -1;
				int result =  DGen.OffertaAdd(idSelezionato, txtNome.getText(),DData.idClienteSelezionato);
	              if (result==JOptionPane.OK_OPTION)
					{ 
							Offerta offe = DGen.recuperateOffertaDaMaschera(idSelezionato,DData.idClienteSelezionato );
							long  i = DataSource.aggiungiOfferta(offe);
							System.out.println("NUOVA OFFERTA ID"+ i + "/CLIENTE SELEZIONATO" + offe.getpIdClienteOfferta() + "/" +offe.getpNomeCliente());
		         	 
							JTableOfferte.refreshTabellaOfferta(-1,
											JTableOfferte.JcomboOrdinamentoOfferte.getSelectedIndex(),
													JTableOfferte.JcomboSelezioneOfferte.getSelectedIndex(), 
													JTableOfferte.txtRicercaOfferte.getText());
							
		
							JTableOfferteSingoloCliente.refreshTabellaOfferta(offe.getpIdClienteOfferta(),JTableOfferteSingoloCliente.JcomboOrdinamentoOfferte.getSelectedIndex(),
									JTableOfferteSingoloCliente.JcomboSelezioneOfferte.getSelectedIndex(), JTableOfferteSingoloCliente.txtRicercaOfferte.getText());
							System.out.println("OFFERTE SING AGGIORNA 556/" +offe.getpIdClienteOfferta() );
							
						
						
	            	  	
					}
				
				
			}
		});
    	
    	bttaddattivita = new JButton(new ImageIcon(getClass().getResource(DData.iconaAddAttivita)));
    	bttaddattivita.setPreferredSize(new Dimension(DData.dimPulsantiX, DData.dimPulsantiy));
    	bttaddattivita.setToolTipText(DString.ttAddAttivita);
    	bttaddattivita.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = DGen.AttivitaAddRecursive(-1);
				
			}
		});
    	
    	jComboTipologia = new JComboBox(DGen.itemComboTipologieEsclusaTutte());
    	jComboTipologia.setMaximumRowCount(16);
    	jComboTipologia.setPrototypeDisplayValue("Data attività Descresc"); //imposta la larghezza massima
    	lblIDcliente = new JLabel(DString.cliIDcliente);
    	lblIDcliente.setVisible(false);
    	
    	lblChkCliente = new JLabel(DString.cliChkCliente);
    	lblIDclienteNumero = new JLabel("");
    	
    	lblIDclienteNumero.setVisible(false);
    	lblNome = new JLabel(DString.cliNome);
    	lblIndirizzio = new JLabel(DString.cliIndirizzio);
    	lblCitta = new JLabel(DString.cliCitta);
    	lblNazione = new JLabel(DString.cliNazione);
    	lblTel = new JLabel(DString.cliTel);
    	lblFax = new JLabel(DString.cliFax);
    	lblwww = new JLabel(DString.cliWww);
    	lblCapMail = new JLabel(DString.cliCapMail);
    	lblLati = new JLabel(DString.cliLati);
    	lblLongi = new JLabel(DString.cliLongi);
    	lblNota = new JLabel(DString.cliNota);
    	
    	lblfree1 = new JLabel(DData.hashProprieta.get(1).getpTxtProprieta());
    	txtfree1 = new JTextField(20);
    	if (!DData.hashProprieta.get(1).getpBooProprieta())
    	{lblfree1.setVisible(false);
    	 txtfree1.setVisible(false);
    	}
    	
    	lblfree2 = new JLabel(DData.hashProprieta.get(2).getpTxtProprieta());
    	txtfree2 = new JTextField(20);
    	if (!DData.hashProprieta.get(2).getpBooProprieta())
    	{lblfree2.setVisible(false);
    	 txtfree2.setVisible(false);
    	}
    	
    	lblfree3= new JLabel(DData.hashProprieta.get(3).getpTxtProprieta());
    	txtfree3 = new JTextField(20);
    	if (!DData.hashProprieta.get(3).getpBooProprieta())
    	{lblfree3.setVisible(false);
    	 txtfree3.setVisible(false);
    	}
    	
    	lblfree4 = new JLabel(DData.hashProprieta.get(4).getpTxtProprieta());
    	txtfree4 = new JTextField(20);
    	if (!DData.hashProprieta.get(4).getpBooProprieta())
    	{lblfree4.setVisible(false);
    	 txtfree4.setVisible(false);
    	}
    	chkCliente = new JCheckBox();
    	txtNome = new JTextField(15);
    	txtIndirizzio = new JTextField(20);
    	txtcitta = new JTextField(15);
    	txtnazione = new JTextField(10);
    	txttel = new JTextField(10);
    	txtfax = new JTextField(10);
    	txtwww = new JTextField(20);
    	txtlati = new JTextField(10);
    	txtCapMail = new JTextField(10);
    	txtlati.setText("0");
    	txtlongi = new JTextField(10);
    	txtlongi.setText("0");
    	txtNote =  new JTextArea();
    	txtNote.setRows(3);
    	txtNote.setLineWrap(true);
    	txtNote.setWrapStyleWord(true);
    	  Border border = BorderFactory.createLineBorder(Color.GRAY);
      	txtNote.setBorder(border);
    	
    	
    	
    
    	
    }
    
    
    public static boolean controlloErroreCoordinate()
    {
    	boolean result = false;
		
    	double lati = 0;
		double longi = 0;
		try {
			//longi = Long.parseLong(txtlongi.getText());
			longi = Double.parseDouble(txtlongi.getText());
		} catch (Exception e) {
			result = true;
			
		
		}
		try {
			lati = Double.parseDouble(txtlati.getText());
		} catch (Exception e) {
			result = true;
			
		}
		
		return result;
    }
    public static boolean controllaClienteModificato()
    {boolean result = false;
    //System.out.println("ControllaClienteModificato/" + (cliente==null)+"/" + txtNome.getText() + "/"  + cliente.getpNome());
    try {
		
	
   if ((cliente!=null)&&(txtNome.getText()!=null))
   {	
	   if (cliente.getpTipo() != 
    			Long.parseLong(DData.HashIDComboToIDTipologiaSenza.get(jComboTipologia.getSelectedIndex()+""))) result = true;
    	if (!(cliente.getpNome().equals(txtNome.getText()))) result = true;
    	if (!(cliente.getpInidirizzo().equals(txtIndirizzio.getText()))) result = true;
    	if (!(cliente.getpCittà().equals(txtcitta.getText()))) result = true;
    	try {
    		if (!(cliente.getpNazione().equals(txtnazione.getText()))) result = true;
		} catch (Exception e) {
			
		}
    	if (!(cliente.getpCap().equals(txtCapMail.getText()))) result = true;
    	if (!(cliente.getpTelefono().equals(txttel.getText()))) result = true;
    	if (!(cliente.getpFax().equals(txtfax.getText()))) result = true;
    	if (!(cliente.getpSito().equals(txtwww.getText()))) result = true;
    	if (!(cliente.getpMemo().equals(txtNote.getText()))) result = true;
    	
    	if (!(cliente.getpMemo().equals(txtNote.getText()))) result = true;
    	if (!(cliente.getpLatitudine() == Double.parseDouble(txtlati.getText()))) result = true;
    	if (!(cliente.getpLongitudine() == Double.parseDouble(txtlongi.getText()))) result = true;
    	
    	if (!(cliente.getpFree1().equals(txtfree1.getText()))) result = true;
    	if (!(cliente.getpFree2().equals(txtfree2.getText()))) result = true;
    	if (!(cliente.getpFree3().equals(txtfree3.getText()))) result = true;
    	if (!(cliente.getpFree4().equals(txtfree4.getText()))) result = true;
    	
    	boolean checkEd = cliente.getpSpare1()==1?true:false;
    	if (!(checkEd==chkCliente.isSelected())) result = true;
    	//if (!(cliente.getpLongitudine().equals(txtlongi.getText()))) result = true;
    	//mancano coordinate
   }else{ //se il cliente è nullo controllo se è stato inserito qualcosa
	   	result = !clienteVuoto();
   }
    } catch (Exception e) {
		result = false;
	}
    	
    	
    	
    	return result;
    }
    
    public static posizioneGps createClienteDaMaschera()
    {posizioneGps result = new posizioneGps();
    		if (lblIDclienteNumero.getText().length()>0) 
    			{	
    				long res = (long) Double.parseDouble(lblIDclienteNumero.getText());
    			
    				result.setpId((res));
    			}else{
    				result.setpId(-1);
    			}
    		result.setpTipo(Integer.parseInt(DData.HashIDComboToIDTipologiaSenza.get(jComboTipologia.getSelectedIndex()+"")));
    		result.setpnome(txtNome.getText());
    		result.setpInidirizzo(txtIndirizzio.getText());
    		result.setpCittà(txtcitta.getText());
    		result.setpNazione(txtnazione.getText());
    		result.setpCap(txtCapMail.getText());
    		System.out.println("CREATECLIENTEDAMASCHERA/"+ txtCapMail.getText());
    		result.setptelefono(txttel.getText());
    		result.setpFax(txtfax.getText());
    		result.setpSito(txtwww.getText());
    		result.setpMemo(txtNote.getText());
    		result.setpDataInMillis(System.currentTimeMillis());
    		result.setpLongitudine(Double.parseDouble(txtlongi.getText()));
    		result.setpLatitudine(Double.parseDouble(txtlati.getText()));
    		System.out.println("CreaClienteMaschera/" + txtfree1.getText());
    		result.setpFree1(txtfree1.getText());
    		result.setpFree2(txtfree2.getText());
    		result.setpFree3(txtfree3.getText());
    		result.setpFree4(txtfree4.getText());
    		result.setpSpare1(chkCliente.isSelected()?1:0);
    		
    
    return result;
    }
    public static void aggiornaCampiTxt(long idCliente)
    {	
    	if (idCliente>=0)
		{
				cliente = DataSource.getClientID(idCliente);
				System.err.println("CLIENTI PANEL AGGIONACAMPI/" + cliente.getpDataAttività()+"/" + cliente.getpId() + "/" + cliente.getpNome());
				txtNote.setText(cliente.getpMemo());
				try {
					jComboTipologia.setSelectedIndex(Integer.parseInt(DData.HashIDTipologiaToIDComboSenza.get(cliente.getpTipo()+"")));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				lblIDclienteNumero.setText(cliente.getpId()+"");
				txtNome.setText(cliente.getpNome());
		    	txtIndirizzio.setText(cliente.getpInidirizzo());
		    	txtcitta.setText(cliente.getpCittà());
		    	txtnazione.setText(cliente.getpNazione());
		    	txtCapMail.setText(cliente.getpCap());
		    	txttel.setText(cliente.getpTelefono());
		    	txtfax.setText(cliente.getpFax());
		    	txtwww.setText(cliente.getpSito());
		    	txtlati.setText(cliente.getpLatitudine()+"");
		    	txtlongi.setText(cliente.getpLongitudine()+"");
		    	txtfree1.setText(cliente.getpFree1());
		    	txtfree2.setText(cliente.getpFree2());
		    	txtfree3.setText(cliente.getpFree3());
		    	txtfree4.setText(cliente.getpFree4());
		    	txtNome.setCaretPosition(0);
		    	chkCliente.setSelected(cliente.getpSpare1()==1?true:false);
		    	jSCliente.getVerticalScrollBar().setValue(0);
		    	// le righe sotto portano lo scrol panel in cima
		    	SwingUtilities.invokeLater(new Runnable() {
		    	      public void run() {
		    	    	  jSCliente.getViewport().setViewPosition(new java.awt.Point(0, 0));
		    	      }
		    	});
    	
    	
		}else{
			
				cliente = null;
				lblIDclienteNumero.setText("");
				txtNome.setText("");
		    	txtIndirizzio.setText("");
		    	txtcitta.setText("");
		    	txtnazione.setText("");
		    	txtCapMail.setText("");
		    	txttel.setText("");
		    	txtfax.setText("");
		    	txtwww.setText("");
		    	txtlati.setText("0");
		    	txtlongi.setText("0");
		    	txtNote.setText("");
		    	txtfree1.setText("");
		    	txtfree2.setText("");
		    	txtfree3.setText("");
		    	txtfree4.setText("");
		    	chkCliente.setSelected(false);
	    	
		}
    	
    	
    	
    	
    		JTableReferentiAttivita.refreshTabellaAttivita(idCliente);
    		JTableReferentiAttivita.refreshTabellaReferenti(idCliente);
    		JTableOfferteSingoloCliente.refreshTabellaOffertaDaAltraTabella(idCliente);
    		JTableFilesCliente.refreshTabellaFile();
    		
    	
}
    	
    public static boolean clienteVuoto(){
    	boolean result = false;
    	String superstringa = txtNome.getText()+txtIndirizzio.getText()+txtcitta.getText()+
    			txtnazione.getText()+txttel.getText()+txtfax.getText()+txtwww.getText()+txtCapMail.getText()+
    			txtNote.getText(); //tolgo +txtlongi.getText()+txtlati.getText() cosi imposta a 0 le coordinate
    	String sFree = "";
    		if (txtfree1.isVisible()) sFree = sFree + txtfree1.getText().toString();
    		if (txtfree2.isVisible()) sFree = sFree + txtfree2.getText().toString();
    		if (txtfree3.isVisible()) sFree = sFree + txtfree3.getText().toString();
    		if (txtfree4.isVisible()) sFree = sFree + txtfree4.getText().toString();
    	superstringa = superstringa+sFree;	
    	if (superstringa.length()==0) result=true;
    	return result;
    	
    }
    
   
	public static boolean aggiornaCliente (long idCliente)
	{	
		boolean result = true; //resitutisce false solo se non deve aprire altre finstre
		if (idCliente<0)
		{
			bttaddreferente.setEnabled(false);
			bttaddattivita.setEnabled(false);
			bttaddCoordinate.setEnabled(false);
			bttaddofferta.setEnabled(false);
			bttadd.setEnabled(false);
		}else{
			bttaddreferente.setEnabled(true);
			bttaddattivita.setEnabled(true);
			bttaddCoordinate.setEnabled(true);
			bttadd.setEnabled(true);
			bttaddofferta.setEnabled(true);
		}
		
		if ((cliente!=null)||(clienteVuoto()==false))
		{ 	if (controlloErroreCoordinate())
			{int i = DGen.showConfirm(DString.gAttenzione, DString.msgErroreCoordinate, JOptionPane.YES_NO_OPTION);
				if (i==JOptionPane.YES_OPTION)
				{
					aggiornaCampiTxt(idCliente);
				}
			}else{
				
				if (controllaClienteModificato())
				{
					int ritornoMessaggio = DGen.showConfirm(DString.gAttenzione, DString.msgClienteModificato, JOptionPane.YES_NO_CANCEL_OPTION);
					switch (ritornoMessaggio) {
					case JOptionPane.YES_OPTION:
							//salvamodifice
						System.out.println("CLIENTI PANEL YES");
						posizioneGps nuovoCliente = createClienteDaMaschera();
						
						//non aggiorna distanza 
						if (nuovoCliente.getpId()>0)
						{	System.out.println("maschera"+ nuovoCliente.getpFree1()+"/" + nuovoCliente.getpFree2()+"/" + nuovoCliente.getpCap()) ;
							//nuovoCliente.setpCap(cliente.getpCap());
							nuovoCliente.setpDistanza(cliente.getpDistanza());
							nuovoCliente.setpLatitudineProi(cliente.getpLatitudineProi());
							nuovoCliente.setpLongitudineProi(cliente.getpLongitudineProi());
							nuovoCliente.setpDataAttività(cliente.getpDataAttività());
							nuovoCliente.setpGiorniAttività(cliente.getpGiorniAttività());
							
							nuovoCliente.setpMesiAttivita(cliente.getpMesiAttivita());
							
							System.out.println("VECCHIO"+DData.idClienteSelezionato + nuovoCliente.getpCap());
							nuovoCliente.setpSpare1(chkCliente.isSelected()?1:0);
							DataSource.aggiornaClientedaID(nuovoCliente);
							//DData.idClienteSelezionato = cliente.getpId();
							//cliente = nuovoCliente;
							
							aggiornaCampiTxt(idCliente);
						}else{
							//aggiorna i cmpi proizione
							double latiNewD = Double.parseDouble(nuovoCliente.getpLatitudine()+"");
					    	double longiNewD = Double.parseDouble(nuovoCliente.getpLongitudine()+"");
					    	double zeroD = Double.parseDouble(0+"");
							double proilati = DGen.distance(latiNewD, longiNewD,zeroD,longiNewD);
					    	double proilongi = DGen.distance(latiNewD, longiNewD,latiNewD,zeroD);
							nuovoCliente.setpProiezioneSettato(nuovoCliente, proilati, proilongi, false);
							// aggiorna sistanza preicisione cap provincia 
							//nuovoCliente.setpCap(nuovoCliente.getpCap());
							nuovoCliente.setpDistanza((float)((DData.latitudinePartenza-proilati)*(DData.latitudinePartenza-proilati) + (DData.longitudinePartenza-proilongi)*(DData.longitudinePartenza-proilongi)));
							nuovoCliente.setpPrecisione(100000);
							nuovoCliente.setpProvincia("");
							nuovoCliente.setpSpare1(0);
							
							System.out.println("NUOVO"+DData.idClienteSelezionato + "/" + nuovoCliente.getpFree1()+"/" + nuovoCliente.getpFree2());
							
							System.out.println("NUOVO1"+DData.idClienteSelezionato);
							//idCliente = DData.idClienteSelezionato;
							//cliente = nuovoCliente;
							lblIDclienteNumero.setText(idCliente+"");
							if (idCliente<0)
							{	DData.idClienteSelezionato = DataSource.aggiungiCliente(nuovoCliente);
								aggiornaCampiTxt(DData.idClienteSelezionato);
							}else{
								aggiornaCampiTxt(idCliente);
							}
							//aggiornaCampiTxt(DData.idClienteSelezionato);
						}
						ListClient.refreshTabella();
						break;
					case JOptionPane.NO_OPTION:
							
							 aggiornaCampiTxt(idCliente);
						break;
					case JOptionPane.CANCEL_OPTION:
						result = false;
						break;
					

					
					}
				}else{
					
					aggiornaCampiTxt(idCliente);
					
				}
			}
		}else{
			
			aggiornaCampiTxt(idCliente);
			
		}
		return result;
		
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
		    	
		    	 String lingua = Locale.getDefault().getLanguage(); 
                 final Geocoder geocoder = new Geocoder(); 
                 String indirizzo = txtIndirizzio.getText() + " " + txtcitta.getText() + " " + txtnazione.getText();
		    	
		    	 try {
		            	
	                 GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(indirizzo).setLanguage(lingua).getGeocoderRequest(); 
	                 GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest); 
	                 List<GeocoderResult> results = geocoderResponse.getResults(); 
	                 System.err.println(results.size());
	                 float latitude = results.get(0).getGeometry().getLocation().getLat().floatValue(); 
	                 float longitude = results.get(0).getGeometry().getLocation().getLng().floatValue(); 
	                
	                 double latiProi = DGen.distance(latitude, longitude, 0, longitude); 
	                 double longiProi = DGen.distance(latitude, longitude, latitude, 0); 
	                 txtlati.setText(latitude+"");
	                 txtlongi.setText(longitude+"");
	                 cliente.setpLatitudineProi(latiProi);
	                 cliente.setpLongitudineProi(longiProi);
	                 
	                 
				} catch (Exception e) {
					jdp.setVisible(false);
					DGen.showMessage(DString.gAttenzione, DString.msgErroreRecuperoCoordinate);
					// TODO: handle exception
					
				}	
		    	
		    	
		    	System.out.println("fineRicerca");
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

}
