# 🎨 Guide de l'Éditeur de Niveaux

## 🚀 Lancer l'Éditeur
```
# 1. Compiler l'éditeur
./build.sh

# 2. Lancer l'éditeur
java -cp 'bin/LevelEditor.jar:gson-2.10.1.jar' com.leilie.leveleditor.LevelEditor
```

## 🎮 Utiliser l'Éditeur

### Outils disponibles :
1. **Départ** - Cliquez pour placer le point de départ du joueur (boule bleue)
2. **Arrivée** - Cliquez pour placer l'objectif (drapeau vert)
3. **Plateforme** - Glissez pour créer une plateforme (rectangle marron)
4. **Ennemi** - Glissez pour créer un ennemi (rectangle rouge)
5. **Pièce** - Cliquez pour placer une pièce (cercle jaune)
6. **Supprimer** - Cliquez sur un élément pour le supprimer

### Créer un niveau :
1. Sélectionnez "Départ" et cliquez pour placer le point de départ
2. Sélectionnez "Arrivée" et cliquez pour placer l'objectif
3. Sélectionnez "Plateforme" et glissez pour créer des plateformes
4. Ajoutez des ennemis et des pièces
5. Cliquez sur "Sauvegarder"
6. Donnez un nom à votre niveau (ex: `niveau2.json`)

## 📦 Ajouter un Niveau dans le Jeu

### Étape 1 : Créer le niveau avec l'éditeur
Sauvegardez votre niveau (ex: `niveau2.json`)

### Étape 2 : Copier le fichier dans le projet
```bash
# Copier le niveau dans le dossier assets
cp niveau2.json /home/votre_user/n7/s9/vibe_coding_2/PlatformGame/app/src/main/assets/
```

### Étape 3 : Recompiler l'APK
```bash
cd /home/votre_user/n7/s9/vibe_coding_2/PlatformGame
gradle assembleDebug
```

### Étape 4 : Réinstaller l'APK
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## 📁 Structure d'un Niveau (JSON)

```json
{
  "startX": 100.0,
  "startY": 300.0,
  "endX": 2000.0,
  "endY": 300.0,
  "platforms": [
    {"x": 0.0, "y": 400.0, "width": 500.0, "height": 50.0}
  ],
  "enemies": [
    {"x": 700.0, "y": 350.0, "width": 40.0, "height": 40.0}
  ],
  "coins": [
    {"x": 300.0, "y": 300.0, "collected": false}
  ]
}
```

## 💡 Conseils pour Créer de Bons Niveaux

1. **Départ et Arrivée** : Placez-les toujours en premier
2. **Plateformes** : Créez un chemin jouable du départ à l'arrivée
3. **Espacement** : Laissez ~150-200 pixels entre les plateformes pour les sauts
4. **Ennemis** : Placez-les sur les plateformes, pas dans le vide
5. **Pièces** : Mettez-les sur le chemin ou dans des endroits difficiles
6. **Testez** : Compilez et testez votre niveau sur le téléphone

## 🔄 Procédure Complète

```bash
# 1. Lancer l'éditeur
cd /home/leilie/n7/s9/vibe_coding_2
make editor

# 2. Créer et sauvegarder votre niveau (ex: niveau2.json)

# 3. Copier dans le projet
cp niveau2.json PlatformGame/app/src/main/assets/

# 4. Recompiler
cd PlatformGame
gradle assembleDebug

# 5. Installer
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## 📝 Niveaux Existants

- `level1.json` - Niveau par défaut (déjà dans le jeu)

Vous pouvez créer `level2.json`, `level3.json`, etc.

## ❓ Dépannage

**L'éditeur ne se lance pas** :
```bash
cd LevelEditor
chmod +x build.sh
./build.sh
```

**Erreur "gson not found"** :
```bash
cd LevelEditor
wget https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
```

**Le niveau n'apparaît pas dans le jeu** :
- Vérifiez que le fichier est bien dans `app/src/main/assets/`
- Recompilez l'APK
- Réinstallez l'APK sur le téléphone
