package program_classes.StudentLists

import DataBaseClasses.DataBaseConnection
import program_classes.DataList
import program_classes.Student
import program_classes.Student_short
import kotlin.collections.HashMap

class Student_list_DB: Student_list() {

    private val connection: DataBaseConnection = DataBaseConnection.getConnection()

    fun getStudentById(id: Int): Student? {
        val request = "SELECT * FROM Student as t where t.id=${id}"
        val result = connection.executeSqlSelect(request)
        if (result != null && result.next()) {
            val resultHash:HashMap<String,String?> = hashMapOf<String,String?>()
            resultHash["ID"] = result.getString("ID")
            resultHash["name"] = result.getString("name")
            resultHash["fam_name"] = result.getString("fam_name")
            resultHash["father_name"] = result.getString("father_name")
            resultHash["phone"] = result.getString("phone")
            resultHash["email"] = result.getString("email")
            resultHash["git"] = result.getString("git")
            resultHash["telegram"] = result.getString("telegram")
            result.close();
            return Student(resultHash);
        };
        return null;
    }

    override fun getKNStudentShortList(n: Int, k: Int): DataList<Student_short> {
        val request = "SELECT * FROM Student as t ORDER BY t.id OFFSET ${(n-1)*k} ROWS LIMIT ${k}"
        val result = connection.executeSqlSelect(request);
        if (result != null) {
            val resultList:MutableList<Student_short> = mutableListOf()
            while(result.next()){
                val resultHash:HashMap<String,String?> = hashMapOf<String,String?>()
                resultHash.set("ID",result.getString("ID"))
                resultHash.set("name",result.getString("name"))
                resultHash.set("fam_name",result.getString("fam_name"))
                resultHash.set("father_name",result.getString("father_name"))
                resultHash.set("phone",result.getString("phone"))
                resultHash.set("email",result.getString("email"))
                resultHash.set("git",result.getString("git"))
                resultHash.set("telegram",result.getString("telegram"))
                resultList.add(Student_short(Student(resultHash)));
            }
            result.close();
            return DataList<Student_short>(resultList);
        };
        return DataList<Student_short>(mutableListOf());
    }

    override fun add(st: Student) {
        val studentProps = st.getHashMap()
        var columns = "";
        var values = "";
        for(key in studentProps.keys){
            if(studentProps[key] !=null && key!="id"){
                columns+="${key},"
                values+="'${studentProps[key]}',"
            }
        }
        columns=columns.dropLast(1)
        values=values.dropLast(1)
        val request = "insert into Student(${columns}) values (${values})"
        connection.executeSql(request);
    }

    fun updateStudent(id:Int,student: Student) {
        val studentProps = student.getHashMap()
        var values = "";
        for(key in studentProps.keys){
            if(studentProps[key] !=null && key!="id"){
                values+="${key}='${studentProps[key]}',"
            }
        }
        values=values.dropLast(1)
        val request = "update Student t set ${values} where t.id=${id}"
        connection.executeSql(request);
    }

    fun deleteStudent(id: Int) {
        val request = "delete from Student as t where t.id=${id}"
        connection.executeSql(request);
    }
    //
    fun getCount(): Int {
        val request = "SELECT count(*) as c FROM Student"
        val result = connection.executeSqlSelect(request);
        if (result != null && result.next()) {
            val count = result.getInt("c");
            result.close();
            return count;
        };
        return 0;
    }
}