# Guide d'Installation Détaillé

## Méthode 1 : Installation Automatique (Recommandée)

### Prérequis
- Linux (Ubuntu/Debian recommandé)
- Connexion Internet
- 2 GB d'espace disque libre

### Étapes

1. **Installer Java (si pas déjà installé)**
```bash
sudo apt update
sudo apt install openjdk-11-jdk
```

2. **Installer Android SDK**

Option A - Android Studio (Interface graphique) :
```bash
# Télécharger depuis https://developer.android.com/studio
# Ou via snap:
sudo snap install android-studio --classic

# Lancer Android Studio et suivre l'assistant d'installation
# Le SDK sera installé dans ~/Android/Sdk
```

Option B - Outils en ligne de commande uniquement :
```bash
# Télécharger les command-line tools
cd ~
wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip
unzip commandlinetools-linux-9477386_latest.zip -d Android
cd Android/cmdline-tools
mkdir latest
mv bin lib NOTICE.txt source.properties latest/

# Installer les packages nécessaires
cd latest/bin
./sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"

# Définir ANDROID_HOME
echo 'export ANDROID_HOME=$HOME/Android' >> ~/.bashrc
echo 'export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools' >> ~/.bashrc
source ~/.bashrc
```

3. **Compiler le projet**
```bash
cd /home/leilie/n7/s9/vibe_coding_2
./setup_and_build.sh
```

4. **Installer sur votre téléphone**
```bash
# Activer le débogage USB sur votre téléphone:
# Paramètres > À propos du téléphone > Appuyez 7 fois sur "Numéro de build"
# Paramètres > Options de développement > Débogage USB (activer)

# Connecter le téléphone via USB
adb devices  # Vérifier que le téléphone est détecté

# Installer l'APK
make install
# ou
adb install PlatformGame/app/build/outputs/apk/debug/app-debug.apk
```

## Méthode 2 : Avec Android Studio

1. **Installer Android Studio**
   - Télécharger depuis https://developer.android.com/studio
   - Installer et lancer l'assistant de configuration

2. **Ouvrir le projet**
   - File > Open
   - Sélectionner `/home/leilie/n7/s9/vibe_coding_2/PlatformGame`
   - Attendre la synchronisation Gradle

3. **Compiler**
   - Build > Build Bundle(s) / APK(s) > Build APK(s)
   - Attendre la fin de la compilation

4. **Installer**
   - Connecter votre téléphone via USB
   - Run > Run 'app'
   - Ou installer manuellement l'APK généré

## Méthode 3 : Sans Android SDK (Pré-compilé)

Si vous ne pouvez pas installer Android SDK, demandez à quelqu'un de compiler l'APK pour vous :

1. Récupérez le fichier `app-debug.apk`
2. Transférez-le sur votre téléphone Android
3. Ouvrez le fichier depuis le gestionnaire de fichiers
4. Autorisez l'installation depuis des sources inconnues
5. Installez l'application

## Vérification de l'Installation

### Vérifier Java
```bash
java -version
# Devrait afficher: openjdk version "11.x.x" ou supérieur
```

### Vérifier Android SDK
```bash
echo $ANDROID_HOME
# Devrait afficher: /home/user/Android/Sdk ou similaire

$ANDROID_HOME/platform-tools/adb version
# Devrait afficher la version d'ADB
```

### Vérifier la compilation
```bash
cd /home/leilie/n7/s9/vibe_coding_2/PlatformGame
./gradlew tasks
# Devrait lister les tâches Gradle disponibles
```

## Résolution de Problèmes

### Erreur: "ANDROID_HOME is not set"
```bash
# Trouver où est installé le SDK
find ~ -name "platform-tools" 2>/dev/null

# Définir ANDROID_HOME (remplacer par votre chemin)
export ANDROID_HOME=/home/user/Android/Sdk
echo 'export ANDROID_HOME=/home/user/Android/Sdk' >> ~/.bashrc
```

### Erreur: "SDK location not found"
```bash
# Créer le fichier local.properties
echo "sdk.dir=$ANDROID_HOME" > PlatformGame/local.properties
```

### Erreur: "Gradle sync failed"
```bash
# Nettoyer et réessayer
cd PlatformGame
./gradlew clean
./gradlew assembleDebug
```

### Erreur: "Permission denied"
```bash
# Rendre les scripts exécutables
chmod +x setup_and_build.sh
chmod +x build_apk.sh
chmod +x PlatformGame/gradlew
```

### Le téléphone n'est pas détecté par ADB
```bash
# Vérifier les règles udev (Linux)
sudo apt install android-tools-adb
sudo usermod -aG plugdev $USER

# Redémarrer le serveur ADB
adb kill-server
adb start-server
adb devices
```

### Erreur de compilation Gradle
```bash
# Vérifier la version de Java
java -version  # Doit être 11 ou supérieur

# Nettoyer le cache Gradle
rm -rf ~/.gradle/caches
cd PlatformGame
./gradlew clean build --refresh-dependencies
```

## Configuration Minimale Requise

### Pour la compilation :
- OS : Linux, macOS, ou Windows
- RAM : 4 GB minimum, 8 GB recommandé
- Espace disque : 5 GB pour Android SDK + 500 MB pour le projet
- Java : JDK 11 ou supérieur

### Pour l'exécution sur téléphone :
- Android 7.0 (API 24) ou supérieur
- Gyroscope (requis)
- Microphone (requis)
- 50 MB d'espace libre

## Commandes Utiles

```bash
# Compiler l'APK
make build

# Installer sur le téléphone
make install

# Exécuter les tests
make test

# Lancer l'éditeur de niveaux
make editor

# Nettoyer les builds
make clean

# Tout configurer et compiler
make setup
```

## Support

Si vous rencontrez des problèmes :
1. Consultez la section "Résolution de Problèmes" ci-dessus
2. Vérifiez les logs de compilation
3. Assurez-vous que toutes les dépendances sont installées
4. Vérifiez que ANDROID_HOME est correctement défini

## Prochaines Étapes

Après l'installation réussie :
1. Lisez le fichier QUICKSTART.md pour apprendre à utiliser le jeu
2. Consultez README.md pour la documentation complète
3. Essayez l'éditeur de niveaux pour créer vos propres niveaux
