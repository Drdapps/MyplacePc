import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import javax.swing.JTextArea;

import org.jdesktop.swingx.JXDatePicker;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;

///IMPORTANTE
//se da errore per mancaza classe inserire la libreria servlet-api_2.5-20081211.jar
//return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

//SE SI TOGLIE L'AUTORIZZAZIONE E' NECESSARIO ELIMINARE I TOKEN enrico/.store/calendar_sample
public class JDialogAggiungiEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;

	
	private JSpinner spOraInizio, spMinutoInizio;
	private JComboBox jcDurata;
	private JTextArea txtDesc;

	private JXDatePicker jpDataInizio;
	
	 private static final String APPLICATION_NAME = "MyCustomerPC";

	  /** Directory to store user credentials. */
	 //file dove vengono immagazzinate le credenziali
	  private static final java.io.File DATA_STORE_DIR =
	      new java.io.File(System.getProperty("user.home"), ".store/calendar_sample");

	  /**
	   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
	   * globally shared instance across your application.
	   */
	  private static FileDataStoreFactory dataStoreFactory;

	  /** Global instance of the JSON factory. */
	  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	  /** Global instance of the HTTP transport. */
	  private static HttpTransport httpTransport;

	  @SuppressWarnings("unused")
	  //nel caso di errore di importaazione importare la libs calendare che si trova in libs in workspace
	  private static Calendar client;

	  /** Authorizes the installed application to access user's protected data. */
	  private static Credential authorize() throws Exception {
	    // load client secrets
	    //GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
	      //  new InputStreamReader(CalendarSample.class.getResourceAsStream("client_secret_183097656372-k9b1uhqeqbmh16uncuc94cm1i9mk1hmp.apps.googleusercontent.com.json")));
	    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
	            new InputStreamReader(JDialogAggiungiEvento.class.getResourceAsStream("/client_secret_183097656372-k9b1uhqeqbmh16uncuc94cm1i9mk1hmp.apps.googleusercontent.com.json")));
	        
	    System.out.println("clientSecrets/"+clientSecrets.getDetails().getClientId().startsWith("Enter "));
	    if (clientSecrets.getDetails().getClientId().startsWith("Enter") ||
	        clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
	      System.out.println(
	          "Overwrite the src/main/resources/client_secrets.json file with the client secrets file "
	          + "you downloaded from the Quickstart tool or manually enter your Client ID and Secret "
	          + "from https://code.google.com/apis/console/?api=calendar#project:846885241864 "
	          + "into src/main/resources/client_secrets.json");
	      
	      System.exit(1);
	    }

	    // Set up authorization code flow.
	    // Ask for only the permissions you need. Asking for more permissions will
	    // reduce the number of users who finish the process for giving you access
	    // to their accounts. It will also increase the amount of effort you will
	    // have to spend explaining to users what you are doing with their data.
	    // Here we are listing all of the available scopes. You should remove scopes
	    // that you are not actually using.
	    
	  
	    Set<String> scopes = new HashSet<String>();
	    scopes.add(CalendarScopes.CALENDAR);
	    scopes.add(CalendarScopes.CALENDAR_READONLY);
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        httpTransport, JSON_FACTORY, clientSecrets, scopes)
	        .setDataStoreFactory(dataStoreFactory)
	        .build();
	    
	    // authorize
	    //se da errore per mancaza classe inserire la libreria servlet-api_2.5-20081211.jar
	    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	  }
	
	
	
	public JDialogAggiungiEvento(long idCliente, long dataEvento, String descrizioneAggiungtiva) {
		
		System.out.println("JDIALOAGADDALARM/"+ idCliente + "/" + dataEvento + "/" + descrizioneAggiungtiva);
		setBounds(300, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel(DString.jdEvTitolo);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtTitle = new JTextField();
			txtTitle.setText(ClientiPanel.txtNome.getText());
			GridBagConstraints gbc_txtTitle = new GridBagConstraints();
			gbc_txtTitle.gridwidth = 11;
			gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTitle.insets = new Insets(0, 0, 5, 5);
			gbc_txtTitle.gridx = 1;
			gbc_txtTitle.gridy = 0;
			contentPanel.add(txtTitle, gbc_txtTitle);
			
		}
		{
			JLabel lblNewLabel_1 = new JLabel(DString.jdEvDescrizione);
			
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			txtDesc = new JTextArea();
			txtDesc.setLineWrap(true);
			txtDesc.setWrapStyleWord(true);
			//txtDesc.setText(ClientiPanel.txtIndirizzio.getText()+ " "+ ClientiPanel.txtcitta.getText()+ " " + ClientiPanel.txtnazione.getText());
			 Border border = BorderFactory.createLineBorder(Color.GRAY);
			 txtDesc.setBorder(border);
			GridBagConstraints gbc_txtDesc = new GridBagConstraints();
			gbc_txtDesc.gridwidth = 11;
			gbc_txtDesc.insets = new Insets(0, 0, 5, 0);
			gbc_txtDesc.fill = GridBagConstraints.BOTH;
			gbc_txtDesc.gridx = 1;
			gbc_txtDesc.gridy = 1;
			
			txtDesc.setText(DString.cliTel + ": " + ClientiPanel.txttel.getText() + " - " + descrizioneAggiungtiva);
			contentPanel.add(txtDesc, gbc_txtDesc);
		}
		{
			JLabel lblNewLabel_2 = new JLabel(DString.jdEvDataInizio);
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 2;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{

	    	Date data  = new Date(dataEvento);
	    	jpDataInizio = new JXDatePicker(data, Locale.getDefault());
	    	
		
			GridBagConstraints gbc_txtdatainizio = new GridBagConstraints();
			gbc_txtdatainizio.gridwidth = 2;
			gbc_txtdatainizio.insets = new Insets(0, 0, 5, 5);
			gbc_txtdatainizio.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtdatainizio.gridx = 1;
			gbc_txtdatainizio.gridy = 2;
			contentPanel.add(jpDataInizio, gbc_txtdatainizio);
			
		}
		{
			
			java.util.Calendar rightNow = java.util.Calendar.getInstance();
			int hour = rightNow.get(java.util.Calendar.HOUR_OF_DAY);
			 SpinnerModel model = new SpinnerNumberModel(hour, -1, 25, 1);
			 spOraInizio = new JSpinner(model);
			 spOraInizio.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					try {
						if (spOraInizio.getValue().equals(-1)) spOraInizio.setValue(23);
						if (spOraInizio.getValue().equals(24)) spOraInizio.setValue(0);
						
						
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					
				}
			});
			{
				JLabel lblNewLabel_3 = new JLabel(DString.jdEvOraInizio);
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
				
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 0;
				gbc_lblNewLabel_3.gridy = 3;
				contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			
			 
			
			
			
			GridBagConstraints gbc_txtorainizio = new GridBagConstraints();
			gbc_txtorainizio.insets = new Insets(0, 0, 5, 5);
			gbc_txtorainizio.gridx = 1;
			gbc_txtorainizio.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtorainizio.gridy = 3;
			Dimension d = spOraInizio.getPreferredSize();
	        d.width = 70;
			spOraInizio.setPreferredSize(d);
			JFormattedTextField ftf = getTextField(spOraInizio);
		      if (ftf != null ) {

		            ftf.setHorizontalAlignment(JTextField.CENTER);
		
		        }
			contentPanel.add(spOraInizio, gbc_txtorainizio);
			
		}
{			java.util.Calendar rightNow = java.util.Calendar.getInstance();
			int minuto = rightNow.get(java.util.Calendar.MINUTE);
			spMinutoInizio = new JSpinner(new SpinnerNumberModel(minuto, -1, 60, 1));
	 		spMinutoInizio.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					try {
						if (spMinutoInizio.getValue().equals(-1)) spMinutoInizio.setValue(59);
						if (spMinutoInizio.getValue().equals(60)) spMinutoInizio.setValue(0);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					
				}
			});
			
			
			
			GridBagConstraints gbc_txtorainizio = new GridBagConstraints();
			gbc_txtorainizio.insets = new Insets(0, 0, 5, 5);
			gbc_txtorainizio.anchor = GridBagConstraints.EAST;
			gbc_txtorainizio.gridx = 2;
			gbc_txtorainizio.gridy = 3;
			Dimension d = spMinutoInizio.getPreferredSize();
	        d.width = 70;
	        spMinutoInizio.setPreferredSize(d);
	        

			JFormattedTextField ftf = getTextField(spMinutoInizio);
		      if (ftf != null ) {

		            ftf.setHorizontalAlignment(JTextField.CENTER);
		
		        }
			contentPanel.add(spMinutoInizio, gbc_txtorainizio);
			
			
		}
		
		{
			JLabel lblDataFine = new JLabel(DString.jdEvDurata);
			GridBagConstraints gbc_lblDataFine = new GridBagConstraints();
			gbc_lblDataFine.anchor = GridBagConstraints.WEST;
			gbc_lblDataFine.insets = new Insets(0, 0, 5, 5);
			gbc_lblDataFine.gridx = 0;
			gbc_lblDataFine.gridy = 4;
			contentPanel.add(lblDataFine, gbc_lblDataFine);
		}
		{
			jcDurata = new JComboBox(DData.durataEvento);
			GridBagConstraints gbc_spnDurata = new GridBagConstraints();
			gbc_spnDurata.gridwidth = 2;
			gbc_spnDurata.insets = new Insets(0, 0, 0, 5);
			gbc_spnDurata.fill = GridBagConstraints.HORIZONTAL;
			gbc_spnDurata.gridx = 1;
			gbc_spnDurata.gridy = 4;
			contentPanel.add(jcDurata, gbc_spnDurata);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(DString.gSalva);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Task task = new Task();
						  
						    task.execute();
						
						
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(DString.gAnnulla);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	
	public JFormattedTextField getTextField(JSpinner spinner) {
	        JComponent editor = spinner.getEditor();
	        if (editor instanceof JSpinner.DefaultEditor) {
	            return ((JSpinner.DefaultEditor) editor).getTextField();
	        } else {
	            System.err.println("Unexpected editor type: " + spinner.getEditor().getClass() + " isn't a descendant of DefaultEditor");
	            return null;

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
		    	
		    	 long datainMillis = jpDataInizio.getDate().getTime();
		    	 
		    	 	int ora = (Integer) spOraInizio.getValue();
		    	 	int minuto = (Integer) spMinutoInizio.getValue();
					
					
					
					
					long orainMillis = ora *3600*1000+ minuto*60000; 
					
					
					long durata = DData.durataEventoMillis[jcDurata.getSelectedIndex()];
					
					long dataInizio = orainMillis + datainMillis;
					System.out.println("JDIALOAD EVENTO/" +ora+"/"+minuto+"/___/" + DGen.stampaOraMinutiProva(dataInizio));
					long dataFinale = dataInizio+durata;
					
					try {
					      // initialize the transport
					      httpTransport = GoogleNetHttpTransport.newTrustedTransport();

					      // initialize the data store factory
					      dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

					      // authorization
					      Credential credential = authorize();

					      // set up global Calendar instance
					      client = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
					          .setApplicationName(APPLICATION_NAME).build();
					      		
					      		Event event = new Event();
					    	    event.setSummary(txtTitle.getText());
					    	    event.setDescription(txtDesc.getText());
					    	    event.setLocation(ClientiPanel.txtIndirizzio.getText()+ " "+ ClientiPanel.txtcitta.getText()+ " " + ClientiPanel.txtnazione.getText());
					    	    Date startDate = new Date(dataInizio);
					    	    Date endDate = new Date(dataFinale);
					    	    DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
					    	    event.setStart(new EventDateTime().setDateTime(start));
					    	    DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
					    	    event.setEnd(new EventDateTime().setDateTime(end));
					    	    Event result = client.events().insert("primary", event).execute();
					    	   // Event result = client.events().insert(calendar.getId(), event).execute();
					      
					
					      

					
					    } catch (IOException e) {
					      System.err.println(e.getMessage()+"OOPP");
					      DGen.showMessage(DString.gAttenzione, DString.msgErroreCalendario);
					    } catch (Throwable t) {
					    	 System.err.println("OOPP1");
					      t.printStackTrace();
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

}
