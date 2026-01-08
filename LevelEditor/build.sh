#!/bin/bash
# Script pour compiler l'éditeur de niveaux

# Télécharger Gson si nécessaire
if [ ! -f "gson-2.10.1.jar" ]; then
    wget https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
fi

# Compiler
javac -cp ".:gson-2.10.1.jar" src/com/leilie/leveleditor/LevelEditor.java -d bin/

# Créer le JAR
cd bin
jar cvfe LevelEditor.jar com.leilie.leveleditor.LevelEditor com/leilie/leveleditor/*.class
cd ..

echo "Éditeur compilé: bin/LevelEditor.jar"
echo "Pour lancer: java -cp 'bin/LevelEditor.jar:gson-2.10.1.jar' com.leilie.leveleditor.LevelEditor"
