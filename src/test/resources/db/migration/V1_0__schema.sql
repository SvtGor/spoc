--twożymy tabeli
CREATE TABLE blog_post (
id bigint PRIMARY KEY,
title VARCHAR(255),
content VARCHAR(255)
--VARCHAR to oznacza String
--bigint to oznacza int
);

CREATE TABLE blog_post_comment(
id bigint PRIMARY KEY,
content VARCHAR(255),
blog_post_id bigint NOT NULL,
FOREIGN KEY(blog_post_id) REFERENCES blog_post(id)
);
CREATE TABLE tag(
id bigint PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE blog_post_tags(
post_id bigint NOT NULL,
tag_id bigint NOT NULL,
FOREIGN KEY(post_id)REFERENCES blog_post(id),
FOREIGN KEY(tag_id) REFERENCES tag(id)
);