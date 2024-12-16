package program_classes.StudentLists

import kotlinx.serialization.decodeFromString
import com.charleskorn.kaml.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.SetSerializer
import kotlinx.serialization.serializer
import program_classes.DataList
import program_classes.Student
import program_classes.Student_short
import java.io.File
import java.io.FileNotFoundException

import NumberOrStringSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject


class Student_list_yaml: Student_list(){

    override fun readFromFile(address: String){
        val inputStream: File = File(address)

        if(!inputStream.exists()){
            throw FileNotFoundException("File not found")
        }

        val inputStr = inputStream.readText()

        stList.clear()
        val yamlOb = Yaml.default.parseToYamlNode(inputStr).yamlList
        yamlOb.items.forEach {stList.add(Student(createMap(it.yamlMap)))}
    }

    override fun writeToFile(path: String, fileName: String){
        val outputFile: File = File(path+"\\"+fileName)
        val writer = outputFile.printWriter()

        val res = Yaml.default.encodeToString(ListSerializer(MapSerializer(String.serializer(),
            NumberOrStringSerializer
        )),stList.map { convertMap(it.getHashMap()) })


        writer.use{
                out -> out.println(res)
        }
    }

    override fun createMap(el: Any): HashMap<String, String?> {
        val map = HashMap<String, String?>()
        for ((key, value) in (el as YamlMap).entries.entries) {
            map[key.content] = value.yamlScalar.content
        }
        return map
    }
}