package com.example.headlines.features.sources.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.headlines.databinding.ItemSourceBinding
import com.example.headlines.features.sources.models.Source
import com.example.headlines.utils.extensions.setVisible

/**
 * View item for source [Source]
 * in the [com.example.headlines.features.sources.adapters.SourcesAdapter].
 */
class SourceItemViewHolder(private val binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(source: Source, onClick: ((Source) -> Unit)) {
        binding.tvSrcName.text = source.name
        (source.description != null).let {
            binding.tvSrcDesc.setVisible(it)
            if (it) {
                binding.tvSrcDesc.text = source.description
            }
        }

        itemView.setOnClickListener {
            onClick.invoke(source)
        }
    }

}

