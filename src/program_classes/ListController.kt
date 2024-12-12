package program_classes

import program_classes.StudentLists.Student_list

class ListController (
    path: String,
) {
    var path: String = path

    var stList: Student_list = Student_list()

    fun rebase(newList: Student_list){
        newList.setList(stList.stList)
        stList = newList
    }
}