SELECT CURDATE();

SELECT CURTIME();

SELECT SYSDATE();

SELECT NOW();

SELECT Country,COUNT(Medal) cnt
from summermedals
group by Medal, Country
HAVING Medal = 'Gold'
ORDER BY cnt desc
    limit 10;


-- 아이디, 비번, 이름, 이메일 --
CREATE table member(
                       memberNo bigint primary key auto_increment,
                       memberId varchar(50) not null,
                       password varchar(50) not null,
                       memberName varchar(50) not null,
                       email varchar(50),
                       regDate datetime default current_timestamp
);

-- 글번호, 제목, 작성자, 작성일, 조회수, 본문 --
CREATE table board(
                      boardNo bigint primary key auto_increment,
                      title varchar(100) not null,
                      content text not null,
                      regDate datetime default current_timestamp,
                      readcount int not null,
                      memberId varchar(50) not null
                          memberName varchar(50) NOT NULL);

CREATE procedure loop_insert(in cnt int)
begin
	declare i int default 4;
	while (i <= cnt) do
		INSERT into board (title,memberId,content,memberName) values (concat(i,'번 테스트 글'),'administrator',concat(i,'번째 테스트 글입니다.\n',i,'th Test Content.'),'관리자');
		set i = i + 1;
end while;
end;

DROP procedure loop_insert;

DELETE from board;

CALL loop_insert(900);

SELECT COUNT(board_no) cnt from board;

SELECT COUNT(board_no)/25 pages from board;


SELECT CEIL(COUNT(board_no)/25) pages from board;