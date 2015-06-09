package seeurrenamer.main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import seeurrenamer.main.model.RenameMethod;
import seeurrenamer.main.model.SelectedPath;
import seeurrenamer.main.util.PathRenamer;
import seeurrenamer.main.util.PositionalPathRenamer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertOverwriteManipulatorController implements Initializable {
	@FXML
	private ComboBox<String> operationModeComboBox;

	@FXML
	private ComboBox<String> directionComboBox;

	@FXML
	private TextField newStringTextField;

	@FXML
	private Spinner<Integer> indexSpinner;

	private ObservableList<SelectedPath> selectedPathList;

	private RenameMethod renameMethod;

	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.operationModeComboBox.getItems().setAll(
				PositionalPathRenamer.INSERT_OPERATION,
				PositionalPathRenamer.OVERWRITE_OPERATION);
		this.operationModeComboBox
				.setValue(PositionalPathRenamer.INSERT_OPERATION);
		this.directionComboBox.getItems().setAll(
				PositionalPathRenamer.LEFT_SIDE,
				PositionalPathRenamer.RIGHT_SIDE);
		this.directionComboBox.setValue(PositionalPathRenamer.LEFT_SIDE);
		this.indexSpinner
				.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
						0, 3000, 0));
	}

	@FXML
	public void handleRenameOperation() {
		PathRenamer pathRenamer = new PositionalPathRenamer(
				this.operationModeComboBox.getValue(),
				this.directionComboBox.getValue(), this.indexSpinner.getValue());
		this.selectedPathList.setAll(pathRenamer.rename(
				this.selectedPathList, this.newStringTextField.getText()));
		System.out.println(this.newStringTextField.getText());
	}
	
	@FXML
	public void handleOkButton() {
		this.stage.close();
	}

	public void setSelectedPathList(
			ObservableList<SelectedPath> selectedPathList) {
		this.selectedPathList = selectedPathList;
	}

	public void setRenameMethod(RenameMethod renameMethod) {
		this.renameMethod = renameMethod;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
