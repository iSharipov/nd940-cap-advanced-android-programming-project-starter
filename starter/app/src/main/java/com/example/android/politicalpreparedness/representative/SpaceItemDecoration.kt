package com.example.android.politicalpreparedness.representative

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(private val bottomSpace: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.bottom = bottomSpace
    }
}