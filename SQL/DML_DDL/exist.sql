use COMPANY;
/*Q3*/

select e.FNAME, e.LNAME
from EMPLOYEES e
where
	EXISTS(Select d.ESSN from DEPENDENTS d 
			where e.SSN = d.ESSN and
					d.RELATIONSHIP = 'Spouse'
	) 
	and e.DNO = 5
	/*This should get research dept employees who are married*/
INTERSECT
select e.FNAME, e.LNAME
from EMPLOYEES e
where
	NOT EXISTS(Select d.ESSN from DEPENDENTS d 
			where e.SSN = d.ESSN and
					d.RELATIONSHIP = 'Daughter'
	) 
	and e.DNO = 5
	/*This should get research dept employees who have no daughter*/
INTERSECT
select e.FNAME, e.LNAME
from EMPLOYEES e
where
	NOT EXISTS(Select d.ESSN from DEPENDENTS d 
			where e.SSN = d.ESSN and
					d.RELATIONSHIP = 'Son'
	) 
	and e.DNO = 5
	/*This should get research dept employees who have no son. They should all combine into those who
	work for research, are married, and have no kids. This is my best guess anyway. I'm assuming there is
	a better way, with left join or something? I don't fully understand the different join's*/
