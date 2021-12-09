package com.dipesh.onlinegadgetsstore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dipesh.onlinegadgetsstore.R
import com.dipesh.onlinegadgetsstore.adapter.DiscoverItemAdapter
import com.dipesh.onlinegadgetsstore.database.RoomDb
import com.dipesh.onlinegadgetsstore.entity.DiscoverItem
import com.dipesh.onlinegadgetsstore.model.DiscoverItemModel
import com.dipesh.onlinegadgetsstore.repository.DiscoverItemRepository
import com.smarteist.autoimageslider.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private lateinit var sliderLayout: SliderLayout
    private lateinit var rvProducts: RecyclerView
    private var listProducts=ArrayList<DiscoverItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        //Image slider
        sliderLayout=view.findViewById(R.id.imgSlider)
        rvProducts=view.findViewById(R.id.rvProducts)
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL)
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderLayout.setScrollTimeInSec(1)
        setSliderViews()

        CoroutineScope(Dispatchers.IO).launch {
            val repository= DiscoverItemRepository()
            val response = repository.getProducts()
            val lst =response.data
            RoomDb.getInstance(requireContext()).getProductInfo().insertProduct(lst as List<DiscoverItem>)
            withContext(Dispatchers.Main){

                val productAdapter=DiscoverItemAdapter(lst as ArrayList<DiscoverItem>,context!!)
                rvProducts.layoutManager=GridLayoutManager(context,2)
                rvProducts.adapter=productAdapter
            }
        }

        return view
    }


    private fun setSliderViews() {
        for(i in 0..4){
            val sliderView = DefaultSliderView(context)
            when(i){
                0 ->{
                    sliderView.imageUrl="https://icms-image.slatic.net/images/ims-web/934ef866-8368-49f4-9dc7-cdba33eaf81a.jpg"
                }

                1 ->{
                    sliderView.imageUrl="https://icms-image.slatic.net/images/ims-web/8fc746f1-acd2-4395-a260-546d6ffdc75c.jpg_1200x1200.jpg"
                }
                2 ->{
                    sliderView.imageUrl="https://rukminim1.flixcart.com/flap/844/140/image/9ee6313203b1e13e.jpg?q=50"
                }
                3 ->{
                    sliderView.imageUrl="https://icms-image.slatic.net/images/ims-web/3296ad03-9722-482c-a557-3687c71d9d1f.jpg"
                }
                4 ->{
                    sliderView.imageUrl="https://icms-image.slatic.net/images/ims-web/0375b5b5-9a1d-4009-8944-f3825d52a117.jpg"
                }
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            sliderLayout.addSliderView(sliderView)
        }
    }
}