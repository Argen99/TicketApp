package com.example.presentation.di

import com.example.domain.di.hotelsDomainModule
import org.koin.dsl.module

val hotelsModule = module {
    includes(hotelsDomainModule)
}