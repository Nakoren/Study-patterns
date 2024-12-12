package program_classes.FileReader

import kotlinx.serialization.decodeFromString
import net.mamoe.yamlkt.Yaml
import program_classes.DataList
import program_classes.Student
import program_classes.Student_short
import java.io.File
import java.io.FileNotFoundException


class Student_list_yaml{
    private var stList: MutableList<Student> = mutableListOf()

    fun readFromFile(address: String){
        val inputStream: File = File(address)

        if(!inputStream.exists()){
            throw FileNotFoundException("File not found")
        }

        val inputStr = inputStream.readText()

        val resList = Yaml.decodeFromString<MutableList<Student>>(inputStr)

        stList = resList
    }

    fun writeToFile(path: String, fileName: String, list: List<Student>){
        val outputFile: File = File(path+"\\"+fileName)
        val writer = outputFile.printWriter()

        val res = Yaml.encodeToString(stList)

        writer.use{
                out -> out.println(res)
        }
    }

    fun getStudent(id: Int): Student?{
        if((id>=stList.size)||(id<0)){
            return null
        }
        var restSt: Student? = null;
        for(i in 0..< stList.size){
            if(stList[i].id == id) restSt = stList[i]
        }
        return restSt
    }

    fun getKNStudentShortList(num:Int, startInd:Int): DataList<Student_short>{
        if(startInd<0){
            return DataList<Student_short>(listOf())
        }
        val resList = mutableListOf<Student_short>()
        for(i in 0..< num){
            resList.addLast(Student_short(stList[startInd+num]))
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

    fun add(st: Student){
        var tempId = 0
        while (checkIdExistence(tempId)) tempId++
        st.id = tempId
        stList.addLast(st)
    }

    fun replace(st:Student, id: Int): Boolean{
        if(checkIdExistence(id)){
            stList[id] = st
            return true
        }
        return false
    }

    fun delete(id: Int){
        if(checkIdExistence(id)){
            stList.removeAt(id)
        }
    }

    fun getStudentShortCount():Int{
        return stList.count()
    }
}