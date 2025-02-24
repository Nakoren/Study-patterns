import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.text.Font
import program_classes.Controller.StudentListController
import program_classes.View.GUI.StudentApplication
import program_classes.View.GUI.StudentDisplayPanel

class UpdateDataPanel: GridPane {
    lateinit var stListController: StudentListController

    lateinit var displayPanel: StudentDisplayPanel

    var addScene: AddScene? = null

    val addButton = Button("Add")
    val changeButton = Button("Change")
    val deleteButton = Button("Delete")
    val updateButton = Button("Update")

    constructor(controller: StudentListController, view: StudentApplication): super(){
        stListController = controller
        maxWidth = Double.MAX_VALUE
        alignment = Pos.CENTER
        hgap = 5.0
        padding = Insets(15.0)

        val constraint: ColumnConstraints = ColumnConstraints(10.0, 10.0, Double.MAX_VALUE)
        constraint.hgrow = Priority.ALWAYS
        columnConstraints.add(constraint)

        addButton.font = Font(30.0); addButton.padding = Insets(10.0)
        changeButton.font = Font(30.0); changeButton.padding = Insets(10.0)
        deleteButton.font = Font(30.0); deleteButton.padding = Insets(10.0)
        updateButton.font = Font(30.0); updateButton.padding = Insets(10.0)

        addButton.setOnAction {
            addScene = AddScene(view, controller)
            addScene!!.start()
        }
        changeButton.setOnAction {
            val selectedStudent = displayPanel.selectedStudent
            addScene = AddScene(view, controller, selectedStudent!!)
            addScene!!.start()
        }
        deleteButton.setOnAction {
            val selectedStudent = displayPanel.selectedStudent
            stListController.delete(selectedStudent!!.id)
        }
        updateButton.setOnAction {
            stListController.refreshData()
        }

        add(addButton, 0, 0)
        add(updateButton, 0, 1)
        add(changeButton, 0, 2)
        add(deleteButton, 0, 3)

        disableChange()
        disableDelete()
    }

    fun enableChange(){
        changeButton.isDisable = false
    }

    fun disableChange(){
        changeButton.isDisable = true
    }

    fun enableDelete(){
        deleteButton.isDisable = false
    }

    fun disableDelete(){
        deleteButton.isDisable = true
    }
}