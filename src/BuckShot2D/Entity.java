package BuckShot2D;

abstract class Entity implements EntityInterface {

    GamePanel gp;
    MouseHandler mouseH;

    // Personal Attributes
    private String name;
    private int health = 6;
    public boolean aimOnOpp;

    // Objects assigned to a participant
    Shotgun shotgun;
    protected Entity opponent;

    // Participant States
    public boolean isDead = false; // might not need, but just in case.
    public boolean inAction = true; // Boolean decides whether participant can move or not. When correct move, set this to true again, else false.
    public boolean shotSelfWithDud = false;

    public Entity (GamePanel gp, MouseHandler mouseH, Shotgun shotgun) {
        this.gp = gp;
        this.mouseH = mouseH;
        this.shotgun = shotgun;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void setOpponent(Entity opponent) {
        this.opponent = opponent;
    }


    private void changeTarget() {
        if (aimOnOpp) {
            aimOnOpp = false;
        } else {
            aimOnOpp = true;
        }
    }

    public void shootSelf() {
        if (aimOnOpp){
            changeTarget();
        }
        attack();
    }

    public void shootOpp() {
        if (!aimOnOpp){
            changeTarget();
        }
        attack();
    }

    public void damageTaken() {
        // Updates health after shooting
        this.health -= 1;
        // Health Check
        if (health <= 0){
            isDead = true;
            inAction = false;
            System.out.println(getName() + " died...");
        }
    }
    @Override
    public void attack() {
        if (aimOnOpp) {
            if (shotgun.shoot()) {
                opponent.damageTaken();
                System.out.println(opponent.getName() + " was shot");
                System.out.println();
                inAction = false;
            }
            else {
                System.out.println();
                inAction = false;
            }
        } else {
            if (shotgun.shoot()) {
                damageTaken();
                System.out.println("shot self with live");
                System.out.println();
                inAction = false;
            }
            else {
                System.out.println("dud bullet on self");
                System.out.println();
                shotSelfWithDud = true;
            }
            System.out.println();
        }
        if (health <= 0) {
            isDead = true;
        }
        if (!isDead && !opponent.isDead && shotgun.loadedBullets.isEmpty()){
            shotgun.reloadShotgun();
            System.out.println("reloading from participant class");
        }
    }
}
