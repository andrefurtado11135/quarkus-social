CREATE TABLE quarkus_social.USERS(
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	age INTEGER NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE quarkus_social.POSTS(
	id BIGINT NOT NULL AUTO_INCREMENT,
	post_text VARCHAR(150) NOT NULL,
	date_time TIMESTAMP NOT NULL,
	user_id BIGINT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT `fk_user` FOREIGN KEY (user_id) REFERENCES USERS(id)
);

CREATE TABLE quarkus_social.FOLLOWERS(
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	follower_id BIGINT NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE quarkus_social.FOLLOWERS ADD CONSTRAINT `fk_user_follower` FOREIGN KEY (user_id) REFERENCES USERS(id);
ALTER TABLE quarkus_social.FOLLOWERS ADD CONSTRAINT `fk_follower` FOREIGN KEY (follower_id) REFERENCES USERS(id);