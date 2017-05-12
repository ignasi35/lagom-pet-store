# Lagom Pet store

Pet store application to demonstrate OpenAPI support in Lagom.

## Structure

A Lagom port of the famous Open API [Pet Store](http://petstore.swagger.io/) example. The relevant contents are: 

```
./build.sbt
./petService
./petService/src/main/openapi/petService.json
./petService/src/main/java/com/example/pet/impl/
./storeService
./storeService/src/main/openapi/storeService.json
./petService/src/main/scala/com/example/store/impl/
./web
./web/src/main/java/controllers
./web/src/main/java/Module.java
./web/src/main/openapi
./web/src/main/openapi/petService.json
./web/src/main/openapi/storeService.json
./web/src/main/...
```

 * `./build.sbt`: in the project build we include the `LagomOpenApiPlugin` on a project to make it scan its `src/main/openapi` for `json` or `yaml` files. The detected files are then converted into source code managed by `sbt` You can inspect the genreated code browsing the `target/scala-2.11/openapi/` folder. Any code under `target/` will be overwriten in future compilations and should not be edited or committed. Note that there's no `petService-api` separate from `petService`, this is just a convenience approach assuming that only `petService.json` will be shared with consumers.  
 * `./petService`: a Lagom service implemented in java. Once the code is compiled a `PetserviceApi.java` file exists with the Lagom descriptor. We can then work on the implementation normally (see `PetServiceImpl.java`).   
 * `./petService/src/main/openapi/petService.json`: an OpenAPI specification of the pet service.
 * `./petService/src/main/java/com/example/pet/impl/`: the lagom implementation of the specification. This is an in-memory hardcoded service to keep the source code to a minimum.
 * `./storeService`: a Lagom service implemented in scala.
 * `./storeService/src/main/openapi/storeService.json`: an OpenAPI specification of the store service.
 * `./web`: a Play application using the Java API that will consume both pet service and store service. 
 * `./web/src/main/java/Module.java`: using the Play Guice DI API we bind the clients to consume the Lagom services. 
 * `./web/src/main/openapi`: contains copies of the swagger specifications of the services it wants to consume.

