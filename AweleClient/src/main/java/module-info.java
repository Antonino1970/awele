module ITLG.toni.aweleClientFx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires gson;
    requires org.kordamp.bootstrapfx.core;
    requires okhttp;
    requires logging.interceptor;
    requires okio;
    requires threetenbp;
    requires java.annotation;
    requires swagger.annotations;
    requires gson.fire;
    requires java.sql;

    exports ITLG.toni.aweleClientFx;
    exports io.swagger.client.model;
    opens io.swagger.client.model to gson, javafx.fxml;
    opens ITLG.toni.aweleClientFx to gson, javafx.fxml;
}