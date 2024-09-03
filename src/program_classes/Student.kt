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
        get() = field
        set(value) {
            field = value.lowercase()
        }
    var fam_name: String = fam_name
        get() = field
        set(value) {
            field = value.lowercase()
        }
    var father_name: String = father_name
        get() = field
        set(value) {
            field = value.lowercase()
        }
    var phone: String? = phone
        get() = field
        set(value) {
            field = value?.lowercase()
        }
    var email: String? = email
        get() = field
        set(value) {
            field = value?.lowercase()
        }
    var git: String? = git
        get() = field
        set(value) {
            field = value?.lowercase()
        }
    var telegram: String? = telegram
        get() = field
        set(value) {
            field = value?.lowercase()
        }
}