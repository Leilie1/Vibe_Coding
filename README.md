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

## Compilation Rapide
### En ligne de commande
```bash
# 1. Configurer ANDROID_HOME
export ANDROID_HOME=/home/votre_user/Android/Sdk

# 2. Compiler l'APK
cd /home/votre_user/n7/s9/vibe_coding
./COMPILE_NOW.sh

# 3. L'APK est généré dans :
PlatformGame/app/build/outputs/apk/debug/app-debug.apk
```

## Installation sur Android
### Transfert manuel
1. Copiez le fichier `app-debug.apk` sur votre téléphone
2. Ouvrez le fichier depuis le gestionnaire de fichiers
3. Autorisez l'installation depuis des sources inconnues si demandé
4. Installez l'application

## Utiliser l'Éditeur de Niveaux

```bash
cd LevelEditor
./build.sh
java -cp 'bin/LevelEditor.jar:gson-2.10.1.jar' com.leilie.leveleditor.LevelEditor
```

### Créer un niveau personnalisé :
1. Lancez l'éditeur
2. Utilisez les outils pour placer des éléments
3. Sauvegardez le niveau (ex: `mon_niveau.json`)
4. Copiez le fichier dans `PlatformGame/app/src/main/assets/`
5. Recompilez l'APK

## Lancer le Jeu

1. Ouvrez l'application "Platform Game" sur votre téléphone
2. Tenez le téléphone en mode paysage
3. Cliquez sur "Démarrer"
4. Penchez le téléphone pour déplacer la boule
5. Criez pour sauter !

## Contrôles

- **Pencher à droite** : Avancer
- **Pencher à gauche** : Reculer
- **Crier** : Sauter (plus fort = plus haut)
- **Bouton Pause** : Mettre en pause

## Dépannage

### "ANDROID_HOME not set"
```bash
# Trouvez votre SDK Android
find ~ -name "platform-tools" 2>/dev/null

# Définissez ANDROID_HOME (ajoutez à ~/.bashrc pour permanence)
export ANDROID_HOME=/chemin/trouvé/..
```

### "Permission denied"
```bash
chmod +x build_apk.sh
chmod +x PlatformGame/gradlew
```

### "Gradle sync failed"
- Ouvrez le projet dans Android Studio
- Cliquez sur "File" > "Sync Project with Gradle Files"

### Le gyroscope ne fonctionne pas
- Vérifiez que votre téléphone a un gyroscope
- Testez avec une autre application de gyroscope

### Le microphone ne détecte rien
- Accordez la permission microphone dans les paramètres de l'app
- Testez avec une autre application d'enregistrement

## Tests

```bash
cd PlatformGame
./gradlew test
```

## Structure des Fichiers

```
leilie/
├── PlatformGame/          # Application Android
│   ├── app/
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/          # Code source
│   │   │   │   ├── res/           # Ressources
│   │   │   │   └── assets/        # Niveaux JSON
│   │   │   └── test/              # Tests unitaires
│   │   └── build.gradle
│   └── build/
│       └── outputs/apk/debug/     # APK généré ici
├── LevelEditor/           # Éditeur de niveaux
│   ├── src/
│   └── bin/
└── README.md
```

## Workflow

Afin d'assurer la continuité du contexte de développement entre différentes sessions ou machines pour un travail en groupe optimisé.
Le fichier `PROJECT_MEMORY.md` stocke les informations importantes concernant les modifications récentes et l'état du projet.
En complément, l'utilisation de Git assure la traçabilité du contexte, permettant une restauration rapide vers un état fonctionnel en cas de régression.

