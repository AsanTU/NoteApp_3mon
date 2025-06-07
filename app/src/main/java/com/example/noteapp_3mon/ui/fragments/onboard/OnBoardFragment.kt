package com.example.noteapp_3mon.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp_3mon.R
import com.example.noteapp_3mon.databinding.FragmentOnBoardBinding
import com.example.noteapp_3mon.ui.activity.MainActivity
import com.example.noteapp_3mon.ui.adapters.PagerAdapter
import com.example.noteapp_3mon.ui.fragments.notes.NotesFragment

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private val dots = mutableListOf<View>()
    private val totalPages = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        setupIndicators()
        setCurrentDot(0)
        onClickStart()
    }

    private fun initialize() {
        binding.viewPager.adapter = PagerAdapter(this)
    }

    private fun setupListeners() = with(binding.viewPager) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) = with(binding) {
                super.onPageSelected(position)
                setCurrentDot(position)
                if (position == 2) {
                    txtSkip.visibility = View.GONE
                    btnStart.visibility = View.VISIBLE
                } else {
                    txtSkip.visibility = View.VISIBLE
                    btnStart.visibility = View.GONE
                    txtSkip.setOnClickListener {
                        setCurrentItem(currentItem + 2, true)
                    }
                }


            }
        })
    }

    private fun setupIndicators() {
        dots.clear()
        binding.dotsContainer.removeAllViews()

        for (i in 0 until totalPages) {
            val dot = View(requireContext()).apply {
                val params = ViewGroup.MarginLayoutParams(20, 20)
                params.setMargins(8, 0, 8, 0)
                layoutParams = params
                setBackgroundResource(R.drawable.dot_inactive)
            }
            dots.add(dot)
            binding.dotsContainer.addView(dot)
        }
    }

    private fun setCurrentDot(index: Int) {
        dots.forEachIndexed { i, dot ->
            dot.setBackgroundResource(if (i == index) R.drawable.dot_active else R.drawable.dot_inactive)
        }
    }

    private fun onClickStart() {
        binding.btnStart.setOnClickListener {
            (activity as? MainActivity)?.setOnBoardShown()

            activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.container,
                NotesFragment()
            )?.commit()
        }
    }
}