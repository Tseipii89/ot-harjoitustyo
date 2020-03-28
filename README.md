# FlappyBird #

## Harjoitustyö ##

[Hours Sheet](/dokumentointi/hourSheet.md)

[Requirements analysis](/dokumentointi/vaatimusmaarittely.md)

[Architecture Definition](/dokumentointi/architectureDefinition.md)


### Viikko 3 Tehtävät asiat ###

* Repostitorion juuresta löytyy Maven-projekti
* Projektin koodin pystyy suorittamaan NetBeansin vihreällä napilla tai/ja komennolla mvn compile exec:java -Dexec.mainClass=pakkaus.Paaohjelma
   * Projektin koodin suorittamisesta ohje README:hin
   * Linkkit TravisCI:hin suorittamisen onnistumisen varmistamiseksi
* ~Projekti toteuttaa ainakin osan jostain viikolla 2 tekemäsi määrittelydokumentin toiminnallisuudesta~
   * ~Lintu hyppii ruudulla ylöspäin, kun klikkaa ylöspäin nuolta.~ 
   * ~Lintu voi tippua alareunan läpi.~ 
   * ~Lintu ei mene yläreunana yli.~
* Sovelluksella on oltava vähintään yksi testi jonka voi suorittaa komennolla mvn test <-- Testit yllä olevalle toiminnallisuudelle.
* Sovellukselle tulee pystyä generoimaan testikattavuusraportti komennolla mvn test jacoco:report
   * Lisää jacoco badge githubiin
* README kunnossa
   * ~Arkkitehtuurikuvausksen raakaversio, joka elää~
   * README:hin kuvaus Flappybirdistä.
   * ~Linkit laskareihin poistettu~
* ~Repositorio siisti~
   * ~.gitignore kunnosa: ei ylimääräistä tavaraa (mm. hakemistoa target/ tai tietokantatiedostoja)~