package Comunications;

public class AppFrameType {
    private boolean asteroid;
    private boolean ball;
    private boolean bullet;
    private boolean control_action;
    private boolean misil;
    private boolean player_ship;
    private boolean player_ship_action;

    public boolean isAsteroid() {
        return asteroid;
    }

    public void setAsteroid(boolean asteroid) {
        this.asteroid = asteroid;
    }

    public boolean isBall() {
        return ball;
    }

    public void setBall(boolean ball) {
        this.ball = ball;
    }

    public boolean isBullet() {
        return bullet;
    }

    public void setBullet(boolean bullet) {
        this.bullet = bullet;
    }

    public boolean isControl_action() {
        return control_action;
    }

    public void setControl_action(boolean control_action) {
        this.control_action = control_action;
    }

    public boolean isMisil() {
        return misil;
    }

    public void setMisil(boolean misil) {
        this.misil = misil;
    }

    public boolean isPlayer_ship() {
        return player_ship;
    }

    public void setPlayer_ship(boolean player_ship) {
        this.player_ship = player_ship;
    }

    public boolean isPlayer_ship_action() {
        return player_ship_action;
    }

    public void setPlayer_ship_action(boolean player_ship_action) {
        this.player_ship_action = player_ship_action;
    }
}
