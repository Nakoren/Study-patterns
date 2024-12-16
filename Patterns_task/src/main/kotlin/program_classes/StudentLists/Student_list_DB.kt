package program_classes.StudentLists

import DataBaseClasses.DataBaseConnection
import program_classes.Student
import kotlin.collections.HashMap

class Student_list_DB {

    init {
        DataBaseConnection.createConnection()
    }

    fun getStudentById(id: Int): Student? {
        val request = "SELECT * FROM Student as t where t.id=${id}"
        val result = DataBaseConnection.executeSqlSelect(request)
        if (result != null && result.next()) {
            val resultHash:HashMap<String,String?> = hashMapOf<String,String?>()
            resultHash.set("ID",result.getString("ID"))
            resultHash.set("name",result.getString("name"))
            resultHash.set("fam_name",result.getString("fam_name"))
            resultHash.set("father_name",result.getString("father_name"))
            resultHash.set("phone",result.getString("phone"))
            resultHash.set("email",result.getString("email"))
            resultHash.set("git",result.getString("git"))
            resultHash.set("telegram",result.getString("telegram"))
            result.close();
            return Student(resultHash);
        };
        return null;
    }

    fun getKStudents(n: Int, k: Int): List<Student> {
        val request = "SELECT * FROM Student as t ORDER BY t.id OFFSET ${(n-1)*k} ROWS LIMIT ${k}"
        val result = DataBaseConnection.executeSqlSelect(request);
        if (result != null) {
            val resultList:MutableList<Student> = mutableListOf()
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
                resultList.add(Student(resultHash));
            }
            result.close();
            return resultList;
        };
        return mutableListOf();
    }

    fun addStudent(student: Student) {
        val studentProps = student.getHashMap()
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
        DataBaseConnection.executeSql(request);
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
        DataBaseConnection.executeSql(request);
    }

    fun deleteStudent(id: Int) {
        val request = "delete from Student as t where t.id=${id}"
        DataBaseConnection.executeSql(request);
    }
    //
    fun getCount(): Int {
        val request = "SELECT count(*) as c FROM Student"
        val result = DataBaseConnection.executeSqlSelect(request);
        if (result != null && result.next()) {
            val count = result.getInt("c");
            result.close();
            return count;
        };
        return 0;
    }


}