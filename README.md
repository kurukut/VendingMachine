# VendingMachine
how to run the project
=======================
unzip VendingMachine.zip
go inside the folder VendingMachine
VendingMachine]$ ls
docker-compose.yml  Dockerfile  HELP.md  mvnw  mvnw.cmd  pom.xml  src  target

run the docker-compose.yml file using the command "docker-compose up"

This will start  2 containers
vendingmachine_vendingmachine_1 === this is the application container, running at port 9090
vendingmachine_mysqldb ==== mysql db container , running at 3306

to see the database use these commands
=======================================
docker exec -it vendingmachine_mysqldb mysql -usreevidya -p
password // this is the password to login
use vending_machine // this is the schema for the project
show tables // this will display the tables used in the project. Namely VendingMachineEntity,ProductEntity,CoinEntity. The project starts with some values populated into these tables. To populate more values use the POST commands listed at the bottom.


to run the GET and POST mappings use Postman or curl 
======================================================
sample GET request
http://localhost:9090//vendingmachine/alldetails/vendingMachineID/1



Source Code details
=====================
VendingMachineApplication: main class
com.entity= contains all @Entity classes

VendingMachineEntity represents VendingMachineEntity table
mysql> select * from VendingMachineEntity;
+------+
| vmId |
+------+
|    1 |
+------+
1 row in set (0.00 sec)

mysql> desc VendingMachineEntity;
+-------+------+------+-----+---------+-------+
| Field | Type | Null | Key | Default | Extra |
+-------+------+------+-----+---------+-------+
| vmId  | int  | NO   | PRI | NULL    |       |
+-------+------+------+-----+---------+-------+
1 row in set (0.00 sec)



CoinEntity
mysql> select * from CoinEntity;
+-----------+-----------+------+
| coinValue | coinCount | vmId |
+-----------+-----------+------+
|       0.5 |        20 |    1 |
|         1 |         5 |    1 |
|         2 |         5 |    1 |
|         5 |         9 |    1 |
|        10 |        12 |    1 |
+-----------+-----------+------+
5 rows in set (0.01 sec)

desc CoinEntity;
+-----------+-------+------+-----+---------+-------+
| Field     | Type  | Null | Key | Default | Extra |
+-----------+-------+------+-----+---------+-------+
| coinValue | float | NO   | PRI | NULL    |       |
| coinCount | int   | YES  |     | NULL    |       |
| vmId      | int   | NO   | PRI | NULL    |       |
+-----------+-------+------+-----+---------+-------+



ProductEntity

select * from ProductEntity;
+-----------+-------------+--------------+--------------+------+
| productId | productCost | productCount | productName  | vmId |
+-----------+-------------+--------------+--------------+------+
|         1 |          10 |            5 | kitkat       |    1 |
|         2 |           5 |            5 | greek yogurt |    1 |
+-----------+-------------+--------------+--------------+------+
2 rows in set (0.00 sec)

mysql> desc ProductEntity;
+--------------+--------------+------+-----+---------+-------+
| Field        | Type         | Null | Key | Default | Extra |
+--------------+--------------+------+-----+---------+-------+
| productId    | int          | NO   | PRI | NULL    |       |
| productCost  | float        | YES  |     | NULL    |       |
| productCount | int          | YES  |     | NULL    |       |
| productName  | varchar(255) | YES  |     | NULL    |       |
| vmId         | int          | NO   | PRI | NULL    |       |
+--------------+--------------+------+-----+---------+-------+



com.repository=contains all JPA repository classes for the entities
com.services=contains business logic
com.controller=rest controller


com.exceptions=exceptions are defined here
=================================
InsufficientAmountException
InsufficientProductQuantityException
NoChangeException
ProductNotFoundException

GET and POST mappings
===============

	@PostMapping("/vendingmachine/addVendingMachine/vendingMachineID/{vendingMachineID}")
    add a new vendingMachineId
	
	@PostMapping("/vendingmachine/addCoin/vendingMachineID/{vendingMachineID}/coinValue/{coinValue}/coinCount/{coinCount}")
    add new coins to a particular vendingMachineId
	
	@PostMapping("/vendingmachine/addProduct/vendingMachineID/{vendingMachineID}/productId/{productId}/productName/{productName}/productCount/{productCount}/productCost/{productCost}")
	add a new product to a particular vending machine

	@GetMapping("/vendingmachine/alldetails")
    get all the details of vending machine
    
	
	
	@GetMapping("/vendingmachine/alldetails/vendingMachineID/{vendingMachineID}")
    get all details of a particular vendingMachineId
	
	
	@GetMapping("/vendingmachine/productdetails")
    get all the product details

	
	@GetMapping("/vendingmachine/productdetails/vendingMachineId/{vendingMachineID}/productId/{productId}")
    get a particular product detail

	
	@GetMapping("/vendingmachine/coindetails")
    get all coin details
	
	
	
	@GetMapping("/vendingmachine/checkprice/vendingMachineID/{vendingMachineID}/product/{productId}/productCount/{productCount}")
    check the price
    sample request`
    http://localhost:9090//vendingmachine/checkprice/vendingMachineID/1/product/2/productCount/5
    check the price of 5 pieces of product 2 in vending machine1

	
	@PostMapping("/vendingmachine/confirmPayment/vendingMachineID/{vendingMachineID}/product/{productId}/productCount/{productCount}/userTotal/{userTotal}")
    make the payment
	userTotal is the total amount entered by user
    sample request
    http://localhost:9090/vendingmachine/confirmPayment/vendingMachineID/1/product/7/productCount/1/userTotal/2?1=2

    1=2 represents the map of denominations entered by user.
    In this case it means coin with denomination =1, 2 coins
	
	
	
	
	
	
	

