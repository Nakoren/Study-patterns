package program_classes

class Student_short(
    val id : Int,
    val shortName : String,
    val git: String?,
    val contact: String?
){
    constructor(sourceSt: Student): this(
        id = sourceSt.id,
        shortName = sourceSt.getShortNameString(),
        git = sourceSt.getGitString(),
        contact = sourceSt.getContactString()
    )
    constructor(sourceString: String): this(
        id = -1,
        shortName = sourceString.split(" ")[0],
        git = sourceString.split(" ")[1],
        contact = sourceString.split(" ")[2]
    )
}