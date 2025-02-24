package program_classes.Model

import program_classes.View.GUI.StudentApplication


class DataListStudentShort(
    stList: List<Student_short>
): DataList<Student_short>(data = stList)
{
    var updateListener: IListUpdateListener<Student_short>? = null

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

    fun notify(view: StudentApplication) {
        if(updateListener != null){
            updateListener!!.notifyListUpdate(getList())
        }
    }
}