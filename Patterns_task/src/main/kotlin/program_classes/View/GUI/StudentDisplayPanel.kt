package program_classes.View.GUI

import UpdateDataPanel
import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import program_classes.Controller.StudentListController
import program_classes.Model.Student_short
import program_classes.View.GUI.ClassViews.StudentShortView


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
        pageLabel.maxWidth = 50.0
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

    fun goToNextPage() {
        stListController.currentPage += 1
        pageLabel.text = (stListController.currentPage + 1).toString()
        if (stListController.currentPage == stListController.totalCount - 1) {
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