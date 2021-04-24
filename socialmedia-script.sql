

--CREATE TABLE STATEMENTS
CREATE TABLE users (
	user_id SERIAL PRIMARY KEY
	,	user_fname varchar(100)
	,	user_lname varchar(100)
	,	user_email varchar(150) UNIQUE 
	,	user_password varchar(150)
	,	user_username varchar(150) UNIQUE 
	, 	user_avatar varchar(100)
);


CREATE TABLE user_post (
	user_id int
	,	post_id SERIAL PRIMARY KEY
	,	post_description varchar(255)
	, 	CONSTRAINT user_post_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE  
);


CREATE TABLE likes (
	like_id SERIAL PRIMARY KEY
	,	user_id int
	,	post_id int
	, 	CONSTRAINT like_user_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE  
	,	CONSTRAINT like_post_id FOREIGN KEY (post_id) REFERENCES user_post(post_id) ON DELETE CASCADE  
);


CREATE TABLE photos (
	photo_id SERIAL PRIMARY KEY
	,	post_id int
	,	photobyte varchar(150)
	,	CONSTRAINT photo_post_id FOREIGN KEY (post_id) REFERENCES user_post(post_id) ON DELETE CASCADE
);


INSERT INTO users (user_fname, user_lname, user_email, user_password, user_username, user_avatar) 
VALUES ('Frank', 'LeHioya', 'revaturefrank@gmail.com', '123', 'Mikey', 'Life is like an apple. You never know when one might drop on you');    

INSERT INTO users (user_fname, user_lname, user_email, user_password, user_username, user_avatar) 
VALUES ('Lia', 'Summer', 'summer@email.com', '123', 'Ms.Summer', 'Sunshine and Rainbows');    

INSERT INTO users (user_fname, user_lname, user_email, user_password, user_username, user_avatar) 
VALUES ('John', 'Big', 'Big@email.com', '123', 'Destroyer', 'EVIL');    


INSERT INTO user_post (post_description, user_id)
VALUES ('post', 1);

INSERT INTO user_post (post_description, user_id)
VALUES ('post 2', 1);

INSERT INTO user_post (post_description, user_id)
VALUES ('post 3', 1);


--DELECT STATEMENTS
DROP TABLE photos;
DROP TABLE likes;
DROP TABLE user_post;
DROP TABLE users;

DROP TABLE post;


--SELECT STATEMENTS
SELECT * FROM users;
SELECT * FROM user_post;
SELECT * FROM postlikes;

DELETE FROM users WHERE user_id = 4;


--INSERT STATEMENTS
INSERT INTO users (user_fname, user_lname, user_email, user_password, user_username, user_avatar, description, loggedin) 
VALUES ('Frank', 'LeHioya', 'revaturefrank@gmail.com', 'zQU7zOHy38ooEml6lThxjg==', 'Mikey', 'https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg', 'Life is like an apple. You never know when one might drop on you', FALSE)    

INSERT INTO users (user_fname, user_lname, user_email, user_password, user_username, user_avatar,description, loggedin) 
VALUES ('Lia', 'Summer', 'summer@email.com','zQU7zOHy38ooEml6lThxjg==', 'Ms.Summer', 'https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_2.jpg', 'Life is like an apple. You never know when one might drop on you', FALSE)    

INSERT INTO users (user_fname, user_lname, user_email, user_password, user_username, user_avatar,description, loggedin) 
VALUES ('John', 'Big', 'Big@email.com', 'zQU7zOHy38ooEml6lThxjg==','Destroyer', 'https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_3.jpg', 'Life is like an apple. You never know when one might drop on you', FALSE)    


INSERT INTO user_post (user_id, post_description) 
VALUES (1, 'post');

INSERT INTO user_post (user_id, post_description) 
VALUES (1, 'post 2');

INSERT INTO user_post (user_id, post_description) 
VALUES (1, 'post 3');

INSERT INTO likes(user_id, post_id) 
VALUES (1,1);






