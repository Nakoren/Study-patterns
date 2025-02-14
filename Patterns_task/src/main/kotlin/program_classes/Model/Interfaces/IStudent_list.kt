package program_classes.Model.Interfaces

import program_classes.Model.DataList
import program_classes.Model.Student
import program_classes.Model.Student_short

interface IStudent_list {
    fun getStudent(id: Int): Student?;
    fun getKNStudentShortList(num:Int, startInd:Int): DataList<Student_short>;
    fun add(st: Student);
    fun replace(st: Student, id: Int): Boolean;
    fun delete(id: Int);
    fun getStudentShortCount():Int;
}