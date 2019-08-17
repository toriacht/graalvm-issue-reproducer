# graalvm-issue-reproducer
This repo is a simplified reproducer graal vm issue
Issue

I'm trying to do a native build (using Quarkus). I include the `-H:ResourceConfigurationFiles=mypath/reflection.json` but the build does not like my file schema. It throws
`Error: Error parsing resource configuration in /project/classes/reflection.json: first level of document must be an object`

The file is located in /src/main/resources


# How to reproduce

1. clone repo
2. run `./mvnw package -Pnative -Dnative-image.docker-build=true`
3. Expected error
```
Error: Error parsing resource configuration in /project/classes/reflection.json:
first level of document must be an object
Verify that the configuration matches the schema described in the -H:PrintFlags=+ output for option ResourceConfigurationFiles.
com.oracle.svm.core.util.UserError$UserException: Error parsing resource configuration in /project/classes/reflection.json:
first level of document must be an object
Verify that the configuration matches the schema described in the -H:PrintFlags=+ output for option ResourceConfigurationFiles.
	at com.oracle.svm.core.util.UserError.abort(UserError.java:65)

```
