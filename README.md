Database:

1.user  | CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) 

2. customer | CREATE TABLE `customer` (
  `cusId` int(11) NOT NULL,
  `cusName` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phoneNo` varchar(50) DEFAULT NULL,
  `Landline` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cusId`)
) 
3.customerAccount | CREATE TABLE `customerAccount` (
  `cusId` int(11) DEFAULT NULL,
  `accNo` int(11) NOT NULL,
  'currentAccId' int(11) FOREIGN KEY,
  PRIMARY KEY (`accNo`),
  KEY `cusId` (`cusId`),
  CONSTRAINT `customerAccount_ibfk_1` FOREIGN KEY (`cusId`) REFERENCES `customer` (`cusId`) on delete cascade
)

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

5.payment | CREATE TABLE `payment` (
  `payment` varchar(100) DEFAULT NULL,
  `accId` int(11) DEFAULT NULL,
  `paydate` varchar(50) DEFAULT NULL,
  KEY `accId` (`accId`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`accId`) REFERENCES `accounts` (`accId`) on delete cascade
)
PURCHASE:
1. purchaseLineItem | CREATE TABLE `purchaseLineItem` (
  `purchaseId` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  `purchaseLineId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`purchaseLineId`),
  KEY `purchaseId` (`purchaseId`),
  KEY `productId` (`productId`),
  CONSTRAINT `purchaseLineItem_ibfk_1` FOREIGN KEY (`purchaseId`) REFERENCES `purchaseMetaData` (`purchaseId`) ON DELETE CASCADE,
  CONSTRAINT `purchaseLineItem_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`) ON DELETE CASCADE
  
  2. purchaseMetaData | CREATE TABLE `purchaseMetaData` (
  `purchaseId` int(11) NOT NULL,
  `billDate` date DEFAULT NULL,
  `vat` float DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `billTotal` float DEFAULT NULL,
  PRIMARY KEY (`purchaseId`)
) 
3.purchasePayment | CREATE TABLE `purchasePayment` (
  `purchaseId` int(11) DEFAULT NULL,
  `payId` int(11) NOT NULL AUTO_INCREMENT,
  `payDate` date DEFAULT NULL,
  `paidAmount` float DEFAULT NULL,
  PRIMARY KEY (`payId`),
  KEY `purchaseId` (`purchaseId`),
  CONSTRAINT `purchasePayment_ibfk_1` FOREIGN KEY (`purchaseId`) REFERENCES `purchaseMetaData` (`purchaseId`) ON DELETE CASCADE
) 
