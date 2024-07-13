package com.example.domain.use_case

import com.example.domain.repository.MainRepository

class SetLastDepartureLocation(private val mainRepository: MainRepository) {
    operator fun invoke(location: String) = mainRepository.setLastDepartureLocation(location)
}