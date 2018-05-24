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

insert into t_product(name,price) values('�ʼǱ�����',5000.0);
insert into t_product(name,price) values('̨ʽ����',3000.0);
insert into t_product(name,price) values('ƻ������',9000.0);
insert into t_product(name,price) values('�����˵���',15000.0);
insert into t_product(name,price) values('�ֻ�',5000.0);
insert into t_product(name,price) values('ipad',3000.0);
insert into t_product(name,price) values('���¿���',3.0);
insert into t_product(name,price) values('�Ӷ౦',4.0);
insert into t_product(name,price) values('������',10000.0);
insert into t_product(name,price) values('���˻�',100000.0);


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



insert into t_user values(1,'admin','admin','14587965423','�Ͼ��ػ�','12345678@qq.com');










