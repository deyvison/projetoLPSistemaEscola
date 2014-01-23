import java.util.LinkedList;
import java.util.List;


public class Disciplina {
	
	private String nome;
	private String codigo;
	List<Turma> turmas;
	
	public Disciplina(String nome,String codigo){
		this.nome=nome;
		this.codigo=codigo;
		turmas = new LinkedList<Turma>();
	}
	public String getNome(){
		return this.nome;
	}
	public void setNome(String nome){
		this.nome=nome;
	}
	public String getCodigo(){
		return this.codigo;
	}
	public void setCodigo(String codigo){
		this.codigo=codigo;
	}
	public void adicionaTurma(int numero)throws TurmaJaExisteException{
		boolean existe=false;
		for(Turma t : this.turmas){
			if(t.getNumero()==numero){
				existe=true;
				break;
			}
		}
		if(existe){
			throw new TurmaJaExisteException("Turma de número:"+numero+"Já existe.");
		}
		else{
			Turma t = new Turma(this,numero);
			this.turmas.add(t);
		}
		
	}
	public void removeTurma(int numero){
		for(Turma t : turmas){
			if(t.getNumero()==numero){
				this.turmas.remove(t);
				break;
			}
		}
	}
	public List<Turma> getTurmas(){
		return this.turmas;
	}
	
	public String toString(){
		String texto ="Disciplina: "+this.nome+"Código:" +this.codigo +"Turmas:";
		
		for(Turma t : this.turmas){
			texto +="\n"+t.toString();
		}
		return texto;


	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (turmas == null) {
			if (other.turmas != null)
				return false;
		} else if (!turmas.equals(other.turmas))
			return false;
		return true;
	}
	
	

}
