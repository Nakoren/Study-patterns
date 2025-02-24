package program_classes.View.GUI

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.text.Font
import javafx.scene.text.Text
import program_classes.Controller.StudentListController
import program_classes.View.GUI.LocalElements.TextFieldWithNoOption
import program_classes.View.GUI.LocalElements.TextFieldWithOption

class FiltrationPanel: GridPane
{
    lateinit var stListController: StudentListController

    val nameArea: TextFieldWithNoOption = TextFieldWithNoOption("Name")
    val gitArea: TextFieldWithOption = TextFieldWithOption("Git")
    val mailArea: TextFieldWithOption = TextFieldWithOption("Mail")
    val phoneArea: TextFieldWithOption = TextFieldWithOption("Phone")
    val telegramArea: TextFieldWithOption = TextFieldWithOption("Telegram")

    lateinit var displayPanel: StudentDisplayPanel

    constructor(displayPanel: StudentDisplayPanel, controller: StudentListController): super(){
        stListController = controller
        this.displayPanel = displayPanel
        maxWidth = Double.MAX_VALUE
        alignment = Pos.TOP_LEFT
        hgap = 3.0
        padding = Insets(3.0)

        val constraint: ColumnConstraints = ColumnConstraints(75.0, 75.0, Double.MAX_VALUE)
        constraint.hgrow = Priority.ALWAYS
        columnConstraints.add(constraint)

        val panelText: Text = Text("Фильтрация"); panelText.font = Font(15.0)
        add(panelText, 0, 0)
        add(nameArea, 0, 1)
        add(gitArea, 0, 2)
        add(mailArea, 0, 3)
        add(phoneArea, 0, 4)
        add(telegramArea, 0, 5)

        val filterButton = javafx.scene.control.Button("Filter")
        filterButton.maxWidth = Double.MAX_VALUE
        filterButton.font = Font(10.0)
        filterButton.setOnAction {
            controller.refreshData()
        }
        add(filterButton, 0 , 6)
    }
}