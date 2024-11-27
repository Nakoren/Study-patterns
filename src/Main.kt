import program_classes.Student

fun main() {
    var Student1 = Student(id = 1, name = "name1", fam_name = "fam1", father_name = "fname1")
    var Student2 = Student(id = 2, name = "name2", fam_name = "fam2", father_name = "fname2")
    var Student3 = Student(id = 3, name = "name3", fam_name = "fam3", father_name = "fname3")

    val student4 = Student(id = 4, name = "name4", fam_name = "fam4", father_name = "fname4", phone = "+7918x   1579970", email = "mail@inbox.ru",null,null)
    val stMap = student4.getHashMap()
    val mapStudent = Student(stMap)
    print(mapStudent)
    print(mapStudent.checkGitExistence())
    /*
    val studMap = Student1.getHashMap()
    val studFromMap = Student(studMap)
    */


}