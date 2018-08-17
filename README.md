# AltimaTest

### How to run the application

1. Open your command line and position yourself in the root directory of the project.
2. Execute "mvn compile".
3. Execute "mvn exec:java -Dexec.mainClass="altima.test.demo.TreeDemo" -Dexec.args="ENTER PATH TO YOUR FILE"".
    Example: mvn exec:java -Dexec.mainClass="altima.test.demo.TreeDemo" -Dexec.args="E:/myfile".

If "-Dexec.args="ENTER PATH TO YOUR FILE" is omitted from the third step, default path one will used ("src/main/resources/example1.txt").

Expected output example:<br>

File content:<br>
```
Adam Ivan
Marko Stjepan
Stjepan Adam
Robert Stjepan
Fran Ivan
Leopold Luka
Adam Leopold
```
Output:<br>
```
Ivan
    Adam
        Stjepan
            Marko
            Robert
    Fran
Luka
    Leopold
        Adam
            Stjepan
                Marko
                Robert
```

### How to run tests

1. Open your command line and position yourself in the root directory of the project.
2. Execute "mvn -Dtest=TreeTest test".
