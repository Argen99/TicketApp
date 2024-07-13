package com.example.presentation.di

import com.example.domain.di.subscriptionsDomainModule
import org.koin.dsl.module

val subscriptionsModule = module {
    includes(subscriptionsDomainModule)
}