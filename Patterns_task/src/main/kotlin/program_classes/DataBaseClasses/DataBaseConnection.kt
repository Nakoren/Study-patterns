package DataBaseClasses

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.util.LinkedList

object DataBaseConnection {
    private var conn: Connection? = null

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

    fun getConnection() = this.conn;

    fun executeSqlSelect(query:String): ResultSet? {
        val res:LinkedList<HashMap<String,Any>>;
        try {
            val conn = getConnection()
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
            val conn = getConnection()
            val affectedRows = conn?.prepareStatement(query)?.executeUpdate()
        } catch (e: java.lang.Exception) {
            println(e)
        }
    }
}