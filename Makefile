install:
	./gradlew clean build

run-dist:
	./build/install/app/bin/app

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew check
test:
	./gradlew test