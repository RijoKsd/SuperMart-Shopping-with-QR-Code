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
  `bill_price` int(20) DEFAULT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `bill` */

insert  into `bill`(`bill_id`,`master_id`,`product_id`,`quantity`,`bill_price`) values (1,1,20,203,NULL);

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

insert  into `bill_master`(`master_id`,`shop_id`,`user_id`,`amount`,`date`,`status`) values (1,18,2,0,'2023-03-16','cart');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`type`,`user_id`,`complaint`,`date`,`reply_date`,`reply`) values (1,'user',2,'The UI is very old ','2023-03-02','2023-03-02','We will fix it soon\r\n');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(30) NOT NULL AUTO_INCREMENT,
  `sender_id` int(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`sender_id`,`type`,`date`,`feedback`) values (1,2,'user','2023-03-02','Thank You for developing this app');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `user_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`user_name`,`password`,`user_type`) values (1,'admin','admin','admin'),(2,'rijo@gmail.com','rijo','user'),(3,'salman@gmail.com','salman','user'),(18,'jio@gmail.com','jio','shop'),(20,'spicy@gmail.com','spicy','shop'),(21,'max@gmail.com','max','shop'),(22,'lucky@gmail.com','lucky','pending'),(23,'aaa@gmail.com','aaa','shop'),(24,'test@gmail.com','test','pending'),(29,'sanesh@gmail.com','sanesh','user'),(30,'lenin@gmail.com','lenin','user'),(31,'sachin@gmail.com','sachin','user'),(32,'akash@gmail.com','akash','user'),(33,'aleena@gmail.com','aleena','user'),(34,'jinsha@gmail.com','jinsha','user');

/*Table structure for table `offer` */

DROP TABLE IF EXISTS `offer`;

CREATE TABLE `offer` (
  `offer_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `offer` varchar(20) DEFAULT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  PRIMARY KEY (`offer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `offer` */

insert  into `offer`(`offer_id`,`product_id`,`offer`,`date_from`,`date_to`) values (7,20,'50','2023-02-12','2023-03-25'),(8,23,'23','2023-02-12','2023-03-11'),(9,22,'2','2023-02-12','2023-03-11'),(10,24,'1','2023-02-15','2023-03-08'),(11,47,'34','2023-03-17','2023-04-08'),(12,46,'54','2023-03-11','2023-04-07'),(13,45,'34','2023-03-11','2023-04-08'),(14,44,'23','2023-03-11','2023-04-07'),(15,31,'1','2023-04-08','2023-04-29'),(16,35,'5','2023-03-11','2023-03-31'),(17,56,'5','2023-03-16','2023-03-31'),(18,21,'45','2023-03-16','2023-04-08'),(19,25,'45','2023-03-16','2023-04-06'),(20,26,'34','2023-03-16','2023-03-27');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(40) DEFAULT NULL,
  `ifsc_code` varchar(40) DEFAULT NULL,
  `account_no` varchar(40) DEFAULT NULL,
  `account_balance` float DEFAULT NULL,
  `holder_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`bank_id`,`bank_name`,`ifsc_code`,`account_no`,`account_balance`,`holder_id`) values (1,'rijo','SBI2345','1234567890',11454.7,2),(2,'jio','ICICI343','2231235635',32021.3,18),(3,'spicy','SBI4566','3453434232',37338,20),(4,'MAX','ABC4589','3449080005',35970,21);

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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`name`,`price`,`details`,`shop_id`,`image`) values (20,'Salt',20,'1kg salt',18,'/static/images/230127-231204 .jpg'),(21,'Butter',54,'Amul Butter\r\n',20,'/static/images/230211-130940 .jpg'),(22,'Rice',105,'10kg ',21,'/static/images/230211-131014 .jpg'),(23,'Onion',58,'1 kg ',18,'/static/images/230211-143217 .jpg'),(24,'Coffee',30,'Nescafe coffee',21,'/static/images/230212-095623 .jpg'),(25,'Bread',30,'Wheat Bread',20,'/static/images/230212-095908 .jpg'),(26,'Brush',25,'Oral B  brush\r\n',20,'/static/images/230212-100008 .jpg'),(27,'Dettol',50,'100ml Dettol ',20,'/static/images/230212-100037 .jpg'),(28,'Tea Powder',30,' TATA tea',20,'/static/images/230212-100105 .jpg'),(29,'Paste',30,'Colgate paste 200g',20,'/static/images/230212-100133 .jpg'),(30,'Carrot',25,'carrot 500g',23,'/static/images/230212-100732 .jpg'),(31,'Cauliflower',30,' 1 kg cauliflower\r\n',18,'/static/images/230221-095728 .jpg'),(32,'Turmeric powder',25,'100g turmeric powder',18,'/static/images/230221-095823 .jpg'),(35,'Sugar',45,'1 kg sugar',18,'/static/images/230304-145429 .jpg'),(42,'Tomato',30,'1kg tomato',18,'/static/images/230304-151010 .jpg'),(43,'Carrot',45,'1 kg carrot',18,'/static/images/230304-162531 .jpg'),(44,'Brushes',150,'Pack of 10 brush',18,'/static/images/230307-195326 .jpg'),(45,'Coffee Powder',55,'Nescafe coffe',18,'/static/images/230307-195501 .jpg'),(46,'Chicken Masala',30,'100 g\r\nAachi chicken masala',18,'/static/images/230309-110535 .jpg'),(47,'Tomato',40,'1kg  Tomato',18,'/static/images/230309-111716 .jpg'),(52,'Bread',34,'Wheat bread',18,'/static/images/230312-160419 .jpg'),(53,'Salt',20,'1kg tata salt',18,'/static/images/230312-160747 .jpg'),(54,'Coffe',30,'100g bru coffee powder',18,'/static/images/230316-094133 .jpg'),(55,'Chaat Masala',45,' 200g chatt masala',18,'/static/images/230316-095210 .jpg'),(56,'Biriyani Rice',100,'10kg daawat biriyani rice\r\n\r\n',18,'/static/images/230316-101328 .jpg'),(57,'Cauliflower',45,'  1kg cauliflower',20,'/static/images/230316-154020 .jpg');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `rating` int(20) DEFAULT NULL,
  `shop_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`date`,`user_id`,`rating`,`shop_id`) values (1,'2023-03-02',2,4,18),(2,'2023-03-08',2,2,21),(3,'2023-03-14',2,5,20);

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

insert  into `shop`(`shop_id`,`name`,`place`,`pincode`,`mail`,`phone`,`image`) values (18,'Jio Mart','Kasargod',671541,'jio@gmail.com',9562147895,'/static/images/230125-072019 .jpg'),(20,'Spicy','Kannur',925416,'spicy@gmail.com',9874514785,'/static/images/230211-123914 .jpg'),(21,'Max','Kuttikol',674582,'max@gmail.com',9541237852,'/static/images/230211-124100 .jpg'),(22,'Lucky','Paduppu',652145,'lucky@gmail.com',8745321456,'/static/images/230211-131049 .jpg'),(23,'aaa','Munnad',654124,'aaa@gmail.com',8231456878,'/static/images/230211-131523 .jpg'),(24,'Test','jl',6565,'test@gmail.com',55454,'/static/images/230212-100628 .jpg');

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `stock_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `quantity` int(20) DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

insert  into `stock`(`stock_id`,`product_id`,`quantity`) values (43,20,206),(44,21,619),(45,22,119),(46,23,566),(47,24,23),(48,25,45),(49,26,50),(50,27,65),(51,28,5845),(52,29,60),(53,30,65148),(54,31,145),(55,32,216),(56,35,30),(57,42,633),(58,44,400),(59,43,68),(60,45,67),(61,46,302),(62,47,234),(63,53,343),(64,52,343),(65,54,343),(66,55,422),(67,56,339),(68,57,565);

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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`name`,`place`,`pincode`,`email`,`gender`,`phone_no`,`image`) values (2,'rijo','ksd',671541,'rijo@gmail.com','male',9446626926,'/static/images/230125-073807 .jpg'),(3,'salman','ksd',671123,'salman@gmail.com','male',9456324789,'/static/images/230125-211830 .jpg'),(29,'Sanesh','kasargod ',671541,'sanesh@gmail.com','Male',9468123564,'/static/images/230214-141007 .jpg'),(30,'Lenin','Kasargod',671541,'lenin@gmail.com','Male',9421386480,'/static/images/230215-073018 .jpg'),(31,'Sachin','Kannur',671544,'sachin@gmail.com','Male',9564812398,'/static/images/230316-153837 .jpg'),(32,'Akash','Udma',671543,'akash@gmail.com','Male',9547832159,'/static/images/230219-130138 .jpg'),(33,'Aleena ','Paduppu',67154,'aleena@gmail.com','Female',9452139874,'/static/images/230307-195737 .jpg'),(34,'Jinsha','Udma ',685148,'jinsha@gmail.com','Female',9157081239,'/static/images/230311-194424 .jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
