# Architecture definition

## Structure (WIP)

Application follows [Three-tier](https://en.wikipedia.org/wiki/Multitier_architecture) architecture and the structure (and classes) are as follows:

<img src="/documents/images/layer-architecture.png">

Package _flappyBird.ui_ handles the interface using JavaFX 

_flappyBird.domain_ handles the app logic 

_flappyBird.dao_ handles the persistence saving of data

### Initialization of a new game ###

Below is an sequence graphic of the initialization of a new game. It has been simplified a little bit to only account the necessary information about game initialization (for example new Image and new FIle are not given their own vertical objects).

<img src="/documents/images/Sequence-initialization.png">
