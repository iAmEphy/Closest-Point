	/** A main class for the Closest Pair algorithms
	 *  Programming assignment for
	 *  CSI403 Algorithms and Data Structures
	 *  University at Albany - SUNY
	 *  
	 * Instructions: Implement methods: 
	 * 1) getCPBruteForce()
	 * 2) getCPDivideAndConquer()
	 * As discussed in class and in the assignment part (a)
	 */
	package closestpair;
	import java.util.HashSet;

	public class ClosestPair {
		

		public static Point[] getCPBruteForce (Point[] pts)  {

			Point x;
			x = null;

			Point y;
			y = null;

			Double minimumdistance = Double.MAX_VALUE;

			for(int i = 0; i < pts.length - 1; i++){

				int a;
				for(a = i + 1; j < pts.length; a++){
					Double distance = pts[i].dist(pts[a]);

					if(distance < minimumdistance){

						x = pts[i];
						y = pts[a]; 
						minimumdistance = distance;
					}


				}

			/**So this will get the disntance between two points
			and iterate thorugh all the points. If the points are closer to the current minimum, it will store it

			*/
			}





			return new Point[]{x, y};
		}
		
		/** A driver for the Divide-And-Conquer method for the closest pair
		 *  takes unsorted array of points, sorts them and invokes 
		 *  the recursive method you are required to implement
		 *  
		 *  @returns an array of exactly the two closest points
		 *  IMPORTANT: DO NOT CHANGE THIS METHOD
		 */
		public static Point[] getCPDivideAndConquer(Point[] pts) {
			Point[] ptsX = Point.sortByX(pts); 
			Point[] ptsY = Point.sortByY(pts);
			return getCPDivideAndConquer(ptsX, ptsY);
		}
		
		/** TODO: IMPLEMENT 
		 * 
		 *  takes as input the points sorted by increasing x
		 *  and y coordinates in ptsX and ptsY respectively
		 *  @returns an array of exactly the two closest points.
		
		 */
		public static Point[] getCPDivideAndConquer(Point[] ptsX, Point[] ptsY) {


			if(ptsX.length <= 3){
				return getCPBruteForce(ptsX_);
			}
           /** So the first part checks for n less than 3 to brute force it.

           then it finds the medium x to use as the vertical line

           */
			int medium = ptsX.length / 2;
			double l = (ptsX[medium - 1].x + ptsX[medium].x) / 2;

			Point[] x1 = new Point[medium];
			Point[] x2 = new Point[ptsX.length - medium];
			Point[] y1 = new Point[medium];
			Point[] y2 = new Point[ptsX.length - medium];

			int x1temp = 0;
			int x2temp = 0;
			int y1temp = 0;
			int y2temp = 0;

			/** this whole part will divide the x and y into the left and right part of the equation to calculate and
			perform the recursion
			*/
			for(int i = 0; i < ptsX.length; ++i){
				if(ptsX[i].x < 1){
					x1[x1temp] = ptsX[i];
					++x1temp;
				} 
				else{
					x2[x2temp] = ptsX[i];
					++x2temp;
				}

				if(ptsY[i].x < 1){
					y1[y1temp] = ptsY[i];
					++y1temp;
				}
				else{
					y2[y2temp] = ptsY[i];
					++y2temp;
				}
			}

			Point[] leftresult = getCPDivideAndConquer(x1, y1);
			Point[] rightresult = getCPDivideAndConquer(x2, y2);

			double min = Math.min(leftresult[0].dist(leftresult[1]), rightresult[0].dist(rightresult[1]));

			Point[] ptsYs = new Point[ptsX.length];
			int ptsYsize = 0;

			for(int i = 0; i < ptsY.length; ++i){
				if(Math.abs(ptsY[i].x - 1) < min){
					ptsYs[ptsYsize] = ptsY[i];
					++ptsYsize;
				}
			}

			/** this will check points for minimimum distance of the line*/

			double secondmin = min;

			Point[] result = new Point[2];

			for(int i = 0; i < ptsYsize - 1; ++i){
				for(int a = i + 1; a < Math.min(i + 5, ptsYsize); ++a){

					if(ptsYs[i].dist(ptsYs[a]) < secondmin){
						secondmin = ptsYs[i].dist(ptsYs[a]);

						result[0] = ptsYs[i];
						result[1] = ptsYs[a];
					}
				}


			}


			if(secondmin < min){
				return result;
			}

			if(leftresult[0].dist(leftresult[1]) < rightresult[0].dist(rightresult[1])){
				return leftresult;
			}

			return rightresult;

			/**so the first will check if the minimum is less than the current min and if so, it will use that minimum

			then it will check for the lesser of the left and right results and return that;
			*/
		}
	}