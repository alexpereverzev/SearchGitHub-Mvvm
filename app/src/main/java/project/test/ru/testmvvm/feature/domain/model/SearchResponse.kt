package project.test.ru.testmvvm.feature.domain.model

data class SearchResponse(var total: Int? = 0, var users: List<User>? = null)

data class User(val id: Int, val login: String, val avatar_url: String)