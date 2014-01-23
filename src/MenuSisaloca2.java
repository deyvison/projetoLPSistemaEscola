import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;


public class MenuSisaloca2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SisalocaIF sisaloca = new Sisaloca();
		
		try{
			sisaloca.carregarProfessoresDeArquivo("professores.txt");
			sisaloca.carregarDisciplinasDeArquivo("disciplinas.txt");
			sisaloca.carregarTurmasDeDisciplinasEmArquivo("turmas.txt");
			sisaloca.carregaInteressesDeProfessoresPorDisciplinasDeArquivo("preferencias.txt");
		}catch(IOException err){
			return;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
		boolean sair = false;
		do{
			try{
				int opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Cadastrar.\n2 - Pesquisar.\n3 - Consultar/Obter\n4 - Remover.\n5 - Alocar.\n6 - Ajuda.\n7 - Sair."));
				switch(opcao){
					case 1: // cadastrar
						opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Cadastrar professor.\n2 - Cadastrar aluno.\n3 - Adicionar disciplina.\n" +
								"4 - Adicionar turma.\n5 -  Cadastrar nivel de interesse do professor por disciplina.\n6 - Cadastrar horario a turma.\n" +
								"7 - Aloca professor a turma.\n8 - Voltar."));
						switch(opcao){
							case 1: // cadastraProfessor
								String nome = JOptionPane.showInputDialog("Digite o nome do professor: ");
								String matricula = JOptionPane.showInputDialog("Digite a matrícula: ");
								sisaloca.cadastraProfessor(nome, matricula);
								JOptionPane.showMessageDialog(null,"Professor cadastrado: "+nome+", Matrícula:"+matricula);
								break;
							case 2: //cadastraAluno
								nome = JOptionPane.showInputDialog("Digite o nome do aluno: ");
								matricula = JOptionPane.showInputDialog("Digite a matrícula: ");
								sisaloca.cadastraAluno(nome, matricula);
								JOptionPane.showMessageDialog(null,"Aluno cadastrado: "+nome+", Matrícula:"+matricula);
								break;
							case 3: //adicionaDisciplina
								nome = JOptionPane.showInputDialog("Digite o nome da disciplina: ");
								String codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
								sisaloca.adicionaDisciplina(nome, codigo);
								JOptionPane.showMessageDialog(null,"Disciplina adicionada: "+nome+", Código:"+codigo);
								break;
							case 4://adicionaTurma
								codigo = JOptionPane.showInputDialog("Digite o código da disciplina: ");
								int numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
								sisaloca.adicionaTurma(codigo, numTurma);
								JOptionPane.showMessageDialog(null,"A turma:"+numTurma+", foi adicionada a disciplina de código"+codigo);
								break;
							case 5://cadastraNivelDeInteresseDeProfessorPorDisciplina
								
								String matriculaProf = JOptionPane.showInputDialog("Digite a matrícula do professor: ");
								String codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
								int nivelPreferencia =Integer.parseInt(JOptionPane.showInputDialog("Digite o nível de preferência que o professor tem por essa disciplina:\n" +
										"1 - Preferida\n2 - Razoável\n3 - Não gosto\n4 - Nenhuma preferência")); 
								sisaloca.cadastraNivelDeInteresseDeProfessorPorDisciplina(matriculaProf, codDisciplina, nivelPreferencia);
								JOptionPane.showMessageDialog(null,"Preferências do professor de matrícula "+matriculaProf+" Foram adicionadas. ");
								break;
							case 6://cadastraHorarioTurma
								codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina que deseja pesquisar: ");
								numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
								String diaString;
								int horaInicio;
								int horaFim;
								int dia = Integer.parseInt(JOptionPane.showInputDialog("Digite o número correspondente ao dia do horário:\n1 - Domingo.\n2 - Segunda.\n3 -  Terça.\n4 - " +
										"Quarta.\n5 - Quinta.\n6 - Sexta.\n7 - Sábado"));
								while(dia != 1 && dia != 2 && dia != 3 && dia != 4 && dia != 5 && dia != 6 && dia != 7 ){
									dia = Integer.parseInt(JOptionPane.showInputDialog("Opção inválida, digite novamente:\n1 - Domingo.\n2 - Segunda.\n3 -  Terça.\n4 - " +
											"Quarta.\n5 - Quinta.\n6 - Sexta.\n7 - Sábado"));
								}
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
								
								horaInicio = Integer.parseInt(JOptionPane.showInputDialog("Digite a hora do início da aula: "));
								horaFim = Integer.parseInt(JOptionPane.showInputDialog("Digite a hora do fim da aula: "));
								
								sisaloca.cadastraHorarioTurma(codDisciplina, numTurma, diaString, horaInicio, horaFim);
								break;
							case 7://alocaProfessorAturma *
								matriculaProf = JOptionPane.showInputDialog("Digite a matricula do professor: ");
								codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
								numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
								sisaloca.alocaProfessorATurma(codDisciplina, numTurma, matriculaProf);
								JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
										" foi alocado com sucesso à turma "+numTurma);
								break;
							case 8:// voltar
								break;
							default:
								JOptionPane.showMessageDialog(null,"Opção inválida.");
							
						}
						break;
					case 2: // pesquisar

						opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Pesquisar professor pelo nome.\n2 - Pesquisar professor pela ma" +
								"trícula.\n3 - Pesquisar aluno pelo nome.\n4 - Pesquisar aluno pela matrícula.\n5 - Pesquisar Disciplina.\n6 - " +
								"Pesquisar horários do professor.\n7 - Pesquisar turmas do professor.\n8 - Pesquisar disciplinas do professor." +
								"\n9 - Pesquisar turma.\n10 - Voltar."));
						switch(opcao){
							case 1: // pesquisar professor pelo nome
								String nome = JOptionPane.showInputDialog("Digite o nome do professor: ");
								List <Professor> resultado = sisaloca.pesquisaProfessorPeloNome(nome);
								if(resultado.size()!= 0){
									String texto ="Professores encontrados com o nome:"+nome+".\n";
									for(Professor p : resultado){
										texto +="\n"+p.toString();
									}
									JOptionPane.showMessageDialog(null,texto);
								}else{
									JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum professor com nome:"+nome);
								}
								break;
							case 2:// pesquisar professor pela matricula
								String matricula = JOptionPane.showInputDialog("Digite a matricula do professor: ");
								Professor p = sisaloca.pesquisaProfessorPelaMatricula(matricula);
								JOptionPane.showMessageDialog(null,p.toString());
								break;
							case 3:// pesquisar aluno pelo nome
								nome = JOptionPane.showInputDialog("Digite o nome do aluno que deseja pesquisar: ");
								List <Aluno> alunos = sisaloca.pesquisaAlunoPeloNome(nome);
								if(alunos.size()!= 0){
									String texto ="Alunos encontrados:\n";
									for(Aluno a : alunos){
										texto += a.toString()+"\n";
									}
									JOptionPane.showMessageDialog(null,texto);
								}else{
									JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum aluno com este nome.");
								}
								break;
							case 4:// pesquisar aluno pela matricula
								matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno que deseja pesquisar: ");
								Aluno a = sisaloca.pesquisaAlunoPelaMatricula(matricula);
								JOptionPane.showMessageDialog(null,"Aluno encontrado:"+ a.toString());
								break;
							case 5:// pesquisar disciplina
								String codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina que deseja pesquisar: ");
								Disciplina d = sisaloca.pesquisaDisciplina(codDisciplina);
								JOptionPane.showMessageDialog(null,"Disciplina encontrada: "+d.toString());
								break;
							case 6:// pesquisar horarios do professor
								String matriculaProfessor = JOptionPane.showInputDialog("Digite a matricula do professor: ");
								List <Horario> horarios = sisaloca.pesquisaHorariosProfessor(matriculaProfessor);
								if(horarios.size()!= 0){
									String texto ="Horários encontrado:\n";
									for(Horario h : horarios){
										texto += h.toString()+"\n";
									}
									JOptionPane.showMessageDialog(null,texto);
								}else{
									JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum horário para este professor.");
								}
								break;
							case 7:// pesquisar turmas do professor
								matriculaProfessor = JOptionPane.showInputDialog("Digite a matricula do professor: ");
								List <Turma> turmas = sisaloca.pesquisaTurmasDoProfessor(matriculaProfessor);
								if(turmas.size()!= 0){
									String texto ="Turmas encontradas:\n";
									for(Turma t : turmas){
										texto += t.toString()+"\n";
									}
									JOptionPane.showMessageDialog(null,texto);
								}else{
									JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma turma para este professor.");
								}
								break;
							case 8:// pesquisar disciplinas do professor
								matriculaProfessor = JOptionPane.showInputDialog("Digite a matricula do professor: ");
								List <Disciplina> disciplinas = sisaloca.pesquisaDisciplinasDoProfessor(matriculaProfessor);
								if(disciplinas.size()!= 0){
									String texto ="Disciplinas encontradas:\n";
									for(Disciplina dis : disciplinas){
										texto += dis.toString()+"\n";
									}
									JOptionPane.showMessageDialog(null,texto);
								}else{
									JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma disciplina para este professor.");
								}
								break;
							case 9:// pesquisar turma
								codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
								int numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
								Turma t = sisaloca.pesquisaTurma(codDisciplina, numTurma);
								JOptionPane.showMessageDialog(null,"Turma encontrada:\n"+t.toString());
								break;
							case 10:// voltar
								break;
							default:
								JOptionPane.showMessageDialog(null,"Opção inválida.");
						}
						
						break;
					case 3:// Consultar/ obter

						opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Consultar disciplinas pela preferência do professor.\n2 - Obter " +
								"lista de professores cadastrados.\n3 - Obter lista de disciplinas.\n4 - Imprimir professores e disciplinas.\n5 - Voltar."));
						switch(opcao){
							case 1: // consultar disciplinas pela preferencia do professor
								String matriculaProfessor = JOptionPane.showInputDialog("Digite a matrícula do professor: ");
								int nivelPref = Integer.parseInt(JOptionPane.showInputDialog("Digite o nível de preferência das disciplinas que deseja consultar:\n" +
										"1 - Preferida\n2 - Razoável\n3 - Não gosto\n4 - Nenhuma preferência"));
								while(nivelPref != 1 && nivelPref != 2 && nivelPref != 3 && nivelPref != 4){
									nivelPref = Integer.parseInt(JOptionPane.showInputDialog("Entrava inválida, digite novamente:\n" +
											"1 - Preferida\n2 - Razoável\n3 - Não gosto\n4 - Nenhuma preferência"));
								}
								List <Disciplina> dis = sisaloca.consultaDisciplinasPorPreferenciaPorProfessor(matriculaProfessor, nivelPref);
								if(dis.size()!= 0){
									String texto = "Disciplinas de preferência: "+nivelPref+"\n";
									for(Disciplina disciplina : dis){
										texto += disciplina.getNome()+"\n";
									}
									JOptionPane.showMessageDialog(null,texto);
								}else{
									JOptionPane.showMessageDialog(null,"Não existe disciplinas com o nível de preferência: "+nivelPref);
								}
								break;
							case 2:// obter lista de todos professores cadastrados
								List <Professor> prof = sisaloca.obterListaDeProfessores();
								if(prof.size()!= 0){
									String texto ="Professores:\n";
									for(Professor p : prof){
										texto += p.toString()+"\n";
									
									}
									JOptionPane.showMessageDialog(null,texto);
								}else{
									JOptionPane.showMessageDialog(null,"Não existe nenhum professor cadastrado.");
								}
								break;
							case 3://obter lista de todas as disciplinas cadastradas
								List<Disciplina> disciplinas = sisaloca.obterListaDeDisciplinas();
								if(disciplinas.size()!=0){
									String texto ="Disciplinas cadastradas:\n";
									for(Disciplina d : disciplinas){
										texto  += d.toString()+"\n";
									}
									JOptionPane.showMessageDialog(null,texto);
								}else{
									JOptionPane.showMessageDialog(null,"Nenhuma disciplina cadastrada.");
								}
								break;
							case 4:// imprimir todos os professores e disciplinas
								sisaloca.imprimeProfessoresEDisciplinas();
								break;
							case 5:// voltar
								break;
							default:
								JOptionPane.showMessageDialog(null,"Opção inválida.");
						}
						break;
					case 4:// remover

						opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Remover professor.\n2 - Remover Aluno.\n3 - Remover " +
								"Disciplina.\n4 - Remover Turma\n5 - Desaloca professor de uma turma.\n6 - Voltar."));
						switch(opcao){
							case 1:// remover professor
								String matricula = JOptionPane.showInputDialog("Digite a matrícula do professor que deseja remover: ");
								sisaloca.removeProfessor(matricula);
								JOptionPane.showMessageDialog(null,"Professor foi removido.");
								break;
							case 2:// remover aluno
								matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno que deseja remover: ");
								sisaloca.removeAluno(matricula);
								JOptionPane.showMessageDialog(null,"Aluno removido do sistema!.");
								break;
							case 3:// remover disciplina
								String codigo = JOptionPane.showInputDialog("Digite o código da disciplina que deseja remover: ");
								sisaloca.removeDisciplina(codigo);
								JOptionPane.showMessageDialog(null,"Disciplina removida do sistema!.");
								break;
							case 4:// remover turma
								String codigoDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
								int numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
								sisaloca.removeTurma(codigoDisciplina, numTurma);
								JOptionPane.showMessageDialog(null,"A Turma foi removida da disciplina.");
								
								break;
							case 5:// desalocar professor de uma turma
								String matriculaProf = JOptionPane.showInputDialog("Digite a matricula do professor: ");
								String codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
								numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
								sisaloca.desalocaProfessorDeTurma(codDisciplina, numTurma, matriculaProf);
								JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
										" foi desalocado com sucesso à turma "+numTurma);
								break;
							case 6:// voltar
								break;
							default:
								JOptionPane.showMessageDialog(null,"Opção inválida.");
						}
						break;
					case 5:// alocar / deslocar
						//alocaProfessorAturma *
//						desalocaProfessorATurma *
						opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Alocar professor a uma turma.\n2 - Desalocar professor a " +
								"uma turma.\n3 -  Voltar."));
						switch(opcao){
							case 1:// alocar professor
								String matriculaProf = JOptionPane.showInputDialog("Digite a matricula do professor: ");
								String codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
								int numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
								sisaloca.alocaProfessorATurma(codDisciplina, numTurma, matriculaProf);
								JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
										" foi alocado com sucesso à turma "+numTurma);
								break;
							case 2: // desalocar professor
								matriculaProf = JOptionPane.showInputDialog("Digite a matricula do professor: ");
								codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
								numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
								sisaloca.desalocaProfessorDeTurma(codDisciplina, numTurma, matriculaProf);
								JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
										" foi desalocado com sucesso à turma "+numTurma);
								break;
							case 3: // voltar
								break;
							default:
								JOptionPane.showMessageDialog(null,"Opção inválida.");
						}
						break;
					case 6:// Ajuda
						JOptionPane.showMessageDialog(null,"SISTEMA DE ALOCAÇÃO DE DISCIPLINAS\n \nDESCRIÇÃO BREVE:\nO sistema de alocação de disciplinas é um sistema para gerenciar as alocações " +
								"entre turmas e professores e fazer várias verificações.");
						break;
					case 7: // sair
						sair = true;
						break;
					default:
						JOptionPane.showMessageDialog(null,"Opção inválida.");
				}
			}catch(NumberFormatException err){
				JOptionPane.showMessageDialog(null,"Entrada inválida.");
			}catch(Exception er){
				JOptionPane.showMessageDialog(null,er.getMessage());
			}
			
			
		}while(!sair);
		try{
			sisaloca.gravarProfessoresEmArquivo("professores.txt");
			sisaloca.gravarDisciplinasEmArquivo("disciplinas.txt");
			
			sisaloca.gravarTurmasDeDisciplinasEmArquivo("turmas.txt");
			sisaloca.gravaInteressesDeProfessoresPorDisciplinasEmArquivo("preferencias.txt");
		}catch(IOException err){
		
		
			
		}
		JOptionPane.showMessageDialog(null,"Software encerrado. xD");
	}

}
