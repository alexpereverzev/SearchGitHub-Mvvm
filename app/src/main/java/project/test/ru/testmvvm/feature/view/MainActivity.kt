package project.test.ru.testmvvm.feature.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.jakewharton.rxbinding2.widget.RxSearchView
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import project.test.ru.testmvvm.R
import project.test.ru.testmvvm.feature.domain.model.SearchResponse
import project.test.ru.testmvvm.feature.domain.state.SearchViewState
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SearchViewModel

    private var adapter: SearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = SearchAdapter(LayoutInflater.from(this))
        rv_users.adapter = adapter
        rv_users.layoutManager = LinearLayoutManager(this)


        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        viewModel.result.observe(this, Observer { state -> render(state!!) })
        searchEvent()

    }


    fun render(viewState: SearchViewState) {
        when (viewState) {
            is SearchViewState.SearchNotStartedYet -> renderSearchNotStarted()
            is SearchViewState.LoadingState -> renderLoading()
            is SearchViewState.ShowDataState -> renderResult(viewState.searchResponse)
            is SearchViewState.EmptyResult -> renderEmptyResult()
            is SearchViewState.ErrorState -> {
                renderError()
            }
        }
    }

    private fun searchEvent() {
        viewModel.search(RxSearchView.queryTextChanges(search)
            .skip(SKIP_COUNT) // Because after screen orientation changes query Text will be resubmitted again
            .filter { queryString -> queryString.length > SKIP_COUNT || queryString.isEmpty() }
            .debounce(DELAY_TIME, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .map { it.toString() }
            .observeOn(AndroidSchedulers.mainThread()))
    }

    private fun renderResult(result: SearchResponse) {
        rv_users.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        tv_empty.visibility = View.GONE
        tv_error.visibility = View.GONE
        adapter?.users = result.users
        adapter?.notifyDataSetChanged()
    }

    private fun renderSearchNotStarted() {
        rv_users.visibility = View.GONE
        progressBar.visibility = View.GONE
        tv_error.visibility = View.GONE
        tv_empty.visibility = View.GONE
    }

    private fun renderLoading() {
        rv_users.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        tv_empty.visibility = View.GONE
        tv_error.visibility = View.GONE
    }

    private fun renderError() {
        rv_users.visibility = View.GONE
        progressBar.visibility = View.GONE
        tv_error.visibility = View.VISIBLE
        tv_empty.visibility = View.GONE
    }

    private fun renderEmptyResult() {
        rv_users.visibility = View.GONE
        progressBar.visibility = View.GONE
        tv_error.visibility = View.GONE
        tv_empty.visibility = View.VISIBLE
    }

    companion object {
        private const val DELAY_TIME = 500L
        private const val SKIP_COUNT = 2L
    }

}
