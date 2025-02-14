package program_classes.Controller

import AddScene
import program_classes.View.GUI.StudentListController

interface IController{
    var addScene: AddScene
    var studentController: StudentListController

    abstract fun initScene()

    abstract fun validate()
}