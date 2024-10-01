import program_classes.Student

fun main() {
    var Student1 = Student(name = "name1", fam_name = "fam1", father_name = "fname1")
    var Student2 = Student(name = "name2", fam_name = "fam2", father_name = "fname2")
    var Student3 = Student(name = "name3", fam_name = "fam3", father_name = "fname3")

    var student4 = Student(name = "name4", fam_name = "fam4", father_name = "fname4", phone = "+7918x   1579970", email = "mail@inbox.ru",null,null)
    var stMap = student4.getHashMap()
    var mapStudent = Student(stMap)
    print(mapStudent)
    print(mapStudent.checkGitExistence())
    /*
    val studMap = Student1.getHashMap()
    val studFromMap = Student(studMap)
    */


}