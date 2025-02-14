package program_classes.StudentLists

import program_classes.DataList
import program_classes.Interfaces.IStudent_list
import program_classes.Student
import program_classes.Student_short
import java.io.File
import java.io.FileNotFoundException

open class Student_list: IStudent_list {

    internal var stList: MutableList<Student> = mutableListOf()

    open fun readFromFile(address: String){
        val inputStream: File = File(address)

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

    override open fun getKNStudentShortList(num:Int, startInd:Int): DataList<Student_short> {
        if(startInd<0){
            return DataList<Student_short>(listOf())
        }
        val resList = mutableListOf<Student_short>()
        for(i in 0..< num){
            if(startInd + i >=stList.size){
                break
            }
            resList.addLast(Student_short(stList[startInd+i]))
        }
        val resDataList = DataList(resList)
        return resDataList
    }

    fun sort(){
        stList.sortBy{"${it.fam_name[0]}${it.name[0]}${it.father_name[0]}"}
    }

    fun checkIdExistence(id:Int):Boolean{
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
    }

    override fun replace(st: Student, id: Int): Boolean{
        if(checkIdExistence(id)){
            stList[id] = st
            return true
        }
        return false
    }

    override fun delete(id: Int){
        if(checkIdExistence(id)){
            stList.removeAt(id)
        }
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