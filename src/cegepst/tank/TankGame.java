package cegepst.tank;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

public class TankGame extends Game {

    private GamePad gamepad;

    @Override
    public void initialize() {
        gamepad = new GamePad();
        addKeyListener(gamepad);
    }

    @Override
    public void update() {
        if (gamepad.isQuitPressed()) {
            stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {

    }

    @Override
    public void conclude() {

    }
}
