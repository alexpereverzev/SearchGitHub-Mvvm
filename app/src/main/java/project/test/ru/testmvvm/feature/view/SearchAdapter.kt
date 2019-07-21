package project.test.ru.testmvvm.feature.view


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.fitCenterTransform
import project.test.ru.testmvvm.R
import project.test.ru.testmvvm.feature.domain.model.User


internal class SearchAdapter(private val inflater: LayoutInflater)
    : androidx.recyclerview.widget.RecyclerView.Adapter<SearchViewHolder>() {
    var users: List<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(inflater.inflate(R.layout.user_item, parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        users?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int = users?.size ?: 0
}

class SearchViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {


    private var user: User? = null

    fun bind(user: User) {
        this.user = user
        Glide.with(itemView.context)
            .load(user.avatar_url)
            .apply(fitCenterTransform())
            .into(itemView.findViewById(R.id.iv_avatar))
        itemView.findViewById<TextView>(R.id.tv_user_name).text = user.login
    }
}