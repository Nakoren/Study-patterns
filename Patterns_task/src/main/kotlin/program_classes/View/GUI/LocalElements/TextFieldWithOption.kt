package program_classes.View.GUI.LocalElements

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.text.Font
import javafx.scene.text.Text

class TextFieldWithOption(): GridPane() {
    var text: String = ""
    lateinit var textField: TextField
    lateinit var comboBox: ComboBox<String>

    fun getValue(): String {
        if (textField.text == null){
            return ""
        }
        return textField.text
    }
    fun getChoiceValue(): Int{
        if(comboBox.value == "Yes") return 1
        if(comboBox.value == "No") return -1
        return 0
    }

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
        textField = TextField()
        textField.isDisable = true
        add(textField, 0, 1)

        val obsList: ObservableList<String> = FXCollections.observableArrayList("Yes", "No", "Doesn't matter")
        comboBox = ComboBox(obsList)
        comboBox.setOnAction {
            val value = comboBox.value
            if(value == "Yes"){
                textField.isDisable = false
            }
            else{
                textField.text = ""
                textField.isDisable = true
            }
        }
        comboBox.value = "Doesn't matter"
        add(comboBox, 0, 2)
    }
}