package com.example.presentation.di

import com.example.domain.di.profileDomainModule
import org.koin.dsl.module

val profileModule = module {
    includes(profileDomainModule)
}