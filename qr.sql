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
  `reply_date` date DEFAULT '0000-00-00',
  `reply` varchar(200) DEFAULT 'pending',
  PRIMARY KEY (`complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`type`,`user_id`,`complaint`,`date`,`reply_date`,`reply`) values (1,'user',2,'worst app','2022-12-28','2022-12-30','ok'),(2,'user',3,'use more memmory','2022-12-26','2022-12-30','we fixed it\r\n'),(3,'user',4,'more ads','2022-12-29','2023-01-02','we fixed it'),(4,'shop',5,'worst payment method','2023-01-01','2022-12-30','thankyou'),(5,'shop',6,'bill payment error','2022-12-23','0000-00-00','pending'),(6,'shop',7,'gst not include','2023-01-02','2022-12-30','gt');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(30) NOT NULL AUTO_INCREMENT,
  `sender_id` int(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`sender_id`,`type`,`date`,`feedback`) values (1,2,'user','2022-02-02','N/W slow'),(2,9,'shop','2022-05-12','good application'),(3,8,'shop','2022-04-12','thankyou'),(4,3,'user','2022-02-22','GOOD');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `user_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`user_name`,`password`,`user_type`) values (1,'admin','admin','admin'),(2,'rijo','rijo','user'),(3,'salman','salman','user'),(4,'sachin','sachin','user'),(7,'s&s','s&s','shop'),(8,'riss@gmail.com','1234','shop'),(9,'abc@gmail.com','abc','shop');

/*Table structure for table `offer` */

DROP TABLE IF EXISTS `offer`;

CREATE TABLE `offer` (
  `offer_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `offer` int(20) DEFAULT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  PRIMARY KEY (`offer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `offer` */

insert  into `offer`(`offer_id`,`product_id`,`offer`,`date_from`,`date_to`) values (1,5,33,'2023-01-03','2023-01-10'),(2,5,4,'2023-01-04','2023-01-10'),(3,1,12,'2023-01-05','2023-01-19');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`name`,`price`,`details`,`shop_id`,`image`) values (1,'salman',400,'dfdsf',8,'/static/images/230102-125502 .jpg'),(5,'iphone 14',189000,'iphone 14, deep purple 1tb',9,'/static/images/230102-142314 .jpg'),(6,'new',334,'sdf',9,'/static/images/230102-153549 .jpg');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `rating` int(20) DEFAULT NULL,
  `shop_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`date`,`user_id`,`rating`,`shop_id`) values (1,'2012-01-01',2,5,5);

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

insert  into `shop`(`shop_id`,`name`,`place`,`pincode`,`mail`,`phone`,`image`) values (7,'s&s','ksd',647584,'ss@gmail.com',9895555555,NULL),(8,'riss ','knr',9475632,'riss@gmail.com',9785452455,'/static/images/230102-120856 .jpg'),(9,'abc','ksd',641752,'abc@gmail.com',9745624984,'/static/images/230102-131410 .jpg');

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `stock_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `quantity` int(20) DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

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

insert  into `user`(`user_id`,`name`,`place`,`pincode`,`email`,`gender`,`phone_no`,`image`) values (2,'rijo','ksd',671541,'rijo@gmail.com','male',9446626926,NULL),(3,'salman','ksd',671123,'salman@gmail.com','male',9456324789,NULL),(4,'sachin','ksd',671541,'sachin@gmail.com','male',7584126574,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
