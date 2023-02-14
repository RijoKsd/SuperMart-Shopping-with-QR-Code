/*
SQLyog Community Edition- MySQL GUI v8.03 
MySQL - 5.6.12-log : Database - qr_shopping
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`qr_shopping` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `qr_shopping`;

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `bill_id` int(20) NOT NULL AUTO_INCREMENT,
  `master_id` int(20) DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  `quantity` int(20) DEFAULT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bill` */

/*Table structure for table `bill_master` */

DROP TABLE IF EXISTS `bill_master`;

CREATE TABLE `bill_master` (
  `master_id` int(20) NOT NULL AUTO_INCREMENT,
  `shop_id` int(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`master_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bill_master` */

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `complaint_id` int(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `complaint` varchar(200) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `reply_date` varchar(200) DEFAULT 'pending',
  `reply` varchar(200) DEFAULT 'pending',
  PRIMARY KEY (`complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`type`,`user_id`,`complaint`,`date`,`reply_date`,`reply`) values (1,'user',2,'worst app','2022-12-28','2022-12-30','ok'),(2,'user',3,'use more memmory','2022-12-26','2022-12-30','we fixed it\r\n'),(3,'user',4,'more ads','2022-12-29','2023-01-02','we fixed it'),(22,'shop',18,'some glitches in dashboard','2023-01-25','2023-01-25','We fixed this problem in this update, please update to latest version'),(23,'shop',20,'complaint is sending...\r\n','2023-02-12','2023-02-12','reply is sending...\r\n'),(24,'user',2,'image section not showing in registration section','2023-02-14','pending','pending'),(25,'user',4,'xheck','2023-02-14','pending','pending'),(26,'user',4,'xheck','2023-02-14','pending','pending'),(27,'user',2,'checkkkk','2023-02-14','pending','pending'),(28,'user',2,'28387rbdnf','2023-02-14','pending','pending'),(29,'user',2,'chev','2023-02-14','pending','pending'),(30,'user',3,'zbjdu','2023-02-14','2023-02-14','fixed\r\n');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(30) NOT NULL AUTO_INCREMENT,
  `sender_id` int(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`sender_id`,`type`,`date`,`feedback`) values (1,2,'user','2022-02-02','N/W slow'),(4,3,'user','2022-02-22','GOOD'),(21,18,'shop','2023-01-25','very use full app'),(22,20,'shop','2023-02-12','Feedback is sending...');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `user_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`user_name`,`password`,`user_type`) values (1,'admin','admin','admin'),(2,'rijo@gmail.com','rijo','user'),(3,'salman','salman','user'),(4,'sachin','sachin','user'),(18,'jio@gmail.com','jio','shop'),(19,'lenin','lenin','user'),(20,'spicy@gmail.com','spicy','shop'),(21,'max@gmail.com','max','shop'),(22,'lucky@gmail.com','lucky','pending'),(23,'aaa@gmail.com','aaa','shop'),(24,'test@gmail.com','test','pending'),(25,'aarijo@gmail.com','123','user'),(26,'aa@gmail.com','123','user'),(27,'aaaa@gmail.com','123','user'),(28,'h@gmail.com','111','user'),(29,'sanesh@gmail.com','sanesh','user');

/*Table structure for table `offer` */

DROP TABLE IF EXISTS `offer`;

CREATE TABLE `offer` (
  `offer_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `offer` varchar(20) DEFAULT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  PRIMARY KEY (`offer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `offer` */

insert  into `offer`(`offer_id`,`product_id`,`offer`,`date_from`,`date_to`) values (7,20,'10','2023-02-12','2023-03-11'),(8,23,'23','2023-02-12','2023-03-11'),(9,22,'2','2023-02-12','2023-03-11'),(10,24,'1','2023-02-15','2023-03-08');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL,
  `details` longtext,
  `shop_id` int(20) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`name`,`price`,`details`,`shop_id`,`image`) values (20,'Salt',100,'1kg salt',18,'/static/images/230127-231204 .jpg'),(21,'Butter',54,'Amul Butter\r\n',20,'/static/images/230211-130940 .jpg'),(22,'Rice',105,'10kg ',21,'/static/images/230211-131014 .jpg'),(23,'Onion',58,'1 kg ',18,'/static/images/230211-143217 .jpg'),(24,'Coffee',30,'Nescafe coffee',21,'/static/images/230212-095623 .jpg'),(25,'Bread',30,'Wheat Bread',20,'/static/images/230212-095908 .jpg'),(26,'Brush',25,'Oral B  brush\r\n',20,'/static/images/230212-100008 .jpg'),(27,'Dettol',50,'100ml Dettol ',20,'/static/images/230212-100037 .jpg'),(28,'Tea Powder',30,' TATA tea',20,'/static/images/230212-100105 .jpg'),(29,'Paste',30,'Colgate paste 200g',20,'/static/images/230212-100133 .jpg'),(30,'Carrot',25,'carrot 500g',23,'/static/images/230212-100732 .jpg');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `rating` int(20) DEFAULT NULL,
  `shop_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

/*Table structure for table `shop` */

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `shop_id` int(20) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `place` varchar(30) DEFAULT NULL,
  `pincode` int(30) DEFAULT NULL,
  `mail` varchar(30) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `shop` */

insert  into `shop`(`shop_id`,`name`,`place`,`pincode`,`mail`,`phone`,`image`) values (18,'Jio Mart','Kasargod',671541,'jio@gmail.com',9562147895,'/static/images/230125-072019 .jpg'),(20,'Spicy','knr',64646,'spicy@gmail.com',4554,'/static/images/230211-123914 .jpg'),(21,'Max','liuo',565,'max@gmail.com',3132154,'/static/images/230211-124100 .jpg'),(22,'Lucky','ld',66565,'lucky@gmail.com',64664,'/static/images/230211-131049 .jpg'),(23,'aaa','sdf',1654,'aaa@gmail.com',645645,'/static/images/230211-131523 .jpg'),(24,'Test','jl',6565,'test@gmail.com',55454,'/static/images/230212-100628 .jpg');

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `stock_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `quantity` int(20) DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

insert  into `stock`(`stock_id`,`product_id`,`quantity`) values (43,20,12),(44,21,32),(45,22,34),(46,23,5),(47,24,23),(48,25,45),(49,26,55),(50,27,5),(51,28,12),(52,29,60),(53,30,4);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(35) DEFAULT NULL,
  `place` varchar(30) DEFAULT NULL,
  `pincode` int(10) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `gender` varchar(30) DEFAULT NULL,
  `phone_no` bigint(20) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`name`,`place`,`pincode`,`email`,`gender`,`phone_no`,`image`) values (2,'rijo','ksd',671541,'rijo@gmail.com','male',9446626926,'/static/images/230125-073807 .jpg'),(3,'salman','ksd',671123,'salman@gmail.com','male',9456324789,'/static/images/230125-211830 .jpg'),(4,'sachin','ksd',671541,'sachin@gmail.com','male',7584126574,'/static/images/230104-223112 .jpg'),(27,'aa','hs',976546,'aaaa@gmail.com','Female',9977664433,'/static/images/230214-112029 .jpg'),(28,'from','CCM',879,'h@gmail.com','Other',55879776,'/static/images/230214-112629 .jpg'),(29,'Sanesh','kasargod ',671541,'sanesh@gmail.com','Male',9468123564,'/static/images/230214-141007 .jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
