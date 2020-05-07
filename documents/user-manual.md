# User's manual

## To start

Download the newest verion of the game from [Here](https://github.com/Tseipii89/ot-harjoitustyo/releases) (at the time of writing V2.0)

### Configuration

To use the different difficulty settings, you must include the config.properties file in the root folder. It contains the difficulty settings for easy, medium and hard difficulties. 

For example the easy -level is configured as follows:
```
EasyspaceBetweenPipes = 300
EasysizeOfHole = 175
EasyspeedOfPipes = 2
```

## To start the game

After downloading the JAR -file you can start the game with the command 

```
java -jar FallyBird-(version).jar
```

Unfortunately I couldn't get the config.properties file to work with the JAR -file. So if you want to also include the difficulty settings, you should clone the git repo (the command is "git clone git@github.com:Tseipii89/ot-harjoitustyo.git"), and run "mvn compile exec:java -Dexec.mainClass=main.Main"

## Login

In the login screen the player writes his nickname, that he/she wishes to use. The nickname needs to be between 3 and 8 characters. If the player hits a new highscore this nickname will be saved with the info about the highscore. Multiple players can have same nickname.

Also if you installed the game using the cloning method, you can also choose the difficulty for the game.

![Login screen](https://github.com/Tseipii89/ot-harjoitustyo/blob/master/documents/images/flappy-login.JPG)

## Game play

After successful login, you'll be shown the next screen. The game starts after you press UP-arrow the first time. 

As you can see your current nickname is shown on the top left and the alltime highscorer is shown on the right top corner. The related scores are also shown.

![Gamestart screen](https://github.com/Tseipii89/ot-harjoitustyo/blob/master/documents/images/flappy-gameStart.JPG)

After the pressing of UP -arrow the game starts. You bounce the bird with UP -arrow and try to get past the holes in the pipes. Each hole you pass gives you 1 point.

![GameOn screen](https://github.com/Tseipii89/ot-harjoitustyo/blob/master/documents/images/flappy-gameOn.JPG)
