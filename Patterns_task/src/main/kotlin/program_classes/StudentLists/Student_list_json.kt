package program_classes.StudentLists

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import program_classes.Student
import java.io.File
import java.io.FileNotFoundException


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

    override fun writeToFile(path: String, fileName: String){
        val outputFile: File = File(path+"\\"+fileName)
        val writer = outputFile.printWriter()

        val json = Json.encodeToString(stList)

        writer.use{
                out -> out.println(json)
        }
    }
}