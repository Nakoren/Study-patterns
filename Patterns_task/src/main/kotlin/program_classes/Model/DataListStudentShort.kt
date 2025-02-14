package program_classes.Model

import program_classes.View.GUI.Interfaces.IViewListener
import program_classes.View.GUI.StudentApplication
import program_classes.View.GUI.StudentListController


class DataListStudentShort(
    stList: List<Student_short>
): DataList<Student_short>(data = stList), IViewListener
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

    override fun notify(view: StudentApplication) {
        view.displayPanel.updateObsList(getList())
    }
}