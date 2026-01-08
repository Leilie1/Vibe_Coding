.PHONY: help setup build install test clean editor

help:
	@echo "Platform Game - Commandes disponibles:"
	@echo ""
	@echo "  make setup    - Configure l'environnement et compile tout"
	@echo "  make build    - Compile l'APK Android"
	@echo "  make install  - Installe l'APK sur le téléphone connecté"
	@echo "  make test     - Exécute les tests unitaires"
	@echo "  make editor   - Lance l'éditeur de niveaux"
	@echo "  make clean    - Nettoie les fichiers de build"
	@echo ""

setup:
	@./setup_and_build.sh

build:
	@cd PlatformGame && ./gradlew assembleDebug

install:
	@adb install -r PlatformGame/app/build/outputs/apk/debug/app-debug.apk

test:
	@cd PlatformGame && ./gradlew test

editor:
	@cd LevelEditor && java -cp 'bin:gson-2.10.1.jar' com.leilie.leveleditor.LevelEditor

clean:
	@cd PlatformGame && ./gradlew clean
	@rm -rf LevelEditor/bin/*
	@echo "Nettoyage terminé"
