int sumOfTheDigitsOfHarshadNumber(int x) {
    int sumNumber = 0;
    int temp = x;
    while (temp) {
        sumNumber += temp % 10;
        temp /= 10;
    }
    if (x % sumNumber == 0)
        return sumNumber;
    return -1;
}