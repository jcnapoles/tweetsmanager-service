
--INSERT INTO user (id, id_str, name, screen_name, followers_count) VALUES (2, '2', 'cesar', 'jcnapoles', 5);
--INSERT INTO user (id, id_str, name, screen_name, followers_count) VALUES (3, '3', 'julio', 'jcpuente', 10);
--INSERT INTO place (id, name, place_type, country_code, country) VALUES (3, 'Barcelona', 'city', 'ES', 'Spain');


--INSERT INTO tweet (id, id_str, text, validation, language, user_id, place_id) VALUES (1, '1','Servicio para manejar tweets', false,'ES',2,3);

--INSERT INTO hashtag (id, text) VALUES (4, 'development');
--INSERT INTO hashtag (id, text) VALUES (5, 'microservices');
--INSERT INTO hashtag (id, text) VALUES (6, 'Spain');
--INSERT INTO hashtag (id, text) VALUES (7, 'accionacompany');
--INSERT INTO hashtag (id, text) VALUES (8, 'backend');
--INSERT INTO hashtag (id, text) VALUES (9, 'test');
--INSERT INTO hashtag (id, text) VALUES (10, 'tweet');
--INSERT INTO hashtag (id, text) VALUES (11, 'tweet2');

--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (1, 4);
--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (1, 5);
--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (1, 6);
--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (1, 7);
--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (1, 8);

--INSERT INTO tweet (id, id_str, text, validation, language, user_id, place_id) VALUES (2, '2','Otro Servicio', false,'ES',2,3);

--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (2, 8);
--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (2, 7);
--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (2, 6);


--INSERT INTO tweet (id, id_str, text, validation, language, user_id, place_id) VALUES (3, '3','Servicio de Prueba', false,'IT',3,3);

--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (3, 8);
--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (3, 7);
--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (3, 11);

--INSERT INTO tweet (id, id_str, text, validation, language, user_id, place_id) VALUES (4, '4','Spring Boot', false,'FR',3,3);

--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (4, 8);
--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (4, 9);
--INSERT INTO tweet_hashtag (tweet_id, hashtag_id) VALUES (4, 10);

SELECT * FROM tweet;
