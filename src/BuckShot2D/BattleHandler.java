package BuckShot2D;

public class BattleHandler {
    GamePanel gp;

    Shotgun shotgun;
    PlayerEntity player;
    DealerEntity dealer;

    public BattleHandler(GamePanel gp) {
        this.gp = gp;
        this.shotgun = new Shotgun(gp);
        this.dealer = new DealerEntity(gp, gp.mouseH, shotgun);
        this.player = new PlayerEntity(gp, gp.mouseH, shotgun);

        dealer.setOpponent(player);
        player.setOpponent(dealer);

        shotgun.reloadShotgun();
    }

    public void update() {
        if (!(dealer.isDead) && !(player.isDead)) {

            player.takeAction();
            dealer.makeChoice();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        if (dealer.isDead) {
            System.out.println("Dealer's a loser. You win!!!");
            System.out.println(dealer.toString());
        } else if (player.isDead) {
            System.out.println("You suck at this game...");
            System.out.println(player.toString());
        }


    }

}
