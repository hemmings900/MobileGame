package com.mygdx.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.mygdx.gameObjects.BasicBlock;
import com.mygdx.gameObjects.GameObject;
import com.mygdx.gameObjects.Sprite;
/*
 * This class handles all the game blocks. 
 * Handles adding and deleting blocks
 */
public class BlockController {	
	private ArrayList<GameObject> blocks; 
	//private int blockLimit;
	private Random rand;
	
	public BlockController(int newBlockLimit){
//		blockLimit = newBlockLimit;
		rand = new Random();
		blocks = new ArrayList<GameObject>();
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
				//System.out.println("Block removed");
				}
			}				
		}		
	
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
	 * Add a random block at a random position.
	 * Picks a random block out of available blocks and creates that 
	 * block at a random position at a random time.
	 */
	public void addRandomBlock(int blockSpeed, int timeChance){
		//find random X position (Y is left at 0 so it will start at the bottom of the screen)
		if (rand.nextInt(timeChance) == 1){
			int randomX = rand.nextInt(Gdx.graphics.getWidth());
			Sprite newSprite = new Sprite("block.png");
			GameObject newBlock = new BasicBlock(randomX,0-newSprite.getImage().getHeight(),blockSpeed,newSprite);
			blocks.add(newBlock);
			}
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

//	public int getBlockLimit() {
//		return blockLimit;
//	}
//
//	public void setBlockLimit(int blockLimit) {
//		this.blockLimit = blockLimit;
//	}
}