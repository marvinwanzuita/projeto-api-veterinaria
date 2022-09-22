

INSERT INTO tb_tutor (email, nome, telefone) VALUES ('giovani@gmail.com', 'Giovanni Passos', '(68) 2350-2365');
INSERT INTO tb_tutor (email, nome, telefone) VALUES ('leandra@gmail.com', 'Leandra Norte', '(85) 3653-6108');
INSERT INTO tb_tutor (email, nome, telefone) VALUES	('girlene@gmail.com', 'Girlene Carvalho', '(67) 2788-5763');
INSERT INTO tb_tutor (email, nome, telefone) VALUES ('rita@gmail.com', 'Rita Nazare', '(51) 2188-9115');
INSERT INTO tb_tutor (email, nome, telefone) VALUES ('evelyn@gmail.com', 'Evelyn Silva', '(55) 3750-2738');
INSERT INTO tb_tutor (email, nome, telefone) VALUES ('patricia@gmail.com', 'Patrícia Teodoro', '(48) 2184-1815');
INSERT INTO tb_tutor (email, nome, telefone) VALUES ('diogo@gmail.com', 'Diogo Maia', '(84) 3515-1534');
INSERT INTO tb_tutor (email, nome, telefone) VALUES ('simone@gmail.com', 'Simone Matos', '(69) 3394-3724');
INSERT INTO tb_tutor (email, nome, telefone) VALUES ('lina@gmail.com', 'Lina Paulo', '(79) 3886-7203');
INSERT INTO tb_tutor (email, nome, telefone) VALUES ('anacristina@gmail.com', 'Ana Cristina Passos', '(98) 3989-0721');

INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2016-10-05', 'Pitico', 0, 5);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2000-07-14', 'Pipo', 0, 8);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2008-12-26', 'Bebel', 1, 6);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2016-12-31', 'Lolita', 1, 9);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2015-09-30', 'Bilú', 0, 7);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2010-01-18', 'Laika', 1, 3);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2015-07-15', 'Brutus', 0, 1);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2016-01-20', 'Spock', 0, 8);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2003-10-12', 'Babi', 1, 4);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2007-11-20', 'Guto', 0, 6);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2009-11-12', 'Lulu', 1, 9);
INSERT INTO tb_paciente (data_nascimento, nome, sexo, tutor_id) VALUES ('2013-04-21', 'Pops', 1, 2);

INSERT INTO tb_procedimento (descricao) VALUES ('Vacinação');
INSERT INTO tb_procedimento (descricao) VALUES ('Consulta pediátrica');
INSERT INTO tb_procedimento (descricao) VALUES ('Curativos');
INSERT INTO tb_procedimento (descricao) VALUES ('Corte de unha');
INSERT INTO tb_procedimento (descricao) VALUES ('Odontológico');
INSERT INTO tb_procedimento (descricao) VALUES ('Tosa');

INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-11-22', '08:30', 15.3, 0, 1);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-11-04', '10:00', 20.4, 2, 2);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-12-04', '12:30', 25.4, 3, 3);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-11-06', '14:00', 4.6, 1, 4);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-12-13', '10:30', 7.9, 2, 5);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-10-08', '10:30',  4.6, 3, 6);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-12-26', '15:30', 30.6, 2, 7);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-11-13', '10:30', 18.7, 0, 8);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2020-10-10', '17:00', 34.8, 0, 9);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-10-08', '11:30', 42.7, 0, 10);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-12-23', '09:00', 38.4, 2, 11);
INSERT INTO tb_agendamento (data_atendimento, hora_atendimento, peso_paciente, status_agendamento, paciente_id) VALUES ('2022-10-27', '16:00', 4.9, 3, 12);

INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 1, 6)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 1, 2)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 1, 4)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 2, 1)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 3, 2)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 4, 1)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 5, 6)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 5, 3)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 6, 6)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 7, 5)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 8, 4)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 9, 3)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 10, 2)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 11, 1)  
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 12, 1)
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 12, 5)
INSERT INTO  TB_AGENDAMENTO_PROCEDIMENTOS(AGENDAMENTO_ID, PROCEDIMENTOS_ID) VALUES ( 12, 3)


















