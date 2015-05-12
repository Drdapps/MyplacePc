package it.drd.uuultimatemyplace;

public class Attivita  {
	private long pIdAttivita;
	private long pIdCliente;
	private long pDataAttivita;
	private String pDescrizioneAttivita;
	
	//non ancora utlizzati //da implemtare metodi
	private long pIdReferente;
	private long pIdTipoattivita;
	private long pDataAllarme;
	private String c1;
	private String c2;
	private String c3;
	
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
	
	public Attivita setpAttivita(long idattività, long dataAttivita, String descrizioneAttivita)
	{
		Attivita result = new Attivita();
		result.setpIdAttivita(idattività);
		result.setpDataAttivita(dataAttivita);
		result.setpDescrizioneAttivita(descrizioneAttivita);
		
		
		return result;
		
	}
		 
}



	

