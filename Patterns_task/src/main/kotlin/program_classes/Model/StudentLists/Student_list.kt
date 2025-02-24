package program_classes.Model.StudentLists

import program_classes.Controller.Filters.FilterDataExtendedDecorator
import program_classes.Model.DataListStudentShort
import program_classes.Model.Interfaces.IStudent_list
import program_classes.Model.Student
import program_classes.Model.Student_short
import java.io.File
import java.io.FileNotFoundException

open class Student_list: IStudent_list {

    internal var stList: MutableList<Student> = mutableListOf()
    open val path = "C:\\Users\\minen\\Desktop\\Unik\\курс 4 - сем 1\\Паттерны\\Patterns_task\\Patterns_task\\src\\main\\kotlin"
    open val fileName = "Student.txt"

    open fun readFromFile(path: String, fileName: String){
        val inputStream: File = File(path+ "\\" + fileName)

        if(!inputStream.exists()){
            throw FileNotFoundException("File not found")
        }
        val resList = mutableListOf<Student>()
        inputStream.forEachLine {
            if(it!="")
                resList.addLast(( Student(it) ) )
        }

        stList = resList;
    }

    open fun writeToFile(path: String, fileName: String){
        val outputFile: File = File(path+"\\"+fileName)
        val writer = outputFile.printWriter()
        var resStr: String = ""
        stList.sortByDescending { it.id }
        for (st in stList){
            resStr+=st.toString()+"\n"
        }
        writer.use{
                out -> out.println(resStr)
        }
    }

    override fun getStudent(id: Int): Student?{
        if((id>=stList.size)||(id<0)){
            return null
        }
        var restSt: Student? = null;
        for(i in 0..< stList.size){
            if(stList[i].id == id) restSt = stList[i]
        }
        return restSt
    }

    override fun getKNStudentShortList(num:Int, startInd:Int, filter: FilterDataExtendedDecorator): DataListStudentShort {
        readFromFile(path, fileName)
        if(startInd<0){
            return DataListStudentShort(listOf())
        }
        val resList = mutableListOf<Student_short>()
        for(i in 0..< num){
            if(startInd + i >=stList.size){
                break
            }
            if(stList[i].checkFilterCorrespondence(filter)){
                resList.addLast(Student_short(stList[startInd+i]))
            }
        }
        val resDataList = DataListStudentShort(resList)
        return resDataList
    }

    fun sort(){
        stList.sortBy{"${it.fam_name[0]}${it.name[0]}"}
    }

    open fun checkIdExistence(id:Int):Boolean{
        for(stud in stList){
            if(stud.id == id) return true
        }
        return false
    }

    override fun add(st: Student){
        var tempId = 0
        while (checkIdExistence(tempId)) tempId++
        st.id = tempId
        stList.addLast(st)
        writeToFile(path,fileName)
    }

    override fun replace(st: Student, id: Int){
        if(checkIdExistence(id)){
            stList[id] = st
        }
        writeToFile(path,fileName)
    }

    override fun delete(id: Int){
        for(stud in stList){
            if(stud.id == id) stList.remove(stud)
        }
        writeToFile(path,fileName)
    }

    override fun getStudentShortCount():Int{
        return stList.count()
    }

    fun setList(newList: MutableList<Student>){
        stList = newList
    }

    fun convertMap(hashM: Map<String, String?>): Map<String, Any> {
        val res = mutableMapOf<String, Any>()
        for ((key, value) in hashM.entries) {
            if (value != null) res.set(key, value.toString())
        }
        return res
    }

    open fun createMap(el: Any): HashMap<String, String?> {
        val map = HashMap<String, String?>()
        return map
    }
}