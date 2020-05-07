# Test document

The game was tested using unit and integration tests. The testing library was JUNIT. 
These tests were used to check that anything didn't break. The main "acceptance" testing was done manually vía running the game and checking for errors.

The testing has been done with the version where the config.properties -file is active. The testing has been done with windows 10 system, 
however the acceptance tests have been also run with Linux system.

## Unit- and integrationtesting

The two packages that were tested, were domain and dao. 

### Domain -package

Most of the tests in the domain package [domain](https://github.com/Tseipii89/ot-harjoitustyo/tree/master/src/main/java/domain) 
were for the [Game](https://github.com/Tseipii89/ot-harjoitustyo/blob/master/src/main/java/domain/Game.java) class. 
The game -class handles most of the game logic, so this makes sense. The untested methods are mostly getters and setters. 
One black sheep are the methods that handle the Bird jumping. These haven't been tested in the Game class due to time limits 
(and the Game class actually only calls the Bird -class's jump method).
Maybe I'm to be forgiven since the Bird jumping is tested in the Bird class.

In the [Bird](https://github.com/Tseipii89/ot-harjoitustyo/blob/master/src/main/java/domain/Bird.java) "almost" (I'll let the almost be very very coarse) all other methods are tested
apart from the render method. 

Same thing with the [Pipe](https://github.com/Tseipii89/ot-harjoitustyo/blob/master/src/main/java/domain/Pipe.java) class.

[Nickname](https://github.com/Tseipii89/ot-harjoitustyo/blob/master/src/main/java/domain/Nickname.java) class is used to save highscore information, and it is tested 100% (yay!).

### DAO -package

FlappyBird Game uses one DAO [DAO](https://github.com/Tseipii89/ot-harjoitustyo/blob/master/src/main/java/dao/FileHighscoreDao.java) -class.

The test have been written for the reading of the highscore DAO. FileHighscoreDao -class creates a new highscore.txt file if one doesn't exist.
Both scenarios have been tested. The one where the highscore.txt exists and the one where it doesn't. The temporary file is created using command:

```
deleteOnExit();
```

### Test coverage

The UI package is not tested. Test instruction coverage for DAO and Domain packages is 76% and branch coverate is 65%.

![Test coverage](/documents/images/test-coverage.JPG)

The testing is not perfect, and the player can find ways to destroy the game (for example with wrong input to nickname).
