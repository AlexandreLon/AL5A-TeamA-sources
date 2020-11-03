path=$(cd "$(dirname "${BASH_SOURCE[0]}")"; pwd -P)
cd $path

cd ../../back
mvn clean package -DskipTests -Pdocker
cd $path

cp ../../back/webservices/target/train-management-backend.war .
cd ../..

echo "Compiling the Train Management system"
docker build -t train-management/back-end ./docker/back
echo "Done"

rm -rf train-management-backend.war