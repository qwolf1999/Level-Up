import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Dimension;

public class Game implements Runnable {	
	public static final int TILE_SIZE = 16; //the pixel size of each tile image
	public static final double TILE_SCALE = 5; //the factor to multiply the size with
	public static final int UNIT = (int)(TILE_SIZE * TILE_SCALE); //Factor to multiply world coordinates into screenspace pixel coordinates

	public TileMap map;
	public Player player;
	public ArrayList<Mob> enemies;
	public Camera camera;
	public Renderer renderer;
	
	public Game () {
		map = new TileMap(100,100);
		renderer = new Renderer(this);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		player = new Player(this, 10, 5);
		camera = new Camera(player);
		camera.setXOffset((screenSize.getWidth()/2) / (Game.UNIT) - (.5));
		camera.setYOffset((screenSize.getHeight()/2) / Game.UNIT - (.5));
		
		enemies = new ArrayList<Mob>();
		Enemy e1 = new Enemy(player, "fireMonster", 1, 1, 3);
		Enemy e2 = new Enemy(player, "piskel", 1, 1, 4);
		enemies.add(e1);
		//enemies.add(e2);
		
    new Thread(this).start();
	}
	
	public void run(){
		while(true){
			update();
		}
	}
	
	long lastTime = System.currentTimeMillis();
	double counter = 0;
	
	public void update(){
		long nowTime = System.currentTimeMillis();
		counter += (nowTime - lastTime);
		lastTime = nowTime;
		
		if(counter >= 1000/60){
			counter = 0;
			player.update();
			camera.update();
			for(Mob mob : enemies){
				mob.update();
			}
		}
		
		renderer.repaint();
	}
}
