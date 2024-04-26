package com.example.mid_practice

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mid_practice.databinding.FragmentOneBinding

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // 화면을 만들어서 리턴 (뷰바인딩 방법으로 작성하자)
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        // id가 fragButton인 버튼이 눌리면
        binding.btnDate.setOnClickListener {
            // DatePickerDialog: 날짜 입력받기 (context, 리스너, 기본값 년, 월, 일)
            // show() 필수
            DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {

                // 날짜가 입력되면...
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    binding.btnDate.text = "$year 년 ${month + 1} 월 $dayOfMonth 일"
                }
            }, 2024, 3, 27).show()
        }

        binding.btnTime.setOnClickListener {

            // TimePickerDialog: 시간 입력받기 (context, 리스너, 기본값 시, 분, 24시간 형식)
            // show() 필수
            TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {

                // 시간이 입력되면...
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    if (hourOfDay < 12) {
                        binding.btnTime.text = "오전 $hourOfDay 시 $minute 분"
                    } else {
                        binding.btnTime.text = "오후 $hourOfDay 시 $minute 분"
                    }
                }
            }, 15, 0, true).show()
        }

        // 선택된 아이템의 인덱스를 저장할 변수
        var selected = 0
        val items = arrayOf<String>("Room 1", "Room 2", "Room 3")

        val eventHandler2 = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Toast.makeText(requireContext(), "${items[selected]}이 최종 선택되었습니다.", Toast.LENGTH_SHORT).show()
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Toast.makeText(requireContext(), "예약룸 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                    Log.d("mobileapp", "BUTTON_NEGATIVE")
                }
            }
        }

        binding.btnRoom.setOnClickListener {
            // 하나만 선택 가능한 알림창 만들기
            AlertDialog.Builder(requireContext()).run() {
                setTitle("예약룸 선택하기")
                setIcon(android.R.drawable.ic_dialog_alert)

                // setSingleChoiceItems: 선택될 아이템들 설정 (아이템 배열, 기본 체크될 인덱스, 아이템 클릭 리스너)
                setSingleChoiceItems(items, 0, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        selected = which
                    }
                })
                setPositiveButton("OK", eventHandler2)
                setNegativeButton("CANCEL", eventHandler2)
                show()
            }
        }

        binding.btnEnd.setOnClickListener {
            binding.txtInfo.text = "예약자는 ${binding.editName.text.toString()}\n" +
                    "예약 날짜는 ${binding.btnDate.text.toString()} ${binding.btnTime.text.toString()}\n" +
                    "예약룸은 ${items[selected]}"
            binding.txtInfo.visibility = View.VISIBLE
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