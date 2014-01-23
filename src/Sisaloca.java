import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;


public class Sisaloca implements SisalocaIF {
	
	private List<Professor> professores;
	private List<Aluno> alunos;
	private List<Disciplina>disciplinas;


	public Sisaloca(){
		this.professores = new LinkedList<Professor>();
		this.alunos = new LinkedList<Aluno>();
		this.disciplinas = new LinkedList<Disciplina>();
		
	}
	public void cadastraProfessor(String nome, String matricula)throws ProfessorJaExisteException{
		boolean existe = false;
		for(Professor p : professores){
			if(p.getMatricula().equals(matricula)){
				existe=true;
				break;
			}
		}
		if(!existe){
			Professor a = new Professor(nome,matricula);
			this.professores.add(a);
		}else{
			throw new ProfessorJaExisteException("Professor já existente.");
		}
	}	
				
		
	

	
	public void cadastraAluno(String nome, String matricula)throws AlunoJaExisteException {
		boolean existe=false;
		for(Aluno a : alunos){
			if(a.getMatricula().equals(matricula)){
				existe=true;
				break;
			}
		}
		if(!existe){
			Aluno e = new Aluno(nome,matricula);
			this.alunos.add(e);
		}else{
			throw new AlunoJaExisteException("Aluno já existente.");
		}
			
		
		
		
	}
	public void removeProfessor(String matricula)throws ProfessorInexistenteException{
		boolean removeu=false;
		for(Professor p : professores){
			if(p.getMatricula().equals(matricula)){
				professores.remove(p);
				removeu=true;
				break;
			}
		}
		if(!removeu){
			throw new ProfessorInexistenteException("Professor inexistente.");
		}
	}
	
		
		
		
	

	
	public List<Professor> pesquisaProfessorPeloNome(String nome) {
		List<Professor> retorno = new LinkedList<Professor>();
		for(Professor p : this.professores){
			int resultado = p.getNome().indexOf(nome);
			if(resultado != -1){
				retorno.add(p);
			}
		}
		return retorno;
		
		
	}

	
	public Professor pesquisaProfessorPelaMatricula(String matricula)throws ProfessorInexistenteException {
		for(Professor p : this.professores){
			if(p.getMatricula().equals(matricula)){
				return p;
			}
		}
		throw new ProfessorInexistenteException("Professor inexistente.");
	}

	
	public List<Aluno> pesquisaAlunoPeloNome(String nome) {
		List<Aluno> retorno = new LinkedList<Aluno>();
		for(Aluno a : this.alunos){
			int resultado=a.getNome().indexOf(nome);
			if(resultado != -1){
				retorno.add(a);
			}
		}
		return retorno;
	}

	
	public Aluno pesquisaAlunoPelaMatricula(String matricula)throws AlunoInexistenteException {
		for(Aluno a : this.alunos){
			if(a.getMatricula().equals(matricula)){
				return a;
			}
		}
		throw new AlunoInexistenteException("Aluno inexistente.");
	}
	public void removeAluno(String matricula)throws AlunoInexistenteException{
		boolean removeu=false;
		for(Aluno a : alunos){
			if(a.getMatricula().equals(matricula)){
				alunos.remove(a);
				removeu=true;
				break;
			}
		}
		if(!removeu){
			throw new AlunoInexistenteException("Aluno inexistente.");
		}
	}
	
	public void adicionaDisciplina(String nome, String codigo)
			throws DisciplinaJaExisteException {
		boolean existe=false;
		for(Disciplina d : this.disciplinas){
			if(d.getCodigo().equals(codigo)){
				existe=true;
				break;
			}
		}
		if(!existe){
			Disciplina a = new Disciplina(nome,codigo);
			this.disciplinas.add(a);
		}else{
			throw new DisciplinaJaExisteException("Disciplina já existe.");
		}
		
		
	}
	
	public void removeDisciplina(String codigo)
			throws DisciplinaInexistenteException {
		boolean removeu=false;
		for(Disciplina d : disciplinas){
			if(d.getCodigo().equals(codigo)){
				disciplinas.remove(d);
				removeu=true;
				break;
			}
		}
		if(!removeu){
			throw new DisciplinaInexistenteException("Disciplina inexistente.");
		}
		
	}
	@Override
	public Disciplina pesquisaDisciplina(String codigo)
			throws DisciplinaInexistenteException {
		for(Disciplina d : disciplinas){
			if(d.getCodigo().equals(codigo)){
				return d;
			}
		}
		throw new DisciplinaInexistenteException ("Disciplina inexistente.");


	}
	
	public void adicionaTurma(String codigoDisciplina, int numTurma)
			throws DisciplinaInexistenteException,TurmaJaExisteException{
		boolean existe=false;
		for(Disciplina p : disciplinas){
			if(p.getCodigo().equals(codigoDisciplina)){
				p.adicionaTurma(numTurma);
				existe=true;
				break;
			}
		}
		if(!existe){
			throw new DisciplinaInexistenteException("Disciplina inexistente.");
		}
		
	}
	@Override
	public void removeTurma(String codigoDisciplina, int numTurma)
			throws DisciplinaInexistenteException {
		boolean removeu=false;
		for(Disciplina d : disciplinas){
			if(d.getCodigo().equals(codigoDisciplina)){
				d.removeTurma(numTurma);
				removeu=true;
				break;
			}
		}
		if(!removeu){
			throw new DisciplinaInexistenteException("Disciplina Inexistente.");
		}
		
	}
	public void cadastraNivelDeInteresseDeProfessorPorDisciplina(String matriculaProf,String codDisciplina,
			int nivelPreferencia) throws PreferenciaInvalidaException,ProfessorInexistenteException,
			DisciplinaInexistenteException{
		Professor prof = this.pesquisaProfessorPelaMatricula(matriculaProf);
		Disciplina dis = this.pesquisaDisciplina(codDisciplina);
		prof.adicionaPreferenciaDisciplina(dis, nivelPreferencia);
	}
	@Override
	public List<Disciplina> consultaDisciplinasPorPreferenciaPorProfessor(
			String matriculaProfessor, int nivelPreferencia)
			throws ProfessorInexistenteException, PreferenciaInvalidaException {
		Professor prof = this.pesquisaProfessorPelaMatricula(matriculaProfessor);
		List<Disciplina> d= prof.getDisciplinasPreferidasComNivel(nivelPreferencia);
		return d;
	}
	public List <Professor> obterListaDeProfessores(){
		return this.professores;//retorna a lista de todos os professores
	}
	public List <Disciplina> obterListaDeDisciplinas(){
		return this.disciplinas;//retorna a lista de todas as disciplinas
	}
	
	public void carregarProfessoresDeArquivo(String nomeArquivo)
			throws ProfessorJaExisteException, IOException {
		BufferedReader leitor = null;
		try {
			leitor = new BufferedReader(new FileReader(nomeArquivo));
			String nomeProf = null;
			do {
				nomeProf = leitor.readLine(); // lê a próxima linha do arquivo, nome do professor
				
				if(nomeProf != null){
					String matriculaProf = leitor.readLine(); //Lê a próxima linha do arquivo, matrícula do professor
					this.cadastraProfessor(nomeProf, matriculaProf);
				}
				
			} while(nomeProf != null); //vai ser null quando chegar no fim do arquivo
		} finally {
			if (leitor!=null){
				leitor.close();
			}
		}
	}
	
	
	public void carregarDisciplinasDeArquivo(String nomeArquivo)
			throws DisciplinaJaExisteException, IOException{
		BufferedReader leitor = null;
		try {
			leitor = new BufferedReader(new FileReader(nomeArquivo));
			String nomeDisciplina= null;
			do {
				nomeDisciplina = leitor.readLine();
				
				if(nomeDisciplina != null){
					String codigoDisciplina = leitor.readLine();
					this.adicionaDisciplina(nomeDisciplina, codigoDisciplina);
				}
			} while (nomeDisciplina != null);
			} finally {
				if(leitor != null){
					leitor.close();
				}
				
			}
	}
	
	public void gravarDisciplinasEmArquivo(String nomeArquivo)throws IOException{
		PrintWriter gravador = null;
		
		try{
			gravador = new PrintWriter(new FileWriter(nomeArquivo));
			for(Disciplina disciplina : this.disciplinas){
				gravador.println(disciplina.getNome());
				
				gravador.println(disciplina.getCodigo());
				
			}
		} finally {
			if(gravador != null){
				gravador.close();
			}
		}
	}
	
	public void gravarProfessoresEmArquivo(String nomeArquivo)throws IOException{
		PrintWriter escritor = null;
		
		try{
			FileWriter file = new FileWriter(nomeArquivo);
			escritor = new PrintWriter(file);
			
			for(Professor professor : this.professores){
				escritor.println(professor.getNome());
				
				escritor.println(professor.getMatricula());
				
			}
			
		} finally {
			if(escritor != null){
				escritor.close();
			}
		}
	}
	
	public void gravarTurmasDeDisciplinasEmArquivo(String nomeArquivo)throws IOException{
		PrintWriter gravador = null;
		
		try{
			FileWriter file = new FileWriter(nomeArquivo);
			gravador = new PrintWriter(file);
			
			for(Disciplina disciplina : this.disciplinas){
				gravador.println(disciplina.getCodigo());
				
				int tamanho =disciplina.getTurmas().size();
				gravador.println(tamanho);
				
				
				for(int k=0;k<disciplina.getTurmas().size();k++){
					Turma t = disciplina.getTurmas().get(k);
					gravador.println(t.getNumero());
					
				}
				
			}
		} finally {
			if(gravador != null){
				gravador.close();
			}
		}
	}
	
	public void carregarTurmasDeDisciplinasEmArquivo(String nomeArquivo)throws IOException,
	DisciplinaInexistenteException, TurmaJaExisteException{
	
		BufferedReader leitor = null;
		
		try{
			FileReader file = new FileReader(nomeArquivo);
			leitor = new BufferedReader(file);
			
			String codigoDisciplina = null;
			
			do{
				codigoDisciplina = leitor.readLine();
				
				if(codigoDisciplina != null){
					int qtTurmas =Integer.parseInt(leitor.readLine());
					for(int b=0;b<qtTurmas;b++){
						int numeroTurma = Integer.parseInt(leitor.readLine());
						this.adicionaTurma(codigoDisciplina, numeroTurma);
						
					}
					
				}
			}while(codigoDisciplina != null);
		}finally{
			if(leitor != null){
				leitor.close();
			}
		}
	}
	
	public void imprimeProfessoresEDisciplinas(){
		if(this.professores.size()!= 0){
			
			for(Professor p : this.professores){
				JOptionPane.showMessageDialog(null,p.toString(),"Consultar todos professores e disciplinas",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}else{
			JOptionPane.showMessageDialog(null,"Nenhum professor cadastrado","Consultar todos professores e disciplinas",JOptionPane.WARNING_MESSAGE);
		}
		
		if(this.disciplinas.size()!= 0){
			
			for(Disciplina d : this.disciplinas){
				JOptionPane.showMessageDialog(null,d.toString(),"Consultar todos professores e disciplinas",JOptionPane.INFORMATION_MESSAGE);
			}
			
		}else{
			JOptionPane.showMessageDialog(null,"Nenhuma disciplina cadastrada","Consultar todos professores e disciplinas",JOptionPane.WARNING_MESSAGE);
		}
		
	}
	@Override
	public void gravaInteressesDeProfessoresPorDisciplinasEmArquivo(
			String nomeArquivo) throws IOException {
		PrintWriter gravador = null;
		
		try{
			FileWriter file = new FileWriter(nomeArquivo);
			gravador = new PrintWriter(file);
			
			for(Professor p : this.professores){
				gravador.println(p.getMatricula());
				/// é aqui
				gravador.println(p.getTextoPreferenciasDisciplinasComCodigo());
				
			}
		} finally {
			if(gravador != null){
				gravador.close();
			}
		}
		
	}
	@Override
	public void carregaInteressesDeProfessoresPorDisciplinasDeArquivo(
			String nomeArquivo) throws PreferenciaInvalidaException,
			ProfessorInexistenteException, DisciplinaInexistenteException,
			IOException {
		BufferedReader leitor = null;
		
		try{
			
			FileReader file = new FileReader(nomeArquivo);
			leitor = new BufferedReader(file);
			String matriculaProf = null;
			
			do{
				
				matriculaProf =leitor.readLine();
				if(matriculaProf != null){
					
					String linhaP1 = leitor.readLine();
					String linhaP2 = leitor.readLine();
					String linhaP3 = leitor.readLine();
					String linhaNP = leitor.readLine();
					
					List<String> P1SemVirgula = this.leListaDeCodigosDeDisciplinasDeLinha(linhaP1);
					List<String> P2SemVirgula = this.leListaDeCodigosDeDisciplinasDeLinha(linhaP2);
					List<String> P3SemVirgula = this.leListaDeCodigosDeDisciplinasDeLinha(linhaP3);
					List<String> NPSemVirgula = this.leListaDeCodigosDeDisciplinasDeLinha(linhaNP);
					
					final int nP1 = 1;
					final int nP2 = 2;
					final int nP3 = 3;
					final int nNP = 4;
					for(int i=0;i<P1SemVirgula.size();i++){
						String codDisciplina = P1SemVirgula.get(i);
						this.cadastraNivelDeInteresseDeProfessorPorDisciplina
						(matriculaProf, codDisciplina, nP1);
					}
					for(int i=0;i<P2SemVirgula.size();i++){
						String codDisciplina = P2SemVirgula.get(i);
						this.cadastraNivelDeInteresseDeProfessorPorDisciplina
						(matriculaProf, codDisciplina, nP2);
					}
					for(int i=0;i<P3SemVirgula.size();i++){
						String codDisciplina = P3SemVirgula.get(i);
						this.cadastraNivelDeInteresseDeProfessorPorDisciplina
						(matriculaProf, codDisciplina, nP3);
					}
					for(int i=0;i<NPSemVirgula.size();i++){
						String codDisciplina = NPSemVirgula.get(i);
						this.cadastraNivelDeInteresseDeProfessorPorDisciplina
						(matriculaProf, codDisciplina, nNP);
					}
					
				}
				
				
				
				
				
				
				
				
			}while(matriculaProf != null);
			
		}finally{
			if(leitor != null){
				leitor.close();
			}
		}
		
	}
	
	
	private List <String> leListaDeCodigosDeDisciplinasDeLinha(String linha){
		List <String> codigos = new LinkedList<String>();
		
		StringTokenizer tokenizer = new StringTokenizer(linha,":");
		String nivelPreferencia = tokenizer.nextToken();//parte antes dos :
		if (tokenizer.hasMoreTokens()){ // Se tiver algo depois dos :
			String listaCodigos = tokenizer.nextToken(); // parte depois dos :
			System.out.println("listaCodigos:"+listaCodigos);
			StringTokenizer tokenizerVirgula = new StringTokenizer(listaCodigos,",");
			while (tokenizerVirgula.hasMoreTokens()){
				String codigo = tokenizerVirgula.nextToken();
				codigos.add(codigo);
			}
		}
		return codigos;
	}
	@Override
	public Turma pesquisaTurma(String codDisciplina, int numTurma)
			throws DisciplinaInexistenteException, TurmaInexistenteException {
		
		

		Disciplina d = this.pesquisaDisciplina(codDisciplina);
		
		for(Turma t: d.getTurmas()){
			if(t.getNumero() == numTurma){
				return t;
				
			}
			
		}
		throw new TurmaInexistenteException("Turma inexistente.");
		
	}
	@Override
	public void cadastraHorarioTurma(String codDisciplina, int numTurma,
			String dia, int horaInicio, int horaFim)
			throws DisciplinaInexistenteException, TurmaInexistenteException {
		
		
		
		Turma t1 = this.pesquisaTurma(codDisciplina, numTurma);
		
		Horario h = new Horario(dia,horaInicio,horaFim);
		t1.adicionarHorario(h);
				
		
		
	}
	@Override
	public void alocaProfessorATurma(String codDisciplina, int numTurma, //DÚUUUVIDA *****************
			String matriculaProf) throws DisciplinaInexistenteException,
			TurmaInexistenteException, ProfessorInexistenteException {
		////Lembre que nesse método vc deve configurar o professor da turma (turma.setProfessor(...)) e deve também adicionar essa turma 
		//à lista de turmas às quais o professor está alocado (p.alocaTurma(...))
		

		Professor p = this.pesquisaProfessorPelaMatricula(matriculaProf);
		
		Turma t = this.pesquisaTurma(codDisciplina, numTurma);
		
		p.alocaTurma(t);
		t.setProfessor(p);
				
		
		
		//preciso encontrar a turma que vou adc e o professor	
		/**verificar se a disciplina existe e se o professor existe nas suas respectivas lists
		 * cada disciplina tem uma list de turmas(varer a list de disciplinas pra ver se existe a turma
		 * se os 3 existirem vou adicionar essa turma na lista de turmasalocado nesse professor)*/
		// verificar se existe a disciplina
		
		
	}
	@Override
	public void desalocaProfessorDeTurma(String codDisciplina, int numTurma,
			String matriculaProf) throws DisciplinaInexistenteException,
			TurmaInexistenteException, ProfessorInexistenteException {
		// //Lembre de desfazer o que o método aloca faz, inclusive de chamar turma.setProfessor(null).
		
		Professor p = this.pesquisaProfessorPelaMatricula(matriculaProf);
		Turma t = this.pesquisaTurma(codDisciplina, numTurma);
		p.desalocaTurma(t);
		t.setProfessor(null);

		
		
	}
	@Override
	public List<Horario> pesquisaHorariosProfessor(String matriculaProf)
			throws ProfessorInexistenteException {
		
		Professor p =this.pesquisaProfessorPelaMatricula(matriculaProf);
		
		return p.getHorarios();
		
	}
	@Override
	public List<Turma> pesquisaTurmasDoProfessor(String matriculaProf)
			throws ProfessorInexistenteException {
		Professor p = this.pesquisaProfessorPelaMatricula(matriculaProf);
		return p.getTurmasAlocado();
	}
	@Override
	public List<Disciplina> pesquisaDisciplinasDoProfessor(String matriculaProf)
			throws ProfessorInexistenteException {
		Professor p = this.pesquisaProfessorPelaMatricula(matriculaProf);
		return p.getDisciplinasAlocado();
	}
	
	

}
