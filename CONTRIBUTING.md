Contribution Guideline
=====================

You are more than welcome to enhance, fix any demo you see in the app. A good way to start is look into existing [issues](https://github.com/amardeshbd/android-constraint-layout-cheatsheet/issues) and see 
if any of them interests you.

Development Guide
----------------------
It is important to keep consistency in the project. Here are some of the key things you need to keep in mind:

* Follow existing app architecture for previewing demo
    * Use `LayoutPreviewBaseActivity` if the demo does not need special user interaction
    * or, Create subclass of `LayoutPreviewBaseActivity` with user interaction specific binding. See `LayoutVisibilityGoneActivity` as an example.
* Follow existing resource naming convention. See [README](https://github.com/amardeshbd/android-constraint-layout-cheatsheet/blob/master/app/src/main/res/layout/.README.md)
* Create vector assets and save it to [raw resources](https://github.com/amardeshbd/android-constraint-layout-cheatsheet/tree/master/resources/vector) for later use. See [README](https://github.com/amardeshbd/android-constraint-layout-cheatsheet/blob/master/resources/vector/README.md) for example resource and how to create new resource.
