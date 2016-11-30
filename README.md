Database:

DATABASE NAME:finance

CREATE TABLE `customer` (
  `accountNo` int(11) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(50) DEFAULT NULL,
  `line` varchar(50) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `contactNo` varchar(50) DEFAULT NULL,
  `collectionType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`accountNo`)
) ENGINE=InnoDB AUTO_INCREMENT=10012 DEFAULT CHARSET=latin1 |
