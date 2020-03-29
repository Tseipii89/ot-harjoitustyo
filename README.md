Travis CI: [![Build Status](https://travis-ci.org/Tseipii89/ot-harjoitustyo.svg?branch=master)](https://travis-ci.org/Tseipii89/ot-harjoitustyo)

# FlappyBird #

This is a school project for the course "Programming techniques". The focus is on agile development, automated testing, proper architecture and version control. The FlappyBird game is really just a way to apply different things I've learned.

FlappyBird is a game where you try to fly through pipes with a bouncing bird. The up-arrow bounces the bird a little bit up. The game ends if the bird hits any pipes or bottom of the screen. More info at <https://en.wikipedia.org/wiki/Flappy_Bird>. A little side note, the game was earning 50,000$ a day as a free mobile game!!! That is just crazy!

## How to start ##

Do a normal clone of the git repo to your wanted folder (the command is "git clone git@github.com:Tseipii89/ot-harjoitustyo.git").

Then use your terminal to navigate to the root of the just cloned program and run mvn compile exec:java -Dexec.mainClass=Main


## School project ##

[Hours Sheet](/dokumentointi/hourSheet.md)

[Requirements analysis](/dokumentointi/vaatimusmaarittely.md)

[Architecture Definition](/dokumentointi/architectureDefinition.md)


### Viikko 3 Tehtävät asiat ###

* ~Repostitorion juuresta löytyy Maven-projekti~
* ~Projektin koodin pystyy suorittamaan NetBeansin vihreällä napilla tai/ja komennolla mvn compile exec:java -Dexec.mainClass=pakkaus.Paaohjelma~
   * ~Projektin koodin suorittamisesta ohje README:hin~
   * ~Linkkit TravisCI:hin suorittamisen onnistumisen varmistamiseksi~
* ~Projekti toteuttaa ainakin osan jostain viikolla 2 tekemäsi määrittelydokumentin toiminnallisuudesta~
   * ~Lintu hyppii ruudulla ylöspäin, kun klikkaa ylöspäin nuolta.~ 
   * ~Lintu voi tippua alareunan läpi.~ 
   * ~Lintu ei mene yläreunana yli.~
* Sovelluksella on oltava vähintään yksi testi jonka voi suorittaa komennolla mvn test <-- Testit yllä olevalle toiminnallisuudelle.
* Sovellukselle tulee pystyä generoimaan testikattavuusraportti komennolla mvn test jacoco:report
   * Lisää jacoco badge githubiin
* ~README kunnossa~
   * ~Arkkitehtuurikuvausksen raakaversio, joka elää~
   * ~README:hin kuvaus Flappybirdistä.~
   * ~Linkit laskareihin poistettu~
* ~Repositorio siisti~
   * ~.gitignore kunnosa: ei ylimääräistä tavaraa (mm. hakemistoa target/ tai tietokantatiedostoja)~
