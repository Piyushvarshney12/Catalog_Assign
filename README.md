# Catalog_Assign
Catalog Assignment

Steps the Program Takes:
a) Read the JSON Input:
  1) The program receives input in JSON format, which contains some information (like the number of points n and the minimum points needed k).
  2) Each point in the JSON consists of:
   2.1) A number (which is the x value, or position of the point).
   2.2) A base (which tells us how the y value is encoded).
   2.3) A value (the actual encoded y value).
b) Convert the Encoded Values:
  1) The y values are not in normal decimal (base 10), so the program converts them to base 10 using the provided base. For example:
   1.1) If the value is "111" in base 2, it will be converted to 7 in base 10.
   1.2) If the value is "213" in base 4, it will be converted to 39 in base 10.
  2) This gives us pairs of x and y values like (1, 4), (2, 7), etc.
c) Use Lagrange Interpolation:
  1) Once the program has the x and y pairs, it uses Lagrange interpolation, a mathematical formula, to calculate the constant term of the polynomial.
  2) Lagrange interpolation helps us find the polynomial’s value at any point using several given points. In this case, we want to find the value at x = 0, which gives us the constant term (i.e., f(0)).
d) Print the Result:
  1) After calculating the constant term using interpolation, the program prints the result, which is the secret.
