use COMPANY;

Select * from VDept_Budget;
GO

Alter View VDept_Budget(Dept_Name, Dept_Number, No_Emp, Sum_Salary, Ave_Salary) as
	Select D.DNAME, D.DNUMBER, count(E.SSN), sum(E.SALARY), avg(E.SALARY) 
	From DEPARTMENTS D left outer join EMPLOYEES E on E.DNO = D.DNUMBER
	group by D.DNAME, D.DNUMBER;
GO

Select * from VDept_Budget;
