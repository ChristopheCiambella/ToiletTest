package eu.ciambella.sanisettes.design.core.topbar

sealed class TopAppBarProperty {
    data class SimpleAppBar(
        val title: String,
        val onBackClicked: (() -> Unit)? = null
    ) : TopAppBarProperty()

    data class SwitchFilterAppBar(
        val title: String,
        val switchLabel: String,
        val switchFilterOn: Boolean,
        val switchFilterChanged: (newValue: Boolean) -> Unit
    ) : TopAppBarProperty()
}
