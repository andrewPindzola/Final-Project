
CREATE TABLE `canadiansolar` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `YearofIssue` int DEFAULT NULL,
  `Revenue` decimal(20,5) DEFAULT NULL,
  `COGS` decimal(20,5) DEFAULT NULL,
  `Depreciation` decimal(20,5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `firstsolar` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `YearofIssue` int DEFAULT NULL,
  `Revenue` decimal(20,5) DEFAULT NULL,
  `COGS` decimal(20,5) DEFAULT NULL,
  `Depreciation` decimal(20,5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `jinkosolar` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `YearofIssue` int DEFAULT NULL,
  `Revenue` decimal(20,5) DEFAULT NULL,
  `COGS` decimal(20,5) DEFAULT NULL,
  `Depreciation` decimal(20,5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `nexteraenergy` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `YearofIssue` int DEFAULT NULL,
  `Revenue` decimal(20,5) DEFAULT NULL,
  `COGS` decimal(20,5) DEFAULT NULL,
  `Depreciation` decimal(20,5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `sunpower` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `YearofIssue` int DEFAULT NULL,
  `Revenue` decimal(20,5) DEFAULT NULL,
  `COGS` decimal(20,5) DEFAULT NULL,
  `Depreciation` decimal(20,5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `tesla` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `YearofIssue` int DEFAULT NULL,
  `Revenue` decimal(20,5) DEFAULT NULL,
  `COGS` decimal(20,5) DEFAULT NULL,
  `Depreciation` decimal(20,5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `user_type` (
  `lastname` varchar(30) NOT NULL,
  `neutral_count` int DEFAULT NULL,
  `depro_count` int DEFAULT NULL,
  `revo_count` int DEFAULT NULL,
  PRIMARY KEY (`lastname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `user_interest` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `canadiansolar` int DEFAULT NULL,
  `firstsolar` int DEFAULT NULL,
  `jinkosolar` int DEFAULT NULL,
  `nexteraenergy` int DEFAULT NULL,
  `sunpower` int DEFAULT NULL,
  `tesla` int DEFAULT NULL,
  `isUserNeutral` varchar(10) DEFAULT NULL,
  `isUserDepro` varchar(10) DEFAULT NULL,
  `isUserRevo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  KEY `lastname` (`lastname`),
  CONSTRAINT `user_interest_ibfk_1` FOREIGN KEY (`lastname`) REFERENCES `user_type` (`lastname`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `devusoid_table` (
  `lastname` varchar(30) DEFAULT NULL,
  `devusoid` int NOT NULL,
  PRIMARY KEY (`devusoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `user_junction` (
  `lastname` varchar(30) DEFAULT NULL,
  `devusoid` int DEFAULT NULL,
  KEY `lastname` (`lastname`),
  KEY `devusoid` (`devusoid`),
  CONSTRAINT `user_junction_ibfk_1` FOREIGN KEY (`lastname`) REFERENCES `user_type` (`lastname`),
  CONSTRAINT `user_junction_ibfk_2` FOREIGN KEY (`devusoid`) REFERENCES `devusoid_table` (`devusoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Below is the data from EDGAR Database from SEC.gov on companies 10-K forms and foreign equivalents */

/*
-- Query: SELECT * FROM giza.canadiansolar
LIMIT 0, 100

-- Date: 2023-03-23 12:58
*/
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (1,2012,1294829.00000,1204468.00000,81398.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (2,2013,1654356.00000,1378661.00000,80821.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (3,2014,2960627.00000,2379633.00000,82627.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (4,2015,3467626.00000,2890856.00000,94217.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (5,2016,2853078.00000,2435890.00000,95849.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (6,2017,3390393.00000,2752795.00000,99273.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (7,2018,3744512.00000,2969430.00000,129256.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (8,2019,3200583.00000,2482086.00000,159723.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (9,2020,3476495.00000,2786581.00000,209118.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (10,2021,5277169.00000,4367857.00000,282769.00000);
/*
-- Query: SELECT * FROM giza.firstsolar
LIMIT 0, 100

-- Date: 2023-03-23 13:02
*/
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (1,2012,3368545.00000,2515796.00000,350228.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (2,2013,3308989.00000,2446235.00000,234370.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (3,2014,3391187.00000,2566246.00000,245798.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (4,2015,3578995.00000,2659728.00000,257825.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (5,2016,2904563.00000,2266145.00000,230940.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (6,2017,2941324.00000,2392377.00000,115313.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (7,2018,2244044.00000,1851867.00000,130736.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (8,2019,3063117.00000,2513905.00000,205475.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (9,2020,2711332.00000,2030659.00000,232925.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (10,2021,2923377.00000,2193423.00000,259900.00000);
/*
-- Query: SELECT * FROM giza.jinkosolar
LIMIT 0, 100

-- Date: 2023-03-23 13:03
*/
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (1,2012,719215.00000,684379.00000,48473.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (2,2013,1061825.00000,846223.00000,49247.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (3,2014,1608247.00000,1247218.00000,56091.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (4,2015,2481781.00000,1977011.00000,60694.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (5,2016,3082333.00000,2525032.00000,64681.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (6,2017,4068817.00000,3609021.00000,92301.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (7,2018,3430563.00000,3131244.00000,116649.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (8,2019,4272787.00000,3492574.00000,110638.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (9,2020,5383825.00000,4437977.00000,177908.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (10,2021,6406572.00000,5361812.00000,251669.00000);
/*
-- Query: SELECT * FROM giza.nexteraenergy
LIMIT 0, 100

-- Date: 2023-03-23 13:03
*/
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (1,2012,93000.00000,44000.00000,24000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (2,2013,179000.00000,107000.00000,54000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (3,2014,356000.00000,179000.00000,97000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (4,2015,471000.00000,256000.00000,141000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (5,2016,772000.00000,470000.00000,235000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (6,2017,807000.00000,495000.00000,226000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (7,2018,771000.00000,328000.00000,203000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (8,2019,855000.00000,622000.00000,259000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (9,2020,917000.00000,664000.00000,271000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (10,2021,982000.00000,748000.00000,288000.00000);
/*
-- Query: SELECT * FROM giza.sunpower
LIMIT 0, 100

-- Date: 2023-03-23 13:04
*/
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (1,2012,2417501.00000,2171103.00000,108656.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (2,2013,2507203.00000,2016131.00000,98191.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (3,2014,3027265.00000,2402138.00000,108795.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (4,2015,1576473.00000,1331827.00000,138007.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (5,2016,2559562.00000,2369596.00000,170537.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (6,2017,1871813.00000,1887084.00000,185283.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (7,2018,1726085.00000,2023166.00000,127204.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (8,2019,1092226.00000,928748.00000,80081.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (9,2020,1124839.00000,957702.00000,48304.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (10,2021,1323493.00000,1101886.00000,11506.00000);
/*
-- Query: SELECT * FROM giza.tesla
LIMIT 0, 100

-- Date: 2023-03-23 13:05
*/
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (1,2012,27557.00000,11531.00000,28825.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (2,2013,15710.00000,13356.00000,106083.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (3,2014,4208.00000,4005.00000,231931.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (4,2015,14477.00000,12287.00000,422590.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (5,2016,181394.00000,178332.00000,947099.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (6,2017,1116266.00000,874538.00000,1636003.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (7,2018,1555244.00000,1364896.00000,1901050.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (8,2019,1531000.00000,1341000.00000,2154000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (9,2020,1994000.00000,1976000.00000,2322000.00000);
INSERT INTO `` (`ID`,`YearofIssue`,`Revenue`,`COGS`,`Depreciation`) VALUES (10,2021,2789000.00000,2918000.00000,2911000.00000);