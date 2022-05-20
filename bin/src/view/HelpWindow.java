package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.HelpItem;
import util.ImageLoader;

public class HelpWindow extends Stage {
	
	private TreeView<HelpItem> helpTree;
	private ImageView hintImage;
	private ImageView buttonImage;
	private Label shortcutLabel;
	private Label descriptionLabel;
	
	public HelpWindow(Stage primaryStage, boolean darkMode) {
		
		this.setScene(createScene(darkMode));
		this.initModality(Modality.WINDOW_MODAL);
		this.setTitle("Nápověda");
		this.initOwner(primaryStage);
		this.setMinHeight(700);
		this.setMinWidth(850);
		this.show();
	}
	
	private Scene createScene(boolean darkMode) {
		Scene scene = new Scene(createRootPane(), 850, 700);
		
		if(darkMode) {
			scene.getStylesheets().add(getClass().getResource("/darkmode.css").toString());
		}
		
		return scene;
	}
	
	private Parent createRootPane() {
		HBox rootPane = new HBox(5);
		rootPane.setPadding(new Insets(10));
		rootPane.setAlignment(Pos.CENTER);
		
		rootPane.getChildren().addAll(createTreeView(), createHintPane());
		
		return rootPane;
	}
	
	private Parent createHintPane() {
		VBox hintPane = new VBox(5);
		hintPane.setPadding(new Insets(10));
		hintPane.setAlignment(Pos.TOP_CENTER);
		
		hintImage = new ImageView(ImageLoader.HINT_DEFAULT);
		
		hintPane.getChildren().addAll(hintImage, createDescriptionPane());
		
		return hintPane;
	}

	private Node createDescriptionPane() {
		VBox descriptionPane = new VBox(5);
		descriptionPane.setPadding(new Insets(10));
		descriptionPane.setAlignment(Pos.CENTER_LEFT);
		
		HBox buttonAndShortcut = new HBox(5);
		buttonAndShortcut.setPadding(new Insets(10));
		buttonAndShortcut.setAlignment(Pos.CENTER_LEFT);
		
		buttonImage = new ImageView();
		buttonImage.setFitWidth(50);
		buttonImage.setPreserveRatio(true);
		
		shortcutLabel = new Label("");
		
		buttonAndShortcut.getChildren().addAll(buttonImage, shortcutLabel);
		
		descriptionLabel = new Label("");
		
		descriptionPane.getChildren().addAll(buttonAndShortcut, descriptionLabel);
		
		return descriptionPane;
	}

	private Node createTreeView() {
		helpTree = new TreeView<HelpItem>();
		TreeItem<HelpItem> rootItem = new TreeItem<HelpItem>(new HelpItem("Tiny Planet Builder", ImageLoader.HINT_DEFAULT));
		helpTree.setRoot(rootItem);
		rootItem.setExpanded(true);
		helpTree.setEditable(true);
		
		createDefaultChildren(helpTree.getRoot());
		
		helpTree.getSelectionModel().selectedItemProperty().addListener(e -> updateHint());
		
		return helpTree;
	}

	private void createDefaultChildren(TreeItem<HelpItem> root) {
		TreeItem<HelpItem> fileItem = new TreeItem<HelpItem>(new HelpItem("Práce se souborem", ImageLoader.HINT_DEFAULT));
		TreeItem<HelpItem> newFile = new TreeItem<HelpItem>(new HelpItem("Nová mapa", ImageLoader.HINT_NEW, ImageLoader.NEW_LIGHT, "Ctr + N", "Nástroj Nová mapa se nejdříve zeptá, zda uložit stávající soubor, a poté umožní vytvořit nový."));
		TreeItem<HelpItem> saveFile = new TreeItem<HelpItem>(new HelpItem("Uložit mapu", ImageLoader.HINT_SAVE, ImageLoader.SAVE_LIGHT, "Ctr + S", "Nástroj Uložit mapu uloží mapu ve formě textového souboru."));
		TreeItem<HelpItem> loadFile = new TreeItem<HelpItem>(new HelpItem("Načíst mapu", ImageLoader.HINT_LOAD, ImageLoader.LOAD_LIGHT, "Ctr + L", "Nástroj Načíst mapu se nejdříve zeptá, zda uložit stávající soubor, a poté umožní \nnačíst mapu z textového souboru."));
		TreeItem<HelpItem> export = new TreeItem<HelpItem>(new HelpItem("Exportovat jako obrázek", ImageLoader.HINT_EXPORT, ImageLoader.EXPORT_LIGHT, "Ctr + E", "Nástroj Exportovat jako obrázek exportuje obrázek mapy a uloží jej ve formátu PNG."));
	
		TreeItem<HelpItem> mapItem = new TreeItem<HelpItem>(new HelpItem("Práce s mapou", ImageLoader.HINT_DEFAULT));
		TreeItem<HelpItem> back = new TreeItem<HelpItem>(new HelpItem("Zpět", ImageLoader.HINT_BACK, ImageLoader.BACK_LIGHT, "Ctr + Z", "Nástroj Zpět vrátí mapu do stavu před poslední provedenou akcí."));
		TreeItem<HelpItem> mode = new TreeItem<HelpItem>(new HelpItem("Změna barevného režimu", ImageLoader.HINT_MODE, ImageLoader.MODE_LIGHT, "Ctr + M", "Nástroj Změna barevného režimu poskytuje možnosti přepínat ze světlého režimu na tmavý a naopak."));
		TreeItem<HelpItem> pick = new TreeItem<HelpItem>(new HelpItem("Vybrat", ImageLoader.HINT_PICK, ImageLoader.PICK_LIGHT, "Escape", "Nástroj Vybrat resetuje volbu nástroje i dlaždice."));
		TreeItem<HelpItem> erase = new TreeItem<HelpItem>(new HelpItem("Vymazat", ImageLoader.HINT_ERASE, ImageLoader.ERASE_LIGHT, "Backspace", "Nástroj Vymazat umožňuje kliknout na dlaždici a tím ji vymazat. Zároveň lze nejdříve nástrojem \nVybrat Oblast vybrat dlaždic více a poté kliknout na nástroj Vymazat."));
		TreeItem<HelpItem> eyedropper = new TreeItem<HelpItem>(new HelpItem("Kapátko", ImageLoader.HINT_EYEDROPPER, ImageLoader.EYEDROPPER_LIGHT, "Shift + E", "Nástroj Kapátko dovolí kliknout na neprázdnou dlaždici a tím ji zvolit \njako vybranou dlaždici místo hledání v menu dlaždic."));
		TreeItem<HelpItem> chooseArea = new TreeItem<HelpItem>(new HelpItem("Vybrat oblast", ImageLoader.HINT_CHOOSEAERA, ImageLoader.CHOOSEAREA_LIGHT, "Shift + C", "Nástroj Vybrat oblast umožňuje kliknout na mapu a táhnout, čímž se vybere oblast - více dlaždic."));
		TreeItem<HelpItem> fillArea = new TreeItem<HelpItem>(new HelpItem("Výplň oblasti", ImageLoader.HINT_FILLAREA, ImageLoader.FILLAREA_LIGHT, "Shift + F", "Nástroj Výplň oblasti by měl být použit po nástroji Vybrat oblast. Pro použití musí \nbýt vybraná nějaká dlaždice (z menu dlaždic nebo pomocí nástroje Kapátko). \nNástroj Výplň oblasti poté celou vybranou oblast vyplní vybranou dlaždicí."));
		TreeItem<HelpItem> fillBorders = new TreeItem<HelpItem>(new HelpItem("Výplň hranic", ImageLoader.HINT_FILLBORDERS, ImageLoader.FILLBORDERS_LIGHT, "Shift + B", "Nástroj Výplň hranic by měl být použit po nástroji Vybrat oblast. Pro použití musí \nbýt vybraná nějaká dlaždice (z menu dlaždic nebo pomocí nástroje Kapátko). \nNástroj Výplň hranic poté hranice vybrané oblasti vyplní vybranou dlaždicí."));
		
		TreeItem<HelpItem> menuItem = new TreeItem<HelpItem>(new HelpItem("Menu pro výběr dlaždic", ImageLoader.HINT_DEFAULT));
		TreeItem<HelpItem> up = new TreeItem<HelpItem>(new HelpItem("Nahoru", ImageLoader.HINT_UP, ImageLoader.TRIANGLE_UP, "↑", "Posune výběr dlaždic v menu směrem nahoru."));
		TreeItem<HelpItem> down = new TreeItem<HelpItem>(new HelpItem("Dolů", ImageLoader.HINT_DOWN, ImageLoader.TRIANGLE_DOWN, "↓", "Posune výběr dlaždic v menu směrem dolů."));
		
		TreeItem<HelpItem> otherItem = new TreeItem<HelpItem>(new HelpItem("Ostatní funkce programu", ImageLoader.HINT_DEFAULT));
		TreeItem<HelpItem> settings = new TreeItem<HelpItem>(new HelpItem("Nastavení", ImageLoader.HINT_SETTINGS, ImageLoader.SETTINGS_LIGHT, "Ctrl + O", "Zobrazí okno pro nastavení, kde je možné změnit velikost mapy, počet viditelných dlaždic,\nbarvu planety a barevný režim."));
		TreeItem<HelpItem> help = new TreeItem<HelpItem>(new HelpItem("Nápověda", ImageLoader.HINT_HELP, ImageLoader.HELP_LIGHT, "Ctrl + H", "Zobrazí okno pro nápovědu."));
		TreeItem<HelpItem> about = new TreeItem<HelpItem>(new HelpItem("O aplikaci", ImageLoader.HINT_ABOUT, ImageLoader.ABOUT_LIGHT, "Ctrl + A", "Zobrazí okno s informacemi o aplikaci."));
		
		root.getChildren().addAll(fileItem, mapItem, menuItem, otherItem);
		
		fileItem.getChildren().addAll(newFile, saveFile, loadFile, export);
		mapItem.getChildren().addAll(back, mode, pick, erase, eyedropper, chooseArea, fillArea, fillBorders);
		menuItem.getChildren().addAll(up, down);
		otherItem.getChildren().addAll(settings, help, about);
	}
	
	private void updateHint() {
		HelpItem chosenItem = helpTree.getSelectionModel().getSelectedItem().getValue();
		
		if(chosenItem.getHintImage() != null) {
			hintImage.setImage(chosenItem.getHintImage());
		}
		else {
			hintImage.setImage(ImageLoader.HINT_DEFAULT);
		}
		
		buttonImage.setImage(chosenItem.getButtonImage());
		
		if(chosenItem.getShortcut() != null) {
			shortcutLabel.setText(chosenItem.getShortcut().get());
		}
		else {
			shortcutLabel.setText("");
		}
		
		if(chosenItem.getDescription() != null) {
			descriptionLabel.setText(chosenItem.getDescription().get());
		}
		else {
			descriptionLabel.setText("");
		}
		
	}

}
