APP_NAME=sbking

build:
	docker build -t $(APP_NAME) .

run:
	docker run --rm $(APP_NAME)

clean:
	docker rmi $(APP_NAME)
	rm ./sbking-client.jar
	rm ./sbking-server.jar

client:
	mvn -f pom-client.xml clean package && cp target/sbking-1.0.0-alpha-jar-with-dependencies.jar ./sbking-client.jar

server:
	mvn clean package && cp target/sbking-1.0.0-alpha-jar-with-dependencies.jar ./sbking-server.jar