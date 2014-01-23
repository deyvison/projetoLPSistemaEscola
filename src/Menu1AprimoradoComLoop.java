import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;


public class Menu1AprimoradoComLoop {
	
	public static void main(String args[]){
		SisalocaIF sisaloca = new Sisaloca();
		boolean sair = false;
		String arqProfessores,arqDisciplinas,arqTurmas,arqPreferencias;
		
		
		arqProfessores = "professores.txt";
		arqDisciplinas = "disciplinas.txt";
		arqTurmas = "turmas.txt";
		arqPreferencias = "preferencias.txt";
		int opcao;
		try{
			sisaloca.carregarProfessoresDeArquivo(arqProfessores);
			sisaloca.carregarDisciplinasDeArquivo(arqDisciplinas);
			sisaloca.carregarTurmasDeDisciplinasEmArquivo(arqTurmas);
			sisaloca.carregaInteressesDeProfessoresPorDisciplinasDeArquivo(arqPreferencias);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e.getMessage());
			e.printStackTrace();
		}
			do{
				try{
					String [] opcoesMenu = {"1 - Menu Professor.","2 - Menu Aluno.","3 - Menu Disciplina.","4 - Menu Turma.","5 - Menu Alocar.","6 - Ajuda.","7 - Sair."};
					String menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
							"", "Menu Principal",JOptionPane.QUESTION_MESSAGE, null, opcoesMenu, opcoesMenu[0]); // Menu Principal
					
					opcao = Integer.parseInt(menu.charAt(0)+"");
				
						switch(opcao){ // switch Menu principal
						
							case 1: // caso 1 - Professor
								boolean menuProfessor = true;
								while(menuProfessor){
									String[] opcoesMenuProfessor = {"1 - Cadastrar.","2 - Remover.","3 - Consultar/Obter","4 - Pesquisar.","5 - Voltar." };
									menu = ""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
											"", "Menu Principal/Professor",JOptionPane.QUESTION_MESSAGE, null, opcoesMenuProfessor, opcoesMenuProfessor[0]); 
									
									opcao = Integer.parseInt(menu.charAt(0)+"");
									switch(opcao){
										case 1: // caso 1 -Professor/Cadastrar
											
											String[] opcoesMenuProfessorCadastrar = {"1 - Cadastrar Professor.", "2 - Cadastrar Nível de Interesse" +
													"de Professor Por Disciplina.","3 - Voltar."};
											menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
													"", "Menu Principal/Professor/Cadastrar",JOptionPane.QUESTION_MESSAGE, null, opcoesMenuProfessorCadastrar, opcoesMenuProfessorCadastrar[0]); // Menu Principal
											
											opcao = Integer.parseInt(menu.charAt(0)+""); // opções de cadastrar
											switch(opcao){
												case 1: // Professor/Cadastrar/Cadastrar Professor
													
													String nome = JOptionPane.showInputDialog(null,"Digite o nome do professor: ","Cadastrar Professor",JOptionPane.QUESTION_MESSAGE);
													String matricula = JOptionPane.showInputDialog(null,"Digite a matrícula do professor: ","Cadastrar Professor",JOptionPane.QUESTION_MESSAGE);
													sisaloca.cadastraProfessor(nome, matricula);
													JOptionPane.showMessageDialog(null,"Professor(a) "+nome+", Cadastrado com sucesso!","Cadastrar Professor",JOptionPane.INFORMATION_MESSAGE);
													break;
												case 2: // Professor/Cadastrar/Cadastrar Nível de preferencia
													String matriculaProf = JOptionPane.showInputDialog(null,"Digite a matrícula do professor: ","Cadastrar nível de preferência",JOptionPane.QUESTION_MESSAGE);
													String codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Cadastrar nível de preferência",JOptionPane.QUESTION_MESSAGE);
													String[] nivelPref = {"1 - Preferida.","2 - Razoável.","3 - Não gosto.","4 - Nenhuma preferência"};
															
													String nivel =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
															"", "Menu de preferências",JOptionPane.QUESTION_MESSAGE, null, nivelPref, nivelPref[0]);
													int nivelPreferencia = Integer.parseInt(""+nivel.charAt(0));
													
													sisaloca.cadastraNivelDeInteresseDeProfessorPorDisciplina(matriculaProf, codDisciplina, nivelPreferencia);
													JOptionPane.showMessageDialog(null,"Preferências do professor de matrícula "+matriculaProf+" Foram adicionadas. ","Preferência Cadastrada",JOptionPane.INFORMATION_MESSAGE);
													break;
												case 3: // voltar
													break;
												
											}
											break;
										case 2: // Professor /Remover
											String matriculaProf = JOptionPane.showInputDialog(null,"Digite a matrícula do professor que deseja remover: ","Remover professor",JOptionPane.QUESTION_MESSAGE);
											sisaloca.removeProfessor(matriculaProf);
											JOptionPane.showMessageDialog(null,"Professor de matrícula: "+matriculaProf+" Foi removido","Remover Professor",JOptionPane.INFORMATION_MESSAGE);
											
											
											break;
										case 3: // Professor/Consultar
											
											
											String [] opcoesMenuProfessorConsultar = {"1 - Obter Professores e disciplinas cadastradas.","2 - Obter" +
													"lista de todos os professores cadastrados.","3 - Voltar."};
											menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
													"", "Menu Principal/Professor/Consultar",JOptionPane.QUESTION_MESSAGE, null, opcoesMenuProfessorConsultar, opcoesMenuProfessorConsultar[0]);
											
											opcao = Integer.parseInt(menu.charAt(0)+"");
											switch(opcao){
												case 1: // Professor/Consultar/Obter Professores e disciplinas cadastradas
													sisaloca.imprimeProfessoresEDisciplinas();
													break;
												case 2: // Professor/Consultar/obter todos os professores
													List <Professor> prof = sisaloca.obterListaDeProfessores();
													if(prof.size()!= 0){
														String texto ="Professores:\n";
														for(Professor p : prof){
															texto += p.toString()+"\n";
														
														}
														JOptionPane.showMessageDialog(null,texto,"Obter todos os professores",JOptionPane.INFORMATION_MESSAGE);
													}else{
														JOptionPane.showMessageDialog(null,"Não existe nenhum professor cadastrado.","Obter todos os professores",JOptionPane.WARNING_MESSAGE);
													}
													
													break;
												case 3: // Professor/Consultar/Voltar
													break;
												
												 
											}
											break;
										case 4: // Pesquisar
											
//											
											String [] opcoesMenuProfessorPesquisar = {"1 - Pesquisar Professor pelo nome.","2 - Pesquisar" +
													"professor pela matrícula.","3 - Consultar disciplinas por preferência do professor.","4 - Pesquisar" +
													"horários do professor.","5 - Pesquisar turmas do professor.","6 - Pesquisar Disciplinas do professor.","7 - " +
													"Voltar."};
											menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
													"", "Menu Principal/Professor/Pesquisar",JOptionPane.QUESTION_MESSAGE, null, opcoesMenuProfessorPesquisar, opcoesMenuProfessorPesquisar[0]);
											
											opcao = Integer.parseInt(menu.charAt(0)+"");
											switch(opcao){
												case 1: // Professor/Pesquisar/ Pesquisar professor pelo nome 
													String nome = JOptionPane.showInputDialog(null,"Digite o nome do professor: ","Pesquisar Professor",JOptionPane.QUESTION_MESSAGE);
													List <Professor> resultado = sisaloca.pesquisaProfessorPeloNome(nome);
													if(resultado.size()!= 0){
														String texto ="Professores encontrados com o nome:"+nome+".\n";
														for(Professor p : resultado){
															texto +="\n"+p.toString();
														}
														JOptionPane.showMessageDialog(null,texto,"Pesquisar Professor",JOptionPane.INFORMATION_MESSAGE);
													}else{
														JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum professor com nome:"+nome,"Pesquisar professor",JOptionPane.WARNING_MESSAGE);
													}
													break;
												case 2: // Professor/Consultar/Pesquisar professor pela matrícula
													String matricula = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Pesquisar Professor",JOptionPane.QUESTION_MESSAGE);
													Professor p = sisaloca.pesquisaProfessorPelaMatricula(matricula);
													JOptionPane.showMessageDialog(null,p.toString(),"Pesquisar professor",JOptionPane.INFORMATION_MESSAGE);
													break;
												case 3:// Professor/Consultar/Consultar disciplinas por preferência do professor
													String matriculaProfessor = JOptionPane.showInputDialog(null,"Digite a matrícula do professor: ","Consultar Preferências",JOptionPane.QUESTION_MESSAGE);
													String[] nivelPref = {"1 - Preferida.","2 - Razoável.","3 - Não gosto.","4 - Nenhuma preferência"};
															
													String nivel =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
															"", "Menu de preferências",JOptionPane.QUESTION_MESSAGE, null, nivelPref, nivelPref[0]);
													int nivelPreferencia = Integer.parseInt(""+nivel.charAt(0));
													List <Disciplina> dis = sisaloca.consultaDisciplinasPorPreferenciaPorProfessor(matriculaProfessor, nivelPreferencia);
													if(dis.size()!= 0){
														String texto = "Disciplinas de preferência: "+nivelPref+"\n";
														for(Disciplina d : dis){
															texto += d.getNome()+"\n";
														}
														JOptionPane.showMessageDialog(null,texto,"Consultar preferências",JOptionPane.INFORMATION_MESSAGE);
													}else{
														JOptionPane.showMessageDialog(null,"Não existe disciplinas com o nível de preferência: "+nivelPref,"Consultar Preferências",JOptionPane.WARNING_MESSAGE);
													}
													break;
												case 4: //Professor/Consultar/Pesquisar Horários do professor
													matriculaProfessor = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Pesquisar horários do professor",JOptionPane.QUESTION_MESSAGE);
													List <Horario> horarios = sisaloca.pesquisaHorariosProfessor(matriculaProfessor);
													if(horarios.size()!= 0){
														String texto ="Horários encontrado:\n";
														for(Horario h : horarios){
															texto += h.toString()+"\n";
														}
														JOptionPane.showMessageDialog(null,texto,"Pesquisar Horários do professor",JOptionPane.INFORMATION_MESSAGE);
													}else{
														JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum horário para este professor.","Pesquisar Horários do professor",JOptionPane.WARNING_MESSAGE);
													}
													
													break;
												case 5:// Professor/Consultar/Pesquisar turmas do professor
													matriculaProfessor = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Pesquisar turmas do professor",JOptionPane.QUESTION_MESSAGE);
													List <Turma> turmas = sisaloca.pesquisaTurmasDoProfessor(matriculaProfessor);
													if(turmas.size()!= 0){
														String texto ="Turmas encontradas:\n";
														for(Turma t : turmas){
															texto += t.toString()+"\n";
														}
														JOptionPane.showMessageDialog(null,texto,"Turmas do professor",JOptionPane.INFORMATION_MESSAGE);
													}else{
														JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma turma para este professor.","Turmas do professor",JOptionPane.WARNING_MESSAGE);
													}
													break;
												case 6: //Professor/Consultar/Pesquisar Disciplinas do professor
													matriculaProfessor = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Pesquisar disciplinas do professor",JOptionPane.QUESTION_MESSAGE);
													List <Disciplina> disciplinas = sisaloca.pesquisaDisciplinasDoProfessor(matriculaProfessor);
													if(disciplinas.size()!= 0){
														String texto ="Disciplinas encontradas:\n";
														for(Disciplina d : disciplinas){
															texto += d.toString()+"\n";
														}
														JOptionPane.showMessageDialog(null,texto,"Disciplinas do professor",JOptionPane.INFORMATION_MESSAGE);
													}else{
														JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma disciplina para este professor.","Disciplinas do professor",JOptionPane.WARNING_MESSAGE);
													}
													break;
												case 7: // Professor/Consultar/voltar
													break;
												
													
											}
											break;
										case 5: // Professor/Voltar
											menuProfessor = false;
											
											
									}
								}
							
//								
								
							
								break;
							case 2: //Aluno
								
								boolean menuAluno = true;
								while(menuAluno){
									String[] opcaoAluno = {"1 - Cadastrar.","2 - Pesquisar.","3 - Remover.","4 - Voltar"};
									menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
											"", "Menu Principal/Aluno",JOptionPane.QUESTION_MESSAGE, null, opcaoAluno, opcaoAluno[0]); 
									opcao = Integer.parseInt(menu.charAt(0)+"");
									switch(opcao){
										case 1: //Aluno/Cadastrar
											String nome = JOptionPane.showInputDialog(null,"Digite o nome do aluno para cadastrar: ","Cadastrar Aluno",JOptionPane.QUESTION_MESSAGE);
											String matricula = JOptionPane.showInputDialog(null,"Digite a matrícula do aluno: ","Cadastrar Aluno",JOptionPane.QUESTION_MESSAGE);
											sisaloca.cadastraAluno(nome, matricula);
											JOptionPane.showMessageDialog(null,"Aluno cadastrado com sucesso.","Cadastrar Aluno",JOptionPane.INFORMATION_MESSAGE);
											break;
										case 2:// Aluno/Pesquisar
											
//											
											String[] opcaoAlunoPesquisar = {"1 - Pesquisar aluno pelo nome.","2 - Pesq" +
													"uisar aluno pela matrícula.","3 - Voltar;"};
											menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
													"", "Menu Principal/Aluno/Pesquisar",JOptionPane.QUESTION_MESSAGE, null, opcaoAlunoPesquisar, opcaoAlunoPesquisar[0]); 
											opcao = Integer.parseInt(menu.charAt(0)+"");
											switch(opcao){ //opções de pesquisar
												case 1: //Aluno/Pesquisar/pesquisar aluno pelo nome
													nome = JOptionPane.showInputDialog(null,"Digite o nome do aluno que deseja pesquisar: ","Pesquisar Aluno pelo nome",JOptionPane.QUESTION_MESSAGE);
													List <Aluno> alunos = sisaloca.pesquisaAlunoPeloNome(nome);
													if(alunos.size()!= 0){
														String texto ="Alunos encontrados:\n";
														for(Aluno a : alunos){
															texto += a.toString()+"\n";
														}
														JOptionPane.showMessageDialog(null,texto,"Pesquisar alunos pelo nome",JOptionPane.INFORMATION_MESSAGE);
													}else{
														JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum aluno com o nome: "+nome,"Pesquisar alunos pelo nome",JOptionPane.WARNING_MESSAGE);
													}
													break;
												case 2: //Aluno/Pesquisar/ pesquisar aluno pela matricula
													matricula = JOptionPane.showInputDialog(null,"Digite a matrícula do aluno que deseja pesquisar: ","Pesquisar aluno pela matrícula",JOptionPane.QUESTION_MESSAGE);
													Aluno a = sisaloca.pesquisaAlunoPelaMatricula(matricula);
													JOptionPane.showMessageDialog(null,"Aluno encontrado:"+ a.toString(),"Pesquisar aluno pela matrícula",JOptionPane.INFORMATION_MESSAGE);
													break;
												case 3: // Aluno/Pesquisar/voltar
													break;
												
											}
											break;
										case 3: // Aluno/Remover
											matricula = JOptionPane.showInputDialog(null,"Digite a matrícula do aluno que deseja remover: ","Remover aluno",JOptionPane.QUESTION_MESSAGE);
											sisaloca.removeAluno(matricula);
											JOptionPane.showMessageDialog(null,"Aluno removido do sistema!.","Remover Aluno",JOptionPane.INFORMATION_MESSAGE);
											break;
										case 4: //Aluno/Voltar
											menuAluno = false;
											
										
									}
								}
								
								break;
							case 3: //Disciplina
								boolean menuDisciplina =  true;
								while(menuDisciplina){
									String[] opcaoDisciplina = {"1 - Adicionar.","2 - Remover.","3 - Pesquisar.","4 - Consu" +
											"ltar/Obter.","5 - Voltar."};
									menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
											"", "Menu Principal/Disciplina",JOptionPane.QUESTION_MESSAGE, null, opcaoDisciplina, opcaoDisciplina[0]); 
									opcao = Integer.parseInt(menu.charAt(0)+"");
									switch(opcao){
										case 1: //Disciplina/Adicinar 
											

											String[] opcaoDisciplinaAdicionar = {"1 - Adicionar disciplina.","2 - Cadastrar nível" +
													" de interesse do professor por uma disciplina.","3 - Voltar"};
											menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
													"", "Menu Principal/Disciplina/Adicinar ",JOptionPane.QUESTION_MESSAGE, null, opcaoDisciplinaAdicionar, opcaoDisciplinaAdicionar[0]); 
											opcao = Integer.parseInt(menu.charAt(0)+"");
											switch(opcao){
												case 1: // Disciplina/Adicinar /adicionar disciplina
													String nomeDis = JOptionPane.showInputDialog(null,"Digite o nome da disciplina que deseja cadastrar: ","Adicionar disciplina",JOptionPane.QUESTION_MESSAGE);
													String codigoDis = JOptionPane.showInputDialog(null,"Digite o código da disciplina que deseja cadastrar: ","Adicionar disciplina",JOptionPane.QUESTION_MESSAGE);
													sisaloca.adicionaDisciplina(nomeDis, codigoDis);
													JOptionPane.showMessageDialog(null,"Disciplina "+nomeDis+" De código "+codigoDis+", Foi adicionada.","Adicionar Disciplina",JOptionPane.INFORMATION_MESSAGE);
													break;
												case 2: //Disciplina/Adicinar/ cadastrar nivel de interesse pela disciplina
													
													String matriculaProf = JOptionPane.showInputDialog(null,"Digite a matrícula do professor: ","Cadastrar interesse pela disciplina",JOptionPane.QUESTION_MESSAGE);
													String codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Cadastrar interesse pela disciplina",JOptionPane.QUESTION_MESSAGE);
													String[] nivelPref = {"1 - Preferida.","2 - Razoável.","3 - Não gosto.","4 - Nenhuma preferência"};
															
													String nivel =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
															"", "Menu de preferências",JOptionPane.QUESTION_MESSAGE, null, nivelPref, nivelPref[0]);
													int nivelPreferencia = Integer.parseInt(""+nivel.charAt(0));
													sisaloca.cadastraNivelDeInteresseDeProfessorPorDisciplina(matriculaProf, codDisciplina, nivelPreferencia);
													JOptionPane.showMessageDialog(null,"Preferências do professor de matrícula "+matriculaProf+" Foram adicionadas. ","Cadastrar interesse pela disciplina",JOptionPane.INFORMATION_MESSAGE);
													break;
												case 3: //Disciplina/Adicinar/voltar
													break;
												
											}
											break;
											
										case 2: // Disciplina/remover
											String codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina que deseja remover: ","Remover disciplina",JOptionPane.QUESTION_MESSAGE);
											sisaloca.removeDisciplina(codDisciplina);
											JOptionPane.showMessageDialog(null,"Disciplina de código: "+codDisciplina+" Foi removida.","Remover disicplina",JOptionPane.QUESTION_MESSAGE);
											break;
										case 3: //Disciplina/pesquisar
											
										
											String[] opcaoDisciplinaPesquisar = {"1 - Pesquisar disciplina.","2 - Consultar disciplinas" +
													" por preferência do professor.","3 - Voltar."};
											menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
													"", "Menu Principal/Disciplina/Pesquisar",JOptionPane.QUESTION_MESSAGE, null, opcaoDisciplinaPesquisar, opcaoDisciplinaPesquisar[0]); 
											opcao = Integer.parseInt(menu.charAt(0)+"");
											switch(opcao){
												case 1: //Disciplina/pesquisar/ pesquisar disciplina
													codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina que deseja pesquisar: ","Pesquisar disciplina",JOptionPane.QUESTION_MESSAGE);
													Disciplina d = sisaloca.pesquisaDisciplina(codDisciplina);
													JOptionPane.showMessageDialog(null,"Disciplina encontrada: "+d.toString(),"Pesquisar disciplina",JOptionPane.INFORMATION_MESSAGE);
													break;
												case 2: // Disciplina/pesquisar/consultar disciplinas pelas preferencias do prof
													String matriculaProfessor = JOptionPane.showInputDialog(null,"Digite a matrícula do professor: ","Pesquisar disciplina por preferência",JOptionPane.QUESTION_MESSAGE);
													String[] nivelPref = {"1 - Preferida.","2 - Razoável.","3 - Não gosto.","4 - Nenhuma preferência"};
															
													String nivel =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
															"", "Menu de preferências",JOptionPane.QUESTION_MESSAGE, null, nivelPref, nivelPref[0]);
													int nivelPreferencia = Integer.parseInt(""+nivel.charAt(0));
													List <Disciplina> dis = sisaloca.consultaDisciplinasPorPreferenciaPorProfessor(matriculaProfessor, nivelPreferencia);
													if(dis.size()!= 0){
														String texto = "Disciplinas de preferência: "+nivelPref+"\n";
														for(Disciplina disciplina : dis){
															texto += disciplina.getNome()+"\n";
														}
														JOptionPane.showMessageDialog(null,texto,"Pesquisar disciplina por preferência",JOptionPane.INFORMATION_MESSAGE);
													}else{
														JOptionPane.showMessageDialog(null,"Não existe disciplinas com o nível de preferência: "+nivelPref,"Pesquisar disciplina por preferência",JOptionPane.WARNING_MESSAGE);
													}
													break;
												case 3: //Disciplina/pesquisar/voltar
													break;
												
											}
											break;
										case 4: // Disciplina/consultar
									
											String[] opcaoDisciplinaConsultar = {"1 - Consultar todas as disciplinas.","2 - Consultar " +
													"todos os professores e disciplinas cadastrados.","3 - Voltar."};
											menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
													"", "Menu Principal/Disciplina/consultar",JOptionPane.QUESTION_MESSAGE, null, opcaoDisciplinaConsultar, opcaoDisciplinaConsultar[0]); 
											opcao = Integer.parseInt(menu.charAt(0)+"");
											switch(opcao){
												case 1: // Disciplina/consultar/consultar todas as disciplinas
													List<Disciplina> disciplinas = sisaloca.obterListaDeDisciplinas();
													if(disciplinas.size()!=0){
														String texto ="Disciplinas cadastradas:\n";
														for(Disciplina d : disciplinas){
															texto  += d.toString()+"\n";
														}
														JOptionPane.showMessageDialog(null,texto,"Consultar todas as disciplinas",JOptionPane.INFORMATION_MESSAGE);
													}else{
														JOptionPane.showMessageDialog(null,"Nenhuma disciplina cadastrada.","Consultar todas as disciplinas",JOptionPane.WARNING_MESSAGE);
													}
													break;
												case 2: //Disciplina/consultar/consultar todos os professores e disciplinas
													sisaloca.imprimeProfessoresEDisciplinas();
													break;
												case 3://Disciplina/consultar/ voltar
													break;
												
											}
											break;
										case 5: //Disciplina/voltar
											menuDisciplina = false;
											
										
									}
								}

								
								break;
							case 4://Turma
								boolean menuTurma = true;
								while(menuTurma){
									String[] opcaoTurma = {"1 - Adicionar.","2 - Remover.","3 - Pesquisar.","4 - Voltar."};
									menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
											"", "Menu Principal/Turma",JOptionPane.QUESTION_MESSAGE, null, opcaoTurma, opcaoTurma[0]); 
									opcao = Integer.parseInt(menu.charAt(0)+"");
									switch(opcao){
										case 1: // Turma/adicionar
//											
											String[] opcaoTurmaAdicionar = {"1 - Adicionar Turma.","2 - Adicionar um horario par" +
													"a determinada turma.","3 - Voltar"};
											menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
													"", "Menu Principal/Turma/Adicionar",JOptionPane.QUESTION_MESSAGE, null, opcaoTurmaAdicionar, opcaoTurmaAdicionar[0]); 
											opcao = Integer.parseInt(menu.charAt(0)+"");
											switch(opcao){
												case 1://Turma/adicionar/ adicionar turma
													String codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina que deseja pesquisar: ","Adicionar Turma",JOptionPane.QUESTION_MESSAGE);
													int numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Adicionar Turma",JOptionPane.QUESTION_MESSAGE));
													sisaloca.adicionaTurma(codDisciplina, numTurma);
													JOptionPane.showMessageDialog(null,"Disciplina de código:"+codDisciplina+", foi adicionada à turma: "+numTurma,"Adicionar Turma",JOptionPane.INFORMATION_MESSAGE);
													break;
												case 2://Turma/adicionar/cadastrar horario turma
													codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina que deseja cadastrar um horário: ","Cadastrar um horário para uma turma",JOptionPane.QUESTION_MESSAGE);
													numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Cadastrar um horário para uma turma",JOptionPane.QUESTION_MESSAGE));
													String diaString;
													int horaInicio,horaFim;
													
													String[] diasDaSemana = {"1 - Domingo.","2 - Segunda.","3 -  Terça.","4 - Qua" +
													"rta.","5 - Quinta.","6 - Sexta.","7 - Sábado"};
													String diasS =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
															"", "Menu Principal",JOptionPane.QUESTION_MESSAGE, null, diasDaSemana, diasDaSemana[0]); 
													int dia = Integer.parseInt(diasS.charAt(0)+"");
													
													Horario h = new Horario();
													if(dia == 1){
														diaString = h.DOMINGO;
													}else if(dia == 2){
														diaString = h.SEGUNDA;
													}else if(dia == 3){
														diaString = h.TERCA;
													}else if(dia == 4){
														diaString = h.QUARTA;
													}else if(dia == 5){
														diaString = h.QUINTA;
													}else if(dia == 6){
														diaString = h.SEXTA;
													}else{
														diaString = h.SABADO;
													}
													
													horaInicio = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite a hora do início da aula: ","Cadastrar um horário para uma turma",JOptionPane.QUESTION_MESSAGE));
													horaFim = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite a hora do fim da aula: ","Cadastrar um horário para uma turma",JOptionPane.QUESTION_MESSAGE));
													
													sisaloca.cadastraHorarioTurma(codDisciplina, numTurma, diaString, horaInicio, horaFim);
													JOptionPane.showMessageDialog(null, "Horário cadastrado na turma com sucesso","Cadastrar um horário para uma turma",JOptionPane.INFORMATION_MESSAGE);
													break;
												case 3://Turma/adicionar/voltar
													break;
												
											}
											break;
										case 2://Turma/remover
											String codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Remover turma",JOptionPane.QUESTION_MESSAGE);
											int numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Remover turma",JOptionPane.QUESTION_MESSAGE));
											sisaloca.removeTurma(codDisciplina, numTurma);
											JOptionPane.showMessageDialog(null,"A turma de número:"+numTurma+" foi removida da disciplina.","Remover Turma",JOptionPane.INFORMATION_MESSAGE);
											break;
										case 3://Turma/pesquisar
											codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Pesquisar turma",JOptionPane.QUESTION_MESSAGE);
											numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Pesquisar turma",JOptionPane.QUESTION_MESSAGE));
											Turma t = sisaloca.pesquisaTurma(codDisciplina, numTurma);
											JOptionPane.showMessageDialog(null,"Turma encontrada:\n"+t.toString(),"Pesquisar turma",JOptionPane.INFORMATION_MESSAGE);
											break;
										case 4:// voltar
											menuTurma = false;
											
									}
								}
//								
								
								break;
							case 5: //Alocar
								boolean menuAlocar = true;
								while(menuAlocar){
									String[] opcaoAlocar = {"1 - Alocar um professor a turma.","2 - Desa" +
											"locar um professor a turma.","3 - Voltar"};
									menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
											"", "Menu Principal/Alocar",JOptionPane.QUESTION_MESSAGE, null, opcaoAlocar, opcaoAlocar[0]); 
									opcao = Integer.parseInt(menu.charAt(0)+"");
									switch(opcao){
										case 1: //Alocar/alocar professor a turma
											String matriculaProf = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Alocar professor em uma turma",JOptionPane.QUESTION_MESSAGE);
											String codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Alocar profesor em uma turma",JOptionPane.QUESTION_MESSAGE);
											int numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Alocar professor em uma turma",JOptionPane.QUESTION_MESSAGE));
											sisaloca.alocaProfessorATurma(codDisciplina, numTurma, matriculaProf);
											JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
													" foi alocado com sucesso à turma "+numTurma,"Alocar professor em uma turma",JOptionPane.INFORMATION_MESSAGE);
											break;
										case 2: // Alocar/desalocar professor a turma
											matriculaProf = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Desalocar professor em uma turma",JOptionPane.QUESTION_MESSAGE);
											codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Desalocar professor em uma turma",JOptionPane.QUESTION_MESSAGE);
											numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Desalocar professor em uma turma",JOptionPane.QUESTION_MESSAGE));
											sisaloca.desalocaProfessorDeTurma(codDisciplina, numTurma, matriculaProf);
											JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
													" foi desalocado com sucesso à turma "+numTurma,"Desalocar professor em uma turma",JOptionPane.INFORMATION_MESSAGE);
											break;
										case 3: //Alocar/voltar
											menuAlocar = false;
										
										
									}
								}
								
								break;
							case 6://Ajuda
								JOptionPane.showMessageDialog(null,"SISTEMA DE ALOCAÇÃO DE DISCIPLINAS\n \nDESCRIÇÃO BREVE:\nO sistema de alocação de disciplinas é um sistema para gerenciar as alocações " +
										"entre turmas e professores e fazer várias verificações.","Ajuda",JOptionPane.INFORMATION_MESSAGE);
								break;
							case 7://Sair
								sair = true;
								
							
						}
						
						
						
						
						
			}catch(NumberFormatException err){
				try{
					sisaloca.gravarProfessoresEmArquivo(arqProfessores);
					sisaloca.gravarDisciplinasEmArquivo(arqDisciplinas);
					
					sisaloca.gravarTurmasDeDisciplinasEmArquivo(arqTurmas);
					sisaloca.gravaInteressesDeProfessoresPorDisciplinasEmArquivo(arqPreferencias);
				}catch(IOException er){
					
				}
				
				return;
			}catch(Exception er){
				JOptionPane.showMessageDialog(null, er.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}		
						
					
				
			}while(!sair);
			
			try{
				sisaloca.gravarProfessoresEmArquivo(arqProfessores);
				sisaloca.gravarDisciplinasEmArquivo(arqDisciplinas);
				
				sisaloca.gravarTurmasDeDisciplinasEmArquivo(arqTurmas);
				sisaloca.gravaInteressesDeProfessoresPorDisciplinasEmArquivo(arqPreferencias);
			}catch(IOException err){
				
			}
			
			JOptionPane.showMessageDialog(null,"Sistema finalizado ;D");
		
		
	}

}
