#!/usr/bin/env bash
curl -X POST http://localhost:8080/init/addUser  -d "money=100000"
curl -X POST http://localhost:8080/init/addUser  -d "money=100000"
curl -X POST http://localhost:8080/init/addUser  -d "money=100000"
curl -X POST http://localhost:8080/init/addUser  -d "money=100000"
curl -X POST http://localhost:8080/init/addUser  -d "money=100000"
curl -X POST http://localhost:8080/init/addUser  -d "money=100000"
curl -X POST http://localhost:8080/init/addUser  -d "money=100000"
curl -X POST http://localhost:8080/init/addUser  -d "money=100000"
curl -X POST http://localhost:8080/init/addUser  -d "money=100000"
curl -X POST http://localhost:8080/init/addUser  -d "money=100000"
curl -X POST http://localhost:8080/init/addUser  -d "money=0"
curl -X POST http://localhost:8080/init/addUser  -d "money=0"
curl -X POST http://localhost:8080/init/addUser  -d "money=0"
curl -X POST http://localhost:8080/init/addUser  -d "money=0"
curl -X POST http://localhost:8080/init/addUser  -d "money=0"
curl -X POST http://localhost:8080/init/addUser  -d "money=0"
curl -X POST http://localhost:8080/init/addUser  -d "money=0"
curl -X POST http://localhost:8080/init/addUser  -d "money=0"
curl -X POST http://localhost:8080/init/addUser  -d "money=0"
curl -X POST http://localhost:8080/init/addUser  -d "money=0"
curl -X POST http://localhost:8080/init/addBond  -d "quantity=100&userId=10"
curl -X POST http://localhost:8080/init/addBond  -d "quantity=100&userId=11"
curl -X POST http://localhost:8080/init/addBond  -d "quantity=100&userId=12"
curl -X POST http://localhost:8080/init/addBond  -d "quantity=100&userId=13"
curl -X POST http://localhost:8080/init/addBond  -d "quantity=100&userId=14"
curl -X POST http://localhost:8080/init/addBond  -d "quantity=100&userId=15"
curl -X POST http://localhost:8080/init/addBond  -d "quantity=100&userId=16"
curl -X POST http://localhost:8080/init/addBond  -d "quantity=100&userId=17"
curl -X POST http://localhost:8080/init/addBond  -d "quantity=100&userId=18"
curl -X POST http://localhost:8080/init/addBond  -d "quantity=100&userId=19"
read -n 1 -p "初始化完成... 正确性验证开始 bond0"
c
curl --request POST --url http://localhost:8080/transaction/buy  --header 'Content-Type: application/json' --data '{"userId": 0, "bondId": 0,"quantity": 10,"price": 40}'
curl --request POST --url http://localhost:8080/transaction/buy  --header 'Content-Type: application/json' --data '{"userId": 1, "bondId": 0,"quantity": 10,"price": 50}'
curl --request POST --url http://localhost:8080/transaction/buy  --header 'Content-Type: application/json' --data '{"userId": 2, "bondId": 0,"quantity": 10,"price": 10}'
curl --request POST --url http://localhost:8080/transaction/buy  --header 'Content-Type: application/json' --data '{"userId": 3, "bondId": 0,"quantity": 10,"price": 20}'
curl --request POST --url http://localhost:8080/transaction/buy  --header 'Content-Type: application/json' --data '{"userId": 4, "bondId": 0,"quantity": 10,"price": 30}'
curl --request POST --url http://localhost:8080/transaction/sale  --header 'Content-Type: application/json' --data '{"userId": 10, "bondId": 0,"quantity": 40,"price": 30}'
curl --request GET  --url http://localhost:8080/monitor/userRepo
curl --request GET --url http://localhost:8080/monitor/billRepo
curl --request POST --url http://localhost:8080/transaction/buy  --header 'Content-Type: application/json' --data '{"userId": 5, "bondId": 0,"quantity": 10,"price": 45}'
curl --request POST --url http://localhost:8080/transaction/buy  --header 'Content-Type: application/json' --data '{"userId": 6, "bondId": 0,"quantity": 10,"price": 55}'
curl --request POST --url http://localhost:8080/transaction/buy  --header 'Content-Type: application/json' --data '{"userId": 7, "bondId": 0,"quantity": 10,"price": 15}'
curl --request POST --url http://localhost:8080/transaction/buy  --header 'Content-Type: application/json' --data '{"userId": 8, "bondId": 0,"quantity": 10,"price": 25}'
curl --request POST --url http://localhost:8080/transaction/sale  --header 'Content-Type: application/json' --data '{"userId": 10, "bondId": 0,"quantity": 40,"price": 50}'
curl --request GET  --url http://localhost:8080/monitor/userRepo
curl --request GET --url http://localhost:8080/monitor/billRepo
curl --request POST --url http://localhost:8080/transaction/buy  --header 'Content-Type: application/json' --data '{"userId": 9, "bondId": 0,"quantity": 10,"price": 35}'
curl --request POST --url http://localhost:8080/transaction/sale  --header 'Content-Type: application/json' --data '{"userId": 10, "bondId": 0,"quantity": 20,"price": 5}'
curl --request GET  --url http://localhost:8080/monitor/userRepo
curl --request GET --url http://localhost:8080/monitor/billRepo







