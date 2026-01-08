#!/bin/bash
# Script d'installation et de compilation automatique

set -e

echo "======================================"
echo "  Platform Game - Setup & Build"
echo "======================================"
echo ""

# Couleurs
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Vérifier Java
echo -n "Vérification de Java... "
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2)
    echo -e "${GREEN}OK${NC} (version $JAVA_VERSION)"
else
    echo -e "${RED}ERREUR${NC}"
    echo "Java n'est pas installé. Installez-le avec:"
    echo "  sudo apt install openjdk-11-jdk"
    exit 1
fi

# Détecter Android SDK
echo -n "Recherche d'Android SDK... "
if [ -n "$ANDROID_HOME" ]; then
    SDK_PATH="$ANDROID_HOME"
    echo -e "${GREEN}Trouvé${NC} ($SDK_PATH)"
elif [ -d "$HOME/Android/Sdk" ]; then
    SDK_PATH="$HOME/Android/Sdk"
    export ANDROID_HOME="$SDK_PATH"
    echo -e "${YELLOW}Trouvé${NC} ($SDK_PATH)"
    echo "export ANDROID_HOME=$SDK_PATH" >> ~/.bashrc
elif [ -d "/usr/lib/android-sdk" ]; then
    SDK_PATH="/usr/lib/android-sdk"
    export ANDROID_HOME="$SDK_PATH"
    echo -e "${YELLOW}Trouvé${NC} ($SDK_PATH)"
else
    echo -e "${RED}Non trouvé${NC}"
    echo ""
    echo "Android SDK n'est pas installé."
    echo ""
    echo "Options:"
    echo "1. Installer Android Studio: https://developer.android.com/studio"
    echo "2. Installer les outils en ligne de commande uniquement"
    echo ""
    echo "Après installation, définissez ANDROID_HOME:"
    echo "  export ANDROID_HOME=/chemin/vers/android/sdk"
    echo "  echo 'export ANDROID_HOME=/chemin/vers/android/sdk' >> ~/.bashrc"
    exit 1
fi

# Créer local.properties
echo -n "Configuration de local.properties... "
echo "sdk.dir=$SDK_PATH" > PlatformGame/local.properties
echo -e "${GREEN}OK${NC}"

# Compiler l'éditeur de niveaux
echo ""
echo "=== Compilation de l'éditeur de niveaux ==="
cd LevelEditor
if [ ! -d "bin" ]; then
    mkdir bin
fi

# Télécharger Gson
if [ ! -f "gson-2.10.1.jar" ]; then
    echo "Téléchargement de Gson..."
    wget -q https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
fi

echo "Compilation..."
javac -cp ".:gson-2.10.1.jar" src/com/leilie/leveleditor/LevelEditor.java -d bin/ 2>/dev/null || {
    echo -e "${YELLOW}Note:${NC} L'éditeur nécessite une compilation manuelle avec les dépendances Swing"
}

cd ..

# Compiler l'APK Android
echo ""
echo "=== Compilation de l'APK Android ==="
cd PlatformGame

echo "Nettoyage..."
./gradlew clean --quiet 2>/dev/null || ./gradlew clean

echo "Compilation (cela peut prendre quelques minutes)..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}======================================"
    echo "  COMPILATION RÉUSSIE !"
    echo -e "======================================${NC}"
    echo ""
    echo "APK généré:"
    echo "  $(pwd)/app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "Pour installer sur votre téléphone:"
    echo "  1. Activez le débogage USB"
    echo "  2. Connectez votre téléphone"
    echo "  3. Exécutez: adb install app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "Ou transférez le fichier APK sur votre téléphone et installez-le."
    echo ""
    echo "Pour lancer l'éditeur de niveaux:"
    echo "  cd LevelEditor"
    echo "  java -cp 'bin:gson-2.10.1.jar' com.leilie.leveleditor.LevelEditor"
    echo ""
else
    echo -e "${RED}======================================"
    echo "  ERREUR DE COMPILATION"
    echo -e "======================================${NC}"
    echo "Consultez les messages d'erreur ci-dessus."
    exit 1
fi
