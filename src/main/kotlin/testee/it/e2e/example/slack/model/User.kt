package testee.it.e2e.example.slack.model

/**
 * Model for users which we will use in our tests.
 */
enum class User(val username: String, val password: String) {

    TEST("email", "password"),

}