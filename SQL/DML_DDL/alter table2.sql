Use COMPANY;
/*Figured out works_on is its own table*/
Create table WORKS_ON(
	ESSN int,
	Pno int,
	Hours float
);

INSERT INTO WORKS_ON VALUES (123456789, 1, 32.5);
/*STEP 3 - ADDING CONSTRAINTS*/


Alter table EMPLOYEES/*primary key for the employee table*/
	Add Primary Key (SSN);
Alter table EMPLOYEES/*key for identifying supervisor relationship*/
	Add Foreign Key (SUPER_SSN) References EMPLOYEES(SSN);
Alter table EMPLOYEES
	Add Foreign Key (DNO) references DEPARTMENTS(DNUMBER);

Select * From EMPLOYEES;

INSERT INTO DEPENDENTS VALUES (123456789, 'Alice', 'F', '31-Dec-78', 'Daughter');
INSERT INTO PROJECTS VALUES ('ProductX', 1, 'Bellaire', 5);
Alter table DEPARTMENTS /*PK for department is the dnumber*/
	Add Primary Key (DNUMBER);

/*Sets up relationship for an employee to be the manager of a dept*/
Alter table DEPARTMENTS ADD Constraint FKMgrSsnPKSsn  
	Foreign Key (MGRSSN) References EMPLOYEES(SSN);

/*Each project should a unique project number*/
Alter table PROJECTS 
	Add Primary Key(Pnumber);

/*relationship for projects to belong to a department*/
Alter table PROJECTS ADD 
	Foreign Key (Dnum) References DEPARTMENTS(DNUMBER);
/*Relates the project number to works_on project numbers so employees can get related to a project*/	
Alter table PROJECTS	
	Add Foreign Key (Pnumber) references WORKS_ON (Pno);

/*Creates the relationship for employees working on a project number*/
Alter table WORKS_ON ADD Constraint FKessnPKssn
	Foreign Key (ESSN) references EMPLOYEES(SSN);

Alter table DEPENDENTS ADD Constraint FKdssnPKssn
	Foreign Key (ESSN) references EMPLOYEES(SSN);

Alter table DEPT_LOCATIONS
	add foreign key (Dnumber) references DEPARTMENTS (DNUMBER);