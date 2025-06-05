package com.example.noteapp_3mon.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp_3mon.R
import com.example.noteapp_3mon.databinding.FragmentPagerBinding

class PagerFragment : Fragment() {

    private lateinit var binding: FragmentPagerBinding

    companion object {
        const val ARG_ONBOARD_POSITION = "onBoardPosition"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                title1.text = "Удобство"
                title2.text = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
                lottieAnimationView.setAnimation(R.raw.first)
                lottieAnimationView.playAnimation()
            }
            1 -> {
                title1.text = "Организация"
                title2.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
                lottieAnimationView.setAnimation(R.raw.second)
                lottieAnimationView.playAnimation()
            }
            2 -> {
                title1.text = "Синхронизация"
                title2.text = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
                lottieAnimationView.setAnimation(R.raw.therd)
                lottieAnimationView.playAnimation()
            }
        }
    }
}