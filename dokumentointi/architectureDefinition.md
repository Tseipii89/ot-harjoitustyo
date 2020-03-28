# Architecture definition

## Structure (WIP)

Application follows [Three-tier](https://en.wikipedia.org/wiki/Multitier_architecture) architecture and the structure (and classes) are as follows:

<img src="/dokumentointi/images/Layer-architecture.png">

Package _flappyBird.ui_ handles the interface using JavaFX 

_flappyBird.domain_ handles the app logic 

_flappyBird.dao_ handles the persistence saving of data
