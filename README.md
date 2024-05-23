# PIEDS (Projet Informatique Encadré Dorian Sacha)

Le but du projet était de créer une application Java en respectant les designs patterns MVC et Observer/Observable. Le jeu est un Sokoban, un jeu de réflexion où le joueur doit déplacer des caisses pour les placer sur des objectifs.

## Auteurs

- [Sacha Terrasson](https://github.com/Fireboss05)
- [Dorian Tonnis](https://github.com/Dorian-T)

## Fonctionnalités

- Lecture de niveaux depuis un fichier
- Menu de sélection de niveaux
- Déplacement du joueur et collisions
- Objets implémentés : joueur, caisses/objectifs, glace, sol fragile/feu, rails, interrupteurs/portes, téléporteurs 
- Gestion de plusieurs couleurs pour les objets
- Réinitialisation du niveau
- Animation des déplacements et des objets

## Crédits

Le concept du jeu et les niveaux proviennent de [sokobanonline.com](https://www.sokobanonline.com/play/tutorials).\
Les assets sont tirés du jeu Baba Is You.

## Documentation Javadoc

Pour créer la javadoc, lancez la commande :
```bash
cd src/; javadoc -encoding UTF-8 -d ../doc -subpackages main; cd ..
```