use COMPANY;
GO

alter procedure SP_Report_NEW_Budget as

declare @name as char(30)
declare @num as smallint
declare @emps as int
declare @sums as int
declare @aves as int
declare @x as int 

select @x = count(*)
from VDept_Budget;

if @x >0
begin
	drop table NEW_Dept_Budget;
	create table NEW_Dept_Budget(
		Dept_No int,
		Dept_Name char(30),
		COUNT_Emp int,
		New_SUM_Salary int,
		New_AVE_Salary int
	);

	declare emp_cursor cursor
		for select  
			q.Dept_Name, q.Dept_Number, q.No_Emp, q.Sum_Salary, q.Ave_Salary
		from VDept_Budget q;

	open emp_cursor;

	fetch next from emp_cursor into
		@name, @num, @emps, @sums, @aves

	while @@FETCH_STATUS = 0
		begin
			if cast(@num as int) > 0 and cast(@num as int) < 2
			begin
				set @sums = @sums*1.1
			end
			 
			if cast(@num as int) > 3 and cast(@num as int) < 5
			begin
				set @sums = @sums*1.2
			end
			if cast(@num as int) > 4 and cast(@num as int) < 6
			begin
				set @sums = @sums*1.3
			end
			if cast(@num as int) > 6 and cast(@num as int) < 8
			begin
				set @sums = @sums*1.4
			end

		insert into NEW_Dept_Budget values
				(@num, @name, @emps, @sums, @aves);
		fetch next from emp_cursor into
				@name, @num, @emps, @sums, @aves;
		end
		
		select * from NEW_Dept_Budget;
end
close emp_cursor;
deallocate emp_cursor;