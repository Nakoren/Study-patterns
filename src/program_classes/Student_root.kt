package program_classes

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
    var git: String?  = null
        set(value) {
            if (checkString(value!!)){
                field = value
            }
            else{
                field = null
            }
        }

    companion object{
        fun checkPhone(phone: String): Boolean{
            val reg = Regex("\\+?\\d{11}")
            return reg.matches(phone)
        }
        fun checkEmail(email: String): Boolean{
            val reg = Regex("[\\w,\\d]+@\\w+\\.\\w+")
            return reg.containsMatchIn(email)
        }
        fun checkString(name: String): Boolean{
            return name!=""
        }
    }
}