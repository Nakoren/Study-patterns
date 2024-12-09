package program_classes


class DataListStudentShort(
    stList: List<Student_short>
):
    DataList(data = stList)
{
    override fun getNames(): List<String> {
        return listOf<String>("shortName", "contact", "git")
    }

    override fun getData(): DataTable {
        var tableList = mutableListOf<List<Any?>>()
        var count: Int = 1;
        for (st in data){
            if(st is Student_short)
            tableList.addLast(listOf<Any?>(count, st.shortName, st.contact, st.git))
            count++
        }
        return DataTable(tableList)
    }
}