package program_classes.View.GUI

import AddScene
import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.*
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage
import program_classes.Model.DataList
import program_classes.View.GUI.ClassViews.StudentShortView
import program_classes.View.GUI.LocalElements.TextFieldWithNoOption
import program_classes.View.GUI.LocalElements.TextFieldWithOption
import program_classes.Model.Student
import program_classes.Model.StudentLists.Student_list
import program_classes.Model.Student_short
import program_classes.View.GUI.ClassViews.FilterView

class StudentApplication{

    lateinit var dataUpdateDataPanel: UpdateDataPanel
    lateinit var stListController: StudentListController
    lateinit var displayPanel: StudentDisplayPanel
    lateinit var filtrationPanel: FiltrationPanel

    constructor(){
        stListController = StudentListController(this)
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
        gridPane.padding = Insets(25.0, 25.0, 25.0, 25.0)
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

        val scene: Scene = Scene(gridPane, 1700.0, 900.0)
        stage.scene = scene

        stage.show()

        stListController.refreshData()
    }

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
            hgap = 5.0
            padding = Insets(25.0)

            val constraint: ColumnConstraints = ColumnConstraints(150.0, 150.0, Double.MAX_VALUE)
            constraint.hgrow = Priority.ALWAYS
            columnConstraints.add(constraint)

            val panelText: Text = Text("Фильтрация"); panelText.font = Font(30.0)
            add(panelText, 0, 0)
            add(nameArea, 0, 1)
            add(gitArea, 0, 2)
            add(mailArea, 0, 3)
            add(phoneArea, 0, 4)
            add(telegramArea, 0, 5)

            val filterButton = javafx.scene.control.Button("Filter")
            filterButton.maxWidth = Double.MAX_VALUE
            filterButton.font = Font(25.0)
            filterButton.setOnAction {
                controller.refreshData()
            }
            add(filterButton, 0 , 6)
        }
    }

    class StudentDisplayPanel(): VBox() {
        lateinit var stListController: StudentListController
        var table: TableView<StudentShortView> = TableView()
        var selectedStudent: Student_short? = null

        lateinit var pageLabel: Label
        val leftButton: Button = Button("<")
        val rightButton: Button = Button(">")

        lateinit var stList: List<Student_short>
        var observableList: ObservableList<StudentShortView> = FXCollections.observableArrayList()

        lateinit var updateDataPanel: UpdateDataPanel

        constructor(updatePanel: UpdateDataPanel, controller: StudentListController):this(){
            stListController = controller
            updateDataPanel = updatePanel
            maxWidth = Double.POSITIVE_INFINITY
            alignment = Pos.CENTER
            pageLabel = Label((stListController.currentPage + 1).toString())

            if(stListController.currentPage == stListController.totalCount-1){
                rightButton.isDisable = true
            }

            formTable()
            children.add(table)
            table.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE)

            leftButton.isDisable = true
            pageLabel.maxWidth = 100.0
            var bottomList: HBox = HBox()

            leftButton.setOnAction {
                goToPrevPage()
            }
            rightButton.setOnAction {
                goToNextPage()
            }

            bottomList.alignment = Pos.CENTER
            bottomList.spacing = 5.0
            bottomList.padding = Insets(10.0)

            leftButton.maxWidth = Double.MAX_VALUE
            rightButton.maxWidth = Double.MAX_VALUE
            pageLabel.maxWidth = Double.MAX_VALUE
            bottomList.children.addAll(leftButton, pageLabel, rightButton)

            children.add(bottomList)

            var totalPageCountLabel: Label = Label((stListController.totalCount).toString())
            totalPageCountLabel.alignment = Pos.CENTER

            children.add(totalPageCountLabel)
        }

        private fun formTable() {
            table = TableView<StudentShortView>(observableList)

            val idColumn: TableColumn<StudentShortView, Int> = TableColumn<StudentShortView, Int>("ID")
            idColumn.cellValueFactory = PropertyValueFactory<StudentShortView, Int>("id")
            table.columns += idColumn

            val nameColumn: TableColumn<StudentShortView, String> = TableColumn<StudentShortView, String>("Name")
            nameColumn.cellValueFactory = PropertyValueFactory<StudentShortView, String>("name")
            table.columns.add(nameColumn)

            val contactColumn: TableColumn<StudentShortView, String> = TableColumn<StudentShortView, String>("Contact")
            contactColumn.cellValueFactory = PropertyValueFactory<StudentShortView, String>("contact")
            table.columns.add(contactColumn)

            val gitColumn: TableColumn<StudentShortView, String> = TableColumn<StudentShortView, String>("Git")
            gitColumn.cellValueFactory = PropertyValueFactory<StudentShortView, String>("git")
            table.columns.add(gitColumn)

            var selectionModel: TableView.TableViewSelectionModel<StudentShortView> = table.selectionModel
            selectionModel.selectedItemProperty().addListener(ChangeListener<StudentShortView> { _, _, studentShortNew ->
                run {
                    if (studentShortNew == null) {
                        updateDataPanel.disableChange()
                        updateDataPanel.disableDelete()
                    } else {
                        updateDataPanel.enableChange()
                        updateDataPanel.enableDelete()
                    }
                    selectedStudent = Student_short(studentShortNew)
                }
            })
        }

        fun updateObsList(list: List<Student_short>){
            val pageListView: MutableList<StudentShortView> = mutableListOf<StudentShortView>()

            for (st in list){
                pageListView.add(StudentShortView(st))
            }
            observableList = FXCollections.observableArrayList(pageListView)
            table.items = observableList

            if(stListController.currentPage == stListController.totalCount-1){
                rightButton.isDisable = true
            }
        }

        fun updateTable(){
            observableList = stListController.getObservableList()!!
            table.items = observableList
        }

        fun goToNextPage(){
            stListController.currentPage+=1
            pageLabel.text = (stListController.currentPage+1).toString()
            if(stListController.currentPage == stListController.totalCount-1){
                rightButton.isDisable = true
            }
            leftButton.isDisable = false
            updateTable()
        }

        fun goToPrevPage(){
            stListController.currentPage-=1
            pageLabel.text = (stListController.currentPage+1).toString()
            if(stListController.currentPage == 0){
                leftButton.isDisable = true
            }
            rightButton.isDisable = false
            updateTable()
        }
    }

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
            padding = Insets(25.0)

            val constraint: ColumnConstraints = ColumnConstraints(10.0, 10.0, Double.MAX_VALUE)
            constraint.hgrow = Priority.ALWAYS
            columnConstraints.add(constraint)

            addButton.font = Font(30.0); addButton.padding = Insets(25.0)
            changeButton.font = Font(30.0); changeButton.padding = Insets(25.0)
            deleteButton.font = Font(30.0); deleteButton.padding = Insets(25.0)
            updateButton.font = Font(30.0); updateButton.padding = Insets(25.0)

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
                stListController.stList.delete(selectedStudent!!.id)
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

}