package com.streep.mod.craftguide;

import java.util.Map;

import org.bukkit.inventory.ItemStack;

import com.streep.mod.Main;

public class SavedRecipe {

	private ItemStack result;
	private String[] shape;
	private Map<Character,ItemStack> ingredients;
	private Main main;
	
	public SavedRecipe(ItemStack result, String[] shape, Map<Character, ItemStack> ingredients, Main main) {
		this.setResult(result);
		this.setShape(shape);
		this.setIngredients(ingredients);
		this.setMain(main);
	}

	public ItemStack getResult() {
		return result;
	}

	public void setResult(ItemStack result) {
		this.result = result;
	}

	public String[] getShape() {
		return shape;
	}

	public void setShape(String[] shape) {
		this.shape = shape;
	}

	public Map<Character,ItemStack> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Map<Character,ItemStack> ingredients) {
		this.ingredients = ingredients;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
}
