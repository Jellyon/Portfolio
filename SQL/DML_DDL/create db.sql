/*
Name: Jeffery Lyon
ID: 2583700
Object: Creating a Relational Database Chema Using SQL and SQL Server
*/
CREATE DATABASE COMPANY; /*creates the database named COMPANY. I ran this first, before any other commands to clear the
error that COMPANY did not exist. By leaving it in the query, it will give the error that COMPANY already exists.
*/

Use COMPANY; /* accesses the database COMPANY so we can create tables in COMPANY and not inside master*/

CREATE TABLE EMPLOYEE(/* creates a table named EMPLOYEE with the data fields listed below
if a field says not null, the database system  will not allow a null value to be entered into that field
*/
	FNAME varchar(20) Not Null,
	MINIT char, 
	LNAME varchar(20) not null,
	SSN int not null,
	DOB varchar(9),
	ADDRESS varchar(50),
	SEX char,
	SALARY int,
	SUPERSSN int,
	DNO int not null

	primary key (SSN), /*I'm assuming this will allow for sorting or searching through the table using ssn*/

);

CREATE TABLE DEPARTMENT(/*Creates a table named DEPARTMENT with the following fields*/
	DNAME varchar(15) not null,
	DNUMBER int not null,
	MGRSSN int not null,
	MGRSTARTDATE varchar(10)

	primary key (DNUMBER), /*so we can sort/search by department number*/
);

/* 
The following codes actually puts data into the table we created named EMPLOYEE
*/
Insert into EMPLOYEE Values ('John', 'B', 'Smith', 123456789, '09-Jan-55', '731 Fondren, Houston, TX', 'M', 30000, 987654321, 5);
Insert into EMPLOYEE Values ('Franklin','T','Wong',333445555,'08-Dec-45','638 Voss, Houston, TX','M',40000,888665555,5);
Insert into EMPLOYEE Values ('Joyce','A','English', 453453453,'31-Jul-62','5631 Rice, Houston, TX','F',25000, 333445555,5);
Insert into EMPLOYEE Values ('Ramesh','K','Narayan', 666884444,'15-Sep-52','975 Fire Oak, Humble, TX','M',38000 , 333445555,5);
Insert into EMPLOYEE Values ('James','E','Borg', 888665555,'10-Nov-27','450 Stone, Houston, TX ','M', 55000, NULL, 1);
Insert into EMPLOYEE Values ('Jennifer','S','Wallace', 987654321,'20-Jun-31','291 Berry, Bellaire, TX ','F', 43000, 888665555,4);
Insert into EMPLOYEE Values ('Ahmad','V','Jabbar', 987987987,'29-Mar-59','980 Dallas, Houston, TX ','M', 25000,987654321 ,4);
Insert into EMPLOYEE Values ('Alicia','J','Zelaya', 999887777,'19-Jul-58','3321 Castle, SPring, TX ','F', 25000, 987654321,4);


/*
The following will populate the table DEPARTMENT with the following info
*/
Insert into DEPARTMENT Values('Headquarters', 1, 888665555, '19-Jun-71');
Insert into DEPARTMENT Values('Administration', 4, 987654321, '01-Jan-85');
Insert into DEPARTMENT Values('Research', 5, 333445555, '22-May-78');
Insert into DEPARTMENT Values('Automation', 7, 123456789, '06-Oct-05');

/*
So now that we have some data inserted into the tables we created, we can now access them, and display them
*/

Select * from EMPLOYEE;
Select * from DEPARTMENT;
