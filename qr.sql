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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `bill` */

insert  into `bill`(`bill_id`,`master_id`,`product_id`,`quantity`) values (1,1,10,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `bill_master` */

insert  into `bill_master`(`master_id`,`shop_id`,`user_id`,`amount`,`date`,`status`) values (1,12,2,25,'2023-01-01','pending');

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`type`,`user_id`,`complaint`,`date`,`reply_date`,`reply`) values (1,'user',2,'worst app','2022-12-28','2022-12-30','ok'),(2,'user',3,'use more memmory','2022-12-26','2022-12-30','we fixed it\r\n'),(3,'user',4,'more ads','2022-12-29','2023-01-02','we fixed it'),(10,'shop',12,'UI is not easy','2023-12-01','2023-01-12','tes555'),(11,'shop',12,'checking','2023-01-12','0000-00-00','pending'),(12,'shop',12,'test xx','2023-01-12','2023-01-12','ok t'),(13,'shop',12,'sfdsf','2023-01-12','2023-01-12','111'),(14,'shop',14,'maxxxxxxxxxx','2023-01-12','2023-01-12','test 1'),(15,'shop',14,'second phase','2023-01-12','0000-00-00','pending'),(16,'shop',14,'varchar\r\n','2023-01-12','2023-01-12','changed from date'),(17,'shop',14,'successfully completed','2023-01-14','pending','pending');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(30) NOT NULL AUTO_INCREMENT,
  `sender_id` int(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`sender_id`,`type`,`date`,`feedback`) values (1,2,'user','2022-02-02','N/W slow'),(4,3,'user','2022-02-22','GOOD'),(7,12,'shop','2023-01-11','checking purpose'),(14,12,'shop','2023-01-12','asfa'),(15,12,'shop','2023-01-12','Thanyou for fixing\r\n'),(16,14,'shop','2023-01-12','maxxxxxxxxx\r\n'),(17,14,'shop','2023-01-14','today 12-01-2023 \r\ntime 02-34 AM');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `user_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`user_name`,`password`,`user_type`) values (1,'admin','admin','admin'),(2,'rijo','rijo','user'),(3,'salman','salman','user'),(4,'sachin','sachin','user'),(12,'spicy@gmail.com','spicy','shop'),(13,'aaa@gmail.com','aaa','shop'),(14,'max@gmail.com','max','shop'),(15,'lucky@gmail.com','lucky','pending');

/*Table structure for table `offer` */

DROP TABLE IF EXISTS `offer`;

CREATE TABLE `offer` (
  `offer_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `offer` varchar(20) DEFAULT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  PRIMARY KEY (`offer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `offer` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL,
  `details` varchar(200) DEFAULT NULL,
  `shop_id` int(20) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`name`,`price`,`details`,`shop_id`,`image`) values (10,'Carrot',50,'Fresh Carrot 1kg',12,'/static/images/230111-214635 .jpg'),(11,'Capsicum',60,'Green Capsicum\r\n500g',12,'/static/images/230111-214721 .jpg'),(12,'Dabur Paste',50,'Red dabur 600g',14,'/static/images/230111-220831 .jpg'),(13,'Colgate',50,'Colgate 200g ',14,'/static/images/230111-220900 .jpg'),(14,'Dettol',79,'Dettol 150g',14,'/static/images/230112-123252 .jpg'),(15,'Potato',45,'Potato 1kg',12,'/static/images/230114-022246 .jpg');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `rating` int(20) DEFAULT NULL,
  `shop_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`date`,`user_id`,`rating`,`shop_id`) values (3,'2023-01-11',2,4,12),(4,'2022-12-30',2,5,13),(5,'2023-01-12',3,3,12),(6,'2023-01-01',4,1,12),(7,'2023-01-12',2,1,14);

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

insert  into `shop`(`shop_id`,`name`,`place`,`pincode`,`mail`,`phone`,`image`) values (12,'spicy','Bandaduka',671541,'spicy@gmail.com',9874521456,'/static/images/230111-214139 .jpg'),(13,'Aaa','Munnad',954265,'aaa@mail.com',9541236547,'/static/images/230111-214341 .jpg'),(14,'Max','Kasargod',674524,'max@gmail.com',857412365,'/static/images/230111-214507 .jpg'),(15,'Lucky','Kannur',658712,'lucky@gmail.com',9853214536,'/static/images/230111-215936 .jpg');

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `stock_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `quantity` int(20) DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

insert  into `stock`(`stock_id`,`product_id`,`quantity`) values (32,12,2),(33,15,3),(34,11,2),(36,10,112);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`name`,`place`,`pincode`,`email`,`gender`,`phone_no`,`image`) values (2,'rijo','ksd',671541,'rijo@gmail.com','male',9446626926,'/static/images/230104-223112 .jpg'),(3,'salman','ksd',671123,'salman@gmail.com','male',9456324789,'/static/images/230104-223112 .jpg'),(4,'sachin','ksd',671541,'sachin@gmail.com','male',7584126574,'/static/images/230104-223112 .jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
