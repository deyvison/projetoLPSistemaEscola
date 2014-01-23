
public class Aluno extends Pessoa {
	public Aluno(String nome, String matricula){
		super(nome,matricula);
	}
	public String getDescricao(){
		return "Aluno: "+super.getNome()+", Matricula: "+super.getMatricula();
	}
	
	public String toString(){
		return this.getDescricao();
	}

}
