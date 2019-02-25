# Not Yet Complete
[![Build Status](https://travis-ci.org/cphdat3sem2019spring/tomcat-jpa-template.svg?branch=master)](https://travis-ci.org/cphdat3sem2019spring/tomcat-jpa-template)

run localy like:

###Before start
- Create a development Database locally
- Create a test database locally (to be used for integration tests)

mvn test
mvn -DPU_NAME=pu-test-with-mysql verify     This sets the name of the persistence-unit to be used with integration tests

## Info
see .travis.yml for how to execute integration tests on travis





