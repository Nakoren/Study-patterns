import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import program_classes.Controller.AddController
import program_classes.Controller.ChangeController
import program_classes.Controller.IController
import program_classes.Model.Student
import program_classes.Model.Student_short
import program_classes.View.GUI.StudentApplication
import program_classes.Controller.StudentListController

class AddScene(
    var mainView: StudentApplication,
    var controller: StudentListController
): Stage() {
    lateinit var sceneNode: Scene

    var stageController: IController = AddController(this, controller)

    val githubField = TextField()
    val fathNameField = TextField()
    val nameField = TextField()
    val famNameField = TextField()
    val phoneField = TextField()
    val telegramField = TextField()
    val emailField = TextField()
    val addButton = Button("Ok")

    val fields = listOf(nameField, famNameField, fathNameField, githubField, phoneField, telegramField, emailField)

    fun start(){
        title = "Add student"

        val grid = GridPane()
        grid.padding = Insets(10.0)
        grid.hgap = 10.0
        grid.vgap = 10.0

        if(stageController is ChangeController){
            val chController = stageController as ChangeController
            val extractedStudent = chController.getStudentData()
            nameField.text = extractedStudent.name
            famNameField.text = extractedStudent.fam_name
            fathNameField.text = extractedStudent.father_name
        }

        val nameLabel = Label("Name:")
        grid.add(nameLabel, 0, 0)
        grid.add(nameField, 1, 0)

        val famNameLabel = Label("Family name:")
        grid.add(famNameLabel, 0, 1)
        grid.add(famNameField, 1, 1)

        val fathNameLabel = Label("Father's name:")
        grid.add(fathNameLabel, 0, 2)
        grid.add(fathNameField, 1, 2)

        val phoneLabel = Label("Phone:")
        grid.add(phoneLabel, 0, 3)
        grid.add(phoneField, 1, 3)

        val telegramLabel = Label("Telegram:")
        grid.add(telegramLabel, 0, 4)
        grid.add(telegramField, 1, 4)

        val emailLabel = Label("Email:")
        grid.add(emailLabel, 0, 5)
        grid.add(emailField, 1, 5)

        val githubLabel = Label("GitHub:")
        grid.add(githubLabel, 0, 6)
        grid.add(githubField, 1, 6)

        grid.add(addButton, 0,7, 1, 2)
        addButton.isDisable = true
        addButton.setOnAction {
            onButtonClick()
        }
        stageController.initScene()
        validateFields()

        fields.forEach { field -> field.textProperty().addListener { _, _, _ -> validateFields() } }
        sceneNode = Scene(grid, 300.0, 300.0)
        scene = sceneNode
        show()
    }

    fun validateFields(){
        stageController.validate()
    }

    constructor(view: StudentApplication, controller: StudentListController, stud: Student_short): this(view, controller){
        stageController = ChangeController(this, controller, stud)
    }

    fun onButtonClick(){
        stageController.executeAction()
        close()
    }
}