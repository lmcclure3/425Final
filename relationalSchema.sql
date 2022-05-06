Create table manufacturer(
    manufacturerID varchar(64),
    companyName varchar(64),
    primary key (manufacturerID)
    
);
Create table customers (
    customerID varchar(64),
    customerPassword varchar (64),
    firstName varchar(64),
    phone varchar(11),
    sex varchar (6),
    birthdate date,
    adressNum int,
    city varchar(64),
    street varchar(64),
    adressState char(2),
    zipCode int,
    primary key (customerID)
);

Create table wharehouse(
    wharehouseID int,
    productID varchar(64),
    quantity int,
    quantityOrdered int,
    primary key (wharehouseID)
);
Create table shipper(
    shipperID int,
    shipperStatus varchar(64),
    trackingNumber int,
    primary key (shipperID)
);


Create table stores(
    storeID int,
    productID varchar(64),
    quantity int,
    quantityOrdered int,
    primary key (storeID)
);

Create table products(
    productID varchar(64),
    price float,
    productCategory varchar(64),
    wharehouseID int,
    manufacturerID varchar(64),
    storeID int, 
    foreign key (manufacturerID) references manufacturer,
    foreign key (storeID)  references stores,
    foreign key (wharehouseID) references wharehouse,
    primary key (productID)
) ;

Create table sales(
    saleID int,
    productID varchar(64),
    quantity int,
    saleDate date,
    storeID int,
    customerID varchar(64),
    shipperID int,
    foreign key (customerID)  references customers,
    foreign key (shipperID)  references shipper,
    primary key (saleID)
);
Create table billing(
    billingID int,
    cardNumber int,
    monthlyCharge float,
    saleID int,
    customerID varchar(64),
    foreign key (saleID) references sales,
    foreign key (customerID) references customers,
    primary key (billingID)
);

Create table buying(
    ProductID varchar(64),
    SaleID int,
    foreign key (ProductID) references Products,
    foreign key (SaleID)  references Sales,
    primary key (ProductID , SaleID)
);

Create table orders (
    wharehouseID int,
    shipperID int,
    foreign key (wharehouseID)  references wharehouse,
    foreign key (shipperID)  references shipper,
    primary key (wharehouseID , shipperID)
);



drop table orders;
drop table buying;
drop table billing;
drop table products;
drop table stores;  
drop table sales;
drop table shipper;
drop table wharehouse;
drop table customers;
drop table manufacturer ; 


INSERT INTO "HR"."MANUFACTURER" (MANUFACTURERID , COMPANYNAME) VALUES ('apple','Apple Computers Inc.');
INSERT INTO "HR"."MANUFACTURER" (MANUFACTURERID , COMPANYNAME) VALUES ('samsung','samsung entertainment lmt.');
INSERT INTO "HR"."MANUFACTURER" (MANUFACTURERID , COMPANYNAME) VALUES ('logitec','logitec products company');

INSERT INTO "HR"."STORES" (STOREID, PRODUCTID, QUANTITY,QUANTITYORDERED) VALUES ('1' , 'Gaming Chair', 10 ,0 );
INSERT INTO "HR"."STORES" (STOREID, PRODUCTID, QUANTITY,QUANTITYORDERED) VALUES ('2' , 'Microphone', 60 ,0 );
INSERT INTO "HR"."STORES" (STOREID, PRODUCTID, QUANTITY,QUANTITYORDERED) VALUES ('3' , 'Gaming Mouse', 100 ,0 );
INSERT INTO "HR"."STORES" (STOREID, PRODUCTID, QUANTITY,QUANTITYORDERED) VALUES ('4' , 'League of Legends Keyboard', 20 ,0 );
INSERT INTO "HR"."STORES" (STOREID, PRODUCTID, QUANTITY,QUANTITYORDERED) VALUES ('5' , 'Headset', 42 ,0 );
INSERT INTO "HR"."STORES" (STOREID, PRODUCTID, QUANTITY,QUANTITYORDERED) VALUES ('6' , 'Printer', 5 ,0 );



INSERT INTO "HR"."PRODUCTS" (PRODUCTID, PRICE, PRODUCTCATEGORY, WHAREHOUSEID, MANUFACTURERID, STOREID) VALUES ('Gaming Chair', 350, 'chair', NULL, 'apple', 1);
INSERT INTO "HR"."PRODUCTS" (PRODUCTID, PRICE, PRODUCTCATEGORY, WHAREHOUSEID, MANUFACTURERID, STOREID) VALUES ('Microphone', 99.99, 'microphone', NULL, 'samsung', 2);
INSERT INTO "HR"."PRODUCTS" (PRODUCTID, PRICE, PRODUCTCATEGORY, WHAREHOUSEID, MANUFACTURERID, STOREID) VALUES ('Gaming Mouse', 25.59, 'keybaord', NULL, 'apple', 3);
INSERT INTO "HR"."PRODUCTS" (PRODUCTID, PRICE, PRODUCTCATEGORY, WHAREHOUSEID, MANUFACTURERID, STOREID) VALUES ('League of Legends Keyboard', 444.00, 'keybaord', NULL, 'apple', 4);
INSERT INTO "HR"."PRODUCTS" (PRODUCTID, PRICE, PRODUCTCATEGORY, WHAREHOUSEID, MANUFACTURERID, STOREID) VALUES ('Headset', 70.01, 'headset', NULL, 'apple', 5);
INSERT INTO "HR"."PRODUCTS" (PRODUCTID, PRICE, PRODUCTCATEGORY, WHAREHOUSEID, MANUFACTURERID, STOREID) VALUES ('Printer', 500 , 'printer', NULL, 'logitec', 6);

INSERT INTO "HR"."SALES" (SALEID, PRODUCTID, QUANTITY, STOREID, CUSTOMERID, SHIPPERID) VALUES ('1', 'Headset', 1, 5, NULL, NULL);


