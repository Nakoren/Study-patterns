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
            field = value.lowercase()
        }
    var fam_name: String = fam_name
        set(value) {
            field = value.lowercase()
        }
    var father_name: String = father_name
        set(value) {
            field = value.lowercase()
        }
    var phone: String? = phone
        set(value) {
            field = value?.lowercase()
        }
    var email: String? = email
        set(value) {
            field = value?.lowercase()
        }
    var git: String? = git
        set(value) {
            field = value?.lowercase()
        }
    var telegram: String? = telegram
        set(value) {
            field = value?.lowercase()
        }

    override fun toString(): String {
        val str = "Full name: $fam_name $name $father_name,\nphone: $phone \nemail:$email \ngit: $git \ntelegram: $telegram\n"
        return str
    }
    fun validate(): Boolean{
        return checkTelegram() || checkGit()
    }
    fun checkGit(): Boolean{
        return (git != null)&&(git != "")
    }
    fun checkTelegram(): Boolean{
        return (telegram != null)&&(telegram != "")
    }
}