package cegepst.viking;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Player extends ControllableEntity {

    private static final String SPRITE_SHEET_PATH = "images/player.png";
    private static final int ANIMATION_SPEED = 8;

    private BufferedImage spriteSheet;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;
    private int currentAnimationFrame = 1; // idle frame (middle)
    private int nextFrame = ANIMATION_SPEED;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        loadSpriteSheet();
        loadAnimationFrames();
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToController();
        if (hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= leftFrames.length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        switch (getDirection()) {
            case RIGHT -> buffer.drawImage(rightFrames[currentAnimationFrame], x, y);
            case LEFT -> buffer.drawImage(leftFrames[currentAnimationFrame], x, y);
            case UP -> buffer.drawImage(upFrames[currentAnimationFrame], x, y);
            case DOWN -> buffer.drawImage(downFrames[currentAnimationFrame], x, y);
        }
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_SHEET_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAnimationFrames() {
        downFrames = new Image[3];
        leftFrames = new Image[3];
        rightFrames = new Image[3];
        upFrames = new Image[3];
        for (int i = 0; i < 3; i++) {
            downFrames[i] = spriteSheet.getSubimage((i * 32), 128, width, height);
            leftFrames[i] = spriteSheet.getSubimage((i * 32), 160, width, height);
            rightFrames[i] = spriteSheet.getSubimage((i * 32), 192, width, height);
            upFrames[i] = spriteSheet.getSubimage((i * 32), 224, width, height);
        }
    }
}
