# JDBC_scripts
all jdbc scripts

## Steps for jdbc connection
#### Install java
> [Download](https://www.oracle.com/java/technologies/downloads/) java jdk and install.
#### Install oracle
> Download and install oracle express edition

#### Set classpath
1. Download [ojdbc14.jar](https://static.javatpoint.com/src/jdbc/ojdbc14.jar)
2. Go to environment variable then click on new button in user variables.
3. In variable name = classpath
4. variable = path to ojdbc14.jar by appending ojdbc14.jar;.;
as C:\oraclexe\app\oracle\product\10.2.0\server\jdbc\lib\ojdbc14.jar;.;

5. restart local mechine

#### Test connection
1. clone this repo
2. compile jdbc.java, compile and run check_jdbc.java file .
```
javac jdbc.java
javac check_jdbc.java
java check_jdbc
```