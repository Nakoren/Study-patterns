package program_classes

open class DataList<T: Any>(
    data: List<T>
) {
    internal val data: List<T> = data

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

    open fun getData(): DataTable{
        return DataTable(mutableListOf<List<Any>>())
    }
}