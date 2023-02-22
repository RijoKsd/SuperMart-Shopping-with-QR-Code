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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `bill` */

insert  into `bill`(`bill_id`,`master_id`,`product_id`,`quantity`) values (1,1,20,10),(2,1,20,100),(3,1,23,2),(4,1,31,2);

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

insert  into `bill_master`(`master_id`,`shop_id`,`user_id`,`amount`,`date`,`status`) values (1,18,2,100,'2023-02-21','add to cart');

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`type`,`user_id`,`complaint`,`date`,`reply_date`,`reply`) values (33,'user',2,'registration image selection not found','2023-02-15','2023-02-19','We fixed it in this update, please use latest version'),(34,'user',30,'More memmory using','2023-02-15','pending','pending'),(35,'user',31,'bad UI','2023-02-15','pending','pending'),(36,'user',31,'using more memmory ','2023-02-15','pending','pending'),(37,'user',32,'rijo','2023-02-19','2023-02-19','replied\r\n\r\n');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(30) NOT NULL AUTO_INCREMENT,
  `sender_id` int(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`sender_id`,`type`,`date`,`feedback`) values (25,30,'user','2023-02-15','good app????????'),(26,31,'user','2023-02-15','good application '),(27,2,'user','2023-02-19','today'),(28,32,'user','2023-02-19','rijo');

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

insert  into `login`(`login_id`,`user_name`,`password`,`user_type`) values (1,'admin','admin','admin'),(2,'rijo@gmail.com','rijo','user'),(3,'salman@gmail.com','salman','user'),(18,'jio@gmail.com','jio','shop'),(20,'spicy@gmail.com','spicy','shop'),(21,'max@gmail.com','max','shop'),(22,'lucky@gmail.com','lucky','pending'),(23,'aaa@gmail.com','aaa','shop'),(24,'test@gmail.com','test','pending'),(29,'sanesh@gmail.com','sanesh','user'),(30,'lenin@gmail.com','lenin','user'),(31,'sachin@gmail.com','sachin','user'),(32,'akash@gmail.com','akash','user'),(33,'rijoksd1@gmail.com','123','user'),(34,'9747215351','BG83s57ohjM6-TO','user');

/*Table structure for table `offer` */

DROP TABLE IF EXISTS `offer`;

CREATE TABLE `offer` (
  `offer_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `offer` varchar(20) DEFAULT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  PRIMARY KEY (`offer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `offer` */

insert  into `offer`(`offer_id`,`product_id`,`offer`,`date_from`,`date_to`) values (7,20,'80','2023-02-12','2023-03-11'),(8,23,'23','2023-02-12','2023-03-11'),(9,22,'2','2023-02-12','2023-03-11'),(10,24,'1','2023-02-15','2023-03-08');

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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`name`,`price`,`details`,`shop_id`,`image`) values (20,'Salt',100,'1kg salt',18,'/static/images/230127-231204 .jpg'),(21,'Butter',54,'Amul Butter\r\n',20,'/static/images/230211-130940 .jpg'),(22,'Rice',105,'10kg ',21,'/static/images/230211-131014 .jpg'),(23,'Onion',58,'1 kg ',18,'/static/images/230211-143217 .jpg'),(24,'Coffee',30,'Nescafe coffee',21,'/static/images/230212-095623 .jpg'),(25,'Bread',30,'Wheat Bread',20,'/static/images/230212-095908 .jpg'),(26,'Brush',25,'Oral B  brush\r\n',20,'/static/images/230212-100008 .jpg'),(27,'Dettol',50,'100ml Dettol ',20,'/static/images/230212-100037 .jpg'),(28,'Tea Powder',30,' TATA tea',20,'/static/images/230212-100105 .jpg'),(29,'Paste',30,'Colgate paste 200g',20,'/static/images/230212-100133 .jpg'),(30,'Carrot',25,'carrot 500g',23,'/static/images/230212-100732 .jpg'),(31,'Cauliflower',30,'',18,'/static/images/230221-095728 .jpg'),(32,'Turmeric powder',25,'100g turmeric powder',18,'/static/images/230221-095823 .jpg');

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

insert  into `rating`(`rating_id`,`date`,`user_id`,`rating`,`shop_id`) values (1,'2023-02-19',31,1,18),(2,'2023-02-19',31,0,20),(3,'2023-02-19',2,2,18),(4,'2023-02-19',2,5,20),(5,'2023-02-19',2,5,21),(6,'2023-02-19',2,3,23),(7,'2023-02-19',32,5,21);

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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

insert  into `stock`(`stock_id`,`product_id`,`quantity`) values (43,20,12),(44,21,32),(45,22,34),(46,23,5),(47,24,23),(48,25,45),(49,26,55),(50,27,5),(51,28,12),(52,29,60),(53,30,4),(54,31,5),(55,32,17);

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

insert  into `user`(`user_id`,`name`,`place`,`pincode`,`email`,`gender`,`phone_no`,`image`) values (2,'rijo','ksd',671541,'rijo@gmail.com','male',9446626926,'/static/images/230125-073807 .jpg'),(3,'salman','ksd',671123,'salman@gmail.com','male',9456324789,'/static/images/230125-211830 .jpg'),(29,'Sanesh','kasargod ',671541,'sanesh@gmail.com','Male',9468123564,'/static/images/230214-141007 .jpg'),(30,'Lenin','Kasargod',671541,'lenin@gmail.com','Male',9421386480,'/static/images/230215-073018 .jpg'),(31,'Sachin','Kannur',671544,'sachin@gmail.com','Male',9564812398,'/static/images/230215-195411 .jpg'),(32,'Akash','Udma',671543,'akash@gmail.com','Male',9547832159,'/static/images/230219-130138 .jpg'),(33,'sreeraj','munnad',671541,'rijoksd1@gmail.com','Female',9747215351,'/static/images/230221-121142 .jpg'),(34,'ttt','bbg',255566,'9747215351','Male',9747215351,'/static/images/230221-142752 .jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
