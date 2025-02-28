package program_classes.Model.StudentLists

import NumberOrStringSerializer
import com.charleskorn.kaml.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import program_classes.Model.Student
import java.io.File
import java.io.FileNotFoundException


class Student_list_yaml: Student_list(){

    override val path = "C:\\Users\\minen\\Desktop\\Unik\\курс 4 - сем 1\\Паттерны\\Patterns_task\\Patterns_task\\src\\main\\kotlin"
    override val fileName = "Student.yaml"

    override fun readFromFile(path: String, fileName: String){
        val inputStream: File = File(path + "\\" + fileName)

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
        stList.sortByDescending { it.id }
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