-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ultimabd
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `arrival` time NOT NULL,
  `checkin_date` date NOT NULL,
  `checkout_date` date NOT NULL,
  `comments` varchar(45) DEFAULT NULL,
  `id_user` int NOT NULL,
  `id_product` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_booking_user_idx` (`id_user`),
  KEY `fk_booking_product_idx` (`id_product`),
  CONSTRAINT `fk_booking_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_booking_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'09:30:59','2023-03-31','2023-04-15','Falta abonar un 30% de la reserva',1,1);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `images_url` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Hotel','Variedad de habitaciones con baño privado y múltiples servicios','https://images.unsplash.com/photo-1564501049412-61c2a3083791?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fEhvdGVsfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60'),(2,'Hostel','Los Hostels tienen dormitorios con varias habitaciones, en la que los huéspedes comparten estancia, y un mismo baño común a todas ellas. Además, suelen contar con zonas comunes para que los clientes puedan relacionarse, cocinar, etc.','https://media.istockphoto.com/id/1000073424/photo/interior-of-female-dormitory-room-at-hostel-3d-rendering.jpg?b=1&s=170667a&w=0&k=20&c=uvx27WKNDEGLW8d7L6Tkkf6PuutQ_Klg8aGQK7m1LbA='),(3,'Departamento','Piso personal con llave propia para contar con libertad en los horarios','https://media.istockphoto.com/id/1367228032/photo/condos-orlando-florida-usa.jpg?b=1&s=170667a&w=0&k=20&c=94vO6bgAPVEQgu2yq_IrACwp35aj_BPqHWzMf56Kt_g='),(4,'Bed & Breakfast','Se caracteriza por un ambiente familiar y lo común es que los propios dueños convivan con los turistas en el mismo. Ofrecen desayuno y una cama para pasar la noche.','https://media.istockphoto.com/id/1322233870/photo/wide-angle-shot-of-a-modern-tiny-home-picnic-table-and-xeriscaped-fire-pit-in-western.jpg?b=1&s=170667a&w=0&k=20&c=lUba5KEmk36jc7WWy5ZXYlJEZryxL6kOmyWACpQsLfc=');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `id_country` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_city_country_idx` (`id_country`),
  CONSTRAINT `fk_city_country` FOREIGN KEY (`id_country`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Buenos Aires',1),(2,'La Paz',2),(3,'Rio de Janeiro',3),(4,'Santiago de Chile',4),(5,'Bogotá',5),(6,'Quito',6),(7,'Ciudad de Mexico',7),(8,'Asunción',8),(9,'Lima',9),(10,'Montevideo',10),(11,'Caracas',11);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Argentina'),(2,'Bolivia'),(3,'Brasil'),(4,'Chile'),(5,'Colombia'),(6,'Ecuador'),(7,'México'),(8,'Paraguay'),(9,'Perú'),(10,'Uruguay'),(11,'Venezuela');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `features`
--

DROP TABLE IF EXISTS `features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `features` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `features`
--

LOCK TABLES `features` WRITE;
/*!40000 ALTER TABLE `features` DISABLE KEYS */;
INSERT INTO `features` VALUES (1,'Cocina',NULL),(2,'Piscina',NULL),(3,'Estacionamiento gratuito',NULL),(4,'Televisor',NULL),(5,'Wi-fi',NULL),(6,'Aire Acondicionado',NULL),(7,'Caja de Seguridad',NULL),(8,'Spa',NULL),(9,'Ropa de Cama y Toallones',NULL);
/*!40000 ALTER TABLE `features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `url` text NOT NULL,
  `id_product` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_image_product_idx` (`id_product`),
  CONSTRAINT `fk_image_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (17,'Hotel La Perla','https://images.unsplash.com/photo-1566073771259-6a8506099945?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',1),(18,'Hotel La Perla','https://images.unsplash.com/photo-1601369435496-2f93548671e0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cGlzY2luYXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60',1),(19,'Hotel La Perla','https://images.unsplash.com/photo-1584622650111-993a426fbf0a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YmF0aHJvb218ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60',1),(20,'Hotel La Perla','https://plus.unsplash.com/premium_photo-1674574586052-6d0cee95c473?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8YmVkcm9vbXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60',1),(21,'Hotel El Cabildo','https://images.unsplash.com/photo-1608198399988-341f712c3711?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGhvc3RlbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60',2),(22,'Hotel El Cabildo','https://images.unsplash.com/photo-1586798271654-0471bb1b0517?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGJhdGhyb29tfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60',2),(23,'Hotel El Cabildo','https://images.unsplash.com/photo-1602774895945-a4983ccb137b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8cGlzY2luYXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60',2),(24,'Hotel Faena','	https://images.unsplash.com/photo-1573052905904-34ad8c27f0cc?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTAwfHxIb3RlbCUyMEZhZW5hfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60',3),(25,'Hotel Faena','	https://images.unsplash.com/photo-1603825491103-bd638b1873b0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGJhdGhyb29tfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60',3),(26,'Hotel Faena','	https://images.unsplash.com/photo-1616594039964-ae9021a400a0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGJlZHJvb218ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60',3),(27,'Casa Franca Recoleta Hostel','https://images.unsplash.com/photo-1522798514-97ceb8c4f1c8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGhvdGVsfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60',4),(28,'Casa Franca Recoleta Hostel','https://images.unsplash.com/photo-1616537937163-387d3f079de8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTV8fGJhdGhyb29tfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60',4),(29,'Casa Franca Recoleta Hostel','https://images.unsplash.com/photo-1602774895754-2772f8a08f6b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTh8fHBpc2NpbmF8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60',4),(30,'Studio in the heart of Palermo','https://images.unsplash.com/photo-1613694699988-9e77e23b15d1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8YXBhcnRhbWVudHN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60',5),(31,'Studio in the heart of Palermo','https://media.istockphoto.com/id/1400295846/photo/jets-of-clean-water-flowing-in-the-shower-cabin.jpg?b=1&s=170667a&w=0&k=20&c=4_SLcqigWATGRnYPTCN00ELkwYXU7FFXcS2Ocne4YU4=',5),(32,'Studio in the heart of Palermo','https://images.unsplash.com/photo-1664192579009-07ded37d50e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8YnJha2Zhc3R8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60',5);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `active` tinyint NOT NULL,
  `address` varchar(255) NOT NULL,
  `latitude` varchar(45) NOT NULL,
  `longitude` varchar(45) NOT NULL,
  `area` varchar(45) DEFAULT NULL,
  `average_score` double DEFAULT NULL,
  `id_category` int NOT NULL,
  `id_city` int NOT NULL,
  `site_policies` mediumtext NOT NULL,
  `ubication` text,
  PRIMARY KEY (`id`),
  KEY `fk_product_category_idx` (`id_category`),
  KEY `fk_product_city_idx` (`id_city`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_product_city` FOREIGN KEY (`id_city`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Hotel La Perla','La Perla Hotel cuenta con servicio personalizado y goza de una ubicación privilegiada en el distrito de Balvanera',1,'Av. Jujuy 36, Buenos Aires','34° 36','58° 26',NULL,NULL,1,1,'Check-In 12hs/Check-Out 10 hs',NULL),(2,'Hotel El Cabildo','	El Hotel El Cabildo ofrece un alojamiento moderno en el centro de Buenos Aires. ',1,'Lavalle 748,Buenos Aires','34° 36','58° 26',NULL,NULL,1,1,'Check-In 12hs/Check-Out 10 hs',NULL),(3,'Hotel Faena','Faena Hotel celebra el espíritu de la ciudad que nunca duerme',1,' Martha Salotti 445,Buenos Aires','34° 36','58° 26',NULL,NULL,1,1,'Check-In 12hs/Check-Out 10 hs',NULL),(4,'Casa Franca Recoleta Hostel','Todas las habitaciones tienen patio. La Casa Franca Recoleta Hostel ofrece algunos alojamientos con vistas a la ciudad.',1,'1854 José Andrés Pacheco de Melo,Buenos Aires','34° 36','58° 26',NULL,NULL,2,1,'Check-In 12hs/Check-Out 10 hs',NULL),(5,'Studio in the heart of Palermo','	Departamento amplio con múltiples comodidades para su hospedaje',1,'Dorrego 1869,Buenos Aires','34° 36','58° 26',NULL,NULL,3,1,'Check-In 12hs/Check-Out 10 hs',NULL),(6,'Lina Tango Guesthouse','El establecimiento se encuentra en el distrito histórico y bohemio de San Telmo y proporciona WiFi gratuita y desayunos sencillos todos los días',1,'Estados Unidos 780,Buenos Aires','34° 36','58° 26',NULL,NULL,4,1,'Check-In 12hs/Check-Out 10 hs',NULL),(7,'Patio de Piedra Hotel Boutique','	El Patio de Piedra Hotel Boutique ocupa una casa colonial con paredes de madera tallada.',1,'Genaro Sanjines N°451, La Paz','30°44','59°38',NULL,NULL,1,2,'Check-In 12hs/Check-Out 10 hs',NULL),(8,'Hostal Iskanwaya','El Hostal Iskanwaya se encuentra a 1,8 km de las estaciones de teleférico Buenos Aires y Sopocachi, y a 4 km del aeropuerto internacional de El Alto.',1,'G.Gonzalez 1542','30°44','59°38',NULL,NULL,2,2,'Check-In 12hs/Check-Out 10 hs',NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_feature`
--

DROP TABLE IF EXISTS `product_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_feature` (
  `id_product` int NOT NULL,
  `id_feature` int NOT NULL,
  KEY `fk_product_features_product_idx` (`id_product`),
  KEY `fk_product_features_features_idx` (`id_feature`),
  CONSTRAINT `fk_product_features_features` FOREIGN KEY (`id_feature`) REFERENCES `features` (`id`),
  CONSTRAINT `fk_product_features_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_feature`
--

LOCK TABLES `product_feature` WRITE;
/*!40000 ALTER TABLE `product_feature` DISABLE KEYS */;
INSERT INTO `product_feature` VALUES (1,2),(1,3),(1,5),(1,6),(1,8),(2,2),(2,3),(2,5),(2,6),(2,8),(3,2),(3,3),(3,5),(3,6),(3,8),(4,1),(4,3),(4,4),(4,5),(8,1),(8,3),(8,4),(8,5),(5,3),(5,6),(5,7),(5,9),(6,6),(6,7),(6,3),(7,2),(7,3),(7,4),(7,5),(7,6),(7,4);
/*!40000 ALTER TABLE `product_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint NOT NULL,
  `id_city` int NOT NULL,
  `id_role` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_idx` (`id_role`),
  KEY `fk_user_city_idx` (`id_city`),
  CONSTRAINT `fk_user_city` FOREIGN KEY (`id_city`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Emilio','Ontiveros','eontiveros@gmail.com','123456789',1,5,1),(2,'Jorge','Moreno','jmoreno@gmail.com','123456789',1,5,1),(3,'Juan','Aguirre','jaguirre@gmail.com','123456789',1,5,1),(4,'Juan Alexander','Melo','amelo@gmail.com','123456789',1,5,1),(5,'Fabio S.','Boschetti','fboschetti@gmail.com','123456789',1,1,1),(6,'Veronica','Albuquerque','valbuquerque@gmail.com','123456789',1,1,1),(7,'Belén','Santander','bsantander@gmail.com','123456789',1,1,2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-03 20:56:47
