# Platform Game - Jeu de Plateforme Android

## Description
Jeu de plateforme 2D pour Android avec contrôles par capteurs :
- **Gyroscope** : Pencher le téléphone à gauche/droite pour déplacer le personnage
- **Microphone** : Crier pour sauter (plus fort = plus haut)

## Fonctionnalités
- Personnage : boule bleue avec des yeux
- Menu principal avec démarrage et sélection de niveau
- Système de pause/reprise/recommencer
- Collecte de pièces
- Ennemis et obstacles
- Conditions de victoire/défaite
- Format paysage

## Structure du Projet

### Application Android (PlatformGame/)
- `MainActivity.java` : Menu principal
- `GameActivity.java` : Activité de jeu
- `GameView.java` : Rendu 2D du jeu
- `GameEngine.java` : Logique du jeu
- `Player.java` : Personnage joueur
- `Level.java` : Structure des niveaux
- `SensorManager.java` : Gestion du gyroscope
- `AudioManager.java` : Gestion du microphone
- `LevelLoader.java` : Chargement des niveaux

### Éditeur de Niveaux (LevelEditor/)
Application desktop Java Swing pour créer des niveaux personnalisés.

## Compilation et Installation

### 1. Compiler l'application Android

```bash
cd PlatformGame
./gradlew assembleDebug
```

L'APK sera généré dans : `app/build/outputs/apk/debug/app-debug.apk`

### 2. Installer l'APK sur Android

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

Ou transférez le fichier APK sur votre téléphone et installez-le manuellement.

### 3. Compiler l'éditeur de niveaux

```bash
cd LevelEditor
chmod +x build.sh
./build.sh
```

### 4. Lancer l'éditeur

```bash
java -cp 'bin/LevelEditor.jar:gson-2.10.1.jar' com.leilie.leveleditor.LevelEditor
```

## Utilisation de l'Éditeur de Niveaux

1. Sélectionnez un outil dans le menu déroulant
2. Cliquez ou glissez sur le canvas pour placer des éléments :
   - **Départ** : Position initiale du joueur
   - **Arrivée** : Objectif à atteindre
   - **Plateforme** : Glissez pour créer une plateforme
   - **Ennemi** : Glissez pour créer un ennemi
   - **Pièce** : Cliquez pour placer une pièce
   - **Supprimer** : Cliquez sur un élément pour le supprimer
3. Cliquez sur "Sauvegarder" pour exporter le niveau en JSON
4. Placez le fichier JSON dans `PlatformGame/app/src/main/assets/`

## Tests

Exécuter les tests unitaires :

```bash
cd PlatformGame
./gradlew test
```

Tests inclus :
- `GameEngineTest` : Tests du moteur de jeu
- `PlayerTest` : Tests du personnage
- `LevelTest` : Tests des structures de niveau

## Permissions Requises

- `RECORD_AUDIO` : Pour détecter les cris via le microphone
- Capteur gyroscope : Pour les contrôles de mouvement

## Configuration Minimale

- Android 7.0 (API 24) ou supérieur
- Gyroscope requis
- Microphone requis

## Notes Techniques

- Le jeu tourne à ~60 FPS (16ms par frame)
- La physique utilise une gravité simple
- Les collisions sont basées sur des formes géométriques
- Les niveaux sont stockés au format JSON

## Développement

Pour modifier le jeu :
1. Ouvrez le projet `PlatformGame` dans Android Studio
2. Modifiez les fichiers Java selon vos besoins
3. Testez avec `./gradlew test`
4. Compilez avec `./gradlew assembleDebug`

## Dépannage

**Le gyroscope ne fonctionne pas** : Vérifiez que votre appareil possède un gyroscope

**Le microphone ne détecte pas les sons** : Accordez la permission RECORD_AUDIO dans les paramètres

**L'APK ne s'installe pas** : Activez "Sources inconnues" dans les paramètres de sécurité

## Licence

Projet éducatif - Libre d'utilisation
