use COMPANY;
/*filling out the rest of the employee table while we're at it*/

Insert into EMPLOYEES Values ('Franklin','T','Wong',333445555,'08-Dec-45','638 Voss, Houston, TX','M',40000,888665555,5);
Insert into EMPLOYEES Values ('Joyce','A','English', 453453453,'31-Jul-62','5631 Rice, Houston, TX','F',25000, 333445555,5);
Insert into EMPLOYEES Values ('Ramesh','K','Narayan', 666884444,'15-Sep-52','975 Fire Oak, Humble, TX','M',38000 , 333445555,5);
Insert into EMPLOYEES Values ('Jennifer','S','Wallace', 987654321,'20-Jun-31','291 Berry, Bellaire, TX ','F', 43000, 888665555,4);
Insert into EMPLOYEES Values ('Ahmad','V','Jabbar', 987987987,'29-Mar-59','980 Dallas, Houston, TX ','M', 25000,987654321 ,4);
Insert into EMPLOYEES Values ('Alicia','J','Zelaya', 999887777,'19-Jul-58','3321 Castle, SPring, TX ','F', 25000, 987654321,4);

/*STEP 2-1: create the schema
an employee manages a dept
employees work in a dept
employees have a supervisor?
employee working on projects
dependants to an employee
departments control projects
*/
/* need to create a dept location table to store location of the dept. as I don't think the city gets derived from employee table*/
CREATE Table DEPT_LOCATIONS(
	Dnumber int,
	Dlocation varchar (25)
)
/*Creating projects table, as we need to maintain a list of projects*/
CREATE Table PROJECTS(
	Pname varchar(25),
	Pnumber int,
	Plocation varchar (25),
	Dnum int
)
/*Creating a table for dependents*/
CREATE Table DEPENDENTS(
	ESSN int,
	Dependent_Name varchar (20),
	Sex char,
	Bdate varchar(9),
	Relationship varchar(15)
)

/*I'm confused about the works on table with the hours column, are we supposed to create WORKS_ON as a table or derive it?
	where do the Hours data come from?
*/

