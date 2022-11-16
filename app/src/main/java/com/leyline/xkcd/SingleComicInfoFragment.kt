package com.leyline.xkcd

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.leyline.xkcd.comic.ComicViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SingleComicInfoFragment : Fragment() {

    private val viewModel by sharedViewModel<ComicViewModel>()
    private lateinit var releaseNumberTextView: TextView
    private lateinit var releaseTitleTextView: TextView
    private lateinit var releaseYearTextView: TextView
    private lateinit var releaseMonthTextView: TextView
    private lateinit var releaseDayTextView: TextView
    private lateinit var linkTextView: TextView
    private lateinit var newsTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var transcriptTextView: TextView
    private lateinit var altTextView: TextView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_single_comic_info, container, false)
        initView(view)
        initObservers()
        initClickListeners()
        return view
    }

    private fun initView(view: View){
        releaseNumberTextView = view.findViewById(R.id.num_TV)
        releaseTitleTextView = view.findViewById(R.id.safe_title_TV)
        releaseYearTextView = view.findViewById(R.id.year_TV)
        releaseMonthTextView = view.findViewById(R.id.month_TV)
        releaseDayTextView = view.findViewById(R.id.day_TV)
        linkTextView = view.findViewById(R.id.link_TV)
        newsTextView = view.findViewById(R.id.news_TV)
        titleTextView = view.findViewById(R.id.title_TV)
        transcriptTextView = view.findViewById(R.id.transcript_TV)
        altTextView = view.findViewById(R.id.alt_TV)
    }

    private fun initObservers(){
        viewModel.uiState.observe(viewLifecycleOwner){ comicDataModel ->
            releaseNumberTextView.text = comicDataModel.num.toString()
            releaseTitleTextView.text = comicDataModel.safe_title
            releaseYearTextView.text = comicDataModel.year
            releaseMonthTextView.text = comicDataModel.month
            releaseDayTextView.text = comicDataModel.day
            linkTextView.text = comicDataModel.link
            newsTextView.text = comicDataModel.news
            titleTextView.text = comicDataModel.title
            transcriptTextView.text = comicDataModel.transcript
            altTextView.text = comicDataModel.alt
        }
    }

    private fun initClickListeners(){

    }

}