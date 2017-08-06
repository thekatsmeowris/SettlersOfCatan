/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author makogenq
 */
public class HexToken {
            Circle circle = new Circle(15);
            Text t1=t1 = new Text(20, 20, "");
            
            HexToken(int tokenValue, double inRadius, int maxColumns, int columnMax, int j, double hexStartingPointY){
            
            circle.setFill(Color.rgb(250, 235, 215, 0.7));
            circle.setLayoutX(200 + (inRadius * (maxColumns - columnMax)) + (inRadius * j * 2));
            circle.setLayoutY(hexStartingPointY);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(2.0);
            t1.setText(""+tokenValue);
            t1.setFont(Font.font("Impact", FontWeight.BOLD, 14));
            t1.setLayoutX(175 + (inRadius * (maxColumns - columnMax)) + (inRadius * j * 2));
            t1.setLayoutY(hexStartingPointY - inRadius + 28);
            t1.setRotate(270);
            t1.setTextAlignment(TextAlignment.CENTER);
            }

}
