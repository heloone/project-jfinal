#sql("sortBylittle")
select * from goods where name like '%?%'  order by price;
#end

#sql("sortBybig")
select * from goods where name like '%?%' order by price desc;
#end

#sql("goodIdByCart")
select goodId from cart where userId = ?;
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

