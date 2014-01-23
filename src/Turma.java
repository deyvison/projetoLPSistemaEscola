import java.util.LinkedList;
import java.util.List;


public class Turma {
	private int numero;
	private Disciplina disciplina;
	Professor professor;
	List <Horario> horarios;
	
	public Turma(Disciplina disciplina,int numero){
		this.numero=numero;
		this.disciplina=disciplina;
		this.professor = null;
		horarios = new LinkedList<Horario>();
		
	}
	public int getNumero(){
		return this.numero;
	}
	public void setNumero(int numero){
		this.numero=numero;
	}
	public Disciplina getDisciplina(){
		return this.disciplina;
	}
	public void setDisciplina(Disciplina disciplina){
		this.disciplina=disciplina;
	}
	
	public String toString(){
		
		String texto = "Turma número: "+this.getNumero() + "Da disciplina:"+ this.disciplina.getNome();
		return texto;
	}
	public Professor getProfessor(){
		return this.professor;
	}
	public void setProfessor(Professor professor){
		this.professor =  professor;
	}
	public List<Horario> getHorarios(){
		return this.horarios;
	}
	public void setHorarios(List<Horario> horarios){
		this.horarios = horarios;
	}
	public void adicionarHorario(Horario horario){
		this.horarios.add(horario);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (horarios == null) {
			if (other.horarios != null)
				return false;
		} else if (!horarios.equals(other.horarios))
			return false;
		if (numero != other.numero)
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		return true;
	}
	

}
