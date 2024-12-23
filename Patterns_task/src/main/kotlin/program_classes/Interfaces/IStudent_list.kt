package program_classes.Interfaces

import program_classes.DataList
import program_classes.Student
import program_classes.Student_short

interface IStudent_list {
    fun getStudent(id: Int): Student?;
    fun getKNStudentShortList(num:Int, startInd:Int): DataList<Student_short>;
    fun add(st: Student);
    fun replace(st: Student, id: Int): Boolean;
    fun delete(id: Int);
    fun getStudentShortCount():Int;
}