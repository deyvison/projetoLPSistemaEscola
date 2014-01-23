import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;


public class Menu2AprimoradoComLoop {

	
	public static void main(String[] args) {
		
		SisalocaIF sisaloca = new Sisaloca();
		String arqProfessores,arqDisciplinas,arqTurmas,arqPreferencias;
		
		
		arqProfessores = "professores.txt";
		arqDisciplinas = "disciplinas.txt";
		arqTurmas = "turmas.txt";
		arqPreferencias = "preferencias.txt";
		try{
			sisaloca.carregarProfessoresDeArquivo(arqProfessores);
			sisaloca.carregarDisciplinasDeArquivo(arqDisciplinas);
			sisaloca.carregarTurmasDeDisciplinasEmArquivo(arqTurmas);
			sisaloca.carregaInteressesDeProfessoresPorDisciplinasDeArquivo(arqPreferencias);
		}catch(IOException err){
			return;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
		boolean sair = false;
		do{
			try{
		
				String[] opcoesMenu = {"1 - Cadastrar.","2 - Pesquisar.","3 - Consultar/Obt" +
				"er","4 - Remover.","5 - Alocar.","6 - Ajuda.","7 - Sair."};
				String menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
				"", "Menu Principal",JOptionPane.QUESTION_MESSAGE, null, opcoesMenu, opcoesMenu[0]); 
				int opcao = Integer.parseInt(menu.charAt(0)+"");
				switch(opcao){
					
					
					case 1: // cadastrar
						boolean menuCadastrar = true;
						while(menuCadastrar){
							String[] opcoesMenuCadastrar = {"1 - Cadastrar professor.","2 - Cadastrar aluno.","3 - Adicionar disciplina.",
									"4 - Adicionar turma.","5 -  Cadastrar nivel de interesse do professor por disciplina.","6 - Cadastrar horario a turma.",
									"7 - Aloca professor a turma.","8 - Voltar."};
							menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
									"", "Menu Principal/Cadastrar",JOptionPane.QUESTION_MESSAGE, null, opcoesMenuCadastrar, opcoesMenuCadastrar[0]); 
							opcao = Integer.parseInt(menu.charAt(0)+"");
							switch(opcao){
								case 1: // cadastraProfessor
									String nome = JOptionPane.showInputDialog(null,"Digite o nome do professor: ","Cadastrar professor",JOptionPane.QUESTION_MESSAGE);
									String matricula = JOptionPane.showInputDialog(null,"Digite a matrícula: ","Cadastrar professor",JOptionPane.QUESTION_MESSAGE);
									sisaloca.cadastraProfessor(nome, matricula);
									JOptionPane.showMessageDialog(null,"Professor cadastrado: "+nome+", Matrícula:"+matricula,"Cadastrar professor",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 2: //cadastraAluno
									nome = JOptionPane.showInputDialog(null,"Digite o nome do aluno: ","Cadastrar aluno",JOptionPane.QUESTION_MESSAGE);
									matricula = JOptionPane.showInputDialog(null,"Digite a matrícula: ","Cadastrar aluno",JOptionPane.QUESTION_MESSAGE);
									sisaloca.cadastraAluno(nome, matricula);
									JOptionPane.showMessageDialog(null,"Aluno cadastrado: "+nome+", Matrícula:"+matricula,"Cadastrar aluno",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 3: //adicionaDisciplina
									nome = JOptionPane.showInputDialog(null,"Digite o nome da disciplina: ","Adicionar disciplina",JOptionPane.QUESTION_MESSAGE);
									String codigo = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Adicionar disciplina",JOptionPane.QUESTION_MESSAGE);
									sisaloca.adicionaDisciplina(nome, codigo);
									JOptionPane.showMessageDialog(null,"Disciplina adicionada: "+nome+", Código:"+codigo,"Adicionar disciplina",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 4://adicionaTurma
									codigo = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Adicionar Turma",JOptionPane.QUESTION_MESSAGE);
									int numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Adicionar turma",JOptionPane.QUESTION_MESSAGE));
									sisaloca.adicionaTurma(codigo, numTurma);
									JOptionPane.showMessageDialog(null,"A turma:"+numTurma+", foi adicionada a disciplina de código"+codigo,"Adicionar turma",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 5://cadastraNivelDeInteresseDeProfessorPorDisciplina
									
									String matriculaProf = JOptionPane.showInputDialog(null,"Digite a matrícula do professor: ","Cadastrar preferências do professor por disicplina",JOptionPane.QUESTION_MESSAGE);
									String codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Cadastrar preferências do professor por disicplina",JOptionPane.QUESTION_MESSAGE);
								
									String[] nivelPref = {"1 - Preferida.","2 - Razoável.","3 - Não gosto.","4 - Nenhuma preferência"};
											
									String nivel =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
											"", "Menu de preferências",JOptionPane.QUESTION_MESSAGE, null, nivelPref, nivelPref[0]);
									int nivelPreferencia = Integer.parseInt(""+nivel.charAt(0));
									
									sisaloca.cadastraNivelDeInteresseDeProfessorPorDisciplina(matriculaProf, codDisciplina, nivelPreferencia);
									JOptionPane.showMessageDialog(null,"Preferências do professor de matrícula "+matriculaProf+" Foram adicionadas. ","Cadastrar preferências do professor por disicplina",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 6://cadastraHorarioTurma
									codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina que deseja pesquisar: ","Cadastrar horário em uma turma",JOptionPane.QUESTION_MESSAGE);
									numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Cadastrar horário em uma turma",JOptionPane.QUESTION_MESSAGE));
									String diaString;
									int horaInicio;
									int horaFim;
//									int dia = Integer.parseInt(JOptionPane.showInputDialog("Digite o número correspondente ao dia do horário:\n1 - Domingo.\n2 - Segunda.\n3 -  Terça.\n4 - " +
//											"Quarta.\n5 - Quinta.\n6 - Sexta.\n7 - Sábado"));
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
									
									horaInicio = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite a hora do início da aula: ","Cadastrar horário em uma turma",JOptionPane.QUESTION_MESSAGE));
									horaFim = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite a hora do fim da aula: ","Cadastrar horário em uma turma",JOptionPane.QUESTION_MESSAGE));
									
									sisaloca.cadastraHorarioTurma(codDisciplina, numTurma, diaString, horaInicio, horaFim);
									JOptionPane.showMessageDialog(null, "O horário foi cadastrado com sucesso", "Cadastrar horário em uma turma", JOptionPane.INFORMATION_MESSAGE);
									break;
								case 7://alocaProfessorAturma *
									matriculaProf = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Aloca professor em uma turma",JOptionPane.QUESTION_MESSAGE);
									codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Aloca professor em uma turma",JOptionPane.QUESTION_MESSAGE);
									numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Aloca professor em uma turma",JOptionPane.QUESTION_MESSAGE));
									sisaloca.alocaProfessorATurma(codDisciplina, numTurma, matriculaProf);
									JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
											" foi alocado com sucesso à turma "+numTurma,"Aloca professor em uma turma",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 8:// voltar
									menuCadastrar = false;
								
								
							}
						}
						
						break;
					case 2: // pesquisar
						boolean menuPesquisar = true;
						while(menuPesquisar){
							String[] opcoesPesquisar = {"1 - Pesquisar professor pelo nome.","2 - Pesquisar professor pela ma" +
									"trícula.","3 - Pesquisar aluno pelo nome.","4 - Pesquisar aluno pela matrícula.","5 - Pesquisar Disciplina.","6 - " +
									"Pesquisar horários do professor.","7 - Pesquisar turmas do professor.","8 - Pesquisar disciplinas do professor.","9 - Pesquisar turma.","10 - Voltar."};
							menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
									"", "Menu Principal/Pesquisar",JOptionPane.QUESTION_MESSAGE, null, opcoesPesquisar,opcoesPesquisar[0]); 
							opcao = Integer.parseInt(menu.charAt(0)+"");
							String testeSair = menu.charAt(0)+""+menu.charAt(1);
							if(testeSair.equals("10")){
								opcao = 10;
							}
							switch(opcao){
								case 1: // pesquisar professor pelo nome
									String nome = JOptionPane.showInputDialog(null,"Digite o nome do professor: ","Pesquisar professor pelo nome",JOptionPane.QUESTION_MESSAGE);
									List <Professor> resultado = sisaloca.pesquisaProfessorPeloNome(nome);
									if(resultado.size()!= 0){
										String texto ="Professores encontrados com o nome:"+nome+".\n";
										for(Professor p : resultado){
											texto +="\n"+p.toString();
										}
										JOptionPane.showMessageDialog(null,texto,"Pesquisar professor pelo nome",JOptionPane.INFORMATION_MESSAGE);
									}else{
										JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum professor com nome:"+nome,"Pesquisar professor pelo nome",JOptionPane.WARNING_MESSAGE);
									}
									break;
								case 2:// pesquisar professor pela matricula
									String matricula = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Pesquisar professor pela matrícula",JOptionPane.QUESTION_MESSAGE);
									Professor p = sisaloca.pesquisaProfessorPelaMatricula(matricula);
									JOptionPane.showMessageDialog(null,p.toString(),"Pesquisar professor pela matrícula",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 3:// pesquisar aluno pelo nome
									nome = JOptionPane.showInputDialog(null,"Digite o nome do aluno que deseja pesquisar: ","Pesquisar aluno pelo nome",JOptionPane.QUESTION_MESSAGE);
									List <Aluno> alunos = sisaloca.pesquisaAlunoPeloNome(nome);
									if(alunos.size()!= 0){
										String texto ="Alunos encontrados:\n";
										for(Aluno a : alunos){
											texto += a.toString()+"\n";
										}
										JOptionPane.showMessageDialog(null,texto,"Pesquisar aluno pelo nome",JOptionPane.INFORMATION_MESSAGE);
									}else{
										JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum aluno com este nome.","Pesquisar aluno pelo nome",JOptionPane.WARNING_MESSAGE);
									}
									break;
								case 4:// pesquisar aluno pela matricula
									matricula = JOptionPane.showInputDialog(null,"Digite a matrícula do aluno que deseja pesquisar: ","Pesquisar aluno pela matricula",JOptionPane.QUESTION_MESSAGE);
									Aluno a = sisaloca.pesquisaAlunoPelaMatricula(matricula);
									JOptionPane.showMessageDialog(null,"Aluno encontrado:"+ a.toString(),"Pesquisar aluno pela matrícula",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 5:// pesquisar disciplina
									String codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina que deseja pesquisar: ","Pesquisar disciplina",JOptionPane.QUESTION_MESSAGE);
									Disciplina d = sisaloca.pesquisaDisciplina(codDisciplina);
									JOptionPane.showMessageDialog(null,"Disciplina encontrada: "+d.toString(),"Pesquisar disciplina",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 6:// pesquisar horarios do professor
									String matriculaProfessor = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Pesquisar horários do professor",JOptionPane.QUESTION_MESSAGE);
									List <Horario> horarios = sisaloca.pesquisaHorariosProfessor(matriculaProfessor);
									if(horarios.size()!= 0){
										String texto ="Horários encontrado:\n";
										for(Horario h : horarios){
											texto += h.toString()+"\n";
										}
										JOptionPane.showMessageDialog(null,texto,"Pesquisar horários do professor",JOptionPane.INFORMATION_MESSAGE);
									}else{
										JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum horário para este professor.","Pesquisar horários do professor",JOptionPane.WARNING_MESSAGE);
									}
									break;
								case 7:// pesquisar turmas do professor
									matriculaProfessor = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Pesquisar turmas do professor",JOptionPane.QUESTION_MESSAGE);
									List <Turma> turmas = sisaloca.pesquisaTurmasDoProfessor(matriculaProfessor);
									if(turmas.size()!= 0){
										String texto ="Turmas encontradas:\n";
										for(Turma t : turmas){
											texto += t.toString()+"\n";
										}
										JOptionPane.showMessageDialog(null,texto,"Pesquisar turmas do professor",JOptionPane.INFORMATION_MESSAGE);
									}else{
										JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma turma para este professor.","Pesquisar turmas do professor",JOptionPane.WARNING_MESSAGE);
									}
									break;
								case 8:// pesquisar disciplinas do professor
									matriculaProfessor = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Pesquisar disciplinas do professor",JOptionPane.QUESTION_MESSAGE);
									List <Disciplina> disciplinas = sisaloca.pesquisaDisciplinasDoProfessor(matriculaProfessor);
									if(disciplinas.size()!= 0){
										String texto ="Disciplinas encontradas:\n";
										for(Disciplina dis : disciplinas){
											texto += dis.toString()+"\n";
										}
										JOptionPane.showMessageDialog(null,texto,"Pesquisar disciplinas do professor",JOptionPane.INFORMATION_MESSAGE);
									}else{
										JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma disciplina para este professor.","Pesquisar disciplinas do professor",JOptionPane.WARNING_MESSAGE);
									}
									break;
								case 9:// pesquisar turma
									codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Pesquisar turma",JOptionPane.QUESTION_MESSAGE);
									int numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Pesquisar turma",JOptionPane.QUESTION_MESSAGE));
									Turma t = sisaloca.pesquisaTurma(codDisciplina, numTurma);
									JOptionPane.showMessageDialog(null,"Turma encontrada:\n"+t.toString(),"Pesquisar turma",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 10:// voltar
									menuPesquisar = false;
								
							}
						}

						
						
						break;
					case 3:// Consultar/ obter
						boolean menuConsultar =true;
						while(menuConsultar){
							String[] opcoesConsultar = {"1 - Consultar disciplinas pela preferência do professor.","2 - Obter " +
									"lista de professores cadastrados.","3 - Obter lista de disciplinas.","4 - Imprimir professores e disciplinas.","5 - Voltar."};
							menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
									"", "Menu Principal/Consultar",JOptionPane.QUESTION_MESSAGE, null, opcoesConsultar,opcoesConsultar[0]); 
							opcao = Integer.parseInt(menu.charAt(0)+"");
							switch(opcao){
								case 1: // consultar disciplinas pela preferencia do professor
									String matriculaProfessor = JOptionPane.showInputDialog(null,"Digite a matrícula do professor: ","Consultar disciplinas por preferência",JOptionPane.QUESTION_MESSAGE);

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
										JOptionPane.showMessageDialog(null,texto,"Consultar disciplinas por preferência",JOptionPane.INFORMATION_MESSAGE);
									}else{
										JOptionPane.showMessageDialog(null,"Não existe disciplinas com o nível de preferência: "+nivelPref,"Consultar disciplinas por preferência",JOptionPane.WARNING_MESSAGE);
									}
									break;
								case 2:// obter lista de todos professores cadastrados
									List <Professor> prof = sisaloca.obterListaDeProfessores();
									if(prof.size()!= 0){
										String texto ="Professores:\n";
										for(Professor p : prof){
											texto += p.toString()+"\n";
										
										}
										JOptionPane.showMessageDialog(null,texto,"Lista de professores cadastrados",JOptionPane.INFORMATION_MESSAGE);
									}else{
										JOptionPane.showMessageDialog(null,"Não existe nenhum professor cadastrado.","Lista de professores cadastrados",JOptionPane.WARNING_MESSAGE);
									}
									break;
								case 3://obter lista de todas as disciplinas cadastradas
									List<Disciplina> disciplinas = sisaloca.obterListaDeDisciplinas();
									if(disciplinas.size()!=0){
										String texto ="Disciplinas cadastradas:\n";
										for(Disciplina d : disciplinas){
											texto  += d.toString()+"\n";
										}
										JOptionPane.showMessageDialog(null,texto,"Lista de disciplinas cadastradas",JOptionPane.INFORMATION_MESSAGE);
									}else{
										JOptionPane.showMessageDialog(null,"Nenhuma disciplina cadastrada.","Lista de disciplinas cadastradas",JOptionPane.WARNING_MESSAGE);
									}
									break;
								case 4:// imprimir todos os professores e disciplinas
									sisaloca.imprimeProfessoresEDisciplinas();
									break;
								case 5:// voltar
									menuConsultar = false;
								
							}
						}
						
						break;
					case 4:// remover
						boolean menuRemover = true;
						while(menuRemover){
							String[] opcoesRemover = {"1 - Remover professor.","2 - Remover Aluno.","3 - Remover " +
									"Disciplina.","4 - Remover Turma","5 - Desaloca professor de uma turma.","6 - Voltar."};
							menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
									"", "Menu Principal/Remover",JOptionPane.QUESTION_MESSAGE, null, opcoesRemover,opcoesRemover[0]); 
							opcao = Integer.parseInt(menu.charAt(0)+"");
							
							switch(opcao){
								case 1:// remover professor
									String matricula = JOptionPane.showInputDialog(null,"Digite a matrícula do professor que deseja remover: ","Remover professor",JOptionPane.QUESTION_MESSAGE);
									sisaloca.removeProfessor(matricula);
									JOptionPane.showMessageDialog(null,"Professor foi removido.","Remover professor",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 2:// remover aluno
									matricula = JOptionPane.showInputDialog(null,"Digite a matrícula do aluno que deseja remover: ","Remover aluno",JOptionPane.QUESTION_MESSAGE);
									sisaloca.removeAluno(matricula);
									JOptionPane.showMessageDialog(null,"Aluno removido do sistema!.","Remover aluno",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 3:// remover disciplina
									String codigo = JOptionPane.showInputDialog(null,"Digite o código da disciplina que deseja remover: ","Remover disciplina",JOptionPane.QUESTION_MESSAGE);
									sisaloca.removeDisciplina(codigo);
									JOptionPane.showMessageDialog(null,"Disciplina removida do sistema!.","Remover disciplina",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 4:// remover turma
									String codigoDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Remover turma",JOptionPane.QUESTION_MESSAGE);
									int numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Remover turma",JOptionPane.QUESTION_MESSAGE));
									sisaloca.removeTurma(codigoDisciplina, numTurma);
									JOptionPane.showMessageDialog(null,"A Turma foi removida da disciplina.","Remover turma",JOptionPane.INFORMATION_MESSAGE);
									
									break;
								case 5:// desalocar professor de uma turma
									String matriculaProf = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Desalocar professor de uma turma",JOptionPane.QUESTION_MESSAGE);
									String codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Desalocar professor de uma turma",JOptionPane.QUESTION_MESSAGE);
									numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Desalocar professor de uma turma",JOptionPane.QUESTION_MESSAGE));
									sisaloca.desalocaProfessorDeTurma(codDisciplina, numTurma, matriculaProf);
									JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
											" foi desalocado com sucesso à turma "+numTurma,"Desalocar professor de uma turma",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 6:// voltar
									menuRemover = false;
								
							}
						}
						
						break;
					case 5:// alocar / deslocar
						//alocaProfessorAturma *
//						desalocaProfessorATurma *
						boolean menuAlocar = true;
						while(menuAlocar){
							String[] opcoesAloca = {"1 - Alocar professor a uma turma.","2 - Desalocar professor a " +
							"uma turma.\n3 -  Voltar."};
							menu =""+JOptionPane.showInputDialog(null,"Escolha uma das opções abaixo" +
							"", "Menu Principal/(Alocar,Desalocar)",JOptionPane.QUESTION_MESSAGE, null, opcoesAloca,opcoesAloca[0]); 
							opcao = Integer.parseInt(menu.charAt(0)+"");
							switch(opcao){
								case 1:// alocar professor
									String matriculaProf = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Alocar professor em uma turma",JOptionPane.QUESTION_MESSAGE);
									String codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Alocar professor em uma turma",JOptionPane.QUESTION_MESSAGE);
									int numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Alocar professor em uma turma",JOptionPane.QUESTION_MESSAGE));
									sisaloca.alocaProfessorATurma(codDisciplina, numTurma, matriculaProf);
									JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
											" foi alocado com sucesso à turma "+numTurma,"Alocar professor em uma turma",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 2: // desalocar professor
									matriculaProf = JOptionPane.showInputDialog(null,"Digite a matricula do professor: ","Desalocar professor de uma turma",JOptionPane.QUESTION_MESSAGE);
									codDisciplina = JOptionPane.showInputDialog(null,"Digite o código da disciplina: ","Desalocar professor de uma turma",JOptionPane.QUESTION_MESSAGE);
									numTurma = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número da turma: ","Desalocar professor de uma turma",JOptionPane.QUESTION_MESSAGE));
									sisaloca.desalocaProfessorDeTurma(codDisciplina, numTurma, matriculaProf);
									JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
											" foi desalocado com sucesso à turma "+numTurma,"Desalocar professor de uma turma",JOptionPane.INFORMATION_MESSAGE);
									break;
								case 3: // voltar
									menuAlocar = false;
								
							}
						}
						
						break;
					case 6:// Ajuda
						JOptionPane.showMessageDialog(null,"SISTEMA DE ALOCAÇÃO DE DISCIPLINAS\n \nDESCRIÇÃO BREVE:\nO sistema de alocação de disciplinas é um sistema para gerenciar as alocações " +
								"entre turmas e professores e fazer várias verificações.","Ajuda",JOptionPane.INFORMATION_MESSAGE);
						break;
					case 7: // sair
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
		JOptionPane.showMessageDialog(null,"Software encerrado. xD");
	}

}
