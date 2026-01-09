# Deuxième Rapport d'Avancement du Projet Platform Game

**Date :** 8 Janvier 2026

## Résumé des Mises à Jour

Depuis le dernier rapport, nous nous sommes concentrés sur l'amélioration du "Game Feel" (sensations de jeu) en ajoutant du feedback haptique et en ajustant les contrôles pour une meilleure réactivité.

## Nouvelles Fonctionnalités & Ajustements

### 1. Retour Haptique (Vibrations)
L'immersion a été renforcée par l'ajout de vibrations contextuelles :

- **Collecte de pièce** : Une vibration courte et légère (50ms).
- **Défaite** : Deux vibrations distinctes pour marquer l'échec.
- **Victoire** : Trois vibrations joyeuses pour célébrer la réussite.

*Implémentation technique : Utilisation du service `Vibrator` d'Android, intégré dans `GameEngine` via une interface callback vers `GameActivity`.*

### 2. Sensibilité du Microphone
La mécanique de saut (crier pour sauter) a été recalibrée pour être moins exigeante.

- **Modification** : Augmentation de la sensibilité par un facteur de 3.
- **Détail** : Le seuil de déclenchement dans `AudioManager` a été ajusté (diviseur passé de 3000f à 1000f).
- **Résultat** : Le saut est plus facile à déclencher sans avoir besoin de crier trop fort.

### 3. Physique du Joueur
- **Friction** : Nous avons confirmé l'absence de friction horizontale sur le joueur.
- **Comportement** : Le joueur ne ralentit pas automatiquement lorsqu'il est au sol (comportement type "glisse" ou "inertie conservée"), ce qui est le comportement voulu pour ce type de contrôle au gyroscope.

### 4. Système de Mémoire Partagée
- **Objectif** : Assurer la continuité du contexte de développement entre différentes sessions ou machines.
- **Mise en place** : Création du fichier `PROJECT_MEMORY.md` à la racine du projet.
- **Utilisation** : Ce fichier centralise les décisions techniques, les changements récents et le contexte global, permettant à l'agent de maintenir une cohérence à long terme.

## Fichiers Impactés

Les modifications récentes ont touché les fichiers suivants :

- **`PlatformGame/app/src/main/AndroidManifest.xml`** : Ajout de la permission `android.permission.VIBRATE`.
- **`PlatformGame/app/src/main/java/com/leilie/platformgame/GameActivity.java`** : Implémentation de la logique de vibration.
- **`PlatformGame/app/src/main/java/com/leilie/platformgame/GameEngine.java`** : Déclenchement des callbacks de vibration lors des événements de jeu.
- **`PlatformGame/app/src/main/java/com/leilie/platformgame/AudioManager.java`** : Ajustement de la sensibilité.
- **`PlatformGame/app/src/test/java/com/leilie/platformgame/GameEngineTest.java`** : Mise à jour des tests pour supporter l'interface de callback (mock).

## État Actuel du Projet

Le projet est toujours **stable et fonctionnel**. L'ajout des vibrations et l'ajustement du micro ne remettent pas en cause l'architecture existante. Tous les tests unitaires (22) passent toujours (avec les ajustements nécessaires effectués sur les mocks).

## Améliorations Possibles

La liste des améliorations futures :

- Tester le ressenti "in-hand" des vibrations sur différents appareils.
- Ajouter des effets sonores (SFX) pour accompagner les vibrations (saut, pièce, fin).
- Ajouter une musique de fond.
- Créer de nouveaux niveaux plus complexes pour exploiter la mécanique de saut ajustée.
- Mise en place d'un fichier de restriction pour contrôler un peu plus les modifications de l'agent
