path=$(cd "$(dirname "${BASH_SOURCE[0]}")"; pwd -P)
cd $path

cd ../..

docker build -t train-management/front-end ./docker/front