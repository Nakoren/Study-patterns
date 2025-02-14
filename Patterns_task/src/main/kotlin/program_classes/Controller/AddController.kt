package program_classes.Controller

import AddScene
import program_classes.Model.Student_root
import program_classes.View.GUI.StudentListController

class AddController(
    scene: AddScene,
    stCont: StudentListController
): IController {
    override var addScene: AddScene = scene
    override var studentController: StudentListController = stCont

    override fun initScene() {

    }

    override fun validate() {
        var check: Boolean = true
        if (!Student_root.checkString(addScene.nameField.text)) check = false
        if (!Student_root.checkString(addScene.famNameField.text)) check = false
        if (!Student_root.checkPhone(addScene.phoneField.text)) check = false
        if (!Student_root.checkEmail(addScene.emailField.text)) check = false

        if(check){
            addScene.addButton.isDisable = false
        }
        else{
            addScene.addButton.isDisable = true
        }
    }
}