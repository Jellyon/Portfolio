use COMPANY;
/*Q2*/

	Select e.SSN, e.LNAME
	From EMPLOYEES e
	Where
		

		e.SEX = 'F' and
		e.SSN IN (select d.ESSN from DEPENDENT d where d.RELATIONSHIP = 'Spouse' )
	/*Gets you the female married employees^^*/

INTERSECT	

Select e.SSN, e.LNAME
	From EMPLOYEES e
	Where 
		e.SSN in (select w.ESSN from WORKS_ON w
				group by w.ESSN having count(w.ESSN) >3
	)
	/*Gets you the working on more than 3 projects*/