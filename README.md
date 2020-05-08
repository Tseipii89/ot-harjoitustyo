Travis CI: [![Build Status](https://travis-ci.org/Tseipii89/ot-harjoitustyo.svg?branch=master)](https://travis-ci.org/Tseipii89/ot-harjoitustyo)

# FlappyBird #

This is a school project for the course "Programming techniques". The focus is on agile development, automated testing, proper architecture and version control. The FlappyBird game is really just a way to apply different things I've learned.

FlappyBird is a game where you try to fly through pipes with a bouncing bird. The up-arrow bounces the bird a little bit up. The game ends if the bird hits any pipes or bottom of the screen. More info at <https://en.wikipedia.org/wiki/Flappy_Bird>. A little side note, the game was earning 50,000$ a day as a free mobile game!!! That is just crazy!

## How to start ##

Two ways:

**First**

Download the newest verion of the game from [Here](https://github.com/Tseipii89/ot-harjoitustyo/releases) and run it with the command 

```
java -jar FlappyBird-(version).jar
```

In this method the difficulty settings don't work.

**Second (requires that maven is installed)**

Do a normal clone of the git repo to your wanted folder 

```
git clone git@github.com:Tseipii89/ot-harjoitustyo.git
```


Then use your terminal to navigate to the root of the just cloned program and run

```
mvn compile exec:java -Dexec.mainClass=main.Main
```

In this version the difficulty settings work.

## Releases ##

[Versio 1.0](https://github.com/Tseipii89/ot-harjoitustyo/releases/tag/Viikko5)

[Versio 2.0](https://github.com/Tseipii89/ot-harjoitustyo/releases/tag/Viikko6)

[Versio 3.0 (final release)](https://github.com/Tseipii89/ot-harjoitustyo/releases/tag/Viikko7)

You can run the JAR -file with command "java -jar FlappyBird-(version).jar" after you have entered to the directory where the JAR -file is located.


## Terminal commands

### Generating JAR

The JAR -files in the releases are done with windows machine. It means that they might not work with linux or mac machines. 

If they don't work you can generate your own JAR -file from the source files with the command after cloning the files.

```
mvn package
```

Be sure to be in the root folder.

This command generates the JAR into target folder. Run the version with the name Flappybird-1.0-SNAPSHOT.jar (the one without the original in the beginning of the name).

### Javadoc ###

Javadoc can be created using command 
```
mvn javadoc:javadoc
```
Javadoc can be found at *target/site/apidocs/index.html*

### Test and Style ###

You can generate the jacoco report with command 
```
mvn jacoco:report
```
You find this document under *target/site/jacoco/index.html* OR you can just run the rest with command 
```
mvn test
```
The jacoco report needs that you have run tests first.

### Checkstyle

You can generate the checkstyle document with command 
```
mvn jxr:jxr checkstyle:checkstyle
```
You find this document under *target/site/checkstyle.html*

## Documents ##

[Hours Sheet](/documents/hourSheet.md)

[Requirements analysis](/documents/Requirements.md)

[Architecture Definition](/documents/architectureDefinition.md)

[User-manual](/documents/user-manual.md)

[Test-document](/documents/test-document.md)




