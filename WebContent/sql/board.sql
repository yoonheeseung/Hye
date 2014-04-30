CREATE TABLE HYBOARD(
	BOARD_NUM INT PRIMARY KEY,
	BOARD_NAME VARCHAR2(20),
	BOARD_PASS VARCHAR2(15),
	BOARD_SUBJECT VARCHAR2(50),
	BOARD_CONTENT VARCHAR2(2000),
	BOARD_FILE VARCHAR2(50),
	BOARD_RE_REF INT,
	BOARD_RE_LEV INT,
	BOARD_RE_SEQ INT,
	BOARD_READCOUNT INT,
	BOARD_DATE DATE
);

create sequence HYBOARD_NO_SEQ
increment by 1 start with 1 nocache;

SELECT * FROM USER_OBJECTS WHERE OBJECT_TYPE='SEQUENCE';

CREATE TABLE HYMEMBER(
	MEMBER_ID	VARCHAR2(15),
	MEMBER_PW	VARCHAR2(13),
	MEMBER_NAME	VARCHAR2(15),
	MEMBER_AGE	INT,
	MEMBER_GENDER	VARCHAR2(5),
	MEMBER_EMAIL	VARCHAR2(30),
	PRIMARY KEY(MEMBER_ID)
);
CREATE TABLE HYMEMBERBOARD(
	BOARD_NUM	INT PRIMARY KEY,
	BOARD_ID	VARCHAR2(20),
	BOARD_SUBJECT	VARCHAR2(50),
	BOARD_CONTENT	VARCHAR2(2000),
	BOARD_FILE	VARCHAR2(50),
	BOARD_RE_REF	INT,
	BOARD_RE_LEV	INT,
	BOARD_RE_SEQ	INT,
	BOARD_READCOUNT	INT,
	BOARD_DATE	DATE
);

select * from hymember;
select * from HYMEMBERBOARD

select * from (select rownum rnum,Board_num,board_name,board_subject,board_content,board_file,board_re_ref,board_re_lev,board_re_seq,board_readcount,board_date
		 from (select * from HYBOARD order by board_re_ref desc, board_re_seq asc))
		where rnum>=1 and rnum<=5;




select * from (select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE 
         from (select * from board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc)) 
         where rnum>=1 and rnum<=5;


select count(*) from hyboard;


select * from gongji7 order by gongji_no desc

select * from 
(select rownum rnum,gongji_no,gongji_name,gongji_title,gongji_hit,gongji_date
from(select * from gongji7 order by gongji_no desc)) 
where rnum>? and rnum<=?"