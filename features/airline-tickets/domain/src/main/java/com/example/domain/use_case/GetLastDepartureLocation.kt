package com.example.domain.use_case

import com.example.domain.repository.MainRepository

class GetLastDepartureLocation(private val mainRepository: MainRepository) {
    operator fun invoke() = mainRepository.getLastDepartureLocation()
}