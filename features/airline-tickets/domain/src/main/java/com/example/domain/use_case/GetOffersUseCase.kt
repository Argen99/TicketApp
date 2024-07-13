package com.example.domain.use_case

import com.example.domain.repository.MainRepository

class GetOffersUseCase(
    private val repository: MainRepository
) {
    operator fun invoke() = repository.getOffers()
}