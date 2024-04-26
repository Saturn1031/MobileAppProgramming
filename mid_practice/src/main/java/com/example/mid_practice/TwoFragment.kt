package com.example.mid_practice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mid_practice.databinding.FragmentTwoBinding
import com.example.mid_practice.databinding.ItemRecyclerviewBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

// adapter 클래스 정의 (리사이클러 뷰에 필수)
class MyRecyclerAdapter(val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // 레이아웃 정의 (item_recyclerview.xml을 뷰홀더에 전달)
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding // holder를 MyViewHolder로 캐스팅
        binding.txtName.text = datas[position].split('\n')[0] // adapter 인수로 전달받은 datas의 position번째 문자열을 item text에 삽입
        binding.phone.text = datas[position].split('\n')[1]
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}



class TwoFragment : Fragment() {
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
        val binding = FragmentTwoBinding.inflate(inflater, container, false)

        // 크기가 바뀔 수 있는 배열 mutableList
        var datas = mutableListOf<String>()


        // adapter & viewHolder (리사이클러 뷰에 필수)
        // adapter 설정, 클래스를 정의하여 대입
        val adapter = MyRecyclerAdapter(datas)
        binding.recyclerView.adapter = adapter

        // layoutManager (리사이클러 뷰에 필수)
        // 아이템의 배치 레이아웃을 결정 (Linear)
        val linear = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = linear
        linear.orientation = LinearLayoutManager.VERTICAL

        // 아이템 데코레이션 적용 (선택적)
        // binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))

        val requestlauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            // 콜백 (AddActivity에서 데이터 받음)
            it.data!!.getStringExtra("recycleText")?.let { // let: null이 아니면 실행
                // datas에 문자열 추가하고 리사이클러뷰에 변경 알림
                if (it != "") {
                    datas?.add(it)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        // 플로팅 액션 버튼을 누르면... 아이템을 추가 (datas에 추가)
        binding.mainFab.setOnClickListener {
            // 인텐트 생성
            val intent = Intent(requireContext(), SecondActivity::class.java)

            if (binding.btnFriend.isChecked) {
                intent.putExtra("checked", "${binding.btnFriend.text.toString()}")
            } else {
                intent.putExtra("checked", "${binding.btnRocation.text.toString()}")
            }

            requestlauncher.launch(intent)
        }

        return binding.root // 반환한 뷰 객체가 출력 됨
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TwoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}