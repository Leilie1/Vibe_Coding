package com.leilie.platformgame;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameEngineTest {
    private GameEngine gameEngine;
    private Level level;

    @Before
    public void setUp() {
        level = new Level();
        level.startX = 100;
        level.startY = 100;
        level.endX = 500;
        level.endY = 100;
        level.platforms.add(new Level.Platform(0, 200, 600, 50));
        level.coins.add(new Level.Coin(150, 150));
        level.enemies.add(new Level.Enemy(300, 150, 40, 40));
        
        gameEngine = new GameEngine(level, null);
    }

    @Test
    public void testGameEngineInitialization() {
        assertNotNull(gameEngine.getPlayer());
        assertNotNull(gameEngine.getLevel());
        assertEquals(0, gameEngine.getCoinsCollected());
        assertFalse(gameEngine.isGameWon());
        assertFalse(gameEngine.isGameLost());
    }

    @Test
    public void testCoinCollection() {
        Player player = gameEngine.getPlayer();
        player.x = 150;
        player.y = 150;
        gameEngine.update();
        assertEquals(1, gameEngine.getCoinsCollected());
    }

    @Test
    public void testWinCondition() {
        Player player = gameEngine.getPlayer();
        player.x = level.endX;
        player.y = level.endY;
        gameEngine.update();
        assertTrue(gameEngine.isGameWon());
    }

    @Test
    public void testLoseConditionFall() {
        Player player = gameEngine.getPlayer();
        player.y = 2500;
        gameEngine.update();
        assertTrue(gameEngine.isGameLost());
    }

    @Test
    public void testReset() {
        gameEngine.reset();
        assertEquals(0, gameEngine.getCoinsCollected());
        assertFalse(gameEngine.isGameWon());
        assertFalse(gameEngine.isGameLost());
    }
}
