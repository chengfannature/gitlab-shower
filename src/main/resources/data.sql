-- commit表测试数据
INSERT INTO `commit` (`id`, `additions`, `author_email`, `author_name`, `created_at`, `deletions`, `project_id`, `short_id`, `title`) VALUES ('f6b55dadbf837858f2ea10e1cf1d16656152b65d', '100', 'pengxg@huawei.com.cn', 'pengxg', '2019-04-01 13:19:13', '1000', '207', 'f6b55dad', '*nisp sql init');
INSERT INTO `commit` (`id`, `additions`, `author_email`, `author_name`, `created_at`, `deletions`, `project_id`, `short_id`, `title`) VALUES ('64e5b48048ad60c6b0ff4d30b884d669b04bf090', '150', 'caojm@huawei.net.cn', 'caojm', '2019-03-05 18:55:48', '100', '205', '64e5b480', '??????TD???? ???????? ??????????');
INSERT INTO `commit` (`id`, `additions`, `author_email`, `author_name`, `created_at`, `deletions`, `project_id`, `short_id`, `title`) VALUES ('64e5b48048ad60c6b0ff4d30b884d669b04bf091', '60', 'chengfan@huawei.net.cn', 'chengfan', '2019-03-05 18:55:48', '10', '205', '64e5b480', '??????TD???? ???????? ??????????');
INSERT INTO `commit` (`id`, `additions`, `author_email`, `author_name`, `created_at`, `deletions`, `project_id`, `short_id`, `title`) VALUES ('64e5b48048ad60c6b0ff4d30b884d669b04bf092', '200', 'chengfan@huawei.net.cn', 'chengfan', '2019-03-05 18:55:48', '100', '205', '64e5b480', '??????TD???? ???????? ??????????');
-- note表测试数据

INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('7592', 'chengfan', '这个路径还是有可能出错，成功与否取决于你的执行路径。\n使用cd `$NISP_HOME/gpscripts/bin/` 应该不会有问题', '2019-01-21 14:15:12', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('7519', 'pengxg', '字段是不是对应有误？这个位置是不是可以不用添加0做默认数据', '2019-01-18 14:14:12', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('7540', 'leigang', '魔法数字不用改吗', '2019-01-18 15:11:38', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6942', 'ghost', '\"protocolType\"其实就是`GenConstant.GEN_PROTOCOL_TYPE_RECORD_NAME`这个常量值.', '2019-01-09 10:28:51', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6943', 'chengfan', '最好能把规范规定的字段注释到类里面，便于参考', '2019-01-09 10:29:42', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6944', 'chengfan', '都没有格式化呀', '2019-01-09 10:29:53', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6735', 'pengxg', 'exited 退出，exist?', '2019-01-03 17:47:23', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6736', 'chengfan', '这个内容是怎么的来的，不要写这么一大坨硬编码的代码在这里啊', '2019-01-03 18:01:37', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6654', 'pengxg', '-1,逻辑有问题么这个位置，i取的是数组下标', '2018-12-28 10:00:03', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6546', 'ghost', '没有格式化', '2018-12-26 09:44:26', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6554', 'chengfan', '这个换行太诡异了，连在一起吧\n`ite` 定义成it好看点', '2018-12-26 09:51:49', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6555', 'chengfan', '这个语句太反人类了，改的易读一点吧', '2018-12-26 09:58:37', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6556', 'chengfan', '这个方法的命名和参数不对应啊，参数不是`virusName`么', '2018-12-26 09:59:20', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6541', 'zhangliang', 'ok', '2018-12-26 09:40:39', '0');
INSERT INTO `note` (`id`, `author_name`, `body`, `created_at`, `resolved`) VALUES ('6423', 'zhangliang', 'OK', '2018-12-21 19:42:55', '0');
