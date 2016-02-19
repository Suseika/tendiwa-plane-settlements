package org.tendiwa.plane.settlements.roadNetworkShape

import org.tendiwa.plane.geometry.graphs.Graph2D
import org.tendiwa.plane.geometry.polygons.Polygon
import org.tendiwa.tools.argumentConstraint

interface RoadNetworkShape {
    val roads: Graph2D
    val quarters: Collection<Polygon>
    val roadWidth: RoadWidth
}

data class RoadWidth(val width: Double) {

    init {
        argumentConstraint(
            width,
            { it > 0.0 },
            { "width must be > 0.0" }
        )
    }
}
