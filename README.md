# Not Yet Complete
[![Build Status](https://travis-ci.org/cphdat3sem2019spring/tomcat-jpa-template.svg?branch=master)](https://travis-ci.org/cphdat3sem2019spring/tomcat-jpa-template)

run localy like:

###Before start
- Create a development Database locally
- Create a test database locally (to be used for integration tests)
- Rename persistence_TEMPLATE.xml --> persistence.xml. DON'T change the persistence-unit names, but update the rest to point to your two databases

### Run like
- mvn test
- mvn `-DPU_NAME=pu-test-with-mysql verify`     This sets the name of the persistence-unit to be used with integration tests

## Info
see .travis.yml for how to execute integration tests on travis





