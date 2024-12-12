package program_classes.StudentLists

import program_classes.DataList
import program_classes.Student
import program_classes.Student_short
import java.io.File
import java.io.FileNotFoundException

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json



class Student_list_json: Student_list(){


    override fun readFromFile(address: String){
        val inputStream: File = File(address)

        if(!inputStream.exists()){
            throw FileNotFoundException("File not found")
        }

        val inputStr = inputStream.readText()

        val resList = Json.decodeFromString<MutableList<Student>>(inputStr)

        stList = resList
    }

    override fun writeToFile(path: String, fileName: String, list: List<Student>){
        val outputFile: File = File(path+"\\"+fileName)
        val writer = outputFile.printWriter()

        val json = Json.encodeToString(stList)

        writer.use{
                out -> out.println(json)
        }
    }
}