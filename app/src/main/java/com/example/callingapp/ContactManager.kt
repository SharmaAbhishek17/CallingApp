object ContactManager {

    private val contacts = mapOf(
        "9999999999" to "Sankar Sir",
        "1234567890" to "Abhishek",
        "9876543210" to "Mom",
        "1111111111" to "Dad",
        "108" to "Ambulance",
        "100" to "Police",
    )

    fun getName(number: String?): String {
        val cleanNumber = number?.trim()
        return contacts[cleanNumber] ?: cleanNumber ?: "Unknown"
    }
}