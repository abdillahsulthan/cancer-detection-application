package com.dicoding.asclepius.view.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.remote.response.ArticleResponse
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleViewModel : ViewModel() {

    private val _listArticle= MutableLiveData<List<ArticlesItem?>?>()
    val listArticle: LiveData<List<ArticlesItem?>?> = _listArticle

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getAllArticles()
    }

    private fun getAllArticles() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllArticles(q = "cancer", category = "health", language = "en")
        client.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val filteredArticles = responseBody.articles?.filter { article ->
                            !article?.title.isNullOrEmpty() &&
                                    !article?.description.isNullOrEmpty() &&
                                    !article?.title?.contains("[Removed]", ignoreCase = true)!! &&
                                    !article.description?.contains("[Removed]", ignoreCase = true)!!
                        }
                        _listArticle.value = filteredArticles
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object{
        private const val TAG = "ArticleViewModel"
    }
}