  import edu.princeton.cs.algs4.Picture;
  import edu.princeton.cs.algs4.StdOut;
  import java.awt.Color;
  import java.lang.*;
  import java.util.*;

  /**
   * SeamCarver is a class that does some sort of witchcraft. Thank god I'm doing
   * this now and not in the 1600's, or else I'd probably tarred and feathered.
   *
   * @author Red Hot
   * @version may the 4th be with you (2018)
   */
  public class SeamCarver {
    private Picture picture;
    private double[][] energyTo;
    private int[][] xTo;

    /**
     * Create a seam carver based on the given picture
     */
    public SeamCarver(Picture picture) {
      this.picture = picture;
    } //SeamCarver(Picture picture)

    /**
     * Current picture
     */
    public Picture picture() {
      return picture;
    } //picture()

    /**
     * Width of current picture
     */
    public int width() {
      return picture.width();
    } //width()

    /**
     * Height of current picture
     */
    public int height() {
      return picture.height();
    } //height()

    /**
     * private method to help find the energy (x)

    private double xGradSquared(int x, int y) {
      double rDiff = Math.pow(picture.get(x - 1, y).getRed() - picture.get(x + 1, y).getRed(),2);
      double gDiff = Math.pow(picture.get(x - 1, y).getGreen() - picture.get(x + 1, y).getGreen(),2);
      double bDiff = Math.pow(picture.get(x - 1, y).getBlue() - picture.get(x + 1, y).getBlue(),2);

      return Math.sqrt(rDiff * rDiff + gDiff * gDiff + bDiff * bDiff);
    } //private xGradSquared(int x, int y)
     */

    /**
     * private method to help find the energy (y)

     private double yGradSquared(int x, int y) {
       double rDiff = Math.pow(picture.get(x, y - 1).getRed() - picture.get(x, y + 1).getRed(),2);
       double gDiff = Math.pow(picture.get(x, y - 1).getGreen() - picture.get(x, y + 1).getGreen(),2);
       double bDiff = Math.pow(picture.get(x, y - 1).getBlue() - picture.get(x, y + 1).getBlue(),2);

       return Math.sqrt(rDiff * rDiff + gDiff * gDiff + bDiff * bDiff);
     } //private yGradSquared(int x, int y)
      */

     /**
      * energy Helper private method
      */
     private double energyHelper(Color up, Color down, Color right, Color left) {
       double xDiff = Math.pow(left.getRed() - right.getRed(),2) + Math.pow(left.getGreen() - right.getGreen(),2) + Math.pow(left.getBlue() - right.getBlue(),2);
       double yDiff = Math.pow(up.getRed() - down.getRed(),2) + Math.pow(up.getGreen() - down.getGreen(),2) + Math.pow(up.getBlue() - down.getBlue(),2);

       return Math.sqrt(xDiff + yDiff);
     } //private energyHelper(Color up, Color down, Color right, Color left)

    /**
     * Energy of pixel at column x, row y
     */
     public double energy(int x, int y) {
       Color up;
       Color down;
       Color right;
       Color left;

         if (x < 0 || x >= width()) {
             throw new IndexOutOfBoundsException();
         } //if

         if (y < 0 || y >= height()) {
             throw new IndexOutOfBoundsException();
         } //if

         if (x == 0) {
           up = this.picture.get(width() - 1, y);
         } else {
           up = this.picture.get(x - 1, y);
         } //if else

         if (x == width() - 1) {
           down = this.picture.get(0, y);
         } else {
           down = this.picture.get(x + 1, y);
         } //if else

         if (y == 0) {
           right = this.picture.get(x, height() - 1);
         } else {
           right = this.picture.get(x, y - 1);
         } //if else

         if (y == height() -1){
           left = this.picture.get(x, 0);
         } else {
           left = this.picture.get(x, y + 1);
          //xGradSquared(x, y) + yGradSquared(x, y);
         } //else

         return energyHelper(up, down, right, left);
     } //energy(int x, int y)

    /**
     * Sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() {
      Picture original = picture;
      Picture transpose = new Picture(original.height(), original.width());

      for (int w = 0; w < transpose.width(); w++) {
        for (int h = 0; h < transpose.height(); h++) {
          transpose.set(w, h, original.get(h, w));
        } //for height
      } //for width
      this.picture = transpose;
      int[] seam = findVerticalSeam();
      this.picture = original;
      return seam;
    } //findHorizontalSeam(int[] seam)

    /**
     * Private relax method - for findVerticalSeam
     */
     private void relax(int x1, int y1, int x2, int y2) {
         if (energyTo[x2][y2] > energyTo[x1][y1] + energy(x2, y2)) {
             energyTo[x2][y2] = energyTo[x1][y1] + energy(x2, y2);
             xTo[x2][y2] = x1;
         } //if
     } //private

    /**
     * Sequence of indices for vertical seam
     */
     public int[] findVerticalSeam() {
         energyTo = new double[width()][height()];
         xTo = new int[width()][height()];

         for (int x = 0; x < width(); x++) {
             for (int y = 0; y < height(); y++) {
               if(y == 0) {
                 energyTo[x][y] = energy(x, y);
              } else {
                energyTo[x][y] = Double.POSITIVE_INFINITY;
              } //if else
            } //for
         } //for

             for (int x = 0; x < width(); x++) {
               for (int y = 0; y < height() - 1; y++) {
                 if (x > 0) {
                     relax(x, y, x - 1, y + 1);
                 } //if

                 relax(x, y, x, y + 1);

                 if (x < width() - 1) {
                     relax(x, y, x + 1, y + 1);
                 } //if
             } //if
         } //if

         // find minimum energy path
         double minEnergy = Double.POSITIVE_INFINITY;
         int minEnergyX = -1;

         for (int w = 0; w < width(); w++) {
             if (energyTo[w][height() - 1] < minEnergy) {
                 minEnergyX = w;
                 minEnergy = energyTo[w][height() - 1];
             } //if
         } //for


         assert minEnergyX != -1;
         int[] seam = new int[height()];
         seam[height() - 1] = minEnergyX;
         int prevX = xTo[minEnergyX][height() - 1];

         for (int h = height() - 2; h >= 0; h--) {
             seam[h] = prevX;
             prevX = xTo[prevX][h];
         } //for

         return seam;
     } //findVerticalSeam()

    /**
     * Remove horizontal seam from current picture
     */
     public void removeHorizontalSeam(int[] seam) {
       // Transpose picture.
       Picture original = picture;
       Picture transpose = new Picture(original.height(), original.width());

       for (int w = 0; w < transpose.width(); w++) {
         for (int h = 0; h < transpose.height(); h++) {
           transpose.set(w, h, original.get(h, w));
         } //for
       } //for

       this.picture = transpose;
       transpose = null;
       original = null;

       // call removeVerticalSeam
       removeVerticalSeam(seam);

       // Transpose back.
       original = picture;
       transpose = new Picture(original.height(), original.width());

       for (int w = 0; w < transpose.width(); w++) {
         for (int h = 0; h < transpose.height(); h++) {
           transpose.set(w, h, original.get(h, w));
         } //for
       } //for

       this.picture = transpose;
       transpose = null;
       original = null;
     } //private removeHorizontalSeam(int[] seam)

    /**
     * Remove vertical seam from current pictuer
     */
    public void removeVerticalSeam(int[] seam) {
      if (seam == null) {
          throw new NullPointerException();
      } //if

      if (seam.length != height()) {
        throw new IllegalArgumentException();
      } //if

      Picture original = this.picture;
      Picture carved = new Picture(original.width() - 1, original.height());

      for (int h = 0; h < carved.height(); h++) {
          for (int w = 0; w < seam[h]; w++) {
              carved.set(w, h, original.get(w, h));
          } //for
          for (int w = seam[h]; w < carved.width(); w++) {
              carved.set(w, h, original.get(w + 1, h));
          } //for
      } //for

      this.picture = carved;
    } //removeVerticalSeam(int[] seam)

    /**
     * Unit testing
     */
    public static void main(String[] args) {

    } //main(String[] args)


  } //SeamCarver
