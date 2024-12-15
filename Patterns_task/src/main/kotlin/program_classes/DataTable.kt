package program_classes

class DataTable(
    table: List<List<Any?>>,
) {
    private val table: List<List<Any?>> = table

    val l: Int
        get() = table[0].size

    val h: Int
        get() = table.size

    fun getLength(): Int{
        return this.l
    }

    fun getHeight(): Int{
        return this.h
    }

    fun get(l:Int, h: Int): Any?{
        if(l>=this.l||h>=this.h){
            return null
        }
        return table[h][l]
    }
}