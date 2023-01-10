-- cossc.TAG definition
DROP TABLE IF EXISTS `HISTORY`;
DROP TABLE IF EXISTS `ANSWER`;
DROP TABLE IF EXISTS `USER_QUIZ`;
DROP TABLE IF EXISTS `QUIZ_TAG`;
DROP TABLE IF EXISTS `TAG`;
DROP TABLE IF EXISTS `QUIZ`;
DROP TABLE IF EXISTS `USER`;

CREATE TABLE `TAG`
(
    `tag_id`       bigint       NOT NULL AUTO_INCREMENT,
    `name`         varchar(100) NOT NULL,
    `created_date` datetime     NOT NULL,
    `created_by`   varchar(255) NOT NULL,
    `updated_date` datetime     NOT NULL,
    `updated_by`   varchar(255) NOT NULL,
    PRIMARY KEY (`tag_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- cossc.`USER` definition
CREATE TABLE `USER`
(
    `user_id`       bigint       NOT NULL AUTO_INCREMENT,
    `auth_provider` varchar(255) DEFAULT NULL,
    `description`   varchar(255) DEFAULT NULL,
    `email`         varchar(255) NOT NULL,
    `img`           varchar(255) DEFAULT NULL,
    `name`          varchar(255) DEFAULT NULL,
    `refresh_token` varchar(255) DEFAULT NULL,
    `role`          varchar(255) DEFAULT NULL,
    `level`         varchar(100) DEFAULT NULL,
    `created_date`  datetime     NOT NULL,
    `created_by`    varchar(255) NOT NULL,
    `updated_date`  datetime     NOT NULL,
    `updated_by`    varchar(255) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
    UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- cossc.QUIZ definition
CREATE TABLE `QUIZ`
(
    `quiz_id`      bigint       NOT NULL AUTO_INCREMENT,
    `title`        varchar(100) NOT NULL,
    `description`  text         NOT NULL,
    `user_id`      bigint       NOT NULL,
    `type`         varchar(100) NOT NULL,
    `created_date` datetime     NOT NULL,
    `created_by`   varchar(255) NOT NULL,
    `updated_date` datetime     NOT NULL,
    `updated_by`   varchar(255) NOT NULL,
    PRIMARY KEY (`quiz_id`),
    KEY `QUIZ_FK` (`user_id`),
    CONSTRAINT `QUIZ_FK` FOREIGN KEY (`user_id`) REFERENCES `USER` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- cossc.QUIZ_TAG definition
CREATE TABLE `QUIZ_TAG`
(
    `quiz_id` bigint NOT NULL,
    `tag_id`  bigint NOT NULL,
    PRIMARY KEY (`quiz_id`, `tag_id`),
    KEY `QUIZ_TAG_FK_1` (`tag_id`),
    CONSTRAINT `QUIZ_TAG_FK` FOREIGN KEY (`quiz_id`) REFERENCES `QUIZ` (`quiz_id`),
    CONSTRAINT `QUIZ_TAG_FK_1` FOREIGN KEY (`tag_id`) REFERENCES `TAG` (`tag_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- cossc.USER_QUIZ definition
CREATE TABLE `USER_QUIZ`
(
    `user_id` bigint NOT NULL,
    `quiz_id` bigint NOT NULL,
    `solved`  bigint DEFAULT NULL,
    `failed`  bigint DEFAULT NULL,
    PRIMARY KEY (`user_id`, `quiz_id`),
    KEY `USER_QUIZ_FK_1` (`quiz_id`),
    CONSTRAINT `USER_QUIZ_FK` FOREIGN KEY (`user_id`) REFERENCES `USER` (`user_id`),
    CONSTRAINT `USER_QUIZ_FK_1` FOREIGN KEY (`quiz_id`) REFERENCES `QUIZ` (`quiz_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- cossc.ANSWER definition
CREATE TABLE `ANSWER`
(
    `answer_id`    bigint       NOT NULL AUTO_INCREMENT,
    `text`         varchar(255) NOT NULL,
    `user_id`      bigint       NOT NULL,
    `quiz_id`      bigint       NOT NULL,
    `created_date` datetime     NOT NULL,
    `created_by`   varchar(255) NOT NULL,
    `updated_date` datetime     NOT NULL,
    `updated_by`   varchar(255) NOT NULL,
    PRIMARY KEY (`answer_id`),
    KEY `ANSWER_FK` (`user_id`),
    KEY `ANSWER_FK_1` (`quiz_id`),
    CONSTRAINT `ANSWER_FK` FOREIGN KEY (`user_id`) REFERENCES `USER` (`user_id`),
    CONSTRAINT `ANSWER_FK_1` FOREIGN KEY (`quiz_id`) REFERENCES `QUIZ` (`quiz_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- cossc.HISTORY definition

CREATE TABLE `HISTORY`
(
    `history_date` date         NOT NULL,
    `user_id`      bigint       NOT NULL,
    `quiz_id`      bigint       NOT NULL,
    `solved`       tinyint(1)   NOT NULL,
    `created_date` datetime     NOT NULL,
    `created_by`   varchar(255) NOT NULL,
    `updated_date` datetime     NOT NULL,
    `updated_by`   varchar(255) NOT NULL,
    PRIMARY KEY (`history_date`, `user_id`, `quiz_id`),
    KEY `HISTORY_FK` (`quiz_id`),
    KEY `HISTORY_FK_1` (`user_id`),
    CONSTRAINT `HISTORY_FK` FOREIGN KEY (`quiz_id`) REFERENCES `QUIZ` (`quiz_id`),
    CONSTRAINT `HISTORY_FK_1` FOREIGN KEY (`user_id`) REFERENCES `USER` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;