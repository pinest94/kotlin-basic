import PersonKt.phoneNumber
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream
import kotlin.system.exitProcess

// This function fills in a Person message based on user input.
fun promptPerson(): Addressbook.Person = person {
    print("Enter person ID: ")
    id = readlnOrNull()?.toInt() ?: 100

    print("Enter name: ")
    name = readlnOrNull().toString()

    print("Enter email address (blank for none): ")
    val email = readlnOrNull() ?: ""
    if (email.isNotEmpty()) {
        this.email = email
    }

    while (true) {
        print("Enter a phone number (or leave blank to finish): ")
        val number = readlnOrNull() ?: ""
        if (number.isEmpty()) break

        print("Is this a mobile, home, or work phone? ")
        val type = when (readlnOrNull() ?: "mobile") {
            "mobile" -> Addressbook.Person.PhoneType.PHONE_TYPE_MOBILE
            "home" -> Addressbook.Person.PhoneType.PHONE_TYPE_HOME
            "work" -> Addressbook.Person.PhoneType.PHONE_TYPE_WORK
            else -> {
                println("Unknown phone type.  Using home.")
                Addressbook.Person.PhoneType.PHONE_TYPE_HOME
            }
        }
        phones += phoneNumber {
            this.number = number
            this.type = type
        }
    }
}

// Reads the entire address book from a file, adds one person based
// on user input, then writes it back out to the same file.
fun main(args: List<*>) {
    if (args.size != 1) {
        println("Usage: add_person ADDRESS_BOOK_FILE")
        exitProcess(-1)
    }
    val path = Path(args.single())
    val initialAddressBook = if (!path.exists()) {
        println("File not found. Creating new file.")
        addressBook {}
    } else {
        path.inputStream().use {
            Addressbook.AddressBook.newBuilder().mergeFrom(it).build()
        }
    }
    path.outputStream().use {
        initialAddressBook.copy { peopleList += promptPerson() }.writeTo(it)
    }
}