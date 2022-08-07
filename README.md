# Polygon
In this assignment I have been asked to examine a given a .kml file, We will examine if the Point is on the Polygon or not.
If it is not inside the polygon, I must return the shortest distance towards the polygon from that desired point.

In this algorithm, the edge of the polygon can be created based on the order of the given coordinates.
The isOn function checks whether the point is on the polygon or not.
If the point is not on the polygon, it will return the shortest distance among the given edges, towards the point I chose.
When we find the same edge, we can search, according to their linear equations, where they meet.
If they meet on the closest edge we found, the meeting point will be saved,
and the distance will be returned from the chosen point towards the meeting point.
In the event that the points do not meet on the closest edge, it will check what the shortest distance between the 2 points of the edge.

After the distance has been found, it create a new polygon with the chosen point, which will fit into the coordinate array.

Next, it will calculate polygons areas, before and after adding the chosen point.
If the area before the addition is smaller than or equal to the area after the addition, we can conclude that the point is inside the polygon.
Otherwise, we will conclude that the point is outside the polygon and return the closest distance towards the polygon.
