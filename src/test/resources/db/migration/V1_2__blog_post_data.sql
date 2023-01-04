INSERT INTO blog_post (id, title, content)
VALUES  (1,'title-1','content-1'),
        (2,'title-2','content-2');

INSERT INTO blog_post_comment (id, content, blog_post_id)
VALUES  (1,'comment-1',1),
        (2,'comment-2',2),
        (3,'comment-3',2);