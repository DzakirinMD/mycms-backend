This postgres is a standalone DB for development only

sudo docker build -t dev-db-postgres .
sudo docker run -itd --name dev-db-postgres -p 5435:5432 dev-db-postgres
sudo docker exec -it dev-db-postgres psql -U postgres

CREATE DATABASE my_cms_domestic_transfer;
CREATE DATABASE my_cms_inhouse_transfer;