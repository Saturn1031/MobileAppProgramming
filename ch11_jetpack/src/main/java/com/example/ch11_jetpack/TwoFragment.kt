package com.example.ch11_jetpack

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch11_jetpack.databinding.FragmentTwoBinding
import com.example.ch11_jetpack.databinding.ItemRecyclerviewBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

// viewHolder 클래스 정의 (리사이클러 뷰에 필수)
class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

// adapter 클래스 정의 (리사이클러 뷰에 필수)
class MyRecyclerAdapter(val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // 레이아웃 정의 (item_recyclerview.xml을 뷰홀더에 전달)
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding // holder를 MyViewHolder로 캐스팅
        binding.itemData.text = datas[position] // adapter 인수로 전달받은 datas의 position번째 문자열을 item text에 삽입
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

// 리사이클러 뷰 항목 꾸미기
class MyDecoration(val context: Context) : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // 아이템들이 배치되기 전에 호출됨
        super.onDraw(c, parent, state)
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), 0f, 0f, null)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // 아이템들이 배치된 후 호출됨 (위에 덧그려짐)
        super.onDrawOver(c, parent, state)
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), 500f, 500f, null)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // 항목들을 꾸밀 때 호출됨
        super.getItemOffsets(outRect, view, parent, state)
        view.setBackgroundColor(Color.parseColor("#345678"))
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

    // onCreateView: 프래그먼트 구현을 위해 반드시 작성
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTwoBinding.inflate(inflater, container, false)

        // 크기가 바뀔 수 있는 배열 mutableList
        var datas = mutableListOf<String>()
        // 문자열을 datas에 add
        for (i in 1 .. 10) {
            datas.add("Item $i")
        }

        // adapter & viewHolder (리사이클러 뷰에 필수)
        // adapter 설정, 클래스를 정의하여 대입
        val adapter = MyRecyclerAdapter(datas)
        binding.recyclerView.adapter = adapter

        // layoutManager (리사이클러 뷰에 필수)
        // 아이템의 배치 레이아웃을 결정 (Linear)
        val linear = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = linear
        linear.orientation = LinearLayoutManager.HORIZONTAL

        // Grid 레이아웃
        var grid = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = grid

        // 아이템 데코레이션 적용 (선택적)
        binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))

        // 플로팅 액션 버튼을 누르면... 아이템을 추가 (datas에 추가)
        binding.mainFab.setOnClickListener {
            datas.add("Android Add")
            
            // adapter에게 데이터가 바뀌었음을 알려야 함
            adapter.notifyDataSetChanged()
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