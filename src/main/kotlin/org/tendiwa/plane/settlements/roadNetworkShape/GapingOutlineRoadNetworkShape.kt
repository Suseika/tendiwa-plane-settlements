package org.tendiwa.plane.settlements.roadNetworkShape

import org.tendiwa.plane.geometry.crackedHoleygon.CrackedHoleygon
import org.tendiwa.plane.geometry.crackedHoleygon.cracks
import org.tendiwa.plane.geometry.crackedHoleygon.shards
import org.tendiwa.plane.geometry.graphs.Graph2D
import org.tendiwa.plane.geometry.graphs.constructors.Graph2D
import org.tendiwa.plane.geometry.holeygons.polygons
import org.tendiwa.plane.geometry.polygons.Polygon
import org.tendiwa.plane.geometry.polygons.masked.PerimeterPiece
import org.tendiwa.plane.geometry.polygons.masked.mask
import org.tendiwa.plane.geometry.segments.Segment

/**
 * Shape of a settlement's road network that is bounded by a holeygon and has
 * gaps in its bounds.
 */
class GapingOutlineRoadNetworkShape(
    base: CrackedHoleygon,
    placeGaps: (Polygon) -> List<PerimeterPiece>,
    override val roadWidth: RoadWidth
) : RoadNetworkShape {
    override val roads: Graph2D

    override val quarters: Collection<Polygon>

    val gaps: Collection<Segment>

    init {
        val maskedPolygons = base.border.polygons
            .map { it.mask(placeGaps(it)) }
        val actualBorderRoadSegments =
            maskedPolygons
                .flatMap { it.unmasked.flatMap { it.segments } }
        gaps = maskedPolygons
            .flatMap { it.masked.flatMap { it.segments } }
        roads = Graph2D(
            actualBorderRoadSegments + base.cracks()
        )
        quarters = base.shards()
    }
}

