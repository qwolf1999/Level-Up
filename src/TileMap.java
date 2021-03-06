//This class holds a matrix of tiles and has methods to modify and read it
public class TileMap {
	private int width;
	private int height;
	private Tile[][] map;
	
	public TileMap(int cols, int rows){
		map = new Tile[cols][rows];
		width = cols;
		height = rows;
	}
	
	//makes a test map of sand with a couple pillars of varying heights
	public void makeTestMap(){
		for(int y = 0; y < map[0].length; y++){
			for(int x = 0; x < map.length; x++){
				if(y == 8 && x % 4 == 2){
						map[x][y] = new Tile(TileType.stone, x/8.0 + .3);
				} else {
					double height = 0;
					TileType type = TileType.sand;
					map[x][y] = new Tile(type,height);
				}
			}
		}
	}
	
	//set a tile at x,y to a specified type and height
	public void setTile(int x, int y, TileType type, double height){
		map[x][y] = new Tile(type, height);
	}
	
	//return the tile object from x,y
	public Tile getTile(int x, int y){
		return map[x][y];
	}
	
	//can the tile at x,y be walked on/through 
	public boolean isTraversable(int x, int y){
		boolean inBounds = (x >= 0 && x < width && y >= 0 && y < height);
		return (inBounds && map[x][y].getHeight() == 0);
	}
	
	//return the size of the matrix in a short int array
	public int[] getSize(){
		int[] size = {map.length, map[0].length};
		return size;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
  
	public Tile[][] getMap(){
		return map;
	}
}
