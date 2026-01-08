package com.leilie.leveleditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LevelEditor extends JFrame {
    private EditorPanel editorPanel;
    private JComboBox<String> toolSelector;
    private Level level;

    public LevelEditor() {
        setTitle("Level Editor - Platform Game");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        level = new Level();
        editorPanel = new EditorPanel(level);
        add(editorPanel, BorderLayout.CENTER);

        JPanel toolbar = new JPanel();
        toolSelector = new JComboBox<>(new String[]{
            "Départ", "Arrivée", "Plateforme", "Ennemi", "Pièce", "Supprimer"
        });
        toolbar.add(new JLabel("Outil:"));
        toolbar.add(toolSelector);

        JButton btnSave = new JButton("Sauvegarder");
        btnSave.addActionListener(e -> saveLevel());
        toolbar.add(btnSave);

        JButton btnClear = new JButton("Effacer");
        btnClear.addActionListener(e -> {
            level = new Level();
            editorPanel.setLevel(level);
            editorPanel.repaint();
        });
        toolbar.add(btnClear);

        add(toolbar, BorderLayout.NORTH);
        setVisible(true);
    }

    private void saveLevel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new java.io.File("level1.json"));
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(level);
                FileWriter writer = new FileWriter(fileChooser.getSelectedFile());
                writer.write(json);
                writer.close();
                JOptionPane.showMessageDialog(this, "Niveau sauvegardé!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage());
            }
        }
    }

    class EditorPanel extends JPanel {
        private Level level;
        private Point dragStart;
        private Point dragEnd;

        public EditorPanel(Level level) {
            this.level = level;
            setBackground(Color.decode("#87CEEB"));

            MouseAdapter mouseAdapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    dragStart = e.getPoint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    dragEnd = e.getPoint();
                    handleToolAction();
                    dragStart = null;
                    dragEnd = null;
                    repaint();
                }
            };

            addMouseListener(mouseAdapter);
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    dragEnd = e.getPoint();
                    repaint();
                }
            });
        }

        public void setLevel(Level level) {
            this.level = level;
        }

        private void handleToolAction() {
            if (dragStart == null) return;

            String tool = (String) toolSelector.getSelectedItem();
            float x = Math.min(dragStart.x, dragEnd != null ? dragEnd.x : dragStart.x);
            float y = Math.min(dragStart.y, dragEnd != null ? dragEnd.y : dragStart.y);
            float width = Math.abs((dragEnd != null ? dragEnd.x : dragStart.x) - dragStart.x);
            float height = Math.abs((dragEnd != null ? dragEnd.y : dragStart.y) - dragStart.y);

            switch (tool) {
                case "Départ":
                    level.startX = dragStart.x;
                    level.startY = dragStart.y;
                    break;
                case "Arrivée":
                    level.endX = dragStart.x;
                    level.endY = dragStart.y;
                    break;
                case "Plateforme":
                    if (width > 10 && height > 10) {
                        level.platforms.add(new Platform(x, y, width, height));
                    }
                    break;
                case "Ennemi":
                    if (width > 10 && height > 10) {
                        level.enemies.add(new Enemy(x, y, width, height));
                    }
                    break;
                case "Pièce":
                    level.coins.add(new Coin(dragStart.x, dragStart.y));
                    break;
                case "Supprimer":
                    removeElementAt(dragStart.x, dragStart.y);
                    break;
            }
        }

        private void removeElementAt(float x, float y) {
            level.platforms.removeIf(p -> 
                x >= p.x && x <= p.x + p.width && y >= p.y && y <= p.y + p.height);
            level.enemies.removeIf(e -> 
                x >= e.x && x <= e.x + e.width && y >= e.y && y <= e.y + e.height);
            level.coins.removeIf(c -> 
                Math.abs(c.x - x) < 20 && Math.abs(c.y - y) < 20);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.decode("#8B4513"));
            for (Platform p : level.platforms) {
                g2d.fillRect((int)p.x, (int)p.y, (int)p.width, (int)p.height);
            }

            g2d.setColor(Color.RED);
            for (Enemy e : level.enemies) {
                g2d.fillRect((int)e.x, (int)e.y, (int)e.width, (int)e.height);
            }

            g2d.setColor(Color.YELLOW);
            for (Coin c : level.coins) {
                g2d.fillOval((int)c.x - 10, (int)c.y - 10, 20, 20);
            }

            g2d.setColor(Color.BLUE);
            g2d.fillOval((int)level.startX - 20, (int)level.startY - 20, 40, 40);
            g2d.setColor(Color.BLACK);
            g2d.drawString("START", (int)level.startX - 20, (int)level.startY - 25);

            g2d.setColor(Color.GREEN);
            g2d.fillRect((int)level.endX - 30, (int)level.endY - 60, 60, 60);
            g2d.setColor(Color.BLACK);
            g2d.drawString("END", (int)level.endX - 15, (int)level.endY - 65);

            if (dragStart != null && dragEnd != null) {
                g2d.setColor(new Color(255, 255, 255, 100));
                int x = Math.min(dragStart.x, dragEnd.x);
                int y = Math.min(dragStart.y, dragEnd.y);
                int w = Math.abs(dragEnd.x - dragStart.x);
                int h = Math.abs(dragEnd.y - dragStart.y);
                g2d.fillRect(x, y, w, h);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LevelEditor::new);
    }
}

class Level {
    public float startX = 100;
    public float startY = 100;
    public float endX = 1000;
    public float endY = 100;
    public List<Platform> platforms = new ArrayList<>();
    public List<Enemy> enemies = new ArrayList<>();
    public List<Coin> coins = new ArrayList<>();
}

class Platform {
    public float x, y, width, height;
    public Platform(float x, float y, float width, float height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }
}

class Enemy {
    public float x, y, width, height;
    public Enemy(float x, float y, float width, float height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }
}

class Coin {
    public float x, y;
    public boolean collected = false;
    public Coin(float x, float y) {
        this.x = x; this.y = y;
    }
}
