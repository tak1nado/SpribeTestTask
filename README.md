# SPRIBE TEST TASK

## Project configuration

1. create package `/src/main/resources/META-INF` (in `.gitignore`)

2. create properties files with project settings and credentials in `META-INF` package:
    1) `META-INF/dev.properties`
    2) `META-INF/qa.properties `
    3) fill them with following params and own credentials for dev and qa environment:
      - api tests dev/qa credentials:
      
           ```properties
           #for example
           #dev credentials
           com.spribe.player.controller.api.url=http://3.68.165.45/

           #default users data
           com.spribe.player.controller.supervisor.login=supervisor
           com.spribe.player.controller.supervisor.role=supervisor

           com.spribe.player.controller.admin.login=admin
           com.spribe.player.controller.admin.role=admin
           ```
                     
3. in project `pom.xml` file find and set **active/default** environment profile `default.active.environment.profile`
      1. **qa** or
      2. **dev**
      
## For tests run

- from commandline: `mvn clean test allure:report`
    1) **clean** - delete old compiled code and artifacts
    2) **test** - compile and run tests
    3) **allure:report** - build report
- also you can run commands separately 
    1) `mvn clean test`  - clean and run 
    2) `mvn allure:report`  - generate report
- then you can open report in your browser using generated report in target/allure-report folder
- by default Spring will run dev profile with dev properties, to run other(f.e. production) profiles, use additional command - **-Dspring.profiles.active=production** :
    ```bash
    mvn clean test allure:report -Dspring.profiles.active=environmentToUse
    ```
- logging Allure Steps into console:
      `-Dlogging.steps=true`


## Dependencies

- download and set up Maven
- `pom.xml` - file with plugins and dependencies

## Documentation

- Swagger UI URL `http://3.68.165.45/swagger-ui.html#/player-controller/getAllPlayersUsingGET`

- Git repository `https://github.com/tak1nado/SpribeTestTask`

