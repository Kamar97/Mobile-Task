package com.example.mobiletask.ui.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobiletask.data.Constants
import com.example.mobiletask.data.RetrofitService
import com.example.mobiletask.data.response.ModuleData
import com.example.mobiletask.data.response.ModulesApiResponse
import com.example.mobiletask.databinding.FragmentModulesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [ModulesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModulesFragment : Fragment() {

    private val adapter by lazy {
        ModulesAdapter(arrayListOf(), requireContext())
    }

    private var _binding: FragmentModulesBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentModulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callModulesApi()
    }

    private fun callModulesApi() {
        val apiService = RetrofitService.createService()
        apiService?.fetchModules(schoolId = Constants.SCHOOL_ID, username = Constants.USERNAME)
            ?.enqueue(object : Callback<ModulesApiResponse> {
                override fun onResponse(
                    call: Call<ModulesApiResponse>,
                    response: Response<ModulesApiResponse>
                ) {
                    val modulesApiResponse = response.body()
                    if (modulesApiResponse?.statusCode == 200) {
                        val moduleList = modulesApiResponse.data.data.modules
                        setRecyclerView(moduleList)
                    }
                }

                override fun onFailure(call: Call<ModulesApiResponse>, t: Throwable) {
                   showToastError()
                }

            })
    }

    private fun showToastError() {
        Toast.makeText(requireContext(),"Something went wrong!",Toast.LENGTH_SHORT).show()
    }

    private fun setRecyclerView(moduleList: ArrayList<ModuleData>) {
        moduleList.sortByDescending { it.status }
        binding.recyclerView.adapter = adapter
        adapter.modulesList = moduleList
        adapter.notifyDataSetChanged()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}