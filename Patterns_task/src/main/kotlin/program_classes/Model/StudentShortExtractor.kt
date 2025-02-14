package program_classes.Model

class StudentShortExtractor (
    dList: DataList<Student>
)
{
    val dList: DataList<Student> = dList

    fun extractStudentShort(num: Int): Student_short?{
        val table: DataTable = dList.getData()

        if (num<table.l) {
            val extStudent = Student_short(id = table.get(0,num) as Int, shortName = table.get(1,num) as String, contact = table.get(2,num) as String?, git = table.get(3,num) as String?)
            return extStudent
        }
        else{
            return null
        }
    }
}