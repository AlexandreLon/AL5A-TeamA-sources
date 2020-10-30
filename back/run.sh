SCRIPT=`realpath -s $0`
SCRIPTPATH=`dirname $SCRIPT`

if [ -f $SCRIPTPATH/settings.xml ]; then
    mvn clean install -s settings.xml -DskipTests
else
    mvn clean install -DskipTests
fi

cd webservices
mvn spring-boot:run