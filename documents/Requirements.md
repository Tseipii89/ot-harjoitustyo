# Requirement specification

## The purpose of the application

The app is a copy of the popular mobile app Flappy Bird from few years ago. The goal of the game is to get as far as possible. By clicking the up arrow the bird will jump a certain distance up. These jumps are used to get past obstacles. <https://en.wikipedia.org/wiki/Flappy_Bird> Wikipedia article on Flappy bird.

## Users

Players enter the username to log in to the game. This nickname is only used to keep track of the highscore and which nicknamed player got it. The highest score and the player name will be shown in the app. There are no other users.

## The functionality provided by the basic version

### Before entering the game

- ~User will be shown a screen with textbox in which to write his nickname. Nickname has to be at least 3 characters long. Nickname doesn't has to be unique.~
- ~User will be shown a button to continue to game. The button doesn't work unless the user has inserted a nickname.~

### The Game

- ~User will see the screen and will be asked to press up-arrow to start the game~
- ~Up-arrow will jump the bird up a little bit~
   - ~The bird can't jump over the top edge of the screen~
- ~The score will get higher the longer distance the user gets to~
- ~The screen will also show the highest score and username of the player who got it~
- ~There are moving obstacles with little holes that the player needs to get past~
   - ~The moving obstacles continue to come as long as the game is on~
- ~If user hits wall, game over text will appear and the score doesn't increase anymore~
   - ~User can start new game by pressing up-arrow.~ 
   - ~Same nickname persists.~
- ~If user hits the bottom of the screen the game ends~

## Further development ideas

Possbile improvements if time permits:

- Users can create an username and password to signin/login into the system
- ~The screen will show some congratulations pop-up if user makes new high score~
- ~Different difficulty settings~
- Maybe some sound effects?