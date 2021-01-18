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

# Create a .war file and store it in back/ directory
if [[ $SARG == 0 ]]; then
    cd ../back
    mvn clean package -DskipTests
    cd $path
fi

cp ../back/webservices/target/train-management-backend.war ./back

# Create a dist directory and store it in front/ directory
if [[ $SARG == 0 ]]; then
    cd ../front
    npm run build
    cd $path
fi

cp -r ../front/dist ./front

cd ../cucumber
zip -r ../docker/cucumber/cucumber.zip ./*
cd $path

# build docker images 
docker-compose build

# delete generated files
rm -rf front/dist back/train-management-backend.war cucumber/cucumber.zip

# run docker 
docker-compose up