package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Item;
import model.ItemManager;

public class HomeViewController {
	public Label addItemIDLabel;
	public TextField addItemNameTextField;
	public TextField addItemQuantityTextField;
	public TextField addItemPriceTextField;
	public TextArea addItemDescriptionTextField;
	public Button removeItemRemoveButton;
	public Label removeItemNameLabel;
	public TextField removeItemIDTextField;
	public Label removeItemQuantityLabel;
	public Label removeItemPriceLabel;
	public Label removeItemDescriptionLabel;
	public TableView viewItemsTable;
	public TableColumn viewItemsIDColumn;
	public TableColumn viewItemsNameColumn;
	public TableColumn viewItemsQuantityColumn;
	public TableColumn viewItemsPriceColumn;
	public TableColumn viewItemsDescriptionColumn;

	private static void mouseHoverOnButtonAction (Button button, boolean isMouseOn) {
		button.setStyle("-fx-background-color: " + (isMouseOn ? "#fdd" : "#ddd"));
	}

	public void initialize () {
		this.addItemIDLabel.setText("Item#" + ItemManager.getManager().getLastID());

		this.viewItemsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.viewItemsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.viewItemsQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		this.viewItemsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		this.viewItemsDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

		this.refreshViewItemsTable();
	}

	private void refreshViewItemsTable () {
		final ObservableList<Item> items = FXCollections.observableArrayList(ItemManager.getManager().getItems());
		this.viewItemsTable.setItems(items);
	}

	private void resetAddItemTab () {
		this.addItemIDLabel.setText("Item#" + ItemManager.getManager().getLastID());
		this.addItemNameTextField.setText("");
		this.addItemQuantityTextField.setText("");
		this.addItemPriceTextField.setText("");
		this.addItemDescriptionTextField.setText("");
	}

	private void resetRemoveItemTab () {
		this.removeItemNameLabel.setText("-");
		this.removeItemQuantityLabel.setText("-");
		this.removeItemPriceLabel.setText("-");
		this.removeItemDescriptionLabel.setText("-");
	}

	private Item removeTabSearchAction () {
		final String idStr = this.removeItemIDTextField.getText();

		if (!idStr.matches("^Item#(\\d+)$")) return null;

		final Integer id = Integer.parseInt(idStr.substring(5));
		final Item searchedItem = ItemManager.getManager().searchItem(id);

		if (searchedItem == null) {
			this.resetRemoveItemTab();
		} else {
			this.removeItemRemoveButton.setDisable(false);
			this.updateRemoveItemTab(searchedItem);
		}

		return searchedItem;
	}

	private void updateRemoveItemTab (Item item) {
		this.removeItemNameLabel.setText(item.getName());
		this.removeItemQuantityLabel.setText(item.getQuantity().toString());
		this.removeItemPriceLabel.setText(item.getPrice().toString());
		this.removeItemDescriptionLabel.setText(item.getDescription());
	}

	private void showAlert (String message, Alert.AlertType type) {
		final Alert alert = new Alert(type);
		alert.setContentText(message);
		alert.setTitle("Item Management Form");
		alert.show();
	}

	public void addItemAddButtonOnAction (ActionEvent actionEvent) {
		final String nameStr = this.addItemNameTextField.getText();
		final String quantityStr = this.addItemQuantityTextField.getText();
		final String priceStr = this.addItemPriceTextField.getText();
		final String descriptionStr = this.addItemDescriptionTextField.getText();

		if (nameStr.isEmpty()) {
			this.showAlert("Please add a name.", Alert.AlertType.WARNING);
			return;
		}

		if (!quantityStr.matches("^\\d+$")) {
			this.showAlert("Invalid Quantity.", Alert.AlertType.WARNING);
			return;
		}

		if (!priceStr.matches("^\\d+(\\.\\d+)?$")) {
			this.showAlert("Invalid Price.", Alert.AlertType.WARNING);
			return;
		}

		ItemManager.getManager().addItem(nameStr, Integer.parseInt(quantityStr), Double.parseDouble(priceStr), descriptionStr);
		this.resetAddItemTab();
		this.refreshViewItemsTable();
	}

	public void addItemAddButtonOnMouseEntered (MouseEvent mouseEvent) {
		HomeViewController.mouseHoverOnButtonAction((Button) mouseEvent.getTarget(), true);
	}

	public void addItemAddButtonOnMouseExited (MouseEvent mouseEvent) {
		HomeViewController.mouseHoverOnButtonAction((Button) mouseEvent.getTarget(), false);
	}

	public void removeItemRemoveButtonOnAction (ActionEvent actionEvent) {
		final String idStr = this.removeItemIDTextField.getText();

		if (!idStr.matches("^Item#(\\d+)$")) {
			this.showAlert("Invalid ID.", Alert.AlertType.WARNING);
			return;
		}

		final Integer id = Integer.parseInt(idStr.substring(5));
		final Item removedItem = ItemManager.getManager().removeItem(id);

		if (removedItem == null) {
			this.showAlert("There is no item with id " + id, Alert.AlertType.WARNING);
		} else {
			this.refreshViewItemsTable();
		}

		this.resetRemoveItemTab();
		this.removeItemRemoveButton.setDisable(true);
	}

	public void removeItemRemoveButtonOnMouseEntered (MouseEvent mouseEvent) {
		HomeViewController.mouseHoverOnButtonAction((Button) mouseEvent.getTarget(), true);
	}

	public void removeItemRemoveButtonOnMouseExited (MouseEvent mouseEvent) {
		HomeViewController.mouseHoverOnButtonAction((Button) mouseEvent.getTarget(), false);
	}

	public void removeItemSearchButtonOnAction (ActionEvent actionEvent) {
		if (this.removeTabSearchAction() == null) this.showAlert("There is no item with given id.", Alert.AlertType.WARNING);
	}

	public void removeItemSearchButtonOnMouseEntered (MouseEvent mouseEvent) {
		HomeViewController.mouseHoverOnButtonAction((Button) mouseEvent.getTarget(), true);
	}

	public void removeItemSearchButtonOnMouseExited (MouseEvent mouseEvent) {
		HomeViewController.mouseHoverOnButtonAction((Button) mouseEvent.getTarget(), false);
	}

	public void removeItemIDTextFieldOnKeyReleased (KeyEvent keyEvent) {
		this.removeItemRemoveButton.setDisable(true);
		this.removeTabSearchAction();
	}

	public void viewItemsRefreshButtonOnAction (ActionEvent actionEvent) {
		this.refreshViewItemsTable();
		this.showAlert("Table refreshed!", Alert.AlertType.INFORMATION);
	}

	public void viewItemsRefreshButtonOnMouseEntered (MouseEvent mouseEvent) {
		HomeViewController.mouseHoverOnButtonAction((Button) mouseEvent.getTarget(), true);
	}

	public void viewItemsRefreshButtonOnMouseExited (MouseEvent mouseEvent) {
		HomeViewController.mouseHoverOnButtonAction((Button) mouseEvent.getTarget(), false);
	}
}
