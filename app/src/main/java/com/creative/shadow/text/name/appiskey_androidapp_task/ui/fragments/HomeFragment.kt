package com.creative.shadow.text.name.appiskey_androidapp_task.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.creative.shadow.text.name.appiskey_androidapp_task.R
import com.creative.shadow.text.name.appiskey_androidapp_task.databinding.FragmentHomeBinding
import com.creative.shadow.text.name.appiskey_androidapp_task.extention.isNetworkAvailable
import com.creative.shadow.text.name.appiskey_androidapp_task.koin.DIComponents
import com.creative.shadow.text.name.appiskey_androidapp_task.adapters.WallpaperListAdapter
import com.creative.shadow.text.name.appiskey_androidapp_task.model.WallpaperModel2
import com.creative.shadow.text.name.appiskey_androidapp_task.utils.Constants


class HomeFragment : Fragment() {

    private var bindingHome:FragmentHomeBinding?=null
    private val binding get() = bindingHome!!
    private var wallpaperListAdapter: WallpaperListAdapter? = null
    private var wallpaperList: ArrayList<WallpaperModel2>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingHome=FragmentHomeBinding .inflate(inflater, container, false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTopLayer()
        if (requireContext().isNetworkAvailable(requireActivity())){
            addItems()
        }else{
            Toast.makeText(requireContext(), "No Internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addItems() {
        wallpaperList = ArrayList()


        DIComponents.wallpaperViewViewModel.muteLivedata.observe(viewLifecycleOwner){
                wallpaperList= it as ArrayList<WallpaperModel2>?
                Log.d("varMsg", "list Size: ${wallpaperList?.size}")

                when (Constants.isSuccessful) {
                    "successful" -> {
                        binding.progressBar.visibility = View.GONE
                        binding.recyclerviewHistory.visibility = View.VISIBLE
                        binding.notFoundImg.visibility = View.GONE

                        wallpaperListAdapter = WallpaperListAdapter(requireContext(),  goTONext = {url ->
                            val bundle=Bundle()
                            bundle.putString("url",url)
                            findNavController().navigate(R.id.fullScreenImgFragment,bundle)
                        })
                        wallpaperList?.let { wallpaperListAdapter?.setConversationList(wallpaperList!!) }
                        binding.recyclerviewHistory.layoutManager = GridLayoutManager(requireContext(), 2)
                        binding.recyclerviewHistory.adapter = wallpaperListAdapter

                        Log.d("varMsg", "successful")

                    }

                    "noResponse" -> {
                        binding.progressBar.visibility = View.GONE
                        binding.notFoundImg.visibility = View.VISIBLE
                        binding.recyclerviewHistory.visibility = View.GONE
                         Log.d("varMsg", "noResponse")
                        Toast.makeText(requireContext(), "noResponse", Toast.LENGTH_SHORT).show()
                    }

                    "Error" -> {
                        binding.progressBar.visibility = View.GONE
                        binding.notFoundImg.visibility = View.VISIBLE
                        binding.recyclerviewHistory.visibility = View.GONE
                         Log.d("varMsg", "Error")
                        Toast.makeText(
                            requireContext(),
                            "This link is not working, try another link please",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    "Failed" -> {
                        binding.progressBar.visibility = View.GONE
                        binding.notFoundImg.visibility = View.VISIBLE
                        binding.recyclerviewHistory.visibility = View.GONE
                        Log.d("varMsg", "Failed")
                        Toast.makeText(requireContext(), "Timeout, Try again", Toast.LENGTH_SHORT)
                            .show()
                    }

                    else -> {
                        binding.progressBar.visibility = View.GONE
                        binding.notFoundImg.visibility = View.VISIBLE
                        binding.recyclerviewHistory.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Something went wrong, try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

    }
    private fun showTopLayer() {
        val topLayer = activity?.findViewById<ConstraintLayout>(R.id.topLayer)
        topLayer?.visibility=View.VISIBLE
    }

}