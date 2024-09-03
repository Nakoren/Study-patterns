import program_classes.Student

fun main() {
    var Student1 = Student(name = "name1", fam_name = "fam1", father_name = "fname1")
    var Student2 = Student(name = "name2", fam_name = "fam2", father_name = "fname2", telegram = "teleg2")
    var Student3 = Student(name = "name3", fam_name = "fam3", father_name = "fname3", git = "git3")

    println(Student1)
    println(Student2)
    println(Student3)
}