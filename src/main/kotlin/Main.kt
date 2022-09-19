import models.User

var user = User()

fun main() {
    println("Welcome to Health Tracker")
    runApp()
}

fun addUser() {
    println("Please enter the following for the user")
    print("Name:")
    user.name = readLine()!!
    print("Email:")
    user.email = readLine()!!
    print("Id:")
    user.id = readLine()?.toIntOrNull() ?: -1
    print("Weight:")
    user.weight = readLine()?.toDoubleOrNull() ?: 0.0
    print("Height:")
    user.height = readLine()?.toFloatOrNull() ?: 0.0F
    getAndValidateGender()
}

fun getAndValidateGender() {
    var gender: Char
    do {
        print("Gender:")
        gender = readLine()?.getOrNull(0) ?: ' '.uppercaseChar()
        when (gender) {
            !in listOf('F', 'M') -> println("Valid Options are F and M")
            in listOf('F', 'M') -> user.gender = gender
            else -> print("Invalid option")
        }
    } while (gender !in listOf('F', 'M'))
}

fun listUser() {
    print("The user details are: $user")
}

fun menu(): Int {
    print(
        """      
        |Main Menu:
        | 1. Add User
            | 2. List User
        | 0. Exit
        |Please enter your options:
        """.trimMargin()
    )
    return readLine()?.toIntOrNull() ?: -1
}

fun runApp() {
    var input: Int
    do {
        input = menu()
        when (input) {
            1 -> addUser()
            2 -> listUser()
            in (3..6) -> println("Feature coming soon")
            0 -> print("Bye...")
            else -> print("Invalid option")
        }
    } while (input != 0)
}