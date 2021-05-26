grant all privileges on spring5.*
to 'user_spring5'@'loacalhost' identified by 'rootroot';

select * from `member`;
delete from `member` where id = 39;

select * from `member`;

delete from `member` where id = 48;

select id,email,password,name,regdate 
	from member 
where regdate between '2021-05-17' and '2021-05-25'
order by regdate desc;
