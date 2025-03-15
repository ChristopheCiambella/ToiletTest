package eu.ciambella.toilettest.present.screen.maps

import eu.ciambella.design.toilettest.components.ShimmerProperty
import eu.ciambella.design.toilettest.content.ContentProperty
import eu.ciambella.design.toilettest.content.LazyColumnContentProperty
import eu.ciambella.design.toilettest.content.MapsContentProperty
import eu.ciambella.design.toilettest.scaffold.ScaffoldProperty
import eu.ciambella.toilettest.domain.toilet.model.Toilet
import eu.ciambella.toilettest.present.common.mapper.NavigationBarPropertyMapper
import eu.ciambella.toilettest.present.common.mapper.RouteNavigationBarProperty
import eu.ciambella.toilettest.present.common.navigation.EventActionHandler

class ToiletMapsScreenMapper(
    private val navigationBarPropertyMapper: NavigationBarPropertyMapper,
) {

    private fun scaffold(
        contentProperty: ContentProperty,
        eventActionHandler: EventActionHandler,
    ) = ScaffoldProperty(
        contentProperty = contentProperty,
        navigationBarProperty = navigationBarPropertyMapper.main(
            selectedRoute = RouteNavigationBarProperty.MAPS,
            eventActionHandler = eventActionHandler,
        ),
    )

    fun loading(
        eventActionHandler: EventActionHandler
    ): ScaffoldProperty = scaffold(
        eventActionHandler = eventActionHandler,
        contentProperty = LazyColumnContentProperty(
            items = mutableListOf(ShimmerProperty)
        )
    )

    fun map(
        state: ToiletMapsState,
        eventActionHandler: EventActionHandler,
    ): ScaffoldProperty {
        if (state.toilets == null) {
            return loading(eventActionHandler)
        }
        return state.toilets.fold(
            onSuccess = {
                mapSuccess(it, eventActionHandler)
            },
            onFailure = {
                TODO()
            }
        )
    }

    private fun mapSuccess(
        toilets: List<Toilet>,
        eventActionHandler: EventActionHandler,
    ) = scaffold(
        eventActionHandler = eventActionHandler,
        contentProperty = MapsContentProperty(
            startLatitude = toilets[0].latitude,
            startLongitude = toilets[0].longitude,
            startZoom = 12f,
        )
    )

}

