package program_classes.GUI.ClassViews
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty

import program_classes.Student_short

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