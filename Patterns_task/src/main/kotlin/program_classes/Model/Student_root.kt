package program_classes.Model

open class Student_root(
    id: Int,
    git: String?
) {
    var id: Int = id
        set(value){
            if((value!=null)&&(value>=0)){field = value}
        }
        get(){
            return field
        }
    var git: String?  = git
        set(value) {
            if (checkString(value!!)){
                field = value
            }
            else{
                field = null
            }
        }
        get(){
            return field
        }

    open fun getNames():List<String>{
        return listOf("id", "git")
    }

    companion object{
        fun checkPhone(phone: String): Boolean{
            if(phone == "") return true
            val reg = Regex("\\+?\\d{11}")
            return reg.matches(phone)
        }
        fun checkEmail(email: String): Boolean{
            if(email == "") return true
            val reg = Regex("[\\w,\\d]+@\\w+\\.\\w+")
            return reg.containsMatchIn(email)
        }
        fun checkString(name: String): Boolean{
            return name!=""
        }
    }
}