package program_classes

class Student(
    name: String,
    fam_name: String,
    father_name: String,
    phone: String? = null,
    email: String? = null,
    telegram: String? = null,
    git: String? = null
    ) {
    var name: String = name
        set(value) {
            field = value
        }
    var fam_name: String = fam_name
        set(value) {
            field = value
        }
    var father_name: String = father_name
        set(value) {
            field = value
        }
    var phone: String? = phone
        set(value) {
            if (checkPhone(value!!)){
                field = value
            }
            else{
                field = null
            }
        }
    var email: String? = email
        set(value) {
            if (checkEmail(value!!)){
                field = value
            }
            else{
                field = null
            }
        }
    var git: String? = git
        set(value) {
            if (checkString(value!!)){
                field = value
            }
            else{
                field = null
            }
        }
    var telegram: String? = telegram
        set(value) {
            if (checkString(value!!)){
                field = value
            }
            else{
                field = null
            }
        }

    override fun toString(): String {
        val str = "Full name: $fam_name $name $father_name,\nphone: $phone \nemail:$email \ngit: $git \ntelegram: $telegram\n"
        return str
    }

    fun validate(): Boolean{
        return checkTelegramExistence() || checkGitExistence()
    }
    fun checkGitExistence(): Boolean{
        return (git != null)&&(git != "")
    }
    fun checkTelegramExistence(): Boolean{
        return (telegram != null)&&(telegram != "")
    }

    companion object{
        fun checkPhone(phone: String): Boolean{
            val reg = Regex("a?\\d{11}")
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