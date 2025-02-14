package program_classes.GUI

import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.HPos
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
import program_classes.DataList
import program_classes.GUI.ClassViews.StudentShortView
import program_classes.GUI.LocalElements.TextFieldWithNoOption
import program_classes.GUI.LocalElements.TextFieldWithOption
import program_classes.Student
import program_classes.StudentLists.Student_list
import program_classes.Student_short

class StudentApplication{

    fun start() {
        val studentList = Student_list()
        studentList.add(Student(1, "Name1", "FamName1", "FathName1", phone = "+79181232323", email = null, git = "Git1", telegram = "Tel1"))
        studentList.add(Student(2, "Name2", "FamName2", "FathName2", phone = "+79181232323", email = null, git = "Git2", telegram = "Tel2"))
        studentList.add(Student(3, "Name3", "FamName3", "FathName3", phone = "+79181232323", email = null, git = "Git3", telegram = "Tel3"))
        studentList.add(Student(4, "Name4", "FamName4", "FathName4", phone = "+79181232323", email = null, git = "Git4", telegram = "Tel4"))
        studentList.add(Student(5, "Name5", "FamName5", "FathName5", phone = "+79181232323", email = null, git = "Git5", telegram = "Tel5"))
        studentList.add(Student(6, "Name6", "FamName6", "FathName6", phone = "+79181232323", email = null, git = "Git6", telegram = "Tel6"))

        val dataUpdateDataPanel = UpdateDataPanel()
        val displayPanel = StudentDisplayPanel(studentList, dataUpdateDataPanel)
        val filtrationPanel = FiltrationPanel(displayPanel = displayPanel)

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
    }

    class FiltrationPanel: GridPane
    {
        val nameArea: GridPane = TextFieldWithNoOption("Name")
        val gitArea: GridPane = TextFieldWithOption("Git")
        val mailArea: GridPane = TextFieldWithOption("Mail")
        val phoneArea: GridPane = TextFieldWithOption("Phone")
        val telegramArea: GridPane = TextFieldWithOption("Telegram")

        var displayPanel: StudentDisplayPanel = StudentDisplayPanel()

        constructor(displayPanel: StudentDisplayPanel): super(){
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
            add(filterButton, 0 , 6)
        }
    }

    class StudentDisplayPanel(): VBox() {
        var stList: Student_list = Student_list()
        var currentPage: Int = 0
        var table: TableView<StudentShortView> = TableView()
        var totalCount: Int = 0
        var pageLabel: Label = Label((currentPage + 1).toString())
        val leftButton: Button = Button("<")
        val rightButton: Button = Button(">")

        var observableList: ObservableList<StudentShortView> = FXCollections.observableArrayList()

        val entriesPerPage = 5

        var updateDataPanel: UpdateDataPanel = UpdateDataPanel()

        constructor(stList: Student_list, updatePanel: UpdateDataPanel):this(){

            updateDataPanel = updatePanel
            this.stList = stList
            maxWidth = Double.POSITIVE_INFINITY
            alignment = Pos.CENTER
            totalCount = this.stList.getStudentShortCount().floorDiv(entriesPerPage) + 1

            if(currentPage == totalCount-1){
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

            var totalPageCountLabel: Label = Label((totalCount).toString())
            totalPageCountLabel.alignment = Pos.CENTER

            children.add(totalPageCountLabel)
        }

        private fun formTable() {

            val pageDataList: DataList<Student_short> = this.stList.getKNStudentShortList(entriesPerPage, currentPage*entriesPerPage)
            val pageList: List<Student_short> = pageDataList.getList()
            val pageListView: MutableList<StudentShortView> = mutableListOf<StudentShortView>()
            for (st in pageList){
                pageListView.add(StudentShortView(st))
            }
            observableList = FXCollections.observableArrayList(pageListView)

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
                }
            })
        }

        private fun updateTable(){
            val pageDataList: DataList<Student_short> = this.stList.getKNStudentShortList(entriesPerPage, currentPage*entriesPerPage)
            val pageList: List<Student_short> = pageDataList.getList()
            val pageListView: MutableList<StudentShortView> = mutableListOf<StudentShortView>()

            for (st in pageList){
                pageListView.add(StudentShortView(st))
            }

            observableList = FXCollections.observableArrayList(pageListView)
            table.items = observableList


        }

        private fun goToNextPage(){
            currentPage+=1
            pageLabel.text = (currentPage+1).toString()
            if(currentPage == totalCount-1){
                rightButton.isDisable = true
            }
            leftButton.isDisable = false
            updateTable()
        }
        private fun goToPrevPage(){
            currentPage-=1
            pageLabel.text = (currentPage+1).toString()
            if(currentPage == 0){
                leftButton.isDisable = true
            }
            rightButton.isDisable = false
            updateTable()
        }
    }

    class UpdateDataPanel: GridPane {

        val addButton = Button("Add")
        val changeButton = Button("Change")
        val deleteButton = Button("Delete")
        val updateButton = Button("Update")

        constructor(): super(){
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