use mldn;
drop table goods_item;
drop table goods;
drop table goods_title;
create table goods_item(
	iid		int 	auto_increment,
	title   varchar(50),
	constraint pk_iid primary key(iid)
)engine innodb;
create table goods(
	gid		int 	auto_increment,
	iid     int,
	title   varchar(50),
	price   double,
	photo   varchar(200),
	constraint pk_gid primary key(gid),
	constraint fk_iid foreign key(iid) references goods_item(iid)
)engine innodb;
create table goods_title(
	tid		int 	auto_increment,
	title   varchar(50),
	constraint pk_tid primary key(tid)
)engine innodb;
create table goods_goods_title(
	tid		int 	,
	gid		int 	,
	constraint fk_gid foreign key(gid) references goods(gid),
	constraint fk_tid foreign key(tid) references goods_title(tid)
)engine innodb;

insert into goods_item(title) values('影音');
insert into goods_item(title) values('家具');
insert into goods_item(title) values('厨房');
insert into goods_item(title) values('电子');
insert into goods_item(title) values('办公');


insert into goods_title(title) values('高端');
insert into goods_title(title) values('中端');
insert into goods_title(title) values('低端');