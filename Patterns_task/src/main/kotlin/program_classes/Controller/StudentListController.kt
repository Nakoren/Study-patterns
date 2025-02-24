package program_classes.Controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import program_classes.Controller.Filters.BasicFilterData
import program_classes.Controller.Filters.FilterDataExtendedDecorator
import program_classes.Model.DataList
import program_classes.Model.DataListStudentShort
import program_classes.Model.Student
import program_classes.Model.StudentLists.Student_list
import program_classes.Model.Student_short
import program_classes.View.GUI.ClassViews.StudentShortView
import program_classes.View.GUI.StudentApplication

class StudentListController{
    var totalCount: Int = 0
    var entriesPerPage = 10
    var currentPage = 0

    lateinit var filter: FilterDataExtendedDecorator

    var stList: Student_list = Student_list()
    lateinit var view: StudentApplication

    fun getKNStudentShortList(n: Int, k: Int): DataList<Student_short> {
        return stList.getKNStudentShortList(n, k, extractFilter())
    }

    fun delete(id: Int){
        stList.delete(id)
        refreshData()
    }

    fun update(student: Student){
        stList.replace(student, student.id)
        refreshData()
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

    fun getStudentById(id:Int): Student? {
        return stList.getStudent(id)
    }

    fun refreshData(){
        filter = extractFilter()
        var data: DataListStudentShort = stList.getKNStudentShortList(entriesPerPage, currentPage*entriesPerPage, filter)
        currentPage = 0
        totalCount = this.stList.getStudentShortCount().floorDiv(entriesPerPage) + 1
        data.updateListener = view
        data.notify(view)
    }

    fun extractFilter(): FilterDataExtendedDecorator {
        val nameFilter= view.filtrationPanel.nameArea.getValue()
        val gitFilter = view.filtrationPanel.gitArea.getValue()
        val mailFilter = view.filtrationPanel.mailArea.getValue()
        val phoneFilter = view.filtrationPanel.phoneArea.getValue()
        val telegramFilter = view.filtrationPanel.telegramArea.getValue()
        val gitReq = view.filtrationPanel.gitArea.getChoiceValue()
        val mailReq = view.filtrationPanel.mailArea.getChoiceValue()
        val phoneReq = view.filtrationPanel.phoneArea.getChoiceValue()
        val telReq = view.filtrationPanel.telegramArea.getChoiceValue()
        val basicFilterData = BasicFilterData(nameFilter, gitFilter, mailFilter, phoneFilter, telegramFilter, gitReq, mailReq, phoneReq, telReq)
        val extendedFilter = FilterDataExtendedDecorator()
        extendedFilter.setBaseFilter(basicFilterData)
        return extendedFilter
    }

    constructor(refView: StudentApplication, studentList: Student_list){
        //testInit()
        stList = studentList
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