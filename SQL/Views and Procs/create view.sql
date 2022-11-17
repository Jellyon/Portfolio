use COMPANY;
go

CREATE VIEW VDept_Budget (Dept_Name, Dept_Number, No_Emp) as
	Select D.DNAME, D.DNUMBER, count(E.SSN) 
	From DEPARTMENTS D left outer join EMPLOYEES E on E.DNO = D.DNUMBER
	group by D.DNAME, D.DNUMBER;
go
Select * from VDept_Budget;