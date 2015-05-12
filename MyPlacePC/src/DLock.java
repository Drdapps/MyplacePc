

import java.io.File;


public class DLock {
public static boolean lockedDaDatabase;
public static boolean isLocked=false; //dice se il database è bloccato
public static boolean myLocked =  false; //dioce se è bloccato da me;
public static long dateLocked=0;
public static final long PERIODOLUNGOLOCKED = 1000*60*30;
public static long dataUltimoAvvisoL;
public static long periodoTraAvvisiLocked;
public static final String  PREF_ULTIMO_AVVISO_LOCKED = "dataUltimoAvvisoL";

public static final String FILELOCKED =  "ll.txt";

	
	
	
	
	
}
