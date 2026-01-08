# 🎮 Platform Game - COMMENCEZ ICI

Bienvenue dans le projet Platform Game ! Ce fichier vous guide pour démarrer rapidement.

## 🚀 Démarrage Ultra-Rapide

### Si vous avez Android Studio installé :
```bash
1. Ouvrez Android Studio
2. File > Open > Sélectionnez le dossier "PlatformGame"
3. Build > Build APK
4. Installez l'APK sur votre téléphone
```

### Si vous préférez la ligne de commande :
```bash
# Une seule commande pour tout installer et compiler :
./setup_and_build.sh

# Puis installer sur votre téléphone :
make install
```

## 📋 Qu'est-ce que c'est ?

Un jeu de plateforme 2D pour Android inspiré de Mario Bros, avec :
- 🔵 Un personnage : une boule bleue avec des yeux
- 📱 Contrôles par capteurs :
  - **Gyroscope** : Penchez le téléphone pour avancer/reculer
  - **Microphone** : Criez pour sauter (plus fort = plus haut)
- 🎯 Objectif : Atteindre la fin du niveau en collectant des pièces
- ⚠️ Évitez les ennemis et ne tombez pas !

## 📁 Structure du Projet

```
leilie/
├── PlatformGame/          ← Application Android (le jeu)
├── LevelEditor/           ← Éditeur de niveaux (desktop)
├── START_HERE.md          ← Vous êtes ici !
├── QUICKSTART.md          ← Guide rapide
├── INSTALLATION.md        ← Guide d'installation détaillé
├── README.md              ← Documentation complète
└── Makefile               ← Commandes simplifiées
```

## 🛠️ Prérequis

### Obligatoire pour compiler :
- ☕ Java JDK 11+ : `sudo apt install openjdk-11-jdk`
- 📱 Android SDK : Installez Android Studio OU les command-line tools

### Pour jouer :
- 📱 Téléphone Android 7.0+
- 🔄 Gyroscope (requis)
- 🎤 Microphone (requis)

## 📖 Documentation

| Fichier | Description |
|---------|-------------|
| **START_HERE.md** | Ce fichier - Vue d'ensemble |
| **QUICKSTART.md** | Guide de démarrage rapide |
| **INSTALLATION.md** | Installation détaillée pas à pas |
| **README.md** | Documentation technique complète |

## 🎯 Étapes Recommandées

### 1️⃣ Installation (Première fois)
```bash
# Lire le guide d'installation
cat INSTALLATION.md

# Ou lancer l'installation automatique
./setup_and_build.sh
```

### 2️⃣ Compilation
```bash
# Compiler l'APK
make build

# Ou avec Gradle directement
cd PlatformGame
./gradlew assembleDebug
```

### 3️⃣ Installation sur téléphone
```bash
# Via ADB (câble USB)
make install

# Ou manuellement :
# 1. Copiez PlatformGame/app/build/outputs/apk/debug/app-debug.apk
# 2. Transférez sur votre téléphone
# 3. Installez depuis le gestionnaire de fichiers
```

### 4️⃣ Jouer !
```
1. Ouvrez "Platform Game" sur votre téléphone
2. Tenez-le en mode paysage
3. Cliquez sur "Démarrer"
4. Penchez pour bouger, criez pour sauter !
```

## 🎨 Créer vos Propres Niveaux

```bash
# Lancer l'éditeur
make editor

# Ou manuellement :
cd LevelEditor
./build.sh
java -cp 'bin/LevelEditor.jar:gson-2.10.1.jar' com.leilie.leveleditor.LevelEditor
```

Dans l'éditeur :
1. Sélectionnez un outil (Départ, Arrivée, Plateforme, Ennemi, Pièce)
2. Cliquez ou glissez sur le canvas
3. Sauvegardez en JSON
4. Copiez dans `PlatformGame/app/src/main/assets/`
5. Recompilez l'APK

## 🧪 Tests

```bash
# Exécuter tous les tests
make test

# Ou avec Gradle
cd PlatformGame
./gradlew test
```

Tests inclus :
- ✅ GameEngineTest : Logique du jeu
- ✅ PlayerTest : Physique du personnage
- ✅ LevelTest : Structure des niveaux
- ✅ LevelLoaderTest : Chargement des niveaux

## 🎮 Contrôles du Jeu

| Action | Contrôle |
|--------|----------|
| Avancer | Pencher le téléphone à droite |
| Reculer | Pencher le téléphone à gauche |
| Sauter | Crier dans le microphone |
| Pause | Bouton "Pause" en haut à droite |

## 🔧 Commandes Make Disponibles

```bash
make help      # Affiche l'aide
make setup     # Configure et compile tout
make build     # Compile l'APK
make install   # Installe sur le téléphone
make test      # Exécute les tests
make editor    # Lance l'éditeur de niveaux
make clean     # Nettoie les builds
```

## ❓ Problèmes Courants

### "ANDROID_HOME not set"
```bash
export ANDROID_HOME=$HOME/Android/Sdk
echo 'export ANDROID_HOME=$HOME/Android/Sdk' >> ~/.bashrc
```

### "Permission denied"
```bash
chmod +x setup_and_build.sh
chmod +x PlatformGame/gradlew
```

### Le gyroscope ne fonctionne pas
- Vérifiez que votre téléphone a un gyroscope
- Testez avec une autre app de gyroscope

### Le microphone ne détecte rien
- Accordez la permission dans les paramètres de l'app
- Criez plus fort ! 😄

## 📞 Besoin d'Aide ?

1. Consultez **INSTALLATION.md** pour les problèmes d'installation
2. Lisez **QUICKSTART.md** pour un guide rapide
3. Voir **README.md** pour la documentation technique complète

## 🎉 C'est Parti !

Vous êtes prêt à commencer ! Choisissez votre méthode :

**Méthode Simple (Android Studio)** :
1. Ouvrez Android Studio
2. Ouvrez le projet PlatformGame
3. Cliquez sur Build APK
4. Installez et jouez !

**Méthode Ligne de Commande** :
```bash
./setup_and_build.sh  # Tout en une commande
make install          # Installer sur le téléphone
```

**Juste Jouer (APK pré-compilé)** :
1. Récupérez app-debug.apk
2. Transférez sur votre téléphone
3. Installez et jouez !

---

**Bon jeu ! 🎮🎉**
