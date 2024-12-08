package program_classes

class DataTable(
    table: List<List<Any>>,
) {
    private val table: List<List<Any>> = table

    public val lenght: Int
        get() = table.get(0).size

    public val height: Int
        get() = table.size
    
}