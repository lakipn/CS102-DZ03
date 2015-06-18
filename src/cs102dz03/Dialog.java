/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102dz03;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author lakipn
 */
public class Dialog {
    
    public static void showDialog(Window window) {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Ko zna - zna");
        dialogStage.setWidth(170);
        dialogStage.setHeight(130);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button dialogStageBtn = new Button("OK");
        dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        dialogStageBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        dialogStage.setScene(new Scene(VBoxBuilder.create().
            children(new Text("\nIgra je završena!\n"), dialogStageBtn).
            alignment(Pos.CENTER).padding(new Insets(5)).build()));
        
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(window);
        dialogStage.show();
    }
    
}
