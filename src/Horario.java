
public class Horario {

	/**
	 * @param args
	 */
	public static final String DOMINGO = "DOM";
	public static final String SEGUNDA = "SEG";
	public static final String TERCA = "TER";
	public static final String QUARTA = "QUAR";
	public static final String QUINTA = "QUIN";
	public static final String SEXTA ="SEX";
	public static final String SABADO = "SAB";
	
	private String diaDaSemana;
	private int horaInicio;
	private int horaFim;
	
	
	public Horario(String diaDaSemana,int horaInicio,int horaFim){
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		
		if(this.DOMINGO.equalsIgnoreCase(diaDaSemana)){
			this.diaDaSemana = this.DOMINGO;
		}else if(this.SEGUNDA.equalsIgnoreCase(diaDaSemana)){
			this.diaDaSemana = this.SEGUNDA;
		}else if(this.TERCA.equalsIgnoreCase(diaDaSemana)){
			this.diaDaSemana = this.TERCA;
		}else if(this.QUARTA.equalsIgnoreCase(diaDaSemana)){
			this.diaDaSemana = this.QUARTA;
		}else if(this.QUINTA.equalsIgnoreCase(diaDaSemana)){
			this.diaDaSemana = this.QUINTA;
		}else if(this.SEXTA.equalsIgnoreCase(diaDaSemana)){
			this.diaDaSemana = this.SEXTA;
		}else {
			this.diaDaSemana = this.SABADO;
		}
		
		
	}
	public Horario(){
		this("Sem diaDaSemana",0,0);
	}
	
	public String getDiaDaSemana(){
		return this.diaDaSemana;
	}
	public void setDiaDasemana(String diaDaSemana){
		this.diaDaSemana = diaDaSemana;
	}
	public int getHoraInicio(){
		return this.horaInicio;
	}
	public void setHoraInicio(int horaInicio){
		this.horaInicio = horaInicio;
	}
	public int getHoraFim(){
		return this.horaFim;
	}
	public void setHoraFim(int horaFim){
		this.horaFim =  horaFim;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Horario other = (Horario) obj;
		if (diaDaSemana == null) {
			if (other.diaDaSemana != null)
				return false;
		} else if (!diaDaSemana.equals(other.diaDaSemana))
			return false;
		if (horaFim != other.horaFim)
			return false;
		if (horaInicio != other.horaInicio)
			return false;
		return true;
	}
	public String toString(){
		return "Dia:"+this.diaDaSemana+" Das"+this.horaInicio+" horas às "+this.horaFim+" horas.";
	}
	
	

}
