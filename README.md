Bugs:
1. If a PolynomialModP is constructed but the lc mod p == 0, then the lc will be 0. This is incorrect since it should just remove it.
