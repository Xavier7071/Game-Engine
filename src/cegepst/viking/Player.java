package cegepst.viking;

import cegepst.engine.Buffer;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {

    private static final String SPRITE_SHEET_PATH = "images/player.png";
    private BufferedImage spriteSheet;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;

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
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(leftFrames[1], x, y);
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
