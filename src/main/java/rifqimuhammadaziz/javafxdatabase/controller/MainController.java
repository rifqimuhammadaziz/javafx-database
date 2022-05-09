package rifqimuhammadaziz.javafxdatabase.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import rifqimuhammadaziz.javafxdatabase.dao.DepartmentDaoImpl;
import rifqimuhammadaziz.javafxdatabase.dao.FacultyDaoImpl;
import rifqimuhammadaziz.javafxdatabase.entity.Department;
import rifqimuhammadaziz.javafxdatabase.entity.Faculty;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField txtFacultyName;
    @FXML
    private TableView<Faculty> tableFaculty;
    @FXML
    private TableColumn<Faculty, Integer> facultyCol01;
    @FXML
    private TableColumn<Faculty, String> facultyCol02;
    @FXML
    private TextField txtDepartmentName;
    @FXML
    private ComboBox<Faculty> comboFaculty;
    @FXML
    private TableView<Department> tableDepartment;
    @FXML
    private TableColumn<Department, Integer> departmentCol01;
    @FXML
    private TableColumn<Department, String> departmentCol02;
    @FXML
    private TableColumn<Department, Faculty> departmentCol03;
    @FXML
    private Button btnSaveFaculty;
    @FXML
    private Button btnResetFaculty;
    @FXML
    private Button btnUpdateFaculty;
    @FXML
    private Button btnDeleteFaculty;
    @FXML
    private Button btnSaveDepartment;
    @FXML
    private Button btnResetDepartment;
    @FXML
    private Button btnUpdateDepartment;
    @FXML
    private Button btnDeleteDepartment;

    private ObservableList<Department> departments;
    private ObservableList<Faculty> faculties;
    private DepartmentDaoImpl departmentDao;
    private FacultyDaoImpl facultyDao;
    private Faculty selectedFaculty;
    private Department selectedDepartment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facultyDao = new FacultyDaoImpl();
        departmentDao = new DepartmentDaoImpl();
        faculties = FXCollections.observableArrayList();
        departments = FXCollections.observableArrayList();

        try {
            faculties.addAll(facultyDao.getAll());
            departments.addAll(departmentDao.getAll());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        comboFaculty.setItems(faculties);
        tableFaculty.setItems(faculties);
        facultyCol01.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        facultyCol02.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        tableDepartment.setItems(departments);
        departmentCol01.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        departmentCol02.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        departmentCol03.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFaculty()));
    }

    @FXML
    private void saveFacultyAction(ActionEvent actionEvent) {
        if (txtFacultyName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill faculty name!");
            alert.showAndWait();
        } else {
            Faculty faculty = new Faculty();
            faculty.setName(txtFacultyName.getText().trim());
            try {
                if (facultyDao.addData(faculty) == 1) {
                    faculties.clear();
                    faculties.addAll(facultyDao.getAll());
                    resetFaculty();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveDepartmentAction(ActionEvent actionEvent) {
        if (txtDepartmentName.getText().trim().isEmpty() || comboFaculty.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill department name & select faculty!");
            alert.showAndWait();
        } else {
            Department department = new Department();
            department.setName(txtDepartmentName.getText().trim());
            department.setFaculty(comboFaculty.getValue());
            try {
                if (departmentDao.addData(department) == 1) {
                    departments.clear();
                    departments.addAll(departmentDao.getAll());
                    resetDepartment();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void updateFacultyAction(ActionEvent actionEvent) {
        if (txtFacultyName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill faculty name!");
            alert.showAndWait();
        } else {
            selectedFaculty.setName(txtFacultyName.getText().trim());
            try {
                if (facultyDao.updateData(selectedFaculty) == 1) {
                    faculties.clear();
                    faculties.addAll(facultyDao.getAll());
                    resetFaculty();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void deleteFacultyAction(ActionEvent actionEvent) {
        deleteObject(selectedFaculty);
    }

    @FXML
    private void tableFacultyClicked(MouseEvent mouseEvent) {
        selectedFaculty = tableFaculty.getSelectionModel().getSelectedItem();
        if (selectedFaculty != null) {
            txtFacultyName.setText(selectedFaculty.getName());
            btnSaveFaculty.setDisable(true);
            btnUpdateFaculty.setDisable(false);
            btnDeleteFaculty.setDisable(false);
        }
    }

    @FXML
    private void updateDepartmentAction(ActionEvent actionEvent) {
        if (txtDepartmentName.getText().trim().isEmpty() || comboFaculty == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill department name and faculty!");
            alert.showAndWait();
        } else {
            selectedDepartment.setName(txtDepartmentName.getText().trim());
            selectedDepartment.setFaculty(comboFaculty.getValue());
            try {
                if (departmentDao.updateData(selectedDepartment) == 1) {
                    departments.clear();
                    departments.addAll(departmentDao.getAll());
                    resetDepartment();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void deleteDepartmentAction(ActionEvent actionEvent) {
        deleteObject(selectedDepartment);
    }

    @FXML
    private void tableDepartmentClicked(MouseEvent mouseEvent) {
        selectedDepartment = tableDepartment.getSelectionModel().getSelectedItem();
        if (selectedDepartment != null) {
            txtDepartmentName.setText(selectedDepartment.getName());
            comboFaculty.setValue(selectedDepartment.getFaculty());
            btnSaveDepartment.setDisable(true);
            btnUpdateDepartment.setDisable(false);
            btnDeleteDepartment.setDisable(false);
        }
    }

    private void resetFaculty() {
        txtFacultyName.clear();
        selectedFaculty = null;
        btnSaveFaculty.setDisable(false);
        btnUpdateFaculty.setDisable(true);
        btnDeleteFaculty.setDisable(true);
    }

    private void resetDepartment() {
        txtDepartmentName.clear();
        selectedDepartment = null;
        btnSaveDepartment.setDisable(false);
        btnUpdateDepartment.setDisable(true);
        btnDeleteDepartment.setDisable(true);
    }

    private void deleteObject(Object object) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure want to delete data?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            if (object instanceof Faculty) {
                try {
                    if (facultyDao.deleteData(selectedFaculty) == 1) {
                        faculties.clear();
                        faculties.addAll(facultyDao.getAll());
                        resetFaculty();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (object instanceof Department) {
                try {
                    if (departmentDao.deleteData(selectedDepartment) == 1) {
                        departments.clear();
                        departments.addAll(departmentDao.getAll());
                        resetDepartment();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
