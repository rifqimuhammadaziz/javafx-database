package rifqimuhammadaziz.javafxdatabase.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import rifqimuhammadaziz.javafxdatabase.util.MySQLConnection;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintReportController {

    @FXML
    private void printAction(ActionEvent actionEvent) {
        // Create new thread (parallel processing to avoid not responding application)
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                HashMap parameters = new HashMap();
                JasperPrint jp = JasperFillManager.fillReport("report/report01.jasper", parameters, MySQLConnection.createConnection());
                JasperViewer viewer = new JasperViewer(jp, false);
                viewer.setVisible(true);
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }
}
