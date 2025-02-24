package program_classes.Controller.Filters

open class BasicFilterData(
    var nameFilter: String = "",
    var gitFilter: String = "",
    var mailFilter: String = "",
    var phoneFilter: String = "",
    var telFilter: String = "",
    var gitReq: Int = 0,
    var mailReq: Int = 0,
    var phoneReq: Int = 0,
    var telReq: Int = 0,
):IFilter {
    override fun getWhereSequence(): String{
        var str = "where "
        if(gitReq == 1){
            if(gitFilter!=""){
                str += "git like '%$gitFilter%' and "
            }
            else{
                str += "git is not NULL and "
            }
        }
        else{
            if(gitReq== -1){
                str += "git is NULL and "
            }
        }
        if(str == "where "){
            return ""
        }
        str = str.dropLast(4)
        return str
    }
}