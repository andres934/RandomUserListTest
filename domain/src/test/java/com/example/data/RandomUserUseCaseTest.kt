package com.example.data

import androidx.paging.PagingData
import com.example.domain.RandomUserRepository
import com.example.domain.RandomUserUseCase
import com.example.domain.model.UserData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RandomUserUseCaseTest {

    private val fakeRepository: RandomUserRepository = mockk()
    private val useCase = RandomUserUseCase(fakeRepository)

    @Test
    fun `on getRandomUsers successful call should return data`() {
        runTest {
            // Given
            val expectedResult: Flow<PagingData<UserData>> = mockk()

            coEvery {
                fakeRepository.getRandomUsers()
            } returns expectedResult

            // When
            val result = useCase.getRandomUsers()

            // Then

            coVerify(exactly = 1) {
                fakeRepository.getRandomUsers()
            }
            assert(result == expectedResult)
            confirmVerified(fakeRepository)
        }
    }

    @Test
    fun `on getRandomUsers failed call should thrown exception`() {
        runTest {
            // Given
            val expectedResult = IllegalArgumentException()

            coEvery {
                fakeRepository.getRandomUsers()
            } throws expectedResult

            try {
                // When
                useCase.getRandomUsers()
            } catch (e: IllegalArgumentException) {
                // Then
                coVerify(exactly = 1) {
                    fakeRepository.getRandomUsers()
                }
                assert(e == expectedResult)
            }
            confirmVerified(fakeRepository)
        }
    }
}