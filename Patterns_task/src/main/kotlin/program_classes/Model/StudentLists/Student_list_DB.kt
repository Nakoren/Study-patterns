package program_classes.Model.StudentLists

import DataBaseClasses.DataBaseConnection
import program_classes.Controller.Filters.FilterDataExtendedDecorator
import program_classes.Model.DataListStudentShort
import program_classes.Model.Student
import program_classes.Model.Student_short
import kotlin.collections.HashMap

class Student_list_DB: Student_list() {

    private val connection: DataBaseConnection = DataBaseConnection.getConnection()

    override fun getStudent(id: Int): Student? {
        val request = "SELECT * FROM public.\"Student\" where \"ID\"=${id}"
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

    override fun getKNStudentShortList(num: Int, startInd: Int,filter: FilterDataExtendedDecorator): DataListStudentShort {
        var request = "SELECT * FROM public.\"Student\" ${filter.getWhereSequence()} ORDER BY \"ID\" OFFSET ${(num)*startInd} ROWS LIMIT ${num}"
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
                val addStud = Student(resultHash)
                val addStudShort = Student_short(addStud)
                resultList.add(addStudShort);
            }
            result.close();
            return DataListStudentShort(resultList);
        };
        return DataListStudentShort(mutableListOf());
    }

    override fun add(st: Student) {
        val studentProps = st.getHashMap()
        var id = 0
        while(checkIdExistence(id)) id++
        studentProps["id"] = id.toString()
        var columns = "\"ID\",";
        var values = "$id,";
        for(key in studentProps.keys){
            if(studentProps[key] !=null && studentProps[key] != "" && key!="id"){
                columns+="${key},"
                values+="'${studentProps[key]}',"
            }
        }
        columns=columns.dropLast(1)
        values=values.dropLast(1)
        val request = "insert into public.\"Student\"(${columns}) values (${values})"
        connection.executeSql(request);
    }

    override fun replace(st: Student, id: Int) {
        val studentProps = st.getHashMap()
        var updateSequence = "";
        for(key in studentProps.keys){
            if(studentProps[key] != "" && key!="id"){
                if(studentProps[key] != null ){
                    updateSequence += "$key = '${studentProps[key]}',"
                }
                else{
                    updateSequence += "$key = NULL,"
                }
            }
        }
        updateSequence = updateSequence.dropLast(1)
        var request = "Update public.\"Student\" set "
        request += updateSequence
        request += " where \"ID\" = $id"
        connection.executeSql(request)
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
        val request = "update public.\"Student\" set ${values} where \"ID\"=${id}"
        connection.executeSql(request);
    }

    override fun delete(id: Int) {
        val request = "delete from public.\"Student\" where \"ID\"=${id}"
        connection.executeSql(request);
    }
    //
    fun getCount(): Int {
        val request = "SELECT count(*) FROM public.\"Student\""
        val result = connection.executeSqlSelect(request);
        if (result != null && result.next()) {
            val count = result.getInt("c");
            result.close();
            return count;
        };
        return 0;
    }
    override fun checkIdExistence(id: Int): Boolean {
        val request = "select \"ID\" from public.\"Student\" \"ID\" where \"ID\" = ${id}"
        val result = connection.executeSqlSelect(request);
        if(result?.next() == true){
            return true
        }
        else{
            return false
        }
    }
}