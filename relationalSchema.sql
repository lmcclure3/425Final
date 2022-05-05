Create table manufacturer(
    manufacturerID int,
    companyName varchar(64),
    primary key (manufacturerID)
);

Create table products(
    productID int,
    price float,
    category int,
    manufacturerID int,
    storeID int, 
    foreign key (manufacturerID) int references manufacturer,
    foreign key (storeID) int references store,
    primary key (productID)
);

Create table stores(
    storeID int,
    productID int,
    quantity int,
    quantityOrdered int,
    foreign key (productID) int references products,
    primary key (storeID)
);

Create table wharehouse(
    wharehouseID int,
    productID int,
    quantity int,
    quantityOrdered int,
    primary key (wharehouseID)
);
Create table billing(
    billingID int,
    cardNumber int,
    monthlyCharge float,
    saleID int,
    customerID int,
    foreign key (saleID) int references sales,
    primary key (billingID)
);
Create table sales(
    saleID int,
    productID int,
    quantity int,
    saleDate date,
    storeID int,
    customerID int,
    shipperID int,
    foreign key (saleID) int references sales,
    foreign key (customerID) int references customer,
    foreign key (shipperID) int references shipper,
    primary key (productID)
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
    primary key (customerID),
);
Create table buying(
    ProductID int,
    SaleID int,
    foreign key (ProductID) int references Product,
    foreign key (SaleID) int references Sale,
    primary key (ProductID , SaleID)
);
Create table order (
    wharehouseID int,
    shipperID int,
    foreign key (wharehouseID) int references wharehouse,
    foreign key (shipperID) int references shipper,
    primary key (wharehouseID , shipperID)
);
Create table shipper(
    shipperID int,
    shipperStatus varchar(64),
    trackingNumber int,
    primary key (shipperID)
); 
