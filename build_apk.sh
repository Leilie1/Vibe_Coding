#!/bin/bash
# Script pour compiler l'APK Android

echo "=== Compilation du jeu Platform Game ==="
echo ""

cd PlatformGame

# Vérifier si Android SDK est installé
if [ -z "$ANDROID_HOME" ]; then
    echo "ERREUR: ANDROID_HOME n'est pas défini"
    echo "Veuillez installer Android SDK et définir ANDROID_HOME"
    echo "Exemple: export ANDROID_HOME=/home/user/Android/Sdk"
    exit 1
fi

echo "Android SDK trouvé: $ANDROID_HOME"
echo ""

# Nettoyer les builds précédents
echo "Nettoyage..."
./gradlew clean

# Compiler l'APK
echo "Compilation de l'APK..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo ""
    echo "=== SUCCÈS ==="
    echo "APK généré: PlatformGame/app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "Pour installer sur votre téléphone:"
    echo "  adb install app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "Ou transférez le fichier APK sur votre téléphone et installez-le manuellement"
else
    echo ""
    echo "=== ERREUR ==="
    echo "La compilation a échoué. Vérifiez les messages d'erreur ci-dessus."
    exit 1
fi
