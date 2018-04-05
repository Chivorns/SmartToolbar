# SmartToolbar
## Usage
### 1. Add the dependencies to your gradle file:

```gradle
dependencies {
    implementation 'com.github.chivorns:smarttoolbar:1.0.2'
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
    app:smtb_background="@color/colorPrimary"
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
