INSERT INTO cossc.USER (user_id, auth_provider, description, email, img, name, refresh_token, role, level, tag_id, created_date, created_by, updated_date, updated_by, oauth_key) VALUES (1, 'GITHUB', null, 'yuio1126@gmail.com', 'https://avatars.githubusercontent.com/u/40600306?v=4', 'D36CHOI', 'eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjb3NzYyIsImlhdCI6MTY3Mzc5MjgyNSwiZXhwIjoxNjc0Mzk3NjI1fQ.DOpmSu-vpvfjQ0zYYoZAdBwWEDU7fJrUp17CVso0ZJUacbYppNYOCojnh8QN7Zf6uY3T4l6CEfTxPqpxBHXsfg', 'USER', null, null, '2023-01-15 14:27:05', 'D36CHOI', '2023-01-15 14:27:05', 'D36CHOI', '40600306');


INSERT INTO cossc.TAG (tag_id, created_date, updated_date, name) VALUES (1, '2023-01-15 17:07:01', '2023-01-15 17:07:00', 'python');
INSERT INTO cossc.TAG (tag_id, created_date, updated_date, name) VALUES (2, '2023-01-15 17:07:18', '2023-01-15 17:07:19', 'java');
INSERT INTO cossc.TAG (tag_id, created_date, updated_date, name) VALUES (3, '2023-01-15 17:07:29', '2023-01-15 17:07:29', 'javascript');
INSERT INTO cossc.TAG (tag_id, created_date, updated_date, name) VALUES (3, '2023-01-15 17:07:29', '2023-01-15 17:07:29', 'typescript');

INSERT INTO cossc.QUIZ (quiz_id, title, description, type, created_date, created_by, updated_date, updated_by,
                        multiple_choice_question_id, ox_choice_question_id, tag_id)
VALUES (1, 'OS 의 뜻은 운영체제다', '테스트용', 'ox', '2023-01-15 23:38:39', '1', '2023-01-15 23:38:40', '1', null, null, 1);

INSERT INTO cossc.QUIZ (quiz_id, title, description, type, created_date, created_by, updated_date, updated_by,
                        multiple_choice_question_id, ox_choice_question_id, tag_id)
VALUES (2, 'java는 구린언어다', '테스트용', 'ox', '2023-01-15 23:38:39', '1', '2023-01-15 23:38:40', '1', null, null, 1);

INSERT INTO cossc.QUIZ (quiz_id, title, description, type, created_date, created_by, updated_date, updated_by,
                        multiple_choice_question_id, ox_choice_question_id, tag_id)
VALUES (3, 'private의 허용범위는 선언 클래스다', '테스트용',  'ox', '2023-01-15 23:38:39', '1', '2023-01-15 23:38:40', '1', null, null, 1);

INSERT INTO cossc.OX_CHOICE_QUESTION (answer_choice, ox_choice_question_id) VALUES (1, 1);
update cossc.QUIZ
set ox_choice_question_id = 1
where quiz_id = 1;
INSERT INTO cossc.OX_CHOICE_QUESTION (answer_choice, ox_choice_question_id) VALUES (0, 2);
update cossc.QUIZ
set ox_choice_question_id = 2
where quiz_id = 2;
INSERT INTO cossc.OX_CHOICE_QUESTION (answer_choice, ox_choice_question_id) VALUES (1, 3);
update cossc.QUIZ
set ox_choice_question_id = 3
where quiz_id = 3;


