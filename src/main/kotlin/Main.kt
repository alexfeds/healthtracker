import controllers.UserStore
import models.User
import mu.KotlinLogging
import utils.Conversion
import utils.Utils

//val user = User()
val userStore = UserStore()
private val logger = KotlinLogging.logger {}

fun main() {
    logger.info { "Welcome to Health Tracker" }
    //Some Temporary Test Data
    userStore.create((User(1, "Homer Simpson", "homer@simpson.com", 178.0, 2.0f, 'M')))
    userStore.create((User(2, "Marge Simpson", "marge@simpson.com", 140.0, 1.6f, 'F')))
    userStore.create((User(3, "Lisa Simpson", "lisa@simpson.com", 100.0, 1.2f, 'F')))
    userStore.create((User(4, "Bart Simpson", "bart@simpson.com", 80.0, 1.0f, 'M')))
    userStore.create((User(5, "Maggie Simpson", "maggie@simpson.com", 50.0, 0.7f, 'F')))
    runApp()
}

fun addUser() {
    userStore.create(getUserDetails())
}

fun listUsers() {
    println("The user details are:")
    userStore.findAll()
        .sortedBy { it.name }
        .forEach { println(it) }
}

fun listUsersImperial() {
    println("The user details (imperial) are:")
    userStore.findAll()
        .sortedBy { it.name }
        .forEach {
            println(
                "  " + it.name
                        + "(" + it.email + "), "
                        + Conversion.convertKGtoPounds(it.weight, 1.0) + " pounds, "
                        + Conversion.convertMetresToInches(it.height.toDouble(), 1.0) + " inches."
            )
        }
}

fun getUserById(): User? {
    print("Enter the id for th user: ")
    return userStore.findOne(readLine()?.toIntOrNull() ?: -1)
}

fun searchByGender() {
    print("Enter the Gender for the user: ")
    userStore.findByGender(Utils.getAndValidateGender())
        .forEach { println(it) }
}

fun updateUser() {
    listUsers()
    val foundUser = getUserById()

    if (foundUser != null) {
        val user = getUserDetails()
        user.id = foundUser.id

        if (userStore.update(user))
            println("User updated")
        else
            println("User not updated")
    } else
        logger.info { "Search = no user found" }
}

fun searchById() {
    val user = getUserById()
    if (user == null)
        logger.info { "Search = no user found" }
    else
        println(user)
}

fun displayUsersReport() {
    val users = userStore.findAll()
    println(
        """
        |------------------------
        |     USERS REPORT
        |------------------------
        |
        |  Number of Users:  ${users.size}
        |  Gender Breakdown: ${users.groupingBy { it.gender }.eachCount()}
        |  Average Weight:   ${users.sumOf { it.weight }.div(users.size)} kg
        |  Min Weight:       ${users.minByOrNull { it.weight }?.weight} kg
        |  Max Weight:       ${users.maxByOrNull { it.weight }?.weight} kg
        |  Average Height:   ${Conversion.round(users.sumOf { it.height.toDouble() }.div(users.size), 2.0)} metres
        |  Min Height:       ${users.minByOrNull { it.height }?.height} metres
        |  Max Height:       ${users.maxByOrNull { it.height }?.height} metres
        |
        |------------------------
        |""".trimMargin()
    )
}

fun deleteUser() {
    if (userStore.delete((getUserById())))
        println("User deleted")
    else
        logger.info { "Search = no user found" }
}

fun menu(): Int {
    print(
        """      
        |Main Menu:
        | 1. Add User
            | 2. List User
            | 3. Search by Id
            | 4. Delete by Id
            | 5. Find by Id
            | 6. Find by Gender
            | 7. User Report
            | 8. Users Imperial
        | 0. Exit
        |Please enter your options:
        """.trimMargin()
    )
    return readLine()?.toIntOrNull() ?: -1
}

private fun getUserDetails(): User {
    val user = User()
    println("Please enter the following for the user")
    user.name = Utils.getAndValidateName()
    user.email = Utils.getAndValidateEmail()
    print("Weight:")
    user.weight = readLine()?.toDoubleOrNull() ?: 0.0
    print("Height:")
    user.height = readLine()?.toFloatOrNull() ?: 0.0F
    user.gender = Utils.getAndValidateGender()
    return user
}

fun runApp() {
    var input: Int
    do {
        input = menu()
        when (input) {
            1 -> addUser()
            2 -> listUsers()
            3 -> searchById()
            4 -> deleteUser()
            5 -> updateUser()
            6 -> searchByGender()
            7 -> displayUsersReport()
            8 -> listUsersImperial()
            in (9..10) -> println("Feature coming soon")
            0 -> print("Bye...")
            else -> print("Invalid option")
        }
    } while (input != 0)
}