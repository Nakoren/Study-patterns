package program_classes

class Student(
    name: String,
    fam_name: String,
    father_name: String
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
    var phone: String? = null
        set(value) {
            if (checkPhone(value!!)){
                field = value
            }
            else{
                field = null
            }
        }
    var email: String?  = null
        set(value) {
            if (checkEmail(value!!)){
                field = value
            }
            else{
                field = null
            }
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
    var telegram: String?  = null
        set(value) {
            if (checkString(value!!)){
                field = value
            }
            else{
                field = null
            }
        }

    override fun toString(): String {
        var str = "$name $fam_name $father_name"
        if (telegram == null){
            str+=" ___"
        }
        else{
            str+=" $telegram"
        }
        if (email == null){
            str+=" ___"
        }
        else{
            str+=" $email"
        }
        if (phone == null){
            str+=" ___"
        }
        else{
            str+=" $phone"
        }
        if (git == null){
            str+=" ___"
        }
        else{
            str+=" $git"
        }
        return str
    }
    fun toReadableString(): String{
        val str = "Full name: $fam_name $name $father_name,\nphone: $phone \nemail:$email \ngit: $git \ntelegram: $telegram\n"
        return str
    }

    fun validate(): Boolean{
        return checkContactExistence() || checkGitExistence()
    }
    fun checkContactExistence():Boolean{
        return (email!=null || phone != null || telegram!=null)
    }
    fun checkGitExistence(): Boolean{
        return (git != null)&&(git != "")
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
    fun setContacts(email: String?, telegram: String?,phone: String?){
        this.email = email
        this.telegram = telegram
        this.phone = phone
    }
    fun getHashMap():HashMap<String, String?>{
        val map = HashMap<String, String?>()
        map["name"] = name
        map["fam_name"] = fam_name
        map["father_name"] = father_name
        map["phone"] = phone
        map["email"] = email
        map["git"] = git
        map["telegram"] = telegram
        return map
    }

    fun getShortNameString():String{
        return fam_name+name[0].uppercaseChar()+father_name[0].uppercaseChar()
    }
    fun getContactString():String {
        if (email != null) {
            return "$email-email"
        }
        if (telegram != null) {
            return "$telegram-telegram"
        }
        if (phone!=null){
            return "$phone-phone"
        }
        return "noContact"
    }
    fun getGitString():String{
        if(git!=null){
            return git.toString()
        }
        return "_"
    }

    fun getInfo(): String{
        val nameData = getShortNameString();
        val gitData = getGitString();
        val contactData:String = getContactString();
        return "$nameData $gitData $contactData";
    }

    constructor(name:String, fam_name: String, father_name: String, phone:String?, email:String?, git:String?, telegram: String?):this(name=name,fam_name=fam_name,father_name=father_name){
        if(phone!=null){
            this.phone = phone
        }
        if(email!=null){
            this.email = email
        }
        if(git!=null){
            this.git = git
        }
        if(telegram!=null){
            this.git = git
        }
    }
    constructor(map: HashMap<String, String?>): this(name=map["name"] as String,fam_name=map["fam_name"] as String,father_name=map["father_name"] as String) {
        if(map["phone"]!=null){
            phone = map["phone"]
        }
        if(map["email"]!=null){
            email = map["email"]
        }
        if(map["git"]!=null){
            git = map["git"]
        }
        if(map["telegram"]!=null){
            telegram = map["telegram"]
        }
    }
    constructor(longString: String):this(name = longString.split(" ")[0], fam_name = longString.split(" ")[1],father_name = longString.split(" ")[2]){
        val parsedString = longString.split(" ")
        if(parsedString[3]!="___"){
            telegram = parsedString[3]
        }
        if(parsedString[4]!="___"){
            email = parsedString[4]
        }
        if(parsedString[5]!="___"){
            phone = parsedString[5]
        }
        if(parsedString[6]!="___"){
            git = parsedString[6]
        }
    }
}