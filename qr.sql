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
  `order_price` int(20) DEFAULT NULL,
  `status_offer` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `bill` */

insert  into `bill`(`bill_id`,`master_id`,`product_id`,`quantity`,`order_price`,`status_offer`) values (1,1,13,45,80,'offer'),(2,2,9,4,20,'no_offer'),(3,2,10,2,570,'offer');

/*Table structure for table `bill_master` */

DROP TABLE IF EXISTS `bill_master`;

CREATE TABLE `bill_master` (
  `master_id` int(20) NOT NULL AUTO_INCREMENT,
  `shop_id` int(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `date` varchar(200) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `bill_master` */

insert  into `bill_master`(`master_id`,`shop_id`,`user_id`,`amount`,`date`,`status`) values (1,5,4,3600,'2023-03-23','paid'),(2,8,4,1220,'2023-03-23','cash');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `complaint_id` int(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `complaint` varchar(200) DEFAULT NULL,
  `date` varchar(200) DEFAULT NULL,
  `reply_date` varchar(200) DEFAULT 'pending',
  `reply` varchar(200) DEFAULT 'pending',
  PRIMARY KEY (`complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`type`,`user_id`,`complaint`,`date`,`reply_date`,`reply`) values (1,'shop',5,'sending by we mart','2023-03-23','2023-03-23','fixed wishing'),(2,'user',4,'bad app','2023-03-23','2023-03-23','Thankyou ');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(30) NOT NULL AUTO_INCREMENT,
  `sender_id` int(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `date` varchar(200) DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`sender_id`,`type`,`date`,`feedback`) values (1,5,'shop','2023-03-23','sending by we mart'),(2,4,'user','2023-03-23','good app ');

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

insert  into `login`(`login_id`,`user_name`,`password`,`user_type`) values (1,'admin','admin','admin'),(2,'rijo@gmail.com','rijo','user'),(3,'salman@gmail.com','salman','user'),(4,'sachin@gmail.com','sachin','user'),(5,'wemart@gmail.com','wemart','shop'),(6,'orange@gmail.com','orange','shop'),(7,'bismi@gmail.com','bismi','shop'),(8,'green@gmail.com','green','shop'),(9,'mgmart@gmail.com','mgmart','pending');

/*Table structure for table `offer` */

DROP TABLE IF EXISTS `offer`;

CREATE TABLE `offer` (
  `offer_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `offer` varchar(20) DEFAULT NULL,
  `date_from` varchar(200) DEFAULT NULL,
  `date_to` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`offer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `offer` */

insert  into `offer`(`offer_id`,`product_id`,`offer`,`date_from`,`date_to`) values (1,1,'5','2023-03-22','2023-03-22'),(2,2,'8','2023-03-22','2023-03-24'),(3,3,'2','2023-03-24','2023-04-06'),(4,5,'20','2023-03-23','2023-04-08'),(5,7,'3','2023-03-24','2023-04-07'),(6,10,'18','2023-03-23','2023-04-07'),(7,12,'25','2023-03-23','2023-03-31'),(8,13,'60','2023-03-23','2023-04-08');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`bank_id`,`bank_name`,`ifsc_code`,`account_no`,`account_balance`,`holder_id`) values (1,'rijo','SBI123','123123',100000,2),(2,'salman','SBI234','124124',90000,3),(3,'sachin','SBI124','125125',116400,4),(4,'we mart','SBI125','126126',3600,5),(5,'orange','SBI126','127127',0,6),(6,'bismi','SBI127','128128',0,7),(7,'green','SBI128','129129',0,8);

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`name`,`price`,`details`,`shop_id`,`image`) values (1,'Potato',20,' Potato per kg\r\n\r\nPotato is a root vegetable and the most versatile of all. It is widely used across Indian kitchens paired with numerous other vegetables for preparation of several delicacies.',5,'/static/images/230322-235141 .jpg'),(2,'Onion',25,'Onion 1 kg\r\nThe beauty of an Onion is that it can go just about anywhere. Chopped Onions are perfect for fish dishes, quiche, stews and chili. Onions can be roasted whole until tender and then chopped, or caramelized by slowly cooking them in butter for a delicious treat for a delicious treat. ',5,'/static/images/220323-235415 .jpg'),(3,'Ginger',25,'Ginger Indian 200 g\r\nGinger give a good kick to any dish with the perfect amount of spicy, tangy, and bold flavor in just a pinch.',5,'/static/images/220323-235948 .jpg'),(4,'Grapes',37,'Sonaka Seedless Grapes 500 g\r\nGrapes offer a wealth of health benefits due to their high nutrient and antioxidant contents. ',6,'/static/images/230323-000421 .jpg'),(5,'Watermelon',54,'Watermelon Kiran Big 1 pc (Approx. 2800 g - 4000 g)\r\nThis summer, get creative with your Watermelon! While cutting the Watermelon into thick slices and serving it with salt is the standard way to eat Watermelon, but there are plenty of other options for getting creative with this sweet and hydrating fruit.',6,'/static/images/230323-000518 .jpg'),(6,'Grapes',53,'Sharad Seedless Grapes 500 g\r\nGrapes offer a wealth of health benefits due to their high nutrient and antioxidant contents. ',6,'/static/images/230323-000633 .jpg'),(7,'Colouring Book',99,'Quixot Colouring Book (3 + yrs)',7,'/static/images/230323-000846 .jpg'),(8,'Animal picture book',99,'Animal Picture Book (3 + yrs)',7,'/static/images/230323-001011 .jpg'),(9,'Amul milk',20,'Amul Kool Kesar Flavoured Milk 180 ml (Bottle)\r\nFlavoured milk is one of the best options to make children have milk without any complaints.',8,'/static/images/230323-001416 .jpg'),(10,'Ghee',695,'Gowardhan Pure Cow Ghee 1 L (Pouch)\r\nGhee is a class of clarified butter that originated in ancient India. It is commonly used in Indian cooking.\r\n',8,'/static/images/230323-001552 .jpg'),(11,'Britannia ',21,'Britannia 100% Veg Choco Chill Sliced Cake 70 g\r\nBritannia 100% Veg Choco Chill Sliced Cake 70 g is one of the most loved treats.',8,'/static/images/230323-001844 .jpg'),(12,'Bauli Moonfils Veg Choco Puff ',50,'  Bauli Moonfils Veg Choco Puff Rolls is a piece of heavenly dessert that is so light and tasty! ',5,'/static/images/230323-002036 .jpg'),(13,'Sunfeast Dark Fantasy Belgian ',199,'Start your day on a refreshing note with Sunfeast Dark Fantasy Belgian Chocolate Milkshake. ',5,'/static/images/230323-002324 .jpg'),(14,'Maggi',24,'Maggi 2-Minute Masala Instant Noodles 70 g\r\nMaggi 2-Minutes Noodles have been a classic Indian snack for a good few decades now. Nestle brings you another delicious instant food product - Maggi 2-Minute Masala Instant Noodles',5,'/static/images/230323-002604 .jpg');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `rating` int(20) DEFAULT NULL,
  `shop_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`date`,`user_id`,`rating`,`shop_id`) values (1,'2023-03-23',2,4,5),(2,'2023-03-23',4,1,5),(3,'2023-03-23',4,2,6),(4,'2023-03-23',4,3,7);

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

insert  into `shop`(`shop_id`,`name`,`place`,`pincode`,`mail`,`phone`,`image`) values (5,'We mart','Ernakulam',675487,'wemart@gmail.com',9526108886,'/static/images/230322-234331 .jpg'),(6,'Orange','Kanhangad',671542,'orange@gmail.com',7854123698,'/static/images/230322-234419 .jpg'),(7,'Bismi','Bandaduka',671541,'bismi@gmail.com',8541236974,'/static/images/230322-234510 .jpg'),(8,'Green Hyper Market','Kasargod',671521,'green@gmail.com',9851478523,'/static/images/230322-234553 .jpg'),(9,'MG mart','Munnad',671541,'mgmart@gmail.com',9654123987,'/static/images/230322-234655 .jpg');

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `stock_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `quantity` int(20) DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

insert  into `stock`(`stock_id`,`product_id`,`quantity`) values (1,1,500),(2,2,567),(3,3,234),(4,4,400),(5,5,343),(6,6,324),(7,7,50),(8,8,56),(9,9,41),(10,10,453),(11,11,598),(12,12,555),(13,13,3400),(14,14,56);

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

insert  into `user`(`user_id`,`name`,`place`,`pincode`,`email`,`gender`,`phone_no`,`image`) values (2,'Rijo Sebastian ','Paduppu',671541,'rijo@gmail.com','Male',9446626926,'/static/images/220323-235004 .jpg'),(3,'Salman Farisi','Kasargod',671121,'salman@gmail.com','Male',9521488932,'/static/images/230322-232327 .jpg'),(4,'Sachin Santhosh ','Kolichal',671526,'sachin@gmail.com','Male',9621470896,'/static/images/230322-232440 .jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
