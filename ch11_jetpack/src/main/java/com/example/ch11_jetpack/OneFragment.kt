package com.example.ch11_jetpack

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.ch11_jetpack.databinding.FragmentOneBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    // 프래그먼트 생성 시 반드시 작성해야 하는 함수
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // 화면을 만들어서 리턴 (뷰바인딩 방법으로 작성하자)
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        // id가 fragButton인 버튼이 눌리면
        binding.fragButton.setOnClickListener {
            // id가 oneFragment인 프래그먼트의 배경색 변경
            binding.oneFragment.setBackgroundColor(Color.parseColor("#00ffff"))

            // 토스트 띄우기
            // 첫번째 인수 this(fragment)가 아닌 context(액티비티)
            Toast.makeText(context, "oneFragment", Toast.LENGTH_SHORT).show()

            // 알러트 메시지 창 띄우기
            // context가 null이 아닌 경우에만 let 실행 됨 (non-null 만 들어올 수 있음)
            context?.let { it1 ->
                AlertDialog.Builder(it1).run() {
                    setTitle("알림 - 모바일 앱")
                    setIcon(android.R.drawable.ic_dialog_alert)
                    setMessage("정말로 종료하시겠습니까?")
                    setPositiveButton("예", null)
                    setNegativeButton("아니오", null)
                    show()
                }
            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}