# 🧠 Mémoire du Projet / Project Context

Ce fichier stocke les informations importantes concernant les modifications récentes et l'état du projet, pour assurer la continuité du développement sur d'autres machines.

## ⚠️ Instructions d'Utilisation

**IMPORTANT :** À chaque modification significative du contexte du projet (ajout de fonctionnalité, changement de configuration, décision de design importante) :
1. **Mettre à jour la mémoire du système** : Utiliser l'outil `save_memory` pour enregistrer le fait.
2. **Mettre à jour ce fichier (`PROJECT_MEMORY.md`)** : Ajouter une entrée décrivant la modification, la date et les fichiers impactés.

Ceci assure que le contexte est préservé à la fois pour l'agent (mémoire à long terme) et pour les développeurs humains (ce fichier).

## 📅 Modifications Récentes (Recent Changes)

### 2026-01-08
- **Sensibilité du Microphone** : La sensibilité du microphone pour le saut a été **augmentée (x3)**.
  - **Fichier modifié** : `PlatformGame/app/src/main/java/com/leilie/platformgame/AudioManager.java`
  - **Détail** : Le diviseur d'amplitude a été changé de `3000f` à `1000f`.
  - **Raison** : Pour rendre le saut plus réactif aux sons moins forts.

## 📝 Contexte Technique (Technical Context)

- **Friction (En attente)** : Une discussion précédente mentionnait l'intention de supprimer la friction dans `Player.java` (`velocityX *= 0.95f`), mais cela n'a pas encore été appliqué dans la session actuelle.

## 📌 À Faire (Todo)

- Vérifier et tester la nouvelle sensibilité du microphone sur un appareil réel.
- Considérer la suppression de la friction si le gameplay le nécessite.
