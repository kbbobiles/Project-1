# Project-1

```
==========CREATING DATABASE==========
1) > mysql -u root -p
2) mysql> create database moviedb;
3) mysql> quit
4) Navigate to sql files
5) > mysql -u root -p -D moviedb < createtable.sql
6) > mysql -u root -p -D moviedb < data.sql

==========COMPILING PROJECT==========
1) Navigate to Project-1/src
FOR WINDOWS
2) > javac -classpath .;../lib/mysql-connector-java-5.0.8-bin.jar project1/Proj1.java -d ../bin
FOR LINUX
2) > javac -classpath .:../lib/mysql-connector-java-5.0.8-bin.jar project1/Proj1.java -d ../bin

===========RUNNING PROJECT===========
3) Navigate to Project-1/bin
FOR WINDOWS
4) > java -classpath .;../lib/mysql-connector-java-5.0.8-bin.jar project1.Proj1
FOR LINUX
4) > java -classpath .:../lib/mysql-connector-java-5.0.8-bin.jar project1.Proj1
```
