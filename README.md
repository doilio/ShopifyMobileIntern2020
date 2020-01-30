# ShopifyMobileIntern2020
Mobile Developer Intern (Android) - Summer 2020

This is a Simple Memory Game developed for the Shopify Mobile Intern Challenge 2020

[Shopify Mobile Developer Project- Matching Game.pdf](https://github.com/doilio/ShopifyMobileIntern2020/files/4135962/Shopify.Mobile.Developer.Project-.Matching.Game.pdf)

## Game Grid Size
For this game I opted on going with a fixed size grid of **48 products** because of two reasons:

1. I didn't want to sacrifice the first requirement *(The user should have to find a minimum of 10 pairs to win.)*
2. I wanted to keep a consistent look throughout the different game modes (**Match 2, Match 3 & Match 4**)

### Why Grid size 48?

Since I had to match 2, 3 and 4 of the same products, I had to find a number which is divisible by 2,3 & 4, which is 12.

But 12 didn't help much because the first requirement is to match a minimum of 10.
So with 12 being the full grid size:
- In game mode 2 I could only match 6 products.
- In game mode 3 I could only match 4 products.
- In game mode 4 I could only match 3 products.

That being said 48 was my magic number where:
- In game mode 2 I could now match 24 products.
- In game mode 3 I could now match 16 products.
- In game mode 4 I could now match 12 products.

48 Products allowed me to show 6 columns with 8 rows. which didn't disrupt my last row by showing a different set of columns in the last row when compared to the upper ones.

### Application Screens

![device-2020-01-21-212440](https://user-images.githubusercontent.com/38020305/72836327-0632af00-3c95-11ea-815c-b0e38c49887c.png)
![device-2020-01-21-212551](https://user-images.githubusercontent.com/38020305/72836328-0632af00-3c95-11ea-89ab-caebd45e4885.png)
![device-2020-01-20-160404](https://user-images.githubusercontent.com/38020305/72734125-d3f45500-3ba1-11ea-8979-9098a1c21f5c.png)
![device-2020-01-20-160530](https://user-images.githubusercontent.com/38020305/72734126-d48ceb80-3ba1-11ea-8917-1e72718c989f.png)

### Loading and Network Error Screens

![device-2020-01-21-213124](https://user-images.githubusercontent.com/38020305/72836692-d1732780-3c95-11ea-9e66-df6d966d2f97.png)
![device-2020-01-21-213203](https://user-images.githubusercontent.com/38020305/72836695-d20bbe00-3c95-11ea-81c0-9fd00bdf3e64.png)

### Game Screens

![device-2020-01-21-213806](https://user-images.githubusercontent.com/38020305/72837450-2d8a7b80-3c97-11ea-86d2-3af77999e1ab.png)
![device-2020-01-21-214119](https://user-images.githubusercontent.com/38020305/72837452-2d8a7b80-3c97-11ea-8e2b-63e2988fb334.png)

### Victory Screen

![device-2020-01-21-214835](https://user-images.githubusercontent.com/38020305/72837855-dfc24300-3c97-11ea-936d-19bbe2708797.png)

## Built With

* [Android Jetpack](https://developer.android.com/jetpack/?gclid=Cj0KCQjwhJrqBRDZARIsALhp1WQBmjQ4WUpnRT4ETGGR1T_rQG8VU3Ta_kVwiznZASR5y4fgPDRYFqkaAhtfEALw_wcB) - Suite of libraries, tools, and guidance to help developers write high-quality apps easier. 
* [Kotlin](https://kotlinlang.org/) - Cross-platform, statically typed, general-purpose programming language with type inference.
* [Glide](https://github.com/bumptech/glide) - Image downloading and caching library for Android.
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android.
* [Android About Page](https://github.com/medyo/android-about-page) - A library to generate beautiful About Pages with less effort.
* [Material](https://material.io/) - Material design system.

### Prerequisites

To run this code you will need:

```
Android Studio 3.2,
Kotlin 1.3.41,
A stable internet connection.
```

## Authors

* **Doilio A. Matsinhe**  - *Contact me on* - [Twitter](https://twitter.com/DoilioMatsinhe) , [Instagram](https://www.instagram.com/doiliomatsinhe/) , [LinkedIn](https://www.linkedin.com/in/doilio-matsinhe)
