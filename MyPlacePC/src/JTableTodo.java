
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
import java.util.List;
import java.util.Locale;
import java.util.Locale.Category;

import javax.jws.Oneway;
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

public class JTableTodo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTable tableAttivita; // JTable
	private BorderLayout border_layout;
	private long idSelezionatoAncheNonDaMouse; // prende il valore dell'id
												// selezionato verra assegnato
												// solo se c'è un click da
												// maouse per evitare di
												// aggiornare se aggiorno senza
												// click

	static Object[][] dataAtt = null;
	// pulsanti sopra
	public JButton bttCancellaTesto; // ,bttRicerca

	public static JTextField txtRicerca;

	public static JComboBox JcomboSelezione, JcomboOrdinamento;

	public static JPanel UILpanel_top;

	//

	public JTableTodo() {
		// pulsanti sopra tabella

		UILpanel_top = new JPanel(new FlowLayout(FlowLayout.LEFT));

		txtRicerca = new JTextField("", 15);
		txtRicerca.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				MyTableModelAttivitaTodo model2 = (MyTableModelAttivitaTodo) tableAttivita
						.getModel();

				model2.dataTabella = aggiornaDatiTabellaAttivitaTodo(
						JcomboOrdinamento.getSelectedIndex(),
						JcomboSelezione.getSelectedIndex(),
						txtRicerca.getText());
				model2.fireTableDataChanged();

				// refreshTabellaAttivita(JcomboOrdinamento.getSelectedIndex(),JcomboSelezione.getSelectedIndex(),
				// txtRicerca.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {

				MyTableModelAttivitaTodo model2 = (MyTableModelAttivitaTodo) tableAttivita
						.getModel();

				model2.dataTabella = aggiornaDatiTabellaAttivitaTodo(
						JcomboOrdinamento.getSelectedIndex(),
						JcomboSelezione.getSelectedIndex(),
						txtRicerca.getText());
				model2.fireTableDataChanged();

				// refreshTabellaAttivita(JcomboOrdinamento.getSelectedIndex(),JcomboSelezione.getSelectedIndex(),
				// txtRicerca.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		bttCancellaTesto = new JButton(new ImageIcon(getClass().getResource(
				DData.iconaCancellaTesto)));
		bttCancellaTesto.setPreferredSize(new Dimension(DData.dimPulsantiX,
				DData.dimPulsantiy));
		bttCancellaTesto.setToolTipText(DString.ttCancellaRicerca);
		bttCancellaTesto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				txtRicerca.setText("");
				refreshTabellaAttivita(JcomboOrdinamento.getSelectedIndex(),
						JcomboSelezione.getSelectedIndex(),
						txtRicerca.getText());

			}
		});
		JcomboOrdinamento = new JComboBox(DString.jTodoSorting);
		// JcomboOrdinamento.setSelectedIndex(1);//seleziona data crescente
		JcomboOrdinamento.setPrototypeDisplayValue("Data attività Descres"); // imposta
																				// la
																				// larghezza
																				// massima
		JcomboOrdinamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshTabellaAttivita(JcomboOrdinamento.getSelectedIndex(),
						JcomboSelezione.getSelectedIndex(),
						txtRicerca.getText());

			}
		});
		JcomboSelezione = new JComboBox(DString.jTodoSelezione);
		JcomboSelezione.setMaximumRowCount(16);
		JcomboSelezione.setPrototypeDisplayValue("Data attività Descresc"); // imposta
																			// la
																			// larghezza
																			// massima
		JcomboSelezione.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshTabellaAttivita(JcomboOrdinamento.getSelectedIndex(),
						JcomboSelezione.getSelectedIndex(),
						txtRicerca.getText());

			}
		});

		UILpanel_top.add(JcomboOrdinamento);
		UILpanel_top.add(JcomboSelezione);
		UILpanel_top.add(txtRicerca);
		UILpanel_top.add(bttCancellaTesto);

		// UILpanel_top.add(bttRicerca);

		//
		border_layout = new BorderLayout(); // creo il layout manager e lo
											// assegno al container
		setLayout(border_layout);

		String col_names_attivita[] = { DString.attid, DString.attData,
				DString.atttitolo, DString.attFatto, DString.attCliente,
				DString.attDescrizione };
		tableAttivita = new JTable(new MyTableModelAttivitaTodo(
				col_names_attivita, dataAtt));

		MultiButtonRendererAttivitaTodo rendererAttivita = new MultiButtonRendererAttivitaTodo();
		tableAttivita.getColumnModel()
				.getColumn(DData.numeroColonnaConPulsantiAttivitaTodo)
				.setCellRenderer(rendererAttivita);
		tableAttivita.getColumnModel()
				.getColumn(DData.numeroColonnaConPulsantiAttivitaTodo)
				.setCellEditor(new MultiButtonEditorAttivitaTodo());
		// /agiungere testo multiwra,
		jTextAreaRendererTodo renderTesto = new jTextAreaRendererTodo();
		tableAttivita.getColumnModel()
				.getColumn(DData.numeroColonnaConJtextAreaToDo)
				.setCellRenderer(renderTesto);
		tableAttivita.getColumnModel()
				.getColumn(DData.numeroColonnaConJtextAreaToDo)
				.setCellEditor(new JTextAreaEditor());
		// tableAttivita.setPreferredScrollableViewportSize(tableAttivita.getPreferredSize());
		refreshTabellaAttivita(JcomboOrdinamento.getSelectedIndex(),
				JcomboSelezione.getSelectedIndex(), txtRicerca.getText());
		aggiornaDimensioniColonneAttivita();

		tableAttivita.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				DData.idClienteSelezionato = idSelezionatoAncheNonDaMouse;
				if (DData.idClienteSelezionato >= 0) {
					if (tableAttivita.getSelectedColumn() > 0) {

						ClientiPanel
								.aggiornaCliente(DData.idClienteSelezionato);
						try {

						} catch (Exception e1) {
							// per evitare eoore di selecto row -1 se aggiorna
							// da panel clienti
							System.err
									.println("errore nella selezione del cliente da ClientiPanel /"
											+ DData.idClienteSelezionato);

						}

					}

				} else {

					ClientiPanel.aggiornaCliente(DData.idClienteSelezionato);
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
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		tableAttivita.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent arg0) {

						if (arg0.getValueIsAdjusting()) // evita di chiamre
														// l'evento due volta
														// quando perde
														// selezione e quando
														// acquisisce con questo
														// valore rimane
														// l'ultimo se metto
														// !arg0 rimane il primo
						{

							int viewRow = tableAttivita.getSelectedRow();
							int column = tableAttivita.getSelectedColumn();
							String nome = "";
							try {
								System.out.println("257");
								int rigaSelezionato = tableAttivita
										.getSelectedRow();
								System.out.println("259");
								if (rigaSelezionato == -1)
									rigaSelezionato = 0;
								System.out.println("261");
								nome = (String) tableAttivita.getValueAt(
										rigaSelezionato, 4);
								System.out.println("263/" + nome);
								long idTable = (long) tableAttivita.getValueAt(
										rigaSelezionato, 6);
								System.out.println("265");
								idSelezionatoAncheNonDaMouse = (long) tableAttivita
										.getValueAt(rigaSelezionato, 6);
								System.out.println("297");
							} catch (Exception e) {
								System.err.println("Jtable todo errore 269/"
										+ e.getMessage()
										+ "/"
										+ nome
										+ "/"
										+ tableAttivita.getValueAt(
												tableAttivita.getSelectedRow(),
												5));
							}

						}

					}
				});
		// Dimension dimA = new Dimension(15,1);

		JScrollPane panelscroll = new JScrollPane();

		JScrollPane panel_table_bottom = new JScrollPane(tableAttivita);

		add(UILpanel_top, BorderLayout.NORTH);
		add(panel_table_bottom, BorderLayout.CENTER); // aggiungo lo split pane
														// al container

	}

	public static void refreshTabellaAttivita(int ordimaneto, int selezione,
			String ricerca) {

		MyTableModelAttivitaTodo model = (MyTableModelAttivitaTodo) tableAttivita
				.getModel();

		model.dataTabella = aggiornaDatiTabellaAttivitaTodo(
				JcomboOrdinamento.getSelectedIndex(),
				JcomboSelezione.getSelectedIndex(), txtRicerca.getText());
		model.fireTableDataChanged();
	}

	public static void fitToContentWidth(final JTable table, final int column) {
		int width = 0;
		for (int row = 0; row < table.getRowCount(); ++row) {
			final Object cellValue = table.getValueAt(row, column);
			final TableCellRenderer renderer = table.getCellRenderer(row,
					column);
			final Component comp = renderer.getTableCellRendererComponent(
					table, cellValue, false, false, row, column);
			width = Math.max(width, comp.getPreferredSize().width);
		}
		final TableColumn tc = table.getColumn(table.getColumnName(column));
		width += table.getIntercellSpacing().width * 2;
		tc.setPreferredWidth(width);
		tc.setMinWidth(width);
	}

	public void aggiornaDimensioniColonneAttivita() { // totale deve essere 600
		DGen.hideColumn(tableAttivita, 0);

		DGen.deffixWidth(tableAttivita, 1, 60);
		DGen.deffixWidth(tableAttivita, 2, 65);
		DGen.deffixWidth(tableAttivita, 3, 40);
		DGen.fixWidth(tableAttivita, 4, 100);

		DGen.fixWidth(tableAttivita, 5, 225);
		DGen.deffixWidth(tableAttivita, 6, 60);
	}

	public static Object[][] aggiornaDatiTabellaAttivitaTodo(int ordimanetoInt,
			int selezioneFatto, String ricerca) {
		List<Attivita> listaattivita = new ArrayList<Attivita>();

		System.out.println("JTABLETODO AGGIORNA DATI");

		try {
			// paremtri per costruzione query
			String ordinamento = "";
			String colonna = "";
			switch (ordimanetoInt) {
			case 0:
				ordinamento = DData.crescente;
				colonna = mydbhelper.COLUMN_ATTIVITA_DATAATIVITA;
				break;
			case 1:
				ordinamento = DData.decrescente;
				colonna = mydbhelper.COLUMN_ATTIVITA_DATAATIVITA;
				break;
			case 2:
				ordinamento = DData.crescente;
				colonna = mydbhelper.COLUMN_NAME;
				break;
			case 3:
				ordinamento = DData.decrescente;
				colonna = mydbhelper.COLUMN_NAME;
				break;
			default:
				break;
			}

			// int selezioneFatto=1; //00ad fare;1=fatto;2=tutti

			listaattivita = DataSource.getAttivitaClienteNome(ordinamento,
					colonna, selezioneFatto, ricerca);

			// listaClienti = DataSource.getAllClienti(ricerca, 0, 0, 0);
			// //ricerca tipologia lati e longi
		} catch (Exception e) {
			// TODO: handle exception
		}
		Object[][] dataDataBase = new Object[listaattivita.size()][DData.numeroColonneTotalAttivitaToDo];
		if (listaattivita.size() > 0) {
			int i = 0;
			NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
			Locale fmtLocale = Locale.getDefault(Category.FORMAT);
			NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
			DateFormat dateFormatter = DateFormat.getDateInstance(
					DateFormat.SHORT, getDefaultLocale());

			for (i = 0; i < listaattivita.size(); i++) {
				dataDataBase[i][0] = listaattivita.get(i).getpIdAttivita();
				dataDataBase[i][1] = dateFormatter.format(new Date(
						listaattivita.get(i).getpDataAttivita()));

				dataDataBase[i][2] = DGen.restituisciTipoAttivita(listaattivita
						.get(i).getpTipoAttivita());

				if (listaattivita.get(i).getpFattaAttivita() == 0) {
					dataDataBase[i][3] = false;
				} else {
					dataDataBase[i][3] = true;
				}

				dataDataBase[i][4] = listaattivita.get(i)
						.getpNomeClienteAttivita();
				dataDataBase[i][5] = listaattivita.get(i)
						.getpDescrizioneAttività();
				dataDataBase[i][6] = listaattivita.get(i).getpIdCliente(); // metto
																			// nella
																			// colonno
																			// 5
																			// l'idcliente
																			// è
																			// la
																			// colonna
																			// dei
																			// pulsanti

			}

		}
		return dataDataBase;

	}
}

class MyTableModelAttivitaTodo extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String columns_nome[];
	public Object[][] dataTabella;

	public MyTableModelAttivitaTodo(String columns_headers[],
			Object[][] dataTabella) {
		this.columns_nome = columns_headers;
		this.dataTabella = dataTabella;

	}

	public void tableChanged(TableModelEvent e) {
		try {
			super.fireTableChanged(e);

		} catch (IndexOutOfBoundsException ex) {
			// Ignore
		}
	}

	public void removeRow(int row) {

		List<Object[]> lo = new ArrayList<Object[]>(Arrays.asList(dataTabella));
		lo.remove(row);
		dataTabella = lo.toArray(new Object[][] {});

		fireTableDataChanged();
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
			value = DString.attCliente;
			break;
		case 5:
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
		return DData.numeroColonneTotalAttivitaToDo;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		return dataTabella[rowIndex][columnIndex];
	}

	public void removeSelectedRows(JTable table, int row) {
		TabellaList model = (TabellaList) table.getModel();

		model.removeRow(row);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		// or serve se vuoglio aggiungere altra colonna con pulsanti
		/*
		 * if (columnIndex==2) //cliccato su vistato { long idSelezionato =
		 * (long) JTableTodo.tableAttivita.getValueAt(rowIndex, 0); //recuero ID
		 * Attivita atti; try { atti = DataSource.getAttivita(idSelezionato);
		 * int attiFatta = 0; if ((boolean) aValue) attiFatta = 1; //se è vero
		 * allo restituisco 1 else 0 System.err.println("setValueAt/"+ aValue +
		 * "/" +attiFatta); atti.setpFattaAttivita(attiFatta);
		 * DataSource.aggiornaAttivitaID(atti); //aggiorna attivita long
		 * massimaAttivita =
		 * DataSource.massimaDataattivita(atti.getpIdCliente());
		 * DataSource.aggiornaClientedaAttivita(atti.getpIdCliente(),
		 * massimaAttivita); ListClient.refreshTabella(); //aggiorna tabella
		 * lista //non aggiornao la tabella todo } catch (SQLException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * JTableTodo.refreshTabellaAttivita
		 * (JTableTodo.JcomboOrdinamento.getSelectedIndex(),
		 * JTableTodo.JcomboSelezione.getSelectedIndex(),
		 * JTableTodo.txtRicerca.getText()); fireTableCellUpdated(rowIndex,
		 * columnIndex);
		 * 
		 * }
		 */

		if (columnIndex == DData.numeroColonnaConPulsantiAttivitaTodo) {

			if ("editA".equals(aValue)) {

				long idSelezionato = (long) JTableTodo.tableAttivita
						.getValueAt(rowIndex, 0);
				System.err.println("idselezionato/" + idSelezionato);

				// aggiornarattivitae
				int result = DGen.AttivitaAdd(idSelezionato);
				if (result == JOptionPane.OK_OPTION) { // aggiungireferente

					Attivita atti = DGen
							.recuperateAttivitaDaMaschera(idSelezionato);
					DataSource.aggiornaAttivitaID(atti);
					posizioneGps POI = DataSource.getClientID(atti
							.getpIdCliente());
					atti.setpNomeClienteAttivita(POI.getpNome());
					long massimaAttivita = DataSource.massimaDataattivita(atti
							.getpIdCliente());
					// atti.getpdataattività restituisce il valore della data
					// dell'atttività attuale
					if (massimaAttivita > atti.getpDataAttivita()) {
						System.out.println("JREFATT/" + "non aggiornare");
					} else {
						System.out.println("JREFATT/" + "aggiornare");

						DataSource.aggiornaClientedaAttivita(
								atti.getpIdCliente(), atti.getpDataAttivita(),
								atti.getpTipoAttivita());
						ListClient.refreshTabella();
					}

					JTableTodo.refreshTabellaAttivita(
							JTableTodo.JcomboOrdinamento.getSelectedIndex(),
							JTableTodo.JcomboSelezione.getSelectedIndex(),
							JTableTodo.txtRicerca.getText());

				}
			}
			// if ("newA".equals(aValue)) {System.out.println("NEWA"); }
			if ("deleteA".equals(aValue)) {

				long idSelezionato = (long) JTableTodo.tableAttivita
						.getValueAt(rowIndex, 0);

				if (DData.GenChiediCOnfermaPrimacancellare == true) {
					if (DGen.showConfirm(DString.gAttenzione, "1 "
							+ DString.msgcancellazioneAttivita,
							JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						DataSource.cancellaAttivita(idSelezionato);
						System.out.println(" JTABLE REF CANCELLA ATTIVATA/"
								+ idSelezionato
								+ DGen.stampaOraProva(DataSource
										.massimaDataattivita(idSelezionato)));
						long nuovaMassimaattivita = DataSource
								.massimaDataattivita(DData.idClienteSelezionato);
						int tipoattivita = DataSource
								.tipoUltimaAttivita(DData.idClienteSelezionato);
						DataSource.aggiornaClientedaAttivita(
								DData.idClienteSelezionato,
								nuovaMassimaattivita, tipoattivita);
						// ListClient.refreshTabella();
						// JTableTodo.refreshTabellaAttivita(DData.idClienteSelezionato);
						// fireTableDataChanged();

						// fireTableCellUpdated(rowIndex, columnIndex);
						ClientiPanel.aggiornaCliente(DData.idClienteSelezionato);
						ListClient.refreshTabella();
						JTableTodo
								.refreshTabellaAttivita(
										JTableTodo.JcomboOrdinamento
												.getSelectedIndex(),
										JTableTodo.JcomboSelezione
												.getSelectedIndex(),
										JTableTodo.txtRicerca.getText());

					}
				} else {

					DataSource.cancellaAttivita(idSelezionato);
					System.out
							.println("CANCELLA ATTIVATA/"
									+ DData.idClienteSelezionato
									+ DGen.stampaOraProva(DataSource
											.massimaDataattivitaTabellaAttivita(idSelezionato)));
					long nuovaMassimaattivita = DataSource
							.massimaDataattivitaTabellaAttivita(DData.idClienteSelezionato);
					int tipoattivita = DataSource
							.tipoUltimaAttivita(DData.idClienteSelezionato);
					DataSource.aggiornaClientedaAttivita(
							DData.idClienteSelezionato, nuovaMassimaattivita,
							tipoattivita);
					ClientiPanel.aggiornaCliente(DData.idClienteSelezionato);
					ListClient.refreshTabella();
					JTableTodo.refreshTabellaAttivita(
							JTableTodo.JcomboOrdinamento.getSelectedIndex(),
							JTableTodo.JcomboSelezione.getSelectedIndex(),
							JTableTodo.txtRicerca.getText());

				}
			}
			System.out.println("jtabletodo 576");
			dataTabella[rowIndex][columnIndex] = aValue;
			fireTableCellUpdated(rowIndex, columnIndex);
			// remove(value);

		}

	}

	public void add(Attivita value) {
		/*
		 * int startIndex = getRowCount(); data.add(value);
		 * fireTableRowsInserted(startIndex, getRowCount() - 1);
		 */
	}

	public void remove(Attivita value) {
		/*
		 * int startIndex = data.indexOf(value);
		 * System.out.println("startIndexA = " + startIndex);
		 * data.remove(value); fireTableRowsInserted(startIndex, startIndex);
		 */
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		boolean result = false;
		switch (columnIndex) {
		case 2:
			result = true;
			break;
		case DData.numeroColonnaConPulsantiAttivitaTodo:
			result = true;
			break;

		default:
			break;
		}

		return result;

	}

}

class MultiButtonPanelAttivitaTodo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private JButton bttnewA;
	private JButton btteditA, bttdeleteA;

	private String state;

	public MultiButtonPanelAttivitaTodo() {
		setLayout(new GridBagLayout());

		Dimension dim = new Dimension(30, 30);
		btteditA = new JButton(new ImageIcon(getClass().getResource(
				DData.iconaEdit)));
		btteditA.setMaximumSize(dim);
		btteditA.setMinimumSize(dim);
		btteditA.setActionCommand("editA");
		/*
		 * bttnewA = new JButton(new
		 * ImageIcon(getClass().getResource(DData.iconaNuova)));
		 * bttnewA.setMaximumSize(dim); bttnewA.setMinimumSize(dim);
		 * bttnewA.setActionCommand("newA");
		 */
		bttdeleteA = new JButton(new ImageIcon(getClass().getResource(
				DData.iconaCancella)));
		bttdeleteA.setMaximumSize(dim);
		bttdeleteA.setMinimumSize(dim);
		bttdeleteA.setActionCommand("deleteA");

		add(btteditA);
		// add(bttnewA);
		add(bttdeleteA);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state = e.getActionCommand();
				System.out.println("Jtable todo StateA = " + state);
			}
		};

		btteditA.addActionListener(listener);
		// bttnewA.addActionListener(listener);
		bttdeleteA.addActionListener(listener);
	}

	public void addActionListener(ActionListener listener) {
		btteditA.addActionListener(listener);
		// bttnewA.addActionListener(listener);
		bttdeleteA.addActionListener(listener);
	}

	public String getState() {
		return state;
	}
}

class MultiButtonRendererAttivitaTodo extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MultiButtonPanelAttivita multiButtonPanel;

	public MultiButtonRendererAttivitaTodo() {
		multiButtonPanel = new MultiButtonPanelAttivita();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			multiButtonPanel.setBackground(table.getSelectionBackground());
		} else {
			multiButtonPanel.setBackground(table.getBackground());
		}
		return multiButtonPanel;
	}
}

class MultiButtonEditorAttivitaTodo extends AbstractCellEditor implements
		TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MultiButtonPanelAttivita multiButtonPanel;

	public MultiButtonEditorAttivitaTodo() {
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
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (isSelected) {
			multiButtonPanel.setBackground(table.getSelectionBackground());
		} else {
			multiButtonPanel.setBackground(table.getBackground());
		}
		return multiButtonPanel;
	}
}

class jTextAreaRendererTodo implements TableCellRenderer {
	JScrollPane scrollPane;
	JTextArea textArea;

	public jTextAreaRendererTodo() {
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
	}

	private static int countLines(JTextArea textArea) {
		int noLines = 0;
		try { // meso il try erchè altrimenti ogni tanto da errore se non ci
				// sono caratteri
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

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText((String) value);

		// si imposta il setFont perche altrimenti ha un carattere
		// textArea.setFont(DData.FONT);
		// textArea.setFont(new java.awt.Font("Tahoma", 0, 11));
		int fontHeight = textArea.getFontMetrics(textArea.getFont())
				.getHeight();
		int textLength = textArea.getText().length();
		// int lines = (textLength /
		// (table.getColumnModel().getColumn(column).getWidth()/8));//+1, cause
		// we need at least 1 row.
		int lines = countLines(textArea);
		int acapo = DGen.contaACapo(textArea.getText());
		int lineafinale = lines + acapo;

		// System.out.println("CELL RENDERE/" + fontHeight + "/" + textLength +
		// "/" + lines + "___" + countLines(textArea));
		int height = fontHeight * lineafinale + 10 + lines; // lines inserito
															// per aumentare il
															// margine se ci
															// sono molte linee

		table.setRowHeight(row, height);
		return scrollPane;
	}
}

class JTextAreaEditorTodo1 implements TableCellEditor {
	JTextArea textArea;
	JScrollPane scrollPane;

	public JTextAreaEditorTodo1() {
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText((String) value);

		return scrollPane;
	}

	public void addCellEditorListener(CellEditorListener l) {
	}

	public void cancelCellEditing() {
	}

	public Object getCellEditorValue() {
		return textArea.getText();
	}

	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}

	public void removeCellEditorListener(CellEditorListener l) {
	}

	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}

	public boolean stopCellEditing() {
		return true;
	}
}
