package com.exam;

import com.exam.controllers.IClientController;
import com.exam.controllers.ICustomerOrderController;
import com.exam.controllers.impl.ClientController;
import com.exam.controllers.impl.CustomerOrderController;
import com.exam.entites.Article;
import com.exam.entites.Client;
import com.exam.services.impl.ClientService;
import com.exam.services.impl.CustomerOrderService;
import com.exam.repository.impl.ClientRepository;
import com.exam.repository.impl.CustomerOrderRepository;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppView extends Application {

    private TextField phoneField;
    private TextField nameField;
    private TextField addressField;
    private ComboBox<String> articleComboBox;
    private TextField priceField;
    private TextField quantityField;
    private TextField totalField;
    private TableView<Article> articleTable;
    private ObservableList<Article> articleList;
    private Button addButton;
    private Button saveButton;

    private IClientController clientController;
    private ICustomerOrderController orderController;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ajouter une commande");

        clientController = new ClientController(new ClientService(new ClientRepository()));
        orderController = new CustomerOrderController(new CustomerOrderService(new CustomerOrderRepository()));

        // Champs de recherche du client
        phoneField = new TextField();
        phoneField.setPromptText("Numéro de téléphone");
        Button searchButton = new Button("Rechercher");
        searchButton.setOnAction(e -> searchClient());

        // Champs pour afficher les informations du client
        nameField = new TextField();
        nameField.setPromptText("Nom complet");
        nameField.setEditable(false);

        addressField = new TextField();
        addressField.setPromptText("Adresse");
        addressField.setEditable(false);

        // ComboBox pour sélectionner un article
        articleComboBox = new ComboBox<>();
        articleComboBox.setPromptText("Sélectionner un article");
        loadArticles();

        priceField = new TextField();
        priceField.setPromptText("Prix");
        quantityField = new TextField();
        quantityField.setPromptText("Quantité");

        totalField = new TextField();
        totalField.setPromptText("Total");
        totalField.setEditable(false);

        addButton = new Button("Ajouter");
        addButton.setOnAction(e -> addArticle());

        // Table des articles
        articleTable = new TableView<>();
        articleList = FXCollections.observableArrayList();
        articleTable.setItems(articleList);

        TableColumn<Article, String> articleColumn = new TableColumn<>("Article");
        articleColumn.setCellValueFactory(data -> data.getValue().articleProperty());

        TableColumn<Article, String> priceColumn = new TableColumn<>("Prix");
        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty());

        TableColumn<Article, String> quantityColumn = new TableColumn<>("Quantité");
        quantityColumn.setCellValueFactory(data -> data.getValue().quantityProperty());

        TableColumn<Article, String> montantColumn = new TableColumn<>("Montant");
        montantColumn.setCellValueFactory(data -> data.getValue().montantProperty());

        articleTable.getColumns().addAll(articleColumn, priceColumn, quantityColumn, montantColumn);

        // Bouton pour sauvegarder la commande
        saveButton = new Button("valider la commande");
        saveButton.setOnAction(e -> saveOrder());

        // Disposition des éléments
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        HBox clientInfoBox = new HBox(10, new Label("Numéro de téléphone:"), phoneField, searchButton, new Label("Nom complet:"), nameField, new Label("Adresse:"), addressField);
        HBox articleBox = new HBox(10, articleComboBox, priceField, quantityField, addButton);
        HBox totalBox = new HBox(10, new Label("Total:"), totalField);
        VBox vbox = new VBox(10, clientInfoBox, articleBox, articleTable, totalBox, saveButton);

        Scene scene = new Scene(vbox, 800, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void searchClient() {
        String phoneNumber = phoneField.getText();
        // Logique pour rechercher le client par numéro de téléphone
        Client client = clientController.findClientByPhoneNumber(phoneNumber);
        if (client != null) {
            nameField.setText(client.getFullName());
            addressField.setText(client.getAddress());
            // Activer la partie commande
            enableOrderSection(true);
        } else {
            nameField.clear();
            addressField.clear();
            // Désactiver la partie commande
            enableOrderSection(false);
        }
    }

    private void enableOrderSection(boolean enable) {
        articleComboBox.setDisable(!enable);
        priceField.setDisable(!enable);
        quantityField.setDisable(!enable);
        addButton.setDisable(!enable);
        saveButton.setDisable(!enable);
    }

    private void loadArticles() {
        // Logique pour charger les articles depuis la base de données
        ObservableList<String> articles = FXCollections.observableArrayList(
            "Article 1", "Article 2", "Article 3"
        );
        articleComboBox.setItems(articles);
    }

    private void addArticle() {
        String article = articleComboBox.getValue();
        String price = priceField.getText();
        String quantity = quantityField.getText();

        if (article != null && !price.isEmpty() && !quantity.isEmpty()) {
            double priceValue = Double.parseDouble(price);
            int quantityValue = Integer.parseInt(quantity);
            double montantValue = priceValue * quantityValue;

            articleList.add(new Article(article, price, quantity, String.valueOf(montantValue)));
            articleComboBox.getSelectionModel().clearSelection();
            priceField.clear();
            quantityField.clear();

            updateTotal();
        }
    }

    private void updateTotal() {
        double total = articleList.stream()
                .mapToDouble(article -> Double.parseDouble(article.getMontant()))
                .sum();
        totalField.setText(String.valueOf(total));
    }

    private void saveOrder() {
        // Logique pour sauvegarder la commande
    }

    public static class Article {
        private final StringProperty article;
        private final StringProperty price;
        private final StringProperty quantity;
        private final StringProperty montant;

        public Article(String article, String price, String quantity, String montant) {
            this.article = new SimpleStringProperty(article);
            this.price = new SimpleStringProperty(price);
            this.quantity = new SimpleStringProperty(quantity);
            this.montant = new SimpleStringProperty(montant);
        }

        public StringProperty articleProperty() {
            return article;
        }

        public StringProperty priceProperty() {
            return price;
        }

        public StringProperty quantityProperty() {
            return quantity;
        }

        public StringProperty montantProperty() {
            return montant;
        }

        public String getMontant() {
            return montant.get();
        }
    }
}