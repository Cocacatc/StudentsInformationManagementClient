module com.example.studentsinformationmanagementclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens com.example.studentsinformationmanagementclient to javafx.fxml;
    exports com.example.studentsinformationmanagementclient;
    opens com.example.studentsinformationmanagementclient.request to  com.google.gson;
    exports com.example.studentsinformationmanagementclient.controller to javafx.fxml, com.google.gson;
    opens com.example.studentsinformationmanagementclient.controller to javafx.fxml, com.google.gson;
    opens com.example.studentsinformationmanagementclient.model to javafx.base, com.google.gson;
    exports com.example.studentsinformationmanagementclient.model to javafx.base, com.google.gson;

}