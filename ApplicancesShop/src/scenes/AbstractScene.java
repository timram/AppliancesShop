package scenes;

import java.util.Optional;

import main.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public abstract class AbstractScene extends BorderPane{
	protected boolean initialized;
	protected MainApp app;
	private Alert alertInfo;
	private Alert alertConfirm;
	
	public AbstractScene(MainApp app) {
		this.app = app;
		initialized = false;
		alertInfo = new Alert(AlertType.INFORMATION);
		alertConfirm = new Alert(AlertType.CONFIRMATION);
		alertConfirm.setWidth(500);
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	protected void addTextLimiter(TextField tf, int maxLength) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
				if(newVal.length() > maxLength) {
					tf.setText(oldVal);
				}
			}
		});
	}
	
	protected void bindTextFieldClearText(TextField tf, Text text) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				text.setText("");
			}
		});
	}
	
	protected void showMessage(String message) {
		alertInfo.setContentText(message);
		alertInfo.showAndWait();
	}
	
	protected boolean showConfirmation(String message) {
		alertConfirm.setContentText(message);
		Optional<ButtonType> result = alertConfirm.showAndWait();
		alertConfirm = new Alert(AlertType.CONFIRMATION);
		return (result.get() == ButtonType.OK) ? true : false;
	}
	
	protected Label makeLabel(String name) {
		Label label = new Label(name);
		label.setFont(Font.font("SansSerif", FontWeight.NORMAL, 20));
		return label;
	}
	
	protected Label makeLabel() {
		Label label = new Label();
		label.setFont(Font.font("SansSerif", FontWeight.NORMAL, 20));
		label.setTextFill(Color.DARKSLATEBLUE);
		return label;
	}
	
	public abstract void init();
	public abstract void reset();
}
