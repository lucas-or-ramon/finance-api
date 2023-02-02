db.createCollection("revenue");
db.getCollection("revenue").insertMany([
    {"description": "Salário", "date": new Date(), "value": 2000},
    {"description": "Mesada", "date": new Date(), "value": 50},
    {"description": "Freela", "date": new Date(), "value": 1000},
    {"description": "Cashback", "date": new Date(), "value": 80},
]);
db.createCollection("expenditure");
db.getCollection("expenditure").insertMany([
    {"description": "Aluguel", "date": new Date(), "value": 1000},
    {"description": "Energia", "date": new Date(), "value": 250},
    {"description": "Água", "date": new Date(), "value": 100},
    {"description": "Internet", "date": new Date(), "value": 220},
]);