Environments

Use the strictly layered config:

application-common.properties → application-{env}.properties (dev/uat/ppd) → local/secrets (gitignored) → -D overrides → TEST__... env vars.

Pick env:

./gradlew test -Denv=dev         # or uat / ppd

API (TestNG)

Sequential:

./gradlew apiTest -Denv=dev


Parallel (methods):

./gradlew apiTest -Denv=dev -Dpar.mode=methods -Dpar.threads=6


With Gradle forks (2 JVMs × 6 threads):

./gradlew apiTest -Denv=dev -Dpar.mode=methods -Dpar.threads=6 -Dpar.forks=2

UI (TestNG)

Default browser from config:

./gradlew uiTest -Denv=ppd


Headless + multi-browser + parallel:

./gradlew uiTest -Denv=ppd -Dsel.headless=true -Dsel.browsers=chrome,firefox \
-Dpar.mode=methods -Dpar.threads=6


Edge only:

./gradlew uiTest -Denv=dev -Dsel.browsers=edge

Mixed API + UI (TestNG)

Run both packs together (suite controls ordering):

./gradlew test -Denv=uat -Dsuite=testng-mixed.xml


Parallelize both (methods):

./gradlew test -Denv=uat -Dsuite=testng-mixed.xml \
-Dpar.mode=methods -Dpar.threads=8

Cucumber — API pack

Sequential:

./gradlew cukeApi -Denv=dev


Parallel (per-scenario via DP) + forks:

./gradlew cukeApi -Denv=dev -Dpar.mode=methods -Dpar.threads=6 -Dpar.forks=2


Tag filters:

./gradlew cukeApi -Denv=dev -Dcucumber.filter.tags="@api and @smoke"

Cucumber — UI pack

Headless, multi-browser (browser chosen in Hooks by first config entry unless you add @chrome/@firefox tags):

./gradlew cukeUi -Denv=ppd -Dsel.headless=true


Parallel scenarios:

./gradlew cukeUi -Denv=ppd -Dpar.mode=methods -Dpar.threads=6


Tag filters:

./gradlew cukeUi -Dcucumber.filter.tags="@ui and not @wip"

Browsers & Headless

Override at runtime (highest precedence):

# one browser
./gradlew uiTest -Dsel.browsers=chrome

# many browsers (used by CrossBrowser tests and/or your Hooks)
./gradlew uiTest -Dsel.browsers=chrome,firefox,edge

# headless toggle
./gradlew uiTest -Dsel.headless=true

Parallel controls

TestNG level:

-Dpar.mode=tests|classes|methods  -Dpar.threads=8  -Dpar.dpThreads=8


Gradle JVM forks:

-Dpar.forks=2  -Dpar.forkEvery=0


Combine (e.g., 2 forks × 6 methods):

./gradlew test -Dpar.forks=2 -Dpar.mode=methods -Dpar.threads=6

Property overrides (without editing files)

System properties:

./gradlew test -Dapi.base=https://blue.api.example.com -Dui.base=https://the-internet.herokuapp.com


Environment variables (CI-friendly):

export TEST__par__mode=methods
export TEST__par__threads=8
export TEST__sel__headless=true
export TEST__api__base=https://reqres.in
./gradlew test

Suites & task shortcuts

Choose a suite file:

./gradlew test -Dsuite=testng-api.xml
./gradlew test -Dsuite=testng-ui.xml
./gradlew test -Dsuite=testng-mixed.xml


Or use dedicated tasks:

./gradlew apiTest
./gradlew uiTest
./gradlew cukeApi
./gradlew cukeUi

Reports & artifacts (local)

TestNG HTML: build/reports/tests/test/index.html

JUnit XML: build/test-results/test/

Cucumber:

HTML: build/reports/cuke-api/html and build/reports/cuke-ui/html

JSON/XML: build/reports/cuke-*/