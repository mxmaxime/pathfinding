package traverse;

import map.MapMatrix;
import org.junit.jupiter.api.Test;
import plan.Step2D;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstIteratorTest {

  @Test
  void yolo() {
    int xSize = 5;
    int ySize = 5;

    Step2D<MapMatrix.Values>[][] matrix = new Step2D[xSize][ySize];

    for (int x = 0; x < xSize; x++) {
      for (int y = 0; y < ySize; y++) {
        matrix[x][y] = new Step2D<>(x, y, MapMatrix.Values.ROAD);
      }
    }

    MapMatrix mapMatrix = new MapMatrix(matrix);

    BreadthFirstIterator it = new BreadthFirstIterator<>(mapMatrix, matrix[0][0], matrix[3][3]);
    assertTrue(it.hasNext());

    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}
