package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.mygdx.gameObjects.BasicBlock;
import com.mygdx.gameObjects.GameObject;
import com.mygdx.gameObjects.Sprite;
import com.mygdx.gamePhysics.Point;
/*
 * This class handles all the game blocks. 
 * Handles adding and deleting blocks
 */
public class BlockController {	
	private ArrayList<GameObject> blocks; 
	private int blockSpeed;
	// chance for block to spawn is based on chance 1 out of X where X is blockChance.
	private int blockChance;
	private Random rand;
	
	public BlockController(int newBlockSpeed, int newBlockChance){

		rand = new Random();
		blocks = new ArrayList<GameObject>();
		blockSpeed = newBlockSpeed;
		blockChance = newBlockChance;
	}
	
	/*
	 * Iterate through all blocks in list and update position.
	 */
	public void MoveBlocks(){
		for (GameObject gb : blocks){
			Point pointToFollow = new Point(gb.getObjectX(),Gdx.graphics.getHeight()+1);
			gb.FollowPoint(pointToFollow);
			}
	}
	/*
	 * If number of blocks in list is not over the limit. Add the given block.
	 */
	public void AddGameObject(GameObject newBlock){
//		if (blocks.size() < blockLimit)
			blocks.add(newBlock);
	}
	
	/*
	 *Check if block has moved off screen. Using either index number or block itself
	 */
	public boolean isBlockOffScreen(int index){
		if (blocks.get(index).getObjectY() > Gdx.graphics.getHeight())
			return true;
		else 
			return false;
	}
	
	/*
	 * Go through all blocks, if a block is off screen, remove that block
	 * from the list.
	 */
	public void RemoveOutsideBlocks(){
		for (int i = 0; i < blocks.size(); i++){
			if (isBlockOffScreen(blocks.get(i))){
				blocks.remove(i);
				}
			}				
		}		
	
	//Check if block is off the screen.
	public boolean isBlockOffScreen(GameObject blockToDrop){
		if (blockToDrop.getObjectY() > Gdx.graphics.getHeight())
			return true;
		else 
			return false;
	}
	/*
	 * Remove a block from the list by either giving the list a specific 
	 * block to remove, or just remove the block based on its index number.
	 */
	public void RemoveGameObject(GameObject block){
		blocks.remove(block);
	}
	
	public void RemoveGameBlock(int blockIndex){
		blocks.remove(blockIndex);
	}
	
	/*
	 * Fill boolean array with random true or false values.
	 * Loop through array, if value is true, create a block at Index number * sprite width.
	 * (Seems redundant to have two loops but when adding extra block types and pickups 
	 * this should reduce spaghetti code)
	 */
	public void addRandomBlocks(){
		Sprite newSprite = new Sprite("gameObjects/block.png");
		//Create array
		int blocksToGenerate = Gdx.graphics.getWidth()/newSprite.getImage().getWidth();
		boolean[] newBlockLine =  new boolean[blocksToGenerate];
		//Loop through array and add random values
		for (int i = 0; i < newBlockLine.length; i++){
			if (rand.nextInt(blockChance) == 1){
				newBlockLine[i] = true;
			}
			else
				newBlockLine[i] = false;
		}
		//Loop through array for each true value and add block.
		for (int i = 0; i < newBlockLine.length; i++){
			if (newBlockLine[i]){
				
				BasicBlock newBlock = new BasicBlock(i*newSprite.getImage().getWidth(),
													 0-newSprite.getImage().getHeight(),
													 blockSpeed,newSprite);
				blocks.add(newBlock);
			}
		}		
//		if (rand.nextInt(timeChance) == 1){
//			int randomX = rand.nextInt(Gdx.graphics.getWidth());
//			Sprite newSprite = new Sprite("block.png");
//			GameObject newBlock = new BasicBlock(randomX,,blockSpeed,newSprite);
//			blocks.add(newBlock);
//			}
	}
	
	/*#########################################################
	 * Setters and getters for BlockController properties.#####
	 #########################################################*/
	public int getBlockCount(){
		return blocks.size();
	}
	
	public GameObject getBlock(int index){
		return blocks.get(index);
	}
	
	public ArrayList<GameObject> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<GameObject> blocks) {
		this.blocks = blocks;
	}

	public int getBlockSpeed() {
		return blockSpeed;
	}

	public void setBlockSpeed(int blockSpeed) {
		this.blockSpeed = blockSpeed;
	}

	public int getBlockChance() {
		return blockChance;
	}

	public void setBlockChance(int blockChance) {
		this.blockChance = blockChance;
	}

//	public int getBlockLimit() {
//		return blockLimit;
//	}
//
//	public void setBlockLimit(int blockLimit) {
//		this.blockLimit = blockLimit;
//	}
}