# SmartToolbar
## Usage
### Add the dependencies to your gradle file:

```gradle
dependencies {
    implementation 'com.github.chivorns:smarttoolbar:1.0.1'
}
```

### Add SmartToolbar to your layout:

```xml
<com.chivorn.smarttoolbar.SmartToolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="50dp"
    app:smtb_showRightBtn="false"
    app:smtb_titleText="Toolbar Title" />
```
