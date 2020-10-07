package com.example.headlines.sources.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.headlines.models.source.Source

/**
 * This diff util is used to compare two different sources
 */
class SourcesDiffUtils : DiffUtil.ItemCallback<Source>() {

    override fun areItemsTheSame(old: Source, aNew: Source): Boolean {
        return old.id == aNew.id
    }

    override fun areContentsTheSame(old: Source, aNew: Source): Boolean {
        return old == aNew
    }

}