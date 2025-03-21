package eu.ciambella.sanisettes.di

import eu.ciambella.sanisettes.present.common.mapper.ErrorPropertyMapper
import eu.ciambella.sanisettes.present.common.mapper.MarkerPropertyMapper
import eu.ciambella.sanisettes.present.common.mapper.NavigationBarPropertyMapper
import eu.ciambella.sanisettes.present.common.mapper.SanisetteCardMapper
import eu.ciambella.sanisettes.present.common.mapper.TopAppBarPropertyMapper
import eu.ciambella.sanisettes.present.screen.details.DetailsScreenMapper
import eu.ciambella.sanisettes.present.screen.list.ListScreenMapper
import eu.ciambella.sanisettes.present.screen.maps.MapsScreenMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uiMapperModule = module {
    singleOf(::NavigationBarPropertyMapper)
    singleOf(::ListScreenMapper)
    singleOf(::MapsScreenMapper)
    singleOf(::DetailsScreenMapper)
    singleOf(::TopAppBarPropertyMapper)
    singleOf(::SanisetteCardMapper)
    singleOf(::ErrorPropertyMapper)
    singleOf(::MarkerPropertyMapper)
}
