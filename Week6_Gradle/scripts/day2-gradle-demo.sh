#!/usr/bin/env bash
set -euo pipefail

BASE_URL_VALUE="${BASE_URL:-http://localhost:5173}"
HEADLESS_VALUE="${HEADLESS:-true}"

echo "=== W6D2: Maven baseline ==="
/usr/bin/time -p mvn -q clean -Dtest=RefactoringTest test

echo
echo "=== W6D2: Gradle compile and dependency graph ==="
./gradlew --version
./gradlew clean testClasses
./gradlew build
./gradlew dependencies --configuration testRuntimeClasspath > build/w6d2-test-runtime-dependencies.txt
echo "Dependency graph saved to build/w6d2-test-runtime-dependencies.txt"

echo
echo "=== W6D2: Gradle catalogPOMTest test ==="
/usr/bin/time -p ./gradlew catalogPOMTest -Pheadless="${HEADLESS_VALUE}" -PbaseUrl="${BASE_URL_VALUE}"

echo
echo "=== W6D2: Gradle cucumberSmoke Test ==="
./gradlew cucumberSmoke -Pheadless="${HEADLESS_VALUE}" -PbaseUrl="${BASE_URL_VALUE}"


