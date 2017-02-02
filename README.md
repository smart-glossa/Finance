
<h1>MySQL Database:finance</h1>

<p>
1 CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `mobileNumber` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userName` (`userName`)
) 
</p>
<p>
2. CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `mobileNumber` varchar(12) DEFAULT NULL,
  `referralName` varchar(100) DEFAULT NULL,
  `referralAddress` varchar(255) DEFAULT NULL,
  `referralContactNo` varchar(12) DEFAULT NULL,
  `landLine` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 |

</p>
<p>
3.CREATE TABLE `customerAccount` (
  `accountNumber` int(11) NOT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountNumber`),
  KEY `customerId` (`customerId`),
  CONSTRAINT `customerAccount_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE

)
</p>
<p>
4.CREATE TABLE `accounts` (
  `accId` int(11) NOT NULL AUTO_INCREMENT,
  `line` varchar(20) DEFAULT NULL,
  `duration` varchar(50) DEFAULT NULL,
  `amountGiven` float DEFAULT NULL,
  `amountToPay` float DEFAULT NULL,
  `givenDate` varchar(100) DEFAULT NULL,
  `accountNumber` int(11) DEFAULT NULL,
  `modeOfPayment` varchar(50) DEFAULT NULL,
  `currentAccount` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`accId`),
  KEY `accountNumber` (`accountNumber`),
  CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`accountNumber`) REFERENCES `customerAccount` (`accountNumber`) ON DELETE CASCADE
 )
</p>
<p>
5. CREATE TABLE `payment` (
  `amount` float DEFAULT NULL,
  `collectionDate` varchar(100) DEFAULT NULL,
  `EntryDate` varchar(100) DEFAULT NULL,
  `accountId` int(11) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  KEY `accountId` (`accountId`),
  KEY `userName` (`userName`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `accounts` (`accId`) ON DELETE CASCADE,
  CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`userName`) REFERENCES `user` (`userName`) ON DELETE CASCADE

)
</p>
