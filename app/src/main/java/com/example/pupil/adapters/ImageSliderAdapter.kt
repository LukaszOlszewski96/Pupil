package com.example.pupil.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso

class ImageSliderAdapter(val context: Context, val images: ArrayList<String>): PagerAdapter()
{
    override fun getCount(): Int  = images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var imageView: ImageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Picasso.get()
            .load(images[position])
            .fit()
            .centerCrop()
            .into(imageView)

        container.addView(imageView);
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }

}