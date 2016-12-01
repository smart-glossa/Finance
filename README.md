Database:

1.customer :CREATE TABLE `customer` (
  `cusId` int(11) NOT NULL auto_increment,
  `cusName` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `contactNo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cusId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 |

2.Accounts :CREATE TABLE `Accounts` (
  `accId` int(11) NOT NULL auto_increment,
  `cusId` int(11) DEFAULT NULL,
  `line` varchar(50) DEFAULT NULL,
  `amountGiven` varchar(50) DEFAULT NULL,
  `amountToPay` varchar(50) DEFAULT NULL,
  `collType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`accId`),
  KEY `cusId` (`cusId`),
  CONSTRAINT `Accounts_ibfk_1` FOREIGN KEY (`cusId`) REFERENCES `customer` (`cusId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 |

3.Payment :CREATE TABLE `Payment` (
  `payId` int(11) NOT NULL auto_increment,
  `accId` int(11) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`payId`),
  KEY `accId` (`accId`),
  CONSTRAINT `Payment_ibfk_1` FOREIGN KEY (`accId`) REFERENCES `Accounts` (`accId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 |
