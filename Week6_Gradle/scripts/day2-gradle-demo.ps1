param(
    [string]$BaseUrl = "http://localhost:5173",
    [string]$Headless = "true",
    [switch]$Browser
)

$ErrorActionPreference = "Stop"

Write-Host "=== W6D2: Maven baseline ==="
mvn -q clean -Dtest=RefactoringTest test

Write-Host ""
Write-Host "=== W6D2: Gradle compile and dependency graph ==="
.\gradlew.bat --version
.\gradlew.bat clean testClasses
.\gradlew.bat build
.\gradlew.bat dependencies --configuration testRuntimeClasspath | Out-File -Encoding utf8 build\w6d2-test-runtime-dependencies.txt
Write-Host "Dependency graph saved to build\w6d2-test-runtime-dependencies.txt"

Write-Host ""
Write-Host "=== W6D2: Gradle CatalogPOM test ==="
.\gradlew.bat catalogPOMTest "-Pheadless=$Headless" "-PbaseUrl=$BaseUrl"

Write-Host ""
Write-Host "=== W6D2: Gradle parallel fork demo ==="
.\gradlew.bat cucumberSmoke "-Pheadless=$Headless" "-PbaseUrl=$BaseUrl"

# if ($Browser) {
#     Write-Host ""
#     Write-Host "=== W6D2: Optional cucumberSmoke Test==="
#     .\gradlew.bat w6d1CheckoutTest "-Pheadless=$Headless" "-PbaseUrl=$BaseUrl"
# }
#
# Write-Host ""
# Write-Host "Optional build scan:"
# Write-Host ".\gradlew.bat RefractaringTest --scan"
