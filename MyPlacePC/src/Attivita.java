



public class Attivita  {
	private long pIdAttivita;
	private long pIdCliente;
	private long pDataAttivita;
	private String pDescrizioneAttivita;
	
	//non ancora utlizzati //da implemtare metodi
	private long pIdReferente;
	private long pIdTipoattivita;
	private long pDataAllarme;
	private int c1;
	private int c2; //usato per attività 0 telefonata, 1 visita, 2email 3 altro
	private String c3; //usa per il nome del cliente in jTabletodo
	
	public long getpIdCliente (){
		return pIdCliente;
	}
	public void setpIdCliente (long pidcliente){
		this.pIdCliente = pidcliente;
	}
	public long getpIdAttivita (){
		return pIdAttivita;
	}
	public void setpIdAttivita (long pIdPos){
		this.pIdAttivita = pIdPos;
	}
	public long getpDataAttivita (){
		return pDataAttivita;
	}
	public void setpDataAttivita (long pdata){
		this.pDataAttivita = pdata;
	}
	public String getpDescrizioneAttività (){
		return pDescrizioneAttivita;
	}
	public void setpDescrizioneAttivita (String pdes){
		this.pDescrizioneAttivita = pdes;
	}
	
	public int getpFattaAttivita (){
		return c1;
	}
	public void setpFattaAttivita (int pdata){
		this.c1 = pdata;
	}
	
	//utilizzo il campo 2 per mettere il nome nel todo.
	public String getpNomeClienteAttivita (){
		return c3;
	}
	public void setpNomeClienteAttivita (String nomeC){
		this.c3 = nomeC;
	}
	
	
	public int getpTipoAttivita (){
		return c2;
	}
	public void setpTipoAttivita (int visi){
		this.c2 = visi;
	}
	
	
	
	public Attivita setpAttivita(long idattività, long dataAttivita, String descrizioneAttivita, int fattaAttivita, int visita)
	{
		Attivita result = new Attivita();
		result.setpIdAttivita(idattività);
		result.setpDataAttivita(dataAttivita);
		result.setpDescrizioneAttivita(descrizioneAttivita);
		result.setpFattaAttivita(fattaAttivita);
		result.setpTipoAttivita(visita);
		return result;
		
	}
		 
}



	

