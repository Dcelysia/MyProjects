# 凡路大作业项目
凡路springboot+vue做的简易原神web管理后台

## Environments

1.先下载两个文件夹，其中一个是springboot文件一个是vue文件。
2.要把springboot中application.yml中mysql账号密码改成本地的账号密码。并在本地Mysql中新建mq数据库并将以下代码运行

~~~ sql
CREATE TABLE `characters` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `desp` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `cur_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
~~~
这样本地Mysql设置完毕，若更改数据库名字和表名，记得手动在springboot更改相关的sql语句(不推荐)

3.安装node.js，在vue文件夹上地址栏输入cmd调出控制面板，并输入
~~~
npm run serve
~~~
之后弹出网址ctrl+单击便可以打开

tip:先启动后端在启动前端！！

由于前端视频过大不能上传到github上面，所有所有前端代码将需要到百度网盘上下载链接：https://pan.baidu.com/s/1hfRfuaIN22yYG_IWSbR7rw?pwd=6666 
提取码：6666