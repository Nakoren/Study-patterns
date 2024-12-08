package program_classes

class DataTable(
    table: List<List<Any>>,
) {
    private val table: List<List<Any>> = table

    val lenght: Int
        get() = table[0].size

    val height: Int
        get() = table.size

    fun getLength(): Int{
        return this.lenght
    }

    fun getHeight(): Int{
        return this.height
    }

    fun get(l:Int, h: Int): Any?{
        if(l>=lenght||h>=height){
            return null
        }
        return table[h][l]
    }
}