USE COMPANY;
/*I'M CREATING A NEW TABLE because drop primary key wasn't working screen caps provided; I will inquire why in email*/

CREATE TABLE EMPLOYEES (
	FNAME varchar(20),
	MINIT char, 
	LNAME varchar(20) ,
	SSN int ,
	DOB varchar(9),
	ADDRESS varchar(50),
	SEX char,
	SALARY int,
	SUPERSSN int,
	DNO int 
);

/*I created a new table for this also, to keep inline with the lab, and having no primary keys*/

CREATE TABLE DEPARTMENTS(/*Creates a table named DEPARTMENTS with the following fields*/
	DNAME varchar(15),
	DNUMBER int,
	MGRSSN int,
	MGRSTARTDATE varchar(10)
);

/*The following Tuples added to employees per step 1: With 1 repeating SSN*/
INSERT INTO EMPLOYEES VALUES('Jeff','T','LYON',555649834,'20-May-92','123 Road, North Olmsted, OH','M',50000, 987654321, 5);

INSERT INTO EMPLOYEES VALUES ('Jimmy', 'E', 'Besoz', 999887777, '10-Nov-64', '450 Rock, Houston, TX', 'M', 55000, NULL, 1);
INSERT INTO EMPLOYEES VALUES('John','B','Smith',123456789,'9-Jan-55','731 Fondren, Houston, TX','M',30000,987654321, 5);

INSERT INTO EMPLOYEES VALUES ('James', 'E', 'Borg', 888665555, '10-Nov-27', '450 Stone, Houston, TX', 'M', 55000, NULL, 1);