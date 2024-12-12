package program_classes


class DataListStudentShort(
    stList: List<Student_short>
):
    DataList<Student_short>(data = stList)
{

    override fun getData(): DataTable {
        var tableList = mutableListOf<List<Any?>>()
        var count: Int = 1;
        for (st in data){
            if(st is Student_short){
                tableList.addLast(listOf<Any?>(count, st.shortName, st.contact, st.git))
                }
            count++
        }
        return DataTable(tableList)
    }
}