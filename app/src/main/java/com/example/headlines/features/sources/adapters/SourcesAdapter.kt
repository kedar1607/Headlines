package com.example.headlines.features.sources.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.headlines.databinding.ItemSourceBinding
import com.example.headlines.features.sources.models.Source
import com.example.headlines.features.sources.diffutils.SourcesDiffUtils
import com.example.headlines.features.sources.viewholders.SourceItemViewHolder

/**
 * Adapter that shows a list of available sources of type [Source].
 */
class SourcesAdapter(private val onClick: ((Source) -> Unit)) :
    ListAdapter<Source, SourceItemViewHolder>(SourcesDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSourceBinding.inflate(inflater, parent, false)
        return SourceItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SourceItemViewHolder, position: Int) {
        val source = getItem(position)
        holder.bind(source, onClick)
    }

}