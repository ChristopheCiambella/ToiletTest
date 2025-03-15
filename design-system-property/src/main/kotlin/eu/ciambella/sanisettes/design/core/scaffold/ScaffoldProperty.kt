package eu.ciambella.sanisettes.design.core.scaffold

import eu.ciambella.sanisettes.design.core.bottombar.NavigationBarProperty
import eu.ciambella.sanisettes.design.core.content.ContentProperty

data class ScaffoldProperty(
    val contentProperty: ContentProperty,
    val navigationBarProperty: NavigationBarProperty?
)
