package program_classes

class Student_short(
    id: Int,
    shortName : String,
    contact: String?,
    git: String?
)
    :Student_root(git = null, id = id){

    var shortName: Int = id
        set(value){
            field = value
        }
        get(){
            return field
        }
    var contact: Int = id
        set(value){
            field = value
        }
        get(){
            return field
        }
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