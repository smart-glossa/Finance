
<h1>MySQL Database:</h1>

<p>
1.user  | CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) 
</p>
<p>
2. customer | CREATE TABLE `customer` (
  `cusId` int(11) NOT NULL,
  `cusName` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phoneNo` varchar(50) DEFAULT NULL,
  `Landline` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cusId`)
) 
</p>
<p>
3.customerAccount | CREATE TABLE `customerAccount` (
  `cusId` int(11) DEFAULT NULL,
  `accNo` int(11) NOT NULL,
  'currentAccId' int(11) FOREIGN KEY,
  PRIMARY KEY (`accNo`),
  KEY `cusId` (`cusId`),
  CONSTRAINT `customerAccount_ibfk_1` FOREIGN KEY (`cusId`) REFERENCES `customer` (`cusId`) on delete cascade
)
</p>
<p>
4.accounts | CREATE TABLE `accounts` (
  `accId` int(11) NOT NULL AUTO_INCREMENT,
  `accNo` int(11) DEFAULT NULL,
  `line` varchar(50) DEFAULT NULL,
  `duration` varchar(50) DEFAULT NULL,
  `modeOfPayment` varchar(50) DEFAULT NULL,
  `amountGiven` varchar(50) DEFAULT NULL,
  `amountToPay` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`accId`),
  KEY `accNo` (`accNo`),
  CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`accNo`) REFERENCES `customerAccount` (`accNo`) on delete cascade
 )
</p>
<p>
5.payment | CREATE TABLE `payment` (
  `payment` varchar(100) DEFAULT NULL,
  `accId` int(11) DEFAULT NULL,
  `paydate` varchar(50) DEFAULT NULL,
  KEY `accId` (`accId`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`accId`) REFERENCES `accounts` (`accId`) on delete cascade
)
</p>
