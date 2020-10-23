path=$(cd "$(dirname "${BASH_SOURCE[0]}")"; pwd -P)
cd $path

cd ../../back
mvn clean package -DskipTests
cd $path

cp ../../back/webservices/target/tain-management-backend.war .

echo "Compiling the Train Management system"
docker build -t train-management/back-end .
echo "Done"

rm -rf tain-management-backend.war