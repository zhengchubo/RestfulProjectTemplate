CREATE TABLE `user` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
`name` varchar(15) NOT NULL DEFAULT '',
`password` varchar(30) NOT NULL DEFAULT '',
`age` int(2) DEFAULT NULL,
`deleteFlag` int(2) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
