path=$(cd "$(dirname "${BASH_SOURCE[0]}")"; pwd -P)
cd $path

cd ../..

zip -r docker/cucumber/cucumber.zip cucumber/*

echo "Compiling the Train Management system"
docker build -t train-management/cucumber ./docker/cucumber
echo "Done"

rm -rf docker/cucumber/cucumber.zip