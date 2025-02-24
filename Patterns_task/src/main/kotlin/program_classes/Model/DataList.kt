package program_classes.Model

import program_classes.View.GUI.StudentApplication

open class DataList<T: Any>(
    internal val data: List<T>
){

    private var selected: MutableList<Int> = mutableListOf<Int>()

    fun select(number: Int){
        if(number<data.size)
        selected.addLast(number)
    }

    fun resetSelected(){
        selected = mutableListOf<Int>()
    }

    inline fun <reified R: T>getNames(): List<String>{
        val fields = R::class.java.declaredFields
        val resList = mutableListOf<String>()
        for(field in fields){
            resList.addLast(field.name)
        }
        return resList
    }
    open fun getData(): DataTable {
        return DataTable(mutableListOf<List<Any>>())
    }
    open fun getList(): List<T>{
        return data
    }
}