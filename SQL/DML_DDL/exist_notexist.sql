use COMPANY;
/*Q4*/

select e.LNAME 
from EMPLOYEES e
where
	EXISTS(Select d.ESSN from DEPENDENTS d 
			where e.SSN = d.ESSN and
					d.RELATIONSHIP = 'Spouse'
	) 
	/*This should get the married employees*/
	and
	EXISTS(Select d.ESSN from DEPENDENTS d 
			where e.SSN = d.ESSN and
				d.RELATIONSHIP = 'Daughter'
			)
	and
	NOT EXISTS(Select d.ESSN from DEPENDENTS d 
			where e.SSN = d.ESSN and
					d.RELATIONSHIP = 'Son'
	)
	/*I don't know why I didn't realize to do this sooner. I guess that's what you get late at night after working
	and going to school full time. Well, I guess some of those union/intersects propbably weren't necessary*/