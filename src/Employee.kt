data class Employee(val firstName: String, val lastName: String, val salary: Int) {
    fun combineFirstAndLastName() = "$firstName $lastName"
}