package program_classes.Model

import program_classes.Model.StudentLists.Student_list

class ListController (
    path: String,
    fileName: String
) {
    var path: String = path
    var fileName: String = fileName

    var stList: Student_list = Student_list()

    fun read(){
        stList.readFromFile(path, fileName)
    }

    fun rebase(newList: Student_list){
        newList.setList(stList.stList)
        stList = newList
    }
}