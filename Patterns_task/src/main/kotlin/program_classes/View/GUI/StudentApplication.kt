package program_classes.View.GUI

import UpdateDataPanel
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.layout.*
import javafx.stage.Stage
import program_classes.Controller.StudentListController
import program_classes.Model.IListUpdateListener
import program_classes.Model.StudentLists.Student_list
import program_classes.Model.Student_short

class StudentApplication: IListUpdateListener<Student_short>{

    lateinit var dataUpdateDataPanel: UpdateDataPanel
    lateinit var stListController: StudentListController
    lateinit var displayPanel: StudentDisplayPanel
    lateinit var filtrationPanel: FiltrationPanel

    constructor(stList: Student_list){
        stListController = StudentListController(this, stList)
        dataUpdateDataPanel = UpdateDataPanel(stListController, this)
        displayPanel = StudentDisplayPanel(dataUpdateDataPanel, stListController)
        filtrationPanel = FiltrationPanel(displayPanel = displayPanel, stListController)
        dataUpdateDataPanel.displayPanel = displayPanel
    }

    fun start() {
        val stage: Stage = Stage()

        stage.title = "Student control app"
        val gridPane: GridPane = GridPane()
        gridPane.style = "-fx-background-color: gray"
        gridPane.alignment = Pos.CENTER
        gridPane.hgap = 10.0
        gridPane.vgap = 10.0
        gridPane.padding = Insets(10.0, 10.0, 10.0, 10.0)
        val area1: ColumnConstraints = ColumnConstraints(150.0, 150.0, Double.MAX_VALUE)
        area1.hgrow = Priority.ALWAYS
        val area2: ColumnConstraints = ColumnConstraints(400.0, 400.0, Double.MAX_VALUE)
        area2.hgrow = Priority.ALWAYS
        val area3: ColumnConstraints = ColumnConstraints(150.0, 150.0, Double.MAX_VALUE)
        area3.hgrow = Priority.ALWAYS
        gridPane.isGridLinesVisible = true
        gridPane.columnConstraints.addAll(area1, area2, area3)

        gridPane.add(filtrationPanel, 0, 0)
        gridPane.add(displayPanel, 1, 0)
        gridPane.add(dataUpdateDataPanel, 2,0)

        val scene: Scene = Scene(gridPane, 900.0, 450.0)
        stage.scene = scene

        stage.show()

        stListController.refreshData()
    }

    fun refresh(){
        stListController.refreshData()
    }

    override fun notifyListUpdate(newList: List<Student_short>) {
        displayPanel.updateObsList(newList)
    }

}