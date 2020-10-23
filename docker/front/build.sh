path=$(cd "$(dirname "${BASH_SOURCE[0]}")"; pwd -P)
cd $path


cd ../../front
npm run build
cd $path

cp -r ../../front/dist .
cd ../..

docker build -t train-management/front-end ./docker/front

rm -rf dist