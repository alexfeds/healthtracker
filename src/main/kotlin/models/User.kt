package models

data class User(
    var id: Int = -1,
    var name: String = "no name yest",
    var email: String = "no email yet",
    var weight: Double = 0.0,
    var height: Float = 0.0F,
    var gender: Char = ' '
)