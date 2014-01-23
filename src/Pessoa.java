
public abstract class Pessoa {
	private String nome;
	private String matricula;
	
	public Pessoa(String nome,String matricula){
		this.nome=nome;
		this.matricula=matricula;
	}
	public String getNome(){
		return this.nome;
	}
	public String getMatricula(){
		return this.matricula;
	}
	public void setNome(String nome){
		this.nome=nome;
	}
	public void setMatricula(String matricula){
		this.matricula=matricula;
	}
	public abstract String getDescricao();
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	

}
