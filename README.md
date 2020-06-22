# Banking transaction 

Simple banking application. 

# SBT Commands 

##### Project run  
```
sbt run applicationpos/run 
``` 

##### Global test command 
```
sbt run test 
``` 

# Project structure 
* bankingapp/
    * application/ (Core application)
        * src/
        * test/
    * applicationtransact/
        * src/
        * test/
   * applicationtransactpos/
        * src/
        * test/
    * common/ (Common concerns)
        * src/
        * test/
    * domain/ (Business domain concerns)
        * src/
        * test/
    * infrastructure/(Infrastructural concerns)
        * src/
        * test/
    * project/
        * build.properties
        * plugins.sbt
        * ProjectDependencies
    * build.sbt (Build definition)
