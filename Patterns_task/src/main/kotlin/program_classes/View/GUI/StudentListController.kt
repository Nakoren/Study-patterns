package program_classes.View.GUI

import javafx.application.Application
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import program_classes.Model.DataList
import program_classes.Model.DataListStudentShort
import program_classes.Model.Student
import program_classes.Model.StudentLists.Student_list
import program_classes.Model.Student_short
import program_classes.View.GUI.ClassViews.StudentShortView

class StudentListController{
    var totalCount: Int = 0
    var entriesPerPage = 5
    var currentPage = 0

    var nameFilter: String = ""
    var gitFilter: String = ""
    var mailFilter: String = ""
    var phoneFilter: String = ""
    var telegramFilter: String = ""
    var gitReq: Int = 0
    var mailReq: Int = 0
    var phoneReq: Int = 0
    var telReq: Int = 0

    var stList: Student_list = Student_list()
    lateinit var view: StudentApplication

    fun getKNStudentShortList(n: Int, k: Int): DataList<Student_short> {
        return stList.getKNStudentShortList(n, k)
    }

    fun getObservableList(): ObservableList<StudentShortView>? {
        val pageDataList: DataList<Student_short> = getKNStudentShortList(entriesPerPage, currentPage*entriesPerPage)
        val pageList: List<Student_short> = pageDataList.getList()
        val pageListView: MutableList<StudentShortView> = mutableListOf<StudentShortView>()

        for (st in pageList){
            pageListView.add(StudentShortView(st))
        }
        return FXCollections.observableArrayList(pageListView)
    }

    fun refreshData(){
        getFilter()
        var data: DataListStudentShort = stList.getKNStudentShortList(entriesPerPage, currentPage*entriesPerPage)
        totalCount = this.stList.getStudentShortCount().floorDiv(entriesPerPage) + 1
        data.notify(view)
    }

    fun getFilter(){
        nameFilter= view.filtrationPanel.nameArea.getValue()
        gitFilter = view.filtrationPanel.gitArea.getValue()
        mailFilter = view.filtrationPanel.mailArea.getValue()
        phoneFilter = view.filtrationPanel.phoneArea.getValue()
        telegramFilter = view.filtrationPanel.telegramArea.getValue()
        gitReq = view.filtrationPanel.gitArea.getChoiceValue()
        mailReq = view.filtrationPanel.gitArea.getChoiceValue()
        phoneReq = view.filtrationPanel.gitArea.getChoiceValue()
        telReq = view.filtrationPanel.gitArea.getChoiceValue()
    }

    constructor(refView: StudentApplication){
        testInit()
        totalCount = this.stList.getStudentShortCount().floorDiv(entriesPerPage) + 1
        this.view = refView
    }

    private fun testInit(){
        stList.add(Student(1, "Name1", "FamName1", "FathName1", phone = "+79181232323", email = null, git = "Git1", telegram = "Tel1"))
        stList.add(Student(2, "Name2", "FamName2", "FathName2", phone = "+79181232323", email = null, git = "Git2", telegram = "Tel2"))
        stList.add(Student(3, "Name3", "FamName3", "FathName3", phone = "+79181232323", email = null, git = "Git3", telegram = "Tel3"))
        stList.add(Student(4, "Name4", "FamName4", "FathName4", phone = "+79181232323", email = null, git = "Git4", telegram = "Tel4"))
        stList.add(Student(5, "Name5", "FamName5", "FathName5", phone = "+79181232323", email = null, git = "Git5", telegram = "Tel5"))
        stList.add(Student(6, "Name6", "FamName6", "FathName6", phone = "+79181232323", email = null, git = "Git6", telegram = "Tel6"))
    }
}