package program_classes.Controller.Filters

class FilterDataExtendedDecorator (): BasicFilterData(), IFilter {
    lateinit var decoratedFilter: BasicFilterData

    fun setBaseFilter(baseFilter: BasicFilterData) {
        decoratedFilter = baseFilter
        nameFilter = baseFilter.nameFilter
        gitFilter = baseFilter.gitFilter
        mailFilter = baseFilter.mailFilter
        phoneFilter = baseFilter.phoneFilter
        telFilter = baseFilter.telFilter
        gitReq = baseFilter.gitReq
        mailReq = baseFilter.mailReq
        phoneReq = baseFilter.phoneReq
        telReq = baseFilter.telReq
    }

    override fun getWhereSequence(): String{
        var str = decoratedFilter.getWhereSequence()
        if(str == ""){
            str = "where "
        }
        else{
            str += "and "
        }
        if(nameFilter != "") {
            val nameSplit = nameFilter.split(" ")
            if(nameSplit.size >= 1){
                str += "fam_name like '%${nameSplit[0]}%' and "
            }
            if(nameSplit.size >= 2){
                str += "name like '%${nameSplit[1]}%' and "
            }
            if(nameSplit.size >= 3){
                str += "father_name like '%${nameSplit[2]}%' and "
            }
        }

        if(mailReq == 1){
            if(mailFilter!=""){
                str += "email like '%$mailFilter%' and "
            }
            else{
                str += "email is not NULL and "
            }
        }
        else{
            if(mailReq== -1){
                str += "email is NULL and "
            }
        }

        if(phoneReq == 1){
            if(phoneFilter!=""){
                str += "phone like '%$phoneFilter%' and "
            }
            else{
                str += "phone is not NULL and "
            }
        }
        else{
            if(phoneReq == -1){
                str += "phone is NULL and "
            }
        }

        if(telReq == 1){
            if(telFilter!=""){
                str += "telegram like '%$telFilter%' and "
            }
            else{
                str += "telegram is not NULL and "
            }
        }
        else{
            if(telReq == -1){
                str += "telegram is NULL and "
            }
        }
        if(str == "where ") return ""
        str = str.dropLast(4)
        return str
    }
}