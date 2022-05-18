package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class HelpItem {
	
	StringProperty name = new SimpleStringProperty();
	ObjectProperty<Image> hintImage = new SimpleObjectProperty<Image>();
	ObjectProperty<Image> buttonImage = new SimpleObjectProperty<Image>();
	StringProperty shortcut = new SimpleStringProperty();
	StringProperty description = new SimpleStringProperty();
	
	public HelpItem(String name, Image hintImage, Image buttonImage, String shortcut, String description) {
		this.name.set(name);
		this.hintImage.set(hintImage);
		this.buttonImage.set(buttonImage);
		this.shortcut.set(shortcut);
		this.description.set(description);
	}
	
	public HelpItem(String name, Image hintImage) {
		this.name.set(name);
		this.hintImage.set(hintImage);
	}
	
	public StringProperty getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public Image getHintImage() {
		return hintImage.get();
	}
	
	public void setHintImage(Image img) {
		this.hintImage.set(img);
	}
	
	public Image getButtonImage() {
		return buttonImage.get();
	}
	
	public void setButtonImage(Image img) {
		this.buttonImage.set(img);
	}
	
	public StringProperty getShortcut() {
		return shortcut;
	}
	
	public void setShortcut(String shortcut) {
		this.shortcut.set(shortcut);
	}
	
	public StringProperty getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description.set(description);
	}
	
	public String toString() {
		return this.name.get();
	}

}
