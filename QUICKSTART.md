# Guide de Démarrage Rapide

## Prérequis

### Pour compiler l'application Android :
1. **Android SDK** (obligatoire)
   - Téléchargez Android Studio : https://developer.android.com/studio
   - Ou installez uniquement les outils en ligne de commande
   - Définissez la variable d'environnement : `export ANDROID_HOME=/chemin/vers/android/sdk`

2. **Java JDK 11 ou supérieur**
   ```bash
   sudo apt install openjdk-11-jdk  # Ubuntu/Debian
   ```

### Pour l'éditeur de niveaux :
- Java JDK 8 ou supérieur

## Compilation Rapide

### Option 1 : Avec Android Studio (Recommandé)
1. Ouvrez Android Studio
2. Cliquez sur "Open Project"
3. Sélectionnez le dossier `PlatformGame`
4. Attendez la synchronisation Gradle
5. Cliquez sur "Build" > "Build Bundle(s) / APK(s)" > "Build APK(s)"
6. L'APK sera dans `app/build/outputs/apk/debug/app-debug.apk`

### Option 2 : En ligne de commande
```bash
# 1. Configurer ANDROID_HOME
export ANDROID_HOME=/home/votre_user/Android/Sdk

# 2. Compiler l'APK
cd /home/leilie/n7/s9/vibe_coding_2
./build_apk.sh

# 3. L'APK est généré dans :
# PlatformGame/app/build/outputs/apk/debug/app-debug.apk
```

## Installation sur Android

### Méthode 1 : Via ADB (câble USB)
```bash
# Activer le débogage USB sur votre téléphone
# Connecter le téléphone via USB
adb install PlatformGame/app/build/outputs/apk/debug/app-debug.apk
```

### Méthode 2 : Transfert manuel
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

## Support

Pour plus d'informations, consultez le fichier README.md complet.
