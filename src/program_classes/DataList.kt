package program_classes

class DataList(
    data: List<Any>,
) {
    private val data: List<Any> = data

    private var selected: MutableList<Int> = mutableListOf<Int>()

    fun select(number: Int){
        if(number<data.size)
        selected.addLast(number)
    }

    fun resetSelected(){
        selected = mutableListOf<Int>()
    }

    fun getNames(): List<String>{
        return mutableListOf<String>()
    }

    fun getData(): DataTable{
        return DataTable(mutableListOf<List<Any>>())
    }
}