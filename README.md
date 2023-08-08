## sec_c_sec_c_naresh.vellingiri__corejava_project_2

### Share To Rise

**Fundraiser table**

```sql
CREATE TABLE `fundraiser` (
  `personID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `FundingGoal` double DEFAULT NULL,
  `FundEndingDate` date DEFAULT NULL,
  `NoOfDaysRequired` int DEFAULT NULL,
  PRIMARY KEY (`personID`)
);
