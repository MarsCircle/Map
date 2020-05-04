CREATE TABLE `map_countall` (
  `countall_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `json_data_current` mediumtext   NOT NULL  COMMENT '当前时间世界航班json数据',
  `json_data_today` mediumtext  NOT NULL  COMMENT '当日世界航班json数据总计',
  `all_count_today` varchar(50)   NOT NULL DEFAULT '' COMMENT '当日世界航班数量统计',
  `count_current` varchar(100)  NOT NULL DEFAULT '' COMMENT '当前时间世界航班数量统计' ,
  `departure_airport_most_current` varchar(50)  NOT NULL DEFAULT '' COMMENT '当前时间起飞航班最多的机场',
  `arrival_airport_most_current` varchar(50)  NOT NULL DEFAULT '' COMMENT '当前时间降落航班最多的机场',
  `departure_airport_most_today` varchar(50)  NOT NULL DEFAULT '' COMMENT '当日起飞航班最多的机场',
  `arrival_airport_most_today` varchar(50)  NOT NULL DEFAULT ''COMMENT '当日降落航班最多的机场',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`countall_id`),
  INDEX time(`update_time`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;