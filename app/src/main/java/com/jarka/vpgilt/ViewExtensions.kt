package com.jarka.vpgilt

import android.view.View
import android.widget.ViewAnimator

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
fun ViewAnimator.showChild(child: View) {
    displayedChild = indexOfChild(child)
}