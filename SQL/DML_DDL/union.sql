/*part 2*/
use COMPANY;
/*Q1*/
select  
	d.DNUMBER, e.FNAME, e.LNAME, s.FNAME, s.LNAME
from
	DEPARTMENTS	d, EMPLOYEES e, EMPLOYEES s
where
	e.DNO = d.DNUMBER AND s.SSN = e.SUPERSSN 
	/*gets the standard case - department has an employee and employee has a supervisor*/
UNION
select  
	d.DNUMBER, '', '', s.FNAME, s.LNAME
from
	DEPARTMENTS d, EMPLOYEES s
where 
	D.DNUMBER NOT IN (select E.DNO from EMPLOYEES E)
	and
	d.MGRSSN = s.SSN
	/*gets the department without any supervisor, for these I included the department manager info*/
UNION
select
	d.DNUMBER, e.FNAME, e.LNAME, '', ''
from
	DEPARTMENTS	d, EMPLOYEES e
where
	d.DNUMBER = e.DNO and e.SUPERSSN is null
	/*Gets the employees who do not have a supervisor*/
order by DNUMBER;

/*Q1-1*/
select  
	d.DNUMBER, e.FNAME, e.LNAME, s.FNAME, s.LNAME
from
	DEPARTMENTS	d, EMPLOYEES e, EMPLOYEES s
where
	e.DNO = d.DNUMBER AND s.SSN = e.SUPERSSN  
union
select
	d.DNUMBER, e.FNAME, e.LNAME, '', ''
from
	DEPARTMENTS	d, EMPLOYEES e
where
	d.DNUMBER = e.DNO and e.SUPERSSN is null
order by DNUMBER;

/*I honestly figured this part out first and just union'd it with other queries. I feel like there is probably an
easier way to do Q1*/