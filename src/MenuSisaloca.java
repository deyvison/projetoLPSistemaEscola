import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;


public class MenuSisaloca {
	
	public static void main(String args[]){
		SisalocaIF sisaloca = new Sisaloca();
		boolean sair = false;
		int opcao=0;
		try{
			sisaloca.carregarProfessoresDeArquivo("professores.txt");
			sisaloca.carregarDisciplinasDeArquivo("disciplinas.txt");
			sisaloca.carregarTurmasDeDisciplinasEmArquivo("turmas.txt");
			sisaloca.carregaInteressesDeProfessoresPorDisciplinasDeArquivo("preferencias.txt");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e.getMessage());
			e.printStackTrace();
		}
			do{
				try{
				
					opcao = Integer.parseInt(JOptionPane.showInputDialog("Bem vindo!\nDigite o número correspondende ao que deseja fazer:\n" +
							"1 - Professor.\n2 - Aluno.\n3 - Disciplina.\n4 - Turma.\n5 - Alocar.\n6 - Ajuda.\n7 - Sair.")); // Menu Principal
				
					
					
				
				
						switch(opcao){ // switch Menu principal
							case 1: // caso 1 - Professor
								int opcao11=0;
							
								opcao11 = Integer.parseInt(JOptionPane.showInputDialog("1 - Cadastrar.\n2 - Remover.\n" +
										"3 - Consultar/Obter.\n4 - Pesquisar.\n5 - Voltar.")); //Opções do professor
								switch(opcao11){
									case 1: // caso 1 - Cadastrar
										int opcao111 = 0;
										opcao111 = Integer.parseInt(JOptionPane.showInputDialog("1 - Cadastrar Professor.\n 2 - Cadastrar Nível de Interesse" +
												"de Professor Por Disciplina.\n3 - Voltar.")); // opções de cadastrar
										switch(opcao111){
											case 1: // caso 1 (cadastrar professor)
												String nome = JOptionPane.showInputDialog("Digite o nome do professor: ");
												String matricula = JOptionPane.showInputDialog("Digite a matrícula do professor: ");
												sisaloca.cadastraProfessor(nome, matricula);
												JOptionPane.showMessageDialog(null,"Professor "+nome+" Cadastrado com sucesso!");
												break;
											case 2: // caso 2 (cadastrar as preferências)
												String matriculaProf = JOptionPane.showInputDialog("Digite a matrícula do professor: ");
												String codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
												int nivelPreferencia =Integer.parseInt(JOptionPane.showInputDialog("Digite o nível de preferência que o professor tem por essa disciplina:\n" +
														"1 - Preferida\n2 - Razoável\n3 - Não gosto\n4 - Nenhuma preferência")); 
												sisaloca.cadastraNivelDeInteresseDeProfessorPorDisciplina(matriculaProf, codDisciplina, nivelPreferencia);
												JOptionPane.showMessageDialog(null,"Preferências do professor de matrícula "+matriculaProf+" Foram adicionadas. ");
												break;
											case 3: // voltar
												break;
											default: // opção inválida
												JOptionPane.showMessageDialog(null,"Opção inválida!");
										}
										break;
									case 2: // Remover
										String matriculaProf = JOptionPane.showInputDialog("Digite a matrícula do professor que deseja remover: ");
										sisaloca.removeProfessor(matriculaProf);
										JOptionPane.showMessageDialog(null,"Professor de matrícula: "+matriculaProf+" Foi removido");
										
										
										break;
									case 3: // Consultar/Obter
										int opcao131 = 0;
										opcao131 = Integer.parseInt(JOptionPane.showInputDialog("1 - Obter Professores e disciplinas cadastradas.\n2 - Obter" +
												"lista de todos os professores cadastrados.\n3 - Voltar."));
										switch(opcao131){
											case 1: // obter lista de professores e disciplinas
												sisaloca.imprimeProfessoresEDisciplinas();
												break;
											case 2: // obter todos os professores
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
											case 3: // voltar
												break;
											default: // opção inválida
												JOptionPane.showMessageDialog(null,"Opção inválida!");
											 
										}
										break;
									case 4: // Pesquisar
										int opcao141 = 0;
										opcao141 = Integer.parseInt(JOptionPane.showInputDialog("1 - Pesquisar Professor pelo nome.\n2 - Pesquisar" +
												"professor pela matrícula.\n3 - Consultar disciplinas por preferência do professor.\n4 - Pesquisar" +
												"horários do professor.\n5 - Pesquisar turmas do professor.\n6 - Pesquisar Disciplinas do professor.\n7 - " +
												"Voltar."));
										switch(opcao141){
											case 1: // pesquisar prof pelo nome
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
											case 2: // pesquisar prof pela matricula
												String matricula = JOptionPane.showInputDialog("Digite a matricula do professor: ");
												Professor p = sisaloca.pesquisaProfessorPelaMatricula(matricula);
												JOptionPane.showMessageDialog(null,p.toString());
												break;
											case 3:// Consultar disciplinas por preferência do professor
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
													for(Disciplina d : dis){
														texto += d.getNome()+"\n";
													}
													JOptionPane.showMessageDialog(null,texto);
												}else{
													JOptionPane.showMessageDialog(null,"Não existe disciplinas com o nível de preferência: "+nivelPref);
												}
												break;
											case 4: //Pesquisar Horários do professor
												matriculaProfessor = JOptionPane.showInputDialog("Digite a matricula do professor: ");
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
											case 5:// Pesquisar turmas do professor
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
											case 6: //Pesquisar Disciplinas do professor
												matriculaProfessor = JOptionPane.showInputDialog("Digite a matricula do professor: ");
												List <Disciplina> disciplinas = sisaloca.pesquisaDisciplinasDoProfessor(matriculaProfessor);
												if(disciplinas.size()!= 0){
													String texto ="Disciplinas encontradas:\n";
													for(Disciplina d : disciplinas){
														texto += d.toString()+"\n";
													}
													JOptionPane.showMessageDialog(null,texto);
												}else{
													JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma disciplina para este professor.");
												}
												break;
											case 7: // voltar
												break;
											default:
												JOptionPane.showMessageDialog(null,"Opção inválida!");
												
										}
										break;
									case 5: // Voltar
										break;
										
										
								}
							
								break;
							case 2: //Aluno
								int opcao121 = 0;
								opcao121 =Integer.parseInt(JOptionPane.showInputDialog("1 - Cadastrar.\n2 - Pesquisar.\n3 - Remover.\n4 - Voltar"));
								switch(opcao121){
									case 1: //Cadastrar
										String nome = JOptionPane.showInputDialog("Digite o nome do aluno para cadastrar: ");
										String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno: ");
										sisaloca.cadastraAluno(nome, matricula);
										JOptionPane.showMessageDialog(null,"Aluno cadastrado com sucesso.");
										break;
									case 2:// Pesquisar
										int opcao122 = 0;
										opcao122 =Integer.parseInt(JOptionPane.showInputDialog("1 - Pesquisar aluno pelo nome.\n2 - Pesq" +
												"uisar aluno pela matrícula.\n3 - Voltar;"));
										switch(opcao122){ //opções de pesquisar
											case 1: //pesquisar aluno pelo nome
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
											case 2: // pesquisar aluno pela matricula
												matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno que deseja pesquisar: ");
												Aluno a = sisaloca.pesquisaAlunoPelaMatricula(matricula);
												JOptionPane.showMessageDialog(null,"Aluno encontrado:"+ a.toString());
												break;
											case 3: // voltar
												break;
											default:
												JOptionPane.showMessageDialog(null,"Opção inválida.");
										}
										break;
									case 3: // Remover
										matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno que deseja remover: ");
										sisaloca.removeAluno(matricula);
										JOptionPane.showMessageDialog(null,"Aluno removido do sistema!.");
										break;
									case 4: //Voltar
										break;
									default:
										JOptionPane.showMessageDialog(null,"Opção inválida.");
								}
								break;
							case 3: //Disciplina
								int opcao131 = 0;
								opcao131 =Integer.parseInt(JOptionPane.showInputDialog("1 - Adicionar.\n2 - Remover.\n3 - Pesquisar.\n4 - Consu" +
										"ltar/Obter.\n5 - Voltar."));
								switch(opcao131){
									case 1: // Adicinar 
										int opcao311 = 0;
										opcao311 =Integer.parseInt(JOptionPane.showInputDialog("1 - Adicionar disciplina.\n2 - Cadastrar nível" +
												"de interesse do professor por uma disciplina.\n3 - Voltar"));
										switch(opcao311){
											case 1: // adicionar disciplina
												String nomeDis = JOptionPane.showInputDialog("Digite o nome da disciplina que deseja cadastrar: ");
												String codigoDis = JOptionPane.showInputDialog("Digite o código da disciplina que deseja cadastrar: ");
												sisaloca.adicionaDisciplina(nomeDis, codigoDis);
												JOptionPane.showMessageDialog(null,"Disciplina "+nomeDis+" De código "+codigoDis+", Foi adicionada.");
												break;
											case 2: // cadastrar nivel de interesse pela disciplina
												
												String matriculaProf = JOptionPane.showInputDialog("Digite a matrícula do professor: ");
												String codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
												int nivelPreferencia =Integer.parseInt(JOptionPane.showInputDialog("Digite o nível de preferência que o professor tem por essa disciplina:\n" +
														"1 - Preferida\n2 - Razoável\n3 - Não gosto\n4 - Nenhuma preferência")); 
												sisaloca.cadastraNivelDeInteresseDeProfessorPorDisciplina(matriculaProf, codDisciplina, nivelPreferencia);
												JOptionPane.showMessageDialog(null,"Preferências do professor de matrícula "+matriculaProf+" Foram adicionadas. ");
												break;
											case 3: // voltar
												break;
											default:
												JOptionPane.showMessageDialog(null,"Opção inválida.");
										}
										break;
										
									case 2: // remover
										String codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina que deseja remover: ");
										sisaloca.removeDisciplina(codDisciplina);
										JOptionPane.showMessageDialog(null,"Disciplina de código: "+codDisciplina+" Foi removida.");
										break;
									case 3: // pesquisar
										int opcao331 = 0;
										opcao331 = Integer.parseInt(JOptionPane.showInputDialog("1 - Pesquisar disciplina.\n2 - Consultar disciplinas" +
												" por preferência do professor.\n3 - Voltar."));
										switch(opcao331){
											case 1: // pesquisar disciplina
												codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina que deseja pesquisar: ");
												Disciplina d = sisaloca.pesquisaDisciplina(codDisciplina);
												JOptionPane.showMessageDialog(null,"Disciplina encontrada: "+d.toString());
												break;
											case 2: // consultar disciplinas pelas preferencias do prof
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
											case 3: // voltar
												break;
											default:
												JOptionPane.showMessageDialog(null,"Opção inválida.");
										}
										break;
									case 4: // consultar/obter
										int opcao341 = Integer.parseInt(JOptionPane.showInputDialog("1 - Consultar todas as disciplinas.\n2 - Consultar " +
												"todos os professores e disciplinas cadastrados.\n3 - Voltar."));
										switch(opcao341){
											case 1: // consultar todas as disciplinas
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
											case 2: //consultar todos os professores e disciplinas
												sisaloca.imprimeProfessoresEDisciplinas();
												break;
											case 3:// voltar
												break;
											default:
												JOptionPane.showMessageDialog(null,"Opção inválida.");
										}
										break;
									case 5: // voltar
										break;
									default:
										JOptionPane.showMessageDialog(null,"Opção inválida.");
								}
								break;
							case 4://Turma
								int opcao141 = 0;
								opcao141 = Integer.parseInt(JOptionPane.showInputDialog("1 - Adicionar.\n2 - Remover.\n3 - Pesquisar.\n4 - Voltar."));
								switch(opcao141){
									case 1: // adicionar
										int opcao411 = Integer.parseInt(JOptionPane.showInputDialog("1 - Adicionar Turma.\n2 - Adicionar um horario par" +
												"a determinada turma.\n3 - Voltar"));
										switch(opcao411){
											case 1:// adicionar turma
												String codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina que deseja pesquisar: ");
												int numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
												sisaloca.adicionaTurma(codDisciplina, numTurma);
												JOptionPane.showMessageDialog(null,"Disciplina de código:"+codDisciplina+", foi adicionada à turma: "+numTurma);
												break;
											case 2:// cadastrar horario turma
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
											case 3:
												break;
											default:
												JOptionPane.showMessageDialog(null,"Opção inválida.");
										}
										break;
									case 2:// remover
										String codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
										int numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
										sisaloca.removeTurma(codDisciplina, numTurma);
										JOptionPane.showMessageDialog(null,"A turma de número:"+numTurma+" foi removida da disciplina.");
										break;
									case 3:// pesquisar
										codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
										numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
										Turma t = sisaloca.pesquisaTurma(codDisciplina, numTurma);
										JOptionPane.showMessageDialog(null,"Turma encontrada:\n"+t.toString());
										break;
									case 4:// voltar
										break;
									default:
										JOptionPane.showMessageDialog(null,"Opção inválida.");
								}
								break;
							case 5: //Alocar
								int opcao511 = 0;
								opcao511 = Integer.parseInt(JOptionPane.showInputDialog("1 - Alocar um professor a turma.\n2 - Desalocar um professor a turma.\n3 - Voltar"));
								switch(opcao511){
									case 1: // alocar professor a turma
										String matriculaProf = JOptionPane.showInputDialog("Digite a matricula do professor: ");
										String codDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
										int numTurma = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da turma: "));
										sisaloca.alocaProfessorATurma(codDisciplina, numTurma, matriculaProf);
										JOptionPane.showMessageDialog(null,"Professor de matrícula:"+matriculaProf+", da disciplina "+codDisciplina+"" +
												" foi alocado com sucesso à turma "+numTurma);
										break;
									case 2: // desalocar professor a turma
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
							case 6://Ajuda
								JOptionPane.showMessageDialog(null,"SISTEMA DE ALOCAÇÃO DE DISCIPLINAS\n \nDESCRIÇÃO BREVE:\nO sistema de alocação de disciplinas é um sistema para gerenciar as alocações " +
										"entre turmas e professores e fazer várias verificações.");
								break;
							case 7://Sair
								sair = true;
								break;
							default:
								JOptionPane.showMessageDialog(null,"Opção inválida!!");
						}
						
						
						
						
						
			}catch(NumberFormatException err){
				JOptionPane.showMessageDialog(null,"Entrava inválida. ");
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
			
			JOptionPane.showMessageDialog(null,"Sistema finalizado ;D");
		
		
	}

}
