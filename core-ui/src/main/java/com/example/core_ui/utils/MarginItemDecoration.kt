package com.example.core_ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val spaceSize: Int,
    private val orientation: Int = LinearLayoutManager.HORIZONTAL
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        if (orientation == LinearLayoutManager.HORIZONTAL) {
            if (position != itemCount - 1) {
                outRect.right = spaceSize
            }
        }
    }
}