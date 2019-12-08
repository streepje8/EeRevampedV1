package com.streep.mod.dimensions;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class MarsPopulator extends BlockPopulator{

	@Override
	public void populate(World world, Random random, Chunk chunk) {
		int X, Y, Z;
		boolean isStone;
		int spawnupper = 20;
		for (int i = 1; i < 15; i++) {  // Number of tries
		    if (random.nextInt(100) < 60 + spawnupper) {  // The chance of spawning
			X = random.nextInt(15);
			Z = random.nextInt(15);
			Y = random.nextInt(50)+1;  // Get randomized coordinates
			if (chunk.getBlock(X, Y, Z).getType() == Material.STONE) {
				isStone = true;
				while (isStone) {
					chunk.getBlock(X, Y, Z).setType(Material.COAL_ORE);
					if (random.nextInt(100) < 90)  {   // The chance of continuing the vein
						switch (random.nextInt(5)) {  // The direction chooser
						case 0: X++; break;
						case 1: Y++; break;
						case 2: Z++; break;
						case 3: X--; break;
						case 4: Y--; break;
						case 5: Z--; break;
						}
						isStone = (chunk.getBlock(X, Y, Z).getType() == Material.STONE) && (chunk.getBlock(X, Y, Z).getType() != Material.COAL_ORE);
					} else isStone = false;
				}
			}
		    }
		}
		for (int i = 1; i < 15; i++) {  // Number of tries
		    if (random.nextInt(100) < 80 + spawnupper) {  // The chance of spawning
			X = random.nextInt(15);
			Z = random.nextInt(15);
			Y = random.nextInt(50)+1;  // Get randomized coordinates
			if (chunk.getBlock(X, Y, Z).getType() == Material.STONE) {
				isStone = true;
				while (isStone) {
					chunk.getBlock(X, Y, Z).setType(Material.ICE);
					if (random.nextInt(100) < 90)  {   // The chance of continuing the vein
						switch (random.nextInt(5)) {  // The direction chooser
						case 0: X++; break;
						case 1: Y++; break;
						case 2: Z++; break;
						case 3: X--; break;
						case 4: Y--; break;
						case 5: Z--; break;
						}
						isStone = (chunk.getBlock(X, Y, Z).getType() == Material.STONE) && (chunk.getBlock(X, Y, Z).getType() != Material.ICE);
					} else isStone = false;
				}
			}
		    }
		}
		for (int i = 1; i < 15; i++) {  // Number of tries
		    if (random.nextInt(100) < 5 + spawnupper) {  // The chance of spawning
			X = random.nextInt(15);
			Z = random.nextInt(15);
			Y = random.nextInt(50)+1;  // Get randomized coordinates
			if (chunk.getBlock(X, Y, Z).getType() == Material.STONE) {
				isStone = true;
				while (isStone) {
					chunk.getBlock(X, Y, Z).setType(Material.IRON_BLOCK);
					if (random.nextInt(100) < 90)  {   // The chance of continuing the vein
						switch (random.nextInt(5)) {  // The direction chooser
						case 0: X++; break;
						case 1: Y++; break;
						case 2: Z++; break;
						case 3: X--; break;
						case 4: Y--; break;
						case 5: Z--; break;
						}
						isStone = (chunk.getBlock(X, Y, Z).getType() == Material.STONE) && (chunk.getBlock(X, Y, Z).getType() != Material.IRON_BLOCK);
					} else isStone = false;
				}
			}
		    }
		}
		for (int i = 1; i < 15; i++) {  // Number of tries
		    if (random.nextInt(100) < 10 + spawnupper) {  // The chance of spawning
			X = random.nextInt(15);
			Z = random.nextInt(15);
			Y = random.nextInt(50)+1;  // Get randomized coordinates
			if (chunk.getBlock(X, Y, Z).getType() == Material.STONE) {
				isStone = true;
				while (isStone) {
					chunk.getBlock(X, Y, Z).setType(Material.IRON_ORE);
					if (random.nextInt(100) < 90)  {   // The chance of continuing the vein
						switch (random.nextInt(5)) {  // The direction chooser
						case 0: X++; break;
						case 1: Y++; break;
						case 2: Z++; break;
						case 3: X--; break;
						case 4: Y--; break;
						case 5: Z--; break;
						}
						isStone = (chunk.getBlock(X, Y, Z).getType() == Material.STONE) && (chunk.getBlock(X, Y, Z).getType() != Material.IRON_ORE);
					} else isStone = false;
				}
			}
		    }
		}
		for (int i = 1; i < 15; i++) {  // Number of tries
		    if (random.nextInt(100) < 10 + spawnupper) {  // The chance of spawning
			X = random.nextInt(15);
			Z = random.nextInt(15);
			Y = random.nextInt(50)+1;  // Get randomized coordinates
			if (chunk.getBlock(X, Y, Z).getType() == Material.STONE) {
				isStone = true;
				while (isStone) {
					chunk.getBlock(X, Y, Z).setType(Material.DIAMOND_ORE);
					if (random.nextInt(100) < 60)  {   // The chance of continuing the vein
						switch (random.nextInt(5)) {  // The direction chooser
						case 0: X++; break;
						case 1: Y++; break;
						case 2: Z++; break;
						case 3: X--; break;
						case 4: Y--; break;
						case 5: Z--; break;
						}
						isStone = (chunk.getBlock(X, Y, Z).getType() == Material.STONE) && (chunk.getBlock(X, Y, Z).getType() != Material.DIAMOND_ORE);
					} else isStone = false;
				}
			}
		    }
		}
		
		
	}
		

}
