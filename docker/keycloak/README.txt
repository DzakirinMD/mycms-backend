This keycloak is a standalone SSO for development only

sudo docker run -itd --name dev-keycloak-mycms -p 5436:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.1.1 start-dev
sudo docker start

Admin Console:
http://localhost:5436/admin



Resources:
https://www.keycloak.org/getting-started/getting-started-docker