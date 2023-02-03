This postgres is a standalone DB for development only

docker build -t dev-db-postgres .
docker run -itd --name dev-db-postgres -p 5435:5432  dev-db-postgres
docker exec -it dev-db-postgres psql -U postgres