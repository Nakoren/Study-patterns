package program_classes.View.GUI.Interfaces

import program_classes.View.GUI.StudentApplication
import program_classes.View.GUI.StudentListController

interface IViewListener {
    fun notify(view: StudentApplication)
}