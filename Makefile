install:
	./gradlew clean install
	./gradlew check
	./gradlew test
run-dist:
	./build/install/app/bin/app

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew check
test:
	./gradlew test

