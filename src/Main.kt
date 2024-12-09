import program_classes.*

fun main() {
    var Student1 = Student( 1, "name1", "fam1", "fname1", null, "mail1", "git1", "tel1")
    println(Student1.toString())
    var Student2 = Student(id = 2, name = "name2", fam_name = "fam2", father_name = "fname2")
    println(Student2.toString())
    var Student3 = Student(id = 3, name = "name3", fam_name = "fam3", father_name = "fname3")
    println(Student3.toString())

    var stList = Student.readFromTxt("C:\\Users\\minen\\Desktop\\Study-patterns\\src\\Input.txt")

    val stShortList = mutableListOf<Student_short>()
    for(st in stList){
        stShortList.addLast(Student_short(st))
    }

    val DataList = DataListStudentShort(stList = stShortList)

    val extractor = StudentShortExtractor(DataList)

    val extStudent = extractor.extractStudentShort(2)
    println(extStudent.toString())


    /*
    val studMap = Student1.getHashMap()
    val studFromMap = Student(studMap)
    */


}