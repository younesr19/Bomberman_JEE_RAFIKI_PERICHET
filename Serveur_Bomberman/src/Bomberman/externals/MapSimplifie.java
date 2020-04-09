package Bomberman.externals;

public class MapSimplifie {
	private String filename;
	private int size_x;
	private int size_y;
	
	private boolean walls[][];
	private boolean start_brokable_walls[][];
	public MapSimplifie() {
		super();
	}
	public MapSimplifie(String filename, int size_x, int size_y, boolean[][] walls, boolean[][] start_brokable_walls) {
		super();
		this.filename = filename;
		this.size_x = size_x;
		this.size_y = size_y;
		this.walls = walls;
		this.start_brokable_walls = start_brokable_walls;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the size_x
	 */
	public int getSize_x() {
		return size_x;
	}
	/**
	 * @param size_x the size_x to set
	 */
	public void setSize_x(int size_x) {
		this.size_x = size_x;
	}
	/**
	 * @return the size_y
	 */
	public int getSize_y() {
		return size_y;
	}
	/**
	 * @param size_y the size_y to set
	 */
	public void setSize_y(int size_y) {
		this.size_y = size_y;
	}
	/**
	 * @return the walls
	 */
	public boolean[][] getWalls() {
		return walls;
	}
	/**
	 * @param walls the walls to set
	 */
	public void setWalls(boolean[][] walls) {
		this.walls = walls;
	}
	/**
	 * @return the start_brokable_walls
	 */
	public boolean[][] getStart_brokable_walls() {
		return start_brokable_walls;
	}
	/**
	 * @param start_brokable_walls the start_brokable_walls to set
	 */
	public void setStart_brokable_walls(boolean[][] start_brokable_walls) {
		this.start_brokable_walls = start_brokable_walls;
	}
	
	
	
}
