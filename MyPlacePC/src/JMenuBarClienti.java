
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class JMenuBarClienti extends JMenuBar
{
    // componenti dei menu
    private JMenuBar menu_bar;
    private JMenu file_menu;
    private JMenu setting_menu;
    private JMenu help_menu;
    
    private JMenuItem mApri, mEsci, mPreferennza, mPosizione, mHelp, mAbout;
  

    public JMenuBarClienti()
    {
        
        
    	  //creo i menu;
        file_menu = new JMenu(DString.mFile);
        setting_menu = new JMenu(DString.mSetting);
        help_menu = new JMenu(DString.mHelp);
       
        
        //menu file
       mApri = new JMenuItem(DString.mApri, new ImageIcon(LayoutPC.class.getResource(DData.iconaMenuApri)));
       mEsci = new JMenuItem(DString.mEsci, new ImageIcon(LayoutPC.class.getResource(DData.iconaMenuEsci)));
       file_menu.add(mApri);
       file_menu.add(mEsci);
        //menu preferenze
       mPreferennza = new JMenuItem(DString.mSetting); 
       mPosizione = new JMenuItem(DString.mCampbiaPosizioneRiferimento);
      
       setting_menu.add(mPreferennza);
       setting_menu.add(mPosizione);
       
        //menu help
       mHelp = new JMenuItem(DString.mHelp); 
       mAbout = new JMenuItem(DString.mAbout); 
       help_menu.add(mHelp);
       help_menu.add(mAbout); 
       
       
       //creo la  menubar
       menu_bar = new JMenuBar(); // creo la menu bar
        // aggiungo alla menu bar i menu
        menu_bar.add(file_menu);
        menu_bar.add(setting_menu);
        menu_bar.add(help_menu);
        
        
    }

 }
