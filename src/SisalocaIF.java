import java.io.IOException;
import java.util.List;


public interface SisalocaIF {
	public void cadastraProfessor(String nome,String matricula)throws ProfessorJaExisteException;
	
	public void cadastraAluno(String nome,String matricula)throws AlunoJaExisteException;
	
	public void removeProfessor(String matricula)throws ProfessorInexistenteException;
	
	public List<Professor> pesquisaProfessorPeloNome(String nome);
	
	public Professor pesquisaProfessorPelaMatricula(String matricula)throws ProfessorInexistenteException;
	
	public List<Aluno> pesquisaAlunoPeloNome(String nome);
	
	public Aluno pesquisaAlunoPelaMatricula(String matricula)throws AlunoInexistenteException;
	
	public void removeAluno(String matricula)throws AlunoInexistenteException;
	
	public void adicionaDisciplina(String nome, String codigo)throws DisciplinaJaExisteException;
	
	public void removeDisciplina(String codigo)throws DisciplinaInexistenteException;
	
	public Disciplina pesquisaDisciplina(String codigo)throws DisciplinaInexistenteException;
	
	public void adicionaTurma(String codigoDisciplina, int numTurma)
			throws DisciplinaInexistenteException,TurmaJaExisteException;
	
	public void removeTurma(String codigoDisciplina, int numTurma)throws DisciplinaInexistenteException;
	
	public void cadastraNivelDeInteresseDeProfessorPorDisciplina(String matriculaProf,String codDisciplina,
			int nivelPreferencia) throws PreferenciaInvalidaException, DisciplinaInexistenteException,
			ProfessorInexistenteException;
	
	public List <Disciplina> consultaDisciplinasPorPreferenciaPorProfessor(String matriculaProfessor, int nivelPreferencia)
			throws ProfessorInexistenteException, PreferenciaInvalidaException;
	
	public List <Professor> obterListaDeProfessores(); 
	
	public List <Disciplina> obterListaDeDisciplinas();   
	
	
	public void carregarProfessoresDeArquivo(String nomeArquivo)throws ProfessorJaExisteException,
	IOException;
	
	public void carregarDisciplinasDeArquivo(String nomeArquivo)throws DisciplinaJaExisteException,
	IOException;
	
	public void gravarProfessoresEmArquivo(String nomeArquivo)throws IOException;
	
	public void gravarDisciplinasEmArquivo(String nomeArquivo)throws IOException;
	
	public void gravarTurmasDeDisciplinasEmArquivo(String nomeArquivo)throws IOException;
	
	public void carregarTurmasDeDisciplinasEmArquivo(String nomeArquivo)throws IOException,
	DisciplinaInexistenteException, TurmaJaExisteException;
	
	public void imprimeProfessoresEDisciplinas();
	
	public void gravaInteressesDeProfessoresPorDisciplinasEmArquivo(String nomeArquivo) throws  IOException;
	
	public void carregaInteressesDeProfessoresPorDisciplinasDeArquivo(String nomeArquivo) throws PreferenciaInvalidaException, 
	ProfessorInexistenteException, DisciplinaInexistenteException, IOException;
	
	public Turma pesquisaTurma(String codDisciplina, int numTurma) throws DisciplinaInexistenteException, TurmaInexistenteException;
	
	public void cadastraHorarioTurma(String codDisciplina, int numTurma, String dia, int horaInicio, int horaFim) throws DisciplinaInexistenteException
	, TurmaInexistenteException;
	
	public void alocaProfessorATurma(String codDisciplina, int numTurma, String matriculaProf) throws DisciplinaInexistenteException, 
	TurmaInexistenteException, ProfessorInexistenteException;
	
//Lembre que nesse método vc deve configurar o professor da turma (turma.setProfessor(...)) e deve também adicionar essa turma 
//à lista de turmas às quais o professor está alocado (p.alocaTurma(...))
	
	public void desalocaProfessorDeTurma(String codDisciplina, int numTurma, String matriculaProf) throws DisciplinaInexistenteException, TurmaInexistenteException,
	ProfessorInexistenteException;
	
//Lembre de desfazer o que o método aloca faz, inclusive de chamar turma.setProfessor(null).
	
	
	public List < Horario > pesquisaHorariosProfessor(String matriculaProf) throws ProfessorInexistenteException;
	
	public List < Turma > pesquisaTurmasDoProfessor(String matriculaProf) throws ProfessorInexistenteException;
	
	public List < Disciplina > pesquisaDisciplinasDoProfessor(String matriculaProf) throws ProfessorInexistenteException;



}
