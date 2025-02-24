package program_classes.Controller

import AddScene
import program_classes.Model.Student
import program_classes.Model.Student_short

interface IController{
    var addScene: AddScene
    var studentController: StudentListController

    abstract fun executeAction()

    abstract fun initScene()

    abstract fun validate()
}