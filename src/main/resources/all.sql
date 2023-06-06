#sql("sortBylittle")
select * from goods where name like '%?%'  order by price;
#end

#sql("sortBybig")
select * from goods where name like '%?%' order by price desc;
#end

#sql("find_goods_admin")
select goods.id as id,goods.name as name,img,info,type,price,user from
goods inner join admins on goods.ownerId = admins.id where goods.id = ?;
#end

#sql("goodIdByCart")
select id,name ,img,info,type,price from goods inner join (select goodId from cart where userId = ?) as carts on id = goodId ;
#end

#sql("upsetGood")
update goods set name = ?,img = ?,type = ?,info = ?,price = ? where id = ?;
#end

#sql("delectCart")
delete from cart where goodId = ? and userId = ?;
#end

#sql("findtUser")
select * from users where name = ? and password =?;
#end

#sql("upsetUser")
update users set name=?,user=?,password = ?,address = ? where name = ? and password = ?;
#end

#sql("find_good")
select * from goods where name like concat('%', #para(goodName), '%') limit #para(page),#para(limit);
#end

#sql("good_count")
select * from goods where name like concat('%', ?, '%');
#end

#sql("del_good")
delete from goods where id = ?;
#end

#sql("del_cart")
delete from cart where goodId = ?;
#end

#sql("sear_shopping-user")
select users.name as userName,user,shopping.name,img,info,type,price,time
from shopping inner join users on users.id = shopping.userId where ownerId = ? limit ?,?;
#end

#sql("sear_shopping-userByUser")
select users.name as userName,user,shopping.name,img,info,type,price,time
from shopping inner join users on users.id = shopping.userId where ownerId = #para(ownerId) and user like concat('%', #para(findName), '%') limit #para(page),#para(limit);
#end

#sql("sear_shopping-userByUserName")
select users.name as userName,user,shopping.name,img,info,type,price,time
from shopping inner join users on users.id = shopping.userId where ownerId = 2 and users.name like concat('%', #para(findName), '%') limit #para(page),#para(limit);
#end

#sql("sear_shopping-userByShoppingName")
select users.name as userName,user,shopping.name,img,info,type,price,time
from shopping inner join users on users.id = shopping.userId where ownerId = 2 and shopping.name like concat('%', #para(findName), '%') limit #para(page),#para(limit);
#end

#sql("sear_shopping-userByType")
select users.name as userName,user,shopping.name,img,info,type,price,time
from shopping inner join users on users.id = shopping.userId where ownerId = 2 and type like concat('%', #para(findName), '%') limit #para(page),#para(limit);
#end

#sql("upsetAdmin")
update admins set user=?,password = ?,address = ?,phone = ? where id = ? and password = ?;
#end

#sql("show_orders")
select type as name,count(id) as value from shopping where ownerId = ? group by type;
#end

#sql("show_goods")
select type as name,count(id) as value from goods group by type;
#end


