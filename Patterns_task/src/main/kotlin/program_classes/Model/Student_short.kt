package program_classes.Model

import program_classes.View.GUI.ClassViews.StudentShortView


class Student_short(
    id: Int,
    shortName : String,
    contact: String?,
    git: String?
) : Student_root(git = git, id = id)
{
    var shortName: String = shortName
        set(value) {
            field = value
        }
        get() {
            return field
        }
    var contact: String? = contact
        set(value) {
            field = value
        }
        get() {
            return field
        }

    constructor(sourceSt: Student) : this(
        id = sourceSt.id,
        shortName = sourceSt.getShortNameString(),
        git = sourceSt.git,
        contact = sourceSt.getContactString()
    )

    constructor(sourceString: String) : this(
        id = -1,
        shortName = sourceString.split(" ")[0],
        git = sourceString.split(" ")[1],
        contact = sourceString.split(" ")[2]
    )

    constructor(stView: StudentShortView):this(
        id = stView.id,
        shortName = stView.name,
        git = stView.name,
        contact = stView.contact
    )

    override fun getNames(): List<String> {
        return listOf("id", "short name", "contact", "git")
    }
}