package com.leilie.platformgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    private Thread gameThread;
    private boolean isPlaying = false;
    private GameEngine gameEngine;
    private Paint paint;
    private float cameraX = 0;
    private float cameraY = 0;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public void setGameEngine(GameEngine engine) {
        this.gameEngine = engine;
    }

    public void startGame() {
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pauseGame() {
        isPlaying = false;
        try {
            if (gameThread != null) gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        if (gameEngine != null) {
            gameEngine.update();
            Player player = gameEngine.getPlayer();
            cameraX = player.x - getWidth() / 2f;
            cameraY = player.y - getHeight() / 2f;
        }
    }

    private void draw() {
        if (getHolder().getSurface().isValid() && gameEngine != null) {
            Canvas canvas = getHolder().lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.rgb(135, 206, 235));

                canvas.save();
                canvas.translate(-cameraX, -cameraY);

                Level level = gameEngine.getLevel();
                
                paint.setColor(Color.rgb(139, 69, 19));
                for (Level.Platform platform : level.platforms) {
                    canvas.drawRect(platform.x, platform.y, 
                        platform.x + platform.width, platform.y + platform.height, paint);
                }

                paint.setColor(Color.RED);
                for (Level.Enemy enemy : level.enemies) {
                    canvas.drawRect(enemy.x, enemy.y, 
                        enemy.x + enemy.width, enemy.y + enemy.height, paint);
                }

                paint.setColor(Color.YELLOW);
                for (Level.Coin coin : level.coins) {
                    if (!coin.collected) {
                        canvas.drawCircle(coin.x, coin.y, 20, paint);
                    }
                }

                paint.setColor(Color.GREEN);
                canvas.drawRect(level.endX - 30, level.endY - 60, 
                    level.endX + 30, level.endY, paint);

                Player player = gameEngine.getPlayer();
                paint.setColor(Color.BLUE);
                canvas.drawCircle(player.x, player.y, player.radius, paint);
                
                paint.setColor(Color.WHITE);
                canvas.drawCircle(player.x - 15, player.y - 10, 8, paint);
                canvas.drawCircle(player.x + 15, player.y - 10, 8, paint);
                paint.setColor(Color.BLACK);
                canvas.drawCircle(player.x - 15, player.y - 10, 4, paint);
                canvas.drawCircle(player.x + 15, player.y - 10, 4, paint);

                canvas.restore();
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }
}
