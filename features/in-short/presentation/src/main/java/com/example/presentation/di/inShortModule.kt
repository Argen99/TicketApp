package com.example.presentation.di

import com.example.domain.di.inShortDomainModule
import org.koin.dsl.module

val inShortModule = module {
    includes(inShortDomainModule)
}