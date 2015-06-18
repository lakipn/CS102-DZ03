/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102dz03;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 *
 * @author lakipn
 */
public class SetControlsOnPane {
    
    /**
     * @param root
     * @param label
     * @param layoutX
     * @param layoutY
     * @param font
     * @param alignment
     * @param prefWidth
     */
    public static void setLabelOnPane(Pane root, Label label, int layoutX,
            int layoutY, Font font, Pos alignment, int prefWidth)
    {
        
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setFont(font);
        if(alignment != null)
            label.setAlignment(alignment);
        if(prefWidth != 0)
            label.prefWidth(prefWidth);
        
        root.getChildren().add(label);
    }
    
    /**
     *
     * @param root
     * @param text
     * @param layoutX
     * @param layoutY
     * @param prefWidth
     * @param prefHeight
     * @param font
     */
    public static void setTextAreaOnPane(Pane root, TextArea text, int layoutX,
            int layoutY, int prefWidth, int prefHeight, Font font)
    {
        text.setLayoutX(layoutX);
        text.setLayoutY(layoutY);
        text.setPrefWidth(prefWidth);
        text.setPrefHeight(prefHeight);
        text.setFont(font);
        
        root.getChildren().add(text);
    }
    
    /**
     * 
     * @param root
     * @param button
     * @param layoutX
     * @param layoutY
     * @param prefWidth
     * @param prefHeight
     * @param font
     * @param eventHandler 
     */
    public static void setButtonOnPane(Pane root, Button button, int layoutX,
            int layoutY, int prefWidth, int prefHeight, Font font,
            EventHandler<ActionEvent> eventHandler)
    {
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefWidth(prefWidth);
        button.setPrefHeight(prefHeight);
        button.setFont(font);
        
        button.setOnAction(eventHandler);
        
        root.getChildren().add(button);
    }
    
    /**
     * 
     * @param root
     * @param text
     * @param layoutX
     * @param layoutY
     * @param prefWidth
     * @param prefHeight
     * @param font
     * @param eventHandler 
     */
    public static void setTextFieldOnPane(Pane root, TextField text, int layoutX,
            int layoutY, int prefWidth, int prefHeight, Font font,
            EventHandler<KeyEvent> eventHandler)
    {
        text.setLayoutX(layoutX);
        text.setLayoutY(layoutY);
        text.setPrefWidth(prefWidth);
        text.setPrefHeight(prefHeight);
        text.setFont(font);
        
        text.setOnKeyPressed(eventHandler);
        
        root.getChildren().add(text);
    }
    
    /**
     * 
     * @param root
     * @param pBar
     * @param lblPercentage
     * @param layoutX
     * @param layoutY
     * @param prefWidth
     * @param prefHeight
     * @param progress
     * @param labelPercentageLayoutX
     * @param labelPercentageLayoutY
     * @param labelPercentagePrefWidth 
     */
    public static void setProgressBarOnPane(Pane root, ProgressBar pBar, Label lblPercentage,
            int layoutX, int layoutY, int prefWidth, int prefHeight,
            int progress, int labelPercentageLayoutX, int labelPercentageLayoutY,
            int labelPercentagePrefWidth)
    {
        pBar.setLayoutX(layoutX);
        pBar.setLayoutY(layoutY);
        pBar.setPrefWidth(prefWidth);
        pBar.setPrefHeight(prefHeight);
        pBar.setProgress(progress);
        pBar.setVisible(true);
        
        lblPercentage.setLayoutX(labelPercentageLayoutX);
        lblPercentage.setLayoutY(labelPercentageLayoutY);
        lblPercentage.setPrefWidth(labelPercentagePrefWidth);
        
        root.getChildren().add(pBar);
        root.getChildren().add(lblPercentage);
    }
}
