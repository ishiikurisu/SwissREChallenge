#!/usr/bin/env python3
def product_window(window):
    result = 1

    for x in window:
        result *= int(x)

    return result


def main():
    k = "136708422"
    # k = "13678422"
    w = 4
    i = 0
    limit = len(k) - w + 1
    result = 1

    while i < limit:
        window = k[i:i+w]
        product = product_window(window)
        if product > result:
            result = product
        i += 1

    print(result)


if __name__ == '__main__':
    main()
