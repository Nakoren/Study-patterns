import program_classes.Model.Student
/*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.*

 */
class StudentTest {
    /*
    @Test
    fun `create student with only required fields`() {
        val student = Student(1, "Иванов", "Иван", "Иванович")
        assertEquals(1, student.id)
        assertEquals("Иванов", student.father_name)
        assertEquals("Иван", student.name)
        assertEquals("Иванович", student.fam_name)
        assertNull(student.phone)
        assertNull(student.telegram)
        assertNull(student.email)
        assertNull(student.git)
    }

    @Test
    fun `create student with all fields correctly filled`() {
        val student = Student(
            1, "Иванов", "Иван", "Иванович",
            "+79123456789",
            "@ivanov",
            "ivanov@example.com",
            "ivanov-github"
        )
        assertEquals(1, student.id)
        assertEquals("Иванов", student.father_name)
        assertEquals("Иван", student.name)
        assertEquals("Иванович", student.fam_name)
        assertEquals("+79123456789", student.phone)
        assertEquals("@ivanov", student.telegram)
        assertEquals("ivanov@example.com", student.email)
        assertEquals("ivanov-github", student.git)
    }

    @Test
    fun `create student from a string`() {
        val student = Student("id:1, lastName:Иванов, firstName:Иван, middleName:Иванович, telegram:@ivanov, email:ivanov@example.com, phone:+79001234567, github:johndoe")
        assertEquals(1, student.id)
        assertEquals("Иванов", student.father_name)
        assertEquals("Иван", student.name)
        assertEquals("Иванович", student.fam_name)
        assertEquals("+79001234567", student.phone)
        assertEquals("@ivanov", student.telegram)
        assertEquals("ivanov@example.com", student.email)
        assertEquals("johndoe", student.git)
    }

    @Test
    fun `create student with incorrect phone number`() {
        var test = Student(1, "Иванов", "Иван", "Иванович", "123", null, null, null)
        assertEquals(test.phone, null)
    }

    @Test
    fun `create student without firstName`() {
        assertThrows<IllegalArgumentException> {
            Student(hashMapOf(
                "id" to "1",
                "lastName" to "Иванов",
                "middleName" to "Иванович"
            ))
        }
    }

    @Test
    fun `create student without middle name`() {
        assertThrows<IllegalArgumentException> {
            Student(hashMapOf(
                "id" to "",
                "lastName" to "Иванов",
                "firstName" to "Иван"
            ))
        }
    }

    @Test
    fun `create student with null optional fields`() {
        val student = Student(1, "Иванов", "Иван", "Иванович",
            null, null, null, null)
        assertEquals(1, student.id)
        assertEquals("Иванов", student.fam_name)
        assertEquals("Иван", student.name)
        assertEquals("Иванович", student.father_name)
        assertNull(student.phone)
        assertNull(student.telegram)
        assertNull(student.email)
        assertNull(student.git)
    }

    @Test
    fun `modify phone number to valid one`() {
        val student = Student(1, "Иванов", "Иван", "Иванович")
        student.phone = "+1234567890"
        assertEquals("+1234567890", student.phone)
    }

    @Test
    fun `modify phone number to invalid one`() {
        val student = Student(1, "Иванов", "Иван", "Иванович")
        student.phone = "123"
        assertEquals(student.phone, null)
    }

    @Test
    fun `test invalid email`() {
        val test = Student(1, "Иванов", "Иван", "Иванович", null, "invalid_email", null, null)
        assertEquals(test.email, null)
    }


    @Test
    fun `test invalid middle name`() {
        assertThrows<IllegalArgumentException> {
            Student(1, "Иванов", "Иван", "123")
        }
    }

    @Test
    fun `test setContacts method`() {
        val student = Student(1, "Иванов", "Иван", "Иванович")
        student.setContacts(phone = "+79123456789", telegram = "@ivanov", email = "ivanov@example.com")
        assertEquals("+79123456789", student.phone)
        assertEquals("@ivanov", student.telegram)
        assertEquals("ivanov@example.com", student.email)
    }

     */
}