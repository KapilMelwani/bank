Create Docker Network - techbankNet 
====================================
docker network create --attachable -d bridge techbankNet

Create MongoDB Container
========================
docker run -it -d --name mongo-container \
-p 27017:27017 --network techbankNet \
--restart always \
-v mongodb_data_container:/data/db \
mongo:latest

Create MySQL
============    
Run in Docker:
docker run -it -d --name mysql-container \
-p 3307:3306 --network techbankNet \
-e MYSQL_ROOT_PASSWORD=techbankRootPsw \
--restart always \
-v mysql_data_container:/var/lib/mysql  \
mysql:latest

Client tools in Docker – Adminer:
=================================
docker run -it -d --name adminer \
-p 8080:8080 --network techbankNet \
-e ADMINER_DEFAULT_SERVER=mysql-container \
--restart always adminer:latest