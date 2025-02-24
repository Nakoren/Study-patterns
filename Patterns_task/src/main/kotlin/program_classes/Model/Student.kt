package program_classes.Model


import program_classes.Controller.Filters.BasicFilterData
import program_classes.Controller.Filters.IFilter
import java.io.File
import java.io.FileNotFoundException


class Student(
    ID: Int,
    name: String,
    fam_name: String,
    father_name: String?,
    ): Student_root(git = null, id = ID)
{
    var name: String = name
        set(value) {
            field = value
        }
    var fam_name: String = fam_name
        set(value) {
            field = value
        }
    var father_name: String? = if (father_name == "") null else father_name
        set(value) {
            if (checkString(value!!)){
                if(value != "") field = value
                else field = null
            }
            else{
                field = null
            }
        }
    var phone: String? = null
        set(value) {
            if (checkPhone(value!!)){
                if(value != "") field = value
                else field = null
            }
            else{
                field = null
            }
        }
    var email: String?  = null
        set(value) {
            if (checkEmail(value!!)){
                if(value != "") field = value
                else field = null
            }
            else{
                field = null
            }
        }
    var telegram: String?  = null
        set(value) {
            if (checkString(value!!)){
                if(value != "") field = value
                else field = null
            }
            else{
                field = null
            }
        }

    companion object{
        fun readFromTxt(address: String): List<Student>{
            val inputStream: File = File(address)

            if(!inputStream.exists()){
                throw FileNotFoundException("File not found")
            }

            val resList = mutableListOf<Student>()
            inputStream.forEachLine {
                if(it!="")
                resList.addLast(( Student(it) ) )
            }

            return resList;
        }

        fun writeToTxt(path: String, fileName: String, list: List<Student>){
            val outputFile: File = File(path+"\\"+fileName)
            val writer = outputFile.printWriter()
            var resStr: String = ""
            for (st in list){
                resStr+=st.toString()+"\n"
            }
            writer.use{
                out -> out.println(resStr)
            }
        }
    }

    override fun toString(): String {
        var str = "$id $name $fam_name"
        if(father_name == null){
            str += "___"
        }
        else{
            str+= " $father_name"
        }
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
    private fun checkContactExistence():Boolean{
        return (email!=null || phone != null || telegram!=null)
    }
    fun checkGitExistence(): Boolean{
        return (git != null)&&(git != "")
    }

    fun setContacts(email: String?, telegram: String?,phone: String?){
        this.email = email
        this.telegram = telegram
        this.phone = phone
    }
    fun getHashMap():HashMap<String, String?>{
        val map = HashMap<String, String?>()
        map["id"] = id.toString()
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
        var resStr = "$fam_name ${name[0].uppercaseChar()}."
        if(father_name!=null){
            resStr+="${father_name!![0].uppercaseChar()}. "
        }
        return resStr
    }
    fun getContactString():String {
        if (email != null && email != "") {
            return "$email-email"
        }
        if (telegram != null && telegram != "") {
            return "$telegram-telegram"
        }
        if (phone!=null && phone != ""){
            return "$phone-phone"
        }
        return "No contact"
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

    fun checkFilterCorrespondence(filter: BasicFilterData): Boolean{

        if(filter.nameFilter != "") {
            val nameSplit = filter.nameFilter.split(" ")
            if(nameSplit.size >= 1){
                val reg = Regex(nameSplit[0])
                if (!reg.containsMatchIn(fam_name)){
                    return false
                }
            }
            if(nameSplit.size >= 2){
                val reg = Regex(nameSplit[1])
                if (!reg.containsMatchIn(name)){
                    return false
                }
            }
            if(nameSplit.size >= 3){
                val reg = Regex(nameSplit[2])
                if(father_name == null) return false
                if (!reg.containsMatchIn(father_name!!)){
                    return false
                }
            }
        }

        if(filter.gitReq == 1){
            if(git == null) return false
            if(filter.gitFilter!=""){
                val regex = Regex(filter.gitFilter)
                if(!regex.containsMatchIn(git!!)) return false
            }
        }
        if(filter.gitReq == -1){
            return (git == null)
        }

        if(filter.mailReq == 1){
            if(email == null) return false
            if(filter.mailFilter!=""){
                val regex = Regex(filter.mailFilter)
                if(!regex.containsMatchIn(email!!)) return false
            }
        }
        if(filter.mailReq == -1){
            return (email == null)
        }

        if(filter.phoneReq == 1){
            if(phone == null) return false
            if(filter.phoneFilter!=""){
                val regex = Regex(filter.phoneFilter)
                if(!regex.containsMatchIn(phone!!)) return false
            }
        }
        if(filter.phoneReq == -1){
            return (phone == null)
        }

        if(filter.telReq == 1){
            if(telegram == null) return false
            if(filter.telFilter!=""){
                val regex = Regex(filter.telFilter)
                if(!regex.containsMatchIn(telegram!!)) return false
            }
        }
        if(filter.telReq == -1){
            return (telegram == null)
        }

        return true
    }

    constructor(id: Int, name:String, famName: String, fatherName: String, phone:String?, email:String?, git:String?, telegram: String?):this(ID = id, name=name,fam_name=famName,father_name=fatherName){
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
            this.telegram = telegram
        }
    }
    constructor(map: HashMap<String, String?>): this(ID = map["ID"]!!.toInt(),name=map["name"] as String,fam_name=map["fam_name"] as String,father_name=map["father_name"] as String?) {
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
    constructor(longString: String):this(ID = longString.split(" ")[0].toInt(),name = longString.split(" ")[1], fam_name = longString.split(" ")[2],father_name = longString.split(" ")[3]){
        val parsedString = longString.split(" ")
        if(parsedString[4]!="___"){
            telegram = parsedString[4]
        }
        if(parsedString[5]!="___"){
            email = parsedString[5]
        }
        if(parsedString[6]!="___"){
            phone = parsedString[6]
        }
        if(parsedString[7]!="___"){
            git = parsedString[7]
        }
    }
}