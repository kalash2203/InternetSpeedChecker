package com.example.internetspeedchecker.prersentation.fragment

import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.TrafficStats
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.internetspeedchecker.databinding.FragmentSpeedInfoBinding
import com.example.internetspeedchecker.prersentation.services.SpeedService
import com.example.internetspeedchecker.prersentation.viewModel.SpeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpeedInfoFragment : BaseFragment<FragmentSpeedInfoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSpeedInfoBinding =
        FragmentSpeedInfoBinding::inflate

    private val viewModel by activityViewModels<SpeedViewModel>()

    override fun setup() {
            requestPostNotificationPermission()

        if (context?.isServiceRunning(SpeedService::class.java) == false) {
            binding?.startstop?.text = "start service"
        }
        else {
            binding?.startstop?.text = "stop service"
        }

        binding?.startstop?.setOnClickListener {
            if (context?.isServiceRunning(SpeedService::class.java) == false) {
                ContextCompat.startForegroundService(
                    requireContext(),
                    Intent(requireContext(), SpeedService::class.java)
                )
                toast("Service Started")
                binding?.startstop?.text = "stop service"
            }
            else {
                requireContext().stopService(Intent(requireContext(), SpeedService::class.java))
                toast("Service Stopped")
                binding?.startstop?.text = "start service"
            }
        }

        viewModel.apply {
                avgDownSpeed.observe(viewLifecycleOwner){
                    Log.d("Speed","Avg Down Speed - $it")
                    binding?.txtAvgDownSpeed?.text = "Average Speed- $it Mbps"
                }
                currentDownSpeed.observe(viewLifecycleOwner){
                    Log.d("Speed","Current Down Speed - $it")
                    binding?.txtCurrDownSpeed?.text = "Current Speed- $it Mbps"
                }
                maxDownSpeed.observe(viewLifecycleOwner){
                    Log.d("Speed","Max Down Speed - $it")
                    binding?.txtMaxDownSpeed?.text = "Maximum Speed- $it Mbps"
                }
                minDownSpeed.observe(viewLifecycleOwner) {
                    Log.d("Speed", "Min Down Speed - $it")
                    binding?.txtMinDownSpeed?.text = "Minimum Speed- $it Mbps"
                }
                avgUpSpeed.observe(viewLifecycleOwner){
                    Log.d("Speed","Avg Up Speed - $it")
                    binding?.txtAvgUpSpeed?.text = "Average Speed- $it Mbps"
                }
                maxUpSpeed.observe(viewLifecycleOwner){
                    Log.d("Speed","Max Up Speed - $it")
                    binding?.txtMaxUpSpeed?.text = "Maximum Speed- $it Mbps"
                }
                minUpSpeed.observe(viewLifecycleOwner){
                    Log.d("Speed","Min Up Speed - $it")
                    binding?.txtMinUpSpeed?.text = "Minimum Speed- $it Mbps"
                }
                currentUpSpeed.observe(viewLifecycleOwner){
                    Log.d("Speed","Current Up Speed - $it")
                    binding?.txtCurrUpSpeed?.text = "Current Speed- $it Mbps"
                }
            }
    }


    private fun requestPostNotificationPermission() {
        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { _: Boolean ->

            }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS)
            }
            return
        }
    }

    @Suppress("DEPRECATION")
    private fun <T> Context.isServiceRunning(service: Class<T>): Boolean {
        return (getSystemService(ACTIVITY_SERVICE) as ActivityManager)
            .getRunningServices(Integer.MAX_VALUE)
            .any { it -> it.service.className == service.name }
    }
}