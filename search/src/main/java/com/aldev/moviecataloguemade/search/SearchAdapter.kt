package com.aldev.moviecataloguemade.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.common.databinding.ItemMovieBinding
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var listSearchResult = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listSearchResult.clear()
        listSearchResult.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val data = listSearchResult[position]
        holder.bindItem(data)
    }

    override fun getItemCount(): Int = listSearchResult.size

    inner class SearchViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Movie) {
            binding.apply {
                tvItemTitle.text = item.title
                tvItemRating.text = item.voteAverage.toString()
                tvItemDesc.text = item.overview

                Glide.with(root)
                    .load(CommonConstant.posterUrlPath + item.posterPath)
                    .apply(RequestOptions())
                    .into(binding.imgItemPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listSearchResult[adapterPosition])
            }
        }
    }
}