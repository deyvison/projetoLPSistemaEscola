import java.util.LinkedList;
import java.util.List;
public class Professor extends Pessoa {
	
	public static final int PREF_P1=1;
	public static final int PREF_P2=2;
	public static final int PREF_P3=3;
	public static final int PREF_NP=4;
	
	private List<Turma> turmasAlocado;
	private List<Disciplina> listaDisciplinasP1;
	private List<Disciplina> listaDisciplinasP2;
	private List<Disciplina> listaDisciplinasP3;
	private List<Disciplina> listaDisciplinasNP;
	
	public Professor(String nome, String matricula){
		super(nome,matricula);
		this.turmasAlocado = new LinkedList<Turma>();
		this.listaDisciplinasP1= new LinkedList<Disciplina>();
		this.listaDisciplinasP2= new LinkedList<Disciplina>();
		this.listaDisciplinasP3= new LinkedList<Disciplina>();
		this.listaDisciplinasNP= new LinkedList<Disciplina>();
	}
	public Professor(){
		this("Sem nome","Sem matricula");
	}
	
	
	public String getDescricao() {
		// TODO Auto-generated method stub
		return "Professor: "+super.getNome()+", Siape: "+super.getMatricula();
	}
	
	public void adicionaPreferenciaDisciplina(Disciplina d, int nivelPreferencia)
	throws PreferenciaInvalidaException{
		if(this.PREF_P1==nivelPreferencia){
			this.listaDisciplinasP1.add(d);
		}else if(this.PREF_P2==nivelPreferencia){
			this.listaDisciplinasP2.add(d);
		}else if(this.PREF_P3==nivelPreferencia){
			this.listaDisciplinasP3.add(d);
		}else if(this.PREF_NP==nivelPreferencia){
			this.listaDisciplinasNP.add(d);
		}else{
			throw new PreferenciaInvalidaException("Preferência inválida.");
		}
	}
	
	public List<Disciplina> getDisciplinasPreferidasComNivel(int nivelPreferencia)
	throws PreferenciaInvalidaException{
		if(this.PREF_P1==nivelPreferencia){
			return this.listaDisciplinasP1;
		}else if(this.PREF_P2==nivelPreferencia){
			return this.listaDisciplinasP2;
		}else if(this.PREF_P3==nivelPreferencia){
			return this.listaDisciplinasP3;
		}else if(this.PREF_NP==nivelPreferencia){
			return this.listaDisciplinasNP;
		}else{
			throw new PreferenciaInvalidaException("Preferência inválida.");
		}
		
	}
	
	public String toString(){

		String txt = getDescricao()+"\n";
		txt+="Disciplinas P1:"+getListaNomesDisciplinas(this.listaDisciplinasP1);		
		txt+="\nDisciplinas P2:"+getListaNomesDisciplinas(this.listaDisciplinasP2);
		txt+="\nDisciplinas P3:"+getListaNomesDisciplinas(this.listaDisciplinasP3);
		txt+="\nDisciplinas NP:"+getListaNomesDisciplinas(this.listaDisciplinasNP);
		return txt;
	}
	public String getListaNomesDisciplinas(List < Disciplina > lista){
		String listaNomes="";
		for (int k=0; k< lista.size(); k++){
			Disciplina d  = lista.get(k);
			listaNomes+= d.getNome();
			if (k!= lista.size()-1){
				listaNomes+=",";
			}
		}
		return listaNomes;
	}
	
	public String getTextoPreferenciasDisciplinasComCodigo(){
		String texto ="";
		texto +="P1:";
		texto +=this.getListaCodigosDisciplinas(listaDisciplinasP1);
		texto +="\nP2:";
		texto +=this.getListaCodigosDisciplinas(listaDisciplinasP2);
		texto +="\nP3:";
		texto +=this.getListaCodigosDisciplinas(listaDisciplinasP3);
		texto +="\nNP:";
		texto +=this.getListaCodigosDisciplinas(listaDisciplinasNP);
		
		return texto;
		
		
	}
	public String getListaCodigosDisciplinas(List <Disciplina> lista){
		String retorno ="";
		
		for(int i = 0;i<lista.size();i++){
			
			retorno+=(lista.get(i).getCodigo());
			if(i!=lista.size()-1){
				retorno +=",";
			}
		}
		return retorno;
		
		
	}
	
	public void alocaTurma(Turma turma){
		this.turmasAlocado.add(turma);
	}
	public void desalocaTurma(Turma turma){
		this.turmasAlocado.remove(turma);
	}
	public List<Turma> getTurmasAlocado(){
		return this.turmasAlocado;
	}
	public List<Disciplina> getDisciplinasAlocado(){
		List <Disciplina> retorno = new LinkedList<Disciplina>();
		for(Turma t : this.turmasAlocado){
			if(!retorno.contains(t.getDisciplina())){
				retorno.add(t.getDisciplina());
			}
			
		}
		return retorno;
	}
	public List<Horario> getHorarios(){
		List<Horario> retorno = new LinkedList<Horario>();
		for(Turma t : this.turmasAlocado){
			List<Horario> horarios =(t.getHorarios());
			for(Horario h : horarios){
				retorno.add(h);
			}
		}
		return retorno;
	}
	
	
}
