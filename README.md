# FlappyBird #

## Harjoitustyö ##

[Tuntikirjanpito](/dokumentointi/tuntikirjanpito.md)

[Vaatimusmäärittely](/dokumentointi/vaatimusmaarittely.md)

### Viikko 2 Tehtävät asiat ###

* ~Alustavan tiedon keräys~
* ~Aiheen valinta~
* ~Vaatimusmäärittelyn kirjoittaminen~

### Viikko 3 Tehtävät asiat ###

* Repostitorion juuresta löytyy Maven-projekti
* Projektin koodin pystyy suorittamaan NetBeansin vihreällä napilla tai/ja komennolla mvn compile exec:java -Dexec.mainClass=pakkaus.Paaohjelma
   * Projektin koodin suorittamisesta ohje README:hin
   * Linkkit TravisCI:hin suorittamisen onnistumisen varmistamiseksi
* Projekti toteuttaa ainakin osan jostain viikolla 2 tekemäsi määrittelydokumentin toiminnallisuudesta <-- Kirjoitan tämän myöhemmin, kun olen ensin selvittänyt arkkitehtuurikuvan
* Sovelluksella on oltava vähintään yksi testi jonka voi suorittaa komennolla mvn test <-- Määritän tämän arkkitehtuurikuvan jälkeen
* Sovellukselle tulee pystyä generoimaan testikattavuusraportti komennolla mvn test jacoco:report
   * Lisää jacoco badge githubiin
* README kunnossa
   * Arkkitehtuurikuvaus
   * README:hin kuvaus Flappybirdistä.
   * ~Linkit laskareihin poistettu~
* Repositorio siisti
   * .gitignore kunnosa: ei ylimääräistä tavaraa (mm. hakemistoa target/ tai tietokantatiedostoja)