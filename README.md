Travis CI: [![Build Status](https://travis-ci.org/Tseipii89/ot-harjoitustyo.svg?branch=master)](https://travis-ci.org/Tseipii89/ot-harjoitustyo)

# FlappyBird #

This is a school project for the course "Programming techniques". The focus is on agile development, automated testing, proper architecture and version control. The FlappyBird game is really just a way to apply different things I've learned.

FlappyBird is a game where you try to fly through pipes with a bouncing bird. The up-arrow bounces the bird a little bit up. The game ends if the bird hits any pipes or bottom of the screen. More info at <https://en.wikipedia.org/wiki/Flappy_Bird>. A little side note, the game was earning 50,000$ a day as a free mobile game!!! That is just crazy!

## How to start ##

Do a normal clone of the git repo to your wanted folder (the command is "git clone git@github.com:Tseipii89/ot-harjoitustyo.git").

Then use your terminal to navigate to the root of the just cloned program and run "mvn compile exec:java -Dexec.mainClass=Main"

You can also create a JAR file via command "mvn package". This file can be found at *target/FlappyBird-1.0-SNAPSHOT.jar*

### Test and Style ###

You can generate the jacoco report with command "mvn jacoco:report". You find this document under *target/site/jacoco/index.html* OR you can just run the rest with command "mvn test"

You can generate the checkstyle document with command "mvn jxr:jxr checkstyle:checkstyle". You find this document under *target/site/checkstyle.html*

## School project ##

[Hours Sheet](/documents/hourSheet.md)

[Requirements analysis](/documents/Requirements.md)

[Architecture Definition](/documents/architectureDefinition.md)



