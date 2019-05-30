--
-- Table structure for table `commit`
--

DROP TABLE IF EXISTS `commit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commit` (
  `id`           varchar(255) NOT NULL,
  `additions`    int(11)      NOT NULL,
  `author_email` varchar(255) DEFAULT NULL,
  `author_name`  varchar(255) DEFAULT NULL,
  `created_at`   datetime     DEFAULT NULL,
  `deletions`    int(11)      NOT NULL,
  `project_id`   int(11)      NOT NULL,
  `short_id`     varchar(255) DEFAULT NULL,
  `title`        varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id`       int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `email`    varchar(45) DEFAULT NULL,
  `is_admin` tinyint(1)  DEFAULT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id`          int(11) NOT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  `body`        longtext,
  `created_at`  datetime     DEFAULT NULL,
  `resolved`    bit(1)  NOT NULL,
  PRIMARY KEY (`id`)
);