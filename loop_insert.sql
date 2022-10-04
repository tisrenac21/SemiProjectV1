CREATE procedure loop_insert(in cnt int)
begin
	declare i int default 1;
	while (i <= cnt) do
		INSERT into board (title,member_id,content) values ('테스트x','administrator','테스트 x번째 글입니다.');
		INSERT into board (title,member_id,content) values ('테스트xx','administrator','테스트 xx번째 글입니다.');
		INSERT into board (title,member_id,content) values ('테스트xxx','administrator','테스트 xxx번째 글입니다.');
		set i = i + 1;
	end while;
end;

DELETE from board;

CALL loop_insert(400);