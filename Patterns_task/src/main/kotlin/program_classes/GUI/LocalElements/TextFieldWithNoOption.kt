package program_classes.GUI.LocalElements

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.text.Font
import javafx.scene.text.Text

class TextFieldWithNoOption(): GridPane() {
    var text: String = ""
    constructor(text: String): this(){
        this.text = text
        alignment = Pos.TOP_LEFT
        hgap = 3.0
        vgap = 3.0
        padding = Insets(15.0)
        val constraint: ColumnConstraints = ColumnConstraints(150.0, 150.0, Double.MAX_VALUE)
        constraint.hgrow = Priority.ALWAYS
        columnConstraints.add(constraint)

        val textNode: Text = Text(text); textNode.font = Font(25.0);
        add(textNode, 0, 0)
        val textField: TextField = TextField()
        add(textField, 0, 1)

    }
}