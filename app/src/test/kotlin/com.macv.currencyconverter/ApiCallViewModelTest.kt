package com.macv.currencyconverter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.macv.currencyconverter.data.repository.CurrencyRateRepository
import com.macv.currencyconverter.data.repository.CurrencyRepository
import com.macv.currencyconverter.viewmodel.ConversionViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ApiCallViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = InitUnitTest()

    @Mock
    private lateinit var currencyRepository: CurrencyRepository

    @Mock
    private lateinit var currencyRateRepository: CurrencyRateRepository

    private lateinit var viewModel: ConversionViewModel

    @Before
    fun setUp() {
        viewModel = ConversionViewModel(currencyRepository, currencyRateRepository)
    }

    @Test
    fun testCurrencyList() {
        testCoroutineRule.runBlockingTest {
            viewModel.loadCurrencyList()
            viewModel.onCurrencyList.observeForever {
                assert(it.peekContent().currencies.size >= 0)
            }
        }
    }

    @Test
    fun testCurrencyRateList() {
        testCoroutineRule.runBlockingTest {
            viewModel.loadCurrencyRate()
            viewModel.onCurrencyRateList.observeForever {
                assert(it.peekContent().size >= 0)
            }
        }
    }
}