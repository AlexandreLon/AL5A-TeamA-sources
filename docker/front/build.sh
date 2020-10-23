path=$(cd "$(dirname "${BASH_SOURCE[0]}")"; pwd -P)
cd $path

docker build -t train-management/front-end .