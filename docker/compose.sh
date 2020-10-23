path=$(cd "$(dirname "${BASH_SOURCE[0]}")"; pwd -P)
cd $path

# Create a .war file and store it in back/ directory
cd ../back
mvn clean package -DskipTests
cd $path

cp ../back/webservices/target/train-management-backend.war ./back

# Create a dist directory and store it in front/ directory
cd ../front
npm run build
cd $path

cp -r ../front/dist ./front

# build docker images 
docker-compose build

# delete generated files
rm -rf front/dist back/train-management-backend.war

# run docker 
docker-compose up