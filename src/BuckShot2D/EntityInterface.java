package BuckShot2D;

public interface EntityInterface {
        void setOpponent(Entity opponent);
        void attack() throws InterruptedException;
        void shootSelf();
        void shootOpp();

}
