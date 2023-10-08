package com.leyline.xkcd

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.facebook.drawee.view.SimpleDraweeView
import com.leyline.xkcd.comic.ComicViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SingleComicViewFragment : Fragment() {
    private val viewModel by sharedViewModel<ComicViewModel>()
    private lateinit var comicImageView: SimpleDraweeView
    private lateinit var firstComicTextView: TextView
    private lateinit var lastComicTextView: TextView
    private lateinit var comicInfoTextView: TextView
    private lateinit var nextComicTextView: TextView
    private lateinit var latestComicTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_single_comic_view, container, false)
        initView(view)
        initObservers()
        initClickListeners()
        return view
    }

    private fun initView(view: View) {
        // Could be removed with databinding, I personally like have this block here.
        comicImageView = view.findViewById(R.id.img_ImgV) as SimpleDraweeView
        firstComicTextView = view.findViewById(R.id.first_comic_TV)
        lastComicTextView = view.findViewById(R.id.last_comic_TV)
        comicInfoTextView = view.findViewById(R.id.comic_info_TV)
        nextComicTextView = view.findViewById(R.id.next_comic_TV)
        latestComicTextView = view.findViewById(R.id.latest_comic_TV)
    }

    private fun initObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { comicDataModel ->
            val imageUrl: String = comicDataModel.img
            val imageUri: Uri = Uri.parse(imageUrl)
            comicImageView.setImageURI(imageUri, null)
        }
        viewModel.currentComicId.observe(viewLifecycleOwner) { int ->
            lifecycleScope.launch {
                viewModel.getComicById(int)
            }
        }
    }

    private fun initClickListeners() {
        firstComicTextView.setOnClickListener {
            viewModel.requestFirstComic()
        }
        lastComicTextView.setOnClickListener {
            viewModel.decrementCurrentComic()
        }
        comicInfoTextView.setOnClickListener {
            val action = SingleComicViewFragmentDirections.actionSingleComicViewFragmentToSingleComicInfoFragment()
            findNavController().navigate(action)
        }
        nextComicTextView.setOnClickListener {
            viewModel.incrementCurrentComic()
        }
        latestComicTextView.setOnClickListener {
            viewModel.requestLatestComic()
        }
        // Request latest comic once
        viewModel.requestLatestComic()
    }

}