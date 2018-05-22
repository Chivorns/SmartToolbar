# SmartToolbar
[ ![Download](https://api.bintray.com/packages/chivorn/maven/smarttoolbar/images/download.svg) ](https://bintray.com/chivorn/maven/smarttoolbar/_latestVersion)

Customize your toolbar with beautiful style for your Android project
## Functionalities support
- Support center titleText and titleIcon
- Support change left and right button icon
- Support change background with color code and drawable with image, normal selector and gradient
- Support change visibility of left button , right button and title icon
- Support using with CoordinatorLayout to handle scroll event
- Automatically hide original actionbar
- No need to call showing method
- Easy to use
## Usage
### 1. Add the dependencies to your gradle file:

```gradle
dependencies {
    implementation 'com.github.chivorns:smarttoolbar:1.0.5'
}
```

### 2. Add SmartToolbar to your layout:

#### 2.1 Simple layout with default value
```xml
<com.chivorn.smarttoolbar.SmartToolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="50dp" />
```

#### 2.2 More  attributes

```xml
<com.chivorn.smarttoolbar.SmartToolbar
    android:layout_width="match_parent"
    android:layout_height="50dp"
    android:background="@color/colorPrimary"
    app:smtb_leftBtnIcon="@drawable/ic_arrow_back_white_24dp"
    app:smtb_rightBtnIcon="@drawable/ic_close_white_24dp"
    app:smtb_showLeftBtn="true"
    app:smtb_showRightBtn="true"
    app:smtb_showTitleIcon="false"
    app:smtb_titleColor="#f1ecec"
    app:smtb_titleIcon="@drawable/title_icon"
    app:smtb_titleText="Sample Title Text" />
```

### 3. Update at runtime
#### 3.1 Using Java

```java
// TODO: setTitleText
   smartToolbar.setTitleText("New Title Text");
```

```java
// TODO: setTitleTextColor
   smartToolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent)); // or
   smartToolbar.setTitleTextColor(Color.YELLOW);
```

```java
// TODO: setShowTitleIcon & setTitleIcon
   smartToolbar.setShowTitleIcon(true);
   smartToolbar.setTitleIcon(getResources().getDrawable(R.drawable.ic_close_white_24dp));
```

```java
// TODO: setBackgroundColor & setBackgroundDrawable
   smartToolbar.setBackground(getResources().getDrawable(R.drawable.smart_toolbar_bg_gradient)); // or
   smartToolbar.setBackgroundColor(Color.YELLOW);
```

```java
// TODO: setVisibility of left and right button
   smartToolbar.setShowLeftButton(true);
   smartToolbar.setShowRightButton(true);
```

```java
// TODO: setIcon to left and right button
   smartToolbar.setLeftButtonIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
   smartToolbar.setRightButtonIcon(getResources().getDrawable(R.drawable.ic_close_white_24dp));
```

```java
 // TODO: show custom status bar
    smartToolbar.showCustomStatusBar(this);
```

```java
  // TODO: set status bar color
  // smartToolbar.setStatusBarColor(getResources().getDrawable(R.drawable.smart_toolbar_bg_gradient));
  // smartToolbar.setStatusBarColor(Color.BLUE);
```

#### 3.2 Using Kotlin

```kotlin
// TODO: setTitleText
   smartToolbar.setTitleText("New Title Text")
```

```kotlin
// TODO: setTitleTextColor
   smartToolbar.setTitleTextColor(resources.getColor(R.color.colorAccent)) // or
   smartToolbar.setTitleTextColor(Color.YELLOW)
```

```kotlin
// TODO: setShowTitleIcon & setTitleIcon
   smartToolbar.setShowTitleIcon(true)
   smartToolbar.setTitleIcon(resources.getDrawable(R.drawable.ic_close_white_24dp))
```

```kotlin
// TODO: setBackgroundColor & setBackgroundDrawable
   smartToolbar.background = resources.getDrawable(R.drawable.smart_toolbar_bg_gradient) // or
   smartToolbar.setBackgroundColor(Color.YELLOW)
```

```kotlin
// TODO: setVisibility of left and right button
   smartToolbar.setShowLeftButton(true)
   smartToolbar.setShowRightButton(true)
```

```kotlin
// TODO: setIcon to left and right button
   smartToolbar.setLeftButtonIcon(resources.getDrawable(R.drawable.ic_arrow_back_white_24dp))
   smartToolbar.setRightButtonIcon(resources.getDrawable(R.drawable.ic_close_white_24dp))
```
