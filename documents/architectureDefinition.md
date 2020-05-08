# Architecture definition #

## Structure ##

Application follows [Three-tier](https://en.wikipedia.org/wiki/Multitier_architecture) architecture and the structure (and classes) are as follows:

<img src="/documents/images/layer-architecture.png">

### Package _flappyBird.ui_ ###

Package _flappyBird.ui_ handles the interface using JavaFX. It contains only one class: Render. Render class initializes the stage, set the appropriate scene (either starter Scene that asks user for his nickname or game Scene that is the game itself). Render class also listens to users key presses.

### Package _flappyBird.domain_ ###

_flappyBird.domain_ handles the app logic. The motor of the game is in the Game -class. Game class sets difficulty, counts scores, updates highscores accordinly to HighscoreDAO, jumps the bird, creates Pipes, reset the game etc. All the main logic is behind the Game class.

The domain package also has the Pipe and Bird classes that implement Sprite interface. These classes are used handle the x and y position of the sprites and more importantly the movement of the game elements. The Sprite interface also has method called intersect that checks if this Sprite has collided with another one. This method is used to check if the Bird element has collided with any Pipes.

Lastly domain package has the Nickname -class that is used to only have the information about the nickname of the player and his highscore. It is used to save highscore and nickname information of the highscore holder. It could also be under DAO package, but I wanted to save the DAO package to only preserve methods about accessing the "database".

### Package _flappyBird.dao_ ###

_flappyBird.dao_ handles the persistence saving of data. The classes implement the [DAO](https://en.wikipedia.org/wiki/Data_access_object) model so new data access methods are easy to implement.

The data access method used in this realisation of the game uses file to save the nickname and highscore holder in the format of "easyplayer;easyhighscore;mediumplayer;mediumhighscore;hardplayer;hardhighscore". The class to handle the file saving method is called "FileHighscoreDao". If there is no highscore, the game initializes the highscore file in the format of "none;0;none;0;none;0". The highscore file can be found at the root of the source name as a "highscore.txt".

## Initialization of a new game ##

Below is an sequence graphic of the initialization of a new game. It has been simplified a little bit to only account the necessary information about game initialization (for example new Image and new File are not given their own vertical objects).

<img src="/documents/images/Sequence-initialization.png">
