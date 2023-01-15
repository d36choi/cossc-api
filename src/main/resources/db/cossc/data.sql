INSERT INTO cossc.TAG (tag_id, created_date, updated_date, name, created_by, updated_by) VALUES (1, '2023-01-15 17:07:01', '2023-01-15 17:07:00', 'python', 'choi', 'choi');
INSERT INTO cossc.TAG (tag_id, created_date, updated_date, name, created_by, updated_by) VALUES (2, '2023-01-15 17:07:18', '2023-01-15 17:07:19', 'java', 'choi', 'choi');
INSERT INTO cossc.TAG (tag_id, created_date, updated_date, name, created_by, updated_by) VALUES (3, '2023-01-15 17:07:29', '2023-01-15 17:07:29', 'js', 'js', 'choi');

INSERT INTO cossc.QUIZ (quiz_id, title, description, user_id, type, created_date, created_by, updated_date, updated_by,
                        multiple_choice_question_id, ox_choice_question_id, tag_id)
VALUES (1, 'OS 의 뜻은 운영체제다', '테스트용', 1, 'ox', '2023-01-15 23:38:39', '1', '2023-01-15 23:38:40', '1', null, null, 1);

INSERT INTO cossc.QUIZ (quiz_id, title, description, user_id, type, created_date, created_by, updated_date, updated_by,
                        multiple_choice_question_id, ox_choice_question_id, tag_id)
VALUES (2, 'java는 구린언어다', '테스트용', 1, 'ox', '2023-01-15 23:38:39', '1', '2023-01-15 23:38:40', '1', null, null, 1);

INSERT INTO cossc.QUIZ (quiz_id, title, description, user_id, type, created_date, created_by, updated_date, updated_by,
                        multiple_choice_question_id, ox_choice_question_id, tag_id)
VALUES (3, 'private의 허용범위는 선언 클래스다', '테스트용', 1, 'ox', '2023-01-15 23:38:39', '1', '2023-01-15 23:38:40', '1', null, null, 1);

INSERT INTO cossc.OX_CHOICE_QUESTION (answer_choice, quiz_id, ox_choice_question_id) VALUES (1, 1, 1);
INSERT INTO cossc.OX_CHOICE_QUESTION (answer_choice, quiz_id, ox_choice_question_id) VALUES (0, 2, 2);
INSERT INTO cossc.OX_CHOICE_QUESTION (answer_choice, quiz_id, ox_choice_question_id) VALUES (1, 3, 3);


