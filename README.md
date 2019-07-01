sudo apt-get install httpie

mvn spring-boot:run -Dserver.port=8000
mvn spring-boot:run -Dserver.port=8001
http localhost:8000/simple-blockchain/v1.0/mine
echo "{\"nodes\":[\"localhost:8000\"]}" | http POST localhost:8001/simple-blockchain/v1.0/nodes/register
echo "{\"nodes\":[\"localhost:8001\"]}" | http POST localhost:8000/simple-blockchain/v1.0/nodes/register
http localhost:8001/simple-blockchain/v1.0/chain
http localhost:8001/simple-blockchain/v1.0/nodes/resolve
http localhost:8001/simple-blockchain/v1.0/chain