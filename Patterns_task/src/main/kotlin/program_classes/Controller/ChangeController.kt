package program_classes.Controller

import AddScene
import program_classes.Model.Student
import program_classes.Model.Student_root
import program_classes.Model.Student_short

class ChangeController(
    scene: AddScene,
    stCont: StudentListController,
    var studentToUpdate: Student_short
): IController {
    override var addScene: AddScene = scene
    override var studentController: StudentListController = stCont

    override fun executeAction() {
        val newStudent = Student(studentToUpdate.id, addScene.nameField.text, addScene.famNameField.text, addScene.fathNameField.text, addScene.phoneField.text, addScene.emailField.text, addScene.githubField.text, addScene.telegramField.text)
        studentController.stList.replace(newStudent, studentToUpdate.id)
        studentController.refreshData()
    }

    override fun initScene() {
        addScene.githubField.isDisable = true
        addScene.telegramField.isDisable = true
        addScene.emailField.isDisable = true
        addScene.phoneField.isDisable = true
    }

    override fun validate() {
        var check: Boolean = true
        if (!Student_root.checkString(addScene.nameField.text)) check = false
        if (!Student_root.checkString(addScene.famNameField.text)) check = false

        if(check){
            addScene.addButton.isDisable = false
        }
        else{
            addScene.addButton.isDisable = true
        }
    }

    fun getStudentData(): Student {
        val extractedStudent = studentController.getStudentById(studentToUpdate!!.id)
        if(extractedStudent != null){
            return extractedStudent
        }
        else{
            return Student(0, "", "", "")
        }
    }
}