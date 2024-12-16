package program_classes.StudentLists

import kotlinx.serialization.json.Json
import program_classes.Student
import java.io.File
import java.io.FileNotFoundException

import NumberOrStringSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject


class Student_list_json: Student_list(){

    override fun readFromFile(address: String){
        val inputStream: File = File(address)

        if(!inputStream.exists()){
            throw FileNotFoundException("File not found")
        }

        val inputStr = inputStream.readText()

        stList.clear()
        val jsonOb = Json.parseToJsonElement(inputStr).jsonArray
        jsonOb.forEach { stList.add(Student(createMap(it.jsonObject))) }

    }

    override fun writeToFile(path: String, fileName: String){
        val outputFile: File = File(path+"\\"+fileName)
        val writer = outputFile.printWriter()

        val jsonFormat = Json { prettyPrint = true }
        val res = jsonFormat.encodeToString(
            ListSerializer(MapSerializer(String.serializer(), NumberOrStringSerializer)),
            stList.map { convertMap(it.getHashMap()) })

        writer.use{
                out -> out.println(res)
        }
    }

    override fun createMap(el: Any): HashMap<String, String?> {
        val map = HashMap<String, String?>()
        for ((key, value) in el as JsonObject) {
            map[key] = value.toString().replace("\"", "")
        }
        return map
    }
}