package program_classes

open class DataList(
    data: List<Any>
) {
    internal val data: List<Any?> = data

    private var selected: MutableList<Int> = mutableListOf<Int>()

    fun select(number: Int){
        if(number<data.size)
        selected.addLast(number)
    }

    fun resetSelected(){
        selected = mutableListOf<Int>()
    }

    open fun getNames(): List<String>{
        return mutableListOf<String>()
    }

    open fun getData(): DataTable{
        return DataTable(mutableListOf<List<Any>>())
    }
}