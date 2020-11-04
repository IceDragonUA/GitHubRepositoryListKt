package com.evaluation.tests.dao.network

import com.evaluation.executor.TestExecutor
import com.evaluation.network.RestApi
import com.evaluation.search.network.AppRestApiDao
import com.evaluation.search.network.AppRestApiDaoImpl
import com.evaluation.tests.dao.RetrofitMocks
import com.evaluation.tests.test
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


/**
 * @author Vladyslav Havrylenko
 * @since 11.10.2020
 */
class AppRestApiDaoTest {

    private lateinit var restApiDao: AppRestApiDao

    private var appRest: RestApi = RetrofitMocks.appRest

    @Mock
    private lateinit var executor: TestExecutor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        restApiDao = AppRestApiDaoImpl(appRest, executor)
        whenever(executor.mainExecutor).thenReturn(Schedulers.trampoline())
        whenever(executor.postExecutor).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `should do call`() {
        assertNotNull(appRest)
        assertNotNull(executor)
        assertNotNull(restApiDao)

        val query = "IceDragonUA"
        val page = 1
        val perPage = 20

        restApiDao.repositoryList(query, page, perPage).test {
            assertValueCount(1)
            assertNoErrors()
            assertComplete()
        }
    }

    private fun okHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }
}