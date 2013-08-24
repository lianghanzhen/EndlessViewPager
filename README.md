# Endless ViewPager #

This is a custom ViewPager widget that can be scrolled infinitely. It is useful for some cases, like ads banner.
It can be used with the popular project: Android-ViewPagerIndicator.


## How to use it ##

1. checkout my fork: https://github.com/lianghanzhen/Android-ViewPagerIndicator. It has been customized to make sure that can be used with EndlessViewPager.

2. checkout the repository, then import the library. make Android-ViewPagerIndicator a dependency to it.

3. the sample project shows you how to use this library, enjoy yourself.


## Bonus ##

This project has a class called BannerHandler to handle auto scrolling. The API is so simple:

+ *start()*: start handling auto scrolling
+ *stop()*: stop handling auto scrolling
+ *setSwitchDelay*: set the delay of auto scrolling


## Note ##

EndlessViewPager only supports >=3 pagers when you use FragmentPagerAdapter or FragmentStatePagerAdapter. When EndlessViewPager start scrolling infinitely, the pages that being created and destroyed are the same page if you have less than 3 pages, in this case, EndlessViewPager will give you an blank page. Be careful with it.
