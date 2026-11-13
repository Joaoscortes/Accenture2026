
INSERT INTO `User` (`id`,`age`,`email`,`password`,`photoUrl`,`role`,`username`,`deleted`) VALUES (1,18,'user@teste.pt','$2a$10$H1cHKLiISAxt1.k/ItLM1emoGrTdRpz9mnNHDXq6I75V15JmpnfFK',NULL,'USER','User',false);
INSERT INTO `User` (`id`,`age`,`email`,`password`,`photoUrl`,`role`,`username`,`deleted`) VALUES (2,20,'admin@teste.pt','$2a$10$VJzY3JiSRli8KGANDKCvwO3Y/9hJSRWUVG6N/RolcAh6JhjPIs7SW',NULL,'ADMIN','Admin',false);

INSERT INTO `Address` (`id`,`city`,`country`,`postalCode`,`street`,`user_id`) VALUES (1,'Lisboa','Portugal','2715','Rua de Cima',1);