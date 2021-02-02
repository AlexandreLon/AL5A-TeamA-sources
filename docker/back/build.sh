PARAMS=""
SARG=0
while (("$#")); do
    case "$1" in
    -s | --skip)
        SARG=1
        break
        ;;
    --) # end argument parsing
        shift
        break
        ;;
    -* | --*=) # unsupported flags
        echo "Error: Unsupported flag $1" >&2
        exit 1
        ;;
    *) # preserve positional arguments
        PARAMS="$PARAMS $1"
        shift
        ;;
    esac
done

path=$(cd "$(dirname "${BASH_SOURCE[0]}")"; pwd -P)
cd $path

if [[ $SARG == 0 ]]; then
    cd ../back
    mvn clean package -DskipTests -Pdocker
    cd $path
fi

cp ../../back/webservices/target/train-management-backend.war .
cd ../..

echo "Compiling the Train Management system"
docker build -t train-management/back-end ./docker/back
echo "Done"

rm -rf train-management-backend.war