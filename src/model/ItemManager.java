package model;

import java.util.ArrayList;

public class ItemManager {
	private static ItemManager manager;

	final private ArrayList<Item> items;
	private int lastID;

	private ItemManager () {
		this.items = new ArrayList<>();
		this.lastID = 1;

		this.addItem("Sugar", 23, 2000.0, "20 Kg");
	}

	public static ItemManager getManager () {
		if (ItemManager.manager == null) ItemManager.manager = new ItemManager();
		return ItemManager.manager;
	}

	public void addItem (String name, Integer quantity, Double price, String description) {
		this.items.add(new Item(this.lastID++, name, quantity, price, description));
		System.out.println(this.items);
	}

	public int getLastID () {
		return this.lastID;
	}

	public Item searchItem (Integer id) {
		for (Item item : this.items) if (item.getId().equals(id)) return item;
		return null;
	}

	public Item removeItem (Integer id) {
		for (int g = 0; g < this.items.size(); g++) if (this.items.get(g).getId().equals(id)) return this.items.remove(g);
		return null;
	}

	public ArrayList<Item> getItems () {
		return this.items;
	}
}
