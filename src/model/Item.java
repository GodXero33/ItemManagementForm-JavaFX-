package model;

public class Item {
	private Integer id;
	private String name;
	private Integer quantity;
	private Double price;
	private String description;

	public Item (Integer id, String name, Integer quantity, Double price, String description) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
	}

	public Integer getId () {
		return this.id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public Integer getQuantity () {
		return this.quantity;
	}

	public void setQuantity (Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice () {
		return this.price;
	}

	public void setPrice (Double price) {
		this.price = price;
	}

	public String getDescription () {
		return this.description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	@Override
	public String toString () {
		return String.format(
			"Item: { id: %d, name: %s, quantity: %d, price: %.2f, description: %s }",
			this.id,
			this.name,
			this.quantity,
			this.price,
			this.description
		);
	}

	@Override
	public boolean equals (Object obj) {
		return (obj instanceof Item) && ((Item) obj).id.equals(this.id);
	}
}
