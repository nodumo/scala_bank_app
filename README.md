# Banking Credit Transaction Validator 

Simple banking application. 

This was an attempt to process application transaction data events one by one from a file.
I took the basic stipulation and sought to demonstrate how best the problem could be modelled 
as a real application solution in two days time and came about 90% of the way there. 

# Application design pattern used 
1. Onion Architecture 
2. Domain Driven Design 

# SBT Commands 

##### Project run  
```
sbt run application/run 
``` 

##### Global test command 
```
sbt run test 
``` 

# Project structure 
* bankingapp/
    * application/ (Core application)
        * src/ (Processing lines of input data)
        * test/ 
    * common/ (Common concerns)
        * src/ (Common utilities mostly around issues I ran into with dates)
        * test/
    * domain/ (Business domain concerns)
        * src/
        * test/ (Test coverage for validation but not constructors)
    * infrastructure/(Infrastructural concerns)
        * src/
        * test/
    * project/(No Test coverage for validation)
        * build.properties
        * plugins.sbt
        * ProjectDependencies
    * build.sbt (Build definition)

# Things that could have been done better 
1. Fix the validation logic 
2. Investigate better ways of handling dates
   * Serializing between Java SQL date time and Joda time may  be dropping timezone
3. More test coverage, I prioritized validating the validation logic. 