package com.example.groupproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PieceDetailFragement : Fragement{
    private var _binding: PieceDetailBinding? = null
    private val binding get() = _binding!!

    private val characterViewModel: Board by Piece()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = Piece.inflate(inflater, container, false)

        if (arguments != null) {
            val pieces = PieceViewModel.fetchById(requireArguments().getInt(Bundle_ID))

            //  Glide.with(requireContext()).load(_data.image).into(binding.characterImage)
            //  binding.Data.text = getString(R.string.data, data.name)
            //  binding.characterUniverse.text = _data.universe
        }

        return binding.root


    }
    companion object{
        private const val Bundle_ID = "id"

        fun newInstance(id: Int) = PieceDetailFragment().apply {
            arguments = bundleOf(Bundle_ID to id)
        }
    }
}