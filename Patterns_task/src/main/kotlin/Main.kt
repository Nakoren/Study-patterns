import DataBaseClasses.DataBaseConnection
import javafx.application.Platform
import program_classes.Model.Student
import program_classes.View.GUI.StudentApplication
import program_classes.Model.StudentLists.Student_list_DB
import program_classes.Model.StudentLists.Student_list_json
import program_classes.Model.StudentLists.Student_list_txt
import program_classes.Model.StudentLists.Student_list_yaml

fun main() {
    Platform.startup {
        DataBaseConnection.init()
        val stListDB = Student_list_DB()
        val stListTXT = Student_list_txt()
        val stListYAML = Student_list_yaml()
        val stListJSON = Student_list_json()

        val App = StudentApplication(stListTXT)
        App.start()
    }

    /*
    var Student1 = Student( 1, "name1", "fam1", "fname1", null, "mail1", "git1", "tel1")
    println(Student1.toString())
    var Student2 = Student(id = 2, name = "name2", fam_name = "fam2", father_name = "fname2")
    println(Student2.toString())
    var Student3 = Student(id = 3, name = "name3", fam_name = "fam3", father_name = "fname3")
    println(Student3.toString())

    var stListController = ListController("C:\\Users\\minen\\Desktop\\Study-patterns\\Patterns_task\\src\\main\\kotlin\\Student.txt")


    stListController.read()

    stListController.stList.writeToFile("C:\\Users\\minen\\Desktop\\Study-patterns\\Patterns_task\\src\\main\\kotlin\\", "output.txt")

    val listJSon = Student_list_json()
    val listYaml = Student_list_yaml()
    val listTxt = Student_list_txt()

    /*
    stListController.rebase(listJSon)
    stListController.stList.writeToFile("C:\\Users\\minen\\Desktop\\Study-patterns\\Patterns_task\\src\\main\\kotlin\\", "Student.json")


    stListController.path = "C:\\Users\\minen\\Desktop\\Study-patterns\\Patterns_task\\src\\main\\kotlin\\Student.json"
    stListController.read()

    */


    stListController.rebase(listYaml)
    /*
    stListController.stList.writeToFile("C:\\Users\\minen\\Desktop\\Study-patterns\\Patterns_task\\src\\main\\kotlin\\", "Student.yaml")
    */
    stListController.path = "C:\\Users\\minen\\Desktop\\Study-patterns\\Patterns_task\\src\\main\\kotlin\\Student.yaml"
    stListController.read()

    /*
    val studMap = Student1.getHashMap()
    val studFromMap = Student(studMap)
    */
    */

    DataBaseConnection.init()

    var stListDB = Student_list_DB()
}