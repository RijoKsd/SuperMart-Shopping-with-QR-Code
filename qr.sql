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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;

/*Data for the table `bill` */

insert  into `bill`(`bill_id`,`master_id`,`product_id`,`quantity`,`order_price`,`status_offer`) values (16,14,1,294,20,'no_offer'),(17,14,2,450,23,'offer'),(25,19,9,1,20,'no_offer'),(32,20,14,50,24,'no_offer'),(34,20,2,10,25,'no_offer'),(50,24,12,1,38,'offer'),(53,27,4,300,37,'no_offer'),(54,27,5,300,43,'offer'),(55,28,4,1,37,'no_offer'),(56,28,5,1,43,'offer'),(58,24,2,1,25,'no_offer'),(59,24,16,200,33,'offer'),(60,29,3,1,23,'offer'),(61,30,12,5,50,'no_offer'),(62,30,1,3,9,'offer'),(63,30,2,4,25,'no_offer'),(64,31,16,4,50,'no_offer'),(65,31,17,4,29,'offer'),(66,31,1,3,9,'offer'),(67,32,20,50,56,'no_offer'),(68,32,19,30,400,'no_offer'),(69,32,18,60,500,'no_offer');

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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

/*Data for the table `bill_master` */

insert  into `bill_master`(`master_id`,`shop_id`,`user_id`,`amount`,`date`,`status`) values (14,5,4,16230,'2023-03-23','paid'),(19,8,2,65050,'2023-03-26','cash'),(24,5,2,6663,'2023-03-26','paid'),(27,6,2,24000,'2023-03-27','cash'),(28,6,2,80,'2023-03-27','booked'),(29,5,2,23,'2023-03-27','cash'),(30,5,2,127,'2023-04-06','paid'),(31,5,2,343,'2023-04-10','cash'),(32,5,2,0,'2023-04-10','cart');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`type`,`user_id`,`complaint`,`date`,`reply_date`,`reply`) values (1,'shop',5,'Using more memory','2023-04-09','2023-04-09','We fixed this issue'),(2,'shop',7,'Cant add new products, plz fix it','2023-04-09','2023-04-09','We fixed it in latest update.\r\n'),(3,'shop',6,'Cant delete products, plz fix it\r\n','2023-04-09','2023-04-10','fixed'),(4,'user',2,'Profile not viewing ','2023-04-09','2023-04-09','Fixed it in latest update.\r\nPlease update your app'),(5,'user',2,'fou','2023-04-10','pending','pending');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(30) NOT NULL AUTO_INCREMENT,
  `sender_id` int(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `date` varchar(200) DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`sender_id`,`type`,`date`,`feedback`) values (1,7,'shop','2023-04-09','Good application very helpful'),(2,5,'shop','2023-04-09','Good app, more user friendly'),(3,6,'shop','2023-04-09','More customers are using this app to shop with our supermarket, Thank you for your produt');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `user_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`user_name`,`password`,`user_type`) values (1,'admin','admin','admin'),(2,'rijo@gmail.com','rijo','user'),(3,'salman@gmail.com','salman','user'),(4,'sachin@gmail.com','sachin','user'),(5,'wemart@gmail.com','wemart','shop'),(6,'orange@gmail.com','orange','shop'),(7,'bismi@gmail.com','bismi','shop'),(8,'green@gmail.com','green','shop'),(9,'mgmart@gmail.com','mgmart','pending'),(10,'vshsjkdk@gmail.com','gzgshdb','user');

/*Table structure for table `offer` */

DROP TABLE IF EXISTS `offer`;

CREATE TABLE `offer` (
  `offer_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `offer` varchar(20) DEFAULT NULL,
  `date_from` varchar(200) DEFAULT NULL,
  `date_to` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`offer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `offer` */

insert  into `offer`(`offer_id`,`product_id`,`offer`,`date_from`,`date_to`) values (11,1,'55','2023-03-29','2023-05-06'),(12,17,'5','2023-04-10','2023-04-11');

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

insert  into `payment`(`bank_id`,`bank_name`,`ifsc_code`,`account_no`,`account_balance`,`holder_id`) values (1,'rijo','SBI123','123123',80144,2),(2,'salman','SBI234','124124',89603,3),(3,'sachin','SBI124','125125',96903,4),(4,'we mart','SBI125','126126',127,5),(5,'orange','SBI126','127127',0,6),(6,'bismi','SBI127','128128',7623,7),(7,'green','SBI128','129129',120,8);

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`name`,`price`,`details`,`shop_id`,`image`) values (1,'Potato',20,' Potato per kg\r\n\r\n ',5,'/static/images/230322-235141 .jpg'),(2,'Onion',25,'Onion 1 kg\r\n ',5,'/static/images/220323-235415 .jpg'),(3,'Ginger',25,'Ginger Indian 200 g\r\n',5,'/static/images/220323-235948 .jpg'),(4,'Grapes',37,'Sonaka Seedless Grapes 500 g\r\nGrapes offer a wealth of health benefits due to their high nutrient and antioxidant contents. ',6,'/static/images/230323-000421 .jpg'),(5,'Watermelon',54,'Watermelon Kiran Big 1 pc (Approx. 2800 g - 4000 g)\r\nThis summer, get creative with your Watermelon! While cutting the Watermelon into thick slices and serving it with salt is the standard way to eat Watermelon, but there are plenty of other options for getting creative with this sweet and hydrating fruit.',6,'/static/images/230323-000518 .jpg'),(6,'Grapes',53,'Sharad Seedless Grapes 500 g\r\nGrapes offer a wealth of health benefits due to their high nutrient and antioxidant contents. ',6,'/static/images/230323-000633 .jpg'),(7,'Colouring Book',99,'Quixot Colouring Book (3 + yrs)',7,'/static/images/230323-000846 .jpg'),(8,'Animal picture book',99,'Animal Picture Book (3 + yrs)',7,'/static/images/230323-001011 .jpg'),(9,'Amul milk',20,' Amul Kool Kesar Flavoured Milk 180 ml (Bottle)\r\n ',8,'/static/images/230323-001416 .jpg'),(10,'Ghee',695,' Gowardhan Pure Cow Ghee 1 L (Pouch)\r\n ',8,'/static/images/230323-001552 .jpg'),(11,'Britannia ',21,' Britannia 100% Veg Choco Chill Sliced Cake 70 g\r\n ',8,'/static/images/230323-001844 .jpg'),(15,'Watermelon',40,'5kg watermelon',8,'/static/images/260323-093126 .jpg'),(16,'Grapes',50,'1kg grapes',5,'/static/images/260323-122632 .jpg'),(17,'Brush',30,'Soft colgate brush',5,'/static/images/100423-144311 .jpg'),(18,'500',500,'ty',5,'/static/images/100423-223155 .jpg'),(19,'400',400,'54',5,'/static/images/100423-223219 .jpg'),(20,'400',56,'j',5,'/static/images/100423-223717 .jpg');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  `rating` int(20) DEFAULT NULL,
  `shop_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`date`,`user_id`,`rating`,`shop_id`) values (1,'2023-03-23',2,5,5),(2,'2023-03-23',4,1,5),(3,'2023-03-23',4,2,6),(4,'2023-03-23',4,3,7),(5,'2023-03-23',3,2,7);

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

insert  into `stock`(`stock_id`,`product_id`,`quantity`) values (1,1,0),(2,2,13),(3,3,133),(4,4,100),(5,5,43),(6,6,324),(7,7,20),(8,8,9),(9,9,34),(10,10,453),(11,11,598),(12,12,549),(13,13,3400),(14,14,3),(15,15,455),(16,16,30),(17,17,30),(18,18,5665),(19,19,54),(20,20,56);

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`name`,`place`,`pincode`,`email`,`gender`,`phone_no`,`image`) values (2,'Rijo Sebastian ','Paduppu',671541,'rijo@gmail.com','Male',9446626926,'/static/images/220323-235004 .jpg'),(3,'Salman Farisi','Kasargod',671121,'salman@gmail.com','Male',9521488932,'/static/images/230322-232327 .jpg'),(4,'Sachin Santhosh ','Kolichal',671526,'sachin@gmail.com','Male',9621470896,'/static/images/230322-232440 .jpg'),(10,'rojo','sbbdb',658123,'vshsjkdk@gmail.com','Male',8574236985,'/static/images/230410-143900 .jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
