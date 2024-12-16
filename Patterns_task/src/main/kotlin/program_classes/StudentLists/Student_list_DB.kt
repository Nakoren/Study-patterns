package program_classes.StudentLists

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import program_classes.Student
import java.io.File
import java.io.FileNotFoundException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.util.*
import kotlin.collections.HashMap

class Student_list_DB {
    private var conn: Connection? = null;

    init {
        createConnection()

    }

    fun getStudentById(id: Int): Student? {
        val request = "SELECT * FROM ref_student as t where t.id=${id}"
        val result = executeSqlSelect(request);
        if (result != null && result.next()) {
            val resultHash:HashMap<String,String?> = hashMapOf<String,String?>()
            resultHash.set("id",result.getString("id"))
            resultHash.set("name",result.getString("name"))
            resultHash.set("surname",result.getString("surname"))
            resultHash.set("patronymic",result.getString("patronymic"))
            resultHash.set("phoneNumber",result.getString("phoneNumber"))
            resultHash.set("gitHub",result.getString("gitHub"))
            resultHash.set("email",result.getString("email"))
            resultHash.set("telegram",result.getString("telegram"))
            result.close();
            return Student(resultHash);
        };
        return null;
    }

    fun getKStudents(n: Int, k: Int): List<Student> {
        val request = "SELECT * FROM ref_student as t ORDER BY t.id OFFSET ${(n-1)*k} ROWS LIMIT ${k}"
        val result = executeSqlSelect(request);
        if (result != null) {
            val resultList:MutableList<Student> = mutableListOf()
            while(result.next()){
                val resultHash:HashMap<String,String?> = hashMapOf<String,String?>()
                resultHash.set("id",result.getString("id"))
                resultHash.set("name",result.getString("name"))
                resultHash.set("surname",result.getString("surname"))
                resultHash.set("patronymic",result.getString("patronymic"))
                resultHash.set("phoneNumber",result.getString("phoneNumber"))
                resultHash.set("gitHub",result.getString("gitHub"))
                resultHash.set("email",result.getString("email"))
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
        val request = "insert into ref_student(${columns}) values (${values})"
        executeSql(request);
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
        val request = "update ref_student t set ${values} where t.id=${id}"
        executeSql(request);
    }

    fun deleteStudent(id: Int) {
        val request = "delete from ref_student as t where t.id=${id}"
        executeSql(request);
    }
    //
    fun getCount(): Int {
        val request = "SELECT count(*) as c FROM ref_student"
        val result = executeSqlSelect(request);
        if (result != null && result.next()) {
            val count = result.getInt("c");
            result.close();
            return count;
        };
        return 0;
    }

    fun createConnection(){
        val url = "jdbc:postgresql://localhost:1434/StudentDataBase"
        val user = "DESKTOP-HKQJ7CQ"
        val password = ""
        this.conn = null
        try {
            this.conn = DriverManager.getConnection(url, user, password)
        } catch (e: Exception) {
            println(e.message)
        }
    }


    fun executeSqlSelect(query:String): ResultSet? {
        val res: LinkedList<HashMap<String, Any>>;
        try {
            val statement = conn?.createStatement()
            if (statement != null) {
                return statement.executeQuery(query)
            }
        } catch (e: java.lang.Exception) {
            println(e.message)
        }
        return null;
    }
    fun executeSql(query:String){
        try {
            val affectedRows = conn?.prepareStatement(query)?.executeUpdate()
        } catch (e: java.lang.Exception) {
            println(e)
        }
    }
}