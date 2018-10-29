/******************************************************************************
 *  Name: Noah Carpenter
 *
 *  Operating system: Macintosh El Capitan
 *  Compiler: Javac
 *  Text editor / IDE: Atom
 *  Hours to complete assignment (optional): 18 - 22
 ******************************************************************************/

/******************************************************************************
 *  Describe concisely your algorithm to compute the horizontal and
 *  vertical seam.
 *****************************************************************************/
first, I set my two double nested variables energyTo and xTo to be the width,
and height respectively. then, the fun begins. I loop through, and relax the
edges/vertices, and assign the values the lowest possible value. Lastly, I
return the seam that will be removed later

for horizontal seam, I transpose the picture, run vertical seam on the
transposed picture, and then return the seam, to be removed later.

/******************************************************************************
 *  Describe what makes an image ideal for this seamCarving algorithm and what
 *   kind of image would not work well.
 *****************************************************************************/
The ideal image would keep the more detailed parts of a picture looking the
same, things such as people, mountains, or structures, and less important
visuals such as the sky, or random pieces of grass, or the area between people
would disappear in such a fashion that the objects may look closer together,
but wouldn't change.

/******************************************************************************
 *  Give a formula (using tilde notation) for the running time
 *  (in seconds) required to reduce image size by one row and a formula
 *  for the running time required to reduce image size by one column.
 *  Both should be functions of W and H. Removal should involve exactly
 *  one call to the appropriate find method and one call to the
 *  appropriate remove method. The randomPicture() method in SCUtility
 *  may be useful.
 *
 *  Justify your answer with sufficient data using large enough
 *  W and H values. To dampen system effects, you may wish to perform
 *  many trials for a given value of W and H and average the results.
 *
 *  Be sure to give the leading coefficient and the value of the exponents
 *  as a fraction with 2 decimal places .
 *****************************************************************************/
W x H = n^2 = big O


W = 30

 H           Row removal time (seconds)     Column removal time (seconds)
--------------------------------------------------------------------------
10      21.58 seconds      ---
20      24.85 seconds      ---
40      37.61 seconds      ---
80      58.04 seconds      ---


H =

 W           Row removal time (seconds)     Column removal time (seconds)
--------------------------------------------------------------------------
10      23.22 seconds      ---
20      28.36 seconds      ---
40      37.76 seconds      ---
80      53.28 seconds      ---


Running time to remove one row as a function of both W and H:  ~



Running time to remove one column as a function of both W and H:  ~






/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
I will admit my code isn't the most efficient, so it's a bit slow. Other than
that, it seems to run smoothly

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and exercises, but do
 *  include any help from people (including classmates and friends) and
 *  attribute them by name.
 *****************************************************************************/
Joey bloom at the QRS, Jake Norris

/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/
It took me about 5 hours to debug my code, and then you came in told me it was
an error with my terminal inputs. I've never been closer to smashing my
computer to the ground

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/
 This was a fun class, I really enjoyed seeing all the cool things I could do
 with this class.
