package ru.practicum.android.diploma.ui.root

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ActivityRootBinding
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.presentation.MyViewModel

class RootActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRootBinding.inflate(layoutInflater) }

    private val vm by viewModel<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.root_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.searchFragment -> showBottomNavigation()
                R.id.favoriteVacancyFragment -> showBottomNavigation()
                R.id.teamFragment -> showBottomNavigation()
                else -> hideBottomNavigation()
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            vm.getVacancies("Челюстно лицевой хирург").collect {
                when (it) {
                    is SearchResultData.Data -> Log.d("MyLog", "вакансии ${it.value}")
                    is SearchResultData.NoInternet -> Log.d("MyLog", "вакансии ${getString(it.message)}")
                    is SearchResultData.Empty -> Log.d("MyLog", "вакансии ${getString(it.message)}")
                    is SearchResultData.ErrorServer -> Log.d("MyLog", "вакансии ${getString(it.message)}")
                }
            }
        }

        lifecycleScope.launch {
            vm.getCurrentVacancy("92156439").collect {
                when (it) {
                    is SearchResultData.Data -> Log.d("MyLog", "инд ${it.value}")
                    is SearchResultData.NoInternet -> Log.d("MyLog", "инд ${getString(it.message)}")
                    is SearchResultData.Empty -> Log.d("MyLog", "инд ${getString(it.message)}")
                    is SearchResultData.ErrorServer -> Log.d("MyLog", "инд ${getString(it.message)}")
                }
            }
        }

        // Пример использования access token для HeadHunter API
        networkRequestExample(accessToken = BuildConfig.HH_ACCESS_TOKEN)

    }

    private fun networkRequestExample(accessToken: String) {
        // ...
    }

    private fun showBottomNavigation() {
        binding.bottomNavigationView.isVisible = true
        binding.divider.isVisible = true
    }

    private fun hideBottomNavigation() {
        binding.bottomNavigationView.isVisible = false
        binding.divider.isVisible = false
    }
}
