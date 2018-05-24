create table t_user(
	id int primary key auto_increment,
	username varchar(20),
	password varchar(50),
	phone varchar(20),
	address varchar(20),
	email varchar(20)
)engine=Innodb default charset=utf8;

create table t_product(
	id int primary key auto_increment,
	name varchar(20),
	price double
)engine=Innodb default charset=utf8;

insert into t_product(name,price) values('笔记本电脑',5000.0);
insert into t_product(name,price) values('台式电脑',3000.0);
insert into t_product(name,price) values('苹果电脑',9000.0);
insert into t_product(name,price) values('外星人电脑',15000.0);
insert into t_product(name,price) values('手机',5000.0);
insert into t_product(name,price) values('ipad',3000.0);
insert into t_product(name,price) values('百事可乐',3.0);
insert into t_product(name,price) values('加多宝',4.0);
insert into t_product(name,price) values('机器人',10000.0);
insert into t_product(name,price) values('无人机',100000.0);


create table t_order(
	id int primary key auto_increment,
	user_id int,
	num int,
	price double,
	constraint FK_t_user foreign key(user_id) references t_user(id)
)engine=Innodb default charset=utf8;

alter table t_order change num no varchar(30);

create table t_item(
	id int primary key auto_increment,
	product_id int,
	num int,
	order_id int,
	price double,
	constraint FK_t_product foreign key(product_id) references t_product(id),
	constraint FK_t_order foreign key(order_id) references t_order(id)
)engine=Innodb default charset=utf8;




alter table t_order add foreign key(user_id) references t_user(id);



insert into t_user values(1,'admin','admin','14587965423','南京秦淮','12345678@qq.com');










