package org.tendiwa.plane.settlements

import org.junit.Test
import org.tendiwa.math.sliders.CircularSlider
import org.tendiwa.plane.geometry.crackedHoleygon.random.RandomCrackedHoleygon
import org.tendiwa.plane.geometry.holeygons.Holeygon
import org.tendiwa.plane.geometry.polygons.masked.PerimeterPiece
import org.tendiwa.plane.geometry.polygons.masked.mask
import org.tendiwa.plane.geometry.rectangles.SquareAt0

class GapingOutlineRoadNetworkShapeTest {
    @Test
    fun `can have gaps`() {
        GapingOutlineRoadNetworkShape(
            RandomCrackedHoleygon(
                Holeygon(SquareAt0(10.0))
            ),
            {
                polygon ->
                polygon.mask(
                    listOf(
                        PerimeterPiece(
                            CircularSlider(0.4),
                            8.0
                        )
                    )
                )
            },
            RoadWidth(1.0)
        )
            .gaps
            .apply { assert(isNotEmpty()) }
    }
}
