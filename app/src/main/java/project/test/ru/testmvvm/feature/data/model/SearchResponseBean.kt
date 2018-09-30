package project.test.ru.testmvvm.feature.data.model



data class SearchResponseBean(val total_count: Int, val items: List<UserBean>)

data class UserBean(val id: Int, val login: String, val avatar_url: String)