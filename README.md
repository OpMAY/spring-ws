#Spring Project v a.2
##추가 목록
####1.InstagramAPI
 - 인스타그램 API가 추가되었습니다. (`InstagramAPI.class` 참조)

####2. AOP
 - AOP 설정이 추가되었습니다. (`aop` 디렉토리)
 - 기존 코드에 영향을 주지 않으면서 메소드에 부가기능을 추가할 수 있습니다.
 - `LogAop`클래스는 각 컨트롤러, 서비스의 시작과 끝에서 로그를 기록합니다.
 - `RequestLogger`클래스는 `Bean Scope: request`로, 각 request의 uuid를 기록하여 로그 추적을 용이하게 해줍니다.
 - `LogAop`에 메소드를 추가/수정하거나 `execution`을 설정해서 추적 설정을 변경할 수 있습니다.

##업데이트

####0.Build
 - 이제 모든 설정 파일이 Github에 올라가게 됩니다. (`log4j.xml` 제외)

####1.Properties
 - 모든 properties는 Github에 올라가게 됩니다.
 - 모든 설정들은 `key.properties`, `config.properties`로 통일됩니다.
 - `.properties`의 모든 파일들의 value는 암호화됩니다.
 - `.properties`의 모든 value는 프로젝트를 빌드할 때 복호화됩니다.
 - 이제 PropertyResource Annotation을 사용하지 않고도 Value Annotation이 사용가능합니다.

####2.Middleware
 - 이제 package_path를 사용하지 않아도 자동으로 Class Casting이 완료됩니다.
 JSON 및 JSONArray는 자동으로 Class Casting이 완료됩니다. (Ref : `mapper.xml`, `Middleware Package`)

####3.FileUploadUtility
 - File Upload Utility 에 전략패턴을 적용하였습니다.
 - `FileUploadUtility` 클래스의 생성자에 있는 `@Qualifier`에서 파일업로드 전략을 변경할 수 있습니다.

####4. DB Rollback System
 - 메소드에 `@Transactional`을 붙이면 로직 중간에 에러 발생시 자동으로 DB가 롤백됩니다.
 - 기본적으로 **Unchecked Exception** 만 롤백됩니다.
 - Checked Exception 발생시에도 롤백을 하고싶다면 어노테이션에 속성에서 설정해야합니다.
 - 기본적으로 `@Transactional`메소드가 다른 `@Transactional`메소드를 내부 호출하면 호출된 메소드의 DB Transaction 은 호출한 메소드에 귀속됩니다.

####5. ProtocolBuilder
 - 모든 REST API는 `ProtocolBuilder.class`를 통해서 만들 수 있습니다.(사용법 `ProtocolBuilder.class` 참조)
 - `ProtocolBuilder.class`를 통해 받는 서버의 메세지는 모든 Model로 return할 수 있도록 수정되었습니다.
 - ProtocolBuilder의 GET, POST 방식에 있어서 Body 및 URL에 더 좋게 도움을 줄 수 있도록 `ProtocolBuilderHelper.class`가 추가되었습니다.(사용법 `ProtocolBuilderHelper.class` 참조)

####6. SNS Login API
 - `key.properties`에 SNS Login에 관련된 키가 포함되었습니다.
 - `ProtocolBuilder.class`를 통해 재구성되었습니다.

####7. applicationContext.xml, web.xml -> config java class
 - 코드 관리의 용이성 + 컴파일단계에서 에러를 잡을 수 있도록 xml 파일에서 java class로 변경하였습니다.
 - spring 구조상 dispatcher servlet은 유지됩니다.

##Database Init
```
CREATE DATABASE  IF NOT EXISTS `flowtest` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `flowtest`;
-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: flowtest
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `arrayrecursivetest`
--

DROP TABLE IF EXISTS `arrayrecursivetest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arrayrecursivetest` (
  `no` int NOT NULL AUTO_INCREMENT,
  `recursives` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arrayrecursivetest`
--

LOCK TABLES `arrayrecursivetest` WRITE;
/*!40000 ALTER TABLE `arrayrecursivetest` DISABLE KEYS */;
INSERT INTO `arrayrecursivetest` VALUES (1,'[{\"package_path\":\"com.model.Recursive\",\"no\":1,\"name\":\"kimwoosik\",\"price\":1000},{\"package_path\":\"com.model.Recursive\",\"no\":2,\"name\":\"kimwoosik\",\"price\":1000}]');
/*!40000 ALTER TABLE `arrayrecursivetest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arraytest`
--

DROP TABLE IF EXISTS `arraytest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arraytest` (
  `no` int NOT NULL AUTO_INCREMENT,
  `usertest` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arraytest`
--

LOCK TABLES `arraytest` WRITE;
/*!40000 ALTER TABLE `arraytest` DISABLE KEYS */;
INSERT INTO `arraytest` VALUES (1,'[{\"no\":0,\"email\":\"zlzldntlr@naver.com\",\"id\":\"zlzldntlr\",\"name\":\"김우식\",\"grant\":\"normal\",\"access_token\":\"token\"},{\"no\":0,\"email\":\"zlzldntlr@naver.com\",\"id\":\"zlzldntlr\",\"name\":\"김우식\",\"grant\":\"normal\",\"access_token\":\"token\"},{\"no\":0,\"email\":\"zlzldntlr@naver.com\",\"id\":\"zlzldntlr\",\"name\":\"김우식\",\"grant\":\"normal\",\"access_token\":\"token\"}]');
/*!40000 ALTER TABLE `arraytest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `no` int NOT NULL AUTO_INCREMENT,
  `testcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'asdfs'),(3,'asdf');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `no` int NOT NULL AUTO_INCREMENT,
  `usertest` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'{\"no\":0,\"email\":\"zlzldntlr@naver.com\",\"id\":\"zlzldntlr\",\"name\":\"김우식\",\"grant\":\"normal\",\"access_token\":\"token\"}'),(3,'test');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-19 23:49:21

```