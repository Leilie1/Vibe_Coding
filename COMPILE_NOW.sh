#!/bin/bash
# Script de compilation rapide - Tout en un

set -e

clear
echo "╔════════════════════════════════════════════════════════╗"
echo "║     PLATFORM GAME - COMPILATION RAPIDE                ║"
echo "╚════════════════════════════════════════════════════════╝"
echo ""

# Couleurs
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

# Fonction pour afficher les étapes
step() {
    echo -e "${BLUE}▶${NC} $1"
}

success() {
    echo -e "${GREEN}✓${NC} $1"
}

error() {
    echo -e "${RED}✗${NC} $1"
}

warning() {
    echo -e "${YELLOW}⚠${NC} $1"
}

# Vérifier Java
step "Vérification de Java..."
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2)
    success "Java trouvé : $JAVA_VERSION"
else
    error "Java n'est pas installé"
    echo ""
    echo "Installez Java avec :"
    echo "  sudo apt install openjdk-11-jdk"
    exit 1
fi

# Détecter Android SDK
step "Recherche d'Android SDK..."
SDK_FOUND=false

if [ -n "$ANDROID_HOME" ] && [ -d "$ANDROID_HOME" ]; then
    success "Android SDK trouvé : $ANDROID_HOME"
    SDK_FOUND=true
elif [ -d "$HOME/Android/Sdk" ]; then
    export ANDROID_HOME="$HOME/Android/Sdk"
    success "Android SDK trouvé : $ANDROID_HOME"
    SDK_FOUND=true
elif [ -d "/usr/lib/android-sdk" ]; then
    export ANDROID_HOME="/usr/lib/android-sdk"
    success "Android SDK trouvé : $ANDROID_HOME"
    SDK_FOUND=true
fi

if [ "$SDK_FOUND" = false ]; then
    error "Android SDK non trouvé"
    echo ""
    echo "Vous devez installer Android SDK :"
    echo ""
    echo "Option 1 - Android Studio (Recommandé) :"
    echo "  1. Téléchargez : https://developer.android.com/studio"
    echo "  2. Installez et lancez Android Studio"
    echo "  3. Suivez l'assistant d'installation"
    echo "  4. Le SDK sera dans ~/Android/Sdk"
    echo ""
    echo "Option 2 - Command-line tools :"
    echo "  Consultez INSTALLATION.md pour les instructions"
    echo ""
    echo "Puis définissez ANDROID_HOME :"
    echo "  export ANDROID_HOME=\$HOME/Android/Sdk"
    echo "  echo 'export ANDROID_HOME=\$HOME/Android/Sdk' >> ~/.bashrc"
    echo ""
    exit 1
fi

# Créer local.properties
step "Configuration de local.properties..."
echo "sdk.dir=$ANDROID_HOME" > PlatformGame/local.properties
success "Configuration créée"

# Vérifier les permissions
step "Vérification des permissions..."
chmod +x PlatformGame/gradlew 2>/dev/null || true
success "Permissions OK"

# Compilation
echo ""
echo "╔════════════════════════════════════════════════════════╗"
echo "║     COMPILATION EN COURS...                           ║"
echo "╚════════════════════════════════════════════════════════╝"
echo ""

cd PlatformGame

# Nettoyer
step "Nettoyage des builds précédents..."
./gradlew clean --quiet 2>/dev/null || ./gradlew clean
success "Nettoyage terminé"

# Compiler
step "Compilation de l'APK (cela peut prendre 2-5 minutes)..."
echo ""

if ./gradlew assembleDebug; then
    echo ""
    echo "╔════════════════════════════════════════════════════════╗"
    echo "║     ✓ COMPILATION RÉUSSIE !                          ║"
    echo "╚════════════════════════════════════════════════════════╝"
    echo ""
    
    APK_PATH="$(pwd)/app/build/outputs/apk/debug/app-debug.apk"
    
    if [ -f "$APK_PATH" ]; then
        APK_SIZE=$(du -h "$APK_PATH" | cut -f1)
        success "APK généré : $APK_PATH"
        success "Taille : $APK_SIZE"
        echo ""
        
        echo "╔════════════════════════════════════════════════════════╗"
        echo "║     PROCHAINES ÉTAPES                                 ║"
        echo "╚════════════════════════════════════════════════════════╝"
        echo ""
        echo "📱 INSTALLER SUR VOTRE TÉLÉPHONE :"
        echo ""
        echo "Méthode 1 - Via ADB (câble USB) :"
        echo "  1. Activez le débogage USB sur votre téléphone"
        echo "  2. Connectez le téléphone via USB"
        echo "  3. Exécutez : adb install $APK_PATH"
        echo ""
        echo "Méthode 2 - Transfert manuel :"
        echo "  1. Copiez le fichier APK sur votre téléphone"
        echo "  2. Ouvrez-le depuis le gestionnaire de fichiers"
        echo "  3. Autorisez l'installation depuis des sources inconnues"
        echo "  4. Installez l'application"
        echo ""
        echo "🎮 JOUER :"
        echo "  1. Ouvrez 'Platform Game' sur votre téléphone"
        echo "  2. Tenez le téléphone en mode paysage"
        echo "  3. Penchez pour bouger, criez pour sauter !"
        echo ""
        echo "🎨 CRÉER DES NIVEAUX :"
        echo "  cd ../LevelEditor"
        echo "  ./build.sh"
        echo "  java -cp 'bin/LevelEditor.jar:gson-2.10.1.jar' com.leilie.leveleditor.LevelEditor"
        echo ""
        
        # Vérifier si ADB est disponible
        if command -v adb &> /dev/null; then
            echo "💡 ADB détecté ! Pour installer rapidement :"
            echo "   adb install -r $APK_PATH"
            echo ""
        fi
        
    else
        warning "APK non trouvé à l'emplacement attendu"
    fi
    
else
    echo ""
    echo "╔════════════════════════════════════════════════════════╗"
    echo "║     ✗ ERREUR DE COMPILATION                          ║"
    echo "╚════════════════════════════════════════════════════════╝"
    echo ""
    error "La compilation a échoué"
    echo ""
    echo "Consultez les messages d'erreur ci-dessus."
    echo ""
    echo "Solutions courantes :"
    echo "  1. Vérifiez que ANDROID_HOME est correct"
    echo "  2. Assurez-vous d'avoir Java 11+"
    echo "  3. Essayez : ./gradlew clean build"
    echo "  4. Consultez INSTALLATION.md pour plus d'aide"
    echo ""
    exit 1
fi
