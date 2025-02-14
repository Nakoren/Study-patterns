package program_classes.View.GUI.ClassViews

import program_classes.Model.Student_short

class StudentShortView() {
    var id: Int = 0
        set(value){
            field = value
        }
        get(){
            return field
        }
    var name: String = "None"
        set(value){
            field = value
        }
        get(){
            return field
        }
    var contact: String = "None"
        set(value){
            field = value
        }
        get(){
            return field
        }
    var git: String = "None"
        set(value){
            field = value
        }
        get(){
            return field
        }
    constructor(srcStudent: Student_short): this(){
        id = srcStudent.id
        name = srcStudent.shortName
        if(srcStudent.contact != null){
            contact = srcStudent.contact!!
        }
        if(srcStudent.git != null){
            git = srcStudent.git!!
        }
    }
}